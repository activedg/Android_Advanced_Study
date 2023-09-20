import org.gradle.kotlin.dsl.DependencyHandlerScope

object Version{
    const val Gradle = "8.1.0"
    const val AppCompat = "1.6.1"
    const val Core = "1.10.1"
    const val Material = "1.9.0"
    const val Navigation = "2.6.0"
    const val Timber = "5.0.1"
    const val Secret = "2.0.1"
    const val DataStore = "1.0.0"
    const val SplashScreen = "1.0.0"

    // Kotlin, Coroutine
    const val Kotlin = "1.9.0"
    const val Coroutine = "1.7.2"

    // JavaX
    const val JavaX = "1"

    // Hilt
    const val Hilt = "2.47"
    const val AndroidHilt = "1.0.0"

    // Retrofit
    const val Retrofit = "2.9.0"
    const val OkHttp = "4.9.1"

    // Compose
    const val Compose = "2023.06.01"
    const val ComposeHilt = "1.0.0"
    const val ComposeLifecycle = "2.6.1"
    const val ComposeConstraint = "1.0.1"

    // Room
    const val Room = "2.6.0-beta01"

    // Test
    const val JUnit = "4.13.2"
    const val Ext = "1.1.5"
    const val Espresso = "3.5.1"
    const val Mockk ="1.13.5"
    const val TestCore = "1.5.0"

    const val Orbit = "6.0.0"

    const val Coil = "2.4.0"
    const val Kakao = "2.15.0"
}

object Utils {
    const val Timber = "com.jakewharton.timber:timber:${Version.Timber}"
    const val Coil = "io.coil-kt:coil-compose:${Version.Coil}"
    const val Kakao = "com.kakao.sdk:v2-user:${Version.Kakao}"
}

fun DependencyHandlerScope.Project() {
    classPaths(
        "com.android.tools.build:gradle:${Version.Gradle}",
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.Kotlin}",
        "com.google.dagger:hilt-android-gradle-plugin:${Version.Hilt}",
    )
}

fun DependencyHandlerScope.AndroidX(){
    implementations(
        "androidx.core:core-ktx:${Version.Core}",
        "androidx.appcompat:appcompat:${Version.AppCompat}",
        "com.google.android.material:material:${Version.Material}",
        "androidx.navigation:navigation-compose:${Version.Navigation}",
        "androidx.navigation:navigation-ui-ktx:${Version.Navigation}"
    )
}


fun DependencyHandlerScope.Compose(){
    implementations(
        platform("androidx.compose:compose-bom:${Version.Compose}"),
        "androidx.compose.foundation:foundation",
        "androidx.compose.ui:ui",
        "androidx.compose.ui:ui-tooling",
        "androidx.compose.material:material",
        "androidx.hilt:hilt-navigation-compose:${Version.ComposeHilt}",
        "androidx.lifecycle:lifecycle-runtime-compose:${Version.ComposeLifecycle}",
        "androidx.constraintlayout:constraintlayout-compose:${Version.ComposeConstraint}"
    )
}

fun DependencyHandlerScope.Coroutine(){
    implementations(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Coroutine}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Coroutine}"
    )
}

fun DependencyHandlerScope.Desugaring() {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
}

fun DependencyHandlerScope.Domain(){
    implementations(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Coroutine}",
        "javax.inject:javax.inject:${Version.JavaX}",
    )
}

fun DependencyHandlerScope.Hilt() {
    implementations(
        "com.google.dagger:hilt-android:${Version.Hilt}"
    )
    kapts(
        "com.google.dagger:hilt-android-compiler:${Version.Hilt}"
    )
}

fun DependencyHandlerScope.Orbit(){
    implementations(
        "org.orbit-mvi:orbit-core:${Version.Orbit}",
        "org.orbit-mvi:orbit-viewmodel:${Version.Orbit}",
        "org.orbit-mvi:orbit-compose:${Version.Orbit}"
    )
    testImplementations(
        "org.orbit-mvi:orbit-test:${Version.Orbit}"
    )
}

fun DependencyHandlerScope.Test() {
    testImplementations(
        "junit:junit:${Version.JUnit}"
    )
    androidTestImplementations(
        "androidx.test.ext:junit:${Version.Ext}",
        "androidx.test.espresso:espresso-core:${Version.Espresso}"
    )
}

fun DependencyHandlerScope.Local() {
    implementations(
        "androidx.datastore:datastore-preferences:${Version.DataStore}",
        "androidx.room:room-runtime:${Version.Room}",
        "androidx.room:room-ktx:${Version.Room}",
    )
    kapts(
        "androidx.room:room-compiler:${Version.Room}"
    )
}

fun DependencyHandlerScope.Retrofit() {
    implementations(
        "com.squareup.retrofit2:retrofit:${Version.Retrofit}",
        "com.squareup.retrofit2:converter-gson:${Version.Retrofit}",
        "com.squareup.okhttp3:logging-interceptor:${Version.OkHttp}"
    )
}


fun DependencyHandlerScope.coreLibraryDesugaring(vararg notations: Any) {
    notations.forEach { notation ->
        add("coreLibraryDesugaring", notation)
    }
}

fun DependencyHandlerScope.kapts(vararg notations: Any) {
    notations.forEach { notation ->
        add("kapt", notation)
    }
}

fun DependencyHandlerScope.classPaths(vararg notations: Any) {
    notations.forEach { notation ->
        add("classpath", notation)
    }
}

fun DependencyHandlerScope.implementations(vararg notations: Any) {
    notations.forEach { notation ->
        add("implementation", notation)
    }
}

fun DependencyHandlerScope.testImplementations(vararg notations: Any) {
    notations.forEach { notation ->
        add("testImplementation", notation)
    }
}

fun DependencyHandlerScope.runtimeImplementations(vararg notations: Any) {
    notations.forEach { notation ->
        add("runtimeOnly", notation)
    }
}

fun DependencyHandlerScope.debugImplementations(vararg notations: Any) {
    notations.forEach { notation ->
        add("debugImplementation", notation)
    }
}

fun DependencyHandlerScope.androidTestImplementations(vararg notations: Any) {
    notations.forEach { notation ->
        add("androidTestImplementation", notation)
    }
}