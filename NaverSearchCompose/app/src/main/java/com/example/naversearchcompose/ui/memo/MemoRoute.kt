package com.example.naversearchcompose.ui.memo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    // Local에서 가져와서 읽기
    // 읽음 여부 처리하기(페이지 들어가는 순간 먼저 로직 수행 -> 읽음 처리)
    // Room에서 읽음 여부 처리하는 거 테스트
    // 테스트용으로 데이터 추가하는 버튼도 만들기

    val state: MemoState by viewModel.state.collectAsStateWithLifecycle()

    MemoScreen(
        state = state,
        onClickAdd = viewModel::addMemos
    )
}

@Composable
private fun MemoScreen(
    state : MemoState,
    onClickAdd: () -> Unit = {}
){
    Scaffold {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn{
                items(state.memoList){ item ->
                    MemoContent(memo = item)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onClickAdd) {
                Text(text = "추가")
            }
        }
    }
}

@Composable
private fun MemoContent(
    memo: MemoUiModel,
){
    Row(
        modifier = Modifier.padding(24.dp)
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