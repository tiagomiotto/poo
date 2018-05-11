package parser;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import grid.Coordinates;
import grid.Tuple;

public class Parser extends DefaultHandler {

    private Coordinates finCoord, initCoord;
    private int c_max, v_init, v_max, k, miu, p, delta, columns, rows;
    private Tuple[] tuples;
    private Coordinates[] obstacles;
    private int xi_tuple, yi_tuple, xf_tuple, yf_tuple, n_obst, n_tups;
    private boolean tupleFlag = false;

    public void parseFile(String filename)
    {
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

    @Override
    public void startDocument() {
        System.out.println("Parsing started");
    }

    @Override
    public void endDocument() {
        System.out.println("Parsing concluded");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("simulation"))
        {
            c_max = Integer.parseInt(attributes.getValue("finalinst"));
            v_init = Integer.parseInt(attributes.getValue("initpop"));
            v_max = Integer.parseInt(attributes.getValue("maxpop"));
            k = Integer.parseInt(attributes.getValue("comfortsens"));
        }
        else if(qName.equalsIgnoreCase("grid"))
        {
            columns = Integer.parseInt(attributes.getValue("colsnb"));
            rows = Integer.parseInt(attributes.getValue("rowsnb"));
        }
        else if(qName.equalsIgnoreCase("initialpoint"))
        {
            int xinicial = Integer.parseInt(attributes.getValue("xinitial"));
            int yinicial = Integer.parseInt(attributes.getValue("yinitial"));
            // initial point of the simulation
            initCoord = new Coordinates(xinicial, yinicial);
        }
        else if(qName.equalsIgnoreCase("finalpoint"))
        {
            int xfinal = Integer.parseInt(attributes.getValue("xfinal"));
            int yfinal = Integer.parseInt(attributes.getValue("yfinal"));
            // final point of the simulation
            finCoord = new Coordinates(xfinal, yfinal);
        }
        else if(qName.equalsIgnoreCase("specialcostzones"))
        {
            int num = Integer.parseInt(attributes.getValue("num"));
            // array of high cost zones (tuples
            tuples = new Tuple[num];
        }
        else if(qName.equalsIgnoreCase("zone"))
        {
            xi_tuple = Integer.parseInt(attributes.getValue("xinitial"));
            yi_tuple = Integer.parseInt(attributes.getValue("yinitial"));
            xf_tuple = Integer.parseInt(attributes.getValue("xfinal"));
            yf_tuple = Integer.parseInt(attributes.getValue("yfinal"));
            tupleFlag = true;
        }
        else if(qName.equalsIgnoreCase("obstacles"))
        {
            int num = Integer.parseInt(attributes.getValue("num"));
            // array of obstacles
            obstacles = new Coordinates[num];
        }
        else if(qName.equalsIgnoreCase("obstacle"))
        {
            int x = Integer.parseInt(attributes.getValue("xpos"));
            int y = Integer.parseInt(attributes.getValue("ypos"));

            // creates obstacle and adds it to the array, incrementing the total number of obstacles
            Coordinates newObstacle = new Coordinates(x, y);
            obstacles[n_obst] = newObstacle;
            n_obst++;
        }
        else if(qName.equalsIgnoreCase("death"))
        {
            miu = Integer.parseInt(attributes.getValue("param"));
        }
        else if(qName.equalsIgnoreCase("reproduction"))
        {
            p = Integer.parseInt(attributes.getValue("param"));
        }
        else if(qName.equalsIgnoreCase("move"))
        {
            delta = Integer.parseInt(attributes.getValue("param"));
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if(tupleFlag)
        {
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
    public Coordinates getFinCoord() {return finCoord;}
    public Coordinates getInitCoord() {return initCoord;}
    public int getRows() {return rows;}
    public int getColumns() {return columns;}
    public Tuple[] getTuples() {return tuples;}
    public Coordinates[] getObstacles() {return obstacles;}
    public int getC_max() {return c_max;}
    public int getV_init() {return v_init;}
    public int getV_max() {return v_max;}
    public int getK() {return k;}
    public int getMiu() {return miu;}
    public int getP() {return p;}
    public int getDelta() {return delta;}
}
