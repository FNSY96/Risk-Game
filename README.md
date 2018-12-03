# RISK Game

Risk is a strategy board game of diplomacy, conflict and conquest for two to six players. **This is a simplified version of RISK with only two players**. We allow the user to choose either to play against another human opponent, an AI agent or a Non AI agent, further more the user can select any 2 non human agents and click play to watch them playing each other and conquering the map! This can be used for pure research purpose in the AI in games field. 
 <br /> This project is one of CS 482 Artificial Intelligence course projects in Alexandria University.
## Non AI Agents
###### Human Agent
###### Passive Agent
Never attacks, and always places all its additional armies on the vertex that has the fewest armies, breaking ties by favoring the lowest-numbered vertex.
###### Aggressive Agent
Always places all its bonus armies on the vertex with the most armies, and greedily attempts to attack so as to cause the most damage - i.e. to prevent its opponent getting a continent bonus (the largest possible).
###### Pacifiest Agent
Places its armies like the completely passive agent, then conquers only one vertex (if it can), such that it loses as few armies as possible.
 
## AI Agents
###### Greedy Agent
Generate all possible states for the current state and put them into heap based on the heuristic value and pick the state with the maximum heuristic value and then it is the play which greedy algorithm chooses.
###### A* Agent
Generate all possible states for the current state and put them into heap based on the level of the children and the heuristic value and pick the state with the maximum sum for both of them “heuristic and the depth” and then it is the play which the AStar algorithm chooses.
<br /> We notice that because the expand the children of one node so the level “depth” for all children will be the same for all children so it will not affect of the heap “it will be the same without using the level in calculations” but this is more logical than building a full tree that gives the A* agent to backtrack in a ply and cancel opponents turn/s due to having a better state is at the top of the heap currently.
###### Real Time A* Agent
Real time A* ‘s basic idea is for every turn we build a tree for the current state with depth d and choose the the next state from the path that is most probably will lead the agent to win so for simplification we choose d is equal to one and perform the algorithm. 

 
## Heuristic
The heuristic is based upon the real game, as we played more than about 30 online RISK games to be able to understand the opponents and the main idea of the game.
The heuristic is the addition of 
- The sum of countries owned multiplied by a priority factor = 1
- The sum of potential continent bonus multiplied by a priority factor = 2
**This is multiplied by -1 to insert in a min heap.**
