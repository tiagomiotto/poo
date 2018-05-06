package grid;

import java.util.List;
public class Grid {
    private int cols;
    private int rows;
    private Point[][] gridMatrix;
    private Coordinates initCoord, finCoord;
    /*
    private Tuple[] tuples;
    private Point[] obstacles;
    */

    // constructor
    public Grid (int cols, int rows/*Tuple[] costZones, Obstacle[] obstList*/) {
        this.cols = cols;
        this.rows = rows;
        /*
        this.tuples = costZones;
        this.obstacles = obstList;
        */
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



	// toString description
    @Override
    public String toString() {
        String outString = "Grid: ";
        Point aPoint;

        for (int x = 0 ; x < cols ; x++) {
            for (int y = 0 ; y < rows ; y++) {
                aPoint = gridMatrix[x][y];
                outString += "\n(" +(x+1) +"," +(y+1) +")" +"obstáculo:" +(aPoint.obsFlag()) +"\n";

                if (!(aPoint.obsFlag())) {
                    // norte
                    if (y < rows-1  &&  aPoint.adjFlag(gridMatrix[x][y+1])) {
                        outString += "Norte: " +aPoint.getAdjCost(gridMatrix[x][y+1]) +"\n";
                    }
                    // sul
                    if (y > 0  &&  aPoint.adjFlag(gridMatrix[x][y-1])) {
                        outString += "Sul: " +aPoint.getAdjCost(gridMatrix[x][y-1]) +"\n";
                    }
                    // este
                    if (x < cols-1  &&  aPoint.adjFlag(gridMatrix[x+1][y])) {
                        outString += "Este: " +aPoint.getAdjCost(gridMatrix[x+1][y]) +"\n";
                    }
                    // oeste
                    if (x > 0  &&  aPoint.adjFlag(gridMatrix[x-1][y])) {
                        outString += "Oeste: " +aPoint.getAdjCost(gridMatrix[x-1][y]) +"\n";
                    }
                }
            }
        }
        return outString;
    }

    // getter de um Point da gridMatrix correspondente às Coordinates dadas
    public Point getPoint (Coordinates aCoord) {
        return gridMatrix[aCoord.getX()][aCoord.getY()];
    }

    // adiciona um Point à Grid
    public void addPoint (Point aPoint) {
        gridMatrix[aPoint.getX()][aPoint.getY()] = aPoint;
    }

    // adiciona os adjacentes com custo 1 aos pontos da Grid
    public void fillGridAdj () {
        // percorre a gridMatrix
        for (int x = 0 ; x < cols ; x++) {
            for (int y = 0 ; y < rows ; y++) {
                // se o ponto não for obstáculo
                if (!(gridMatrix[x][y].obsFlag())) {
                    // norte
                    if (y < rows-1  &&  !(gridMatrix[x][y+1].obsFlag())) {
                        gridMatrix[x][y].addAdj(gridMatrix[x][y+1],1);
                    }
                    // sul
                    if (y > 0  &&  !(gridMatrix[x][y-1].obsFlag())) {
                        gridMatrix[x][y].addAdj(gridMatrix[x][y-1],1);
                    }
                    // este
                    if (x < cols-1  &&  !(gridMatrix[x+1][y].obsFlag())) {
                        gridMatrix[x][y].addAdj(gridMatrix[x+1][y],1);
                    }
                    // oeste
                    if (x > 0  &&  !(gridMatrix[x-1][y].obsFlag())) {
                        gridMatrix[x][y].addAdj(gridMatrix[x-1][y],1);
                    }
                }
            }
        }
    }

    // adiciona as zonas de custo Tuples à Grid
    public void fillGridTuples (Tuple[] tuples) {
        int xi, yi, xf, yf;
        int cost;
        Point pointA, pointB;

        for (Tuple tuple : tuples) {
            xi = tuple.getiCoord().getX()-1;
            yi = tuple.getiCoord().getY()-1;
            xf = tuple.getfCoord().getX()-1;
            yf = tuple.getfCoord().getY()-1;
            cost = tuple.getCost();

            // direcção horizontal
            for (int x = xi ; x <= xf ; x++) {
                pointA = gridMatrix[x][yi];
                pointB = gridMatrix[x][yf];
                // este
                if (x < xf) {
                    if (pointA.getAdjCost(gridMatrix[x+1][yi]) < cost  &&  !(pointA.obsFlag())) {
                        pointA.setAdjCost(gridMatrix[x+1][yi],cost);
                    }
                    if (pointB.getAdjCost(gridMatrix[x+1][yf]) < cost  &&  !(pointB.obsFlag())) {
                        pointB.setAdjCost(gridMatrix[x+1][yf],cost);
                    }
                }
                // oeste
                if (x > xi) {
                    if (pointA.getAdjCost(gridMatrix[x-1][yi]) < cost  &&  !(pointA.obsFlag())) {
                        pointA.setAdjCost(gridMatrix[x-1][yi],cost);
                    }
                    if (pointB.getAdjCost(gridMatrix[x-1][yf]) < cost  &&  !(pointB.obsFlag())) {
                        pointB.setAdjCost(gridMatrix[x-1][yf],cost);
                    }
                }
            }
            // direcção vertical
            for (int y = yi ; y <= yf ; y++) {
                pointA = gridMatrix[xi][y];
                pointB = gridMatrix[xf][y];
                // norte
                if (y < yf) {
                    if (pointA.getAdjCost(gridMatrix[xi][y+1]) < cost  &&  !(pointA.obsFlag())) {
                        pointA.setAdjCost(gridMatrix[xi][y+1],cost);
                    }
                    if (pointB.getAdjCost(gridMatrix[xf][y+1]) < cost  &&  !(pointB.obsFlag())) {
                        pointB.setAdjCost(gridMatrix[xf][y+1],cost);
                    }
                }
                // sul
                if (y > yi) {
                    if (pointA.getAdjCost(gridMatrix[xi][y-1]) < cost  &&  !(pointA.obsFlag())) {
                        pointA.setAdjCost(gridMatrix[xi][y-1],cost);
                    }
                    if (pointB.getAdjCost(gridMatrix[xf][y-1]) < cost  &&  !(pointB.obsFlag())) {
                        pointB.setAdjCost(gridMatrix[xf][y-1],cost);
                    }
                }
            }
        }
    }



    // matrix builder
    public void buildGrid (Coordinates[] obstacles) {
        Point aPoint;

        // posiciona os obstáculos
        for (Coordinates obstacle : obstacles) {
            aPoint = new Obstacle(obstacle.getX() - 1,obstacle.getY() - 1);
            addPoint(aPoint);
        }

        // preenche o resto dos pontos livres
        for (int x = 0 ; x < cols ; x++) {
            for (int y = 0 ; y < rows ; y++) {
                if (gridMatrix[x][y] == null) {
                    aPoint = new Point(x,y);
                    addPoint(aPoint);
                }
            }
        }

        // preenche os pontos adjacentes de cada ponto da gridMatrix
        this.fillGridAdj();
    }

    // calcula a distância até ao ponto final
    public double dist (Coordinates coord) {
        return Math.sqrt(Math.pow(this.finCoord.getX()-coord.getX(),2) + Math.pow(this.finCoord.getY()-coord.getY(),2));
    }

    public int cost(List<Coordinates> path, int length) {
        //TODO cost calculator
        int i, cost = 0;
        return cost;
    }

}
