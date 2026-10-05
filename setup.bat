@echo off
setlocal EnableExtensions

cd /d "%~dp0"

echo === Parallel Worlds development setup ===
echo.

REM Git

where git >nul 2>&1

if errorlevel 1 (
    echo Git is not installed.
    echo.

    where winget >nul 2>&1

    if errorlevel 1 (
        echo Error: Git is required and winget is not available.
        echo Install Git and run setup.bat again.
        exit /b 1
    )

    echo Installing Git with winget...
    winget install --id Git.Git -e --source winget

    if errorlevel 1 (
        echo Error: Failed to install Git.
        exit /b 1
    )

    echo.
    echo Git was installed.
    echo Please restart this terminal and run setup.bat again.
    exit /b 0
)

echo Git:
git --version

REM Git repository

if not exist ".git\" (
    echo.
    echo Initializing Git repository...
    git init

    if errorlevel 1 exit /b 1
)

REM Java 25

set "JAVA25_HOME="

if defined JAVA_HOME (
    "%JAVA_HOME%\bin\java.exe" -version 2>&1 | findstr /R /C:"version \"25" >nul

    if not errorlevel 1 (
        set "JAVA25_HOME=%JAVA_HOME%"
    )
)

if not defined JAVA25_HOME (
    for /f "delims=" %%J in ('where java 2^>nul') do (
        "%%J" -version 2>&1 | findstr /R /C:"version \"25" >nul

        if not errorlevel 1 (
            for %%D in ("%%J\..\..") do set "JAVA25_HOME=%%~fD"
            goto :java_found
        )
    )
)

:java_found

if not defined JAVA25_HOME (
    echo.
    echo Java 25 is required.

    where winget >nul 2>&1

    if errorlevel 1 (
        echo Error: Java 25 is required and winget is not available.
        echo Install a Java 25 JDK and run setup.bat again.
        exit /b 1
    )

    echo Installing Eclipse Temurin 25 with winget...

    winget install --id EclipseAdoptium.Temurin.25.JDK -e --source winget

    if errorlevel 1 (
        echo Error: Failed to install Java 25.
        exit /b 1
    )

    echo.
    echo Java 25 was installed.
    echo Please restart this terminal and run setup.bat again.
    exit /b 0
)

set "JAVA_HOME=%JAVA25_HOME%"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo Java:
java -version

REM Gradle wrapper

echo.
echo Gradle:
call gradlew.bat --version

if errorlevel 1 exit /b 1

REM Git hooks

echo.
echo Configuring Git hooks...

if not exist ".githooks\" mkdir ".githooks"

git config core.hooksPath .githooks

if errorlevel 1 exit /b 1

if exist ".githooks\pre-push" (
    echo Pre-push hook found.
)

echo Git hooks path:
git config core.hooksPath

REM Development dependencies

echo.
echo Downloading and configuring development dependencies...

call gradlew.bat spotlessApply

if errorlevel 1 exit /b 1

call gradlew.bat classes

if errorlevel 1 exit /b 1

REM Validation

echo.
echo Running formatter check...

call gradlew.bat spotlessCheck

if errorlevel 1 exit /b 1

REM Python

where py >nul 2>&1

if errorlevel 1 (
    echo.
    echo Python is required.

    where winget >nul 2>&1

    if errorlevel 1 (
        echo Error: Python is required and winget is not available.
        echo Install Python 3.13 and run setup.bat again.
        exit /b 1
    )

    echo Installing Python 3.13 with winget...

    winget install --id Python.Python.3.13 -e --source winget

    if errorlevel 1 (
        echo Error: Failed to install Python.
        exit /b 1
    )

    echo.
    echo Python was installed.
    echo Please restart this terminal and run setup.bat again.
    exit /b 0
)

echo Python:
py --version

if not exist ".venv\" (
    echo.
    echo Creating Python virtual environment...
    py -3 -m venv .venv

    if errorlevel 1 exit /b 1
)

echo.
echo Installing Python development tools...

".venv\Scripts\python.exe" -m pip install --upgrade pip

if errorlevel 1 exit /b 1

".venv\Scripts\python.exe" -m pip install black

if errorlevel 1 exit /b 1

echo Black:
".venv\Scripts\python.exe" -m black --version

echo.
echo === Development environment ready ===
echo.
echo Java 25:       OK
echo Gradle:        OK
echo Git:           OK
echo Git hooks:     OK
echo Formatters:    OK
echo Fabric/Loom:   OK
echo Python:        OK
echo Black:         OK

endlocal
