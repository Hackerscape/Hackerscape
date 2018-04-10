@echo off
title Server
:run
cls
"C:\Program Files\Java\jdk1.8.0_161\bin\java.exe" -Xmx1000m -cp bin;deps/poi.jar;deps/mysql.jar;deps/mina.jar;deps/slf4j.jar;deps/slf4j-nop.jar;deps/jython.jar;log4j-1.2.15.jar; server.Server
goto run