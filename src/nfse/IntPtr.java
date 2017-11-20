/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nfse;

import com.sun.jna.ptr.ByReference;

/**
 *
 * @author André Ramos Neves
 */
public class IntPtr extends ByReference{
    public void setValue(String value) {  
        getPointer().setString(0L, value);  
    }  
    public String getValue() {  
        return getPointer().getString(0L);  
    }  
    public IntPtr(int value) {  
        //super(value.getBytes().length + 1);  
        super(value);
        //uper
        //setValue(value.);  
    }
}
