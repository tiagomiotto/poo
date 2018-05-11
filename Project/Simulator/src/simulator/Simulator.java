package simulator;

import grid.Coordinates;
import grid.Grid;
import grid.Tuple;
import parser.Dom;
import parser.Parser;
import pec.*;
import population.Individual;
import population.IndividualComparator;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Simulator.java - A simple class to implement the main necessary for the Simulator
 *
 * @author Tiago Miotto
 * @version 1.0
 */
public class Simulator {

    private PEC pec;
    private Event currentEv;
    private Grid grid;
    private Comparator<Individual> comp = new IndividualComparator();
    private PriorityQueue<Individual> population = new PriorityQueue<Individual>(10, comp);
    private PriorityQueue<Individual> winners = new PriorityQueue<Individual>(10, comp);
    private EventVariables variables = new EventVariables(0, 0, 0, 0, 0, 0, 0);
    private double currentTime;
    private int events;


    public static void main(String[] args) {

        Simulator sim = new Simulator();
        sim.currentTime = 0.0;
        int observ = 0;
        sim.events = 0;
        int i = 1;
        Parser myParser = new Parser();
        myParser.parseFile("/Users/joaotrindade/IdeaProjects/poonoob/data1.xml");
        sim.createGrid(myParser);
        sim.pec = new PEC();
        sim.begin(myParser);

        System.out.println("Hey");
        sim.currentEv = sim.pec.getEvents().peek();
        System.out.println("Ho");
        sim.currentTime = sim.currentEv.getTimestamp();

        while (sim.currentTime < sim.variables.c_max) {

            if (sim.currentTime > sim.variables.c_max * i / 20) {  //Observations
                observ++;
                System.out.println("Observation: " + observ);
                System.out.println("Present instant: " + sim.currentTime);
                System.out.println("Number of realized events: " + sim.events);

                if (sim.winners.size() > 0) System.out.println("Final point has been hit? Yes");
                else System.out.println("Final point has been hit? No");

                System.out.println("Path of the best fit individual: " + sim.population.peek().getPathDesc());
                System.out.println("Cost/Comfort " + sim.population.peek().getCost() + "/" + sim.population.peek().getComfort());
                System.out.println("Final coordinates " + sim.grid.getFinCoord().getX() + "," + sim.getGrid()
                        .getFinCoord().getY());
                System.out.println("Size of pop: " + sim.population.size());

                System.out.println();

                i++;
            }
            sim.currentEv = sim.pec.nextEventPEC();
            if (sim.currentEv == null) {
                System.out.println("PEC ran out of events at time: " + sim.currentTime);

                break;
            }
            sim.currentTime = sim.currentEv.getTimestamp();
            sim.events++;
        }
        //Final tally
        if (sim.winners.size() > 0) System.out.println("Path of the best fit winner: " + sim.winners.peek().getPathDesc
                ());
        else if (sim.population.peek() == null) {
            System.out.println("Everyone died");
        } else {
            System.out.println("Simulation finished");
            System.out.println("Path of the best fit individual: " + sim.population
                    .peek()
                    .getPathDesc());
            System.out.println("Finished: " + sim.population.peek().isEnded());
        }

    }

    /**
     * A method to read and save the provided variables for the simulation
     *
     * @param myParser, a reference to the XML parser object
     */
    private void begin(Parser myParser) {
        variables = new EventVariables(myParser.getMiu(),myParser.getP(),myParser.getDelta(),myParser.getV_max(),myParser.getK(),myParser.getC_max(),myParser.getV_init());
        for (int i = 0; i < variables.v_init; i++) {
            Individual firstborn = new Individual(this);
            pec.addEventPEC(new Reproduction(firstborn, 0, pec));
            pec.addEventPEC(new Move(firstborn, 0, pec));
            pec.addEventPEC(new Death(firstborn, 0, pec));
            population.add(firstborn);
        }
    } //Create initial population

    /**
     * Retrieve a reference to the Individuals which finished
     */
    public PriorityQueue<Individual> getWinners() {
        return winners;
    }

    /**
     * Retrieve the simulation variables
     */
    public EventVariables getVariables() {
        return variables;
    }

    /**
     * Retrieve the Grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Retrieve a reference to the Population
     */
    public PriorityQueue<Individual> getPopulation() {
        return population;
    }

    /**
     * Retrieve a reference to the PEC
     */
    public PEC getPec() {
        return pec;
    }

    /**
     * A method to create and fill the grid with the values obtained from the XML
     *
     * @param myParser, a reference to the XML parser object
     */
    private void createGrid(Parser myParser) {
        Dom parse = new Dom();
        Tuple[] tuples = myParser.getTuples();
        Coordinates[] obst = myParser.getObstacles();
        int n_obst = myParser.getObstacles().length;
        int n_tup = myParser.getTuples().length;
        grid = new Grid(myParser.getColumns(), myParser.getRows());
        grid.setInitCoord(myParser.getInitialPoint());
        grid.setFinCoord(myParser.getFinalPoint());
        grid.updateGrid();
        grid.buildGrid(obst);
        grid.fillGridTuples(tuples);
    }
}
