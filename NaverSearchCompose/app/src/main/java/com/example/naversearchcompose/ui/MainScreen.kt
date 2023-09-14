package com.example.naversearchcompose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.naversearchcompose.domain.model.News
import com.example.naversearchcompose.model.PageState
import com.example.naversearchcompose.ui.MainContract.*

@Composable
fun MainRoute(
    viewModel: MainViewModel = hiltViewModel()
){
    val state : MainState by viewModel.state.collectAsStateWithLifecycle()

    MainScreen(
        state = state,
        onLoadMore = viewModel::load
    )
}

@Composable
private fun MainScreen(
    state: MainState,
    onLoadMore: () -> Unit = {},
    lazyColumnListState: LazyListState = rememberLazyListState()
){
    val shouldStartPaginate by remember {
        derivedStateOf{
            (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
                    && state.pageState != PageState.EXHAUST
        }
    }

    LaunchedEffect(shouldStartPaginate){
        if (shouldStartPaginate && state.pageState == PageState.IDLE) onLoadMore()
    }

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
        ) {
            LazyColumn(
                state = lazyColumnListState
            ){
                itemsIndexed(state.newsList){ index, item ->
                    NewsItem(news = item, isLastItem = index == state.newsList.lastIndex)
                }
            }

            if (state.pageState == PageState.LOADING){
                CircularProgressIndicator(modifier = Modifier.size(50.dp).align(Alignment.Center))
            }
        }
    }
}

@Composable
private fun NewsItem(
    news: News,
    isLastItem: Boolean = false
){
    Column {
        Text(text = news.title, style = MaterialTheme.typography.h6)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = news.description, style = MaterialTheme.typography.subtitle1)

        Spacer(modifier = Modifier.height(4.dp))

        if (isLastItem.not()){
            Divider(modifier = Modifier.fillMaxWidth())
        }
    }
}