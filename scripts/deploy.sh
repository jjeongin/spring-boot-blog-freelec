#!/bin/bash

REPOSITORY=/home/ecw-user/app/step2
PROJECT_NAME=spring-boot-blog-freelec

echo "> Copy Build file"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Check current running application"

CURRENT_PID=$(pgrep -fl spring-boot-blog-freelec | grep jar | awk '{print $1}')

echo "Current running application pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> No current application"
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> New application deploy"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> Make $JAR_NAME executable"

chmod +x $JAR_NAME

echo "> Execute $JAR_NAME"

nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
  -Dspring.profiles.active=real \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &