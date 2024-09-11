package argha.netutils.models.dnsx


import com.google.gson.annotations.SerializedName

data class DnsInfo(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)

data class Data(
    @SerializedName("class")
    val classX: String,
    @SerializedName("data")
    val `data`: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("ttl")
    val ttl: String,
    @SerializedName("type")
    val type: String
)