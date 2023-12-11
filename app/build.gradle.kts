plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.sergio.rodriguez.twitterchallengeresolution"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sergio.rodriguez.twitterchallengeresolution"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //EXTENSIONES DE KOTLIN PARA LIBRERÍAS DE ANDROID
    implementation("androidx.core:core-ktx:1.12.0")

    //CLASE VIEWMODEL - LIVEDATA - LIFECYCLE
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //ACTIVIDAD DE COMPOSE
    implementation("androidx.activity:activity-compose:1.8.1")

    //CONSTRAINT LAYOUT COMPOSE
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //COMPOSE
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    //MATERIAL ICONS
    implementation("androidx.compose.material:material-icons-extended")

    //JUNIT
    testImplementation("junit:junit:4.13.2")

    //TEST DE INTERGRACIÓN Y UI
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    //CONFIGURACIONES DE BUILD TYPE DEBUG
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}