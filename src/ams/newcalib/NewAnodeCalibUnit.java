/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.newcalib;

/**
 *
 * @author control
 */
public class NewAnodeCalibUnit {
    private double m_dblCurrent;
    private double m_dblVoltageTesterMeasuredAK;
    private double m_dblVoltageTesterMeasuredR;
    private double m_dblVoltageAdc;
    
    private double m_dblCalcedR;
    private double m_dblCalcedA;
    private double m_dblCalcedB;
    
    public double GetI()    { return m_dblCurrent; }
    public double GetU_ak() { return m_dblVoltageTesterMeasuredAK; }
    public double GetU_r()  { return m_dblVoltageTesterMeasuredR; }
    public double GetU_adc() { return m_dblVoltageAdc; }
    
    public double GetR()    { return m_dblCalcedR; }
    public double GetA()    { return m_dblCalcedA; }
    public double GetB()    { return m_dblCalcedB; }
    
    public void SetI( double dblNewVal)    { m_dblCurrent = dblNewVal; }
    public void SetU_ak( double dblNewVal) { m_dblVoltageTesterMeasuredAK = dblNewVal; }
    public void SetU_r( double dblNewVal)  { m_dblVoltageTesterMeasuredR = dblNewVal; }
    public void SetU_adc( double dblNewVal) { m_dblVoltageAdc = dblNewVal; }
    
    public void SetR( double dblNewVal)    { m_dblCalcedR = dblNewVal; }
    public void SetA( double dblNewVal)    { m_dblCalcedA = dblNewVal; }
    public void SetB( double dblNewVal)    { m_dblCalcedB = dblNewVal; }
}
