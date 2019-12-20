package example.service.hello.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Decodes the JWT token sent in the RSocket metadata.
 */
@Component
public class JwtDecoder implements ReactiveJwtDecoder {
    private static final Logger LOG = LoggerFactory.getLogger(JwtDecoder.class);

    @Value("${example.service.hello.jwt.secret}")
    private String sharedSecret;

    @Override
    public Mono<Jwt> decode(String token) throws JwtException {
        return null;
    }
}
