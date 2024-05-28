plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "my.app.momschoice"
    compileSdk = 34

    defaultConfig {
        applicationId = "my.app.momschoice"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-analytics:21.6.2")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-inappmessaging-display:20.4.2")
    implementation("com.google.firebase:firebase-messaging:23.4.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation("com.hbb20:ccp:2.5.0")
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation ("com.google.android.material:material:1.12.0")

//    implementation("com.theartofdev.edmodo:android-image-cropper:2.8.0")
    implementation ("com.squareup.picasso:picasso:2.5.2")
    api("com.theartofdev.edmodo:android-image-cropper:2.8.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    //extra ones
    implementation ("com.squareup.retrofit2:retrofit:2.6.1")
    implementation ("androidx.vectordrawable:vectordrawable:1.2.0")
    implementation ("androidx.navigation:navigation-fragment:2.7.7")
    implementation ("androidx.navigation:navigation-ui:2.7.7")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("com.google.android.material:material:1.12.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation("com.firebaseui:firebase-ui-database:6.2.1")
//    implementation ("com.cepheuen.elegant-number-button:lib:1.0.2")









}