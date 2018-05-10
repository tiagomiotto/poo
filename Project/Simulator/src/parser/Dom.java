package parser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import grid.Coordinates;
import grid.Grid;
import grid.Tuple;
import pec.EventVariables;


public class Dom {

    public void parser(EventVariables event_var, Grid grid_var, Tuple[] tuple_var,
                       Coordinates[] obst_var, int n_tup, int n_obst) {
        // TODO Auto-generated method stub
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("data1.xml");
            /*-----GET SIMULATION VALUES-------*/
            NodeList SIMULATION = doc.getElementsByTagName("simulation");
            for (int i = 0; i < SIMULATION.getLength(); i++) {
                Node p = SIMULATION.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element sim = (Element) p;
                    event_var.setC_max(Double.parseDouble(sim.getAttribute("finalinst")));
                    event_var.setV_init(Double.parseDouble(sim.getAttribute("initpop")));
                    event_var.setV_max(Double.parseDouble(sim.getAttribute("maxpop")));
                    event_var.setK(Double.parseDouble(sim.getAttribute("comfortsens")));
                }
            }
            /*-----GET GRID VALUES-------*/
            NodeList GRID = doc.getElementsByTagName("grid");
            Node p1 = GRID.item(0);
            if (p1.getNodeType() == Node.ELEMENT_NODE) {
                Element grid = (Element) p1;
                grid_var.setCols(Integer.parseInt(grid.getAttribute("colsnb")));
                grid_var.setRows(Integer.parseInt(grid.getAttribute("rowsnb")));

            }
            /*-----GET INITIAL POINTS-------*/
            NodeList initP = doc.getElementsByTagName("initialpoint");
            Node p2 = initP.item(0);
            if (p2.getNodeType() == Node.ELEMENT_NODE) {
                Element init_P = (Element) p2;
                grid_var.getInitCoord().setX(Integer.parseInt(init_P.getAttribute("xinitial")));
                grid_var.getInitCoord().setY(Integer.parseInt(init_P.getAttribute("yinitial")));
            }
            /*-----GET FINAL POINTS-------*/
            NodeList finalP = doc.getElementsByTagName("finalpoint");
            Node p3 = finalP.item(0);
            if (p3.getNodeType() == Node.ELEMENT_NODE) {
                Element finit_P = (Element) p3;
                grid_var.getFinCoord().setX(Integer.parseInt(finit_P.getAttribute("xfinal")));
                grid_var.getFinCoord().setY(Integer.parseInt(finit_P.getAttribute("yfinal")));
            }
            /*-----GET SPECIAL COST ZONES-------*/

            NodeList SpecZ = doc.getElementsByTagName("specialcostzones");
            Node p4 = SpecZ.item(0);
            if (p4.getNodeType() == Node.ELEMENT_NODE) {
                Element spec_Z = (Element) p4;
                String nume = spec_Z.getAttribute("num");

                NodeList zone = doc.getElementsByTagName("zone");

                int num1 = Integer.parseInt(nume);
                n_tup = num1;
                for (int m = 0; m < num1 - 1 ; m++) {
                    Node spec = zone.item(m);
                    if (spec.getNodeType() == Node.ELEMENT_NODE) {
                        Element speci = (Element) spec;
                        tuple_var[m] = new Tuple(null, null, 0);
                        tuple_var[m].getiCoord().setX(Integer.parseInt(speci.getAttribute("xinitial")));
                        tuple_var[m].getiCoord().setY(Integer.parseInt(speci.getAttribute("yinitial")));
                        tuple_var[m].getfCoord().setX(Integer.parseInt(speci.getAttribute("xfinal")));
                        tuple_var[m].getfCoord().setY(Integer.parseInt(speci.getAttribute("yfinal")));
                        tuple_var[m].setCost(Integer.parseInt(speci.getTextContent()));
                    }

                }
            }

            /*-----GET OBSTACLES-------*/
            NodeList Obs = doc.getElementsByTagName("obstacles");
            Node p5 = Obs.item(0);
            if (p5.getNodeType() == Node.ELEMENT_NODE) {
                Element Ob_s = (Element) p5;
                String numer = Ob_s.getAttribute("num");

                int num2 = Integer.parseInt(numer);
                n_obst = num2;
                NodeList obst = doc.getElementsByTagName("obstacle");
                for (int u = 0; u < num2 - 1 ; u++) {
                    Node ob = obst.item(u);
                    if (ob.getNodeType() == Node.ELEMENT_NODE) {
                        Element obstacle = (Element) ob;
                        obst_var[u] = new Coordinates(0, 0);
                        obst_var[u].setX(Integer.parseInt(obstacle.getAttribute("xpos")));
                        obst_var[u].setY(Integer.parseInt(obstacle.getAttribute("ypos")));
                    }
                }
            }

            /*-----GET EVENT PARAMETERS-------*/
            /*-----DEATH PARAMETER------------*/
            NodeList DEATH = doc.getElementsByTagName("death");
            Node p6 = DEATH.item(0);
            if (p6.getNodeType() == Node.ELEMENT_NODE) {
                Element d_param = (Element) p6;
                event_var.setMiu(Double.parseDouble(d_param.getAttribute("param")));

            }
            /*-----REPRODUCTION PARAMETER------------*/
            NodeList REP = doc.getElementsByTagName("reproduction");
            Node p7 = REP.item(0);
            if (p6.getNodeType() == Node.ELEMENT_NODE) {
                Element r_param = (Element) p7;
                event_var.setP(Double.parseDouble(r_param.getAttribute("param")));

            }
            /*-----MOVE PARAMETER------------*/
            NodeList MOV = doc.getElementsByTagName("move");
            Node p8 = MOV.item(0);
            if (p6.getNodeType() == Node.ELEMENT_NODE) {
                Element m_param = (Element) p8;
                event_var.setDelta(Double.parseDouble(m_param.getAttribute("param")));

            }


        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
