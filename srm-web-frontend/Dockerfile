# This Dockerfile is for compiling the frontend stuff
FROM debian:wheezy
VOLUME /data
RUN apt-get update
RUN apt-get -y install node
RUN apt-get -y install vim
RUN apt-get update
RUN apt-get -y install curl
RUN curl -sL https://deb.nodesource.com/setup | bash -
RUN apt-get install -y nodejs
RUN apt-get install -y build-essential
RUN npm install express-generator -g
RUN npm install nodemon -g
RUN npm install forever -g
RUN apt-get update
RUN npm install -g typescript
RUN npm install -g tsd
RUN npm install -g jasmine
RUN apt-get update
RUN npm install webpack -g
#RUN apt-get -y install postgresql
#RUN apt-get -y install nginx
