package itesm.mx.ariqmov;


import org.cocos2d.sound.SoundEngine;

import android.content.Context;
/**
 * 
 * @author Amilcar Andrade García
 * @Version 1.0.0
 * @since 1.0.0
 *
 */
public class Music {

	public static enum Musica {PLAYED, PAUSED, RESUME}
	private static Musica musica;
	private static Context _cocos2dContext;
	private static Context soundEffect;
	
	/**
	 * 
	 * @param Context
	 * @return void
	 * Inicializa la musica
	 */
	
	public static void soundInit(Context contexto) {
		_cocos2dContext = contexto;
    	SoundEngine.sharedEngine().preloadSound(_cocos2dContext, R.raw.menumusic);
    	
	}
	/**
	 * 
	 * @param Context
	 * @return void
	 * Inicializa el sonido
	 */
	public static void soundEffectInit(Context contexto) {
		soundEffect = contexto;
    	SoundEngine.sharedEngine().preloadEffect(soundEffect, R.raw.amilcarmedio);
    	
	}
	/**
	 * 
	 * @param void
	 * @return void
	 * Reproduce el sonido
	 */
	public static void playEffect(){
		if(Ajustes.efectoHabilitado){
			SoundEngine.sharedEngine().playEffect(soundEffect, R.raw.amilcarmedio);
		}
	}
	
	/**
	 * 
	 * @param void
	 * @return void
	 * Reproduce la musica
	 */
	public static void playMusic() {
		if (!Ajustes.sonidoHabilitado) {
			Music.soundTransition(Musica.PAUSED);
		} else {
			Music.soundTransition(Musica.PLAYED);
		}
	}
	
	/**
	 * 
	 * @param void
	 * @return void
	 * Libera recursos de musica
	 */
	public static void destruyeSonidosyEfectos(){
		SoundEngine.sharedEngine().realesAllEffects();
		SoundEngine.sharedEngine().realesAllSounds();
		SoundEngine.purgeSharedEngine();
	}
	
	/**
	 * 
	 * @param Musica 
	 * @return void
	 * Detecta las transiciones de la musica
	 */
	
	public static void soundTransition(Musica lista) { 
		musica = lista;
		if (musica == Musica.PLAYED) {
			SoundEngine.sharedEngine().playSound(_cocos2dContext,
					R.raw.menumusic, true);
		} else if (musica == Musica.PAUSED) {
			SoundEngine.sharedEngine().pauseSound();
		} else if (musica == Musica.RESUME) {
			SoundEngine.sharedEngine().resumeSound();
		} else {
			throw new RuntimeException("No valid input");
		}
	}
}