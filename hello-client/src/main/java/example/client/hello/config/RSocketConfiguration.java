package example.client.hello.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.security.rsocket.metadata.BasicAuthenticationEncoder;
import org.springframework.util.MimeTypeUtils;

@Configuration
public class RSocketConfiguration {

    @Value("${example.service.hello.hostname}")
    private String helloServiceHostname;

    @Value("${example.service.hello.port}")
    private int helloServicePort;

    @Bean("insecureRSocketRequester")
    public RSocketRequester insecureRSocketRequester() {
        return RSocketRequester.builder()
                .dataMimeType(MimeTypeUtils.TEXT_PLAIN)
                .connectTcp(helloServiceHostname, helloServicePort)
                .block();
    }

    @Bean(name = "secureRSocketRequester")
    public RSocketRequester helloServiceRequester() {
        return RSocketRequester.builder()
                .rsocketStrategies(builder -> {
                    builder.encoder(new BasicAuthenticationEncoder());
                })
                .dataMimeType(MimeTypeUtils.TEXT_PLAIN)
                .connectTcp(helloServiceHostname, helloServicePort)
                .block();
    }
}
