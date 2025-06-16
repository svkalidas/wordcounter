FROM mcr.microsoft.com/windows/servercore:ltsc2022

# Set the working directory inside the container
WORKDIR /app

# Copy all .exe files into the container
COPY ../target/wordcount*.exe .

# Use PowerShell to find any .exe file and rename it to wordcount.exe
RUN powershell -Command "Get-ChildItem -Path . -Filter *.exe | Rename-Item -NewName 'wordcount.exe'"

# Use CMD to start an interactive shell
CMD ["cmd"]