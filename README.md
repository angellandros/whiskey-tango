# whiskey-tango
Whiskey Tango

## Install
Install `sbt` and run Apache Spark, then add the Spark master URL to the config file in `whiskey-tango/conf/application.conf` or export it as an environment variable:
```
$ export SPARK_MASTER="spark://<ip>:<port>"
```
Put the semicolon-separated values (CSV) file inside the root directory as `input.csv`.
Then inside the web-app folder `whiskey-tango` (where you can find `build.sbt`) run:
```
$ sbt clean build package
$ sbt run
```
The project will then be accessible from port 9000. Until now, there is only one REST API for the cities:
```
http://localhost:9000/v1.0/cities?from=2010-01-30&until=2017-09-30&city=Hamburg
```
