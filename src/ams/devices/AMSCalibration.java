/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.devices;

import ams.AMSApp;
import ams.AMSConstants;
import ams.AMSSettingsTDev;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author yaroslav
 */
public class AMSCalibration {
    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger( AMSCalibration.class);
    AMSApp theApp;
    
    HashMap m_mapCalibrationUnits;
    
    public AMSCalibrationUnit GetCalibUnit( int nADCEnum) {
        return ( ( AMSCalibrationUnit) m_mapCalibrationUnits.get( nADCEnum));
    }
    
    public AMSCalibration( AMSApp app) {
        theApp = app;
        
        m_mapCalibrationUnits = new HashMap( 4);
        
        AMSCalibrationUnit m_adcCalib = new AMSCalibrationUnit();
        m_mapCalibrationUnits.put( AMSConstants.ADC1, m_adcCalib);
            
        m_adcCalib = new AMSCalibrationUnit();
        m_mapCalibrationUnits.put( AMSConstants.ADC2, m_adcCalib);
            
        m_adcCalib = new AMSCalibrationUnit();
        m_mapCalibrationUnits.put( AMSConstants.ADC3, m_adcCalib);
            
        m_adcCalib = new AMSCalibrationUnit();
        m_mapCalibrationUnits.put( AMSConstants.ADC4, m_adcCalib);
    }
    
    public boolean LoadCalibration() {
        boolean bResOk = true;
        try {
            SAXReader reader = new SAXReader();
            
            String strCalibrationFilePathName = System.getenv( "AMS_ROOT") + "/etc/calibration.xml";
            URL url = ( new java.io.File( strCalibrationFilePathName)).toURI().toURL();
            
            Document document = reader.read( url);
            
            Element root = document.getRootElement();

            // iterate through child elements of root
            for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                String name = element.getName();
                String value = element.getText();
                
                //logger.debug( "LoadCalibration(): Pairs: [" + name + " : " + value + "]");
                
                if( "ADC1".equals( name)) {
                    for ( Iterator andevsparams = element.elementIterator(); andevsparams.hasNext(); ) {
                    
                        Element elementAnDev = (Element) andevsparams.next();
                        name = elementAnDev.getName();
                        value = elementAnDev.getText();
                        
                        logger.debug( "LoadCalibration(): Pairs: ADC1: [" + name + " : " + value + "]");
                        
                        String strKind = name.substring( 7, 8);
                        String strChannel = name.substring( 5, 6);
                        int nChannel = -1;
                        boolean bOk = true;
                        try {
                            nChannel = Integer.parseInt( strChannel, 10);
                        } catch( NumberFormatException e) {
                            logger.error( "LoadCalibration(): Strange channel number '" + strChannel + "' has been extracted from name " + name);
                            bOk = false;
                            bResOk = false;
                        }
                        
                        Double dblValue = null;
                        try {
                            value = value.replace( ',', '.');
                            dblValue = new Double( value);
                        }
                        catch( NumberFormatException ex) {
                            logger.error( "LoadCalibration(): NumberFormatException caught while parsing value '" + value + "'");
                            bOk = false;
                            bResOk = false;
                        }
                        
                        if( bOk) {
                            AMSCalibrationUnit unit = ( AMSCalibrationUnit) m_mapCalibrationUnits.get( AMSConstants.ADC1);
                            if( unit != null) {
                                if( strKind.equals( "A")) unit.SetSlope( nChannel, dblValue);
                                else if( strKind.equals( "B")) unit.SetShift( nChannel, dblValue);
                                else {
                                    logger.warn( "Unknown Kind  of param'" + strKind + "' from name '" + name + "' in calibration.xml! Pay attention!");
                                    bResOk = false;
                                }
                            }
                            else {
                                logger.error( "Unit для ADC1 из коллекции m_mapCalibrationUnits равен null!");
                                bResOk = false;
                            }
                        }
                    }
                }
                
                if( "ADC2".equals( name)) {
                    for ( Iterator andevsparams = element.elementIterator(); andevsparams.hasNext(); ) {
                    
                        Element elementAnDev = (Element) andevsparams.next();
                        name = elementAnDev.getName();
                        value = elementAnDev.getText();
                        
                        logger.debug( "LoadCalibration(): Pairs: ADC2: [" + name + " : " + value + "]");
                        
                        String strKind = name.substring( 7, 8);
                        String strChannel = name.substring( 5, 6);
                        int nChannel = -1;
                        boolean bOk = true;
                        try {
                            nChannel = Integer.parseInt( strChannel, 10);
                        } catch( NumberFormatException e) {
                            logger.error( "LoadCalibration(): Strange channel number '" + strChannel + "' has been extracted from name " + name);
                            bOk = false;
                            bResOk = false;
                        }
                        
                        Double dblValue = null;
                        try {
                            value = value.replace( ',', '.');
                            dblValue = new Double( value);
                        }
                        catch( NumberFormatException ex) {
                            logger.error( "LoadCalibration(): NumberFormatException caught while parsing value '" + value + "'");
                            bOk = false;
                            bResOk = false;
                        }
                        
                        if( bOk) {
                            AMSCalibrationUnit unit = ( AMSCalibrationUnit) m_mapCalibrationUnits.get( AMSConstants.ADC2);
                            if( unit != null) {
                                
                                if( strKind.equals( "A")) unit.SetSlope( nChannel, dblValue);
                                else if( strKind.equals( "B")) unit.SetShift( nChannel, dblValue);
                                else {
                                    logger.warn( "Unknown Kind  of param'" + strKind + "' from name '" + name + "' in calibration.xml! Pay attention!");
                                    bResOk = false;
                                }
                            }
                            else {
                                logger.error( "Unit для ADC2 из коллекции m_mapCalibrationUnits равен null!");
                                bResOk = false;
                            }
                        }
                    }
                }
                
                if( "ADC3".equals( name)) {
                    for ( Iterator andevsparams = element.elementIterator(); andevsparams.hasNext(); ) {
                    
                        Element elementAnDev = (Element) andevsparams.next();
                        name = elementAnDev.getName();
                        value = elementAnDev.getText();
                        
                        logger.debug( "LoadCalibration(): Pairs: ADC3. [" + name + " : " + value + "]");
                        
                        String strKind = name.substring( 7, 8);
                        String strChannel = name.substring( 5, 6);
                        int nChannel = -1;
                        boolean bOk = true;
                        try {
                            nChannel = Integer.parseInt( strChannel, 10);
                        } catch( NumberFormatException e) {
                            logger.error( "LoadCalibration(): Strange channel number '" + strChannel + "' has been extracted from name " + name);
                            bOk = false;
                            bResOk = false;
                        }
                        
                        Double dblValue = null;
                        try {
                            value = value.replace( ',', '.');
                            dblValue = new Double( value);
                        }
                        catch( NumberFormatException ex) {
                            logger.error( "LoadCalibration(): NumberFormatException caught while parsing value '" + value + "'");
                            bOk = false;
                            bResOk = false;
                        }
                        
                        if( bOk) {
                            AMSCalibrationUnit unit = ( AMSCalibrationUnit) m_mapCalibrationUnits.get( AMSConstants.ADC3);
                            if( unit != null) {
                                
                                if( strKind.equals( "A")) unit.SetSlope( nChannel, dblValue);
                                else if( strKind.equals( "B")) unit.SetShift( nChannel, dblValue);
                                else {
                                    logger.warn( "Unknown Kind  of param'" + strKind + "' from name '" + name + "' in calibration.xml! Pay attention!");
                                    bResOk = false;
                                }
                            }
                            else {
                                logger.error( "Unit для ADC3 из коллекции m_mapCalibrationUnits равен null!");
                                bResOk = false;
                            }
                        }
                    }
                }
                
                if( "ADC4".equals( name)) {
                    for ( Iterator andevsparams = element.elementIterator(); andevsparams.hasNext(); ) {
                    
                        Element elementAnDev = (Element) andevsparams.next();
                        name = elementAnDev.getName();
                        value = elementAnDev.getText();
                        
                        logger.debug( "LoadCalibration(): Pairs: ADC4: [" + name + " : " + value + "]");
                        
                        String strKind = name.substring( 7, 8);
                        String strChannel = name.substring( 5, 6);
                        int nChannel = -1;
                        boolean bOk = true;
                        try {
                            nChannel = Integer.parseInt( strChannel, 10);
                        } catch( NumberFormatException e) {
                            logger.error( "LoadCalibration(): Strange channel number '" + strChannel + "' has been extracted from name " + name);
                            bOk = false;
                            bResOk = false;
                        }
                        
                        Double dblValue = null;
                        try {
                            value = value.replace( ',', '.');
                            dblValue = new Double( value);
                        }
                        catch( NumberFormatException ex) {
                            logger.error( "LoadCalibration(): NumberFormatException caught while parsing value '" + value + "'");
                            bOk = false;
                            bResOk = false;
                        }
                        
                        if( bOk) {
                            AMSCalibrationUnit unit = ( AMSCalibrationUnit) m_mapCalibrationUnits.get( AMSConstants.ADC4);
                            if( unit != null) {
                                
                                if( strKind.equals( "A")) unit.SetSlope( nChannel, dblValue);
                                else if( strKind.equals( "B")) unit.SetShift( nChannel, dblValue);
                                else {
                                    logger.warn( "Unknown Kind  of param'" + strKind + "' from name '" + name + "' in calibration.xml! Pay attention!");
                                    bResOk = false;
                                }
                            }
                            else {
                                logger.error( "Unit для ADC4 из коллекции m_mapCalibrationUnits равен null!");
                                bResOk = false;
                            }
                        }
                    }
                }
                
            }
            
        } catch( MalformedURLException ex) {
            logger.error( "MalformedURLException caught while loading settings!", ex);
            bResOk = false;
        } catch( DocumentException ex) {
            logger.error( "DocumentException caught while loading settings!", ex);
            bResOk = false;
        }
        
        return bResOk;
    }
    
    public void SaveCalibration() {
        try {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement( "Calibration" );
            
            AMSCalibrationUnit unit = ( AMSCalibrationUnit) m_mapCalibrationUnits.get( AMSConstants.ADC1);
            Element anodes = root.addElement( "ADC1" );
            anodes.addElement( "ADC1_0_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL1)));
            anodes.addElement( "ADC1_0_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL1)));
            anodes.addElement( "ADC1_1_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL2)));
            anodes.addElement( "ADC1_1_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL2)));
            anodes.addElement( "ADC1_2_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL3)));
            anodes.addElement( "ADC1_2_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL3)));
            anodes.addElement( "ADC1_3_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL4)));
            anodes.addElement( "ADC1_3_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL4)));
            anodes.addElement( "ADC1_4_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL5)));
            anodes.addElement( "ADC1_4_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL5)));
            anodes.addElement( "ADC1_5_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL6)));
            anodes.addElement( "ADC1_5_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL6)));
            anodes.addElement( "ADC1_6_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL7)));
            anodes.addElement( "ADC1_6_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL7)));
            anodes.addElement( "ADC1_7_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL8)));
            anodes.addElement( "ADC1_7_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL8)));
            
            unit = ( AMSCalibrationUnit) m_mapCalibrationUnits.get( AMSConstants.ADC2);
            anodes = root.addElement( "ADC2" );
            anodes.addElement( "ADC2_0_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL1)));
            anodes.addElement( "ADC2_0_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL1)));
            anodes.addElement( "ADC2_1_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL2)));
            anodes.addElement( "ADC2_1_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL2)));
            anodes.addElement( "ADC2_2_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL3)));
            anodes.addElement( "ADC2_2_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL3)));
            anodes.addElement( "ADC2_3_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL4)));
            anodes.addElement( "ADC2_3_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL4)));
            anodes.addElement( "ADC2_4_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL5)));
            anodes.addElement( "ADC2_4_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL5)));
            anodes.addElement( "ADC2_5_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL6)));
            anodes.addElement( "ADC2_5_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL6)));
            anodes.addElement( "ADC2_6_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL7)));
            anodes.addElement( "ADC2_6_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL7)));
            anodes.addElement( "ADC2_7_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL8)));
            anodes.addElement( "ADC2_7_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL8)));
            
            unit = ( AMSCalibrationUnit) m_mapCalibrationUnits.get( AMSConstants.ADC3);
            anodes = root.addElement( "ADC3" );
            anodes.addElement( "ADC3_0_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL1)));
            anodes.addElement( "ADC3_0_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL1)));
            anodes.addElement( "ADC3_1_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL2)));
            anodes.addElement( "ADC3_1_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL2)));
            anodes.addElement( "ADC3_2_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL3)));
            anodes.addElement( "ADC3_2_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL3)));
            anodes.addElement( "ADC3_3_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL4)));
            anodes.addElement( "ADC3_3_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL4)));
            anodes.addElement( "ADC3_4_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL5)));
            anodes.addElement( "ADC3_4_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL5)));
            anodes.addElement( "ADC3_5_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL6)));
            anodes.addElement( "ADC3_5_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL6)));
            anodes.addElement( "ADC3_6_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL7)));
            anodes.addElement( "ADC3_6_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL7)));
            anodes.addElement( "ADC3_7_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL8)));
            anodes.addElement( "ADC3_7_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL8)));
            
            unit = ( AMSCalibrationUnit) m_mapCalibrationUnits.get( AMSConstants.ADC4);
            anodes = root.addElement( "ADC4" );
            anodes.addElement( "ADC4_0_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL1)));
            anodes.addElement( "ADC4_0_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL1)));
            anodes.addElement( "ADC4_1_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL2)));
            anodes.addElement( "ADC4_1_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL2)));
            anodes.addElement( "ADC4_2_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL3)));
            anodes.addElement( "ADC4_2_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL3)));
            anodes.addElement( "ADC4_3_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL4)));
            anodes.addElement( "ADC4_3_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL4)));
            anodes.addElement( "ADC4_4_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL5)));
            anodes.addElement( "ADC4_4_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL5)));
            anodes.addElement( "ADC4_5_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL6)));
            anodes.addElement( "ADC4_5_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL6)));
            anodes.addElement( "ADC4_6_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL7)));
            anodes.addElement( "ADC4_6_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL7)));
            anodes.addElement( "ADC4_7_A").addText(String.format("%.3f", unit.GetSlope( AMSConstants.CHANNEL8)));
            anodes.addElement( "ADC4_7_B").addText(String.format("%.3f", unit.GetShift( AMSConstants.CHANNEL8)));
            
            OutputFormat format = OutputFormat.createPrettyPrint();
            String strCalibrationFilePathName = System.getenv( "AMS_ROOT") + "/etc/calibration.xml";
            XMLWriter writer = new XMLWriter( new FileWriter( strCalibrationFilePathName), format);
            
            writer.write( document );
            writer.close();
        } catch (IOException ex) {
            logger.error( "IOException caught while saving calibration!", ex);
        }
    }
    
    public void CallCalibrationDialog() {
        DlgCalibration dlg = new DlgCalibration( theApp.m_pMainWnd, true, theApp);
        dlg.setVisible( true);
    }
    
    public static void main( String args[]) {
        BasicConfigurator.configure();
        logger.setLevel( Level.TRACE);
        AMSCalibration amscalib = new AMSCalibration( null);
        amscalib.SaveCalibration();
        amscalib.LoadCalibration();
    }
}
