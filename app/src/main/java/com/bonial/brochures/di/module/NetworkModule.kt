package com.bonial.brochures.di.module

import com.bonial.brochures.BuildConfig
import com.bonial.brochures.data.api.ShelfApi
import com.bonial.brochures.data.model.ShelfDto.EmbeddedDto.ContentDto
import com.bonial.brochures.data.model.ShelfDto.EmbeddedDto.ContentDto.BrochureContentDto.PremiumBrochureContentDto
import com.bonial.brochures.data.model.ShelfDto.EmbeddedDto.ContentDto.BrochureContentDto.UsualBrochureContentDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(
                PolymorphicJsonAdapterFactory.of(ContentDto::class.java, "contentType")
                    .withSubtype(UsualBrochureContentDto::class.java, "brochure")
                    .withSubtype(PremiumBrochureContentDto::class.java, "brochurePremium")
                    .withDefaultValue(ContentDto.OtherContentDto)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideShelfApi(retrofit: Retrofit): ShelfApi {
        return retrofit.create(ShelfApi::class.java)
    }
}