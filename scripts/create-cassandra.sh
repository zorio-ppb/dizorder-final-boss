#!/bin/zsh

docker create -d --name cassandra_dfb -v "${PWD}"/cassandra_data:/var/lib/cassandra -p 9042:9042 cassandra