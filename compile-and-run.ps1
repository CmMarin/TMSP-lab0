# SOLID Principles Demo (Maven Structure)
Write-Host "SOLID Principles Demo (Maven Structure)" -ForegroundColor Cyan
Write-Host "==========================================" -ForegroundColor Cyan
Write-Host ""

Set-Location "g:\TMSP-lab0"

# Create target directory if it doesn't exist
if (!(Test-Path "target\classes")) {
    New-Item -ItemType Directory -Path "target\classes" -Force | Out-Null
}

Write-Host "Compiling from project root..." -ForegroundColor Yellow
javac -cp src\main\java -d target\classes -sourcepath src\main\java -source 8 -target 8 src\main\java\com\example\solid\SolidDemo.java

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed!" -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}

Write-Host "Compiled successfully!" -ForegroundColor Green
Write-Host ""

Write-Host "Running Interactive Demo..." -ForegroundColor Cyan
java -cp target\classes com.example.solid.SolidDemo

Write-Host ""
Write-Host "Complete!" -ForegroundColor Green