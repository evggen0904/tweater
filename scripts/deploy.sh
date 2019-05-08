#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/tweater-1.0-SNAPSHOT.jar \
    john@192.168.1.200:/home/john/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa john@192.168.1.200 << EOF
ps ax | grep -i java | grep 'tweater' | awk {'print $1'} | xargs kill
nohup java -jar tweater-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'