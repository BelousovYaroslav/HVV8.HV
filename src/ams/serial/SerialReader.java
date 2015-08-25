/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.serial;

import ams.AMSApp;
import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;

/**
 * Thread-class for COM-port listener
 */
public class SerialReader implements Runnable 
{
    static Logger logger = Logger.getLogger( SerialReader.class);
    
    InputStream in;
    TwoWaySerialComm pParent;
    
    private boolean m_bContinue;

    public void StopThread() { m_bContinue = false;}
    
    public SerialReader( InputStream in, TwoWaySerialComm parent)
    {
        this.in = in;
        pParent = parent;
        
        //logger.setLevel( Level.OFF);
        //logger.setLevel( AMSApp.LOG_LEVEL);
    }
        
    @Override
    public void run ()
    {
        m_bContinue = true;
        byte[] buffer = new byte[ CircleBuffer.BUFFER_LEN];
        int len = -1;
        
        logger.debug("In");
        
        try {
            
            logger.debug("before while");
            
            while( m_bContinue) {
                
                if( this.in.available() > 0) {
                    len = this.in.read(buffer);
                
                    logger.trace("after this.in.read. len=" + len);
                
                    String strResponse1 = new String( buffer, 0, len - 1);
                    logger.debug( "COM-INTERACTION Response1: " + strResponse1);
                
                    pParent.crclBuffer.AddBytes( buffer, len);
                
                    if( pParent.currentCommandInAction != null) {
                        len = pParent.crclBuffer.isAnswerReady();
                        if( len > 0) {
                            String strResponse2 = new String( pParent.crclBuffer.getAnswer( len));
                            logger.trace( "Response2: " + strResponse2);
                            
                            boolean bCorrectResponse = true;
                        
                            //check if strResponse is valid
                            //1. \n на конце
                            if( strResponse2.charAt( strResponse2.length() - 1) == '\r') {
                                strResponse2 = strResponse2.substring( 0, strResponse2.length() - 1);
                            }
                            else {
                                logger.warn( "Пришедший ответ без (CR) на конце");
                                bCorrectResponse = false;
                            }
                            
                            /*
                            if( !strResponse1.equals( strResponse2)) {
                                logger.error( "ОПА!");
                                pParent.crclBuffer.PrintState();
                            }
                            */
                            
                            /*
                            //2. $,#,%,@ на начале
                            String strMarker = strResponse.substring( 0, 1);
                            if( !( "!$#%@".contains( strMarker))) {
                                logger.warn( "Ответ с некорректным заголовком!");
                                bCorrectResponse = false;
                            }
                            */
                            
                            /*
                            //3. адресат запроса совпадает с адресатом ответа
                            String strAddress = strResponse.substring( 1, 3);
                            if( pParent.currentCommandInAction.GetClient().GetAddress() != strAddress) {
                                logger.warn( "Адресат запроса не совпадает с адресатом ответа");
                                bCorrectResponse = false;
                            }
                            */
                            
                            if( bCorrectResponse) {
                                
                                
                                //pParent.GetTimeoutThread().CancelTimeout();
                                pParent.GetTimeoutThread().interrupt();
                                
                                CommandItem item = pParent.currentCommandInAction;
                                pParent.currentCommandInAction = null;
                                
                                item.ProcessResponse( strResponse2);
                                
                            }
                        }
                    
                    }
                    else {
                        logger.warn( "Входящие данные при отсутствии запрашивающей команды!");
                    }
                }
                else
                    //logger.debug( "No RX data");
                
                Thread.sleep( 50);
            }
            
            //something new
            //something new2
            logger.debug("after while");
            
        }
        catch ( IOException ex) {
            logger.error( "IOException caught!", ex);
            AMSApp.MessageBoxError( "Ошибка ввода-вывода потока чтения!\nПроверьте COM-подсоединение.", "Ошибка");
        }
        catch ( InterruptedException ex) {
            logger.error( "InterruptedException caught!", ex);
        }
        
        logger.debug("Out");
    }
}