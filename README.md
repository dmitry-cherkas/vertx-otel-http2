## Summary

This repository contains a simple application to illustrate the https://github.com/open-telemetry/opentelemetry-java-instrumentation/issues/10377.


## Steps to reproduce:
1. Clone the repository
2. Build the application with `./gradlew clean assemble`
3. Run the application with the following command:
```bash
java -javaagent:opentelemetry-javaagent-1.32.1.jar \
     -Dotel.javaagent.debug=true \
     -Dotel.traces.exporter=logging \
     -jar build/libs/vertx-otel-http2.jar
```
4. Send a request to the application with the following command:
```bash
curl --http2 -vvv 'localhost:80'
```

## Expected behavior
Request should complete normally and the response should be `Hello, World!`:
```
*   Trying [::1]:80...
* Connected to localhost (::1) port 80
> GET / HTTP/1.1
> Host: localhost
> User-Agent: curl/8.4.0
> Accept: */*
> Connection: Upgrade, HTTP2-Settings
> Upgrade: h2c
> HTTP2-Settings: AAMAAABkAAQAoAAAAAIAAAAA
> 
< HTTP/1.1 101 Switching Protocols
< connection: upgrade
< upgrade: h2c
* Received 101, Switching to HTTP/2
* Copied HTTP/2 data in stream buffer to connection buffer after upgrade: len=62
< HTTP/2 200 
< content-length: 13
< 
* Connection #0 to host localhost left intact
Hello, World!%            
```

## Actual behavior
Server returns an empty response:
```
*   Trying [::1]:80...
* Connected to localhost (::1) port 80
> GET / HTTP/1.1
> Host: localhost
> User-Agent: curl/8.4.0
> Accept: */*
> Connection: Upgrade, HTTP2-Settings
> Upgrade: h2c
> HTTP2-Settings: AAMAAABkAAQAoAAAAAIAAAAA
> 
* Empty reply from server
* Closing connection
curl: (52) Empty reply from server
```

