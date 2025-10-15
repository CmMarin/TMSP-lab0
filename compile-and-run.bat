@echo off
echo SOLID Principles Demo (Maven Structure)
echo ========================================
echo.

cd /d "g:\TMSP-lab0"

echo Compiling from project root...
if not exist target\classes mkdir target\classes
javac -cp src\main\java -d target\classes -sourcepath src\main\java -source 8 -target 8 src\main\java\com\example\solid\SolidDemo.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compiled successfully!
echo.

echo Running Interactive Demo...
java -cp target\classes com.example.solid.SolidDemo

echo.
echo Complete!
pause > nul