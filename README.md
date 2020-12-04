# springboot-rsocketjwt-example
![Build](https://github.com/gregwhitaker/springboot-rsocketjwt-example/workflows/Build/badge.svg)

An example of using [JWT](https://jwt.io/), for authentication and authorization, with [RSocket](http://rsocket.io) and Spring Boot.

This example consists of an RSocket service, `hello-service`, that returns hello messages based upon the method called and the supplied JWT token from the `hello-client` application.

The example assumes that you have already retrieved valid JWT tokens from your choice of Authorization Server. To mimic this, a `token-generator`
project has been included to get valid tokens for use with this demo.

## Building the Example
Run the following command to build the example:

    ./gradlew clean build
    
## Running the Example
Follow the steps below to run the example:

1. Run the following command to generate the admin and user JWT tokens to use for authenticating with the `hello-service`:

        ./gradlew :token-generator:run
        
    If successful, you will see the tokens displayed in the console:

        > Task :token-generator:run
        
        Generated Tokens
        ================
        Admin:
        eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6ImhlbGxvLXNlcnZpY2UiLCJzY29wZSI6IkFETUlOIiwiaXNzIjoiaGVsbG8tc2VydmljZS1kZW1vIiwiZXhwIjoxNTc2ODY4MjE0LCJqdGkiOiIyYjgwOTUwMC0wZWJlLTQ4MDEtOTYwZS1mZjc2MGQ3MjE0ZGUifQ.fzWzcvelcaXooMa5C3w7BI4lJxcruZiA7TwFyPQuH1k
        
        User:
        eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXVkIjoiaGVsbG8tc2VydmljZSIsInNjb3BlIjoiVVNFUiIsImlzcyI6ImhlbGxvLXNlcnZpY2UtZGVtbyIsImV4cCI6MTU3Njg2ODIxNCwianRpIjoiOGQzZDE2YWUtZTg5MS00Nzc4LWFjNWEtN2NhY2ExOGEwMTYwIn0.Tlg1WxTcrMliLOBmBRSPR33C3xfbc6KUEkEZit928tE
        
2. In a new terminal, run the following command to start the `hello-service`:

        ./gradlew :hello-service:bootRun
        
    If successful, you will see a message stating the service has been started in the console:
    
        2019-12-20 10:33:59.223  INFO 1889 --- [           main] e.service.hello.HelloServiceApplication  : Started HelloServiceApplication in 1.185 seconds (JVM running for 1.546)
        
    Now you are ready to start calling the `hello-service`.
    
3. In a new terminal, run the following command to call the unsecured `hello` endpoint:

        ./gradlew :hello-client:bootRun --args="hello Bob"
        
   Notice that the request was successful and you received a hello response:
   
        2019-12-20 10:37:24.282  INFO 1919 --- [           main] e.client.hello.HelloClientApplication    : Response: Hello, Bob! - from unsecured method 
        
4. Next, run the following command to call the `hello.secure` method which requires that the user is authenticated:

        ./gradlew :hello-client:bootRun --args="hello.secure Bob"
        
    You will receive an `io.rsocket.exceptions.ApplicationErrorException: Access Denied` exception because you have not supplied a valid JWT token.
 
5. Now, run the same command again, but this time supply the `User` JWT token you generated earlier:

        ./gradlew :hello-client:bootRun --args="--token {User Token Here} hello.secure Bob"

    You will now receive a successful hello message because you have authenticated with a valid JWT token:
    
        2019-12-20 10:42:14.371  INFO 1979 --- [           main] e.client.hello.HelloClientApplication    : Response: Hello, Bob! - from secured method
        
6. Next, let's test authorization by calling the `hello.secure.adminonly` endpoint with the `User` token by running the following command:

        ./gradlew :hello-client:bootRun --args="--token {User Token Here} hello.secure.adminonly Bob"

    You will receive an `io.rsocket.exceptions.ApplicationErrorException: Access Denied` exception because while you are authenticated, you are not authorized to access the method.
    
7. Finally, let's call the `hello.secure.adminonly` endpoint again, but this time use the `Admin` token by running the following command:

        ./gradlew :hello-client:bootRun --args="--token {Admin Token Here} hello.secure.adminonly Bob"
        
    You will receive a successful hello message because you have supplied a valid JWT token with admin scope:
    
        2019-12-20 10:47:56.047  INFO 2054 --- [           main] e.client.hello.HelloClientApplication    : Response: Hello, Bob! - from secured method [admin only]

## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/springboot-rsocketjwt-example/issues).

## License
MIT License

Copyright (c) 2019 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
