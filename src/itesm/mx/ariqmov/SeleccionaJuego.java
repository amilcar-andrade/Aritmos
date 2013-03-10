package itesm.mx.ariqmov;

import itesm.mx.ariqmov.Music.Musica;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class SeleccionaJuego extends Activity {

    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seljuego);
			
		
	}
    public void atenderBoton(View v){
    	
    	switch (v.getId()){
    	
    	case R.id.btnatrapa_op:
    		Intent calibra = new Intent(SeleccionaJuego.this,MenuAtrapa.class);
    		Music.playEffect();
			startActivity(calibra);
			break;
    	case R.id.btncompleta_op:
    		Intent completa = new Intent(SeleccionaJuego.this,MenuCompleta.class);
    		Music.playEffect();
			startActivity(completa);
			break;
    	    	}
    }
    protected void onResume() {
    	super.onResume();
    	Music.playMusic();
    	}
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Music.soundTransition(Musica.PAUSED);

    }
    
   
}
