package com.example.composetest.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class FavoriteCardElement(
    @DrawableRes val drawable : Int,
    @StringRes val text: Int,
)
