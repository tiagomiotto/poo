package grid;

import java.util.LinkedList;

public class Point extends Coordinates {

    private LinkedList<Edge> listAdj = new LinkedList<Edge>();

    // constructor
    public Point(int x, int y) {
        super(x, y);
    }

    // por definição não é obstáculo
    public boolean obsFlag() {
        return false;
    }

    // adiciona um ponto à sua lista de adjacentes
    public void addAdj(Point adjacent, int costAdj) {
        Edge adjPoint = new Edge(adjacent, costAdj);
        listAdj.addFirst(adjPoint);
    }

    // verifica se um ponto é adjacente a outro
    public boolean adjFlag(Point adjacent) {
        for (Edge edge : listAdj) {
            if (edge.getAdjacent() == adjacent) {
                return true;
            }
        }
        return false;
    }

    // getter do custo do adjacente
    public int getAdjCost(Point adjacent) {
        for (Edge edge : listAdj) {
            if (edge.getAdjacent() == adjacent) {
                return edge.getCost();
            }
        }
        return 0;
    }

    // setter do custo do adjacente
    public void setAdjCost(Point adjacent, int costAdj) {
        for (Edge edge : listAdj) {
            if (edge.getAdjacent() == adjacent) {
                edge.setCost(costAdj);
            }
        }
    }

    public int getAdjNum() {
        return listAdj.size();
    }

    public LinkedList<Edge> getListAdj() {
        return listAdj;
    }
}
