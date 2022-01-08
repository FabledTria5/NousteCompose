plugins {
    id(Plugins.application)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testsRunner

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_stable_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Core
    implementation(dependencyNotation = Dependencies.android_core)
    implementation(dependencyNotation = Dependencies.android_lifecycle_ktx)
    implementation(dependencyNotation = Dependencies.lifecycle_viewmodel)
    implementation(dependencyNotation = Dependencies.timber)

    // Design
    implementation(dependencyNotation = Dependencies.appcompat)
    implementation(dependencyNotation = Dependencies.material)

    // Compose
    implementation(dependencyNotation = Dependencies.compose_ui)
    implementation(dependencyNotation = Dependencies.compose_material)
    implementation(dependencyNotation = Dependencies.compose_tooling_preview)
    implementation(dependencyNotation = Dependencies.compose_activity)

    // Room
    implementation(dependencyNotation = Dependencies.room_runtime)
    implementation(dependencyNotation = Dependencies.room_ktx)
    kapt(dependencyNotation = Dependencies.room_compiler)

    // DataStore
    implementation(dependencyNotation = Dependencies.datastore)

    // Dagger Hilt
    implementation(dependencyNotation = Dependencies.dagger_hilt)
    implementation(dependencyNotation = Dependencies.hilt_navigation_compose)
    kapt(dependencyNotation = Dependencies.hilt_compiler)

    // Accompanist
    implementation(dependencyNotation = Dependencies.navigation)
    implementation(dependencyNotation = Dependencies.insets)
    implementation(dependencyNotation = Dependencies.insets_ui)
    implementation(dependencyNotation = Dependencies.system_ui_controller)
    implementation(dependencyNotation = Dependencies.pager)

    // Testing
    implementation(dependencyNotation = Dependencies.junit)
    implementation(dependencyNotation = Dependencies.junit_android)
    implementation(dependencyNotation = Dependencies.espresso)
    implementation(dependencyNotation = Dependencies.compose_ui_test_junit)
    implementation(dependencyNotation = Dependencies.compose_ui_tooling)
}