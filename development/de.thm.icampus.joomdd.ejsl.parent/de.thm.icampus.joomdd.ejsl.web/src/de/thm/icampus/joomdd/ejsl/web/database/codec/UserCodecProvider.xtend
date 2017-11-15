package de.thm.icampus.joomdd.ejsl.web.database.codec

import de.thm.icampus.joomdd.ejsl.web.database.document.User
import org.bson.codecs.Codec
import org.bson.codecs.configuration.CodecProvider
import org.bson.codecs.configuration.CodecRegistry

class UserCodecProvider implements CodecProvider {

    @SuppressWarnings("unchecked")
    override <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == User) {
            return new UserCodec(registry) as Codec<T>;
        }
        return null;
    }
}