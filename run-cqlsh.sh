#!/bin/zsh

docker run -it --rm --network container:cassandra_dfb cassandra cqlsh $(docker inspect --format='{{ .NetworkSettings.IPAddress }}' cassandra_dfb)