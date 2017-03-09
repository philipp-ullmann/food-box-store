# Food Box Store Demo [![Build Status](https://travis-ci.org/philipp-ullmann/food-box-store.svg?branch=master)](https://travis-ci.org/philipp-ullmann/food-box-store)

Online food box store to sell austrian food packages.

## Development

* Start **webserver** with: `docker-compose up -d`
* Run **tests** with: `docker-compose exec web lein test`
* Update geoip files with: `docker-compose exec web bin/update_geoip_files.sh`
