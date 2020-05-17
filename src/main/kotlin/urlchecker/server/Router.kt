package urlchecker.server

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class Router(private val handler: Handler) {
    @Bean
    fun urlCheckerRouter() = router {
        POST("/").and(contentType(APPLICATION_JSON))(handler::checkUrls)
    }
}