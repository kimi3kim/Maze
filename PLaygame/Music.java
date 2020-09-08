import java.io.File;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music{
  static Clip clip;


  Music(){

    clip = null;
    int count = 5;      // (count+1)回 再生する
    AudioInputStream audioInputStream;

    try{
      File soundFile = new File("Music/game_maoudamashii_6_dangeon22.wav");
      audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      AudioFormat audioFormat = audioInputStream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
      clip = (Clip)AudioSystem.getLine(info);
      clip.open(audioInputStream);
      // clip.loop(count);
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (UnsupportedAudioFileException e){
      e.printStackTrace();
    } catch (IOException e){
      e.printStackTrace();
    } catch (LineUnavailableException e){
      e.printStackTrace();
    }

    // 10秒経過したら終了する
    try{
      clip.loop(Clip.LOOP_CONTINUOUSLY);
      Thread.sleep(10000);
    }catch (InterruptedException e){
    }
    // clip.stop();
    // System.exit(0);
  }
  Music(int a){
      clip = null;

      // int count = 0;      // (count+1)回 再生する
      AudioInputStream audioInputStream;

      try{
        if (a == 1 ) {
          File soundFile = new File("Music/bgm_maoudamashii_fantasy15.wav");
          audioInputStream = AudioSystem.getAudioInputStream(soundFile);
          AudioFormat audioFormat = audioInputStream.getFormat();
          DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
          clip = (Clip)AudioSystem.getLine(info);
          clip.open(audioInputStream);
          clip.loop(Clip.LOOP_CONTINUOUSLY);
        }


      } catch (UnsupportedAudioFileException e){
        e.printStackTrace();
      } catch (IOException e){
        e.printStackTrace();
      } catch (LineUnavailableException e){
        e.printStackTrace();
      }

      // 10秒経過したら終了する
      // try{
      //   clip.loop(Clip.LOOP_CONTINUOUSLY);
      //   Thread.sleep(10000);
      // }catch (InterruptedException e){
      // }
      // clip.stop();
      // System.exit(0);

  }


}
