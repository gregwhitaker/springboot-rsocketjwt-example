# springboot-rsocketjwt-example
An example of using [JWT](https://jwt.io/), for authentication and authorization, with [RSocket](http://rsocket.io) and Spring Boot.

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