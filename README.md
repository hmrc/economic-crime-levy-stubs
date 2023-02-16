# economic-crime-levy-stubs

This service acts as a stub for any DES (Data Exchange Service) and IF (Integration Framework) APIs that the ECL backend
microservices make calls to.

## APIs

The following APIs are stubbed by this service:

| API                           | Endpoint                                                                  | Method |
|-------------------------------|---------------------------------------------------------------------------|--------|
| #1330 Get Obligation Data     | `/enterprise/obligation-data/zecl/{eclRegistrationReference}/ECL`         | GET    |
| #1553 Get Financial Details   | `/enterprise/02.00.00/financial-data/zecl/{eclRegistrationReference}/ECL` | GET    |
| #1534 Get Subscription Status | `/cross-regime/subscription/ECL/SAFE/{businessPartnerId}/status`          | GET    |

## Test Data

Use the IDs in the following tables to trigger different API outcomes (APIs that share the same ID type are grouped in
to the same table):

| ECL Registration Reference | Get Obligation Data                                                 | Get Financial Details                |
|----------------------------|---------------------------------------------------------------------|--------------------------------------|
| XMECL0000000001            | A single open obligation that is due in the future                  | No payment due                       |
| XMECL0000000002            | A single open obligation that is overdue                            | No payment due                       |
| XMECL0000000003            | A single fulfilled obligation that was received before the due date | Payment due                          |
| XMECL0000000404            | No obligations (404)                                                | No financial data (404)              |
| XMECL0000000500            | Internal server error response (500)                                | Internal server error response (500) |
| XMECL0000000400            | Bad request response (400)                                          | Bad request response (400)           |

**NOTE**: Any ECL Registration Reference other than those listed in the above table will result in a 404 'No data found'
response.

| Business Partner ID | Get Subscription Status                |
|---------------------|----------------------------------------|
| XA0000000000001     | Not subscribed (form bundle not found) |
| XA0000000000002     | Already subscribed with ECL reference  |
| XA0000000000404     | No data found (404)                    |
| XA0000000000400     | Bad request response (400)             |
| XA0000000000500     | Internal server error response (500)   |

**NOTE**: Any Business Partner ID other than those listed in the above table will result in 200 'Not subscribed (form
bundle not found)' response.

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