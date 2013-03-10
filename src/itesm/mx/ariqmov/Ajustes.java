package itesm.mx.ariqmov;

import itesm.mx.ariqmov.Music.Musica;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * 
 * @author Amilcar Andrade García
 * @Version 1.0.0
 * @since 1.0.0
 * Pantalla que despliega el xml de ajustes y habilita y deshabilita los efectos de musica o sonidos
 *
 */

public class Ajustes extends Activity {
	

	public static boolean efectoHabilitado = true;
	
	public static boolean sonidoHabilitado = true;
	
	protected void onCreate(Bundle savedInstancesState){
		super.onCreate(savedInstancesState);	
		setContentView(R.layout.ajustes);
		 
		final CheckBox checkboxEfectos = (CheckBox) findViewById(R.id.checkBoxEfectos);
		checkboxEfectos.setChecked(efectoHabilitado);
		checkboxEfectos.setOnClickListener(new OnClickListener() {
			
		    public void onClick(View v) {
		        if (((CheckBox) v).isChecked()) {
		        	efectoHabilitado = true;
		        	checkboxEfectos.setChecked(efectoHabilitado);

		        } else {   
		        	efectoHabilitado = false;
		        	checkboxEfectos.setChecked(efectoHabilitado);
		        	
		        }
		    }
		});
		
		final CheckBox checkboxMusica = (CheckBox) findViewById(R.id.checkBoxMusica);
		checkboxMusica.setChecked(sonidoHabilitado);
		checkboxMusica.setOnClickListener(new OnClickListener() {
			
		    public void onClick(View v) {
		        if (((CheckBox) v).isChecked()) {
		        	sonidoHabilitado = true;
		        	checkboxMusica.setChecked(sonidoHabilitado);
		        	Music.soundTransition(Musica.PLAYED);    	

		        	
		        } else {
		        	sonidoHabilitado = false;
		        	checkboxMusica.setChecked(sonidoHabilitado);
		        	Music.soundTransition(Musica.PAUSED);    	

		        }
		    }
		});
		
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