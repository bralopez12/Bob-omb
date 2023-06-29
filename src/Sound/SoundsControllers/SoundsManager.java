
package Sound.SoundsControllers;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 *
 * @author blopez
 */
public class SoundsManager {
    
    private Clip musicClip;
    private String soundPath;
    private int numeroRepeteciones;
        
    
    
    
    public void Reproducir(String soundPath,int numeroRepeticiones) {
    // Lógica para reproducir un efecto de sonido
      try {
            // Cargar el archivo de música
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(soundPath));

            // Crear un Clip para reproducir la música
            musicClip = AudioSystem.getClip();
            musicClip.open(audioInputStream);

            // Reproducir la música en bucle
            musicClip.loop(numeroRepeticiones);
            musicClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public boolean Reproducir(){
        if(musicClip != null){
            musicClip.start();
            return true;
        }
        return false;
    }
    
    public boolean IsRunning(){
        return musicClip.isRunning();
    }
    


    public void Stop() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
        }
    }
        
}
