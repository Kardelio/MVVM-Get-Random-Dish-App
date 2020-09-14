plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.3"

    defaultConfig {
        applicationId = "bk.personal.com.getadish"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "bk.personal.com.getadish.CustomTestRunner"
    }

    buildTypes {
        getByName("debug") {
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets.getByName("test") {
        java.srcDir("src/sharedTest/java")
    }
    sourceSets.getByName("androidTest") {
        java.srcDir("src/sharedTest/java")
    }
}

dependencies {
    val hilt_version = "2.28-alpha"
    val testVersion = "1.2.0"
    val fragmentVersion = "1.3.0-alpha06"

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra.get("kotlinVersion")}")
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
//    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")

    //Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.2")

    //material design stuff including bottom nav
    implementation("com.google.android.material:material:1.1.0")

    //Retrofit Deps
    implementation("com.squareup.retrofit2:retrofit:2.7.2")
    implementation("com.squareup.retrofit2:converter-gson:2.1.0")

    //Useful KTX extensions
    implementation("androidx.fragment:fragment-ktx:1.2.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.2.0")

    implementation("com.airbnb.android:lottie:3.4.2")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.11.0")

    // Room
    implementation("androidx.room:room-runtime:2.2.5")
    implementation("androidx.room:room-ktx:2.2.5")
    kapt("androidx.room:room-compiler:2.2.5")

    //Room inspect via url in debug mode
    debugImplementation("com.amitshekhar.android:debug-db:1.0.6")

    //Hilt Deps
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01")
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha01")

    //Robolectric
    testImplementation("androidx.test.ext:junit:1.1.1")
    testImplementation("org.robolectric:robolectric:4.3.1")

    //Hilt testing deps
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hilt_version")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:$hilt_version")
    debugImplementation("androidx.fragment:fragment-testing:$fragmentVersion")
    androidTestImplementation("androidx.test:core-ktx:$testVersion")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.2.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.2.1")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("junit:junit:4.13")
    testImplementation("org.mockito:mockito-core:3.0.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    androidTestImplementation("org.mockito:mockito-android:3.0.0")
    androidTestImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

}