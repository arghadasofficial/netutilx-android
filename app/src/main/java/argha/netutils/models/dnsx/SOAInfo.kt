package argha.netutils.models.dnsx


import com.google.gson.annotations.SerializedName

data class SOAInfo(
    @SerializedName("data")
    val `data`: List<SoaData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)

data class SoaData(
    @SerializedName("class")
    val classX: String,
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("name")
    val name: String,
    @SerializedName("ttl")
    val ttl: String,
    @SerializedName("type")
    val type: String
)

data class DataX(
    @SerializedName("expire")
    val expire: String,
    @SerializedName("min_ttl")
    val minTtl: String,
    @SerializedName("primary_nameserver")
    val primaryNameserver: String,
    @SerializedName("refresh")
    val refresh: String,
    @SerializedName("responsible_authority")
    val responsibleAuthority: String,
    @SerializedName("retry")
    val retry: String,
    @SerializedName("serial")
    val serial: String
)