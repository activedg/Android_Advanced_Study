package com.example.naversearchcompose.ui.memo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.naversearchcompose.model.MemoUiModel
import com.example.naversearchcompose.ui.memo.MemoContract.*
import com.example.naversearchcompose.ui.theme.NaverSearchComposeTheme

@Composable
fun MemoRoute(
    viewModel : MemoViewModel = hiltViewModel()
){
    val state: MemoState by viewModel.state.collectAsStateWithLifecycle()

    MemoScreen(
        state = state,
        onClickAdd = viewModel::addMemos,
        onClickDelete = viewModel::deleteMemos
    )
}

@Composable
private fun MemoScreen(
    state : MemoState,
    onClickAdd: () -> Unit = {},
    onClickDelete: () -> Unit = {}
){
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                Row {
                    Button(onClick = onClickAdd) {
                        Text(text = "메모 추가하기")
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Button(onClick = onClickDelete) {
                        Text(text = "메모 삭제하기")
                    }
                }

            }

            items(state.memoList){ item ->
                MemoContent(memo = item)
            }
        }
    }
}

@Composable
private fun MemoContent(
    memo: MemoUiModel,
){
    Row(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Text(text = memo.content)

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = if (memo.isRead) "읽음" else "안읽음",
            style = MaterialTheme.typography.button.copy(
                color = if (memo.isRead) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MemoScreenPreview(){
    NaverSearchComposeTheme {
        MemoScreen(
            state = MemoState(
                memoList = listOf(
                    MemoUiModel(
                        id = 1,
                        content = "test",
                        isRead = false
                    )
                )
            )
        )
    }
}