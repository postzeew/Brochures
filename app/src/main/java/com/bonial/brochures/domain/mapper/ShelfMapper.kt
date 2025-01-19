package com.bonial.brochures.domain.mapper

import com.bonial.brochures.data.model.ShelfDto
import com.bonial.brochures.data.model.ShelfDto.EmbeddedDto.ContentDto.BrochureContentDto
import com.bonial.brochures.data.model.ShelfDto.EmbeddedDto.ContentDto.BrochureContentDto.PremiumBrochureContentDto
import com.bonial.brochures.data.model.ShelfDto.EmbeddedDto.ContentDto.BrochureContentDto.UsualBrochureContentDto
import com.bonial.brochures.domain.model.BrochureVo
import com.bonial.brochures.domain.model.BrochureVo.ImageVo
import com.bonial.brochures.domain.model.BrochureVo.TypeVo
import javax.inject.Inject

interface ShelfMapper {
    fun toBrochures(dto: ShelfDto): List<BrochureVo>
}

class ShelfMapperImpl @Inject constructor() : ShelfMapper {

    override fun toBrochures(dto: ShelfDto): List<BrochureVo> {
        return dto.embedded.contents
            .filterIsInstance<BrochureContentDto>()
            .map { content ->
                with(content.content) {
                    BrochureVo(
                        id = id,
                        type = toBrochureType(content),
                        image = toImage(brochureImage),
                        publisherName = publisher.name,
                        distance = distance
                    )
                }
            }
    }

    private fun toBrochureType(content: BrochureContentDto): TypeVo {
        return when (content) {
            is UsualBrochureContentDto -> TypeVo.USUAL
            is PremiumBrochureContentDto -> TypeVo.PREMIUM
        }
    }

    private fun toImage(imageUrl: String?): ImageVo {
        return imageUrl
            ?.let(ImageVo::UrlVo)
            ?: ImageVo.PlaceholderVo
    }
}