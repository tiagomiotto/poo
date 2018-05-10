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

    private Coordinates finalPosition, initialPosition;
    private int simulationTime, initpop, maxpop, k, nu, ro, delta, colsnb, rowsnb;
    private Tuple[] tuples;
    private Coordinates[] obstacles;
    private int xinicialZone, yinicialZone, xfinalZone, yfinalZone, obsnum, zonenum;
    private boolean zoneFlag = false;

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
            simulationTime = Integer.parseInt(attributes.getValue("finalinst"));
            initpop = Integer.parseInt(attributes.getValue("initpop"));
            maxpop = Integer.parseInt(attributes.getValue("maxpop"));
            k = Integer.parseInt(attributes.getValue("comfortsens"));
        }
        else if(qName.equalsIgnoreCase("grid"))
        {
            colsnb = Integer.parseInt(attributes.getValue("colsnb"));
            rowsnb = Integer.parseInt(attributes.getValue("rowsnb"));
        }
        else if(qName.equalsIgnoreCase("initialpoint"))
        {
            int xinicial = Integer.parseInt(attributes.getValue("xinitial"));
            int yinicial = Integer.parseInt(attributes.getValue("yinitial"));

            //Make the point where the simulation starts
            initialPosition = new Coordinates(xinicial, yinicial);
        }
        else if(qName.equalsIgnoreCase("finalpoint"))
        {
            int xfinal = Integer.parseInt(attributes.getValue("xfinal"));
            int yfinal = Integer.parseInt(attributes.getValue("yfinal"));

            //Make the point where the simulation ends
            finalPosition = new Coordinates(xfinal, yfinal);
        }
        else if(qName.equalsIgnoreCase("specialcostzones"))
        {
            int num = Integer.parseInt(attributes.getValue("num"));

            //Create zones array
            tuples = new Tuple[num];
        }
        else if(qName.equalsIgnoreCase("zone"))
        {
            xinicialZone = Integer.parseInt(attributes.getValue("xinitial"));
            yinicialZone = Integer.parseInt(attributes.getValue("yinitial"));
            xfinalZone = Integer.parseInt(attributes.getValue("xfinal"));
            yfinalZone = Integer.parseInt(attributes.getValue("yfinal"));
            zoneFlag = true;
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
            obstacles[obsnum] = newObstacle;

            //Increment obstacles number
            obsnum++;
        }
        else if(qName.equalsIgnoreCase("death"))
        {
            nu = Integer.parseInt(attributes.getValue("param"));
        }
        else if(qName.equalsIgnoreCase("reproduction"))
        {
            ro = Integer.parseInt(attributes.getValue("param"));
        }
        else if(qName.equalsIgnoreCase("move"))
        {
            delta = Integer.parseInt(attributes.getValue("param"));
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if(zoneFlag)
        {
            //Get points to build the zone, coordinates were given by the the attributes of the element
            Coordinates initial_position = new Coordinates(xinicialZone, yinicialZone);
            Coordinates final_position = new Coordinates(xfinalZone, yfinalZone);

            //Get the cost from the element content
            String costString = new String(ch, start, length);
            int cost = Integer.parseInt(costString);

            //Create the zone and add it to the list
            Tuple newZone = new Tuple(initial_position, final_position, cost);
            tuples[zonenum] = newZone;

            //Increment zone number
            zonenum++;

            zoneFlag = false;
        }
    }

    //Getters
    public Coordinates getFinalPoint() {return finalPosition;}
    public Coordinates getInitialPoint() {return initialPosition;}
    public int getRows() {return rowsnb;}
    public int getColumns() {return colsnb;}
    public Tuple[] getTuples() {return tuples;}
    public Coordinates[] getObstacles() {return obstacles;}
    public int getSimulationTime() {return simulationTime;}
    public int getInitPop() {return initpop;}
    public int getMaxPop() {return maxpop;}
    public int getK() {return k;}
    public int getNu() {return nu;}
    public int getRo() {return ro;}
    public int getDelta() {return delta;}
}

