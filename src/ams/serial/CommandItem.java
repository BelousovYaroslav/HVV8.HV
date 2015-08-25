/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.serial;

import ams.devices.AbstractDevice;

/**
 * Class for linking transmitted commands with device-class that will process response or timeout
 * 
 * @author yaroslav
 */
public class CommandItem {
    private String strCmdToSend;
    private AbstractDevice objClient;
    
    public String GetCommand() { return strCmdToSend;}
    public AbstractDevice GetClient() { return objClient;}
    
    /**
     * Constructor
     * @param strCmdToSend
     * ASCII-Command to send
     * @param objClient 
     * device-class instance that will process responses and timeouts
     */
    public CommandItem( String strCmdToSend, AbstractDevice objClient) {
        this.strCmdToSend = strCmdToSend;
        this.objClient = objClient;
    }

    void ProcessTimeOut() {
        objClient.ProcessTimeOut( strCmdToSend);
    }

    void ProcessResponse(String strResponse) {
        objClient.ProcessResponse( strCmdToSend, strResponse);
    }
}
