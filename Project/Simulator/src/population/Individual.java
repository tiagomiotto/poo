package population;

import grid.Coordinates;
import grid.Point;
import simulator.Simulator;


import java.util.ArrayList;

public class Individual {

    private double comfort;
    private int cost; //A função nao calcula certo porque o path ta sempre nulo
    private int length;
    private Point current;
    private ArrayList<Coordinates> path;
    private int possibilities;
    private Simulator simulator;
    private boolean ended = false;

    public void evolve(Point new_P) { //For move
        // TODO - implement Evolution
        current.setX(new_P.getX());
        current.setY(new_P.getY());

        if (simulator.getGrid().getFinCoord().equals(new_P)) {
            ended = true;
            simulator.getWinners().add(this);
        }
        if (path.contains(new_P)) { //Ta mal aqui, ta sempre apagando o path
            //System.out.println("a");
            //System.out.println(path.toString());
            //System.out.println(current.toString());
            int l_aux = path.indexOf(new_P);
            //System.out.println(l_aux);
            ArrayList<Coordinates> auxiliar = new ArrayList<>(path);
            path = new ArrayList<Coordinates>();
            for (int i = 0; i < l_aux + 1; i++) path.add(auxiliar.get(i));
        } else {
            //System.out.println("b");
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

    public Individual(Simulator simulator) {
        this.cost = 0;
        this.length = 0;
        this.current = new Point(1, 1);
        path = new ArrayList<Coordinates>();
        path.add(new Coordinates(1, 1));
        this.simulator = simulator;
        this.possibilities = simulator.getGrid().getPoint(current).getAdjNum();
        double dist = simulator.getGrid().dist(new Coordinates(current.getX(), current.getY()));
        this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
                simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getRows() + simulator
                .getGrid().getCols() + 1))), simulator.getVariables().getK());


    }

    public Individual(Individual o1) { //Construtor para reprodução
        //TODO construct from a parent individual

        //90% do caminho do pai +  uma variavel dependendo go conforto
        double percentage = Math.ceil(90 + o1.getComfort() * 10);

        this.length = (int) Math.ceil(percentage / 100 * o1.path.size()); //Grid pequena da sempre 100% do path
        double auxlen = length;
        /*
        System.out.println("daddy path size; " + o1.path.size());
        System.out.println("child path size; " + auxlen);*/
        if (this.length == 0 || o1.path.size() < 1) {
            path = new ArrayList<Coordinates>();
            path.add(new Point(1, 1));
            this.current = new Point(1, 1);
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
        ; //da erro aqui

        ended = o1.ended;
        double dist = simulator.getGrid().dist(new Coordinates(current.getX(), current.getY()));
        this.comfort = Math.pow((1 - ((this.cost - this.length + 2) / (simulator.getVariables().getC_max() * this.length + 3))),
                simulator.getVariables().getK()) * Math.pow((1 - (dist / (simulator.getGrid().getRows() + simulator
                .getGrid().getCols() + 1))), simulator.getVariables().getK());
    }

    // adicionar uma descrição textual do caminho do individuo

    public String getPathDesc() {

        String aux = "";
        int i;
        //System.out.println(path.get(1).getX());
        for (i = 0; i < path.size() - 1; i++) {
            String my_pos = "(" + (path.get(i).getX() + 1) + "," + (path.get(i).getY() + 1) + ")" + "->";
            aux += my_pos;
        }
        String my_pos = "(" + (path.get(i).getX() + 1) + "," + (path.get(i).getY() + 1) + ")";
        aux += my_pos;


        return aux; //funt
    }


    /*Getters and setters*/

    public boolean isEnded() {
        return ended;
    }

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

    public int getCost() {
        return cost;
    }

    public Point getCurrent() {
        return current;
    }
}
