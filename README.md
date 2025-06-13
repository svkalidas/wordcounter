# WordCounter

An application to count the number of words in a text. The app will ask the user for the text upon start.
It will then output the number of words found in the text. Words are stretches of letters (a-z,A-Z).

## Features
- **Word Counting**: Counts words in a user-provided text input.
- **StopWords**: Provide the provision to configure pre-defined words, which will be excluded from counting.
- **Configuration**: The features can be configured via the cofig.properties file
- **Input Validation & Exception handling**: Ensure that the input is not null or empty and handle unexpected error gracefully.
- **Logging**: Log applicaton events and errors.

## Sample Usage:
1) Without stopwords:
- Enter Text: Mary had a little lamb
- Number of workds: 5
2) With stopwords:
  - stopwords : the, on, a, off
  - Enter Text: Mary had a little lamb
  - Number of workds: 4

## Technologies used

- **Java**: The application is built using Java 21.
- **Junit**: Used for unit testing.
- **SLF4J**: Used for logging.

## Prerequisites

- **Java 21**: Ensure Java 21 or higher is installed on your machine.
- **Maven**: Install Mave to manage dependencies and build the project.

### Building the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/svkalidas/wordcounter.git
   cd word-counter-app
2. Build the project using Maven:
   ```bash
   mvn clean package

3. Running the application:
   ```bash
   java -jar target/word-counter-app-1.0-SNAPSHOT.jar

4. Testing
   ```bash
   mvn test
