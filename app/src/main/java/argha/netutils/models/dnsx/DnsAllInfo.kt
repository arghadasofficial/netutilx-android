package argha.netutils.models.dnsx

import com.google.gson.annotations.SerializedName

data class DnsAllInfo (
    @SerializedName("data")
    val `data`: DnsData,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)

data class DnsData(
    @SerializedName("A")
    val a: List<Data>,
    @SerializedName("NS")
    val ns: List<Data>,
    @SerializedName("MX")
    val mx: List<Data>,
    @SerializedName("SOA")
    val soa: List<SoaData>,
    @SerializedName("TXT")
    val txt: List<Data>
)
