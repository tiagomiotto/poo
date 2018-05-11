package grid;

import java.util.List;

/**
 * Used to map the points, with obstacles, tuples, initial and final points
 *
 * @author Group 18
 */

public class Grid {
    /**
     * cols stores the specified number of columns
     * rows stores the specified number of rows
     */
    private int cols;
    private int rows;
    /**
     * gridMatrix  is (rows x cols) array that representes the grid as a matrix
     */
    private Point[][] gridMatrix;
    /**
     * initCoord stores the initial point for individuals for a specified map
     * finCoord stores the final point for individuals for a specified map
     */
    private Coordinates initCoord, finCoord;

    /**
     * Constructor for class Grid
     * @param cols
     *              specified number of columns
     * @param rows
     *              specified number of rows
     */
    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        initCoord = new Coordinates(0, 0);
        finCoord = new Coordinates(0, 0);

         // initializes the gridMatrix
        this.gridMatrix = new Point[cols][rows];
        for (int i = 0; i < cols; i++)
            for (int j = 0; j < rows; j++)
                gridMatrix[i][j] = null;
    }

    /**
     * Used to update the gridMatrix after parsing the file
     */
    public void updateGrid() {
        this.gridMatrix = new Point[cols][rows];
    }

    public Coordinates getInitCoord() {
        return initCoord;
    }


    public void setInitCoord(Coordinates initCoord) {
        this.initCoord = initCoord;
    }


    public Coordinates getFinCoord() {
        return finCoord;
    }


    public void setFinCoord(Coordinates finCoord) {
        this.finCoord = finCoord;
    }


    public int getCols() {
        return cols;
    }


    public void setCols(int cols) {
        this.cols = cols;
    }


    public int getRows() {
        return rows;
    }


    public void setRows(int rows) {
        this.rows = rows;
    }


    /**
     * Used to obtain a textual description of the Grid
     * @return
     *              string with said description
     */
    @Override
    public String toString() {
        String outString = "Grid: ";
        Point aPoint;

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                aPoint = gridMatrix[x][y];
                outString += "\n(" + (x + 1) + "," + (y + 1) + ")" + "obstáculo:" + (aPoint.obsFlag()) + "\n";

                if (!(aPoint.obsFlag())) {
                    // above
                    if (y < rows - 1 && aPoint.adjFlag(gridMatrix[x][y + 1])) {
                        outString += "Norte: " + aPoint.getAdjCost(gridMatrix[x][y + 1]) + "\n";
                    }
                    // below
                    if (y > 0 && aPoint.adjFlag(gridMatrix[x][y - 1])) {
                        outString += "Sul: " + aPoint.getAdjCost(gridMatrix[x][y - 1]) + "\n";
                    }
                    // right
                    if (x < cols - 1 && aPoint.adjFlag(gridMatrix[x + 1][y])) {
                        outString += "Este: " + aPoint.getAdjCost(gridMatrix[x + 1][y]) + "\n";
                    }
                    // left
                    if (x > 0 && aPoint.adjFlag(gridMatrix[x - 1][y])) {
                        outString += "Oeste: " + aPoint.getAdjCost(gridMatrix[x - 1][y]) + "\n";
                    }
                }
            }
        }
        return outString;
    }

    /**
     * Gets a Point from gridMatrix corresponding to the specified Coordinates
     * @param aCoord
     *              specified Coordinates
     * @return
     *              corresponding gridMatrix Point
     */
    public Point getPoint(Coordinates aCoord) {
        return gridMatrix[aCoord.getX()][aCoord.getY()];
    }


    /**
     * Adds a specified Point to the gridMatrix
     * @param aPoint
     *              specified Point
     */
    public void addPoint(Point aPoint) {
        gridMatrix[aPoint.getX()][aPoint.getY()] = aPoint;
    }

    // adiciona os adjacentes com custo 1 aos pontos da Grid

    /**
     * Fills the gridMatrix with the adjacent Points, with a default cost of 1
     */
    public void fillGridAdj() {
        // cycles through the gridMatrix
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                // if the Point isn't an obstacle
                if (!(gridMatrix[x][y].obsFlag())) {
                    // above the Point
                    if (y < rows - 1 && !(gridMatrix[x][y + 1].obsFlag())) {
                        gridMatrix[x][y].addAdj(gridMatrix[x][y + 1], 1);
                    }
                    // below the Point
                    if (y > 0 && !(gridMatrix[x][y - 1].obsFlag())) {
                        gridMatrix[x][y].addAdj(gridMatrix[x][y - 1], 1);
                    }
                    // right of the Point
                    if (x < cols - 1 && !(gridMatrix[x + 1][y].obsFlag())) {
                        gridMatrix[x][y].addAdj(gridMatrix[x + 1][y], 1);
                    }
                    // left of the Point
                    if (x > 0 && !(gridMatrix[x - 1][y].obsFlag())) {
                        gridMatrix[x][y].addAdj(gridMatrix[x - 1][y], 1);
                    }
                }
            }
        }
    }

    /**
     * Fills the grid with the special cost zones (Tuple)
     * @param tuples
     *              array of the specified special cost zones
     */
    public void fillGridTuples(Tuple[] tuples) {
        int xi, yi, xf, yf;
        int cost;
        Point pointA, pointB;

        // cycles throught the Array of Tuples
        for (Tuple tuple : tuples) {
            // applies the variable change
            xi = tuple.getiCoord().getX() - 1;
            yi = tuple.getiCoord().getY() - 1;
            xf = tuple.getfCoord().getX() - 1;
            yf = tuple.getfCoord().getY() - 1;
            cost = tuple.getCost();

            // cycles through the horizontal direction
            for (int x = xi; x <= xf; x++) {
                // defines Points on the same row
                pointA = gridMatrix[x][yi];
                pointB = gridMatrix[x][yf];
                // right
                if (x < xf) {
                    if (pointA.getAdjCost(gridMatrix[x + 1][yi]) < cost && !(pointA.obsFlag())) {
                        pointA.setAdjCost(gridMatrix[x + 1][yi], cost);
                    }
                    if (pointB.getAdjCost(gridMatrix[x + 1][yf]) < cost && !(pointB.obsFlag())) {
                        pointB.setAdjCost(gridMatrix[x + 1][yf], cost);
                    }
                }
                // left
                if (x > xi) {
                    if (pointA.getAdjCost(gridMatrix[x - 1][yi]) < cost && !(pointA.obsFlag())) {
                        pointA.setAdjCost(gridMatrix[x - 1][yi], cost);
                    }
                    if (pointB.getAdjCost(gridMatrix[x - 1][yf]) < cost && !(pointB.obsFlag())) {
                        pointB.setAdjCost(gridMatrix[x - 1][yf], cost);
                    }
                }
            }
            // cycles through the vertical direction
            for (int y = yi; y <= yf; y++) {
                // defines Points on the same column
                pointA = gridMatrix[xi][y];
                pointB = gridMatrix[xf][y];
                // above
                if (y < yf) {
                    if (pointA.getAdjCost(gridMatrix[xi][y + 1]) < cost && !(pointA.obsFlag())) {
                        pointA.setAdjCost(gridMatrix[xi][y + 1], cost);
                    }
                    if (pointB.getAdjCost(gridMatrix[xf][y + 1]) < cost && !(pointB.obsFlag())) {
                        pointB.setAdjCost(gridMatrix[xf][y + 1], cost);
                    }
                }
                // below
                if (y > yi) {
                    if (pointA.getAdjCost(gridMatrix[xi][y - 1]) < cost && !(pointA.obsFlag())) {
                        pointA.setAdjCost(gridMatrix[xi][y - 1], cost);
                    }
                    if (pointB.getAdjCost(gridMatrix[xf][y - 1]) < cost && !(pointB.obsFlag())) {
                        pointB.setAdjCost(gridMatrix[xf][y - 1], cost);
                    }
                }
            }
        }
    }


    // matrix builder

    /**
     * Builds a bare-bones gridMatrix version of map
     * @param obstacles
     *              array of specified Obstacles
     */
    public void buildGrid(Coordinates[] obstacles) {
        Point aPoint;

        // places the obstacles, applying the variable change
        for (Coordinates obstacle : obstacles) {
            aPoint = new Obstacle(obstacle.getX() - 1, obstacle.getY() - 1);
            addPoint(aPoint);
        }

        // fills the rest of the gridMatrix with "free" (non-obstacle) Points
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                // checks if the Point is "free" (non-obstacle)
                if (gridMatrix[x][y] == null) {
                    aPoint = new Point(x, y);
                    addPoint(aPoint);
                }
            }
        }

        // fills the gridMatrix with the adjacent Points of the "free" Points
        this.fillGridAdj();
    }

    /**
     * Calculates the distance from specified Coordinates to the final Coordinates
     * @param coord
     *              specified Coordinates
     * @return
     *              distance to final Coordinates
     */
    public double dist(Coordinates coord) {
        return Math.sqrt(Math.pow(this.finCoord.getX() - coord.getX(), 2) + Math.pow(this.finCoord.getY() - coord.getY(), 2));
    }

    /**
     * Calculates the total cost of an specified Individual's path
     * @param path
     *              array of Coordinates corresponding to the specified path
     * @param length
     *              integer corresponding to the length of the specified path
     * @return
     *              integer cost of the specified path
     */
    public int cost(List<Coordinates> path, int length) {
        int i, cost = 0;

        // cycles through the path, updating the value of the path cost
        for (i = 0; i < path.size() - 1; i++) {
            cost = cost + gridMatrix[path.get(i).getX()][path.get(i).getY()].getAdjCost(gridMatrix[path.get(i + 1).getX()][path.get(i + 1).getY()]);
        }

        return cost;
    }
}
