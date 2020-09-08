import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


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


class KeyOperation extends JFrame implements KeyListener {
  static int counter;
  int mazeSize;
  int [][] wall;
  int x;
  int y;
  int z;
  int a;
  int b;
  JPanel jp;
  JPanel jp1;
  ImageIcon imageWall;
  ImageIcon imageWay;
  ImageIcon imageUP;
  ImageIcon imageDOWN;
  ImageIcon imageRIGHT;
  ImageIcon imageLEFT;
  JLabel[][] dungeon;

  static Clip clip2;
  AudioInputStream audioInputStream2;
  static int count;


  public KeyOperation( int mazeSize,int[][] wall, JLabel[][] dungeon,JPanel jp,JPanel jp1,ImageIcon imageWalljlabel,ImageIcon imageWayjlabel,ImageIcon imageUPlabel,
                      ImageIcon imageDOWNlabel,ImageIcon imageRIGHTlabel,ImageIcon imageLEFTlabel) {

    this.mazeSize = mazeSize;
    this.wall = wall;
    this.dungeon = dungeon;
    x = 4;
    y = 1;
    a = 4;
    b = 1;

    jp = new JPanel() ;
    this.jp = jp;


    jp1 = new JPanel() ;
    this.jp1 = jp1;

    imageWall = new ImageIcon();
    imageWall = imageWalljlabel;

    imageWay = new ImageIcon();
    imageWay = imageWayjlabel;

    imageUP = new ImageIcon();
    imageUP = imageUPlabel;

    imageDOWN = new ImageIcon();
    imageDOWN = imageDOWNlabel;

    imageRIGHT = new ImageIcon();
    imageRIGHT = imageRIGHTlabel;

    imageLEFT = new ImageIcon();
    imageLEFT = imageLEFTlabel;



    //キー入力の有効化
    addKeyListener(this);
  }

  //画面切り替え用メソッド
  // public void changeBattleview(JPanel panel,JLabel label1,JLabel label2,JLabel label3) {
  public void changeView(JPanel panel) {

    //ContentPaneにはめ込まれたパネルを削除
    getContentPane().removeAll();

    // panel.add(label);
    add(panel);//パネルの追加

    validate();//更新
    repaint();//再描画
  }





  @Override
  	public void keyTyped(KeyEvent e) {
  		//使用しないので空にしておきます。
  	}

  @Override
    public void keyPressed(KeyEvent e)  {

      switch ( e.getKeyCode() ) {
        //上キー
        case KeyEvent.VK_UP:

        System.out.println("上が押されました");
        if (wall[x-1][y] == 0) {
          wall[x][y] = 0;
          x = x-1;
          wall[x][y] = 2;

          // 書き換えたい画像
          dungeon[a][b].setIcon(imageWay);
          a = a-1;
          dungeon[a][b].setIcon(imageUP);

        } else if (wall[x-1][y] == 3){
          wall[x][y] = 0;
          x = x-1;
          wall[x][y] = 2;

          // 書き換えたい画像
          dungeon[a][b].setIcon(imageWay);
          a = a-1;
          dungeon[a][b].setIcon(imageUP);
          // layout.first(cardPanel2);

          Music.clip.stop();


          counter = 0;

          //Battleviewのインスタンス化
          Battleview btv = new Battleview();
          // changeBattleview(btv.battle,btv.char1,btv.back,btv.report);
          changeView(btv.battle);

          int x =1;
          Music main2 = new Music(x);




        }
        break;
        //下キー
        case KeyEvent.VK_DOWN:
        System.out.println("下が押されました");
        if ( x+1 < mazeSize && wall[x+1][y] == 0) {
          wall[x][y] = 0;
          x = x+1;
          wall[x][y] = 2;
          // 書き換えたい画像
          dungeon[a][b].setIcon(imageWay);
          a = a+1;
          dungeon[a][b].setIcon(imageDOWN);
        }
        break;

        //右キー
        case KeyEvent.VK_RIGHT:
        System.out.println("右が押されました");
        if (wall[x][y+1] == 0) {
          wall[x][y] = 0;
          y = y+1;
          wall[x][y] = 2;

          // 書き換えたい画像
          dungeon[a][b].setIcon(imageWay);
          b = b+1;
          dungeon[a][b].setIcon(imageRIGHT);


        }
        break;
        //左キー
        case KeyEvent.VK_LEFT:
        System.out.println("左が押されました");
        if (wall[x][y-1] == 0) {
          wall[x][y] = 0;
          y = y-1;
          wall[x][y] = 2;

          // 書き換えたい画像
          dungeon[a][b].setIcon(imageWay);
          b = b-1;
          dungeon[a][b].setIcon(imageLEFT);
        }
        break;

        // Enterキーを押した時
        case KeyEvent.VK_ENTER:
        if(Char.getHP() > 0) {
          if(Char.getenemyHP() > 0) {
            if(counter == 0) {

              System.out.println("ENTERが押されました。");
              Battleview.report.setText(Char.enemyName + "の攻撃!");
              counter++;

            } else if(counter == 1){
              System.out.println("ENTERが押されました。");
              Battleview.report.setText("勇者は,"+ Char.getDamage() +"ダメージを受けた。");
              battleMusic(counter);
              // Time();
              Battleview.char1.setText(Char.name + "  "+"LV23" + "  " + "HP "+ "200/"+ Char.getHP());
              counter++;
            } else if(counter == 2){
              System.out.println("ENTERが押されました。");

              Battleview.report.setText(Char.name + "の攻撃!"+ "  " +Char.getBraveDamage() +"ダメージあたえた。");
              battleMusic(counter);
              counter = 0;

            }else{
              System.out.println("ENTERが押されました。");
            }

          } else {
            // GAME クリア画面
            Music.clip.stop();
            View clear = new View();
            changeView(clear.gameClear);
            View.endMusic(1);
          }

        }else{
          // GAME OVER 画面に切り替え
          Music.clip.stop();
          View over = new View();
          changeView(over.gameOver);
          View.endMusic(2);
        }
        break;

      }
    }

  @Override
    public void keyReleased(KeyEvent e) {
      switch ( e.getKeyCode() ) {
        //上キー
        case KeyEvent.VK_UP:
        System.out.println("上が離されました");
        break;
        //下キー
        case KeyEvent.VK_DOWN:
        System.out.println("下が離されました");
        break;
        //右キー
        case KeyEvent.VK_RIGHT:
        System.out.println("右が離されました");
        break;
        //左キー
        case KeyEvent.VK_LEFT:
        System.out.println("左が離されました");
        break;
      }
    }

    // 迷路の描写
    public void   printMaze(){
      for (int i = 0; i < wall.length ;i++ ) {
        for(int j = 0; j< wall.length;j++) {
          if (wall[i][j]==0) {
            System.out.print("  ");
          } else if (wall[i][j]== 1) {
            System.out.print("[]");
          } else if (wall[i][j]== 2 ) {
            System.out.print("**");
          } else {
            System.out.print("  ");
          }
        }
        System.out.println("");
      }
      System.out.println("");


      // jp.setBounds(300, 150, 40  * mazeSize, 40 * mazeSize);
      jp.setBackground(Color.BLACK);




      // jp1.setBackground(Color.BLACK);
      add(jp);
      // add(jp1);
      setVisible(true);
    }

    public void battleMusic(int counter){
      int count = 0;
      clip2 = null;
      try{
      if(counter == 1){
        File soundFile2 = new File("Music/se_maoudamashii_battle07.wav");
        audioInputStream2 = AudioSystem.getAudioInputStream(soundFile2);
        AudioFormat audioFormat2 = audioInputStream2.getFormat();
        DataLine.Info info2 = new DataLine.Info(Clip.class, audioFormat2);
        clip2 = (Clip)AudioSystem.getLine(info2);
        clip2.open(audioInputStream2);
        clip2.loop(count);

      }else{
        File soundFile2 = new File("Music/se_maoudamashii_battle03.wav");
        audioInputStream2 = AudioSystem.getAudioInputStream(soundFile2);
        AudioFormat audioFormat2 = audioInputStream2.getFormat();
        DataLine.Info info2 = new DataLine.Info(Clip.class, audioFormat2);
        clip2 = (Clip)AudioSystem.getLine(info2);
        clip2.open(audioInputStream2);
        clip2.loop(count);

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
