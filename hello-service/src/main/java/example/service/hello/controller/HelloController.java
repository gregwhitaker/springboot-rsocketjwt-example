package example.service.hello.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/**
 * Controller that generates hello messages.
 */
@Controller
public class HelloController {

    /**
     * Return a hello message.
     *
     * @param name name to put in the hello message
     * @return hello message
     */
    @MessageMapping("hello")
    public Mono<String> hello(String name) {
        return Mono.just(String.format("Hello, %s! - from unsecured method", name));
    }

    /**
     * Return a hello message for any authenticated user.
     *
     * @param name name to put in the hello message
     * @return hello message
     */
    @MessageMapping("hello.secure")
    public Mono<String> helloSecure(String name) {
        return Mono.just(String.format("Hello, %s! - from secured method", name));
    }

    /**
     * Return a hello message only for authenticated admin users.
     *
     * @param name name to put in the hello message
     * @return hello message
     */
    @MessageMapping("hello.secure.adminonly")
    public Mono<String> helloSecureAdminOnly(String name) {
        return Mono.just(String.format("Hello, %s! - from secured method [admin only]", name));
    }
}
