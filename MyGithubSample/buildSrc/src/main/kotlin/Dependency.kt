import org.gradle.kotlin.dsl.DependencyHandlerScope

object Versions {
    // Android Version
    const val ANDROID = "7.4.0"
    // Kotlin Version
    const val KOTLIN = "1.7.21"

    // AndroidX
    const val APP_COMPAT = "1.6.1"
    const val MATERIAL = "1.8.0"
    const val CONSTRAINT_LAYOUT = "2.1.4"
    const val LIFECYCLE = "2.5.1"
    const val NAVIGATION = "2.5.3"

    // Coroutine
    const val COROUTINE = "1.6.4"

    // Hilt
    const val HILT = "2.44"

    // JavaX - Inject
    const val INJECT = "1"


    const val TIMBER = "5.0.1"

    // TEST
    const val JUNIT = "4.13.2"
    const val JUNIT_EXT = "1.1.5"
    const val TEST_RUNNER = "1.5.2"
    const val ESPRESSO_CORE = "3.5.1"
}

object Libraries{
    object AndroidX {
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
        const val NAVIGATION_UI= "androidx.navigation:navigation-ui:${Versions.NAVIGATION}"
        const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment:${Versions.NAVIGATION}"
        const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel:${Versions.LIFECYCLE}"
        const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime:${Versions.LIFECYCLE}"

        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val TEST_RUNNER = "androidx.test.ext:junit:${Versions.TEST_RUNNER}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    }

    object Coroutine {
        const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE}"
        const val COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE}"
    }

    object Hilt {
        const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
    }

    object JavaX {
        const val INJECT = "javax.inject:javax.inject:${Versions.INJECT}"
    }
}

fun DependencyHandlerScope.implementationAndroidX(){
    implementations(
        Libraries.AndroidX.APP_COMPAT,
        Libraries.AndroidX.MATERIAL,
        Libraries.AndroidX.CONSTRAINT_LAYOUT,
        Libraries.AndroidX.NAVIGATION_UI,
        Libraries.AndroidX.NAVIGATION_FRAGMENT,
        Libraries.AndroidX.LIFECYCLE_VIEWMODEL,
        Libraries.AndroidX.LIFECYCLE_RUNTIME
    )
}

fun DependencyHandlerScope.implementationCoroutine(){
    implementations(
        Libraries.Coroutine.COROUTINE_ANDROID,
        Libraries.Coroutine.COROUTINE_CORE
    )
}

fun DependencyHandlerScope.implementationHilt(){
    implementations(
        Libraries.Hilt.HILT
    )
    kapts(
        Libraries.Hilt.HILT_COMPILER
    )
}

fun DependencyHandlerScope.implementationTest() {
    add("testImplementation", "junit:junit:${Versions.JUNIT}")
    add("androidTestImplementation", "androidx.test.ext:junit-ktx:${Versions.JUNIT_EXT}")
    add("androidTestImplementation", "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}")
}


// Kotlin DSL Extensions
fun DependencyHandlerScope.classPaths(vararg classPath: Any) {
    classPath.forEach {
        add("classpath", it)
    }
}

fun DependencyHandlerScope.implementations(vararg notations: Any) {
    notations.forEach {
        add("implementation", it)
    }
}

fun DependencyHandlerScope.kapts(vararg notations: Any) {
    notations.forEach {
        add("kapt", it)
    }
}

fun DependencyHandlerScope.coreLibraryDesugaring(vararg notation: Any) {
    notation.forEach {
        add("coreLibraryDesugaring", it)
    }
}