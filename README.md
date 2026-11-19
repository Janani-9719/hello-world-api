# Hello World API

A Spring Boot service with one endpoint, `GET /hello-world?name=...`.

- Name starts with A–M → `200 {"message": "Hello Alice"}`
- Name starts with N–Z, or is missing/empty → `400 {"error": "Invalid Input"}`

Requires Java 17+. The Maven wrapper is included, so Maven does not need to be installed.

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

- Leading/trailing whitespace is ignored; a whitespace-only name counts as empty.
- Only the first letter is capitalised; the rest is returned as given (`mcDonald` → `McDonald`).
- Names must start with an English letter, so names starting with a digit, symbol or
  non-English letter (e.g. `Ángel`) return `400`.
