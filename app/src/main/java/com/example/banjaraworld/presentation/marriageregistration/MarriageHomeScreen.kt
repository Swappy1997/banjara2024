import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.R
import com.example.banjaraworld.presentation.commonwidgets.CommonAppBar2
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.ui.theme.onSecondary


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MarriageHomeScreen(modifier: Modifier = Modifier) {
    var scrollState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var onFilterIconClick by remember {
        mutableStateOf(false)
    }

    val count = remember {
        mutableStateOf(3)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(color = Color.White)
    ) {
        Column() {
            CommonAppBar2(
                text = "",
                onCartClick = { /*TODO*/ },
                onBackClick = { /*TODO*/ },
                onFavoriteClick = { /*TODO*/ },
                scrollState = scrollState,
                showSearchIcon = false,
                showCartIcon = false,
                scrollBehavior = scrollBehavior,
                showFavoriteIcon = true,
                favoriteCount = count, showFilterIcon = true,
                onFilterClick = {
                    onFilterIconClick = !onFilterIconClick
                }
            )
            FilterSheet(text = "Filter By", list = listOf())
            AnimatedVisibility(visible = onFilterIconClick) {
                Column {
                    CommonText(
                        text = "Filter By",
                        fontSize = BwDimensions.FONT_14,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    LazyRow(
                    ) {
                        item {
                            FilterCard(
                                text = "Education",
                                icon = painterResource(id = R.drawable.shop),

                                )
                            FilterCard(
                                text = "State",
                                icon = painterResource(id = R.drawable.outline_home_24)
                            )
                            FilterCard(
                                text = "Lastname",
                                icon = painterResource(id = R.drawable.man)
                            )
                            FilterCard(
                                text = "Marital Status ",
                                icon = painterResource(id = R.drawable.wedding)
                            )
                            FilterCard(
                                text = "Matches",
                                icon = painterResource(id = R.drawable.man)
                            )
                            FilterCard(
                                text = "Profession",
                                icon = painterResource(id = R.drawable.man)
                            )
                            FilterCard(
                                text = "New Joined",
                                icon = painterResource(id = R.drawable.man)
                            )
                            FilterCard(
                                text = "Height",
                                icon = painterResource(id = R.drawable.man)
                            )

                        }
                    }

                }
            }

            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White),
                state = scrollState
            ) {
                items(15) {

                    MarriageCardWithOverlay()

                }

            }
        }
    }

}

@Composable
fun MarriageCardWithOverlay() {
    var onFavClick by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_8),
        shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            ) {
                // Image
                Image(
                    painter = painterResource(id = R.drawable.sheet),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                )

                // Black fade gradient overlay at the bottom
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                                startY = 0f,
                                endY = 650f
                            )
                        )
                )

                // Text content on top of the image, aligned to the bottom
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    CommonText(
                        text = "S Rathod",
                        fontSize = BwDimensions.FONT_14,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    CommonText(
                        text = "Software Engineer",
                        fontSize = BwDimensions.FONT_11,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    CommonText(
                        text = "26 Years",
                        fontSize = BwDimensions.FONT_11,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        CommonText(
                            text = "Mumbai MH",
                            fontSize = BwDimensions.FONT_11,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier.clickable(
                        onClick = { onFavClick = !onFavClick },
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ),
                    imageVector = if (onFavClick) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = if (onFavClick) Color.Red else Color.Black
                )

                TextButton(onClick = { /*TODO*/ }) {
                    CommonText(
                        text = "Connect Now",
                        fontSize = BwDimensions.FONT_14,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }
        }
    }


}


@Composable
fun FilterCard(modifier: Modifier = Modifier, text: String = "", icon: Painter) {
    var onClick by remember {
        mutableStateOf(false)
    }
    if (onClick) {
        FilterSheet(
            text = text,
            list = listOf(
                "B.E",
                "M.E",
                "M.B.A",
                "M.C.A",
                "B.E",
                "M.E",
                "M.B.A",
                "M.C.A",
                "B.E",
                "M.E",
                "M.B.A",
                "M.C.A",
                "B.E",
                "M.E",
                "M.B.A",
                "M.C.A",
                "B.E",
                "M.E",
                "M.B.A",
                "M.C.A",
                "B.E", "M.E", "M.B.A", "M.C.A",
                "B.E", "M.E", "M.B.A", "M.C.A",
            )
        )
    }

    AssistChip(
        onClick = {
            onClick = !onClick
        },
        label = {
            CommonText(
                text = text,
                fontSize = BwDimensions.SUB_TITTLE_FONT_SIZE,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
            )
        },
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = "male",
                tint = onSecondary, modifier = Modifier.size(20.dp)
            )
        },
        modifier = modifier.padding(BwDimensions.PADDING_4),
        colors = AssistChipDefaults.assistChipColors(containerColor = Color.White),
        border = null,
        elevation = AssistChipDefaults.assistChipElevation(elevation = BwDimensions.ELEVATION_HEIGHT)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSheet(
    modifier: Modifier = Modifier,
    text: String,
    list: List<String>
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var isSearchVisible by remember { mutableStateOf(false) }


    // Filtered list based on search query
    val filteredList = if (searchQuery.isEmpty()) list else list.filter {
        it.contains(
            searchQuery,
            ignoreCase = true
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier
                .height(400.dp)
                .navigationBarsPadding(),
            containerColor = Color.White,
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Column(
                modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Row with Title and Search Icon
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CommonText(
                        text = "Filter By $text",
                        fontSize = BwDimensions.FONT_17,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                    // Search Icon
                    IconButton(onClick = { isSearchVisible = !isSearchVisible }) {
                        Icon(Icons.Default.Search, contentDescription = "Search Icon")
                    }
                }

                // Show Search Field if icon is clicked
                if (isSearchVisible) {
                    CommonOutlineTextField(
                        modifier = modifier,
                        dummyText = "Search $text",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                        }
                    )
                }

                LazyColumn(
                    modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(filteredList.size) {
                        CommonText(
                            text = filteredList[it],
                            fontSize = BwDimensions.FONT_14,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,
                            modifier = modifier.padding(9.dp)
                        )
                    }
                }
            }
        }
    }
}

