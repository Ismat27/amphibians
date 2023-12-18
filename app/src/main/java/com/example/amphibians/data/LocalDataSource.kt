package com.example.amphibians.data

import com.example.amphibians.network.Amphibian

object LocalDataSource {

    val amphibians = listOf<Amphibian>(
        Amphibian(
            name = "Great Basin Spadefoot",
            description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. " +
                    "Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. " +
                    "They are typically grey, green, or brown with dark spots.",
            type = "Toad",
            img_src = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
        )
    )
}