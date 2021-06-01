#!/bin/bash

stop_tomcat()
{
   "$CATALINA_HOME/bin/catalina.sh" stop
}

fail() {
   echo "There were errors"
}

open_browser() {
chromium "http://localhost:8080/crud/v1/task/getTasks"
}

if ./gradlew build; then
  sudo ./runcrud.sh
  open_browser
else
   stop_tomcat
   fail
fi