import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.dependency.version)
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

