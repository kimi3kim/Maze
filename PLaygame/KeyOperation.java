import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;


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

import java.util.Random;


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
  JPanel space;
  JPanel spaceHP;
  JLabel statas;

  static int spaceCounter;
  static String view ;
  static Clip clip2;
  AudioInputStream audioInputStream2;
  static int count;
  static int count2;
  static int moveCounter;
  static int magicCounter;
  static String action; 

  static String bmAttack;
  static String bmMagic;
  static String bmrunaway;
  static JLabel[] battleMeniu;
  static JLabel[] magicMeniu;
  static int meniuLength ;
  static int magicLength;

  static String[] battleComand;
  static String comand;
  static Random dais;
  static int counterforrun;


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
    battleMeniu = new JLabel[]{btv.attackArrow,btv.magicArrow,btv.runawayArrow};
    magicMeniu = new JLabel[]{btv.brathArrow,btv.drinkArrow};

    battleComand = new String[]{"option","attack","magic","runaway","useMagic"};
    meniuLength = 0; 
    magicLength = 0;
    count2 = 0;


    space = new JPanel();
    LineBorder border = new LineBorder(Color.RED, 2, true);
    space.setBounds(0, 300,300,100);
    space.setBackground(Color.BLACK);
    space.setBorder(border);

    // space　Panelに貼り付けるラベル
    JLabel heal = new JLabel("回復 MP20");
    heal.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    heal.setForeground(Color.WHITE);

    JLabel aro = new JLabel(" ▷ ") ;
    aro.setForeground(Color.WHITE);

    space.add(aro);
    space.add(heal);

    // スペースを押した時にHPとMPを表示するLabelを貼り付けるためのPanel
    spaceHP = new JPanel();
    LineBorder borderHP = new LineBorder(Color.WHITE, 2, true);
    spaceHP.setBounds(0, 0,800,50);
    spaceHP.setBackground(Color.BLACK);
    spaceHP.setBorder(borderHP);

    // HP とMPを表示する Label
    statas = new JLabel(Char.getName() + "  "+"LV23" + "  " + "HP "+  Char.getHP()+"/200"+"  MP "+Char.getMP()); 
    statas.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    statas.setForeground(Color.WHITE);
    spaceHP.add(statas);

    add(spaceHP);
    add(space);

    spaceHP.setVisible(false);
    space.setVisible(false);

    spaceCounter = 0;
    magicCounter = 0;
    counterforrun = 0;

   



    //キー入力の有効化
    addKeyListener(this);
  }

  //画面切り替え用メソッド

  public void changeView(final JPanel panel) {

    //ContentPaneにはめ込まれたパネルを削除
    getContentPane().removeAll();

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

            // encountEnemy();

             // GAME クリア画面
             Music.clip.stop();
             final View clear = new View();
             changeView(clear.gameClear);
             View.endMusic(1);
             view = "gameclear";

          }
        }

        // if Battleview use
        if(view.equals("battle")&& comand.equals(battleComand[0])){
          if(meniuLength -1 >= 0){
            battleMeniu[meniuLength].setText(" ");
            meniuLength = meniuLength - 1; 
            battleMeniu[meniuLength].setText("▷");
          }
        }

        if(view.equals("battle")&& comand.equals(battleComand[2])){
          if(magicLength -1 >= 0){
            magicMeniu[magicLength].setText(" ");
            magicLength = magicLength - 1; 
            magicMeniu[magicLength].setText("▷");
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

        if(view.equals("battle") && comand.equals(battleComand[0])){
          if(meniuLength+1 < battleMeniu.length){
            battleMeniu[meniuLength].setText(" ");
            meniuLength = meniuLength + 1; 
            battleMeniu[meniuLength].setText("▷");

          }
        }

        if(view.equals("battle")&& comand.equals(battleComand[2])){
          if(magicLength +1 < magicMeniu.length){
            magicMeniu[magicLength].setText(" ");
            magicLength = magicLength + 1; 
            magicMeniu[magicLength].setText("▷");
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

        // マップで回復
        if(view.equals("statas")){
          if(Char.getHP() < 200){
            Char.getHeal();
            statas.setText(Char.getName() + "  "+"LV23" + "  " + "HP "+  Char.getHP()+"/200"+"  MP "+Char.getMP());
            Battleview.char1.setText(Char.getName() + "  "+"LV23" + "  " + "HP " + Char.getHP()+"/200"+"  MP "+Char.getMP());

          }
          
        }
      
        // バトル画面の時

        // 攻撃を選択
        if(view.equals("battle") && battleMeniu[0].getText().equals("▷")){
          comand = battleComand[1];
          if(Char.getHP() > 0) {
            if(Char.getenemyHP() > 0) {
              if(counter == 0) {
                Battleview.report.setText(Char.getName() + "の攻撃!"+ "  " +Char.getBraveDamage() +"ダメージあたえた。");
                battleMusic(counter);
                counter++;

              } else if(counter == 1){
                Battleview.report.setText(Char.getEnemyName() + "の攻撃!");
                counter++;

              } else if(counter == 2){
                Battleview.report.setText("勇者は,"+ Char.getDamage() +"ダメージを受けた。");
                battleMusic(counter);
                
                Battleview.char1.setText(Char.getName() + "  "+"LV23" + "  " + "HP "+  Char.getHP()+"/200"+"  MP "+Char.getMP());
                counter++;
                
              }else{
                Battleview.report.setText("次の行動を選択してください.");
                counter = 0;
                comand = battleComand[0];
              }

            } else {
              if(view.equals("battle")){
                counter= 0;
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

          // 呪文を選択
        } else if(view.equals("battle") && battleMeniu[1].getText().equals("▷")){
          if(Char.getHP() > 0) {
            if(Char.getenemyHP() > 0) {
              
      
              if(magicCounter == 0){
                comand = battleComand[2];
                Battleview.chooseMagic.setVisible(true);
                magicCounter ++;  

              } else
                // Breath Careを選択
                if(view.equals("battle") && magicMeniu[0].getText().equals("▷") && magicCounter > 0 ){
                  if(Char.getMP() - 30 < 0){
                    Battleview.chooseMagic.setVisible(false);     
                    Battleview.report.setText("MPがたりません！");
                    comand = battleComand[0];
                   
      
                  }else{
                    if(magicCounter == 1){
                      Battleview.chooseMagic.setVisible(false);
                      Battleview.report.setText(Char.getName() + "は, BREATH CARE を唱えた！");
                      comand = battleComand[4];
                      magicCounter ++;  
                    }else if(magicCounter == 2){
                      Battleview.report.setText("ミントの香りが心地よい！");
                      magicCounter ++;  
                    }else if(magicCounter == 3){
                      Battleview.report.setText(Char.getEnemyName()+" に, "+Char.getBraveMagicDamage("BREATHCARE") +"ダメージあたえた。");
                      Battleview.char1.setText(Char.getName() + "  "+"LV23" + "  " + "HP "+  Char.getHP()+"/200"+"  MP "+Char.getMP());
                      magicCounter ++;  
                    }else if(magicCounter==4){
                      Battleview.report.setText(Char.getEnemyName() + "の攻撃!");
                      magicCounter ++;  
                    } else if(magicCounter==5) {
                      Battleview.report.setText("勇者は,"+ Char.getDamage() +"ダメージを受けた。");
                      battleMusic(counter);
                    
                      Battleview.char1.setText(Char.getName() + "  "+"LV23" + "  " + "HP "+  Char.getHP()+"/200"+"  MP "+Char.getMP());
                      magicCounter ++;  
                    } else{
                      Battleview.report.setText("次の行動を選択してください.");
                      comand = battleComand[0];
                      magicCounter = 0;
                    }
                  } 
                //  DRINK MILK 選択(回復と攻撃力上昇まれにお腹を壊す[固定ダメージを受ける])
                } else{
                  if(view.equals("battle") && magicMeniu[1].getText().equals("▷") && magicCounter > 0 ) {
                    if(Char.getMP() - 40 < 0){
                      Battleview.chooseMagic.setVisible(false);     
                      Battleview.report.setText("MPがたりません！");
                      comand = battleComand[0];
                    }else{
                      if(magicCounter == 1){
                        Battleview.chooseMagic.setVisible(false);
                        Battleview.report.setText(Char.getName() + "は, DRINK MILK を唱えた！");
                        comand = battleComand[4];
                        magicCounter ++;  
                      }else if(magicCounter==2){
                        Battleview.report.setText("目の前に 一杯の牛乳が召喚された！");
                        magicCounter ++; 
                      }else if(magicCounter == 3){
                        Battleview.report.setText(Char.getName() + "は, 躊躇なく飲んだ！");
                        magicCounter ++; 
                      }else if (magicCounter == 4) {
                        // 偶数の時は当たり
                        dais = new Random();
                        if(dais.nextInt(4)%2 == 0){
                          Battleview.report.setText("全身に力が漲ってきた！");
                          Char.getBraveMagicDamage("DRINKMILKUP");
                          Battleview.char1.setText(Char.getName() + "  "+"LV23" + "  " + "HP "+  Char.getHP()+"/200"+"  MP "+Char.getMP());
                          magicCounter ++; 
                        }else{
                          Battleview.report.setText("飲みすぎて腹をくだした！"+ Char.getBraveMagicDamage("DRINKMILKDOWN") +"ダメージを受けた。");
                          Battleview.char1.setText(Char.getName() + "  "+"LV23" + "  " + "HP "+  Char.getHP()+"/200"+"  MP "+Char.getMP());
                          magicCounter ++; 
                        }

                      }else if (magicCounter == 5) {
                        Battleview.report.setText(Char.getEnemyName() + "の攻撃!");
                        battleMusic(counter);
                        magicCounter ++;  
                      } else if(magicCounter==6) {
                        Battleview.report.setText("勇者は,"+ Char.getDamage() +"ダメージを受けた。");
                      
                        Battleview.char1.setText(Char.getName() + "  "+"LV23" + "  " + "HP "+  Char.getHP()+"/200"+"  MP "+Char.getMP());
                        magicCounter ++;  
                      } else {
                        Battleview.report.setText("次の行動を選択してください.");
                        comand = battleComand[0];
                        magicCounter = 0;
                      }
                  }
                }
              }
            }else{

              endBattle();
            }

          }else{
            // GAME OVER 画面に切り替え
            Music.clip.stop();
            final View over = new View();
            changeView(over.gameOver);
            View.endMusic(2);
            view = "gameover";
          }     
                  
        } else if (view.equals("battle") && battleMeniu[2].getText().equals("▷")){
          comand = battleComand[3];
          // 偶数の時は,逃走
          if(Char.getHP() < 0) {
             // GAME OVER 画面に切り替え
             Music.clip.stop();
             final View over = new View();
             changeView(over.gameOver);
             View.endMusic(2);
             view = "gameover";
          }else{
            if(counterforrun == 0){
              dais = new Random();
              if(dais.nextInt(4)%2==0){
                Battleview.report.setText(Char.getName() + "は, うまく逃れた！");
                counterforrun++;
                counter = 4;
                
              }else{
                Battleview.report.setText(Char.getEnemyName() + "に追いつかれた！");
                counterforrun++;
              }
            }else if(counterforrun == 1 && counter==4){
              endBattle();
            }else if(counterforrun == 1){
                Battleview.report.setText(Char.getEnemyName() + "の攻撃!");
                battleMusic(counter);
                counterforrun++;
            }else if(counterforrun==2){
                Battleview.report.setText("勇者は,"+ Char.getDamage() +"ダメージを受けた。");        
                Battleview.char1.setText(Char.getName() + "  "+"LV23" + "  " + "HP "+  Char.getHP()+"/200"+"  MP "+Char.getMP());
                counterforrun++;
  
            }else {
                Battleview.report.setText("次の行動を選択してください.");
                comand = battleComand[0];
                counterforrun=0;
              
            }   
           
          }
        }
        
        break;

        // スペースキーが押されたらパネルを追加
        case KeyEvent.VK_SPACE:
        System.out.println("スペースが押されました");

        if(view.equals("battle") && battleMeniu[1].getText().equals("▷")){
          Battleview.chooseMagic.setVisible(false);
          comand = battleComand[0];
        }else{
          
          if(spaceCounter==0){
            
            view = "statas";
            space.setVisible(true);
            spaceHP.setVisible(true);
  
            spaceCounter++;
          
            
          } else if(spaceCounter==1){
            spaceHP.setVisible(false);
            space.setVisible(false);
            spaceCounter=0;
            view = "maze";
  
          
            
          } else {
            
          }
        }

          
         


        break;
      }
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

        // スペースキーが離された
        case KeyEvent.VK_SPACE:
        System.out.println("スペースが離されました");
       

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
      moveCounter++;
      // ランダムで遭遇するようにする
      if (moveCounter == 4){
        Music.clip.stop();

        view = "battle";
        comand = battleComand[0];

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

              final JLabel way = new JLabel(imageWay);
              jp1.add(way);

              dungeon[i][j]= way;

            } else if (wall[i][j]== 1) {

              final JLabel imagewall = new JLabel(imageWall);
              jp1.add(imagewall);
              dungeon[i][j] = imagewall;

            } else if (wall[i][j]== 2 ) {

              final JLabel uP = new JLabel(imageUP);
              jp1.add(uP);
              dungeon[i][j] = uP;

            } else {

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
      magicCounter=0;
      counter= 0;
      counterforrun=0;
      Char.setenemyHP(Char.getenemymaxHP());
      Char.setBraveatack(Char.getDefaultbraveatack());
      
      // 迷路画面に戻す
      viewMaze();
      changefromBattleview(jp,jp1,space,spaceHP);
      moveCounter = 0;

      final Music main = new Music();
    }


    public void changefromBattleview(final JPanel panel,final JPanel panel1, JPanel space, JPanel spaceHP) {

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
      
      add(spaceHP);
      add(space);

      statas.setText(Char.getName() + "  "+"LV23" + "  " + "HP "+  Char.getHP()+"/200"+"  MP "+ Char.getMP());
      Battleview.report.setText(Char.getEnemyName() +"が飛び出してきた！");
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
