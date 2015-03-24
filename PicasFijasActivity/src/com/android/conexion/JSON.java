package com.android.conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import android.util.Log;

public class JSON {
	private InputStream is;
	private BufferedReader br;
	private StringBuilder sb= new StringBuilder();
	private String line = null;
	private String cadena;	
	
	public Map<String,Integer> getRespuesta(InputStream is) throws IOException {
		this.is=is;
		leerJson();
		Map<String,Integer> datos=convierteArray();
		return datos;
	}
	
	private void leerJson() throws IOException {			
		try {
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while((line=br.readLine()) != null) {
				sb.append(line + "\n");
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			is.close();
		}
		
		cadena=sb.toString();
		
	}
	
	private Map<String,Integer> convierteArray() {
		Map<String, Integer> datos=new HashMap();
		String d[]=cadena.split(",");
				
		for(String d1 : d) {
			if((d1.charAt(1) == 'p') || (d1.charAt(2) == 'p')) {
				int a=Integer.parseInt(d1.substring(d1.indexOf(":")+1, (d1.indexOf(":")+2)));
				datos.put("picas", a);
			}
			
			if((d1.charAt(1) == 'f') || (d1.charAt(2) == 'f')) {
				int a=Integer.parseInt(d1.substring(d1.indexOf(":")+1, (d1.indexOf(":")+2)));
				datos.put("fijas", a);
			}
			
			if(d1.charAt(1) == 'i') {
				int a=Integer.parseInt(d1.substring(d1.indexOf(":")+1, (d1.indexOf("}"))));
				datos.put("intentos", a);
				}			
		}
		return datos;
	}
}
