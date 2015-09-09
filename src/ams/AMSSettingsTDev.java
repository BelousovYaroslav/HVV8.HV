/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

/**
 * Data-class for device-unit in settings. Describes channels on the measurement devices.
 * @author yaroslav
 */
public class AMSSettingsTDev {
        
    
    /**
     * Индекс устройства АЦП анодного напряжения
     */
    private int m_nAnoAdcVoltageDevice;
    /**
     * Канал АЦП анодного напряжения
     */
    private int m_nAnoAdcVoltageChannel;
    
    /**
     * Индекс устройства АЦП анодного тока
     */
    private int m_nAnoAdcCurrentDevice;
    /**
     * Канал АЦП анодного тока
     */
    private int m_nAnoAdcCurrentChannel;
    
    /**
     * Индекс устройства ЦАП для канала анода данного устройства
     */
    private int m_nAnoDacDevice;
    /**
     * Канал ЦАП анодного тока для канала анода данного устройства
     */
    private int m_nAnoDacChannel;
    
    /**
     * Индекс устройства реле для канала анод данного устройства
     */
    private int m_nAnoRelayDevice;
    /**
     * Канал реле для канала анод данного устройства
     */
    private int m_nAnoRelayChannel;
    
    
    /**
     * Индекс устройства АЦП анодного напряжения
     */
    private int m_nTubAdcVoltageDevice;
    /**
     * Канал АЦП анодного напряжения
     */
    private int m_nTubAdcVoltageChannel;
    
    /**
     * Индекс устройства АЦП анодного тока
     */
    private int m_nTubAdcCurrentDevice;
    /**
     * Канал АЦП анодного тока
     */
    private int m_nTubAdcCurrentChannel;
    
    /**
     * Индекс устройства ЦАП для канала анода данного устройства
     */
    private int m_nTubDacDevice;
    /**
     * Канал ЦАП анодного тока для канала анода данного устройства
     */
    private int m_nTubDacChannel;
    
    /**
     * Индекс устройства реле для канала анод данного устройства
     */
    private int m_nTubRelayDevice;
    /**
     * Канал реле для канала анод данного устройства
     */
    private int m_nTubRelayChannel;
    
        
    /**
     * Номер устройства на столе в диапазоне [1-8]
     */
    private int m_nDeviceNumber;
    
    //GET
    //GET.ANODE
    public int GetAnoAdcVoltDev()                   { return m_nAnoAdcVoltageDevice;}
    public int GetAnoAdcVoltChan()                  { return m_nAnoAdcVoltageChannel;}
    
    public int GetAnoAdcCurrDev()                   { return m_nAnoAdcCurrentDevice;}
    public int GetAnoAdcCurrChan()                  { return m_nAnoAdcCurrentChannel;}
    
    public int GetAnoDacDev()                       { return m_nAnoDacDevice;}
    public int GetAnoDacChan()                      { return m_nAnoDacChannel;}
    
    public int GetAnoRelDev()                       { return m_nAnoRelayDevice;}
    public int GetAnoRelChan()                      { return m_nAnoRelayChannel;}
    
    //GET.TUBULATIONS
    public int GetTubAdcVoltDev()                   { return m_nTubAdcVoltageDevice;}
    public int GetTubAdcVoltChan()                  { return m_nTubAdcVoltageChannel;}
    
    public int GetTubAdcCurrDev()                   { return m_nTubAdcCurrentDevice;}
    public int GetTubAdcCurrChan()                  { return m_nTubAdcCurrentChannel;}
    
    public int GetTubDacDev()                       { return m_nTubDacDevice;}
    public int GetTubDacChan()                      { return m_nTubDacChannel;}
    
    public int GetTubRelDev()                       { return m_nTubRelayDevice;}
    public int GetTubRelChan()                      { return m_nTubRelayChannel;}
    
    
    public int GetDeviceNum()                       { return m_nDeviceNumber;}
    
    
    
    //SET
    //SET.ANODE
    public void SetAnoAdcVoltDev( int nNewVal)      { m_nAnoAdcVoltageDevice = nNewVal;}
    public void SetAnoAdcVoltChan( int nNewVal)     { m_nAnoAdcVoltageChannel = nNewVal;}
    public void SetAnoAdcVolt( int nDevice, int nChannel) {
        m_nAnoAdcVoltageDevice = nDevice; m_nAnoAdcVoltageChannel = nChannel;}
    
    public void SetAnoAdcCurrDev( int nNewVal)      {  m_nAnoAdcCurrentDevice = nNewVal;}
    public void SetAnoAdcCurrChan( int nNewVal)     {  m_nAnoAdcCurrentChannel = nNewVal;}
    public void SetAnoAdcCurr( int nDevice, int nChannel) {
        m_nAnoAdcCurrentDevice = nDevice; m_nAnoAdcCurrentChannel = nChannel;}
    
    public void SetAnoDacDev( int nNewVal)          {  m_nAnoDacDevice = nNewVal;}
    public void SetAnoDacChan( int nNewVal)         {  m_nAnoDacChannel = nNewVal;}
    public void SetAnoDac( int nDevice, int nChannel) {
        m_nAnoDacDevice = nDevice; m_nAnoDacChannel = nChannel;}
    
    public void SetAnoRelDev( int nNewVal)          {  m_nAnoRelayDevice = nNewVal;}
    public void SetAnoRelChan( int nNewVal)         {  m_nAnoRelayChannel = nNewVal;}
    public void SetAnoRel( int nDevice, int nChannel) {
        m_nAnoRelayDevice = nDevice; m_nAnoRelayChannel = nChannel;}
    
    //SET.TUBULATIONS
    public void SetTubAdcVoltDev( int nNewVal)      {  m_nTubAdcVoltageDevice = nNewVal;}
    public void SetTubAdcVoltChan( int nNewVal)     {  m_nTubAdcVoltageChannel = nNewVal;}
    public void SetTubAdcVolt( int nDevice, int nChannel) {
        m_nTubAdcVoltageDevice = nDevice; m_nTubAdcVoltageChannel = nChannel;}
    
    public void SetTubAdcCurrDev( int nNewVal)      {  m_nTubAdcCurrentDevice = nNewVal;}
    public void SetTubAdcCurrChan( int nNewVal)     {  m_nTubAdcCurrentChannel = nNewVal;}
    public void SetTubAdcCurr( int nDevice, int nChannel) {
        m_nTubAdcCurrentDevice = nDevice; m_nTubAdcCurrentChannel = nChannel;}
    
    public void SetTubDacDev( int nNewVal)          {  m_nTubDacDevice = nNewVal;}
    public void SetTubDacChan( int nNewVal)         {  m_nTubDacChannel = nNewVal;}
    public void SetTubDac( int nDevice, int nChannel) {
        m_nTubDacDevice = nDevice; m_nTubDacChannel = nChannel;}
    
    public void SetTubRelDev( int nNewVal)          {  m_nTubRelayDevice = nNewVal;}
    public void SetTubRelChan( int nNewVal)         {  m_nTubRelayChannel = nNewVal;}
    public void SetTubRel( int nDevice, int nChannel) {
        m_nTubRelayDevice = nDevice; m_nTubRelayChannel = nChannel;}
    
    public void SetDeviceNum( int nNewVal)          { m_nDeviceNumber = nNewVal;}
    
    /**
     * Default constructor
     * @param nNum 
     */
    public AMSSettingsTDev( int nNum) {
        m_nDeviceNumber = nNum;
        
        m_nAnoAdcVoltageDevice = AMSConstants.ADC1;
        m_nAnoAdcVoltageChannel = 0;
        m_nAnoAdcCurrentDevice = AMSConstants.ADC1;
        m_nAnoAdcCurrentChannel = 0;
        m_nAnoDacDevice = AMSConstants.DAC1;
        m_nAnoDacChannel = 0;
        m_nAnoRelayDevice = AMSConstants.REL1;
        m_nAnoRelayChannel = 0;
        
        m_nTubAdcVoltageDevice = AMSConstants.ADC1;
        m_nTubAdcVoltageChannel = 0;
        m_nTubAdcCurrentDevice = AMSConstants.ADC1;
        m_nTubAdcCurrentChannel = 0;
        m_nTubDacDevice = AMSConstants.DAC1;
        m_nTubDacChannel = 0;
        m_nTubRelayDevice = AMSConstants.REL1;
        m_nTubRelayChannel = 0;
    }
    
    public boolean IsValid() {
        //Anode.voltage
        if( m_nAnoAdcVoltageDevice != AMSConstants.ADC1 && m_nAnoAdcVoltageDevice != AMSConstants.ADC2 &&
            m_nAnoAdcVoltageDevice != AMSConstants.ADC3 && m_nAnoAdcVoltageDevice != AMSConstants.ADC4)
            return false;

        if( m_nAnoAdcVoltageChannel < 0 || m_nAnoAdcVoltageChannel > 7)
            return false;
        
        //Anode.current
        if( m_nAnoAdcCurrentDevice != AMSConstants.ADC1 && m_nAnoAdcCurrentDevice != AMSConstants.ADC2 &&
            m_nAnoAdcCurrentDevice != AMSConstants.ADC3 && m_nAnoAdcCurrentDevice != AMSConstants.ADC4)
            return false;

        if( m_nAnoAdcCurrentChannel < 0 || m_nAnoAdcCurrentChannel > 7)
            return false;
        
        //Anode.dac
        if( m_nAnoDacDevice != AMSConstants.DAC1 && m_nAnoDacDevice != AMSConstants.DAC2 &&
            m_nAnoDacDevice != AMSConstants.DAC3 && m_nAnoDacDevice != AMSConstants.DAC4)
            return false;

        if( m_nAnoDacChannel < 0 || m_nAnoDacChannel > 3)
            return false;
        
        //Anode.relay
        if( m_nAnoRelayDevice != AMSConstants.REL1 && m_nAnoRelayDevice != AMSConstants.REL2)
            return false;

        if( m_nAnoRelayChannel < 0 || m_nAnoRelayChannel > 7)
            return false;
        
        
        //Tubulation.voltage
        if( m_nTubAdcVoltageDevice != AMSConstants.ADC1 && m_nTubAdcVoltageDevice != AMSConstants.ADC2 &&
            m_nTubAdcVoltageDevice != AMSConstants.ADC3 && m_nTubAdcVoltageDevice != AMSConstants.ADC4)
            return false;

        if( m_nTubAdcVoltageChannel < 0 || m_nTubAdcVoltageChannel > 7)
            return false;
        
        //Tubulation.current
        if( m_nTubAdcCurrentDevice != AMSConstants.ADC1 && m_nTubAdcCurrentDevice != AMSConstants.ADC2 &&
            m_nTubAdcCurrentDevice != AMSConstants.ADC3 && m_nTubAdcCurrentDevice != AMSConstants.ADC4)
            return false;

        if( m_nTubAdcCurrentChannel < 0 || m_nTubAdcCurrentChannel > 7)
            return false;
        
        //Tubulation.dac
        if( m_nTubDacDevice != AMSConstants.DAC1 && m_nTubDacDevice != AMSConstants.DAC2 &&
            m_nTubDacDevice != AMSConstants.DAC3 && m_nTubDacDevice != AMSConstants.DAC4)
            return false;

        if( m_nTubDacChannel < 0 || m_nTubDacChannel > 3)
            return false;
        
        //Tubulation.relay
        if( m_nTubRelayDevice != AMSConstants.REL1 && m_nTubRelayDevice != AMSConstants.REL2)
            return false;

        if( m_nTubRelayChannel < 0 || m_nTubRelayChannel > 7)
            return false;
        
        return true;
    }
}
