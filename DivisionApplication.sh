#!/bin/bash

#==========================================================
# Task 4 - Integer division
# This should be at the same folder as DivisionApplication.jar
# This is for executing DivisionApplication application
#==========================================================


# Check for DivisionApplication.jar file
if [ ! -f $(dirname "$0")/DivisionApplication.jar ]; then
echo "Error: DivisionApplication.jar file is not found"
exit 1
fi

# Iterate through the list of given command line arguments
java -jar $(dirname "$0")/DivisionApplication.jar $@
exit 0

