#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh # import profile.sh

REPOSITORY=/home/ec2-user/app/step3
PROJECT_NAME=spring-boot-blog-freelec
echo "> Copy Build file"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> JAR NAME: $JAR_NAME"

echo "> make $JAR_NAME executable"

chmod +x $JAR_NAME

echo "> execute $JAR_NAME"

IDLE_PROFILE=$(find_idle_profile)

echo "> execute $JAR_NAME with profile=$IDLE_PROFILE"
nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
  -Dspring.profiles.active=$IDLE_PROFILE \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &