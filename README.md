# Project Write-Up

## 1. Overview of Algorithm
Our main algorithm utilizes BFS frequently, so first an overview of our BFS function. 

The BFS takes the board and a pair of endpoints. It initializes a Queue, initially filled with a single wire that is just the start endpoint. 
A hashset is also initialized to keep track of coordinates already seen as we navigate the board.
It then goes into a while loop that will continue until the queue is empty. On each iteration, it will pop (poll) the wire at the front of the queue.
If the wire is at the end coordinate, we return the wire as we have connected the endpoints. Otherwise, we will iterate through the adjacent coordinates
and if they are not occupied we add them to our "seen" hashset and make a new wire and add the adjacent endpoint to the end of the wire. The reason we use 
wires in our queue instead of just endpoints is so we can remember where the wire has been and return it. This algorithm will ensure the shortest path between endpoints as the
coordinates closer to the origin endpoint will always be added to the queue before coordinates farther do to the properties of the queue and our BFS. Finally, if we go through everything 
and cannot connect the two points, we return null.

Now for our findPaths function, which utilizes BFS:

We first initialize a ArrayList to hold the resulting wires. We will also initialzise a queue containing the pairs we need to connect. We then go into our while loop until
the queue is empty, popping each iteration. We run BFS on the endpoints that we popped. 
If the BFS returns a wire, we know a path was found and we can place the wire and add it to the result ArrayList.
If the BFS returns null, we know there is no possible way to connect the endpoint. In this case,we will enter another while loop in which
we will "pop" the last element of the result, which is the wire we placed most recently. We will remove this wire from the board and add it to the end of our queue of endpoints to be processed later.
At the end of this while loop we run BFS again, with the last placed wire removed. We will keep doing this process until we can place the wire (BFS doesn't return null). Once we have a valid wire, we place it
on the board and add it to the end of result.
Once the queue is empty, we know we have a valid board and we can return the result.


## 2. Applying our algorithm to an interesting board

https://drive.google.com/file/d/1bZH2Z_qB3xL8apKrwA5Apb4nTVymQEQQ/view?usp=sharing

## 3. Evaluation of our algorithm with respect to finding and minimizing wire layouts

Our algorithm uses BFS. BFS is exhaustive, so if we are unable to find a path between a pair of endpoints, it means it is either impossible, or there are some wires in the way that must be rerouted.
In the case that already some connected wire(s) must be rerouted, we backtrack and remove the most recently placed wire(s) till we are able to place a wire between the current pair of endpoints.
The repetition of this process ensures that we can place all the wires if it is possible. 
Additionally, BFS finds the shortest paths to the spots on the board, so while our backtracking approach may lead to some wires becoming elongated, ultimately we still minimize the wires lengths as best as we can.

For multiple boards without obstacles and wires that cross, our BFS with back-tracking produces the same wire lengths as the normal BFS.
However, if there are any violations, our algorithm with BFS and back-tracking produces wire lengths that fit the situation, both shortening and elongating certain wires to make it work.
On the other hand, our naive approach, with just BFS, fails.

For boards that cannot work under any circumstances, such as a 2x2 board filled with two pairs with endpoints where the start and end are diagonally across from each other on the board, 
our BFS with back-tracking would output an infinite loop as it will continue trying to back-track.
On the other hand, our naive BFS approach would not place down any wires since there is no back-tracking and the board does not work.


## 4. Evaluation of the time complexity and wall-clock time of our algorithm
- p! * (p * n) = all the combinations of wires * bfs for each of the pairs
  - n = number of spots on the board
  - p = number of pairs (could be n/2 at most)
- wall clock time = 41 ms 
  - excluding time to build board
  - tested the first example from question 2
