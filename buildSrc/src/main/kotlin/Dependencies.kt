object Versions {
    // Core
    const val android_core = "1.7.0"
    const val gradle_version = "7.0.4"
    const val lifecycle_ktx = "2.4.0"
    const val kotlin_version = "1.5.31"
    const val timber = "5.0.1"

    // Design
    const val material = "1.4.0"
    const val compose_ui_version = "1.1.0-rc01"
    const val compose_stable_version = "1.0.5"
    const val compose_activity = "1.4.0"

    // Room
    const val room = "2.3.0"

    // Datastore
    const val datastore = "1.0.0"

    // Accompanist
    const val accompanist_version = "0.22.0-rc"

    // Dagger Hilt
    const val hilt_version = "2.40.5"
    const val hilt_navigation_compose = "1.0.0-rc01"

    // Testing
    const val junit = "4.13.2"
    const val androidx_junit = "1.1.3"
    const val espresso_core = "3.4.0"
}

object Dependencies {

    //Core
    const val android_core = "androidx.core:core-ktx:${Versions.android_core}"
    const val android_lifecycle_ktx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_ktx}"
    const val lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle_ktx}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // Design
    const val appcompat = "androidx.appcompat:appcompat:${Versions.material}"
    const val material = "com.google.android.material:material:${Versions.material}"

    // Compose
    const val compose_ui = "androidx.compose.ui:ui:${Versions.compose_ui_version}"
    const val compose_material = "androidx.compose.material:material:${Versions.compose_ui_version}"
    const val compose_tooling_preview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose_stable_version}"
    const val compose_activity = "androidx.activity:activity-compose:${Versions.compose_activity}"

    // Room
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"

    // DataStore
    const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"

    // Dagger Hilt
    const val dagger_hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val hilt_navigation_compose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hilt_navigation_compose}"
    const val hilt_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"

    // Accompanist
    const val navigation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist_version}"
    const val system_ui_controller =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist_version}"
    const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist_version}"
    const val insets_ui =
        "com.google.accompanist:accompanist-insets-ui:${Versions.accompanist_version}"
    const val pager = "com.google.accompanist:accompanist-pager:${Versions.accompanist_version}"

    // Testing
    const val junit = "junit:junit:${Versions.junit}"
    const val junit_android = "junit:junit:${Versions.androidx_junit}"
    const val espresso = "junit:junit:${Versions.espresso_core}"
    const val compose_ui_test_junit =
        "androidx.compose.ui:ui-test-junit4:${Versions.compose_stable_version}"
    const val compose_ui_tooling =
        "androidx.compose.ui:ui-tooling:${Versions.compose_stable_version}"
}

object Plugins {
    const val build_gradle = "com.android.tools.build:gradle:${Versions.gradle_version}"
    const val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val hilt_gradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}"

    const val application = "com.android.application"
    const val android = "android"
    const val kapt = "kapt"
    const val hilt = "dagger.hilt.android.plugin"
}