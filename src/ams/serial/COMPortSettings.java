/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.serial;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

/**
 *
 * @author yaroslav
 */
public class COMPortSettings {
    private String m_strCOMPort;
    private int m_nCOMBaudRate;
    private int m_nCOMDataBits;
    private int m_nCOMParity;
    private int m_nCOMStopBits;
    
    public String   GetPort()       { return m_strCOMPort; }
    public int      GetBaudRate()   { return m_nCOMBaudRate;}
    public int      GetDataBits()   { return m_nCOMDataBits;}
    public int      GetParity()     { return m_nCOMParity;}
    public int      GetStopBits()   { return m_nCOMStopBits;}
    
    public void SetPort( String strNewPort)     { m_strCOMPort = strNewPort; }
    public void SetBaudRate( int nNewBaudRate)  { m_nCOMBaudRate = nNewBaudRate;}
    public void SetDataBits( int nNewDataBits)  { m_nCOMDataBits = nNewDataBits;}
    public void SetParity( int nNewParity)      { m_nCOMParity = nNewParity;}
    public void SetStopBits( int nNewStopBits)  { m_nCOMStopBits = nNewStopBits;}
    
    public COMPortSettings() {
        //Default Settings:
        m_strCOMPort = "/dev/ttyUSB0";
        m_nCOMBaudRate = 115200;
        m_nCOMDataBits = SerialPort.DATABITS_8;
        m_nCOMParity = SerialPort.PARITY_NONE;
        m_nCOMStopBits = SerialPort.STOPBITS_1;
    }
    
    public static String getPortTypeName ( int portType )
    {
        switch ( portType )
        {
            case CommPortIdentifier.PORT_I2C:       return "I2C";
            case CommPortIdentifier.PORT_PARALLEL:  return "Parallel";
            case CommPortIdentifier.PORT_RAW:       return "Raw";
            case CommPortIdentifier.PORT_RS485:     return "RS485";
            case CommPortIdentifier.PORT_SERIAL:    return "Serial";
            default:                                return "unknown type";
        }
    }
}
