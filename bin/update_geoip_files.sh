#!/bin/bash

echo "Downloading files"
curl http://geolite.maxmind.com/download/geoip/database/GeoLiteCityv6-beta/GeoLiteCityv6.dat.gz > resources/GeoLiteCityv6.dat.gz
curl http://geolite.maxmind.com/download/geoip/database/asnum/GeoIPASNumv6.dat.gz > resources/GeoIPASNumv6.dat.gz

echo "Extracting files"
gunzip -c resources/GeoLiteCityv6.dat.gz > resources/GeoLiteCityv6.dat
gunzip -c resources/GeoIPASNumv6.dat.gz > resources/GeoIPASNumv6.dat

echo "Removng files"
rm resources/GeoLiteCityv6.dat.gz resources/GeoIPASNumv6.dat.gz
