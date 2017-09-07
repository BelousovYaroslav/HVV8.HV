/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.devices;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComponent;

/**
 *
 * @author yaroslav
 */
public abstract class AbstractDevice {
    
    /*private String strSerialResponse;
    public void SetReponse( String strResponse) {
        strSerialResponse = strResponse;
    }*/
    
    private int m_nDeviceDescriptor;
    public int GetDeviceDescriptor() { return m_nDeviceDescriptor; }
    public void SetDeviceDescriptor( int nDeviceDescriptor) { m_nDeviceDescriptor = nDeviceDescriptor; }
    
    private String m_strDescription;
    public String GetDescription() { return m_strDescription; }
    public void SetDescription( String strDescription) { m_strDescription = strDescription; }
    
    private String m_strAddress;
    public String GetAddress() { return m_strAddress; }
    public void SetAddress( String strAddress) { m_strAddress = strAddress; }
    
    private boolean m_bCheckSummEnabled;
    public boolean GetCSEnabled() { return m_bCheckSummEnabled;}
    public void SetCSEnabled( boolean bEnable) { m_bCheckSummEnabled = bEnable;}
    
    public HashMap m_vctVisualComponents = new HashMap();
    abstract void AddVisualComponent( int nDescription, JComponent comp);
    
    public abstract void ProcessResponse( String strCmd, String strResponse);
    public abstract void ProcessTimeOut( String strCmd);
    public abstract String GetRequestDataCommand();
    
    public abstract ArrayList initialize( String strAddress, boolean bEnableCS);
}
