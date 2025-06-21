
import desserts.DessertController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.staticfiles.Location
import io.javalin.vue.VueComponent

fun main() {

    val app = Javalin.create { config ->
        config.staticFiles.apply {
            enableWebjars()
            add("/public", Location.CLASSPATH)
        }

        config.vue.apply {
            vueInstanceNameInJs = "app"
            rootDirectory("/vue", Location.CLASSPATH)
        }

        config.router.apiBuilder {
            get("/", VueComponent("home-page"))
            get("/books", VueComponent("books-page"))
            get("/desserts", VueComponent("desserts-page"))
            get("/mouse", VueComponent("mouse-page"))
            get("/games", VueComponent("games-page"))
            path("api") {
                crud("desserts/{id}", DessertController)
            }
        }
    }.start(7000)
}
