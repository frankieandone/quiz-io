package one.and.frankie.quizio.model

import com.google.gson.annotations.SerializedName

data class QA(
    @SerializedName("question") val question: String?,
    @SerializedName("answer") val answer: String?
)