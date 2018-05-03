package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


import org.xml.sax.helpers.DefaultHandler;

public class sax_parser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		//Obtain object for SAX parser
		SAXParser saxParser = factory.newSAXParser();
		
		DefaultHandler handler = new DefaultHandler();
		
		boolean bname = false;
		boolean bprice = false;
		boolean bdescription = false;
		boolean bcalories = false;
		
		public void startElement(String uri, String localName, String qName, Attributes attributes)throws SaxException{
			
			if(qName.equalsIgnoreCase("name")) {
				bname = true;
			}
			if(qName.equalsIgnoreCase("price")) {
				bprice = true;
			}
			if(qName.equalsIgnoreCase("description")) {
				bdescription = true;
			}
			if(qName.equalsIgnoreCase("calories")) {
				bcalories = true;
			}
		}
		
		public void endElement(String uri, String localName, String qName)throws SAXException{
			
		};
		//print data stored in between < and > tags
		public void characters(char ch[], int start,intlength)throws SAXException{
			
			if(bname) {
				System.out.println("name: "+newString(ch,start,length));
				bname = false;
			}
			if(bprice) {
				System.out.println("price: "+newString(ch,start,length));
				bprice = false;
			}
			if(bdescription) {
				System.out.println("description: "+newString(ch,start,length));
				bdescription = false;
			}
			if(bcalories) {
				System.out.println("calories: "+newString(ch,start,length));
				bcalories = false;
			}
		}
		
		saxParser.parse("file.xml", handler);
		
	}

}
