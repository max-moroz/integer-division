#!/bin/bash

#==============================================================
# Task 4 - Integer division
# Execute from project's root folder
# This is for building project and placing it into newly created directory /dist
# together with all source files and uber-jar
# Java archive is not created if any test failed or code coverage not been met.
#==============================================================

./mvnw clean package

mkdir dist
cp DivisionApplication.cmd DivisionApplication.sh ./dist
cd target
cp DivisionApplication.jar ../dist
exit 0
