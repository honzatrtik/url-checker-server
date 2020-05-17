package urlchecker.server

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import arrow.fx.reactor.*
import arrow.fx.reactor.extensions.monok.applicativeError.handleError
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.bodyToMono

@Component
class Handler(private val service: Service) {
    fun checkUrls(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono<CheckUrlsRequest>().k()
            .flatMap { req -> service.checkUrls(req).k() }
            .handleError { e -> CheckUrlsResponseFailure(e.message ?: "Error occurred") }
            .value()
            .flatMap { res ->
                when(res) {
                    is CheckUrlsResponseSuccess ->
                        ServerResponse.ok().contentType(APPLICATION_JSON).bodyValue(res)
                    is CheckUrlsResponseFailure ->
                        ServerResponse.badRequest().contentType(APPLICATION_JSON).bodyValue(res)
                }
            }


    }

}