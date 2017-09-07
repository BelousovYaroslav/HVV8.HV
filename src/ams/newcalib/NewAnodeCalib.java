/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.newcalib;


import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import org.apache.log4j.Logger;


/**
 *
 * @author control
 */
public class NewAnodeCalib {
    static Logger logger = Logger.getLogger( NewAnodeCalib.class);
    
    public boolean m_bUseNewCalibDev1;
    TreeMap m_device1;
    
    public boolean m_bUseNewCalibDev2;
    TreeMap m_device2;
    
    public boolean m_bUseNewCalibDev3;
    TreeMap m_device3;
    
    public boolean m_bUseNewCalibDev4;
    TreeMap m_device4;
    
    public boolean m_bUseNewCalibDev5;
    TreeMap m_device5;
    
    public boolean m_bUseNewCalibDev6;
    TreeMap m_device6;
    
    public boolean m_bUseNewCalibDev7;
    TreeMap m_device7;
    
    public boolean m_bUseNewCalibDev8;
    TreeMap m_device8;
    
    
    
    
    public boolean GetUsage( int nDev) {
        boolean bResult = false;
        switch( nDev) {
            case ams.AMSConstants.T_DEVICE1: bResult = m_bUseNewCalibDev1; break;
            case ams.AMSConstants.T_DEVICE2: bResult = m_bUseNewCalibDev2; break;
            case ams.AMSConstants.T_DEVICE3: bResult = m_bUseNewCalibDev3; break;
            case ams.AMSConstants.T_DEVICE4: bResult = m_bUseNewCalibDev4; break;
            case ams.AMSConstants.T_DEVICE5: bResult = m_bUseNewCalibDev5; break;
            case ams.AMSConstants.T_DEVICE6: bResult = m_bUseNewCalibDev6; break;
            case ams.AMSConstants.T_DEVICE7: bResult = m_bUseNewCalibDev7; break;
            case ams.AMSConstants.T_DEVICE8: bResult = m_bUseNewCalibDev8; break;
        }
        return bResult;
    }
            
    public NewAnodeCalib() {
        
        //DEVICE1 *************************************************************
        m_bUseNewCalibDev1 = true;
        m_device1 = new TreeMap();
        
        //800 mcA
        NewAnodeCalibUnit unit = new NewAnodeCalibUnit();
        unit.SetI( 804.);
        unit.SetU_ak( 555.);
        unit.SetU_r( 875.);
        unit.SetU_adc( 0.564);
        unit.SetR( 398.01);
        unit.SetA( 1167.713);
        unit.SetB( 216.076);
        m_device1.put( 804., unit);
        
        //950 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 949);
        unit.SetU_ak( 542.);
        unit.SetU_r( 918.);
        unit.SetU_adc( 0.6);
        unit.SetR( 399.21);
        unit.SetA( 1167.713);
        unit.SetB( 216.076);
        m_device1.put( 949., unit);
        
        //1050 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1049.);
        unit.SetU_ak( 535.);
        unit.SetU_r( 947.);
        unit.SetU_adc( 0.626);
        unit.SetR( 395.76);
        unit.SetA( 1167.713);
        unit.SetB( 216.076);
        m_device1.put( 1049., unit);
        
        //1150 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1149.);
        unit.SetU_ak( 529.);
        unit.SetU_r( 978.);
        unit.SetU_adc( 0.651);
        unit.SetR( 393.77);
        unit.SetA( 1167.713);
        unit.SetB( 216.076);
        m_device1.put( 1149., unit);

        //1250 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1248.);
        unit.SetU_ak( 523.);
        unit.SetU_r( 1008.);
        unit.SetU_adc( 0.677);
        unit.SetR( 391.62);
        unit.SetA( 1167.713);
        unit.SetB( 216.076);
        m_device1.put( 1248., unit);
        
        //1400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1400.);
        unit.SetU_ak( 516.);
        unit.SetU_r( 1054.);
        unit.SetU_adc( 0.717);
        unit.SetR( 384.29);
        unit.SetA( 1167.713);
        unit.SetB( 216.076);
        m_device1.put( 1400., unit);
                
        //2200 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2204.);
        unit.SetU_ak( 492.);
        unit.SetU_r( 1307.);
        unit.SetU_adc( 0.934);
        unit.SetR( 369.78);
        unit.SetA( 1167.713);
        unit.SetB( 216.076);
        m_device1.put( 2204., unit);
        
        //2400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2404.);
        unit.SetU_ak( 488.);
        unit.SetU_r( 1368.);
        unit.SetU_adc( 0.987);
        unit.SetR( 367.78);
        unit.SetA( 1167.713);
        unit.SetB( 216.076);
        m_device1.put( 2404., unit);
        
        //2600 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2603.);
        unit.SetU_ak( 485.);
        unit.SetU_r( 1433.);
        unit.SetU_adc( 1.042);
        unit.SetR( 365.92);
        unit.SetA( 1167.713);
        unit.SetB( 216.076);
        m_device1.put( 2603., unit);
        
        //DEVICE2 *************************************************************
        m_bUseNewCalibDev2 = true;
        m_device2 = new TreeMap();
        
        //800 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 803.);
        unit.SetU_ak( 555.);
        unit.SetU_r( 870.);
        unit.SetU_adc( 0.514);
        unit.SetR( 392.28);
        unit.SetA( 1358.025);
        unit.SetB( 171.587);
        m_device2.put( 803., unit);
        
        //950 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 948);
        unit.SetU_ak( 544.);
        unit.SetU_r( 912.);
        unit.SetU_adc( 0.544);
        unit.SetR( 390.69);
        unit.SetA( 1358.025);
        unit.SetB( 171.587);
        m_device2.put( 948., unit);
        
        //1050 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1049.);
        unit.SetU_ak( 537.);
        unit.SetU_r( 941.);
        unit.SetU_adc( 0.566);
        unit.SetR( 387.63);
        unit.SetA( 1358.025);
        unit.SetB( 171.587);
        m_device2.put( 1049., unit);
        
        //1150 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1149.);
        unit.SetU_ak( 530.);
        unit.SetU_r( 970.);
        unit.SetU_adc( 0.587);
        unit.SetR( 385.44);
        unit.SetA( 1358.025);
        unit.SetB( 171.587);
        m_device2.put( 1149., unit);

        //1250 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1247.);
        unit.SetU_ak( 525.);
        unit.SetU_r( 999.);
        unit.SetU_adc( 0.609);
        unit.SetR( 382.61);
        unit.SetA( 1358.025);
        unit.SetB( 171.587);
        m_device2.put( 1247., unit);
        
        //1400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1400.);
        unit.SetU_ak( 517.);
        unit.SetU_r( 1044.);
        unit.SetU_adc( 0.642);
        unit.SetR( 376.43);
        unit.SetA( 1358.025);
        unit.SetB( 171.587);
        m_device2.put( 1400., unit);
                
        //2200 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2204.);
        unit.SetU_ak( 492.);
        unit.SetU_r( 1293.);
        unit.SetU_adc( 0.826);
        unit.SetR( 363.43);
        unit.SetA( 1358.025);
        unit.SetB( 171.587);
        m_device2.put( 2204., unit);
        
        //2400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2404.);
        unit.SetU_ak( 488.);
        unit.SetU_r( 1356.);
        unit.SetU_adc( 0.872);
        unit.SetR( 361.25);
        unit.SetA( 1358.025);
        unit.SetB( 171.587);
        m_device2.put( 2404., unit);

        //2600 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2603.);
        unit.SetU_ak( 484.);
        unit.SetU_r( 1420.);
        unit.SetU_adc( 0.919);
        unit.SetR( 359.78);
        unit.SetA( 1358.025);
        unit.SetB( 171.587);
        m_device2.put( 2603., unit);
        
                
        //DEVICE3 *************************************************************
        m_bUseNewCalibDev3 = true;
        m_device3 = new TreeMap();
        
        //800 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 804.);
        unit.SetU_ak( 555.);
        unit.SetU_r( 869.);
        unit.SetU_adc( 0.625);
        unit.SetR( 390.55);
        unit.SetA( 1111.878);
        unit.SetB( 174.076);
        m_device3.put( 804., unit);

        //950 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 953);
        unit.SetU_ak( 543.);
        unit.SetU_r( 913.);
        unit.SetU_adc( 0.664);
        unit.SetR( 388.25);
        unit.SetA( 1111.878);
        unit.SetB( 174.076);
        m_device3.put( 948., unit);
        
        //1050 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1048.);
        unit.SetU_ak( 536.);
        unit.SetU_r( 942.);
        unit.SetU_adc( 0.690);
        unit.SetR( 387.40);
        unit.SetA( 1111.878);
        unit.SetB( 174.076);
        m_device3.put( 1048., unit);
                
        //1150 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1149.);
        unit.SetU_ak( 529.);
        unit.SetU_r( 973.);
        unit.SetU_adc( 0.717);
        unit.SetR( 387.42);
        unit.SetA( 1111.878);
        unit.SetB( 174.076);
        m_device3.put( 1149., unit);

        //1250 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1252.);
        unit.SetU_ak( 523.);
        unit.SetU_r( 1005.);
        unit.SetU_adc( 0.747);
        unit.SetR( 385.98);
        unit.SetA( 1111.878);
        unit.SetB( 174.076);
        m_device3.put( 1252., unit);
        
        //1400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1400.);
        unit.SetU_ak( 515.);
        unit.SetU_r( 1052.);
        unit.SetU_adc( 0.789);
        unit.SetR( 383.57);
        unit.SetA( 1111.878);
        unit.SetB( 174.076);
        m_device3.put( 1400., unit);
                
        //2200 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2205.);
        unit.SetU_ak( 491.);
        unit.SetU_r( 1313.);
        unit.SetU_adc( 1.0243);
        unit.SetR( 372.79);
        unit.SetA( 1111.878);
        unit.SetB( 174.076);
        m_device3.put( 2205., unit);

        //2400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2405.);
        unit.SetU_ak( 487.);
        unit.SetU_r( 1378.);
        unit.SetU_adc( 1.084);
        unit.SetR( 365.01);
        unit.SetA( 1111.878);
        unit.SetB( 174.076);
        m_device3.put( 2405., unit);

        //2600 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2603.);
        unit.SetU_ak( 487.);
        unit.SetU_r( 1378.);
        unit.SetU_adc( 1.142);
        unit.SetR( 363.72);
        unit.SetA( 1111.878);
        unit.SetB( 174.076);
        m_device3.put( 2603., unit);  
        
        
        //DEVICE4 *************************************************************
        m_bUseNewCalibDev4 = true;
        m_device4 = new TreeMap();
        
        //800 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 804.);
        unit.SetU_ak( 555.);
        unit.SetU_r( 880.);
        unit.SetU_adc( 0.504);
        unit.SetR( 404.23);
        unit.SetA( 1409.807);
        unit.SetB( 169.054);
        m_device4.put( 804., unit);

        //950 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 954);
        unit.SetU_ak( 542.);
        unit.SetU_r( 925.);
        unit.SetU_adc( 0.536);
        unit.SetR( 405.47);
        unit.SetA( 1409.807);
        unit.SetB( 169.054);
        m_device4.put( 954., unit);
        
        //1050 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1048.);
        unit.SetU_ak( 536.);
        unit.SetU_r( 955.);
        unit.SetU_adc( 0.557);
        unit.SetR( 403.81);
        unit.SetA( 1409.807);
        unit.SetB( 169.054);
        m_device4.put( 1048., unit);

        //1150 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1149.);
        unit.SetU_ak( 529.);
        unit.SetU_r( 987.);
        unit.SetU_adc( 0.58);
        unit.SetR( 402.61);
        unit.SetA( 1409.807);
        unit.SetB( 169.054);
        m_device4.put( 1149., unit);

        //1250 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1249.);
        unit.SetU_ak( 524.);
        unit.SetU_r( 1018.);
        unit.SetU_adc( 0.602);
        unit.SetR( 399.52);
        unit.SetA( 1409.807);
        unit.SetB( 169.054);
        m_device4.put( 1249., unit);
        
        //1400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1402.);
        unit.SetU_ak( 515.);
        unit.SetU_r( 1066.);
        unit.SetU_adc( 0.636);
        unit.SetR( 393.01);
        unit.SetA( 1409.807);
        unit.SetB( 169.054);
        m_device4.put( 1402., unit);
                
        //2200 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2205.);
        unit.SetU_ak( 490.);
        unit.SetU_r( 1326.);
        unit.SetU_adc( 0.821);
        unit.SetR( 379.14);
        unit.SetA( 1409.807);
        unit.SetB( 169.054);
        m_device4.put( 2205., unit);

        //2400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2403.);
        unit.SetU_ak( 486.);
        unit.SetU_r( 1390.);
        unit.SetU_adc( 0.867);
        unit.SetR( 373.55);
        unit.SetA( 1409.807);
        unit.SetB( 169.054);
        m_device4.put( 2403., unit);

        //2600 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2604.);
        unit.SetU_ak( 483.);
        unit.SetU_r( 1455.);
        unit.SetU_adc( 0.912);
        unit.SetR( 370.62);
        unit.SetA( 1409.807);
        unit.SetB( 169.054);
        m_device4.put( 2603., unit);
        
        //DEVICE5 *************************************************************
        m_bUseNewCalibDev5 = true;
        m_device5 = new TreeMap();
        //600mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 605.);
        unit.SetU_ak( 576.);
        unit.SetU_r( 815.);
        unit.SetU_adc( 0.475);
        unit.SetR( 395.04);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 605., unit);
        
        //900mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 899.);
        unit.SetU_ak( 546.);
        unit.SetU_r( 898.);
        unit.SetU_adc( 0.535);
        unit.SetR( 393.55);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 899., unit);
        
        //1050mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1047.);
        unit.SetU_ak( 536.);
        unit.SetU_r( 942.);
        unit.SetU_adc( 0.568);
        unit.SetR( 389.77);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 1047., unit);
        
        //1150mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1146.);
        unit.SetU_ak( 529.);
        unit.SetU_r( 972.);
        unit.SetU_adc( 0.589);
        unit.SetR( 388.56);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 1146., unit);
        
        //1250mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1248.);
        unit.SetU_ak( 524.);
        unit.SetU_r( 1004.);
        unit.SetU_adc( 0.611);
        unit.SetR( 386.62);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 1248., unit);
        
        //1400mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1404.);
        unit.SetU_ak( 516.);
        unit.SetU_r( 1052.);
        unit.SetU_adc( 0.646);
        unit.SetR( 381.77);
        //unit.SetA( 1347.463);
        //unit.SetB( 181.612);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 1404., unit);
        
        //1600mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1602.);
        unit.SetU_ak( 507.);
        unit.SetU_r( 1113.);
        unit.SetU_adc( 0.691);
        unit.SetR( 378.28);
        //unit.SetA( 1392.941);
        //unit.SetB( 150.176);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 1602., unit);
        
        //1800mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1804.);
        unit.SetU_ak( 501.);
        unit.SetU_r( 1177.);
        unit.SetU_adc( 0.737);
        unit.SetR( 374.72);
        //unit.SetA( 1351.304);
        //unit.SetB( 180.870);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 1804., unit);
        
        //2000mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2004.);
        unit.SetU_ak( 495.);
        unit.SetU_r( 1240.);
        unit.SetU_adc( 0.784);
        unit.SetR( 371.76);
        //unit.SetA( 1392.941);
        //unit.SetB( 148.235);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 2004., unit);
        
        //2200mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2204.);
        unit.SetU_ak( 490.);
        unit.SetU_r( 1304.);
        unit.SetU_adc( 0.830);
        unit.SetR( 369.33);
        //unit.SetA( 1374.286);
        //unit.SetB( 163.714);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 2204., unit);
        
        //2400mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2404.);
        unit.SetU_ak( 486.);
        unit.SetU_r( 1369.);
        unit.SetU_adc( 0.877);
        unit.SetR( 361.479);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 2404., unit);
        
        //2600mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2605.);
        unit.SetU_ak( 483.);
        unit.SetU_r( 1434.);
        unit.SetU_adc( 0.923);
        unit.SetR( 359.249);
        unit.SetA( 1369.906);
        unit.SetB( 166.824);
        m_device5.put( 2605., unit);

        //DEVICE6 *************************************************************
        m_bUseNewCalibDev6 = true;
        m_device6 = new TreeMap();
        
        //600mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 602.);
        unit.SetU_ak( 572.);
        unit.SetU_r( 818.);
        unit.SetU_adc( 0.498);
        unit.SetR( 408.64);
        //unit.SetA( 1358.135);
        //unit.SetB( 141.398);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);

        m_device6.put( 602., unit);
        
        //900mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 899.);
        unit.SetU_ak( 544.);
        unit.SetU_r( 903.);
        unit.SetU_adc( 0.561);
        unit.SetR( 402.33);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);
        m_device6.put( 899., unit);
        
        //1050mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1048.);
        unit.SetU_ak( 533.);
        unit.SetU_r( 948.);
        unit.SetU_adc( 0.594);
        unit.SetR( 398.99);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);
        m_device6.put( 1048., unit);
        
        //1150mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1148.);
        unit.SetU_ak( 527.);
        unit.SetU_r( 979.);
        unit.SetU_adc( 0.618);
        unit.SetR( 396.73);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);
        m_device6.put( 1148., unit);
        
        //1250mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1248.);
        unit.SetU_ak( 521.);
        unit.SetU_r( 1010.);
        unit.SetU_adc( 0.641);
        unit.SetR( 394.83);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);
        m_device6.put( 1248., unit);
        
        //1400mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1403.);
        unit.SetU_ak( 513.);
        unit.SetU_r( 1059.);
        unit.SetU_adc( 0.678);
        unit.SetR( 389.17);
        //unit.SetA( 1333.095);
        //unit.SetB( 155.246);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);
        m_device6.put( 1403., unit);
        
        //1600mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1604.);
        unit.SetU_ak( 505.);
        unit.SetU_r( 1124.);
        unit.SetU_adc( 0.727);
        unit.SetR( 385.91);
        //unit.SetA( 1314.369);
        //unit.SetB( 168.854);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);
        m_device6.put( 1604., unit);
        
        //1800mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1803.);
        unit.SetU_ak( 499.);
        unit.SetU_r( 1190.);
        unit.SetU_adc( 0.777);
        unit.SetR( 383.25);
        //unit.SetA( 1334.284);
        //unit.SetB( 153.382);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);
        m_device6.put( 1803., unit);
        
        //2000mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2004.);
        unit.SetU_ak( 494.);
        unit.SetU_r( 1257.);
        unit.SetU_adc( 0.827);
        unit.SetR( 380.74);
        //unit.SetA( 1334.284);
        //unit.SetB( 153.382);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);
        m_device6.put( 2004., unit);
        
        //2200mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2203.);
        unit.SetU_ak( 489.);
        unit.SetU_r( 1324.);
        unit.SetU_adc( 0.877);
        unit.SetR( 379.03);
        //unit.SetA( 1334.853);
        //unit.SetB( 152.883);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);
        m_device6.put( 2203., unit);
        
        //2400mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2404.);
        unit.SetU_ak( 485.);
        unit.SetU_r( 1392.);
        unit.SetU_adc( 0.928);
        unit.SetR( 373.03);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);
        m_device6.put( 2404., unit);
        
        //2600mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2602.);
        unit.SetU_ak( 481.);
        unit.SetU_r( 1458.);
        unit.SetU_adc( 0.978);
        unit.SetR( 371.468);
        unit.SetA( 1314.369);
        unit.SetB( 165.028);
        m_device6.put( 2602., unit);
        
        //DEVICE7 *************************************************************
        m_bUseNewCalibDev7 = true;
        m_device7 = new TreeMap();
        
        //800 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 800.);
        unit.SetU_ak( 559.);
        unit.SetU_r( 874.);
        unit.SetU_adc( 0.560);
        unit.SetR( 393.75);
        unit.SetA( 1145.63);
        unit.SetB( 232.61);
        m_device7.put( 800., unit);
        
        //950 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 950.);
        unit.SetU_ak( 546.);
        unit.SetU_r( 918.);
        unit.SetU_adc( 0.604);
        unit.SetR( 395.58);
        unit.SetA( 1145.63);
        unit.SetB( 232.61);
        m_device7.put( 950., unit);
        
        //1050 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1050.);
        unit.SetU_ak( 539.);
        unit.SetU_r( 949.);
        unit.SetU_adc( 0.624);
        unit.SetR( 394.48);
        unit.SetA( 1145.63);
        unit.SetB( 232.61);
        m_device7.put( 1050., unit);
        
        //1150 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1150.);
        unit.SetU_ak( 532.);
        unit.SetU_r( 980.);
        unit.SetU_adc( 0.650);
        unit.SetR( 393.57);
        unit.SetA( 1145.63);
        unit.SetB( 232.61);
        m_device7.put( 1150., unit);
        
        //1250 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1250.);
        unit.SetU_ak( 526.);
        unit.SetU_r( 1011.);
        unit.SetU_adc( 0.678);
        unit.SetR( 392.0);
        unit.SetA( 1145.63);
        unit.SetB( 232.61);
        m_device7.put( 1250., unit);
        
        //1400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1400.);
        unit.SetU_ak( 518.);
        unit.SetU_r( 1060.);
        unit.SetU_adc( 0.719);
        unit.SetR( 387.14);
        unit.SetA( 1145.63);
        unit.SetB( 232.61);
        m_device7.put( 1400., unit);
        
        //2200 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2200.);
        unit.SetU_ak( 494.);
        unit.SetU_r( 1325.);
        unit.SetU_adc( 0.951);
        unit.SetR( 377.73);
        unit.SetA( 1145.63);
        unit.SetB( 232.61);
        m_device7.put( 2200., unit);
        
        //2400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2400.);
        unit.SetU_ak( 490.);
        unit.SetU_r( 1391.);
        unit.SetU_adc( 1.01);
        unit.SetR( 376.657);
        unit.SetA( 1145.63);
        unit.SetB( 232.61);
        m_device7.put( 2400., unit);
        
        //2600 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2600.);
        unit.SetU_ak( 486.);
        unit.SetU_r( 1460.);
        unit.SetU_adc( 1.07);
        unit.SetR( 375.857);
        unit.SetA( 1145.63);
        unit.SetB( 232.61);
        m_device7.put( 2600., unit);
        
        
        //DEVICE8 *************************************************************
        m_bUseNewCalibDev8 = true;
        m_device8 = new TreeMap();
        
        //800 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 800.);
        unit.SetU_ak( 560.);
        unit.SetU_r( 881.);
        unit.SetU_adc( 0.530);
        unit.SetR( 401.25);
        unit.SetA( 1325.70);
        unit.SetB( 178.75);
        m_device8.put( 800., unit);
        
        //950 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 950.);
        unit.SetU_ak( 547.);
        unit.SetU_r( 926.);
        unit.SetU_adc( 0.559);
        unit.SetR( 402.23);
        unit.SetA( 1325.70);
        unit.SetB( 178.75);
        m_device8.put( 950., unit);
        
        //1050 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1050.);
        unit.SetU_ak( 540.);
        unit.SetU_r( 957.);
        unit.SetU_adc( 0.587);
        unit.SetR( 402.42);
        unit.SetA( 1325.70);
        unit.SetB( 178.75);
        m_device8.put( 1050., unit);
        
        //1150 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1150.);
        unit.SetU_ak( 533.);
        unit.SetU_r( 989.);
        unit.SetU_adc( 0.611);
        unit.SetR( 398.52);
        unit.SetA( 1325.70);
        unit.SetB( 178.75);
        m_device8.put( 1150., unit);
        
        //1250 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1250.);
        unit.SetU_ak( 527.);
        unit.SetU_r( 1022.);
        unit.SetU_adc( 0.636);
        unit.SetR( 398.);
        unit.SetA( 1325.70);
        unit.SetB( 178.75);
        m_device8.put( 1250., unit);
        
        //1400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 1400.);
        unit.SetU_ak( 519.);
        unit.SetU_r( 1070.);
        unit.SetU_adc( 0.673);
        unit.SetR( 393.57);
        unit.SetA( 1325.70);
        unit.SetB( 178.75);
        m_device8.put( 1400., unit);
        
        //2200 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2200.);
        unit.SetU_ak( 494.);
        unit.SetU_r( 1337.);
        unit.SetU_adc( 0.876);
        unit.SetR( 383.18);
        unit.SetA( 1325.70);
        unit.SetB( 178.75);
        m_device8.put( 2200., unit);
        
        //2400 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2400.);
        unit.SetU_ak( 489.);
        unit.SetU_r( 1401.);
        unit.SetU_adc( 0.923);
        unit.SetR( 377.839);
        unit.SetA( 1325.70);
        unit.SetB( 178.75);
        m_device8.put( 2400., unit);
        
        //2600 mcA
        unit = new NewAnodeCalibUnit();
        unit.SetI( 2600.);
        unit.SetU_ak( 486.);
        unit.SetU_r( 1470.);
        unit.SetU_adc( 0.974);
        unit.SetR( 376.299);
        unit.SetA( 1325.70);
        unit.SetB( 178.75);
        m_device8.put( 2600., unit);
    }
    
    public double GetResult( int nDevice, double dbl_I, double dbl_Uadc) {
        double dblResult = 0.;
        
        TreeMap dev = null;
        switch( nDevice) {
            case ams.AMSConstants.T_DEVICE1: dev = m_device1; break;
            case ams.AMSConstants.T_DEVICE2: dev = m_device2; break;
            case ams.AMSConstants.T_DEVICE3: dev = m_device3; break;
            case ams.AMSConstants.T_DEVICE4: dev = m_device4; break;
            case ams.AMSConstants.T_DEVICE5: dev = m_device5; break;
            case ams.AMSConstants.T_DEVICE6: dev = m_device6; break;
            case ams.AMSConstants.T_DEVICE7: dev = m_device7; break;
            case ams.AMSConstants.T_DEVICE8: dev = m_device8; break;
        }
        
        if( dev != null) {
            Set setI = dev.keySet();
            Iterator it = setI.iterator();
            double runnedI = ( double) it.next();
            
            logger.trace( "fir.runnedI="+ runnedI);
            logger.trace( "fir.dbl_I="+ dbl_I);
            
            NewAnodeCalibUnit calibPoint1 = ( NewAnodeCalibUnit) dev.get( runnedI);
            NewAnodeCalibUnit calibPoint2 = ( NewAnodeCalibUnit) dev.get( runnedI);
            
            if( dbl_I < runnedI) {
                
            }
            else {
                while( it.hasNext()) {
                    calibPoint1 = calibPoint2;
                    
                    runnedI = ( double) it.next();
                    calibPoint2 = ( NewAnodeCalibUnit) dev.get( runnedI);
                    
                    logger.trace( "cyc.runnedI="+ runnedI);
                    logger.trace( "cyc.dbl_I="+ dbl_I);
            
                    if( runnedI > dbl_I)
                        break;
                }                
            }
            
            if( runnedI < dbl_I)
                calibPoint1 = calibPoint2;
            
            logger.trace( "Current I: " + dbl_I);
            logger.trace( "CalibP1_I: " + calibPoint1.GetI());
            logger.trace( "CalibP2_I: " + calibPoint2.GetI());
            
            double appliedR, appliedA, appliedB;
            if( calibPoint1 == calibPoint2) {
                appliedR = calibPoint1.GetR();
                appliedA = calibPoint1.GetA();
                appliedB = calibPoint1.GetB();
            }
            else {
                double i1 = calibPoint1.GetI();
                double r1 = calibPoint1.GetR();
                double i2 = calibPoint2.GetI();
                double r2 = calibPoint2.GetR();
                
                appliedR = ( r2 - r1) / (i2 - i1) * dbl_I + ( i2 * r1 - i1 * r2) / ( i2 -i1);
                appliedA = calibPoint1.GetA();
                appliedB = calibPoint2.GetB();
            }
            
            logger.trace( "appliedA=" + appliedA + " appliedB=" + appliedB + " appliedR=" + appliedR);
            dblResult = appliedA * dbl_Uadc + appliedB;
            logger.trace( "dblResult=" + dblResult);
            dblResult -= appliedR * dbl_I / 1000.;
            logger.trace( "dblResult=" + dblResult);
        }
        
        return dblResult;
    }
    
    
}
