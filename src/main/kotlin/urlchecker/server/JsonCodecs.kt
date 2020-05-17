package urlchecker.server

import arrow.core.Option
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.boot.jackson.JsonComponent

/*
    Provide sane serialization of Option datatype
 */
@JsonComponent
class OptionSerializer<T>: JsonSerializer<Option<T>>() {

    override fun serialize(value: Option<T>?, gen: JsonGenerator?, provider: SerializerProvider?) {
        requireNotNull(value).fold({ requireNotNull(gen).writeNull() }, { nestedValue -> requireNotNull(provider).defaultSerializeValue(nestedValue, gen) })
    }
}