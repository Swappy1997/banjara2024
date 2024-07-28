package com.example.banjaraworld.presentation.commonwidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.navigation.Screen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarriageSheet(onClick: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(true) }
    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.navigationBarsPadding(),
            containerColor = Color.White,
            onDismissRequest = {
                showBottomSheet = false
            },

            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(15.dp)
                        .border(1.dp, Color.Gray, shape = CircleShape)
                        .align(Alignment.End)
                        .clickable {
                            scope
                                .launch { sheetState.hide() }
                                .invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }
                        }
                )
                Text(
                    text = stringResource(R.string.register_to_explore),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 10.sp
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))
                Image(
                    painter = painterResource(id = R.drawable.sheet),
                    contentDescription = null,
                    modifier = Modifier.size(194.dp)
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))

                Text(
                    text = stringResource(R.string.sheettittle),
                    fontSize = BwDimensions.FONT_16,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))

                Text(
                    text = stringResource(id = R.string.sheetdes),
                    fontSize = BwDimensions.FONT_10,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center, lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))

                CommonButton(text = stringResource(R.string.register_now), onClick = {
                    scope.launch {
                        onClick.invoke()
                        showBottomSheet = false

                    }
                })
                Spacer(modifier = Modifier.padding(top = 8.dp))
            }
        }
    }

}