@echo off
echo Starting Grid Hub....
echo start java -jar lib/selenium-server-standalone-2.39.0.jar -role hub -hubConfig ConfigFiles/HubConfig.json
echo Registering Nodes to Hub....
echo start java -jar lib/selenium-server-standalone-2.39.0.jar -role webdriver -nodeConfig ConfigFiles/NodeConfig.json
echo Setting classpath environment variables...
set ANT_HOME=%CD%\ant
set PATH=%PATH%;%ANT_HOME%\bin
set JDK="%ProgramFiles%\Java\jdk*"
for /d %%i in (%JDK%) do set JAVA_HOME=%%i
:: Display Variables and Launch Ant
set JAVA_HOME
echo Starting execution....
call ant runTests_Grid
echo Stopping Nodes and Grid....
echo taskkill /im java.exe
pause