pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "market-app"
include(":app")
include(":data")
include(":core:common")
include(":core:resources")
include(":core:utils")
include(":features:cart")
include(":features:home")
include(":features:profile")
include(":features:details")
include(":navigation")
