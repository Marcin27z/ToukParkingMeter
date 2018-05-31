# parkingMeter

Service runs on localhost:8080

Database is stored in memory, but can be easily changed. Database is cleared with every execution.

Some methods are not used but are implemented to show future development posibilities.

Starting and stoping is executed basing on given id.

Parking once stopped cannot be continued.

There cannot be more than one parking running with the same id. But there can be started another with the same id after the running one is stopped.

Returned cost is cost of the latest parking with the given id.

Cost and duration are not updated in the database until the parking is stopped.

Time period for the price-list is one hour, but can be changed in CostCalculator to for example one minute for testing purposes.
