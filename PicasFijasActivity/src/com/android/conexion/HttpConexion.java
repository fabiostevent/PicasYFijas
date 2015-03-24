/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.android.conexion;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import com.android.conexion.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
/**
 *
 * @author USER
 */
public class HttpConexion {
	private InputStream is;
	private JSON json=new JSON();
	private Map<String,Integer> datos;
	private String direccion;

	public String getURL() {
		return direccion;
	}
	
	@SuppressWarnings("finally")
	public boolean conexion(String url, ArrayList<NameValuePair> parametros) {
        boolean estado=false;        
        try {
            HttpClient httpcliente= new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(parametros));
            HttpResponse httprespuesta = httpcliente.execute(httppost);
            HttpEntity httpentidad = httprespuesta.getEntity();
            conexion("http://"+url+"/PruebaPicasFijas/respuesta.json");
            estado=true;
            }
        catch (Exception e) {
            e.printStackTrace();
             estado = false;
        }
        finally {
            if(estado) {
                return true;
            }
            else {
                return false;
            }
        }
    }
	
	@SuppressWarnings("finally")
	public boolean conexion(String url) {
        boolean estado=false;        
        try {
        	HttpClient cliente=new DefaultHttpClient();
        	HttpPost post=new HttpPost(url);
            HttpResponse respuesta=cliente.execute(post);
            HttpEntity entidad=respuesta.getEntity();
            is = entidad.getContent();
            datos=json.getRespuesta(is);
            }
        catch (Exception e) {
            e.printStackTrace();
             estado = false;
        }
        finally {
            if(estado) {
                return true;
            }
            else {
                return false;
            }
        }
    }
	
	public Map<String,Integer> obtenerRespuesta() {
		return datos;
	}
}
