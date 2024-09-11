package argha.netutils.models.dnsx


import com.google.gson.annotations.SerializedName

data class DnsTypes(
    @SerializedName("data")
    val `data`: List<String>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)