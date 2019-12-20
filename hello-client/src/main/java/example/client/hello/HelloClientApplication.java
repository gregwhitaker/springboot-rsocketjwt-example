package example.client.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.security.rsocket.metadata.BearerTokenMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static picocli.CommandLine.Option;
import static picocli.CommandLine.Parameters;
import static picocli.CommandLine.populateCommand;

@SpringBootApplication
public class HelloClientApplication {
    private static final Logger LOG = LoggerFactory.getLogger(HelloClientApplication.class);

    public static void main(String... args) {
        SpringApplication.run(HelloClientApplication.class, args);
    }

    /**
     * Runs the application.
     */
    @Component
    public class Runner implements CommandLineRunner {

        @Autowired
        private RSocketRequester rSocketRequester;

        @Override
        public void run(String... args) throws Exception {
            ClientArguments params = populateCommand(new ClientArguments(), args);

            LOG.debug("token: {}", params.token);
            LOG.debug("method: {}", params.method);
            LOG.debug("name: {}", params.name);

            if (StringUtils.isEmpty(params.token)) {
                LOG.info("Sending message without Bearer Token...");

                String message = rSocketRequester.route(params.method)
                        .data(params.name)
                        .retrieveMono(String.class)
                        .doOnError(throwable -> {
                            LOG.error(throwable.getMessage(), throwable);
                        })
                        .block();

                LOG.info("Response: {}", message);
            } else {
                LOG.info("Sending message with Bearer Token...");

                String message = rSocketRequester.route(params.method)
                        .metadata(params.token, BearerTokenMetadata.BEARER_AUTHENTICATION_MIME_TYPE)
                        .data(params.name)
                        .retrieveMono(String.class)
                        .doOnError(throwable -> {
                            LOG.error(throwable.getMessage(), throwable);
                        })
                        .block();

                LOG.info("Response: {}", message);
            }
        }
    }

    /**
     * Hello client command line arguments.
     */
    public static class ClientArguments {

        /**
         * JWT token for authentication and authorization
         */
        @Option(names = "--token", description = "jwt token")
        public String token;

        /**
         * RSocket method name
         */
        @Parameters(index = "0", arity = "1", description = "the method to call")
        public String method;

        /**
         * "name" argument to send to the method
         */
        @Parameters(index = "1", arity = "1", defaultValue = "name argument for method")
        public String name;
    }
}
