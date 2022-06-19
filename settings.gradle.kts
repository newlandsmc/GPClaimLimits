rootProject.name = "GPClaimLimits"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { // Paper
            url = uri("https://papermc.io/repo/repository/maven-public/")
        }
        maven("https://jitpack.io") { // Vault
            content {
                includeGroup("com.github.TechFortress")
            }
        }
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}