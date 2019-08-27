import java.lang.module.FindException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORN ARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {

        // if current point out of bound of grid, then return false
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
            return false;

        // if current point cannot be part of the path, return false
        } else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
            return false;

        // if current point equals to exit, return true
        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;

        // if didn't find exit at current point, set current point to PATH
        } else {
            maze.recolor(x, y, PATH);

            // if the neighbour points of current point is exit, return true
            if (findMazePath(x - 1, y) || findMazePath(x + 1, y) ||
            findMazePath(x, y + 1) || findMazePath(x, y -1)) {
                return true;

            // else, recolor current point to TEMPORARY
            } else {
                maze.recolor(x, y, TEMPORARY);
                return false;
            }
        }

    }

    /*
    helper method
     */
    public void findMazePathStackBased(int x, int y,
                                       ArrayList<ArrayList<PairInt>> result,
                                       Stack<PairInt> trace){

        // if x and y are out of bounds or not equal to red, then  the find method terminates
        if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1 ||
                (!maze.getColor(x, y).equals(NON_BACKGROUND))){
            return;

        // if exit is found, then push the exit point to trace, and then add to result
        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            trace.push(new PairInt(x, y)); // exit point added to trace
            ArrayList<PairInt> cur = new ArrayList<>(trace); //
//            cur.addAll(trace); // add all elements to list cur from trace
            result.add(cur);
            trace.pop(); // After visited this point, need to remove from trace
            maze.recolor(x, y, NON_BACKGROUND); // recolor this point to Non_background for re-visiting
            return;
        } else {

            // if the point is not exit, then recursion is executed
            trace.push(new PairInt(x, y)); // push this point to trace
            maze.recolor(x, y, PATH); // recolor this point to PATH before recursion
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            // After tried all possible paths of current point, recolor it to non_background
            // implement backtracking
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
            return;
        }

    }

    /*
    wrapper method
     */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {

        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0, 0, result, trace);

        return result;
    }


    /*
    Find the shortest path by counting element in ArrayList<PairInt>
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y) {

        int index = 0;
        int[] count;
        int min;

        // arrayList minPath is the result from find all possible paths
        ArrayList<ArrayList<PairInt>> allPaths;
        allPaths = findAllMazePaths(x, y);

        // create an arrayList which size equals the size of result
        count = new int[allPaths.size()];

        // each element is the size of previous sublist of the result list
        for (int i = 0; i < allPaths.size(); i++) {
            count[i] = allPaths.get(i).size();
        }

        // initial min = 0
        min = count[0];

        // loop to find the smallest count and its index
        for (int i = 1; i < count.length; i++) {
            if (count[i] < min) {
                min = count[i];
                index = i;
            }
        }

        // return the path which has the smallest count number
        return allPaths.get(index);


    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
