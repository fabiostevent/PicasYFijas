package com.android.picasfijas;

import java.util.ArrayList;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.android.conexion.HttpConexion;
import com.android.conexion.Url;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PicasFijasActivity extends Activity {
	EditText n1, n2, n3, n4; 
    Button conConexion, sinConexion, salir, direccion;
    TextView titulo;
    String RUTA;
    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        titulo=(TextView) findViewById(R.id.txtUrl);
        n1=(EditText)findViewById(R.id.n1);
        n2=(EditText)findViewById(R.id.n2);
        n3=(EditText)findViewById(R.id.n3);
        n4=(EditText)findViewById(R.id.n4);
        conConexion=(Button) findViewById(R.id.conConexion);
        sinConexion=(Button) findViewById(R.id.sinConexion);
        direccion=(Button) findViewById(R.id.urlMain);
        salir=(Button) findViewById(R.id.salir);
        Url url=new Url();
        
        final HttpConexion htco=new HttpConexion();
        
        Typeface fuente= Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf");
		conConexion.setTypeface(fuente);
		sinConexion.setTypeface(fuente);
		salir.setTypeface(fuente);
		titulo.setTypeface(fuente);
		n1.setTypeface(fuente);
		n1.setGravity(Gravity.CENTER_HORIZONTAL);
		n2.setTypeface(fuente);
		n2.setGravity(Gravity.CENTER_HORIZONTAL);
		n3.setTypeface(fuente);
		n3.setGravity(Gravity.CENTER_HORIZONTAL);
		n4.setTypeface(fuente);
		n4.setGravity(Gravity.CENTER_HORIZONTAL);
		direccion.setTypeface(fuente);
		
		Bundle bun=getIntent().getExtras();
		RUTA=url.declaraUrl(bun);
		Log.e("url", RUTA);
				
        conConexion.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
            	switch (esValido(n1, n2, n3, n4)) {
            		case 1:
            			String numero1, numero2, numero3, numero4;
                    	numero1=n1.getText().toString();
                        numero2=n2.getText().toString();
                        numero3=n3.getText().toString();
                        numero4=n4.getText().toString();
                		Map<String, Integer> datos;
                        ArrayList<NameValuePair> parametrosEnviar=new ArrayList<NameValuePair>();
                        parametrosEnviar.add(new BasicNameValuePair("n1", numero1));
                        parametrosEnviar.add(new BasicNameValuePair("n2", numero2));
                        parametrosEnviar.add(new BasicNameValuePair("n3", numero3));
                        parametrosEnviar.add(new BasicNameValuePair("n4", numero4));
                        
                        if(htco.conexion(RUTA, parametrosEnviar)) {
                            Log.e("error", "true");
                            datos = htco.obtenerRespuesta();
                            Intent i=new Intent(getApplicationContext(), RespuestaActivity.class);
                            i.putExtra("picas", datos.get("picas"));
                            i.putExtra("fijas", datos.get("fijas"));
                            i.putExtra("intentos", datos.get("intentos"));
                            startActivity(i);                
                            finish();
                        }
                        else {
                            error("Conexion error", "Ocurrio un problema durante\n la conexion");
                        }
            			break;
            		case 2:
            			error("Ingreso error", "Numeros digitados deben\nser distintos");
            			break;
            		case 3:
            			error("Ingreso error", "Todas las casillas\ndeben ser llenadas");
            			break;
            		default:
            			error("Error", "ERROR");
            			break;
            	}
            }
        });
        
        salir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	finish();
            }
        });
        
        direccion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), UrlActivity.class);
				startActivity(i);
				finish();
			}
		});
        
    }
    
    private void error (String nombreError, String err) {
        LayoutInflater inflater= getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_disenio, (ViewGroup) findViewById(R.id.toast_diseno));
        TextView tv=(TextView) layout.findViewById(R.id.text_toast);
        tv.setText(err);        
        Log.e(nombreError, err);        
        Toast tst = new Toast(getApplicationContext());
        tst.setDuration(Toast.LENGTH_SHORT);
        tst.setView(layout);
        tst.show();
    }
    
    public int esValido(EditText num1, EditText num2, EditText num3, EditText num4) {
    	String numero1=num1.getText().toString();
    	String numero2=num2.getText().toString();
    	String numero3=num3.getText().toString();
    	String numero4=num4.getText().toString();
    	    	
    	if((numero1.length()==1 && numero2.length()==1 && numero3.length()==1 && numero4.length()==1))
    	{
    		int n1=Integer.valueOf(numero1);
    		int n2=Integer.valueOf(numero2);
    		int n3=Integer.valueOf(numero3);
    		int n4=Integer.valueOf(numero4);
    		if (((n1 != n2) && (n1 != n3) && (n1 != n4) && (n2 != n3) && (n2 != n4) && (n3 != n4))) {
            	return 1;
        	}
    		else {
           	return 2;
    		}
    	}
    	else {
    		return 3;
    	}
    }
}