import javax.swing.JLabel;
import java.util.Random;


public class Char{
  static String  name;
  static String  enemyName;
  private static int HP;
  private static int enemyHP;

  // final static int  maxHP;
  static JLabel[] brave;
  static int braveatack;
  static int atack;
  static JLabel[] enemy;
  JLabel bravemove ;
  private static int damage;
  private static int bravedamage;


// 勇者
  Char(String name){
    this.name = name;
    HP = 200;
    // maxHP = HP;

    // brave = new JLabel[1];
    // bravemove = new JLabel(this.name + "は、ダメージを受けた。");
    // brave[1] = bravemove;
    braveatack = 40;

  }

//ニンニクスライム
  Char(String name, int a/*int b*/){
    enemyName = name;
    enemyHP = a;
    // maxHP = b;
    atack = 25;

    enemy = new JLabel[1];
    JLabel enemymove = new JLabel(this.name + "の攻撃");

    // enemy[1] = enemymove;
  }

  public static int getHP(){
    return HP;
  }

  public static int getenemyHP(){
    return enemyHP;
  }



  public static int getDamage(){
     Random rnd2 = new Random();
     damage = atack + rnd2.nextInt(10);

     //Playerへのダメージ
     HP = HP - damage;

     return damage;
  }
  public static int getBraveDamage(){
     Random rnd1 = new Random();
     bravedamage = braveatack + rnd1.nextInt(10);

     //Playerへのダメージ
     enemyHP = enemyHP - bravedamage;

     return bravedamage;
  }

}
