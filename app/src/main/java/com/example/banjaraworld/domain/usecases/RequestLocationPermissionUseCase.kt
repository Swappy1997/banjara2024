import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class RequestLocationPermissionUseCase {
    fun isPermissionGranted(context: Context): Boolean {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}
