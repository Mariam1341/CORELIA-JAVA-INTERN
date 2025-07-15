cd F:\vegaLaptop\Desktop\CORELIA-JAVA-INTERN
git status | findstr /C:"nothing to commit" >nul
if %errorlevel% neq 0 (
  git add .
  git commit -m "Auto commit on %date% %time%"
  git push origin main
)
