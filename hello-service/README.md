# hello-service
Service that returns a hello message.

## API
The `hello-service` exposes the following endpoints:

### hello
Endpoint that returns a hello message without authentication.

- Method: `hello`

### hello.secure
Endpoint that returns a hello message only for authenticated users.

- Method: `hello.secure`

### hello.secure.adminonly
Endpoint that returns a hello message only for authenticated users with the `ADMIN` scope.

- Method: `hello.secure.adminonly`
