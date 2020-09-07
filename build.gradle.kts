// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.set("kotlinVersion", "1.3.72")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra.get("kotlinVersion")}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.28-alpha")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}