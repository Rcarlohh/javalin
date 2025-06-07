import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import io.javalin.http.staticfiles.Location
import io.javalin.vue.VueComponent

fun main() {
    val app = Javalin.create { config ->
        // Configuración de archivos estáticos
        config.staticFiles.apply {
            enableWebjars()
            add("/public", Location.CLASSPATH)
        }

        // Configuración de Vue - IMPORTANTE: debe estar en views/
        config.vue.apply {
            vueInstanceNameInJs = "app"
            rootDirectory("/vue", Location.CLASSPATH)

        }

        // Configuración de rutas usando Vue components
        config.router.apiBuilder {
            // Los nombres DEBEN coincidir con los archivos en vue/views/
            ApiBuilder.get("/", VueComponent("home-page"))
            ApiBuilder.get("/books", VueComponent("books-page"))
            ApiBuilder.get("/desserts", VueComponent("desserts-page"))
            ApiBuilder.get("/mouse", VueComponent("mouse-page"))
            ApiBuilder.get("/games", VueComponent("games-page"))
        }
    }.start(7000)
}