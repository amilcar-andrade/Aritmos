package itesm.mx.ariqmov;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * 
 * @author Amilcar Andrade Garcia
 * @Version 1.0.0
 * @since 1.0.0
 * Pantalla que despliega el xml de puntajes
 *
 */
public class Puntajes extends Activity {


	protected void onCreate(Bundle savedInstancesState){
		super.onCreate(savedInstancesState);
		setContentView(R.layout.puntajes);	
		EditText etPuntaje= (EditText)findViewById(R.id.etpuntaje);
		etPuntaje.setEnabled(false);
		etPuntaje.setText(JuegoAtrapaOperacion.statusJuego[0]+"");
		Button btnSi = (Button)findViewById(R.id.btnSi);
		if (Menu.noConexion){
			btnSi.setVisibility(View.GONE);
		}
	}
		
	/**
	 * 
	 * @param View
	 * @return void
	 * Atiende el boton en el Layout
	 * @throws InterruptedException 
	 */
	
	 public void atenderBoton(View v) throws InterruptedException{
	    	switch (v.getId()){
	    	
	    	case R.id.btnNo:
	    		Thread.sleep(500);
				finish();
				
				break;
	    	case R.id.btnSi:
	    		Intent guardar = new Intent(Puntajes.this,Guardar.class);
				startActivity(guardar);
				finish();
				
				break;
	    	}
	 }
}
