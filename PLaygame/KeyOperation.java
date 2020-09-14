import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
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

  static String view ;
  static Clip clip2;
  AudioInputStream audioInputStream2;
  static int count;
  static int count2;
  static int moveCounter;


  public KeyOperation( final int mazeSize,final int[][] wall, final JLabel[][] dungeon,JPanel jp,JPanel jp1,final ImageIcon imageWalljlabel,final ImageIcon imageWayjlabel,final ImageIcon imageUPlabel,
                      final ImageIcon imageDOWNlabel,final ImageIcon imageRIGHTlabel,final ImageIcon imageLEFTlabel,final int x,final int y) {

    this.mazeSize = mazeSize;
    this.wall = wall;
    this.dungeon = dungeon;

    view = "maze";
    moveCounter = 0;

// キャラのスタート位置を指定
    this.x = x;
    this.y = y;
    a = x;
    b = y;

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

    //Battleviewのインスタンス化
    final Battleview btv = new Battleview();
    count2 = 0;


    //キー入力の有効化
    addKeyListener(this);
  }

  //画面切り替え用メソッド

  public void changeView(final JPanel panel) {

    //ContentPaneにはめ込まれたパネルを削除
    getContentPane().removeAll();

    // panel.add(label);
    add(panel);//パネルの追加

    validate();//更新
    repaint();//再描画
  }





  @Override
  	public void keyTyped(final KeyEvent e) {
  		//使用しないので空にしておきます。
  	}

  @Override
    public void keyPressed(final KeyEvent e)  {

      switch ( e.getKeyCode() ) {


        //上キー
        case KeyEvent.VK_UP:
        System.out.println("上が押されました");

        // 迷路が表示されている時。
        if(view.equals("maze")){

          if (wall[x-1][y] == 0) {
            wall[x][y] = 0;
            x = x-1;
            wall[x][y] = 2;

            // 書き換えたい画像
            dungeon[a][b].setIcon(imageWay);
            a = a-1;
            dungeon[a][b].setIcon(imageUP);
            encountEnemy();

          } else if (wall[x-1][y] == 3){
            wall[x][y] = 0;
            x = x-1;
            wall[x][y] = 2;

            // 書き換えたい画像
            dungeon[a][b].setIcon(imageWay);
            a = a-1;
            dungeon[a][b].setIcon(imageUP);


            Music.clip.stop();

            encountEnemy();


          }
        }
        break;

        //下キー
        case KeyEvent.VK_DOWN:
        System.out.println("下が押されました");

        // 迷路が表示されている時
        if(view.equals("maze")){
          if ( x+1 < mazeSize && wall[x+1][y] == 0) {
            wall[x][y] = 0;
            x = x+1;
            wall[x][y] = 2;
            // 書き換えたい画像
            dungeon[a][b].setIcon(imageWay);
            a = a+1;
            dungeon[a][b].setIcon(imageDOWN);
            encountEnemy();
          }

        }
        break;

        //右キー
        case KeyEvent.VK_RIGHT:
        System.out.println("右が押されました");
        // 迷路が表示されている時
        if(view.equals("maze")){

          if (wall[x][y+1] == 0) {
            wall[x][y] = 0;
            y = y+1;
            wall[x][y] = 2;

            // 書き換えたい画像
            dungeon[a][b].setIcon(imageWay);
            b = b+1;
            dungeon[a][b].setIcon(imageRIGHT);

            encountEnemy();


          }
        }
        break;

        //左キー
        case KeyEvent.VK_LEFT:
        System.out.println("左が押されました");
        // 迷路が表示されている時
        if(view.equals("maze")){

          if (wall[x][y-1] == 0) {
            wall[x][y] = 0;
            y = y-1;
            wall[x][y] = 2;

            // 書き換えたい画像
            dungeon[a][b].setIcon(imageWay);
            b = b-1;
            dungeon[a][b].setIcon(imageLEFT);
            encountEnemy();
          }
        }
        break;

        // Enterキーを押した時の操作
        case KeyEvent.VK_ENTER:
        System.out.println("ENTERが押されました。");

        // バトル画面の時
        if(view.equals("battle")){
          if(Char.getHP() > 0) {
            if(Char.getenemyHP() > 0) {
              if(counter == 0) {
                //
                Battleview.report.setText(Char.getEnemyName() + "の攻撃!");
                counter++;

              } else if(counter == 1){

                Battleview.report.setText("勇者は,"+ Char.getDamage() +"ダメージを受けた。");
                battleMusic(counter);
                // Time();
                Battleview.char1.setText(Char.getName() + "  "+"LV23" + "  " + "HP "+  Char.getHP()+"/200");
                counter++;
              } else if(counter == 2){


                Battleview.report.setText(Char.getName() + "の攻撃!"+ "  " +Char.getBraveDamage() +"ダメージあたえた。");
                battleMusic(counter);
                counter = 0;

              }else{
                System.out.println("ENTERが押されました。");
              }

            } else {
              if(view.equals("battle")){
                endBattle();
                Char.setenemyHP(Char.getenemymaxHP());


              }else{
                // GAME クリア画面
                Music.clip.stop();
                final View clear = new View();
                changeView(clear.gameClear);
                View.endMusic(1);

              }
            }

          }else{
            // GAME OVER 画面に切り替え
            Music.clip.stop();
            final View over = new View();
            changeView(over.gameOver);
            View.endMusic(2);
          }

        }

        break;

        // スペースキーが押されたらパネルを追加


      }
        moveCounter++;
    }

  @Override
    public void keyReleased(final KeyEvent e) {

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

    }


    public void encountEnemy(){
      if (moveCounter == 5){
        Music.clip.stop();

        view = "battle";

        changeView(Battleview.battle);

        final int x =1;
        final Music main2 = new Music(x);

      }

    }

    public void viewMaze(){
      if(count2==0){

        for (int i = 0; i < wall.length ;i++ ) {
          for(int j = 0; j< wall.length;j++) {
            if (wall[i][j]== 0) {
              System.out.print("  ");
              final JLabel way = new JLabel(imageWay);
              jp1.add(way);

              dungeon[i][j]= way;

            } else if (wall[i][j]== 1) {
              System.out.print("[]");
              final JLabel imagewall = new JLabel(imageWall);
              jp1.add(imagewall);
              dungeon[i][j] = imagewall;

            } else if (wall[i][j]== 2 ) {
              System.out.print("**");
              final JLabel uP = new JLabel(imageUP);
              jp1.add(uP);
              dungeon[i][j] = uP;

            } else {
              System.out.print("  ");
              final JLabel way = new JLabel(imageWay);
              jp1.add(way);

              dungeon[i][j]= way;
            }
          }
          System.out.println("");
        }
        System.out.println("");
      }
    }

    public void endBattle(){
      view = "maze";
      Music.clip.stop();

      // 迷路画面に戻す
      viewMaze();
      changefromBattleview(jp,jp1);
      moveCounter = 0;

      final Music main = new Music();
    }

    public void changefromBattleview(final JPanel panel,final JPanel panel1) {

      if(count2==0){
        //ContentPaneにはめ込まれたパネルを削除
        getContentPane().removeAll();

      panel1.setBounds(100, 10, 39  * mazeSize, 45 * mazeSize);
      panel1.setBackground(Color.BLACK);
      panel.setBackground(Color.BLACK);
      count2++;
    }else{
      getContentPane().removeAll();
    }


      add(panel1);//パネルの追加
      add(panel);
      validate();//更新
      repaint();//再描画
    }


    public void battleMusic(final int counter){
      final int count = 0;
      clip2 = null;
      try{
      if(counter == 1){
        final File soundFile2 = new File("Music/se_maoudamashii_battle07.wav");
        audioInputStream2 = AudioSystem.getAudioInputStream(soundFile2);
        final AudioFormat audioFormat2 = audioInputStream2.getFormat();
        final DataLine.Info info2 = new DataLine.Info(Clip.class, audioFormat2);
        clip2 = (Clip)AudioSystem.getLine(info2);
        clip2.open(audioInputStream2);
        clip2.loop(count);

      }else{
        final File soundFile2 = new File("Music/se_maoudamashii_battle03.wav");
        audioInputStream2 = AudioSystem.getAudioInputStream(soundFile2);
        final AudioFormat audioFormat2 = audioInputStream2.getFormat();
        final DataLine.Info info2 = new DataLine.Info(Clip.class, audioFormat2);
        clip2 = (Clip)AudioSystem.getLine(info2);
        clip2.open(audioInputStream2);
        clip2.loop(count);

      }

    } catch (final UnsupportedAudioFileException e){
      e.printStackTrace();
    } catch (final IOException e){
      e.printStackTrace();
    } catch (final LineUnavailableException e){
      e.printStackTrace();
    }


  }




}
