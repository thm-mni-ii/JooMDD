package de.thm.icampus.joomdd.ejsl.web.database.codec

import org.bson.codecs.configuration.CodecProvider
import org.bson.codecs.Codec
import org.bson.codecs.configuration.CodecRegistry
import de.thm.icampus.joomdd.ejsl.web.database.document.User
import de.thm.icampus.joomdd.ejsl.web.database.document.Session

class SessionCodecProvider implements CodecProvider {

    @SuppressWarnings("unchecked")
    override <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == Session) {
            return new SessionCodec(registry) as Codec<T>;
        }
        return null;
    }
}