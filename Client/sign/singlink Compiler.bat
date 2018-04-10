@echo off
if exist "%programfiles%\Java" (call :compile "%programfiles%\Java\") else (goto error)
:compile
for /D %%x in ("%~1jdk*") do (set p="%%~x\bin\javac.exe")
if defined p (%p% -cp . *.java)
if defined p (goto end)
:error
echo You do not have Java installed. Please download it at the site that is about to load.
"%programfiles%\Internet Explorer\iexplore.exe" http://java.sun.com/javase/downloads/index.jsp
:end
echo Finished!
pause
exit