package population;

import grid.Coordinates;
import grid.Point;
import simulator.Simulator;


import java.util.ArrayList;

public class Individual {

	private double comfort;
	private int cost;
	private int length;
	private Point current;
	private ArrayList<Coordinates> path;
    private int possibilities;
    private Simulator simulator;

	public void evolve(int new_x, int new_y) { //For move
        // TODO - implement Evolution
		current.setX(new_x);
		current.setY(new_y);
		if (path.contains(current)) { //Caso já exista no path
			int l_aux = path.indexOf(current);
			path = new ArrayList<Coordinates>(path.subList(0, l_aux + 1));
		} else {
			this.length += 1;
			path.add(current);
		}
		this.cost = simulator.getGrid().cost(path, length);

		double dist = simulator.getGrid().dist(new Coordinates(current.getX(), current.getY()));
        this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
                simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getRows() + simulator
                .getGrid().getCols() + 1))), simulator.getVariables().getK());


		this.possibilities = current.getAdjNum();
		throw new UnsupportedOperationException();
	}

    public Individual(Simulator simulator) {
        this.cost = 0;
        this.length = 0;
		this.current = new Point(1, 1);
		path = new ArrayList<Coordinates>();
        this.simulator = simulator;
		this.possibilities = current.getAdjNum();
		double dist = simulator.getGrid().dist(new Coordinates(current.getX(), current.getY()));
        this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
                simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getRows() + simulator
                .getGrid().getCols() + 1))), simulator.getVariables().getK());


	}

	public Individual(Individual o1) { //Construtor para reprodução
        //TODO construct from a parent individual
        double percentage = Math.ceil(90 + o1.getComfort() * 10);
        this.length = (int) Math.ceil(percentage / 100 * o1.getLength());
		path = new ArrayList<Coordinates>(o1.getPath().subList(0, this.length + 1));
		this.current = new Point((this.path.get(this.length).getX()), this.path.get(this.length).getY());
		this.cost = simulator.getGrid().cost(this.path, this.length);
		this.possibilities = current.getAdjNum();
		this.simulator = o1.simulator;
		double dist = simulator.getGrid().dist(new Coordinates(current.getX(), current.getY()));
		this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
				simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getRows() + simulator
				.getGrid().getCols() + 1))), simulator.getVariables().getK());
    }

	// adicionar uma descrição textual do caminho do individuo

	public String getPathDesc () {

		StringBuffer aux = new StringBuffer();
		for (Coordinates a : path) {
			String my_pos = "(" + a.getX() + "," + a.getY() + ")" + "->";
			aux.append(my_pos);
		}
		return aux.toString();
	}


	// adicionar um ponto ao caminho
	/*
	public void addPointPath (Point aPoint) {

	}
	 */


	/*Getters and setters*/
	public double getComfort() {
		return comfort;
	}


	public int getPossibilities() {
		return possibilities;
	}

	public int getLength() {
		return length;
	}


	public int getMy_x() {
		return current.getX();
	}


	public int getMy_y() {
		return current.getY();
	}


	public ArrayList<Coordinates> getPath() {
        return path;
    }

    public Simulator getSimulator() {
        return simulator;
    }
}