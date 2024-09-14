package argha.netutils.models.auth


import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("data")
    val `data`: LoginData,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)

data class LoginData(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("username")
    val username: String
)