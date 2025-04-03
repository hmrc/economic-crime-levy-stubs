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

| ECL Registration Reference | Get Obligation Data                                                                       | Get Financial Details                              |
|----------------------------|-------------------------------------------------------------------------------------------|----------------------------------------------------|
| leave creds empty          | New User Registering                                                                      |                                                    |
| XMECL0000000001            | A single open obligation that is due in the future                                        |                                                    |
| XMECL0000000002            | Two open obligations; one that is due in the future and one that is overdue               |                                                    |
| XMECL0000000003            | A single fulfilled obligation before the due date                                         | Due obligation                                     |
| XMECL0000000004            | Two obligations; one fulfilled before the due date and one open that is due in the future | Overdue obligation                                 |
| XMECL0000000005            | Three obligations; overdue, due and fulfilled                                             | Paid obligation                                    |
| XMECL0000000006            | A single fulfilled obligation before the due date                                         | Partially paid obligation                          |
| XMECL0000000007            | Three submitted obligations                                                               | Partially paid, paid and overdue                   |
| XMECL0000000008            | A single fulfilled obligation before the due date with a single payment which overpays    | Overpaid obligation single payment                 |
| XMECL0000000009            | A single fufilled obligation before the due date with multiple payments which overpays    | Overpaid obligation multiple payments              |
| XMECL0000000010            |                                                                                           | Paid obligation with partially paid interest       |
| XMECL0000000011            |                                                                                           | Paid obligation with  paid interest                |
| XMECL0000000012            |                                                                                           | Overdue obligation with interest                   |
| XMECL0000000013            |                                                                                           | Refund for overpayment                             |
| XMECL0000000014            |                                                                                           | Overdue obligation without interest document       |
| XMECL0000000015            |                                                                                           | Unexpected document type                           |
| XMECL0000000016            |                                                                                           | Paid obligation with reversal line item            |
| XMECL0000000017            |                                                                                           | Paid charge with interest and reversal line item   |
| XMECL0000000018            | Three submitted obligations                                                               | Partially paid, paid and overdue                   |
| XMECL0000000019            | Deregistered with amendable obligations                                                   | Deregistered with partially paid, paid and overdue |
| XMECL0000000020            |                                                                                           | Clearing Document financial data return            |
| XMECL0000000021            | Deregistered with due obligation                                                          |                                                    |
| XMECL0000000022            | Deregistered with no due obligations                                                      | Fully paid obligations                             |
| XMECL0000000022            | Deregistered with one submitted and one overdue obligation                                | One overdue payment                                |
| XMECL0000000404            | No obligations (404)                                                                      | No financial data (404)                            |
| XMECL0000000500            | Internal server error response (500)                                                      | Internal server error response (500)               |
| XMECL0000000400            | Bad request response (400)                                                                | Bad request response (400)                         |

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

Using [sm2](https://github.com/hmrc/sm2)
with the service manager profile `ECONOMIC_CRIME_LEVY_ALL` will start
all of the Economic Crime Levy microservices as well as the services
that they depend on.

> `sm2 --start ECONOMIC_CRIME_LEVY_ALL`

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
