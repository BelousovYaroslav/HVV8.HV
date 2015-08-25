/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.serial;

import java.util.Locale;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class CheckSumm {
    static Logger logger = Logger.getLogger( CheckSumm.class);
    
    public static boolean CheckCheckSumm( String strValue) {
        
        boolean bResult;
        byte[] btBytes = strValue.getBytes();
        
        short givenCS = Integer.decode( "0x" + strValue.substring( strValue.length() - 2)).shortValue();
        short calcdCS = 0;
        
        for( int i = 0; i < btBytes.length - 2; i++) {
            calcdCS += btBytes[i];
        }
        
        calcdCS = (short) (calcdCS % 0x100);
        
        return ( calcdCS == givenCS);
    }
    
    public static String calcCheckSumm( String strValue) {
        byte[] btBytes = strValue.getBytes();
        
        short calcdCS = 0;
        for( int i = 0; i < btBytes.length; i++) {
            calcdCS += btBytes[i];
        }
        
        calcdCS = (short) (calcdCS % 0x100);
        String strCS = Integer.toHexString(calcdCS);
        strCS = strCS.toUpperCase();
        
        return strCS;
    }
    
    
    public static void main(String args[]) {
        String strCmd = "$056";
        String strCS = calcCheckSumm( strCmd);
        strCS = strCS.toUpperCase();
        System.out.println( strCmd + strCS);
    }
    
    
    
    /*
    public static void main(String args[]) {
        System.out.println( "00:" + calcCheckSumm( "00"));
        System.out.println( "01:" + calcCheckSumm( "01"));
        System.out.println( "02:" + calcCheckSumm( "02"));
        
        System.out.println( "0000:" + CheckCheckSumm( "0060"));
        System.out.println( "0101:" + CheckCheckSumm( "0161"));
        System.out.println( "0203:" + CheckCheckSumm( "0203"));
    }
    */
    
    
    
    /*
    static String formatter( double dblValue) {
        String strValue;
        Locale local = new Locale( "Locale.US");
        strValue = String.format( local, "%+07.3f", dblValue);
        return strValue;
    }
    
    public static void main(String args[]) {
        double dblValue = 0.43;
        System.out.println( "" + dblValue + "   " + formatter( dblValue));
        
        dblValue = 1.43;
        System.out.println( "" + dblValue + "   " + formatter( dblValue));
        
        dblValue = 10.43;
        System.out.println( "" + dblValue + "   " + formatter( dblValue));
        
        dblValue = -0.2;
        System.out.println( "" + dblValue + "   " + formatter( dblValue));
        
        dblValue = -5.6;
        System.out.println( "" + dblValue + "   " + formatter( dblValue));
        
        dblValue = -10.68;
        System.out.println( "" + dblValue + "   " + formatter( dblValue));
        
    }
    */
}
