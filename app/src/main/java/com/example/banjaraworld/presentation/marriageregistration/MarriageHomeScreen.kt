import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Height
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.Utils.formatText
import com.example.banjaraworld.common.utils.Utils.isScrolled
import com.example.banjaraworld.presentation.commonwidgets.CommonAppBar2
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MarriageHomeScreen(modifier: Modifier = Modifier, onContinueClick: () -> Unit) {
    var scrollState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var onFilterIconClick by remember {
        mutableStateOf(false)
    }
    var list = listOf<UserHomeData>(
        UserHomeData(
            image = R.drawable.one,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E", height = "5 ft 8 inches", age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.two,
            name = "S.Rathoddddddddddd",
            location = "TN,India",
            profession = "Fashion designer",
            education = "Post graduate", height = "5 ft 8 inches", age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.three,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E", height = "5 ft 8 inches", age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.eight,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E", height = "5 ft 8 inches", age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.seven,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E", height = "5 ft 8 inches", age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.nine,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E", height = "5 ft 8 inches", age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.ten,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E", height = "5 ft 8 inches", age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.four,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E", height = "5 ft 8 inches", age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.five,
            name = "S.Swaraj",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E", height = "5 ft 8 inches", age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.six,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E", height = "5 ft 8 inches", age = "26 years old"
        ),

        )
    val padding by animateDpAsState(
        targetValue = if (scrollState.isScrolled) 0.dp else 80.dp,
        animationSpec = tween(durationMillis = 300)
    )

    val count = remember {
        mutableStateOf(3)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(color = Color.White)
    ) {
        Column {
            CommonAppBar2(modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(animationSpec = tween(durationMillis = 300))
                .height(height = if (scrollState.isScrolled) 0.dp else 80.dp),
                text = "",
                onCartClick = { /*TODO*/ },
                onBackClick = { /*TODO*/ },
                onFavoriteClick = { /*TODO*/ },
                scrollState = scrollState,
                showSearchIcon = false,
                showCartIcon = false,
                scrollBehavior = scrollBehavior,
                showFavoriteIcon = true,
                favoriteCount = count,
                showFilterIcon = false,
                showUserProfile = true,
                onFilterClick = {
                    onFilterIconClick = !onFilterIconClick
                })
            if (!scrollState.isScrolled) {
                Text(
                    text = formatText(
                        formattedText = "Happiness",
                        discountColor = onPrimary,
                        prefix = "Interact with your ",
                    ),
                    fontSize = BwDimensions.FONT_14,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .animateContentSize(animationSpec = tween(durationMillis = 300))
                )
            }




            Card(
                modifier.fillMaxWidth(),
                contentColor = Color.White
            ) {
                LazyRow(
                    modifier = if (scrollState.isScrolled) Modifier
                        .fillMaxWidth()
                        .systemBarsPadding()
                        .padding(top = padding)
                    else Modifier,
                ) {
                    item {
                        FilterCard(
                            text = "Filter",
                            icon = Icons.Outlined.FilterAlt,

                            )
                        FilterCard(
                            text = "State",
                            icon = null,
                        )
                        FilterCard(
                            text = "Gotra", icon = Icons.Outlined.Person,
                        )
                        FilterCard(
                            text = "Marital Status ",
                            icon = null,
                        )
                        FilterCard(
                            text = "Matches", icon = null
                        )
                        FilterCard(
                            text = "Profession", icon = Icons.Outlined.Work
                        )
                        FilterCard(
                            text = "Height", icon = Icons.Outlined.Height,
                        )

                    }
                }

            }



            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White), state = scrollState
            ) {
                items(list.size) {

                    MarriageCardWithOverlay(onContinueClick, list[it])

                }

            }
        }
    }

}

@Composable
fun MarriageCardWithOverlay(onContinueClick: () -> Unit, userHomeData: UserHomeData) {
    var onFavClick by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BwDimensions.PADDING_8)
    ) {
        Card(
            modifier = Modifier
                .width(150.dp)
                .height(200.dp)
                .padding(BwDimensions.PADDING_8),
            shape = RoundedCornerShape(8.dp), // Adjust the corner radius as needed
            contentColor = Color.White
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Your Image
                AsyncImage(
                    model = userHomeData.image,
                    contentDescription = "profile",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop // Adjust image scaling
                )

                // Gradient Overlay for top-end fade
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color.Black.copy(alpha = 0.1f), Color.Transparent),
                                start = Offset(100f, 0f), // From the top-end
                                end = Offset(100f, 100f) // Towards bottom-start
                            )
                        )
                )

                // Gradient Overlay for bottom-end fade
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.2f)),
                                start = Offset(90f, 100f), // Bottom-end
                                end = Offset(100f, 0f) // Towards top-start
                            )
                        )
                )

                // Icon at the top-end
                Icon(
                    imageVector = if (onFavClick) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "like",
                    modifier = Modifier
                        .clickable(onClick = { onFavClick = !onFavClick },
                            indication = null,
                            interactionSource = remember {
                                MutableInteractionSource()
                            })
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    tint = if (onFavClick) Color.Red else Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(BwDimensions.PADDING_8),
            horizontalAlignment = Alignment.Start
        ) {
            CommonText(
                text = userHomeData.name,
                fontSize = BwDimensions.FONT_14,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
            CommonText(
                text = userHomeData.profession,
                fontSize = BwDimensions.FONT_11,
                color = onSecondary,
                fontWeight = FontWeight.Medium
            )
            CommonText(
                text = userHomeData.education,
                fontSize = BwDimensions.FONT_11,
                color = onSecondary,
                fontWeight = FontWeight.Medium
            )
            CommonText(
                text = userHomeData.height,
                fontSize = BwDimensions.FONT_11,
                color = onSecondary,
                fontWeight = FontWeight.Medium
            )
            CommonText(
                text = userHomeData.age,
                fontSize = BwDimensions.FONT_11,
                color = onSecondary,
                fontWeight = FontWeight.Medium
            )

            CommonText(
                text = userHomeData.location,
                fontSize = BwDimensions.FONT_11,
                color = onSecondary,
                fontWeight = FontWeight.Medium
            )
            Card(
                shape = RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS), elevation = 0.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(onPrimary.copy(alpha = 0.1f), Color.Transparent)
                            )
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable(
                                onClick = { onContinueClick() },
                                indication = null,
                                interactionSource = remember {
                                    MutableInteractionSource()
                                }
                            )
                            .padding(BwDimensions.PADDING_2),
                        horizontalArrangement = Arrangement.spacedBy(BwDimensions.PADDING_4)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = "call",
                            tint = onPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                        CommonText(
                            text = "Connect now",
                            fontSize = BwDimensions.FONT_11,
                            color = onPrimary,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }


        }
    }


}


@Composable
fun FilterCard(modifier: Modifier = Modifier, text: String = "", icon: ImageVector?) {
    var onClick by remember {
        mutableStateOf(false)
    }
    if (onClick) {
        FilterSheet(
            text = text, list = listOf(
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
                fontSize = BwDimensions.TITTLE_FONT_SIZE,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
            )
        },
        leadingIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "male",
                    tint = Color.Black,
                    modifier = Modifier.size(25.dp)
                )
            }
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
    modifier: Modifier = Modifier, text: String, list: List<String>
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var isSearchVisible by remember { mutableStateOf(false) }


    // Filtered list based on search query
    val filteredList = if (searchQuery.isEmpty()) list else list.filter {
        it.contains(
            searchQuery, ignoreCase = true
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
                modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
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
                    CommonOutlineTextField(modifier = modifier,
                        dummyText = "Search $text",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                        })
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


@Stable
data class UserHomeData(
    val image: Int,
    val name: String,
    val age: String,
    val location: String,
    val profession: String,
    val height: String,
    val education: String,
)
