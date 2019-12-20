package example.service.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableRSocketSecurity
public class RSocketSecurityConfiguration {

    @Bean
    public PayloadSocketAcceptorInterceptor rsocketInterceptor(RSocketSecurity rsocket) {
        rsocket.authorizePayload(authorize ->
                authorize
                        .route("hello")
                            .permitAll()
                        .route("hello.secure.adminonly")
                            .hasRole("ADMIN")
                        .anyRequest()
                            .authenticated()
                        .anyExchange()
                            .permitAll()
        )
                .jwt(jwtSpec -> {
                    try {
                        jwtSpec.authenticationManager(jwtReactiveAuthenticationManager(reactiveJwtDecoder()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return rsocket.build();
    }

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec("JAC1O17W1F3QB9E8B4B1MT6QKYOQB36V".getBytes(), mac.getAlgorithm());

        return NimbusReactiveJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    @Bean
    public JwtReactiveAuthenticationManager jwtReactiveAuthenticationManager(ReactiveJwtDecoder reactiveJwtDecoder) {
        JwtReactiveAuthenticationManager jwtReactiveAuthenticationManager = new JwtReactiveAuthenticationManager(reactiveJwtDecoder);

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        authenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        jwtReactiveAuthenticationManager.setJwtAuthenticationConverter( new ReactiveJwtAuthenticationConverterAdapter(authenticationConverter));
        return jwtReactiveAuthenticationManager;
    }
}
