package example.service.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableRSocketSecurity
public class RSocketSecurityConfiguration {

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new MapReactiveUserDetailsService(admin, user);
    }

    @Bean
    public PayloadSocketAcceptorInterceptor rsocketInterceptor(RSocketSecurity rsocket) {
        rsocket.authorizePayload(authorize ->
                authorize
                        .route("hello")
                            .permitAll()         // Permit access to the "hello" route by unauthenticated users
                        .route("hello.secure.adminonly")
                            .hasRole("ADMIN")
                        .anyRequest()
                            .authenticated()
                        .anyExchange()
                            .permitAll()
        )
                .jwt(Customizer.withDefaults());

        return rsocket.build();
    }

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() throws Exception {
        Mac mac = Mac.getInstance(MacAlgorithm.HS256.getName());
        SecretKeySpec secretKey = new SecretKeySpec("demosecret".getBytes(), mac.getAlgorithm());

        return NimbusReactiveJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }
}
