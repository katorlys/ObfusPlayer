import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.8.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0" // Documentation: https://github.com/Minecrell/plugin-yml
    id("com.github.johnrengelman.shadow") version "8.1.1" // Documentation: https://github.com/johnrengelman/shadow
}

val spigotVersion: String by project
val pluginGroup: String by project
val pluginVersion: String by project

group = project.property("pluginGroup") as String
version = project.property("pluginVersion") as String

repositories {
    maven("https://maven.aliyun.com/repository/public/") // Mirror: Comment this if you are not in China
    maven("https://repo.huaweicloud.com/repository/maven/") // Mirror: Comment this if you are not in China
    mavenLocal()
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("org.spigotmc:spigot-api:$spigotVersion")
}

val pluginApi: String by project
val pluginMain: String by project
val pluginName: String by project
val pluginAuthor: String by project
val pluginDescription: String by project

bukkit {
    name = pluginName
    version = pluginVersion
    apiVersion = pluginApi
    main = pluginMain
    author = pluginAuthor
    description = pluginDescription
    commands {
        register("hide")
    }
    permissions {
        register("obfusplayer.hide")
    }
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
    withType<ShadowJar> {
        archiveBaseName.set(pluginName)
        archiveClassifier.set("shadow")
    }
}
