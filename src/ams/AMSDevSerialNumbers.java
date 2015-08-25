/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Data-Class for tested laser devices numbers 
 * @author yaroslav
 */
public class AMSDevSerialNumbers {
    static Logger logger = Logger.getLogger( AMSDevSerialNumbers.class);
    
    private String m_strDev1SerialNumber;
    public void SetDev1SerialNumber( String strNewSerial) { m_strDev1SerialNumber = strNewSerial;}
    public String GetDev1SerialNumber() { return m_strDev1SerialNumber;}
    
    private String m_strDev2SerialNumber;
    public void SetDev2SerialNumber( String strNewSerial) { m_strDev2SerialNumber = strNewSerial;}
    public String GetDev2SerialNumber() { return m_strDev2SerialNumber;}
    
    private String m_strDev3SerialNumber;
    public void SetDev3SerialNumber( String strNewSerial) { m_strDev3SerialNumber = strNewSerial;}
    public String GetDev3SerialNumber() { return m_strDev3SerialNumber;}
    
    private String m_strDev4SerialNumber;
    public void SetDev4SerialNumber( String strNewSerial) { m_strDev4SerialNumber = strNewSerial;}
    public String GetDev4SerialNumber() { return m_strDev4SerialNumber;}
    
    private String m_strDev5SerialNumber;
    public void SetDev5SerialNumber( String strNewSerial) { m_strDev5SerialNumber = strNewSerial;}
    public String GetDev5SerialNumber() { return m_strDev5SerialNumber;}
    
    private String m_strDev6SerialNumber;
    public void SetDev6SerialNumber( String strNewSerial) { m_strDev6SerialNumber = strNewSerial;}
    public String GetDev6SerialNumber() { return m_strDev6SerialNumber;}
    
    private String m_strDev7SerialNumber;
    public void SetDev7SerialNumber( String strNewSerial) { m_strDev7SerialNumber = strNewSerial;}
    public String GetDev7SerialNumber() { return m_strDev7SerialNumber;}
    
    private String m_strDev8SerialNumber;
    public void SetDev8SerialNumber( String strNewSerial) { m_strDev8SerialNumber = strNewSerial;}
    public String GetDev8SerialNumber() { return m_strDev8SerialNumber;}
    
    public String GetDeviceSerialNumber( int nDevice) {
        String strResult = null;
        switch( nDevice) {
            case 1: strResult = GetDev1SerialNumber(); break;
            case 2: strResult = GetDev2SerialNumber(); break;
            case 3: strResult = GetDev3SerialNumber(); break;
            case 4: strResult = GetDev4SerialNumber(); break;
            case 5: strResult = GetDev5SerialNumber(); break;
            case 6: strResult = GetDev6SerialNumber(); break;
            case 7: strResult = GetDev7SerialNumber(); break;
            case 8: strResult = GetDev8SerialNumber(); break;
        }
        return strResult;
    }
    
    public AMSDevSerialNumbers() {
        m_strDev1SerialNumber = "";
        m_strDev2SerialNumber = "";
        m_strDev3SerialNumber = "";
        m_strDev4SerialNumber = "";
        m_strDev5SerialNumber = "";
        m_strDev6SerialNumber = "";
        m_strDev7SerialNumber = "";
        m_strDev8SerialNumber = "";
        LoadDevNumsFromXML();
    }
    
    /**
     * Saves entered numbers to XML file
     */
    public void SaveDevNumsToXML() {
        try {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement( "Settings" );
            root.addElement( "Device1").addText( m_strDev1SerialNumber);
            root.addElement( "Device2").addText( m_strDev2SerialNumber);
            root.addElement( "Device3").addText( m_strDev3SerialNumber);
            root.addElement( "Device4").addText( m_strDev4SerialNumber);
            root.addElement( "Device5").addText( m_strDev5SerialNumber);
            root.addElement( "Device6").addText( m_strDev6SerialNumber);
            root.addElement( "Device7").addText( m_strDev7SerialNumber);
            root.addElement( "Device8").addText( m_strDev8SerialNumber);
        
            OutputFormat format = OutputFormat.createPrettyPrint();
            String strSerialsXmlFile = System.getenv( "AMS_ROOT") + "/serials.xml";
            XMLWriter writer = new XMLWriter( new FileWriter( strSerialsXmlFile), format);
            writer.write( document );
            writer.close();
            
        } catch (IOException ex) {
            logger.error( "IOException caught while saving serials!", ex);
        }
    }
    
    /**
     * Loads tested laser devices numbers from XML<br>
     * So user don't need to enter them one more time
     */
    private void LoadDevNumsFromXML() {
        try {
            SAXReader reader = new SAXReader();
            
            String strSerialsFileName = System.getenv( "AMS_ROOT") + "/serials.xml";
            URL url = ( new java.io.File( strSerialsFileName)).toURI().toURL();
            
            Document document = reader.read(url);
            
            Element root = document.getRootElement();

            // iterate through child elements of root
            for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                String name = element.getName();
                String value = element.getText();
                
                logger.debug( "Pairs: [" + name + " : " + value + "]");
                
                switch( name) {
                    case "Device1": m_strDev1SerialNumber = value; break;
                    case "Device2": m_strDev2SerialNumber = value; break;
                    case "Device3": m_strDev3SerialNumber = value; break;
                    case "Device4": m_strDev4SerialNumber = value; break;
                    case "Device5": m_strDev5SerialNumber = value; break;
                    case "Device6": m_strDev6SerialNumber = value; break;
                    case "Device7": m_strDev7SerialNumber = value; break;
                    case "Device8": m_strDev8SerialNumber = value; break;
                    default: 
                }
            }
        } catch( MalformedURLException ex) {
            logger.error( "MalformedURLException caught while loading serials!", ex);
        } catch( DocumentException ex) {
            logger.error( "DocumentException caught while loading serials!", ex);
        }
                
    }

    public static void main( String[] args ) {
        BasicConfigurator.configure();
        logger.setLevel( Level.TRACE);
        AMSDevSerialNumbers ex = new AMSDevSerialNumbers();
        ex.SetDev1SerialNumber( "100");
        ex.SetDev2SerialNumber( "20150506.001");
        ex.SetDev3SerialNumber( "10-01");
        ex.SetDev4SerialNumber( "05.06.2015.001");
        ex.SetDev5SerialNumber( "05.06.2015.002.битый");
        ex.SetDev6SerialNumber( "05.06.2015.003.перегретый");
        ex.SetDev7SerialNumber( "05.06.2015.004.брак");
        ex.SetDev8SerialNumber( "");
        
        ex.SaveDevNumsToXML();
    }
}
