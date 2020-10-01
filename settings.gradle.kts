pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:4.2.0-alpha12")
            }
        }
    }
}
rootProject.name = "MarvelComicsViewer"


include(":androidApp")
include(":shared")

