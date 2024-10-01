import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ChipDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.common.utils.Utils.formatText
import com.example.banjaraworld.presentation.ErrorText
import com.example.banjaraworld.presentation.commonwidgets.ChipGroup
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.presentation.commonwidgets.CommonText
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage.MarriageSecondScreenEvent
import com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage.MarriageSecondScreenViewModel
import com.example.banjaraworld.ui.theme.PoppinsFont
import com.example.banjaraworld.ui.theme.onPrimary


// Define a data class to hold brother details as well, similar to SisterDetails
data class BrotherDetails(
    val name: String, val isMarried: Boolean
)

// Define a data class to hold sister details
data class SisterDetails(
    val name: String, val brotherInLawName: String, val isMarried: Boolean
)

val motherOccupationList = listOf(
    "House wife", "Business", "Retired", "Gov employee", "working in private sector"
)

val fatherOccupationList = listOf(
    "Business", "not working", "Retired", "Gov employee", "working in private sector"
)
val noOfSiblings = listOf("0", "1", "2", "3", "3+")


@Composable
fun MarriageRegistrationFamilyDetail(
    onContinueClick: () -> Unit,
    marriageSecondScreenViewModel: MarriageSecondScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state = marriageSecondScreenViewModel.state
    val context = LocalContext.current

    // Lists to hold sister and brother details dynamically
    val sistersList = remember { mutableStateListOf<SisterDetails>() }
    val brothersList = remember { mutableStateListOf<BrotherDetails>() }

    // Track the number of siblings selected
    var selectedSistersCount = remember { 0 }
    var selectedBrothersCount = remember { 0 }

    // Listen for validation events to trigger navigation
    LaunchedEffect(key1 = context) {
        marriageSecondScreenViewModel.validationEvents.collect { event ->
            when (event) {
                is MarriageSecondScreenViewModel.ValidationEvent.Success -> {
                    onContinueClick()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .systemBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Progress Indicator
            item {
                LinearDeterminateIndicator(progressValue = 0.4f)
            }

            // Family Details Heading
            item {
                Text(
                    text = formatText(
                        prefix = "Family\n",
                        discountColor = onPrimary,
                        formattedText = "Details?",
                    ), fontSize = 23.sp, fontFamily = PoppinsFont
                )
            }

            // Sisters Section

            item {
                CommonText(
                    text = "what is your father occupation?",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                ChipGroup(
                    options = fatherOccupationList,
                    selectedOption = fatherOccupationList.toString()
                ) {

                }
            }
            item {
                CommonText(
                    text = "what is your mother Occupation ?",
                    fontSize = BwDimensions.TITTLE_FONT_SIZE,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                ChipGroup(
                    options = motherOccupationList,
                    selectedOption = fatherOccupationList.toString()
                ) {

                }
            }
            item {
                CommonText(
                    text = "How many sisters do you have?",
                    fontSize = BwDimensions.FONT_14,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                ChipGroup(options = noOfSiblings,
                    selectedOption = selectedSistersCount.toString(),
                    onOptionSelected = { selectedOption ->
                        selectedSistersCount = selectedOption.toInt()
                        sistersList.clear()
                        repeat(selectedSistersCount) {
                            sistersList.add(SisterDetails("", "", false))
                        }
                    })
            }

            // Display Sister Fields Dynamically
            items(sistersList.size) { index ->
                val sister = sistersList[index]
                CommonOutlineTextField(
                    dummyText = "Sister's name",
                    keyboardType = KeyboardType.Text,
                    value = sister.name,
                    onValueChange = { newName ->
                        sistersList[index] = sister.copy(name = newName)
                    },
                    imeAction = ImeAction.Done,
                    modifier = Modifier
                )
                SuggestionChip(onClick = {
                    sistersList[index] = sister.copy(isMarried = !sister.isMarried)
                }, label = { Text(if (sister.isMarried) "Married" else "Unmarried") },
                    border = null,
                    elevation = SuggestionChipDefaults.suggestionChipElevation(1.dp),
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = Color.White,
                    )
                )

                if (sister.isMarried) {
                    CommonOutlineTextField(
                        dummyText = "Brother-in-law's name",
                        keyboardType = KeyboardType.Text,
                        value = sister.brotherInLawName,
                        onValueChange = { newBILName ->
                            sistersList[index] = sister.copy(brotherInLawName = newBILName)
                        },
                        imeAction = ImeAction.Done,
                        modifier = Modifier
                    )
                }
            }

            // Brothers Section
            item {
                CommonText(
                    text = "How many brothers do you have?",
                    fontSize = BwDimensions.FONT_14,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )

                ChipGroup(options = noOfSiblings,
                    selectedOption = selectedBrothersCount.toString(),
                    onOptionSelected = { selectedOption ->
                        selectedBrothersCount = selectedOption.toInt()
                        brothersList.clear()
                        repeat(selectedBrothersCount) {
                            brothersList.add(BrotherDetails("", false))
                        }
                    })
            }

            // Display Brother Fields Dynamically
            items(brothersList.size) { index ->
                val brother = brothersList[index]
                CommonOutlineTextField(
                    dummyText = "Brother's name",
                    keyboardType = KeyboardType.Text,
                    value = brother.name,
                    onValueChange = { newName ->
                        brothersList[index] = brother.copy(name = newName)
                    },
                    imeAction = ImeAction.Done,
                    modifier = Modifier
                )
                SuggestionChip(
                    onClick = {
                        brothersList[index] = brother.copy(isMarried = !brother.isMarried)
                    },
                    label = { Text(if (brother.isMarried) "Married" else "Unmarried") },
                    border = null,
                    elevation = SuggestionChipDefaults.suggestionChipElevation(1.dp),
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = Color.White,
                    )
                )
            }
        }
        CommonButton(
            stringResource(R.string.continues),
            onClick = { onContinueClick() },
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

