import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Dom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("data1.xml");
			/*-----GET SIMULATION VALUES-------*/
			NodeList SIMULATION = doc.getElementsByTagName("simulation");
			for(int i=0;i< SIMULATION.getLength();i++) {
				Node p = SIMULATION.item(i);
				if(p.getNodeType()==Node.ELEMENT_NODE) {
					Element sim = (Element) p;
					String finalinst = sim.getAttribute("finalinst");
					String initpop = sim.getAttribute("initpop");
					String maxpop = sim.getAttribute("maxpop");
					String comfortsens = sim.getAttribute("comfortsens");
					System.out.println("finallist: " + finalinst + " initpop: " + 
					initpop +" maxpop: "+ maxpop + " comfortsens: "+  comfortsens);	
				}
			}
			/*-----GET GRID VALUES-------*/
			NodeList GRID = doc.getElementsByTagName("grid");
			Node p1 = GRID.item(0);
			if(p1.getNodeType()==Node.ELEMENT_NODE) {
				Element grid = (Element) p1;
				String colsnb = grid.getAttribute("colsnb");
				String rowsnb = grid.getAttribute("rowsnb");
				System.out.println("col: " + colsnb + " row: " + rowsnb);
			}
			/*-----GET INITIAL POINTS-------*/
			NodeList initP = doc.getElementsByTagName("initialpoint");
			Node p2 = initP.item(0);
			if(p2.getNodeType()==Node.ELEMENT_NODE) {
				Element init_P = (Element) p2;
				String x_initial = init_P.getAttribute("xinitial");
				String y_initial = init_P.getAttribute("yinitial");
				System.out.println("xinit: " + x_initial + " yinit: " + y_initial);
			}
			/*-----GET FINAL POINTS-------*/
			NodeList finalP = doc.getElementsByTagName("finalpoint");
			Node p3 = finalP.item(0);
			if(p3.getNodeType()==Node.ELEMENT_NODE) {
				Element finit_P = (Element) p3;
				String x_final = finit_P.getAttribute("xfinal");
				String y_final = finit_P.getAttribute("yfinal");
				System.out.println("xfinit: " + x_final + " yfinit: " + y_final);
			}
			/*-----GET SPECIAL COST ZONES-------*/
			NodeList SpecZ = doc.getElementsByTagName("specialcostzones");
			Node p4 = SpecZ.item(0);
			if(p4.getNodeType()==Node.ELEMENT_NODE) {
				Element spec_Z = (Element) p4;
				String nume = spec_Z.getAttribute("num");
				System.out.println("numero de zonas especiais: " + nume);
				
				NodeList zone = doc.getElementsByTagName("zone");
				
				int num1 = Integer.parseInt(nume);
				String[] xinitial = new String[num1];
				String[] yinitial = new String[num1];
				String[] xfinal = new String[num1];
				String[] yfinal = new String[num1];
				String[] c = new String[num1];
				for(int m=0;m<num1;m++) {
					Node spec = zone.item(m);
					if(spec.getNodeType()==Node.ELEMENT_NODE) {
						Element speci = (Element) spec;
						xinitial[m] = speci.getAttribute("xinitial");
						yinitial[m] = speci.getAttribute("yinitial");
						xfinal[m] = speci.getAttribute("xfinal");
						yfinal[m] = speci.getAttribute("yfinal");
						c[m]=speci.getTextContent();
						System.out.println("xinitial: " + xinitial[m] + " yinitial: "+ yinitial[m]
								+ " XFINAL: "+ xfinal[m] + " yfinal: "+ yfinal[m] + " c: " + c[m]);
					}
				}
			}
			/*-----GET OBSTACLES-------*/
			NodeList Obs = doc.getElementsByTagName("obstacles");
			Node p5 = Obs.item(0);
			if(p5.getNodeType()==Node.ELEMENT_NODE) {
				Element Ob_s = (Element) p5;
				String numer = Ob_s.getAttribute("num");
				System.out.println("numero de obstaculos: " + numer);
				
				int num2 = Integer.parseInt(numer);
				String[] xpos = new String[num2];
				String[] ypos = new String[num2];
				NodeList obst = doc.getElementsByTagName("obstacle");
				for(int u=0;u<num2;u++) {
					Node ob = obst.item(u);
					if(ob.getNodeType()==Node.ELEMENT_NODE) {
						Element obstacle = (Element) ob;
						xpos[u] = obstacle.getAttribute("xpos");
						ypos[u] = obstacle.getAttribute("ypos");
						System.out.println("xpos: " + xpos[u] + " xpos: " + ypos[u]);
					}
				}
			}
			/*-----GET EVENT PARAMETERS-------*/
			/*-----DEATH PARAMETER------------*/
			NodeList DEATH = doc.getElementsByTagName("death");
			Node p6 = DEATH.item(0);
			if(p6.getNodeType()==Node.ELEMENT_NODE) {
				Element d_param = (Element) p6;
				String death = d_param.getAttribute("param");
				System.out.println("DEATH PARAMETER: " + death);
			}
			/*-----REPRODUCTION PARAMETER------------*/
			NodeList REP = doc.getElementsByTagName("reproduction");
			Node p7 = REP.item(0);
			if(p6.getNodeType()==Node.ELEMENT_NODE) {
				Element r_param = (Element) p7;
				String reproduction = r_param.getAttribute("param");
				System.out.println("REPRODUCTION PARAMETER: " + reproduction);
			}
			/*-----MOVE PARAMETER------------*/
			NodeList MOV = doc.getElementsByTagName("move");
			Node p8 = MOV.item(0);
			if(p6.getNodeType()==Node.ELEMENT_NODE) {
				Element m_param = (Element) p8;
				String move = m_param.getAttribute("param");
				System.out.println("MOVE PARAMETER: " + move);
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


/*
 NodeList special = 
 for(int j=0; j<childlist.getLength();j++) {
Node child = childlist.item(j);
if(child.getNodeType()==Node.ELEMENT_NODE) {
	Element chil = (Element) child;
	System.out.println("Something " + finalinst + chil.getTagName());
}
}*/
