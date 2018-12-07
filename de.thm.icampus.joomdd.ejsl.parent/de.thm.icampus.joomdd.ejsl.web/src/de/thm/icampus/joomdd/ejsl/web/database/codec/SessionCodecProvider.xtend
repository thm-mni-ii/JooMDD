package de.thm.icampus.joomdd.ejsl.web.database.codec

import de.thm.icampus.joomdd.ejsl.web.database.document.Session
import org.bson.codecs.Codec
import org.bson.codecs.configuration.CodecProvider
import org.bson.codecs.configuration.CodecRegistry

/**
 * @author Wolf Rost
 */
class SessionCodecProvider implements CodecProvider {

    @SuppressWarnings("unchecked")
    override <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == Session) {
            return new SessionCodec(registry) as Codec<T>;
        }
        return null;
    }
}