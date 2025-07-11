#!/bin/zsh

if docker inspect "cassandra_dfb" > /dev/null 2>&1; then
  docker stop cassandra_dfb
  docker rm cassandra_dfb
  rm -rf ../cassandra_data
fi

docker create --name cassandra_dfb -v "${PWD}"/cassandra_data:/var/lib/cassandra -p 9042:9042 cassandra
