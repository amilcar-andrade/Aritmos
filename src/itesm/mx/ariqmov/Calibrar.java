package itesm.mx.ariqmov;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 
 * @author Amilcar Andrade García
 * @Version 1.0.0
 * @since 1.0.0
 * Pantalla que despliega el xml de calibrar
 *
 */
public class Calibrar extends Activity {


	protected void onCreate(Bundle savedInstancesState){
		super.onCreate(savedInstancesState);
		setContentView(R.layout.calibrar);	
	}
	/**
	 * 
	 * @param View
	 * @return void
	 * Atiende el boton en el Layout
	 */
	 public void atenderBoton(View v){
	    	switch (v.getId()){
	    	
	    	case R.id.btnCalibrar:
	    		Intent atrapa = new Intent(Calibrar.this,MenuAtrapa.class);
	    		Music.playEffect();
				startActivity(atrapa);
				break;
	    	
	    	}
	 }

}
