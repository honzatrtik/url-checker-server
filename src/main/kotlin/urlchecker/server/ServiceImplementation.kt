package urlchecker.server

import arrow.core.none
import arrow.core.some
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


@Component
class ServiceImplementation(val client: urlchecker.client.interop.Client): Service {
    override fun checkUrls(request: CheckUrlsRequest): Mono<CheckUrlsResponse> {
        return Mono.fromFuture(client.check(request.urls).toCompletableFuture())
            .map { list ->
                val urlStatuses = checkNotNull(list)
                    .map {
                        checkNotNull(it).let { clientUrlStatus ->
                            UrlStatus(
                                clientUrlStatus.url(),
                                clientUrlStatus.availability(),
                                // Wow, mapping scala Option to arrow Option is pain in the ass
                                if (clientUrlStatus.description().isDefined)
                                    clientUrlStatus.description().get().some()
                                else
                                    none()
                            )
                        }
                    }
                CheckUrlsResponseSuccess(urlStatuses)
            }
    }
}