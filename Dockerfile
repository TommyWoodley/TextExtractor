FROM openjdk:17.0.1-jdk-slim

RUN apt update

# Install tesseract library
RUN apt install -y tesseract-ocr libtesseract-dev

#ENV TESSDATA_PREFIX=/Users/tomwoodley/IdeaProjects/DLPProcessorV3/ocrProcessor/tessdata/
#ENV TESSDATA_PREFIX_2=/Users/tomwoodley/IdeaProjects/DLPProcessorV3/ocrProcessor/tessdata/

# Check the installation status
RUN tesseract --list-langs
RUN tesseract -v

COPY target/ocrProcessor-0.0.1-SNAPSHOT.jar /app.jar
COPY eng.traineddata /eng.traineddata

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
