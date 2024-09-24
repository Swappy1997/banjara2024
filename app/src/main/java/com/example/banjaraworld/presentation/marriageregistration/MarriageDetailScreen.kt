package com.example.banjaraworld.presentation.marriageregistration

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.ui.theme.onPrimary

@Composable
fun MarriageDetailScreen(modifier: Modifier = Modifier) {
    val images = listOf(
        R.drawable.sheet, R.drawable.sheet, R.drawable.sheet
    )
    val pagerState = rememberPagerState(pageCount = { images.size })
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(modifier = Modifier.systemBarsPadding()) {

        UserPhotos(images = images, pagerState = pagerState, screenWidth = screenWidth)
        Spacer(modifier = Modifier.height(BwDimensions.SPACING_12))
        AboutUser(userName = "Swapnil")
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
fun AboutUser(modifier: Modifier = Modifier, userName: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = BwDimensions.ELEVATION_HEIGHT)
    ) {
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicDetail(modifier: Modifier = Modifier) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BwDimensions.PADDING_4),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = BwDimensions.ELEVATION_HEIGHT)
    ) {
        Column {
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
                value = "10/09/1997"
            )
            BasicDetail2(
                icon = Icons.Default.People,
                tittle = "Marital Status",
                value = "Never Married"
            )
            BasicDetail2(
                icon = Icons.Default.LocationOn,
                tittle = "Lives In",
                value = "Lives in washim ,Maharashtra ,India"
            )
            BasicDetail2(
                icon = Icons.Default.TempleHindu,
                tittle = "Mother Tongue",
                value = "Marathi"
            )
            BasicDetail2(
                icon = Icons.Default.Cookie,
                tittle = "Diet Preference",
                value = "Vegetarian"
            )
            HorizontalDivider()
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

@Composable
fun BasicDetail2(modifier: Modifier = Modifier, icon: ImageVector, tittle: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = tittle,
            tint = Color.White,
            modifier = Modifier.background(color = onPrimary, shape = RectangleShape)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
                fontWeight = FontWeight.Medium
            )
        }
    }

}

@Composable
fun BasicDetailCard(modifier: Modifier = Modifier, text: String) {
    Card(
        modifier.wrapContentSize(),
        colors = CardDefaults.cardColors(contentColor = Color.White),
        border = CardDefaults.outlinedCardBorder(true),
        shape = RectangleShape
    ) {
        CommonText(
            text = text,
            fontSize = BwDimensions.FONT_11,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
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
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )

            ShoppingDivider(dividerOffset, screenWidth / 3, dividerThickness)
        }
    }
}
