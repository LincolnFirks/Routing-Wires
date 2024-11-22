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
        ArrayList<Wire> res = new ArrayList<>();
        Queue<Endpoints> pairs = new LinkedList<>(goals);
        while (!pairs.isEmpty()) {
            Endpoints e = pairs.poll();
            Wire curr = BFS(board, e);
            while (curr == null) {
                Wire last = res.get(res.size()-1);
                res.remove(res.size()-1);
                board.removeWire(last);
                pairs.add(new Endpoints(last.id, last.start(), last.end()));
                curr = BFS(board, e);
            }
            board.placeWire(curr);
            res.add(curr);

        }
        return res;
    }

    public static Wire BFS(Board board, Endpoints e) {
        Queue<Wire> Q = new LinkedList<>();
        Q.add(new Wire(e.id,new ArrayList<Coord>( List.of(e.start))));
        HashSet<Coord> seen = new HashSet<>();
        seen.add(e.start);
        while (!Q.isEmpty()) {
            Wire curr = Q.poll();
            if (curr.end().equals(e.end)) {
                return curr;
            }
            for (Coord adj : board.adj(curr.end())) {
                if ((!seen.contains(adj) &&  !board.isOccupied(adj)) || (adj.equals(e.end))) {
                    seen.add(adj);
                    Wire newWire = new Wire(e.id, curr.getPoints());
                    newWire.add(adj);
                    Q.add(newWire);
                }
            }
        }


        return null;
    }

}
