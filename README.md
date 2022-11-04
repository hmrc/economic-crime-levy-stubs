# economic-crime-levy-stubs

This service acts as a stub for any DES (Data Exchange Service) and IF (Integration Framework) APIs that the ECL backend
microservices make calls to.

## Test Data
Use the IDs in the following table to trigger different API outcomes:

| APIs                  | Data                                                                  | ID              |
|-----------------------|-----------------------------------------------------------------------|-----------------|
| - Get Obligation Data | - A single open obligation that is due in the future                  | XMECL0000000001 |
| - Get Obligation Data | - A single open obligation that is overdue                            | XMECL0000000002 |
| - Get Obligation Data | - A single fulfilled obligation that was received before the due date | XMECL0000000003 |
| - Get Obligation Data | - No obligations (404)                                                | XMECL0000000404 |
| - Get Obligation Data | - Internal server error response (500)                                | XMECL0000000500 |
| - Get Obligation Data | - Bad request response (400)                                          | XMECL0000000400 |

## Running the service

> `sbt run`

The service runs on port `14004` by default.

## Running dependencies

Using [service manager](https://github.com/hmrc/service-manager)
with the service manager profile `ECONOMIC_CRIME_LEVY_ALL` will start
all of the Economic Crime Levy microservices as well as the services
that they depend on.

> `sm --start ECONOMIC_CRIME_LEVY_ALL`

## Running tests

### Unit tests

> `sbt test`

### Integration tests

> `sbt it:test`

### All tests

This is an sbt command alias specific to this project. It will run a scala format
check, run a scala style check, run unit tests, run integration tests and produce a coverage report.
> `sbt runAllChecks`

## Scalafmt and Scalastyle

To check if all the scala files in the project are formatted correctly:
> `sbt scalafmtCheckAll`

To format all the scala files in the project correctly:
> `sbt scalafmtAll`

To check if there are any scalastyle errors, warnings or infos:
> `sbt scalastyle`

### License

This code is open source software licensed under
the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").