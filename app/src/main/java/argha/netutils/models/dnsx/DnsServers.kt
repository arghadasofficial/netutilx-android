package argha.netutils.models.dnsx


import com.google.gson.annotations.SerializedName

data class DnsServers(
    @SerializedName("data")
    val `data`: List<ServerData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)

data class ServerData(
    @SerializedName("id")
    val id: String,
    @SerializedName("ip")
    val ip: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("provider")
    val provider: String
)