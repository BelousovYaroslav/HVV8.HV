/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.devices;

import ams.AMSConstants;
import java.util.HashMap;

/**
 * Data-class for calibration data for calibration unit - Adam4017plus device instance
 * @author yaroslav
 */
public class AMSCalibrationUnit {
    HashMap m_mapSlopes;
    HashMap m_mapShifts;
    
    public AMSCalibrationUnit() {
        
        m_mapSlopes = new HashMap();
        m_mapSlopes.put( AMSConstants.CHANNEL1, 1000.);
        m_mapSlopes.put( AMSConstants.CHANNEL2, 1000.);
        m_mapSlopes.put( AMSConstants.CHANNEL3, 1000.);
        m_mapSlopes.put( AMSConstants.CHANNEL4, 1000.);
        m_mapSlopes.put( AMSConstants.CHANNEL5, 1000.);
        m_mapSlopes.put( AMSConstants.CHANNEL6, 1000.);
        m_mapSlopes.put( AMSConstants.CHANNEL7, 1000.);
        m_mapSlopes.put( AMSConstants.CHANNEL8, 1000.);
        

        m_mapShifts = new HashMap();
        m_mapShifts.put( AMSConstants.CHANNEL1, 1000.);
        m_mapShifts.put( AMSConstants.CHANNEL2, 1000.);
        m_mapShifts.put( AMSConstants.CHANNEL3, 1000.);
        m_mapShifts.put( AMSConstants.CHANNEL4, 1000.);
        m_mapShifts.put( AMSConstants.CHANNEL5, 1000.);
        m_mapShifts.put( AMSConstants.CHANNEL6, 1000.);
        m_mapShifts.put( AMSConstants.CHANNEL7, 1000.);
        m_mapShifts.put( AMSConstants.CHANNEL8, 1000.);
    }
    
    /**
     * Get A-coefficient (slope) for channel nChannel (zero-based)
     * @param nChannel
     *      channel number (zero-based) [0-7]
     * @throws 
     *      ArrayIndexOutOfBoundsException
     * @return
     *      double-value of A-coefficient
     */
    public double GetSlope( int nChannel) {
        return ( double) m_mapSlopes.get( nChannel);
    }
    
    /**
     * Set A-coefficient (slope) for channel nChannel (zero-based)
     * @param nChannel
     *      channel number (zero-based) [0-7]
     * @throws 
     *      ArrayIndexOutOfBoundsException
     */
    public void SetSlope( int nChannel, double dblNewVal) {
        m_mapSlopes.put( nChannel, dblNewVal);
    }
    
    /**
     * Get B-coefficient (shift) for channel nChannel (zero-based)
     * @param nChannel
     *      channel number (zero-based) [0-7]
     * @throws 
     *      ArrayIndexOutOfBoundsException
     * @return
     *      double-value of B-coefficient
     */
    public double GetShift( int nChannel) {
        return ( double) m_mapShifts.get( nChannel);
    }
    
    /**
     * Set B-coefficient (shift) for channel nChannel (zero-based)
     * @param nChannel
     *      channel number (zero-based) [0-7]
     * @throws 
     *      ArrayIndexOutOfBoundsException
     */
    public void SetShift( int nChannel, double dblNewVal) {
        m_mapShifts.put( nChannel, dblNewVal);
    }
}
