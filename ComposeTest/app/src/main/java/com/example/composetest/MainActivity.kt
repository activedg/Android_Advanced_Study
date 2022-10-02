package com.example.composetest


import android.os.Bundle
import android.widget.ImageView
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composetest.data.MessageTest
import com.example.composetest.data.SampleData
import com.example.composetest.ui.theme.ComposeTestTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<HelloViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(viewModel)
                }
            }
        }
    }
}

class HelloViewModel : ViewModel(){
    private val _name = MutableLiveData("")
    val name : LiveData<String>
        get() = _name

    fun onNameChange(newName: String){
        _name.value = newName
    }
}

@Composable
fun MyApp(viewModel: HelloViewModel){
    // State Hoisting
    var shouldShowOnBoarding by rememberSaveable {
        mutableStateOf(true)
    }

    val name: String by viewModel.name.observeAsState("")

    if (shouldShowOnBoarding){
        OnBoardingScreen (onContinuedClick = {shouldShowOnBoarding = false},
            name=name, onNameChange={viewModel.onNameChange(it)} )
    } else {
        Conversation(messages = SampleData.conversationSample)
    }
}

@Composable
fun MessageCard(msg: MessageTest) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "launcher",
            modifier = Modifier
                .size(40.dp)
                // 원 형태로 Clip
                .clip(CircleShape)
                // border 설정
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape),
            contentScale = ContentScale.FillBounds
        )
        // 수평 마진 추가
        Spacer(modifier = Modifier.width(8.dp))

        // 메모리에 로컬 상태 저장
        var isExpanded by rememberSaveable {
            mutableStateOf(false)
        }

        // surface color 변경
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
            // 수직 마진 추가
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.0.dp)
            ) {
                Text(
                    text = msg.name,
                    modifier = Modifier.padding(all = 4.0.dp),
                    // click 된 경우 전부다 표시, 아니면 한줄만
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

    }

}

@Composable
fun OnBoardingScreen(onContinuedClick: () -> Unit, name: String, onNameChange : (String) -> Unit){
    Surface{
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to Compose World")
            Button(
                modifier = Modifier.padding(vertical = 20.dp),
                onClick = onContinuedClick
            ) {
                Text(text = "Continue")
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Hello $name",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.titleLarge
            )
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Name")}
            )
        }

    }
}

@Composable
fun Conversation(messages: List<MessageTest>){
    Column {
        var expanded by remember {
            mutableStateOf(false)
        }
        val extraPadding by animateDpAsState(
            if (expanded) 48.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(all = 4.dp)
        ) {
            Row(modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp),
                ) {
                    Text(text = "Hello, ")
                    Text(
                        text = "Name",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                    if (expanded){
                        Text(
                            text = ("Composem ipsum color sit lazy, " +
                                    "padding theme elit, sed do bouncy. ").repeat(4),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                }

                IconButton(
                    onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (expanded) {
                            stringResource(R.string.show_less)
                        } else{
                            stringResource(R.string.show_more)
                        }
                    )
//                    Text(
//                        if (expanded) "Show Less" else "Show More",
//                        color = Color.White
//                        )
                }
            }
        }
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }
}

//@Preview(
//    name = "Light Mode",
//    showBackground = true
//)
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    name = "Dark Mode",
//    showBackground = true
//)
//@Composable
//fun PreviewMessageCard(){
//    ComposeTestTheme {
//        Conversation(messages = SampleData.conversationSample)
//    }
//}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview(){
    ComposeTestTheme {
        OnBoardingScreen(onContinuedClick = {}, name="", onNameChange = {})
    }
}

@Preview(showBackground = true)
@Composable
fun OnConversationPreview(){
    ComposeTestTheme {
        Conversation(messages = SampleData.conversationSample)
    }
}