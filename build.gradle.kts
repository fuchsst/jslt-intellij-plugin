import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.intellij.platform.gradle.tasks.PatchPluginXmlTask

plugins {
    id("org.jetbrains.intellij.platform") version "2.10.0"
    kotlin("jvm") version "2.2.0"
    id("org.jetbrains.grammarkit") version "2022.3.2"
}

group = "net.stefanfuchs.jslt.intellij.language"
version = "1.0.11"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

val includeInJar: Configuration by configurations.creating {
    isTransitive = false
}

dependencies {
    val jsltLibVersion = "0.1.14"
    // Removed explicit kotlin stdlib dependency to avoid version conflicts with the IDE platform
    implementation("com.schibsted.spt.data:jslt:$jsltLibVersion")
    includeInJar("com.schibsted.spt.data:jslt:$jsltLibVersion") // explicitly include this file in the build step

    intellijPlatform {
        create("IC", "2025.1.3")
        bundledPlugin("org.jetbrains.plugins.yaml")
    }
}

grammarKit {
    // Version of IntelliJ patched JFlex (see the link below), Default is 1.7.0-1
    jflexRelease.set("1.7.0-2")

    // Release version, tag, or short commit hash of Grammar-Kit to use (see link below). Default is 2021.1.2
    grammarKitRelease.set("2021.1.2")

    // Optionally provide an IntelliJ version to build the classpath for GenerateParser/GenerateLexer tasks
    intellijRelease.set("251.9207")
}


// Include the generated files in the source set
sourceSets {
    main {
        java {
            srcDirs("src/main/gen")
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    // Keep toolchain aligned with Java 21
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}


tasks {
    generateLexer {
        // source flex file
        sourceFile.set(File("src/main/grammar/jslt.flex"))

        // target directory for lexer
        targetOutputDir.set(File("src/main/gen/net/stefanfuchs/jslt/intellij/language/"))

        // if set, plugin will remove a lexer output file before generating new one. Default: false
        purgeOldFiles.set(true)
    }

    // IMPORTANT: there is an open issue (https://github.com/JetBrains/gradle-grammar-kit-plugin/issues/23)
    // which block the proper code generation using the Gradle Plugin
    // (the utility methods that can be referenced using the psiImplUtilClass bnf config are not found)
    // which means, that the parser generator must be run twice
    // one may use IntelliJ Plugin (https://plugins.jetbrains.com/plugin/6606-grammar-kit) to do that

    generateParser {
        // source bnf file
        sourceFile.set(File("src/main/grammar/jslt.bnf"))

        // optional, task-specific root for the generated files. Default: none
        targetRootOutputDir.set(File("src/main/gen"))

        // path to a parser file, relative to the targetRoot
        pathToParser.set("/net/stefanfuchs/jslt/intellij/language/parser/JsltParser.java")


        pathToPsiRoot.set("/net/stefanfuchs/jslt/intellij/language/psi") // path to a directory with generated psi files, relative to the targetRoot

        // if set, the plugin will remove a parser output file and psi output directory before generating new ones. Default: false
        purgeOldFiles.set(true)
    }

    buildSearchableOptions {
        enabled = false
    }

    jar {
        from(zipTree(includeInJar.singleFile))
    }

    patchPluginXml {
        sinceBuild.set("252")
        changeNotes.set(
            """
            Minimum IDE version raised to 2025.2; removed upper build bound to stay compatible with future IDE releases.
            """.trimIndent()
        )
    }

}
