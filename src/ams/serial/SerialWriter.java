/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.serial;

import ams.AMSApp;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class SerialWriter implements Runnable 
{
    static Logger logger = Logger.getLogger( SerialWriter.class);
    OutputStream out;
        
    private boolean m_bContinue;
    public void StopThread() { m_bContinue = false;}
                
    TwoWaySerialComm pParent;
    
    
    public SerialWriter ( OutputStream out, TwoWaySerialComm parent)
    {
        this.out = out;
        this.pParent = parent;
        
        //logger.setLevel( Level.OFF);
        //logger.setLevel( AMSApp.LOG_LEVEL);
    }
        
    @Override
    public void run ()
    {
        CommandItem item;
        m_bContinue = true;
        
        try {
                
            while( m_bContinue) {

                //logger.debug( "Alive! pParent.currentCommandInAction = " + pParent.currentCommandInAction);
                
                if( pParent.GetCmdInAction() == null) {                         //нет команды в обработке
                    
                    if( pParent.GetQueue().isEmpty() == false) {                //в очереди что-то есть
                        
                        item = ( CommandItem) pParent.GetQueue().poll();
                    
                        if( item != null) {
                            logger.trace( "Item from queue: '" + item.GetCommand() + "'!");
                            String strCmd = item.GetCommand();
                            logger.trace( "COM-INTERACTION Command '" + strCmd + "' from Queue!");
                            logger.trace( "Queue length: " + pParent.GetQueue().size());

                            strCmd += "\r";

                            //byte [] btsToSend = strCmd.getBytes();
                            //logger.debug( "btsToSend[]=" + btsToSend);

                            this.out.write( strCmd.getBytes());
                            

                            //logger.debug( "Timeout thread isAlive(): " + pParent.GetTimeoutThread().isAlive());
                            //logger.debug( "Timeout thread getState(): " + pParent.GetTimeoutThread().getState());
                            //logger.debug( "Timeout thread GetInProgress(): " + pParent.GetTimeoutThread().GetInProgress());

                            //START TIMEOUT INSTANCE
                            //pParent.CreateNewTimeoutThread();
                            //pParent.GetTimeoutThread().start();
                            pParent.m_lTimeOutId = hvv_timeouts.HVV_TimeoutsManager.getInstance().StartTimeout( 1000);
                            
                            pParent.SetCmdInAction( item);
                        }
                        else {
                            logger.debug( "Item from queue: 'NULL'! Wow!");
                            logger.debug( "Item from queue is 'null'! Queue length: " + pParent.GetQueue().size());
                        }
                    }
                    else {
                        //logger.trace( "2. Очередь команд пустая!");
                    }
                }
                else {
                    /*
                    if( pParent.currentCommandInAction != null) {
                        //logger.trace( "Есть команда в обработке!");
                    }
                    if( cmdQueue.isEmpty()) {
                        //logger.trace( "Очередь команды пустая!");
                    }
                    if( pParent.GetTimeoutThread().GetInProgress() != true) {
                        //logger.trace( "Поток таймаута не закончен!");
                    }
                    */
                    //logger.trace( "1. Команда испущена, а сигнала об окончании её обработки или таймаута не было!");
                }




                /*    
                if( pParent.currentCommandInAction == null) {
                    
                    if( !cmdQueue.empty()) {
                        
                        if( pParent.GetTimeoutThread().GetInProgress() == true) {
                */
                            
                            /*
                            logger.warn( "команды в обработке нет, что-то стоит в очереди, а поток таймаута ещё не закончился. Подождём...");
                            
                            int nPauseCounter = 1;
                            boolean bGoOn = true;
                            do {
                                Thread.sleep(10);
                                
                                logger.debug( "Подождали " + nPauseCounter + "раз.");
                                        
                                if( pParent.GetTimeoutThread().GetInProgress() == true) {
                                    logger.warn( "По прежнему поток таймаута ещё не закончился. Подождём ещё...");
                                    nPauseCounter++;
                                    if( nPauseCounter > 10) {
                                        logger.warn( "Подождали " + nPauseCounter + "раз. По прежнему поток таймаута ещё не закончился. Подождём ещё...");
                                        logger.fatal( "Прождали 10 раз.. ну сколько можно... предположим хрень забъём на поток таймаута.");
                                        bGoOn = false;
                                    }
                                }
                                else {
                                    logger.debug( "И поток таймаута закончился.Идём дальше!");
                                    bGoOn = false;
                                }
                            } while( bGoOn);
                            */
                    /*        
                            logger.warn( "Команды в обработке нет, что-то стоит в очереди, а поток таймаута ещё не закончился. За-join-имся на секундочку...");
                            pParent.GetTimeoutThread().join( 1000);
                            if( pParent.GetTimeoutThread().GetInProgress() == true) {
                                logger.fatal( "Прождали секунду... И по прежнему поток таймаута ещё не закончился... предположим хрень, забъём на поток таймаута.");
                            }
                            else {
                                logger.debug( "И поток таймаута закончился.Идём дальше!");
                            }
                            
                        }
                    
                    
                    }
                    
                }
                */
                
                Thread.sleep( 10);
                
                /*
                int c = 0;
                while ( ( c = System.in.read()) > -1 )
                {
                    this.out.write(c);
                }*/

            }
        }
        catch ( IOException ex) {
            logger.error( "IOException caught!", ex);
            AMSApp.MessageBoxError( "Ошибка ввода-вывода потока записи!\nПроверьте COM-подсоединение.", "Ошибка");
        }
        catch ( InterruptedException ex) {
            logger.error( "InterruptedException caught!", ex);
        }
        
        logger.debug("Out");
    }
}