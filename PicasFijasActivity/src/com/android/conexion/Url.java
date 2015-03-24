package com.android.conexion;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

public class Url {
	private String url;
	private Bundle bnd;
	
	public String declaraUrl(Bundle bundle) {
		bnd=bundle;
		evaluaUrl();
		return (estableceUrl());
	}
		
	private void evaluaUrl() {
        if(bnd==null) {
        	url="192.168.0.6";
        	Log.e("url", url);
        }
        else {
        	url=(bnd.getString("url").toString());
        	Log.e("url", url);
        }
    }
	
	private String estableceUrl() {
		url="http://"+ url +"/PruebaPicasFijas/pic2.php";
		return url;
	}
	
	@SuppressLint("NewApi") private static boolean esNumero(String string) {
	    if (string == null || string.isEmpty()) {
	        return false;
	    }
	    int i = 0;
	    if (string.charAt(0) == '-') {
	        if (string.length() > 1) {
	            i++;
	        } else {
	            return false;
	        }
	    }
	    for (; i < string.length(); i++) {
	        if (!Character.isDigit(string.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
	}
}