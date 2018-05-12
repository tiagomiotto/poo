package population;

import grid.Coordinates;
import grid.Point;
import simulator.Simulator;


import java.util.ArrayList;

/**
 * Individual.java - A class defining the attributes of each individual and
 * how it evolves throughout the simulation, according to the project parameters
 *
 * @author Tiago Miotto
 * @version 1.0
 */

public class Individual implements IIndividual {

    private double comfort;
    private int cost;
    private int length;
    private Point current;
    private ArrayList<Coordinates> path;
    private int possibilities;
    private Simulator simulator;
    private boolean ended = false;

    /**
     * Define how the individual evolves throughout the simulation, in this case, through moves
     *
     * @param new_P, the point the individual is suposed to move to
     */
    public void evolve(Point new_P) {

        current.setX(new_P.getX());
        current.setY(new_P.getY());

        if (simulator.getGrid().getFinCoord().getX() == new_P.getX() + 1 && simulator.getGrid().getFinCoord().getY() == new_P.getY() + 1) {
            ended = true;
            simulator.getWinners().add(this);
        }
        if (path.contains(new_P)) {

            int l_aux = path.indexOf(new_P);
            ArrayList<Coordinates> auxiliar = new ArrayList<>(path);
            path = new ArrayList<Coordinates>();
            for (int i = 0; i < l_aux + 1; i++) path.add(auxiliar.get(i));
        } else {
            this.length += 1;
            path.add(new_P);


        }
        this.cost = simulator.getGrid().cost(path, length);

        double dist = simulator.getGrid().dist(new Coordinates(current.getX(), current.getY()));

        this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
                simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getRows() + simulator
                .getGrid().getCols() + 1))), simulator.getVariables().getK());


        this.possibilities = simulator.getGrid().getPoint(current).getAdjNum();

    }


    /**
     * A simple constructor for a new individual
     *
     * @param simulator, a reference for the simulator object doing the simulation
     */
    public Individual(Simulator simulator) {
        this.cost = 0;
        this.length = 0;
        this.current = new Point(simulator.getGrid().getInitCoord().getX() - 1, simulator.getGrid().getInitCoord().getY() - 1);
        path = new ArrayList<Coordinates>();
        path.add(new Coordinates(simulator.getGrid().getInitCoord().getX() - 1, simulator.getGrid().getInitCoord().getY() - 1));
        this.simulator = simulator;
        this.possibilities = simulator.getGrid().getPoint(current).getAdjNum();
        double dist = simulator.getGrid().dist(new Coordinates(current.getX(), current.getY()));
        this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
                simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getRows() + simulator
                .getGrid().getCols() + 1))), simulator.getVariables().getK());


    }

    /**
     * A simple constructor for a new individual, how is born in a reproduction event, inheriting characteristics from its father
     *
     * @param o1, the parent individual
     */
    public Individual(Individual o1) { //Construtor para reprodução

        //90% do caminho do pai +  uma variavel dependendo go conforto
        double percentage = Math.ceil(90 + o1.getComfort() * 10);

        this.length = (int) Math.ceil(percentage / 100 * o1.path.size()); //Grid pequena da sempre 100% do path
        double auxlen = length;


        if (this.length == 0 || o1.path.size() < 1) {
            path = new ArrayList<Coordinates>();
            path.add(new Point(o1.path.get(0).getX() - 1, o1.path.get(0).getY() - 1));
            this.current = new Point(o1.path.get(0).getX() - 1, o1.path.get(0).getY() - 1);
        } else {
            this.path = new ArrayList<Coordinates>();
            for (int i = 0; i < auxlen; i++) path.add(o1.path.get(i)); //da erro aqui
            if (path.size() == 1)
                this.current = new Point((this.path.get(path.size() - 1).getX()), this.path.get(path.size
                        () - 1
                ).getY
                        ());
            else this.current = new Point((this.path.get(path.size() - 1).getX()), this.path.get(path.size() - 1
            ).getY
                    ());
        }

        this.simulator = o1.simulator;
        this.cost = simulator.getGrid().cost(this.path, this.length);
        this.possibilities = simulator.getGrid().getPoint(current).getAdjNum()
        ;

        ended = o1.ended;
        double dist = simulator.getGrid().dist(new Coordinates(current.getX(), current.getY()));
        this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
                simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getRows() + simulator
                .getGrid().getCols() + 1))), simulator.getVariables().getK());
    }


    /**
     * A method similar to a toString method for the path taken by the individual
     *
     * @return A String data type.
     */
    public String getPathDesc() {

        String aux = "{";
        int i;
        //System.out.println(path.get(1).getX());
        for (i = 0; i < path.size() - 1; i++) {
            String my_pos = "(" + (path.get(i).getX() + 1) + "," + (path.get(i).getY() + 1) + ")" + ",";
            aux += my_pos;
        }
        String my_pos = "(" + (path.get(i).getX() + 1) + "," + (path.get(i).getY() + 1) + ")}";
        aux += my_pos;


        return aux; //funt
    }


    /*Getters and setters*/

    /**
     * Retrieve the ended variable, to see if the individual has reached the final point
     */
    public boolean isEnded() {
        return ended;
    }

    /**
     * Retrieve the individual's confort
     */
    public double getComfort() {
        return comfort;
    }

    /**
     * Retrieve the number of adjacent positions the individual can move to
     */
    public int getPossibilities() {
        return possibilities;
    }

    /**
     * Retrieve a reference to the individuals path
     */
    public ArrayList<Coordinates> getPath() {
        return path;
    }

    /**
     * Retrieve a reference to the simulator
     */
    public Simulator getSimulator() {
        return simulator;
    }

    /**
     * Retrieve individual's curent cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Retrieve a reference to the individual's current Point
     */
    public Point getCurrent() {
        return current;
    }
}
