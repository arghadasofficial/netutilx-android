package argha.netutils.models.auth


import com.google.gson.annotations.SerializedName

data class ApiKeyResponseCreate (
    @SerializedName("data")
    val `data`: ApiKeyResponseCreateData,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)

data class ApiKeyResponseCreateData(
    @SerializedName("key")
    val key: String
)

data class ApiKeyResponseGet(
    @SerializedName("data")
    val `data`: ApiKeyResponseGetData,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)

data class ApiKeyResponseGetData(
    @SerializedName("key")
    val key: String,
    @SerializedName("tokens")
    val tokens: String
)