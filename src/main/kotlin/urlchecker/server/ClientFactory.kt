package urlchecker.server

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import urlchecker.client.interop.Client

@Configuration
class ClientFactory {
    @Bean
    fun makeClient(): Client {
        return Client()
    }
}