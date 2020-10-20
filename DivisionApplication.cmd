::=============================================================
:: Task 4 - Integer division
:: This should be at the same folder as DivisionApplication.jar
:: This is for executing DivisionApplication application
::=============================================================

@ECHO OFF
set file_name=DivisionApplication.jar

:: Check if .jar file available
IF NOT EXIST %~dp0%file_name% (
ECHO Error: DivisionApplication.jar is not found
EXIT /B 2
)


java -jar %~dp0%file_name% %*

