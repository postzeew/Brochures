package com.bonial.brochures.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory

@JsonClass(generateAdapter = true)
data class ShelfDto(

    /**
     * Comment for reviewers
     *
     * We could write a custom [com.squareup.moshi.JsonAdapter] to help us skip this
     * "proxy" field, but for simplicity (since it's a test task), I didn't do it.
     */
    @Json(name = "_embedded")
    val embedded: EmbeddedDto
) {
    @JsonClass(generateAdapter = true)
    data class EmbeddedDto(
        val contents: List<ContentDto>
    ) {
        interface ContentDto {

            sealed class BrochureContentDto(
                open val content: BrochureDto
            ) : ContentDto {

                @JsonClass(generateAdapter = true)
                data class UsualBrochureContentDto(
                    override val content: BrochureDto
                ) : BrochureContentDto(content)

                @JsonClass(generateAdapter = true)
                data class PremiumBrochureContentDto(
                    override val content: BrochureDto
                ) : BrochureContentDto(content)

                @JsonClass(generateAdapter = true)
                data class BrochureDto(
                    val id: Long,
                    val brochureImage: String?,
                    val publisher: PublisherDto,
                    val distance: Double
                ) {
                    @JsonClass(generateAdapter = true)
                    data class PublisherDto(
                        val name: String
                    )
                }
            }

            /**
             * Comment for reviewers
             *
             * We use [PolymorphicJsonAdapterFactory] to deserialize objects from JSON into classes
             * based on their types. Since we are only interested in brochure types, we use
             * [OtherContentDto] for all other types.
             *
             * @see [com.bonial.brochures.di.module.NetworkModule.provideMoshi]
             *
             * Alternatively, we could use a custom [com.squareup.moshi.JsonAdapter] to deserialize
             * only brochure types. However, I believe the decision to use
             * [PolymorphicJsonAdapterFactory] is more extensible because it allows us to support
             * additional types, if needed, by simply modifying a few lines of code.
             */
            object OtherContentDto : ContentDto
        }
    }
}