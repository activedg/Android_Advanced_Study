package com.example.composetodo.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetodo.R
import com.example.composetodo.data.TodoItem
import com.example.composetodo.ui.theme.ComposeTodoTheme
import com.example.composetodo.ui.theme.Ivory
import com.example.composetodo.ui.theme.Teal200

@Composable
fun TaskScreen(
    modifier: Modifier = Modifier
){
    var searchTitle by rememberSaveable { mutableStateOf("") }
    Box {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = stringResource(id = R.string.todo_title),
                fontStyle = FontStyle.Italic,
                fontSize = 24.sp,
                modifier = Modifier.padding(24.dp),
            )
            SearchBar(value = searchTitle, onTextChange = {searchTitle = it})
            Spacer(modifier = Modifier.height(20.dp))
            TodoList()
        }
        ExtendedFloatingActionButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            containerColor = Teal200
        ){
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
            Text(text = "ADD TASK",
                modifier = Modifier.padding(start = 4.dp))
        }
    }

}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onTextChange : (String) -> Unit
){
    TextField(
        value = value,
        onValueChange = onTextChange,
        leadingIcon ={
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )},
        placeholder = {
            Text(stringResource(id = R.string.todo_search_hint))
        },
        maxLines = 1,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        // 백그라운드 컬러 변경 및 밑줄 제거
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Ivory,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        // 둥글게 처리
        shape = MaterialTheme.shapes.medium
    )
}

val todoItemList = listOf(TodoItem("send email", "email email send email"),
    TodoItem("water the plants", "water the plants water the plants"),
    TodoItem("clean the bathroom", "clean the bathroom clean the bathroom"),
    TodoItem("feed the cat", "feed the cat feed the cat"),
    TodoItem("send email", "email email send text"),
    TodoItem("go to park", "go to go to park"),
    TodoItem("play music", "play music play music"),
    TodoItem("completing programming task", "completing programming task completing programming task"),
    TodoItem("go to restaurant", "go to restaurant go to restaurant"),
    TodoItem("water the plants", "water the plants water the plants"),
    TodoItem("water the plants", "water the plants water the plants"),
    TodoItem("send email", "email email send text"),
    TodoItem("go to park", "go to go to park")
)

@Composable
fun TodoList(
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier){
        items(todoItemList){
            item -> TodoListItem(item = item)
        }
    }
}

@Composable
fun TodoListItem(
    modifier: Modifier = Modifier,
    item: TodoItem,
){
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }
    Surface(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 16.dp),
        shape = MaterialTheme.shapes.medium,
        color = Color.LightGray
    ){
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Checkbox(
                    checked = item.checked.value,
                    onCheckedChange = { item.checked.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Teal200
                    )
                )
                Text(
                    text = item.title,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.SemiBold
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,

                        )
                }
            }
            if (expanded){
                
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskScreen(){
    ComposeTodoTheme {
        TaskScreen()
    }
}