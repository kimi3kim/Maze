
import java.awt.Font;

import java.awt.Color;

// import java.awt.BorderLayout;

import javax.swing.ImageIcon;
// import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;




 class Battleview /*extends JFrame*/  {

  static JPanel battle;
  static JLabel report;
  static JLabel char1;
  static JPanel forChar1; //キャラ表示パネル
  static JPanel forback; //モンスター表示パネル
  static JPanel forreport; //文章の表示
  static JPanel battlemenu; //for battle menue
  static JPanel chooseMagic; // if choosed magic
  static LineBorder border;
  JLabel magicArrow;
  JLabel attackArrow;
  JLabel runawayArrow ;
  JLabel brathArrow;
  JLabel drinkArrow;


  static int counter;
  static String[] arrow;

  JLabel back;

  Battleview() {
    arrow = new String[2];
    arrow[0] = "▷"; 
    arrow[1] = " ";


    // BorderLineを宣言
      border = new LineBorder(Color.WHITE, 2, true);


      forChar1  = new JPanel();
      forChar1.setBounds(0, 0,800,50);
      forChar1.setBackground(Color.BLACK);
      forChar1.setBorder(border);
      

      // ラベルに文字を表示する。
      Char test = new Char ("勇者　PLAYER1");

      char1 = new JLabel(Char.getName() + "  "+"LV23" + "  " + "HP " + Char.getHP()+"/200"+"  MP "+ Char.getMP());
      char1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
      char1.setForeground(Color.WHITE);

      forChar1.add(char1);

      // // イメージ画像を貼り付けるラベル（敵の画像をつける予定）
      forback  = new JPanel();
      forback.setLayout(null);
      forback.setBounds(0, 50,800,300);
      forback.setBackground(Color.BLACK);
      forback.setBorder(border);

      ImageIcon imageBack = new ImageIcon( "png/monster1.png" );
      JLabel back = new JLabel(imageBack);
      // 画像のサイズ指定
      back.setBounds(200,50,259,194);
      forback.add(back);


      // 文字を表示させる。(モンスター名、HP,攻撃力)
      forreport  = new JPanel();
      forreport.setBounds(200,350,600,220);
      forreport.setBackground(Color.BLACK);
      forreport.setBorder(border);

      Char test2 = new Char("ニンニクスライム",100,15);
      report = new JLabel( Char.getEnemyName() +"が飛び出してきた！");
      report.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
      report.setForeground(Color.WHITE);
      report.setBounds(0, 800, 800, 100);

      forreport.add(report);

      // Char boss1 = new Char("キングニンニクスライム",300,45);




    // Battle menu Panel
    battlemenu = new JPanel(); 
    battlemenu.setLayout(null);
    battlemenu.setBounds(0, 350,200,220);
    battlemenu.setBackground(Color.BLACK);
    battlemenu.setBorder(border);

    JLabel attack = new JLabel("攻撃");
    attack.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    attack.setForeground(Color.WHITE);
    attack.setBounds(40, 20, 200, 24);

    attackArrow = new JLabel(arrow[0]);
    attackArrow.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    attackArrow.setForeground(Color.WHITE);
    attackArrow.setBounds(0, 20, 24, 24);




    
    JLabel magic = new JLabel("呪文");
    magic.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    magic.setForeground(Color.WHITE);
    magic.setBounds(40, 80, 200, 24);

    magicArrow = new JLabel(arrow[1]);
    magicArrow.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    magicArrow.setForeground(Color.WHITE);
    magicArrow.setBounds(0, 80, 24, 24);


    JLabel runaway = new JLabel("逃げる");
    runaway.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    runaway.setForeground(Color.WHITE);
    runaway.setBounds(40, 140, 200, 24);

    runawayArrow = new JLabel(arrow[1]);
    runawayArrow.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    runawayArrow.setForeground(Color.WHITE);
    runawayArrow.setBounds(0, 140, 24, 24);


    battlemenu.add(attack);
    battlemenu.add(attackArrow);
    battlemenu.add(magic);
    battlemenu.add(magicArrow);
    battlemenu.add(runaway);
    battlemenu.add(runawayArrow);

    // 呪文を選択したときのパネl
    chooseMagic = new JPanel();
    chooseMagic .setLayout(null); 
    chooseMagic .setBounds(100, 250,300,250);
    chooseMagic .setBackground(Color.BLACK);
    chooseMagic .setBorder(border);

    // 呪文の記載用のlabel
    JLabel breathCare = new JLabel("BREATH CARE");
    breathCare.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    breathCare.setForeground(Color.WHITE);
    breathCare.setBounds(40, 20, 200, 24);

    brathArrow = new JLabel(arrow[0]);
    brathArrow.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    brathArrow.setForeground(Color.WHITE);
    brathArrow.setBounds(0, 20, 24, 24);



    JLabel drinkMilk = new JLabel("DRINK MILK");
    drinkMilk.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    drinkMilk.setForeground(Color.WHITE);
    drinkMilk.setBounds(40, 80, 200, 24);

    drinkArrow = new JLabel(arrow[1]);
    drinkArrow.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
    drinkArrow.setForeground(Color.WHITE);
    drinkArrow.setBounds(0, 80, 24, 24);
    

    
    chooseMagic.add(breathCare);
    chooseMagic.add(brathArrow);
    chooseMagic.add(drinkMilk);
    chooseMagic.add(drinkArrow);
    

      // Panelの作成
      battle = new JPanel();
     
      battle.setLayout(null);
      battle.setBackground(Color.BLACK);
      
      battle.add(chooseMagic);
      
      battle.add(battlemenu);
      battle.add(forback);
      battle.add(forreport);
      battle.add(forChar1);
      
      
      chooseMagic.setVisible(false);
      


      // 各LabelをPanel
      // battle.add(char1,BorderLayout.PAGE_START);
      // battle.add(back,BorderLayout.CENTER);
      // battle.add(report,BorderLayout.PAGE_END);
      // battle.add(report);
  }

  
   

  



}
