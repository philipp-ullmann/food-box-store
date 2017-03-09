# Food Box Store Demo

Online food box store to sell austrian food packages.

## Development

* Start **webserver** with: `docker-compose up -d`
* Run **tests** with: `docker-compose exec web lein test`
* Update geoip files with: `docker-compose exec web bin/update_geoip_files.sh`
