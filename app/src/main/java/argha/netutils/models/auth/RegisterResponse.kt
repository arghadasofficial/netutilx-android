package argha.netutils.models.auth


import com.google.gson.annotations.SerializedName

data class RegisterResponse (
    @SerializedName("data")
    val `data`: RegisterData,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)

data class RegisterData(
    @SerializedName("user_id")
    val userId: Int
)