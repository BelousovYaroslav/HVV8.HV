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
                
    private final LinkedList cmdQueue;
    TwoWaySerialComm pParent;
    
    
    public SerialWriter ( OutputStream out, LinkedList queue, TwoWaySerialComm parent)
    {
        this.out = out;
        this.cmdQueue = queue;
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
                
                if( ( pParent.currentCommandInAction == null) &&                        //нет команды в обработке
                        ( !cmdQueue.isEmpty()) &&                                       //в очереди что-то есть
                        ( pParent.GetTimeoutThread().GetInProgress() != true)) {        //таймаут поток погашен
                    
                    item = ( CommandItem) cmdQueue.poll();
                    
                    if( item != null) {
                        logger.debug( "Item from queue: '" + item.GetCommand() + "'!");
                        String strCmd = item.GetCommand();
                        logger.debug( "COM-INTERACTION Command '" + strCmd + "' from Queue!");
                        logger.debug( "Queue length: " + cmdQueue.size());
                    
                        strCmd += "\r";
                        
                        //byte [] btsToSend = strCmd.getBytes();
                        //logger.debug( "btsToSend[]=" + btsToSend);
                        
                        this.out.write( strCmd.getBytes());
                        pParent.currentCommandInAction = item;
                    
                        //logger.debug( "Timeout thread isAlive(): " + pParent.GetTimeoutThread().isAlive());
                        //logger.debug( "Timeout thread getState(): " + pParent.GetTimeoutThread().getState());
                        //logger.debug( "Timeout thread GetInProgress(): " + pParent.GetTimeoutThread().GetInProgress());


                        pParent.CreateNewTimeoutThread();
                        pParent.GetTimeoutThread().start();
                    }
                    else {
                        logger.debug( "Item from queue: 'NULL'! Wow!");
                        logger.debug( "Item from queue is 'null'! Queue length: " + cmdQueue.size());
                    }
                }
                else {
                    if( pParent.currentCommandInAction != null) {
                        //logger.trace( "Есть команда в обработке!");
                    }
                    if( cmdQueue.isEmpty()) {
                        //logger.trace( "Очередь команды пустая!");
                    }
                    if( pParent.GetTimeoutThread().GetInProgress() != true) {
                        //logger.trace( "Поток таймаута не закончен!");
                    }
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
                
                Thread.sleep( 20);
                
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