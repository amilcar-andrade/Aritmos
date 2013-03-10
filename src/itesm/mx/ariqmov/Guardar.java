package itesm.mx.ariqmov;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Guardar extends Activity {
	

	/**
	 * 
	 * @author Amilcar Andrade Garcia
	 * @Version 1.0.0
	 * @since 1.0.0
	 * Pantalla que despliega el xml de guarda_datos
	 *
	 */

	protected void onCreate(Bundle savedInstancesState) {
		super.onCreate(savedInstancesState);
		setContentView(R.layout.guarda_datos);
		EditText etPuntaje = (EditText) findViewById(R.id.etpuntaje);
		etPuntaje.setEnabled(false);
		
		EditText etNivel = (EditText) findViewById(R.id.etNivel);
		EditText etNombre = (EditText) findViewById(R.id.etNombre);
		//EditText etNickname = (EditText) findViewById(R.id.etNickname);
		
		TextView txtNivel = (TextView) findViewById(R.id.txtCampoRequeridoNivel);
		TextView txtNombre = (TextView) findViewById(R.id.txtCampoRequeridoNombre);
		//TextView txtNickName = (TextView) findViewById(R.id.txtCampoRequeridoNickname);
		
		if(JuegoAtrapaOperacion.statusJuego[1] == 1){
			etNivel.setText("Atrapa Operación " + (JuegoAtrapaOperacion.statusJuego[2]+1));	
			etPuntaje.setText(JuegoAtrapaOperacion.statusJuego[0]+"");

		}else{
			etNivel.setText("Completa Operación " + (JuegoAtrapaOperacion.statusJuego[2]+1));	
			etPuntaje.setText(JuegoAtrapaOperacion.statusJuego[0]+"");
		}
		etNivel.setEnabled(false);
		//esconderEtiquetas(txtNivel, txtNickName, txtNombre);
		esconderEtiquetas(txtNivel, txtNombre);
		//limpiarTextBoxs(etNickname, etNombre);
		limpiarTextBoxs(etNombre);

	}
		
	/**
	 * 
	 * @param view
	 * @return void
	 * Atiende al boton dentro del layout
	 */
	public void atenderBoton(View v) {
		
		EditText etNivel = (EditText) findViewById(R.id.etNivel);
		EditText etNombre = (EditText) findViewById(R.id.etNombre);
		//EditText etNickname = (EditText) findViewById(R.id.etNickname);
		EditText etPuntaje = (EditText) findViewById(R.id.etpuntaje);
		TextView txtNivel = (TextView) findViewById(R.id.txtCampoRequeridoNivel);
		TextView txtNombre = (TextView) findViewById(R.id.txtCampoRequeridoNombre);
		//TextView txtNickName = (TextView) findViewById(R.id.txtCampoRequeridoNickname);
		

		switch (v.getId()) {
		case R.id.btnGuardar:
			//String nickname = etNickname.getText().toString();
			String nombre = etNombre.getText().toString();
			String nivel = etNivel.getText().toString();
			String puntaje = etPuntaje.getText().toString();

			if ("".equals(nivel) ||  "".equals(nombre)) {
	
			
				if ("".equals(nivel)){
				txtNivel.setVisibility(View.VISIBLE);
				}
				
				//if ("".equals(nickname)){
				//txtNickName.setVisibility(View.VISIBLE);
				//}
				
				if ("".equals(nombre)){
				txtNombre.setVisibility(View.VISIBLE);
				}
				
			} 
			 else {
				 
					try {
						String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
						Log.d("FECHA", ""+ mydate);
						String url = "http://aritmos.herokuapp.com/guarda/";
						String cadena = nombre + "/" + puntaje + "/" + nivel + "/" + mydate;
						String query = URLEncoder.encode(cadena, "utf-8");
						String encodedurl = url + query;
						new RequestTask().execute(encodedurl);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					RequestTask.esperarHttpResponse();
					
					if (RequestTask.responseServer) {
						Intent confirmacion = new Intent(Guardar.this,
								RegistroGuardado.class);
						startActivity(confirmacion);
						finish();
						
					}
					JuegoAtrapaOperacion.statusJuego[0] = 0;
					
				}

		default:
			break;
		}
	}
	
	
	/**
	 * 
	 * @param EditText
	 * @return void
	 * Deja en blanco las casillas de los EditText en el Layout
	 */
		
    public void limpiarTextBoxs(EditText... editTexts){
		for (EditText editText : editTexts) {
			editText.setText("");
		}
	}
    
	
	/**
	 * 
	 * @param TextView
	 * @return void
	 * Escode las etiquetas de "Campo Requerido" que salen en el Layout
	 */
    
    public void esconderEtiquetas(TextView... textViews){
		for (TextView textView : textViews) {
			textView.setVisibility(View.GONE);
		}
	}
	
}
