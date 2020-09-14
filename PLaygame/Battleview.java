
import java.awt.Font;

import java.awt.Color;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
// import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



 class Battleview /*extends JFrame*/  {

  static JPanel battle;
  static JLabel report;
  static JLabel char1;

  static int counter;

  JLabel back;

  Battleview() {
      // ラベルに文字を表示する。
      Char test = new Char ("勇者　PLAYER1");

      char1 = new JLabel(Char.getName() + "  "+"LV23" + "  " + "HP " + "200/"+Char.getHP());
      char1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
      char1.setForeground(Color.WHITE);

      // // イメージ画像を貼り付けるラベル（敵の画像をつける予定）
      ImageIcon imageBack = new ImageIcon( "png/monster1.png" );
      JLabel back = new JLabel(imageBack);
      // 画像のサイズ指定
      back.setBounds(110,30,259,194);
      //

      // 文字を表示させる。(モンスター名、HP,攻撃力)
      Char test2 = new Char("ニンニクスライム",100,15);
      report = new JLabel( Char.getEnemyName() +"が飛び出してきた！");
      report.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 24));
      report.setForeground(Color.WHITE);
      report.setBounds(0, 800, 800, 100);


      // Panelの作成
      battle = new JPanel();
      battle.setLayout(new BorderLayout());

      battle.setBackground(Color.BLACK);

      // 各LabelをPanel
      battle.add(char1,BorderLayout.PAGE_START);
      battle.add(back,BorderLayout.CENTER);
      // battle.add(report,BorderLayout.PAGE_END);
      battle.add(report);
  }

}
