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

            //Make the point where the simulation starts
            initCoord = new Coordinates(xinicial, yinicial);
        }
        else if(qName.equalsIgnoreCase("finalpoint"))
        {
            int xfinal = Integer.parseInt(attributes.getValue("xfinal"));
            int yfinal = Integer.parseInt(attributes.getValue("yfinal"));

            //Make the point where the simulation ends
            finCoord = new Coordinates(xfinal, yfinal);
        }
        else if(qName.equalsIgnoreCase("specialcostzones"))
        {
            int num = Integer.parseInt(attributes.getValue("num"));

            //Create zones array
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

            //Create obstacles array
            obstacles = new Coordinates[num];
        }
        else if(qName.equalsIgnoreCase("obstacle"))
        {
            int x = Integer.parseInt(attributes.getValue("xpos"));
            int y = Integer.parseInt(attributes.getValue("ypos"));

            //Create obstacle and add it to the array
            Coordinates newObstacle = new Coordinates(x, y);
            obstacles[n_obst] = newObstacle;

            //Increment obstacles number
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
            //Get points to build the zone, coordinates were given by the the attributes of the element
            Coordinates initial_position = new Coordinates(xi_tuple, yi_tuple);
            Coordinates final_position = new Coordinates(xf_tuple, yf_tuple);

            //Get the cost from the element content
            String costString = new String(ch, start, length);
            int cost = Integer.parseInt(costString);

            //Create the zone and add it to the list
            Tuple newZone = new Tuple(initial_position, final_position, cost);
            tuples[n_tups] = newZone;

            //Increment zone number
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
