import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.NumberPicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.banjaraworld.R

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NumberPickerWithSuffix(
    range: IntRange,
    selectedValue: Int,
    onValueChange: (Int) -> Unit,
) {

    AndroidView(modifier = Modifier.wrapContentSize(), factory = { context ->
        val view = LayoutInflater.from(context).inflate(R.layout.numberpicker, null)

        val numberPicker = view.findViewById<NumberPicker>(R.id.numberpicker)

        numberPicker.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        numberPicker.minValue = range.first
        numberPicker.maxValue = range.last
        numberPicker.value = selectedValue

        numberPicker.textSize = 80f

        numberPicker.setOnValueChangedListener { _, _, new ->
            onValueChange(new)
        }
        numberPicker.dividerPadding = 50
        numberPicker.selectionDividerHeight = 10
        numberPicker
    },
        update = { numberPicker ->
            numberPicker.minValue = range.first
            numberPicker.maxValue = range.last
            numberPicker.value = selectedValue
        })

}
