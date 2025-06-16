# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR into the container
COPY target/wordcount.jar /app/wordcount.jar

# Copy the shell script to the container
COPY cicd/wordcount.sh /usr/local/bin/wordcount

# To format the shell scriptfile to Unix-style for linux system
RUN sed -i 's/\r$//' /usr/local/bin/wordcount

# Copy a sample input text file into the container
COPY src/main/resources/mytext.txt /app/mytext.txt

# Make the script executable
RUN chmod +x /usr/local/bin/wordcount

# Set the default command to open a terminal
CMD ["/bin/sh"]