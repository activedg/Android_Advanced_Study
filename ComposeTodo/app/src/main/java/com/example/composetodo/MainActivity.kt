package com.example.composetodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composetodo.data.WellnessTask
import com.example.composetodo.ui.theme.ComposeTodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        MainNavGraph(navController = navController, modifier = Modifier.padding(it))
    }

}

@Composable
fun BottomNavigation(
    items: List<BottomNavItem> = listOf(BottomNavItem.Home, BottomNavItem.Profile),
    modifier: Modifier = Modifier,
    navController: NavController
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        modifier = modifier.wrapContentHeight(),
        tonalElevation = 1.dp,
    ) {
        //icon, label,selected, onclick
        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.screenRoute } == true,
                icon = {
                       Icon(
                           imageVector = item.image,
                           contentDescription = null
                       )
                },
                label = {
                        Text(text = stringResource(id = item.title))
                },
                onClick = {
                    navController.navigate(item.screenRoute){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

@Composable
fun StatefulCounter(
    modifier: Modifier = Modifier
){
    var waterCount by rememberSaveable { mutableStateOf(0)}
    var juiceCount by rememberSaveable { mutableStateOf(0)}

    Row {
        StatelessCounter(count = waterCount, onIncrement = { waterCount++}, modifier)
        StatelessCounter(count = juiceCount, onIncrement = { juiceCount++}, modifier)
    }
}

@Composable
fun StatelessCounter(
    count: Int,
    onIncrement : () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0){
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement,
            modifier = Modifier.padding(top = 8.dp),
            enabled = count < 10
        ) {
            Text("Add one")
        }
    }
}

class WellnessViewModel : ViewModel(){
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks : List<WellnessTask>
        get() = _tasks

    fun remove(item: WellnessTask){
        _tasks.remove(item)
    }

    fun chaneTaskChecked(item: WellnessTask, checked: Boolean){
        tasks.find { it.id == item.id }?.let {
            task -> task.checked.value = checked
        }
    }
}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i")}

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckChange
        )
        IconButton(onClick = onClose) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close"
            )
        }

    }
}

@Composable
fun WellnessTaskList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask>,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit
){
    LazyColumn(modifier = modifier){
        items(items = list,
            key = {task -> task.id}
        ){ task ->
            WellnessTaskItem(taskName = task.label,
                checked = task.checked.value,
                onCheckChange = { checked -> onCheckedTask(task, checked) },
                onClose = {onCloseTask(task)})
        }
    }
}
@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
){
    Column(modifier) {
        StatefulCounter(modifier)
        WellnessTaskList(
            list = wellnessViewModel.tasks,
            onCheckedTask = {task, checked -> wellnessViewModel.chaneTaskChecked(task, checked)},
            onCloseTask = {task -> wellnessViewModel.remove(task)})
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTodoTheme {
        WellnessScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavPreview(){
    ComposeTodoTheme {
        MainScreen()
    }
}

sealed class BottomNavItem(
    @StringRes val title: Int, val image: ImageVector, val screenRoute: String
){
    object Home : BottomNavItem(R.string.home_screen, Icons.Default.Home, "HOME")
    object Profile : BottomNavItem(R.string.profile_screen, Icons.Default.Person, "PROFILE")
}
