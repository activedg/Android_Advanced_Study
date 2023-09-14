// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Version.Gradle apply false
    id("org.jetbrains.kotlin.android") version Version.Kotlin apply false
    id("com.android.library") version Version.Gradle apply false
    id("org.jetbrains.kotlin.jvm") version Version.Kotlin apply false
}


buildscript {
    dependencies {
        Project()
    }
    repositories {
        mavenCentral()
    }
}