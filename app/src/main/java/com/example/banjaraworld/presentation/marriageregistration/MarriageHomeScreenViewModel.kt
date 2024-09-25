import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MarriageHomeScreenViewModel @Inject constructor() : ViewModel() {

    // Use a nullable Int, with null as the default state
    private val _count = MutableStateFlow<Int?>(null)
    val count: StateFlow<Int?> = _count

    fun incrementCount() {
        if (_count.value == null) {
            _count.value = 1 // Initialize to 1 when first incremented from null
        } else {
            _count.value = _count.value?.plus(1)
        }
    }

    fun decrementCount() {
        if (_count.value == null || _count.value == 1) {
            _count.value = null // Set to null if it would go below 0
        } else {
            _count.value = _count.value?.minus(1)
        }
    }
}
