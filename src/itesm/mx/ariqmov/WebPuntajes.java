package itesm.mx.ariqmov;

import itesm.mx.ariqmov.Music.Musica;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;

/**
 * 
 * @author Amilcar Andrade Garcia
 * @Version 1.0.0
 * @since 1.0.0
 * Pantalla que despliega el xml de webpage puntajes
 *
 */
public class WebPuntajes extends Activity {
	
	private ProgressDialog pd;
	private Handler uiCallback = new Handler(){
		public void handleMessage(Message msg){
			pd.dismiss(); 
		}
	};


	protected void onCreate(Bundle savedInstancesState){
		super.onCreate(savedInstancesState);
		if(Menu.noConexion){
			Intent noConexion = new Intent(WebPuntajes.this, NoConexion.class);
			startActivity(noConexion);	
		}
		this.pd = ProgressDialog.show(this, "Cargando", "Cargando recursos...");
		cargarRecursos();
		setContentView(R.layout.webpage_puntajes);	
		WebView wb = (WebView)findViewById(R.id.webView1);
		configuradoWebView(wb);
	}
	
	/**
	 * 
	 * @param Webview
	 * @return void
	 * Pre-configuracion del webview que se mostrara en el layout
	 */
	private void configuradoWebView(WebView wb){
		wb.getSettings().setLoadWithOverviewMode(true);
	    wb.getSettings().setUseWideViewPort(true);
	    wb.getSettings().setBuiltInZoomControls(true);
	    wb.getSettings().setSupportZoom(true);
	    wb.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U;`Android 2.0; en-us; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");
		wb.loadUrl("http://aritmos.herokuapp.com/inicio");
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
    
    private void cargarRecursos() {
		Thread th = new Thread() {
			public void run() {
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				uiCallback.sendEmptyMessage(0);
			}
		};
		th.start();
	}
	
}
