import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banjaraworld.R
import com.example.banjaraworld.common.utils.BwDimensions
import com.example.banjaraworld.common.utils.Utils.formatText
import com.example.banjaraworld.presentation.ErrorText
import com.example.banjaraworld.presentation.commonwidgets.CommonButton
import com.example.banjaraworld.presentation.commonwidgets.CommonOutlineTextField
import com.example.banjaraworld.presentation.commonwidgets.LinearDeterminateIndicator
import com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage.MarriageSecondScreenEvent
import com.example.banjaraworld.presentation.marriageregistration.secondscreenmarriage.MarriageSecondScreenViewModel
import com.example.banjaraworld.ui.theme.PoppinsFont
import com.example.banjaraworld.ui.theme.onPrimary


// Define a data class to hold brother details as well, similar to SisterDetails
data class BrotherDetails(
    val name: String,
    val isMarried: Boolean
)

// Define a data class to hold sister details
data class SisterDetails(
    val name: String,
    val brotherInLawName: String,
    val isMarried: Boolean
)

