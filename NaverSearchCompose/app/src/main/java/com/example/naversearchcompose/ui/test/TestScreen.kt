package com.example.naversearchcompose.ui.test

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TestRoute(
    navigateToMain: () -> Unit,
    navigateToMemo: () -> Unit
){
    TestScreen(navigateToMain = navigateToMain, navigateToMemo = navigateToMemo)
}

@Composable
private fun TestScreen(
    navigateToMain : () -> Unit,
    navigateToMemo: () -> Unit
){
    Box(modifier = Modifier.fillMaxSize()){
        Button(onClick = navigateToMain, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "메인으로")
        }

        Button(onClick = navigateToMemo, modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(text = "메모로")
        }
    }
}