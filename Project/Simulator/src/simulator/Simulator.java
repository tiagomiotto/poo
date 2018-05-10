package simulator;

import grid.Coordinates;
import grid.Grid;
import grid.Tuple;
import parser.Dom;
import pec.*;
import population.Individual;
import population.IndividualComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


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


    public static void main(String args[]) {
        // TODO - implement Simulator.main
        Simulator sim = new Simulator();
        sim.currentTime = 0.0;
        int observ = 0;
        sim.events = 0;
        int i = 1;
        sim.createGrid();
        sim.pec = new PEC();
        sim.begin();

        sim.currentEv = sim.pec.getEvents().peek();
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


    private void begin() {
        for (int i = 0; i < variables.v_init; i++) {
            Individual firstborn = new Individual(this);
            pec.addEventPEC(new Reproduction(firstborn, 0, pec));
            pec.addEventPEC(new Move(firstborn, 0, pec));
            pec.addEventPEC(new Death(firstborn, 0, pec));
            population.add(firstborn);
        }
    } //Create initial population

    public PriorityQueue<Individual> getWinners() {
        return winners;
    }

    public EventVariables getVariables() {
        return variables;
    }

    public Grid getGrid() {
        return grid;
    }

    public PriorityQueue<Individual> getPopulation() {
        return population;
    }

    public PEC getPec() {
        return pec;
    }

    private void createGrid() {
        Dom parse = new Dom();
        Tuple[] tuples = new Tuple[100];
        Coordinates[] obst = new Coordinates[100];
        grid = new Grid(0, 0);
        int n_obst = 0, n_tup = 0;
        parse.parser(variables, grid, tuples, obst, n_obst, n_tup);
        grid.updateGrid();
        tuples = Arrays.copyOf(tuples, n_tup);
        obst = Arrays.copyOf(obst, n_obst);
        grid.fillGridTuples(tuples);
        grid.buildGrid(obst);
    }
}