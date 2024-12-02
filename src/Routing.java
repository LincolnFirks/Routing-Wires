import java.util.*;

public class Routing {

    /**
     * TODO
     * <p>
     * The findPaths function takes a board and a list of goals that contain
     * endpoints that need to be connected. The function returns a list of
     * Paths that connect the points.
     */
    public static ArrayList<Wire>
    findPaths(Board board, ArrayList<Endpoints> goals) {
        ArrayList<Wire> res = new ArrayList<>(); // result
        Queue<Endpoints> pairs = new LinkedList<>(goals);
        // Queue will hold rest of wires in current permutation
        while (!pairs.isEmpty()) {
            Endpoints e = pairs.poll();
            Wire curr = BFS(board, e); // run BFS: O(n) time
            while (curr == null) { // while there is no path found
                Wire last = res.get(res.size()-1); 
                res.remove(res.size()-1);
                board.removeWire(last);
                // remove the last wire we put down
                pairs.add(new Endpoints(last.id, last.start(), last.end()));
                // add removed wire to queue
                curr = BFS(board, e);
                // BFS again: O(n) time
            } // loop could have to run size of res times, which could be O(p) worst case for p pairs of goals
            board.placeWire(curr);
            res.add(curr);
            // once found valid wire placement, add to board and reult

        } // in total, each permutation of order of placing wires could have to be tested. So outer loop is O(p!)
          // each pair in each permutation could have to run BFS, so total time complexity is O(p! * n)
        return res;
    }

    public static Wire BFS(Board board, Endpoints e) {
        Queue<Wire> Q = new LinkedList<>();
        Q.add(new Wire(e.id,new ArrayList<Coord>( List.of(e.start))));
        // will use current wire we are building in queue
        HashSet<Coord> seen = new HashSet<>(); // visited coords
        seen.add(e.start);
        while (!Q.isEmpty()) {
            Wire curr = Q.poll();
            if (curr.end().equals(e.end)) { 
                return curr; // if at destination, return current wire
            }
            for (Coord adj : board.adj(curr.end())) { // go through neighbors
                if ((!seen.contains(adj) &&  !board.isOccupied(adj)) || (adj.equals(e.end))) {
                    // if valid not seen already and space is free
                    seen.add(adj); // add to seen
                    Wire newWire = new Wire(e.id, curr.getPoints());
                    newWire.add(adj);
                    Q.add(newWire);
                    // make new wire with added neighbor and add to queue
                }
            }
        } 
        return null;
    } 
    // loop could have to visit every coordinate on the board
    // Hence, function time complexity for O(n) for n valid coordinates on board

}
