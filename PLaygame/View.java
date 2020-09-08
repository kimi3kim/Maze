import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.awt.CardLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.BufferedReader;
import java.io.InputStreamReader;


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


public class View extends JFrame {
  JPanel gameClear;
  JPanel gameOver;
  static Clip clip3;
  static AudioInputStream audioInputStream3;

  View() {
    JLabel clear1 = new JLabel("Congratulations!");
    clear1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 50));
    clear1.setForeground(Color.GREEN);
    clear1.setHorizontalAlignment(JLabel.CENTER);


    gameClear = new JPanel();
    gameClear.setLayout(new BorderLayout());
    gameClear.add(clear1,BorderLayout.CENTER);

    JLabel over = new JLabel("GAME OVER!");
    over.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 50));
    over.setForeground(Color.RED);
    over.setHorizontalAlignment(JLabel.CENTER);

    gameOver = new JPanel();
    gameOver.setLayout(new BorderLayout());
    gameOver.add(over,BorderLayout.CENTER);
    gameOver.setBackground(Color.BLACK);



  }

  public static void endMusic(int end){
    // int count = 0;
    clip3 = null;
    try{
    if(end == 1){
      File soundFile3 = new File("Music/game_maoudamashii_5_castle01.wav");
      audioInputStream3 = AudioSystem.getAudioInputStream(soundFile3);
      AudioFormat audioFormat3 = audioInputStream3.getFormat();
      DataLine.Info info3 = new DataLine.Info(Clip.class, audioFormat3);
      clip3 = (Clip)AudioSystem.getLine(info3);
      clip3.open(audioInputStream3);
      clip3.loop(Clip.LOOP_CONTINUOUSLY);

    }else{
      File soundFile3 = new File("Music/game_maoudamashii_6_dangeon20.wav");
      audioInputStream3 = AudioSystem.getAudioInputStream(soundFile3);
      AudioFormat audioFormat3 = audioInputStream3.getFormat();
      DataLine.Info info3 = new DataLine.Info(Clip.class, audioFormat3);
      clip3 = (Clip)AudioSystem.getLine(info3);
      clip3.open(audioInputStream3);
      clip3.loop(Clip.LOOP_CONTINUOUSLY);

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
  //   clip2.loop(Clip.LOOP_CONTINUOUSLY);
  //   Thread.sleep(10000);
  // }catch (InterruptedException e){
  // }

}

}
