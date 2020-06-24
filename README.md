# Game Of Three

- A two Spring Boot application  which allows two players to play  Game of Three. 
- Game has two mode (Auto / Manual), you can easily set play mode in `start-server.sh`
- It is implemented with event-driven approach by using spring-cloud-stream-binder-rabbit.

## Technical information
### Back End

*  Java 11
*  Spring Cloud Stream use `RabbitMQ` as binder
*  Docker to run `RabbitMQ`

### Events

Application | Event | Queue |
------------ | ------------- | ------------- |
server | GameStarted | client |
client | PlayerJoined | game |
server | GameEnded | client |
server | PlayerMoved | player2 |
client | PlayerMoved | player1 |

### Prerequisites to run locally
```
1. JDK 11
2. Maven
3. Docker
```

### How to run locally
```
1. git clone https://github.com/burakahmetyoruk/game-of-three
2. Open terminal at the folder of source code.
3. to run `Server` execute command in terminal `start-server.sh`
4. to run `Client` execute command in new terminal `start-client.sh`
```

```
If you want to play in `Manual` game mode, you can easily set `IS_MANUAL=true` in `start-servers.sh`
```
