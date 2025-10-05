import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.vanniktech.maven.publish")
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

extra["signingInMemoryKey"] = System.getenv("GPG_SIGNING_KEY")
extra["signingInMemoryKeyId"] = System.getenv("GPG_SIGNING_KEY_ID")
extra["signingInMemoryKeyPassword"] = System.getenv("GPG_SIGNING_PASSWORD")

configure<MavenPublishBaseExtension> {
    signAllPublications()
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    coordinates(project.group.toString(), project.name, project.version.toString())
    configure(
        KotlinJvm(
            javadocJar = JavadocJar.Dokka("dokkaJavadoc"),
            sourcesJar = true,
        )
    )

    pom {
        name.set("OpenAI Java SDK (Fork by mrhegit)")
        description.set("Forked version of the OpenAI Java SDK with custom enhancements. Based on official openai-java v3.5.3. Please see https://platform.openai.com/docs/api-reference for API details.")
        url.set("https://github.com/mrhegit/openai-java")

        licenses {
            license {
                name.set("Apache-2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("mrhegit")
                name.set("mrhegit")
                email.set("drivemrhe@gmail.com")
                url.set("https://github.com/mrhegit")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/mrhegit/openai-java.git")
            developerConnection.set("scm:git:ssh://git@github.com/mrhegit/openai-java.git")
            url.set("https://github.com/mrhegit/openai-java")
        }
    }
}

tasks.withType<Zip>().configureEach {
    isZip64 = true
}
