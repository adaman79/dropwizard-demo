#!/bin/bash

sudo docker build -t adaman79/dropwizard-kundenverwaltung .
sudo docker push adaman79/dropwizard-kundenverwaltung
fleetctl --tunnel=10.0.3.121 stop dropwizard-customeradmin.1.service
fleetctl --tunnel=10.0.3.121 start dropwizard-customeradmin.1.service
