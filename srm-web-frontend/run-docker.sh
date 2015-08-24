./build-docker.sh
currentDir=`pwd`
docker run -i -t -p 3000:3000 -v $currentDir/data:/data \
-v $currentDir:/www \
srmdockerweb:latest bash

