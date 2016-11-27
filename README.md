# Game of War
This is an example of how to build the "game" of war with a Jersey API and MongoDB db.
 This API is could be consumed by a UI.`` This app can be expanded to host other 
 simple card games as well. 
 </br></br>
 Here are the current endpoints:
 
| URLs        | CRUD           |  Description | 
| :------------- |:-------------|:-------------|
| http://localhost:8080/api/health | GET | Validates Health|
| http://localhost:8080/api/game/war?numberOfSuits={a}&numberOfRanks={b} | POST | Creates new game with {a} suits and {b} ranks, returns {id} |
| http://localhost:8080/api/game/war/play?numberOfSuits={a}&numberOfRanks={b} | POST | Simulates a complete game with {a} suits and {b} ranks. Returns results of the game. |
| http://localhost:8080/api/game/war/{id} | GET | Returns game {id} | 
| http://localhost:8080/api/game/war/{id} | DELETE | Deletes game {id} | 
| http://localhost:8080/api/game/war/{id}/draw | POST | Moves the game ahead by 1 draw (including all resulting wars). Returns current status of the game along with cards played in that draw. |  
 
 I used the following:
- Jersey _2_
- Spring _4_
- mongo-java-driver _2_
- spring-data-mongodb _1.2_

In this project I also tested with difference frameworks
- junit _4.10_
- JMockit _1.24_
- hamcrest _1.3_
