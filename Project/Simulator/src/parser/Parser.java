package parser;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import grid.Coordinates;
import grid.Tuple;

/**
 * Used to parse the xml input file
 *
 * @author Group 18
 */
public class Parser extends DefaultHandler {
    /**
     * finCoord stores the final point for individuals for a specified map
     * initCoord stores the initial point for individuals for a specified map
     */
    private Coordinates finCoord, initCoord;
    /**
     * c_max stores the final instant of the simulation
     * v_init stores the initial population of the simulation
     * v_max stores the maximum population of the simulation
     * k stores the comfort sensitivity to small variations
     * miu stores the Death event parameter
     * p stores the Reproduction event parameter
     * delta stores the Move event parameter
     * columns stores the specified number of columns
     * rows stores the specified number of rows
     */
    private int c_max, v_init, v_max, k, miu, p, delta, columns, rows;
    /**
     * tuples stores an array of high cost zones (Tuples)
     */
    private Tuple[] tuples;
    /**
     * obstacles stores an array of obstacles
     */
    private Coordinates[] obstacles;
    /**
     * xi_tuple stores the initial x coordinate of a Tuple
     * yi_tuple stores the initial y coordinate of a Tuple
     * xf_tuple stores the final x coordinate of a Tuple
     * yf_tuple stores the final y coordinate of a Tuple
     * n_obst stores the total number of Obstacles
     * n_tups stores the total number of Tuples
     */
    private int xi_tuple, yi_tuple, xf_tuple, yf_tuple, n_obst, n_tups;
    /**
     * tupleFlag indicates that a Tuple is being parsed
     */
    private boolean tupleFlag = false;

    /**
     * Parses the xml input file
     * @param filename
     *              String corresponding to the name of the file to be parsed
     */
    public void parseFile(String filename) {
        //Parse the XML files. All the data will be stored in the handler.
        try {
            File inputFile = new File(filename);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the parsing starts
     */
    @Override
    public void startDocument() {
        System.out.println("Parsing started");
    }

    /**
     * Called when the parsing ends
     */
    @Override
    public void endDocument() {
        System.out.println("Parsing concluded");
    }

    /**
     * Called for every new element of the file
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("simulation")) {
            c_max = Integer.parseInt(attributes.getValue("finalinst"));
            v_init = Integer.parseInt(attributes.getValue("initpop"));
            v_max = Integer.parseInt(attributes.getValue("maxpop"));
            k = Integer.parseInt(attributes.getValue("comfortsens"));
        } else if (qName.equalsIgnoreCase("grid")) {
            columns = Integer.parseInt(attributes.getValue("colsnb"));
            rows = Integer.parseInt(attributes.getValue("rowsnb"));
        } else if (qName.equalsIgnoreCase("initialpoint")) {
            int xinicial = Integer.parseInt(attributes.getValue("xinitial"));
            int yinicial = Integer.parseInt(attributes.getValue("yinitial"));
            // initial point of the simulation
            initCoord = new Coordinates(xinicial, yinicial);
        } else if (qName.equalsIgnoreCase("finalpoint")) {
            int xfinal = Integer.parseInt(attributes.getValue("xfinal"));
            int yfinal = Integer.parseInt(attributes.getValue("yfinal"));
            // final point of the simulation
            finCoord = new Coordinates(xfinal, yfinal);
        } else if (qName.equalsIgnoreCase("specialcostzones")) {
            int num = Integer.parseInt(attributes.getValue("num"));
            // array of high cost zones (tuples
            tuples = new Tuple[num];
        } else if (qName.equalsIgnoreCase("zone")) {
            xi_tuple = Integer.parseInt(attributes.getValue("xinitial"));
            yi_tuple = Integer.parseInt(attributes.getValue("yinitial"));
            xf_tuple = Integer.parseInt(attributes.getValue("xfinal"));
            yf_tuple = Integer.parseInt(attributes.getValue("yfinal"));
            tupleFlag = true;
        } else if (qName.equalsIgnoreCase("obstacles")) {
            int num = Integer.parseInt(attributes.getValue("num"));
            // array of obstacles
            obstacles = new Coordinates[num];
        } else if (qName.equalsIgnoreCase("obstacle")) {
            int x = Integer.parseInt(attributes.getValue("xpos"));
            int y = Integer.parseInt(attributes.getValue("ypos"));
            // creates obstacle and adds it to the array, incrementing the total number of obstacles
            Coordinates newObstacle = new Coordinates(x, y);
            obstacles[n_obst] = newObstacle;
            n_obst++;
        } else if (qName.equalsIgnoreCase("death")) {
            miu = Integer.parseInt(attributes.getValue("param"));
        } else if (qName.equalsIgnoreCase("reproduction")) {
            p = Integer.parseInt(attributes.getValue("param"));
        } else if (qName.equalsIgnoreCase("move")) {
            delta = Integer.parseInt(attributes.getValue("param"));
        }
    }

    /**
     * Used to parse each element
     */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (tupleFlag) {
            // points of interest of the tuple
            Coordinates initial_position = new Coordinates(xi_tuple, yi_tuple);
            Coordinates final_position = new Coordinates(xf_tuple, yf_tuple);
            // tuple cost
            String costString = new String(ch, start, length);
            int cost = Integer.parseInt(costString);
            // update of the array of tuples
            Tuple newZone = new Tuple(initial_position, final_position, cost);
            tuples[n_tups] = newZone;
            // increments number of tuples
            n_tups++;

            tupleFlag = false;
        }
    }

    //Getters
    public Coordinates getFinCoord() {
        return finCoord;
    }

    public Coordinates getInitCoord() {
        return initCoord;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Tuple[] getTuples() {
        return tuples;
    }

    public Coordinates[] getObstacles() {
        return obstacles;
    }

    public int getC_max() {
        return c_max;
    }

    public int getV_init() {
        return v_init;
    }

    public int getV_max() {
        return v_max;
    }

    public int getK() {
        return k;
    }

    public int getMiu() {
        return miu;
    }

    public int getP() {
        return p;
    }

    public int getDelta() {
        return delta;
    }
}
