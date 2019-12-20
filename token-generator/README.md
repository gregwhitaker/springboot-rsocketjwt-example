# token-generator
Generates two JWT tokens, admin and user, for this demo.

## Generate Tokens
Run the following command to generate the tokens:

    ./gradlew :token-generator:run
    
If successful, the generated tokens will be printed to the console:

    > Task :token-generator:run
    
    Generated Tokens
    ================
    Admin:
    eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6ImhlbGxvLXNlcnZpY2UiLCJzY29wZSI6IkFETUlOIiwiaXNzIjoiaGVsbG8tc2VydmljZS1kZW1vIiwiZXhwIjoxNTc2ODY3NzUxLCJqdGkiOiI5ZjAxOTQ0NS1hY2M2LTRhMGEtOTkyMy1mZjI2ODRlNGZmNGIifQ.0fTeSks9XBtKJRb9y4trOykfa2cYEZ9SJidspBtmKNc
    
    User:
    eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXVkIjoiaGVsbG8tc2VydmljZSIsInNjb3BlIjoiVVNFUiIsImlzcyI6ImhlbGxvLXNlcnZpY2UtZGVtbyIsImV4cCI6MTU3Njg2Nzc1MiwianRpIjoiMjUzNWNmZjUtNzE3My00ZTVhLWJiOWQtZTRmZDFhZjdlZmMxIn0.OhGWhRAKWL-kS1k6uOsZegRFhPFDu-BspNEvZhv5h4s
    
**Note:** The tokens are valid for `30 minutes`, after which you will need to regenerate new tokens to use the demo.
