package su.afk.cleancode.domain.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val firstName: String,
    val lastName: String,
    val email: String,
    val imageUrl: String
): Parcelable