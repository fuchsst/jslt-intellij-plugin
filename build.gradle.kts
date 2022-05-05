plugins {
    id("org.jetbrains.intellij") version "1.5.3"
    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.grammarkit") version "2021.2.2"
}

group = "net.stefanfuchs.jslt.intellij.language"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2021.3.3")
}

grammarKit {
    // Version of IntelliJ patched JFlex (see the link below), Default is 1.7.0-1
    jflexRelease.set("1.7.0-2")

    // Release version, tag, or short commit hash of Grammar-Kit to use (see link below). Default is 2021.1.2
    grammarKitRelease.set("2021.1.2")

    // Optionally provide an IntelliJ version to build the classpath for GenerateParser/GenerateLexer tasks
    intellijRelease.set("203.7717.81")


}


// Include the generated files in the source set
sourceSets {
    main {
        java {
            srcDirs("src/main/gen")
        }
    }
}


tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    generateLexer {
        // source flex file
        source.set("src/main/grammar/jslt.flex")

        // target directory for lexer
        targetDir.set("src/main/gen/net/stefanfuchs/jslt/intellij/language/")

        // target classname, target file will be targetDir/targetClass.java
        targetClass.set("JsltLexer")

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
        source.set("src/main/grammar/jslt.bnf")

        // optional, task-specific root for the generated files. Default: none
        targetRoot.set("src/main/gen")

        // path to a parser file, relative to the targetRoot
        pathToParser.set("/net/stefanfuchs/jslt/intellij/language/parser/JsltParser.java")


        pathToPsiRoot.set("/net/stefanfuchs/jslt/intellij/language/psi") // path to a directory with generated psi files, relative to the targetRoot

        // if set, the plugin will remove a parser output file and psi output directory before generating new ones. Default: false
        purgeOldFiles.set(true)
    }

//    patchPluginXml {
//        changeNotes.set("""
//            Add change notes here.<br>
//            <em>most HTML tags may be used</em>        """.trimIndent())
//    }
}