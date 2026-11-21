# Hello World API

A Spring Boot service with one endpoint, `GET /hello-world?name=...`.

| Request                           | Response                             |
|-----------------------------------|--------------------------------------|
| Name starts with A–M (any case)   | `200 {"message": "Hello Alice"}`     |
| Name starts with N–Z              | `400 {"error": "Invalid Input"}`     |
| Name is missing or empty          | `400 {"error": "Invalid Input"}`     |

Built with Java 17 and Spring Boot 4.1. The Maven wrapper is included, so only a JDK 17+ is needed.

## Run the application

```bash
./mvnw spring-boot:run
```

Then open http://localhost:8080/hello-world?name=alice

## Run the tests

```bash
./mvnw test
```

## Assumptions

- Leading and trailing whitespace is ignored, so a whitespace-only name counts as empty.
- Only the first letter is capitalised; the rest is returned as given (`mcDonald` → `Hello McDonald`).
- The name must start with an English letter, so a leading digit, symbol or non-English letter
  (e.g. `Ángel`) returns `400`.
- Only the first character is checked (`jean-luc` and `ab1` are accepted).
- If `name` is repeated, Spring joins the values with a comma (`?name=alice&name=bob` → `Hello Alice,bob`).
