/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import javax.swing.ImageIcon;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class AMSResources {
    private ImageIcon m_icoBigRedLed;
    public ImageIcon getIconOn() { return m_icoBigRedLed;}
    //public ImageIcon getBigRedLed() { return m_icoBigRedLed;}
    
    private ImageIcon m_icoBigGreenLed;
    public ImageIcon getIconOff() { return m_icoBigGreenLed;}
    //public ImageIcon getBigGreenLed() { return m_icoBigRedLed;}
    
    private ImageIcon m_icoBigBlackLed;
    public ImageIcon getIconUnknown() { return m_icoBigBlackLed;}
    //public ImageIcon getBigBlackLed() { return m_icoBigRedLed;}
    
    private ImageIcon m_icoLittleBrightRedLed;
    public ImageIcon getLittleIconOn() { return m_icoLittleBrightRedLed;}
    public ImageIcon getLittleBrightRedLed() { return m_icoLittleBrightRedLed;}
    
    private ImageIcon m_icoLittleBrightGreenLed;
    public ImageIcon getLittleIconOff() { return m_icoLittleBrightGreenLed;}
    public ImageIcon getLittleBrightGreenLed() { return m_icoLittleBrightGreenLed;}
    
    static Logger logger = Logger.getLogger( AMSResources.class);
    
    public AMSResources( AMSApp theApp) {
        //logger.setLevel( AMSApp.LOG_LEVEL);
        
        java.net.URL imgURL = getClass().getResource("images/redLED.gif");
        if (imgURL != null) {
            m_icoBigRedLed = new ImageIcon(imgURL);
        } else {
            logger.error("Couldn't find file: " + "images/redLED.gif");
        }
    
        imgURL = getClass().getResource("images/greenLED.gif");
        if (imgURL != null) {
            m_icoBigGreenLed = new ImageIcon(imgURL);
        } else {
            logger.error("Couldn't find file: " + "images/greenLED.gif");
        }
        
        imgURL = getClass().getResource("images/blackLED.gif");
        if (imgURL != null) {
            m_icoBigBlackLed = new ImageIcon(imgURL);
        } else {
            logger.error("Couldn't find file: " + "images/blackLED.gif");
        }
        
        imgURL = getClass().getResource("images/red_little_bright.gif");
        if (imgURL != null) {
            m_icoLittleBrightRedLed = new ImageIcon(imgURL);
        } else {
            logger.error("Couldn't find file: " + "images/red_little_bright.gif");
        }
        
        imgURL = getClass().getResource("images/green_little_bright.gif");
        if (imgURL != null) {
            m_icoLittleBrightGreenLed = new ImageIcon(imgURL);
        } else {
            logger.error("Couldn't find file: " + "images/green_little_bright.gif");
        }
    }
}
