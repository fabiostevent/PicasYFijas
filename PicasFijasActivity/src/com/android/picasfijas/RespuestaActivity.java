package com.android.picasfijas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class RespuestaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.respuesta_activity);
		
		Bundle bundle=getIntent().getExtras();
		Button continuar=(Button) findViewById(R.id.ContinuarRespuesta);
		TextView texto=(TextView) findViewById(R.id.TextoRespuesta);
		TextView marcador=(TextView) findViewById(R.id.marcadorRespuesta);
		String text="", marca="";
		int picas, fijas, intentos;
		
		picas=bundle.getInt("picas");
		fijas=bundle.getInt("fijas");
		intentos=bundle.getInt("intentos");
		
		if(fijas==4) {
			text="¡FELICIDADES, GANASTE!";
			marca="Tu marcador son " + intentos + " intentos.";
		}
		else {
			text="Hay " + picas + " picas y " + fijas + " fijas.";
			marca="Tu marcador son " + intentos + " intentos.";
		}
		
		Typeface fuente= Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf");
		texto.setTypeface(fuente);
		marcador.setTypeface(fuente);
		texto.setGravity(Gravity.CENTER);
		marcador.setGravity(Gravity.CENTER);
		texto.setText(text);
		marcador.setText(marca);
		continuar.setTypeface(fuente);
		
		continuar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), PicasFijasActivity.class);
				startActivity(i);
            	finish();
			}
		});
	}
}
