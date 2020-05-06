#!/bin/bash

cd /Users/guest1/Downloads/redis-5.0.7   

./src/redis-server redis.conf


cd /usr/local/Cellar/rabbitmq/3.7.8

rabbitmq-server -detached


