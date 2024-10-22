package com.example.utils

import androidx.navigation.NavBackStackEntry
import kotlin.reflect.KClass

fun NavBackStackEntry?.routeClass(): KClass<*>? {
    return this?.destination?.route
        ?.split("/")
        ?.first()
        ?.let { java.lang.Class.forName(it) }
        ?.kotlin
}