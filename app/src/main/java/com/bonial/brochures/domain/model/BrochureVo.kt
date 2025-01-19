package com.bonial.brochures.domain.model

data class BrochureVo(
    val id: Long,
    val type: TypeVo,
    val image: ImageVo,
    val publisherName: String
) {
    enum class TypeVo {
        USUAL, PREMIUM
    }

    sealed interface ImageVo {

        @JvmInline
        value class UrlVo(
            val value: String
        ) : ImageVo

        data object PlaceholderVo : ImageVo
    }
}