package urlchecker.server

import reactor.core.publisher.Mono

interface Service {
    fun checkUrls(request: CheckUrlsRequest): Mono<CheckUrlsResponse>
}
