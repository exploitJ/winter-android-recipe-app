import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.github.ben-manes.versions") version "0.51.0"
}

allprojects {
    tasks.withType<DependencyUpdatesTask> {
        gradleReleaseChannel = "current"
        rejectVersionIf {
            isNonStable(candidate.version)
        }
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword =
        listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-rc\\d*)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

