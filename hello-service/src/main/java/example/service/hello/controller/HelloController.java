package example.service.hello.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class HelloController {

    @MessageMapping("hello")
    public Mono<String> hello(String name) {
        return Mono.just(String.format("Hello, %s! - from unsecured method", name));
    }

    @MessageMapping("hello.secure")
    public Mono<String> helloSecure(String name) {
        return Mono.just(String.format("Hello, %s! - from secured method", name));
    }
}
