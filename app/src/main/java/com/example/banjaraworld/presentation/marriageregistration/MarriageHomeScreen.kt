import android.util.Log
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
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Height
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.Utils.formatText
import com.example.banjaraworld.common.utils.Utils.isScrolled
import com.example.banjaraworld.presentation.commonwidgets.CommonAppBar2
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MarriageHomeScreen(
    modifier: Modifier = Modifier,
    onContinueClick: () -> Unit,
    viewModel: MarriageHomeScreenViewModel = hiltViewModel(),
    onBackClick: () -> Boolean,
    onMyProfileClick: () -> Unit
) {
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
            education = "B.E",
            height = "5 ft 8 inches",
            age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.two,
            name = "S.Rathoddddddddddd",
            location = "TN,India",
            profession = "Fashion designer",
            education = "Post graduate",
            height = "5 ft 8 inches",
            age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.three,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E",
            height = "5 ft 8 inches",
            age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.eight,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E",
            height = "5 ft 8 inches",
            age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.seven,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E",
            height = "5 ft 8 inches",
            age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.nine,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E",
            height = "5 ft 8 inches",
            age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.ten,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E",
            height = "5 ft 8 inches",
            age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.four,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E",
            height = "5 ft 8 inches",
            age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.five,
            name = "S.Swaraj",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E",
            height = "5 ft 8 inches",
            age = "26 years old"
        ),
        UserHomeData(
            image = R.drawable.six,
            name = "S.Rathod",
            location = "Mh,India",
            profession = "Software engineer",
            education = "B.E",
            height = "5 ft 8 inches",
            age = "26 years old"
        ),

        )
    val padding by animateDpAsState(
        targetValue = if (scrollState.isScrolled) 0.dp else 80.dp,
        animationSpec = tween(durationMillis = 300)
    )
    val count = viewModel.count.collectAsStateWithLifecycle()
    Log.d("TAG", "MarriageHomeScreen: ${count.value}")

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
                favoriteCount = count.value,
                showFilterIcon = false,
                showUserProfile = true,
                onFilterClick = {
                    onFilterIconClick = !onFilterIconClick
                }, onUserProfileClick = {
                    onMyProfileClick()
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
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White), state = scrollState
            ) {
                items(list.size) {

                    MarriageCardWithOverlay(onContinueClick, list[it], viewModel)

                }

            }
        }
    }

}

@Composable
fun MarriageCardWithOverlay(
    onContinueClick: () -> Unit,
    userHomeData: UserHomeData,
    viewModel: MarriageHomeScreenViewModel
) {
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
                        .clickable(onClick = {
                            if (!onFavClick) {
                                // Increment count if it's not favorited (clicked for the first time)
                                viewModel.incrementCount()
                            } else {
                                // Decrement count if already favorited (clicked again to un-favorite)
                                viewModel.decrementCount()  // You will need to add this function in the ViewModel
                            }
                            onFavClick = !onFavClick
                        },
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
                            .clickable(onClick = { onContinueClick() },
                                indication = null,
                                interactionSource = remember {
                                    MutableInteractionSource()
                                })
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
    val filteredList =
        listOf("State", "Distance", "Height", "Marital Status", "Gotra", "Your Matches")
    var selectedIndex by remember { mutableStateOf(0) }
    val state = listOf<String>(
        "Maharashtra",
        "Telangana",
        "TamilNadu",
        "Karnataka",
        "Kerala",
        "Andhra Pradesh"
    )
    val distance = listOf<String>("within 100km", "within 200km", "within 300km", "within 400km")
    val checkedStates =
        remember { mutableStateListOf<Boolean>().apply { addAll(List(state.size) { false }) } }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.height(600.dp),
            containerColor = Color.White,
            onDismissRequest = {
                showBottomSheet = false
            },
            shape = RoundedCornerShape(
                topStart = BwDimensions.ROUND_CORNER_RADIUS,
                topEnd = BwDimensions.ROUND_CORNER_RADIUS
            ),
            dragHandle = null,
            sheetState = sheetState,
        ) {
            Column(
                modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), // Ensures the content takes the available height
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Row with Title and Close Icon
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(BwDimensions.PADDING_12),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CommonText(
                        text = "Filter",
                        fontSize = BwDimensions.FONT_23,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    // Close Icon
                    IconButton(onClick = { showBottomSheet = false }) {
                        Icon(Icons.Default.Close, contentDescription = "Close Icon")
                    }
                }

                HorizontalDivider()

                // LazyColumns inside a Row with weight to allow flexible layout
                Row(
                    modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(filteredList.size) { it ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Custom Rounded Divider
                                if (selectedIndex == it) {
                                    Box(
                                        modifier = Modifier
                                            .padding(top = BwDimensions.PADDING_12)
                                            .size(width = 8.dp, height = 50.dp)
                                            .clip(RoundedCornerShape(BwDimensions.ROUND_CORNER_RADIUS))
                                            .background(color = onPrimary)
                                    )
                                }

                                CommonText(
                                    text = filteredList[it],
                                    fontSize = BwDimensions.FONT_17,
                                    color = if (selectedIndex == it) onPrimary else Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = modifier
                                        .padding(
                                            start = BwDimensions.PADDING_4,
                                            top = BwDimensions.PADDING_8
                                        )
                                        .clickable(
                                            onClick = { selectedIndex = it },
                                            indication = null,
                                            interactionSource = remember {
                                                MutableInteractionSource()
                                            }
                                        )
                                        .padding(9.dp)
                                )
                            }
                        }
                    }

                    VerticalDivider()

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                    ) {
                        items(state.size) { index ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Checkbox with state control
                                Checkbox(
                                    checked = checkedStates[index],
                                    onCheckedChange = { checked ->
                                        checkedStates[index] = checked
                                    }, colors = androidx.compose.material.CheckboxDefaults.colors(
                                        onPrimary
                                    )
                                )
                                // CommonText that displays the list item
                                CommonText(
                                    text = state[index],
                                    fontSize = BwDimensions.FONT_11,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                        }
                    }
                }

                // Buttons Row at the bottom
                Card(elevation = BwDimensions.ELEVATION_HEIGHT) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(BwDimensions.PADDING_12), // Ensure padding at the bottom
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TextButton(onClick = { /* Clear filters logic */ }) {
                            CommonText(
                                text = "Clear Filters",
                                fontSize = BwDimensions.FONT_17,
                                color = onPrimary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.width(BwDimensions.SPACING_16))
                        // Apply button
                        CommonButton(
                            text = "Apply",
                            onClick = { /*TODO*/ },
                            modifier = modifier,
                            color = onPrimary
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
