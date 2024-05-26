package com.yz3ro.flagquiz.data.model

data class CountryResponse(
    val name: Name,
    val region: String,
    val flags: Flags,
    val translations: Translations
)
data class Name(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeName>
)
data class NativeName(
    val official: String,
    val common: String
)
data class Flags(
    val png: String,
    val svg: String,
    val alt: String
)
data class Translations(
    val tur: Translation
)

data class Translation(
    val official: String,
    val common: String
)