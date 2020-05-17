package urlchecker.server

import arrow.core.Option


data class CheckUrlsRequest(val urls: List<String>) {
    companion object
}

data class UrlStatus(
    val url: String,
    val available: Boolean,
    val description: Option<String>
) {
    companion object
}

sealed class CheckUrlsResponse()

data class CheckUrlsResponseSuccess(val urlStatuses: List<UrlStatus>): CheckUrlsResponse() {
    companion object
}

data class CheckUrlsResponseFailure(val description: String): CheckUrlsResponse() {
    companion object
}
