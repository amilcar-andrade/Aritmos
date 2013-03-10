package itesm.mx.ariqmov;

import itesm.mx.ariqmov.Music.Musica;
import android.app.Activity;
import android.os.Bundle;
/**
 * 
 * @author 
 * Pantalla que despliega instrucciones del juego
 *
 */
public class Instrucciones extends Activity {

	protected void onCreate(Bundle savedInstancesState){
		super.onCreate(savedInstancesState);	
		setContentView(R.layout.instrucciones);
	}
	@Override
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
