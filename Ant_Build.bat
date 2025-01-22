SET SIKULIX_HOME=%CD%\SikuliX64
SET ProgFiles86Root=%ProgramFiles(x86)%
IF NOT "%ProgFiles86Root%"=="" GOTO amd64
SET SIKULIX_HOME=%CD%\SikuliX86
:amd64
:: Set Variables 
set ANT_HOME=%CD%\ant
set PATH=%PATH%;%ANT_HOME%\bin;%SIKULIX_HOME%\libs
set JDK="%ProgramFiles%\Java\jdk*"
for /d %%i in (%JDK%) do set JAVA_HOME=%%i
:: Display Variables and Launch Ant
set JAVA_HOME
echo %ANT_HOME%
echo %SIKULIX_HOME%
echo %PATH%
call ant
pause
