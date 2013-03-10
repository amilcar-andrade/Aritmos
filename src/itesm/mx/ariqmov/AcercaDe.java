package itesm.mx.ariqmov;


import itesm.mx.ariqmov.Music.Musica;
import itesm.mx.ariqmov.R;
import itesm.mx.ariqmov.R.layout;
import android.app.Activity;
import android.os.Bundle;

/**
 * 
 * @author 
 * Pantalla que despliega el xml de acercade
 *
 */

public class AcercaDe extends Activity {


	protected void onCreate(Bundle savedInstancesState){

		super.onCreate(savedInstancesState);
		
		setContentView(R.layout.acercade);		
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
