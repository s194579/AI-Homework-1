# AI-Homework-1
**Game:**
Kalaha (2 players, human or AI)

**Rules:**
The goal of this two-player game is to collect more seeds in our store than our opponent. 
Player sides are divided such that Index 0 (left end) is the store of player 2, Index 7 (right end) is the store of player 1, 
Index 1-6 (bottom) are the houses of player 1, Index 8-13 (top) are the houses of player 2. 
At the start of a players turn, the player chooses a non-empty house on their own side, takes all the seeds from the house, moves in the direction of play (counter clockwise), 
and drops them one by one into the subsequent houses and into their own store. Only when a player completes a turn by placing the last seed of the previously 
selected house in their own store may they take an extra turn.

**Strategy:**
Clever players can take advantage of the extra turns mechanic granted by finishing their turns in their stores and —depending on the configuration of the board— 
can clear the board in a single turn. For example, the longest possible chain on a standard Kalaha board of six pits lasts for seventeen moves where the configuration is 
[5,4,2,3,1,1]. It can be shown that, for all size boards, there exists one and only one pattern.

**Installation:**
1. Install the project source code in zip file and run.
2. Run Main in Application.java 
3. Follow printed out game instructions to decide in which players are human, which are AI, 
   and how many seeds are in each starting house (4+). Four seeds in each house is the traditional method.
4. If AI players are selected, write a single search time for the AI in milliseconds (0+). 
   A higher value increases the performance of the AI. 
