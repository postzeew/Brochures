package com.bonial.brochures

import com.bonial.brochures.data.model.ShelfDto
import com.bonial.brochures.data.model.ShelfDto.EmbeddedDto.ContentDto.BrochureContentDto.BrochureDto
import com.bonial.brochures.data.model.ShelfDto.EmbeddedDto.ContentDto.BrochureContentDto.PremiumBrochureContentDto
import com.bonial.brochures.data.model.ShelfDto.EmbeddedDto.ContentDto.BrochureContentDto.UsualBrochureContentDto
import com.bonial.brochures.data.model.ShelfDto.EmbeddedDto.ContentDto.OtherContentDto
import com.bonial.brochures.domain.mapper.ShelfMapper
import com.bonial.brochures.domain.mapper.ShelfMapperImpl
import com.bonial.brochures.domain.model.BrochureVo
import org.junit.Assert.assertEquals
import org.junit.Test

class ShelfMapperTest {
    private val mapper: ShelfMapper = ShelfMapperImpl()

    @Test
    fun `mapping is correct for different source list types`() {
        // arrange
        val shelf = createShelfDto()

        // act
        val brochures = mapper.toBrochures(shelf)

        // assert
        assertEquals(createExpectedBrochures(), brochures)
    }

    private fun createShelfDto(): ShelfDto {
        return ShelfDto(
            embedded = ShelfDto.EmbeddedDto(
                contents = listOf(
                    UsualBrochureContentDto(
                        content = BrochureDto(
                            id = 1L,
                            brochureImage = "image_1",
                            publisher = BrochureDto.PublisherDto("Publisher 1"),
                            distance = 1.5
                        )
                    ),
                    OtherContentDto,
                    PremiumBrochureContentDto(
                        content = BrochureDto(
                            id = 2L,
                            brochureImage = null,
                            publisher = BrochureDto.PublisherDto("Publisher 2"),
                            distance = 2.4
                        )
                    ),
                    OtherContentDto
                )
            )
        )
    }

    private fun createExpectedBrochures(): List<BrochureVo> {
        return listOf(
            BrochureVo(
                id = 1L,
                type = BrochureVo.TypeVo.USUAL,
                image = BrochureVo.ImageVo.UrlVo("image_1"),
                publisherName = "Publisher 1",
                distance = 1.5
            ),
            BrochureVo(
                id = 2L,
                type = BrochureVo.TypeVo.PREMIUM,
                image = BrochureVo.ImageVo.PlaceholderVo,
                publisherName = "Publisher 2",
                distance = 2.4
            )
        )
    }
}