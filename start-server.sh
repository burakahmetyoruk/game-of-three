docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management


cd game-of-three-server || exit
mvn clean install
java -jar target/server-0.0.1-SNAPSHOT.jar -DIS_MANUAL=false