package com.example.composetest


import android.os.Bundle
import android.widget.ImageView
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composetest.data.AlignYourBodyElement
import com.example.composetest.data.FavoriteCardElement
import com.example.composetest.data.MessageTest
import com.example.composetest.data.SampleData
import com.example.composetest.ui.theme.ComposeTestTheme
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(){
    ComposeTestTheme {
        Scaffold(
            bottomBar = {SootheBottomNavigation()}
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
){
    TextField(
        value = "",
        onValueChange = {},
        // Leading Icon 추가
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        } ,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(horizontal = 16.dp)
    )
}

val alignYourBodyData = ArrayList<AlignYourBodyElement>().apply {
    add(AlignYourBodyElement(R.drawable.ab1_inversions, R.string.ab1_inversions))
    add(AlignYourBodyElement(R.drawable.ab2_quick_yoga, R.string.ab2_quick_yoga))
    add(AlignYourBodyElement(R.drawable.ab3_stretching, R.string.ab3_stretching))
    add(AlignYourBodyElement(R.drawable.ab4_tabata, R.string.ab4_tabata))
    add(AlignYourBodyElement(R.drawable.ab5_hiit, R.string.ab5_hiit))
    add(AlignYourBodyElement(R.drawable.ab6_pre_natal_yoga, R.string.ab6_pre_natal_yoga))
}

val favoriteCardData = ArrayList<FavoriteCardElement>().apply {
    add(FavoriteCardElement(R.drawable.fc1_short_mantras, R.string.fc2_nature_meditations))
    add(FavoriteCardElement(R.drawable.fc2_nature_meditations, R.string.fc2_nature_meditations))
    add(FavoriteCardElement(R.drawable.fc3_stress_and_anxiety, R.string.fc2_nature_meditations))
    add(FavoriteCardElement(R.drawable.fc4_self_massage, R.string.fc2_nature_meditations))
    add(FavoriteCardElement(R.drawable.fc5_overwhelmed, R.string.fc2_nature_meditations))
    add(FavoriteCardElement(R.drawable.fc6_nightly_wind_down, R.string.fc2_nature_meditations))
}


@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(horizontal = 16.dp)
    ){
        items(alignYourBodyData){
            item -> AlignYourBody(drawable = item.drawable, text = item.text)
        }
    }
}
@Composable
fun AlignYourBody(
    @DrawableRes drawable : Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
            )
        )
    }
}

@Composable
fun FavoriteCollectionCardGrid(
    modifier: Modifier = Modifier
){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .height(120.dp)
            .padding(horizontal = 16.dp)
    ){
        items(favoriteCardData){
           item -> FavoriteCollectionCard(
            drawable = item.drawable,
            text = item.text,
            modifier = Modifier.height(56.dp)
        )
        }
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable : Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
){
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
                // matchParentSize로 parent 맞추기 가능
        ) {
           Image(
               painter = painterResource(drawable),
               contentDescription = null,
               contentScale = ContentScale.Crop,
               modifier = Modifier.size(56.dp)
           )
            Text(
                text = stringResource(text),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(modifier) {
        Text(
            text = stringResource(title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
private fun SootheBottomNavigation(
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier,
        tonalElevation = 1.dp) {
        NavigationBarItem(
            // icon, label, selected, onclick
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            // icon, label, selected, onclick
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            },
            selected = true,
            onClick = {}
        )

    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
    ){
        SearchBar()
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(R.string.favorite_collections) {
            FavoriteCollectionCardGrid()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun MyAppPreview(){
    ComposeTestTheme {
        MyApp()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyPreview(){
    ComposeTestTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}