package org.raydelto.rncempresa.util;

import org.ksoap2.serialization.SoapObject;
import org.raydelto.rncempresa.MainActivity;

import android.content.Context;
import android.net.ConnectivityManager;

public class Util {
	public static MainActivity mainActivity;
	public static SoapObject result = null;
	public static boolean searching = false; 
	
	public static boolean checkInternetConnection() {
        ConnectivityManager conMgr = (ConnectivityManager) mainActivity.getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null &&  conMgr.getActiveNetworkInfo().isAvailable()     && conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        }
        return false;
    }
	
	private Util(){
		
	}

}
