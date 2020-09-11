FROM maven:3.6.3-jdk-11-slim

RUN useradd -d /home/deploy -m -s /bin/bash deploy

WORKDIR /tmp/app

RUN chown -R deploy:deploy .

USER deploy

EXPOSE 8080
