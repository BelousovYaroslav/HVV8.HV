/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import ams.devices.Adam4017plus;
import ams.devices.Adam4024;
import ams.devices.Adam4068;
import javax.swing.JLabel;
import org.apache.log4j.Logger;

/**
 * Action class that implements main idea of project:<br>
 * It makes the current that flows through tested laser devices equal to given by user
 * @author yaroslav
 */
public class AMSDeviceManager {
    private Adam4017plus m_adc_current;
    private int m_nAdcCurrentChannel;
    /**
     * Sets ADC-device and it's channel where this manager gets values<br>
     * for the current that flows through tested laser device
     * @param adc
     * ADC-device
     * @param nChannel
     * channel
     */
    public void SetADCCurrent( Adam4017plus adc, int nChannel) { m_adc_current = adc; m_nAdcCurrentChannel = nChannel;}
    public Adam4017plus GetADCCurrent() { return m_adc_current;}
    public int GetAdcCurrentChannel() { return m_nAdcCurrentChannel;}
    
    private Adam4017plus m_adc_voltage;
    private int m_nAdcVoltageChannel;
    /**
     * Sets ADC-device and it's channel where this manager gets values<br>
     * for the voltage that applied to tested laser device
     * @param adc
     * ADC-device
     * @param nChannel
     * channel
     */
    public void SetADCVoltage( Adam4017plus adc, int nChannel) { m_adc_voltage = adc; m_nAdcVoltageChannel = nChannel;}
    public Adam4017plus GetADCVoltage() { return m_adc_voltage;}
    public int GetAdcVoltageChannel() { return m_nAdcVoltageChannel;}
    
    private Adam4024 m_dac;
    private int m_nDacChannel;
    /**
     * Sets DAC-device and it's channel where this manager can adjust<br>
     * the voltage that applied to tested laser device
     * @param dac
     * DAC-device
     * @param nChannel
     * channel
     */
    public void SetDAC( Adam4024 dac, int nChannel) { m_dac = dac; m_nDacChannel = nChannel;}
    public Adam4024 GetDAC() { return m_dac;}
    public int GetDacChannel() { return m_nDacChannel;}
    
    
    private Adam4068 m_relay;
    private int m_nRelayChannel;
    /**
     * Sets RELAY-device and it's channel where this manager can toggle applied voltage
     * @param relay
     * RELAY-device
     * @param nChannel
     * channel
     */
    public void SetRelay( Adam4068 relay, int nChannel) { m_relay = relay; m_nRelayChannel = nChannel;}
    public Adam4068 GetRelay() { return m_relay;}
    public int GetRelayChannel() { return m_nRelayChannel;}
    
    /**
     * Пороговый ток, ток при котором прибор погаснет
     */
    double m_dblEdgeCurrent;
    
    /**
     * Напряжение, при котором прибор погаснет
     */
    double m_dblEdgeVoltage;
            
    private boolean m_bEnabled;
    public boolean GetEnabled() { return m_bEnabled;}
    /**
     * Turns manager on or off
     * @param bEnabled 
     * true - turns on
     * false - turns off
     */
    public void SetEnabled( boolean bEnabled) {
        
        m_bEnabled = bEnabled;
    }
    
    private JLabel m_lblActivationLed;
    /**
     * Returns visual component (label) that indicates this manager state
     * @return 
     */
    public JLabel GetActiveLed() { return m_lblActivationLed;}
    public void SetActiveLed( JLabel lblActivationLed) { m_lblActivationLed = lblActivationLed;}
    
    static Logger logger = Logger.getLogger(AMSDeviceManager.class);
    private final AMSApp theApp;
    
    public AMSDeviceManager( AMSApp app) {
        theApp = app;
        //logger.setLevel( AMSApp.LOG_LEVEL);
        
        //ADC_C
        m_adc_current = null;
        m_nAdcCurrentChannel = -1;
        
        //ADC_V
        m_adc_voltage = null;
        m_nAdcVoltageChannel = -1;
        
        //DAC
        m_dac = null;
        m_nDacChannel = -1;
        
        //RELAY
        m_relay = null;
        m_nRelayChannel = -1;
        
        //STATE
        m_bEnabled = false;
        
        //EDGE CURRENT
        m_dblEdgeCurrent = 3000.;
        
        //EDGE VOLTAGE
        m_dblEdgeVoltage = 3000.;
    }
    
    /**
     * Action method for new incoming voltage value
     */
    public void ActionV() {
        if( m_adc_current == null) { logger.warn( "ActionV(): Не указан объект АЦП тока"); return; }
        if( m_nAdcCurrentChannel == -1) { logger.warn( "ActionV(): Не указан канал АЦП тока"); return; }
        
        if( m_adc_voltage == null) { logger.warn( "ActionV(): Не указан объект АЦП напряжения"); return; }
        if( m_nAdcVoltageChannel == -1) { logger.warn( "ActionV(): Не указан канал АЦП напряжения"); return; }
        
        double dblMeasuredVoltage_V = m_adc_voltage.GetAveragerChannel( m_nAdcVoltageChannel).GetAverage();
        double dblCorrection_mA = dblMeasuredVoltage_V * 2. / 1000;
        //m_adc_current.SetPhysValuePiedestal( dblCorrection_mA, m_nAdcCurrentChannel);
        
        logger.info( "ActionV(): Measured voltage  [V]  = " + dblMeasuredVoltage_V);
        logger.info( "ActionV(): Correction        [mA] = " + dblCorrection_mA);
    }
    
    /**
     * Action method for new incoming current value
     */
    public void ActionC() {
        if( m_adc_current == null) { logger.warn( "ActionC(): Не указан объект АЦП тока"); return; }
        if( m_nAdcCurrentChannel == -1) { logger.warn( "ActionC(): Не указан канал АЦП тока"); return; }
        
        if( m_adc_voltage == null) { logger.warn( "ActionC(): Не указан объект АЦП напряжения"); return; }
        if( m_nAdcVoltageChannel == -1) { logger.warn( "ActionC(): Не указан канал АЦП напряжения"); return; }
        
        if( m_dac == null) { logger.warn( "ActionC(): Не указан объект ЦАП"); return; }
        if( m_nDacChannel == -1) { logger.warn( "ActionC(): Не указан канал ЦАП"); return; }
        
        
        //заданный ток, который нам надо удерживать
        int nOuterCurrent_mcA = theApp.GetOuterCurrent();
        double dblGoalCurrent_mcA = ( double) nOuterCurrent_mcA;
        
        //измеренный ток
        double dblMeasuredCurrent_mcA = m_adc_current.GetAveragerChannel( m_nAdcCurrentChannel).GetAverage();
        //измеренное напряжение
        double dblMeasuredVoltage_V = m_adc_voltage.GetAveragerChannel( m_nAdcVoltageChannel).GetAverage();
        
        if( theApp.GetRegime() == AMSApp.REGIME_I_EDGE) {
            if( dblMeasuredCurrent_mcA < 300) {
                //прибор погас
                //выключаем менеджера
                SetEnabled( false);
                
                //размыкаем реле
                m_relay.QueueSetRelayStateBitCommand( m_nRelayChannel, false);
                
                //ставим на DAC 3.0V
                m_dac.QueueSetChannelOutputValueCommand( m_nDacChannel, AMSConstants.DAC_CHANNEL_REG_OFF_DAC_VOLT);
                
                return;
            }
            else {
                //прибор ещё горит, отслеживаем ток и напряжение
                m_dblEdgeCurrent = dblMeasuredCurrent_mcA;
                m_dblEdgeVoltage = dblMeasuredVoltage_V;
            }
        }
        
        
        /*
        //коррекция из измеренного напряжения (потери на измерительном контуре)
        double dblCorrection_mA = dblMeasuredVoltage_V / 10000;
        
        //измеренный корректированный ток
        double dblCurrentCorrected_mA = dblMeasuredCurrent_mA - dblCorrection_mA;
        */
        //последнее выставленное напряжение на ЦАП
        double dblCurrentOutVoltage_V = m_dac.GetLastSetValue( m_nDacChannel);
        
        //разница между заданным и измеренным откорректированным токами
        double dblDifference_mcA = dblGoalCurrent_mcA - dblMeasuredCurrent_mcA;//dblCurrentCorrected_mA;
        
        //вычисление воздействия
        double dblAffection_V = CountAffectionByDifference_V( dblMeasuredCurrent_mcA, dblDifference_mcA);
        
        
        //покрутим статистикой
        int nStat;
        if( Math.abs( dblDifference_mcA) > 0.10)         nStat = 1;
        else if( Math.abs( dblDifference_mcA) > 0.05)    nStat = 2;
        else if( Math.abs( dblDifference_mcA) > 0.01)    nStat = 3;
        else if( Math.abs( dblDifference_mcA) > 0.005)   nStat = 4;
        else    nStat = 5;
        
        m_adc_current.GetAveragerChannel( m_nAdcCurrentChannel).SetStatistic( nStat);
        m_adc_voltage.GetAveragerChannel( m_nAdcCurrentChannel).SetStatistic( nStat);
            
        
        //применение воздействия
        double dblToDoOutVoltage_V = dblCurrentOutVoltage_V;
        dblToDoOutVoltage_V += dblAffection_V;
        
        //ограничения результата
        if( dblToDoOutVoltage_V > AMSConstants.DAC_MAX_OUTPUT_VOLTAGE)
            dblToDoOutVoltage_V = AMSConstants.DAC_MAX_OUTPUT_VOLTAGE;
        
        if( dblToDoOutVoltage_V < AMSConstants.DAC_MIN_OUTPUT_VOLTAGE)
            dblToDoOutVoltage_V = AMSConstants.DAC_MIN_OUTPUT_VOLTAGE;
        
        //тестовый вывод
        logger.info( "ActionC(): Measured current  [mcA] = " + dblMeasuredCurrent_mcA);
        //logger.info( "ActionC(): Measured voltage  [V]  = " + dblMeasuredVoltage_V);
        //logger.info( "ActionC(): Correction        [mA] = " + dblCorrection_mA);
        //logger.info( "ActionC(): Corrected current [mA] = " + dblCurrentCorrected_mA);
        logger.info( "ActionC(): Goal     current  [mcA] = " + dblGoalCurrent_mcA);
        logger.info( "ActionC(): Difference        [mcA] = " + dblDifference_mcA);
        logger.info( "ActionC(): Current out voltage [V] = " + dblCurrentOutVoltage_V);
        logger.info( "ActionC(): Applied affection   [V] = " + dblAffection_V);
        logger.info( "ActionC(): Todo out voltage    [V] = " + dblToDoOutVoltage_V);
        
        if( theApp.GetMainSwitcher() == true) {
            if( m_bEnabled == true) {
                m_dac.QueueSetChannelOutputValueCommand( m_nDacChannel, dblToDoOutVoltage_V);
            }
            else {
                logger.info( "Управление заблокировано кнопкой включения контроля канала!");
            }
        }
        else {
            logger.info( "Управление заблокировано рубильником!");
        }
        
    }
    
    /**
     * Function for calculation the affection that will be implied (if possible) to<br>
     * corresponding DAC and it's channel
     * @param dblMeasuredCurrent_mcA
     * Measured current in mcA
     * @param dblDifference_mcA
     * difference between measured and user defined currents in mcA
     * @return 
     * affection value (volts)
     */
    double CountAffectionByDifference_V( double dblMeasuredCurrent_mcA, double dblDifference_mcA) {
        double dblRetVal = 0.;
        logger.info( "CountAffectionByDifference_V(): in with " + dblMeasuredCurrent_mcA + ", " + dblDifference_mcA);
        
        /*
        if( dblMeasuredCurrent_mA < 0.3) {
            dblRetVal = Math.signum( dblDifference_mA) * Math.min( Math.abs( dblDifference_mA), 0.2);
            logger.trace( "Прибор не зажжен, загрубляем воздействие: " + dblRetVal);
        }
        else {
            dblRetVal = dblDifference_mA;
            logger.trace( "Прибор зажжен, воздействие равно нехватке: " + dblRetVal);
        }
        */
        
        //dblRetVal = Math.signum( dblDifference_mA) * Math.min( Math.abs( dblDifference_mA), 0.2);
        dblRetVal = dblDifference_mcA;
        
        
        /*if( Math.abs( dblDifference_mcA) > 1500) dblRetVal = Math.signum( dblDifference_mcA) * 1.5;
        else*/
        if( Math.abs( dblDifference_mcA) > 1000) dblRetVal = Math.signum( dblDifference_mcA) * 1.0;
        else if( Math.abs( dblDifference_mcA) > 500) dblRetVal = Math.signum( dblDifference_mcA) * 0.5;
        else if( Math.abs( dblDifference_mcA) > 100) dblRetVal = Math.signum( dblDifference_mcA) * 0.1;
        else if( Math.abs( dblDifference_mcA) > 50) dblRetVal = Math.signum( dblDifference_mcA) * 0.03;
        else if( Math.abs( dblDifference_mcA) > 10) dblRetVal = Math.signum( dblDifference_mcA) * 0.005;
        else if( Math.abs( dblDifference_mcA) > 5) dblRetVal = Math.signum( dblDifference_mcA) * 0.001;
        else dblRetVal = 0;
        
        
        return dblRetVal;
    }
}
