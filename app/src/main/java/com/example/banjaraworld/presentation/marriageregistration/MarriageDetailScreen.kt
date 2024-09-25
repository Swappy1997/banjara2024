package com.example.banjaraworld.presentation.marriageregistration

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.shopping.ShoppingDivider
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.Utils.formatDiscountText
import com.example.banjaraworld.common.utils.Utils.shareContent
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.ui.theme.Pink40
import com.example.banjaraworld.ui.theme.Pink80
import com.example.banjaraworld.ui.theme.Purple40
import com.example.banjaraworld.ui.theme.darkGreen
import com.example.banjaraworld.ui.theme.onPrimary
import com.example.banjaraworld.ui.theme.onSecondary
import kotlinx.coroutines.launch
import kotlin.text.Typography.amp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarriageDetailScreen(modifier: Modifier = Modifier, isProfile: Boolean) {
    val images = listOf(R.drawable.one, R.drawable.two, R.drawable.three)
    val pagerState = rememberPagerState(pageCount = { images.size })
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val listState = rememberLazyListState() // Manage the list state for scroll control
    val coroutineScope = rememberCoroutineScope()

    Box {
        Box(
            modifier = modifier
                .fillMaxSize()
                .systemBarsPadding(),
        ) {
            LazyColumn(
                state = listState // Attach the state to the LazyColumn
            ) {
                item {
                    UserPhotos(images = images, pagerState = pagerState, screenWidth = screenWidth)
                }
                item {
                    Spacer(modifier = Modifier.height(BwDimensions.SPACING_12))
                }
                item {
                    AboutUser(userName = "Swapnil", isProfile = isProfile)
                    Spacer(modifier = Modifier.height(BwDimensions.SPACING_12))
                }
                item {
                    BasicDetail(isProfile = isProfile)
                    Spacer(modifier = Modifier.height(BwDimensions.SPACING_8))
                }
                item {
                    ContactDetail(isProfile = isProfile)
                }
                item {
                    FamilyDetails(isProfile = isProfile)
                }
                item {
                    CareerAndEducation(isProfile = isProfile)
                }

                item {
                    HobbiesAndInterests(isProfile = isProfile)
                }
                item {
                    Spacer(modifier = Modifier.padding(bottom = 100.dp))
                }

            }

            if (listState.firstVisibleItemIndex > 0) {
                AssistChip(
                    colors = AssistChipDefaults.assistChipColors(containerColor = Color.White),
                    border = null,
                    elevation = AssistChipDefaults.assistChipElevation(elevation = 24.dp),
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0) // Scroll to the top when clicked
                        }
                    },
                    label = {
                        CommonText(
                            text = "Swapnil R",
                            fontSize = BwDimensions.FONT_11,
                            color = onPrimary,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.ArrowUpward,
                            contentDescription = "Go to Top",
                            tint = onPrimary
                        )

                    },
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }

        }
        ConnectNow(modifier.align(Alignment.BottomCenter))


    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HobbiesAndInterests(modifier: Modifier = Modifier, isProfile: Boolean) {
    val hobbies = listOf<Hobbies>(
        Hobbies(Icons.Default.FoodBank, "Cooking"),
        Hobbies(Icons.Default.SportsGymnastics, "Working Out"),
        Hobbies(Icons.Default.SportsGymnastics, "Working Out"),
        Hobbies(Icons.Default.FoodBank, "Foodie"),
        Hobbies(Icons.Default.ShoppingBasket, "Fashion"),
        Hobbies(Icons.Default.MusicNote, "Singing"),
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = BwDimensions.ELEVATION_HEIGHT)
    ) {
        Box(modifier = modifier) {
            if (isProfile) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "edit",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(BwDimensions.PADDING_12)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BwDimensions.PADDING_12)
            ) {
                CommonText(
                    text = "Hobbies $amp Interests",
                    fontSize = BwDimensions.FONT_17,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                FlowRow {
                    hobbies.forEach { hobbies ->
                        SuggestionChip(modifier = Modifier.padding(horizontal = BwDimensions.PADDING_4),
                            onClick = { /*TODO*/ },
                            label = {
                                CommonText(
                                    text = hobbies.hobbieName,
                                    fontSize = BwDimensions.FONT_11,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Medium
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = hobbies.imageVector,
                                    contentDescription = hobbies.hobbieName,
                                    tint = Pink40
                                )
                            })

                    }
                }

            }
        }
    }
}

@Composable
fun ConnectNow(modifier: Modifier) {
    val context = LocalContext.current
    var onCallClick by remember {
        mutableStateOf(false)
    }
    var onFavClick by remember {
        mutableStateOf(false)
    }
    var onShareClick by remember {
        mutableStateOf(false)
    }
    if (onCallClick) {
        UpgradeToPremium(text = "Swap", onDismiss = { onCallClick = false })

    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = BwDimensions.ELEVATION_HEIGHT)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(imageVector = Icons.Default.Phone,
                    contentDescription = null,
                    tint = onSecondary,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            onCallClick = true
                        }
                        .border(
                            1.dp, Color.Gray, RoundedCornerShape(4.dp)
                        )
                        .padding(BwDimensions.PADDING_4))
                Spacer(modifier = Modifier.padding(BwDimensions.PADDING_8))
                Icon(imageVector = if (onFavClick) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (onFavClick) Color.Red else onSecondary,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            onFavClick = !onFavClick
                        }
                        .border(
                            1.dp, Color.Gray, RoundedCornerShape(4.dp)
                        )
                        .padding(BwDimensions.PADDING_4))
                Spacer(modifier = Modifier.padding(BwDimensions.PADDING_8))
                Icon(imageVector = Icons.Default.Share,
                    contentDescription = null,
                    tint = onSecondary,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            shareContent(
                                context,
                                title = "Share",
                                text = "Please check this Profile I found over banjaraworld"
                            )
                            onShareClick = !onShareClick
                        }
                        .border(
                            1.dp, Color.Gray, RoundedCornerShape(4.dp)
                        )
                        .padding(BwDimensions.PADDING_4))
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpgradeToPremium(modifier: Modifier = Modifier, text: String, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(true) }
    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.navigationBarsPadding(),
            containerColor = Color.White,
            onDismissRequest = {
                onDismiss.invoke()
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = BwDimensions.PADDING_12)
            ) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = "close",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable {
                            showBottomSheet = false

                        })
            }
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(BwDimensions.PADDING_8),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = R.drawable.sheet,
                    contentDescription = "profile",
                    modifier
                        .size(100.dp)
                        .background(
                            shape = CircleShape, color = Color.Unspecified
                        )
                        .border(1.dp, Color.Gray, CircleShape)
                        .padding(BwDimensions.PADDING_4),
                )
                Spacer(modifier = Modifier.padding(top = BwDimensions.SPACING_12))
                CommonText(
                    text = "Upgrade to Premium",
                    fontSize = BwDimensions.FONT_17,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.padding(top = BwDimensions.SPACING_30))

                Card(
                    modifier = Modifier.wrapContentWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(BwDimensions.ELEVATION_HEIGHT)
                ) {
                    Column(
                        modifier = Modifier.padding(BwDimensions.PADDING_12),
                        verticalArrangement = Arrangement.spacedBy(BwDimensions.PADDING_12)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "phone",
                                tint = darkGreen
                            )
                            Spacer(modifier = Modifier.width(BwDimensions.PADDING_8))
                            CommonText(
                                text = "+91 9876******",
                                fontSize = BwDimensions.FONT_14,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "phone",
                                tint = Color.Gray
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = darkGreen
                            )
                            Spacer(modifier = Modifier.width(BwDimensions.PADDING_8))
                            CommonText(
                                text = "*******@gmail.com",
                                fontSize = BwDimensions.FONT_14,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "phone",
                                tint = Color.Gray
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Lives at",
                                tint = darkGreen
                            )
                            Spacer(modifier = Modifier.width(BwDimensions.PADDING_8))
                            CommonText(
                                text = "****** Maharashtra ,India",
                                fontSize = BwDimensions.FONT_14,
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "phone",
                                tint = Color.Gray
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top = BwDimensions.SPACING_30))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatDiscountText(discount = "55%"), fontSize = BwDimensions.FONT_14
                    )
                    Spacer(modifier = Modifier.width(BwDimensions.PADDING_20))

                    CommonButton(text = "View Plans", onClick = { /*TODO*/ })

                }

            }

        }
    }
}


@Composable
fun UserPhotos(
    images: List<Int>, pagerState: PagerState, screenWidth: Dp
) {
    HorizontalPager(
        state = pagerState, pageSize = PageSize.Fill, modifier = Modifier.fillMaxWidth()
    ) { index ->
        UserMarriageCard(
            image = images[index], pagerState = pagerState, screenWidth = screenWidth
        )
    }
}

@Composable
fun AboutUser(modifier: Modifier = Modifier, userName: String, isProfile: Boolean) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = BwDimensions.ELEVATION_HEIGHT)
    ) {
        Box(modifier = modifier) {
            if (isProfile) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "edit",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(BwDimensions.PADDING_12)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BwDimensions.PADDING_12)
            ) {
                CommonText(
                    text = "About $userName",
                    fontSize = BwDimensions.FONT_17,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start,
                )

                Spacer(modifier = Modifier.height(BwDimensions.SPACING_8))
                Text(
                    text = "Hello ,here little bit about myself . I have completed my B.E/B.Tech now working in Swiggy india as Software engineer/Programmer currently living in Washim Maharashtra.Please get in touch in case you like my profile.",
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal,
                    fontSize = BwDimensions.FONT_14,
                    style = TextStyle.Default.copy(
                        lineBreak = LineBreak.Paragraph
                    ),
                    lineHeight = BwDimensions.FONT_23
                )
            }
        }

    }
}

@Composable
fun CareerAndEducation(modifier: Modifier = Modifier, isProfile: Boolean) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = BwDimensions.ELEVATION_HEIGHT)
    ) {
        Box(modifier = modifier) {
            if (isProfile) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "edit",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(BwDimensions.PADDING_12)
                )
            }
            Column(modifier = Modifier.padding(BwDimensions.PADDING_12)) {
                CommonText(
                    text = "Career $amp Education Details",
                    fontSize = BwDimensions.FONT_17,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(BwDimensions.SPACING_8))
                BasicDetail2(
                    icon = Icons.Default.Work,
                    tittle = "Profession",
                    value = "Software Engineer with Private Company",
                    backgroundColor = Pink80
                )
                BasicDetail2(
                    icon = Icons.Default.Business,
                    tittle = "Employer Name",
                    value = "**********",
                    backgroundColor = Pink80
                )
                BasicDetail2(
                    icon = Icons.Default.Work,
                    tittle = "Annual Income",
                    value = "Self :INR 20 Lakh to 22 Lakh ",
                    backgroundColor = Pink80
                )
                BasicDetail2(
                    icon = Icons.Default.School,
                    tittle = "Highest Qualification",
                    value = "Bachelor of Engineering",
                    backgroundColor = Pink80
                )
                BasicDetail2(
                    icon = Icons.Default.Architecture,
                    tittle = "Education Field",
                    value = "Engineering",
                    backgroundColor = Pink80
                )
                BasicDetail2(
                    icon = Icons.Default.Business,
                    tittle = "College Name",
                    value = "**********",
                    backgroundColor = Pink80
                )
            }

        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FamilyDetails(modifier: Modifier = Modifier, isProfile: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = BwDimensions.ELEVATION_HEIGHT)
    ) {
        Box(modifier = modifier) {
            if (isProfile) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "edit",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(BwDimensions.PADDING_12)
                )
            }
            Column(modifier = Modifier.padding(BwDimensions.PADDING_12)) {
                CommonText(
                    text = "Family Details",
                    fontSize = BwDimensions.FONT_17,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                FlowRow {
                    BasicDetailCard(text = "Moderate")
                    BasicDetailCard(text = "Nuclear")
                }
                Spacer(modifier = Modifier.padding(BwDimensions.PADDING_8))
                BasicDetail2(
                    icon = Icons.Default.People,
                    tittle = "Parents' Details",
                    value = "Father is Farmer \n,Mother is Homemaker",
                    backgroundColor = Purple40
                )
                BasicDetail2(
                    icon = Icons.Default.People,
                    tittle = "No. of Siblings",
                    value = "0 Brother \n,2 Sister",
                    backgroundColor = Purple40
                )
                BasicDetail2(
                    icon = Icons.Default.People,
                    tittle = "Siblings Marital Status",
                    value = "Both Married",
                    backgroundColor = Purple40
                )

                BasicDetail2(
                    icon = Icons.Default.People,
                    tittle = "Family Financial Status",
                    value = "Middle - Annual family income is 4-5 lakhs",
                    backgroundColor = Purple40
                )

            }

        }
    }

}

@Composable
fun ContactDetail(modifier: Modifier = Modifier, isProfile: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = BwDimensions.ELEVATION_HEIGHT)
    ) {
        Box(modifier = modifier) {
            if (isProfile) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "edit",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(BwDimensions.PADDING_12)
                )
            }
            Column(modifier = Modifier.padding(BwDimensions.PADDING_12)) {
                CommonText(
                    text = "Contact Details",
                    fontSize = BwDimensions.FONT_17,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )

                BasicDetail2(
                    icon = Icons.Default.Phone,
                    tittle = "Contact No.",
                    value = "+91 9876******",
                    backgroundColor = darkGreen
                )
                BasicDetail2(
                    icon = Icons.Default.Email,
                    tittle = "Email Id",
                    value = "*********@gmail.com",
                    backgroundColor = darkGreen
                )
                Spacer(modifier = Modifier.padding(BwDimensions.PADDING_8))
                HorizontalDivider()
                Spacer(modifier = Modifier.padding(BwDimensions.PADDING_8))
                CommonText(
                    text = "unlock Contact Details",
                    fontSize = BwDimensions.FONT_17,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                CommonButton(text = "Get Subscription", onClick = { /*TODO*/ })
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicDetail(modifier: Modifier = Modifier, isProfile: Boolean) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = BwDimensions.ELEVATION_HEIGHT)
    ) {
        Box(modifier = modifier) {
            if (isProfile) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "edit",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(BwDimensions.PADDING_12)
                )
            }
            Column(modifier = Modifier.padding(BwDimensions.PADDING_12)) {
                CommonText(
                    text = "Basic Details",
                    fontSize = BwDimensions.FONT_17,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                val BasicDetailList = listOf(
                    "create by myself", "26 years old", "Height 5'8"
                )
                FlowRow {
                    BasicDetailList.forEach {
                        BasicDetailCard(text = it)
                        Spacer(modifier = Modifier.padding(BwDimensions.PADDING_4))
                    }
                }
                BasicDetail2(
                    icon = Icons.Default.DateRange,
                    tittle = "Birth Date",
                    value = "10/09/1997",
                    backgroundColor = onPrimary
                )
                BasicDetail2(
                    icon = Icons.Default.Timer,
                    tittle = "Birth Time",
                    value = "05:10 Am",
                    backgroundColor = onPrimary
                )

                BasicDetail2(
                    icon = Icons.Default.People,
                    tittle = "Marital Status",
                    value = "Never Married",
                    backgroundColor = onPrimary
                )
                BasicDetail2(
                    icon = Icons.Default.LocationOn,
                    tittle = "Lives In",
                    value = "Lives in washim ,Maharashtra ,India",
                    backgroundColor = onPrimary
                )
                BasicDetail2(
                    icon = Icons.Default.TempleHindu,
                    tittle = "Mother Tongue",
                    value = "Marathi",
                    backgroundColor = onPrimary
                )
                BasicDetail2(
                    icon = Icons.Default.Cookie,
                    tittle = "Diet Preference",
                    value = "Vegetarian",
                    backgroundColor = onPrimary
                )
                BasicDetail2(
                    icon = Icons.Default.Star,
                    tittle = "Gotra",
                    value = "0 Brother \n,2 Sister",
                    backgroundColor = onPrimary
                )
                Spacer(modifier = Modifier.padding(BwDimensions.PADDING_8))
                HorizontalDivider()
                Spacer(modifier = Modifier.padding(BwDimensions.PADDING_8))
                CommonText(
                    text = "unlock Birth Date",
                    fontSize = BwDimensions.FONT_17,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                CommonButton(text = "Get Subscription", onClick = { /*TODO*/ })


            }
        }
    }
}

@Composable
fun BasicDetail2(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    tittle: String,
    value: String,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BwDimensions.PADDING_8)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = tittle,
            tint = Color.White,
            modifier = Modifier
                .background(color = backgroundColor, shape = CircleShape)
                .size(30.dp)
                .padding(BwDimensions.PADDING_4)
        )
        Column() {
            CommonText(
                text = tittle,
                fontSize = BwDimensions.FONT_11,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            CommonText(
                text = value,
                fontSize = BwDimensions.FONT_11,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Composable
fun BasicDetailCard(modifier: Modifier = Modifier, text: String) {
    Card(
        modifier
            .wrapContentSize()
            .padding(BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder(true),

        ) {
        CommonText(
            text = text,
            fontSize = BwDimensions.FONT_11,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(BwDimensions.PADDING_4)
        )
    }

}

@Composable
fun UserMarriageCard(image: Int, pagerState: PagerState, screenWidth: Dp) {
    val dividerThickness = 1.dp
    val dividerOffset by animateDpAsState(
        targetValue = when (pagerState.currentPage) {
            0 -> 0.dp // Start for the first page
            1 -> (screenWidth - dividerThickness) / 3 // Center for the second page
            2 -> (screenWidth - dividerThickness) / 2 // Center for the second page
            else -> screenWidth - dividerThickness / 3 // End for the third page
        }, label = "DividerOffset"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RectangleShape
    ) {
        Column {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            ShoppingDivider(dividerOffset, screenWidth / 3, dividerThickness)
        }
    }
}

@Stable
data class Hobbies(val imageVector: ImageVector, val hobbieName: String)