package itesm.mx.ariqmov;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * 
 * @author Amilcar Andrade Garc��a
 * @Version 1.0.0
 * @since 1.0.0
 * Pantalla que despliega el xml de conexion_internet
 *
 */

public class NoConexion extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conexion_internet);
		if(Menu.actividadPreviaPuntajes == true){
			Button btnOk = (Button) findViewById(R.id.btnOk);
			btnOk.setVisibility(View.GONE);
		}
	}

	/**
	 * 
	 * @param View
	 * @return void
	 * Atiende el boton en el Layout
	 */
	public void atenderBoton(View v) {

		switch (v.getId()) {

		case R.id.btnOk:
			Intent selJuego = new Intent(NoConexion.this, SeleccionaJuego.class);
			Music.playEffect();
			startActivity(selJuego);
			finish();
			break;
		default:
			break;

		}
	}
}
