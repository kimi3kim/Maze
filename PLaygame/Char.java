import javax.swing.JLabel;
import java.util.Random;


 class Char{
  private static String  name;
  private static String  enemyName;
  private static int HP;
  private static int enemyHP;
  private static int enemymaxHP;

  // final static int  maxHP;
  static JLabel[] brave;
  static int braveatack;
  static int atack;
  static JLabel[] enemy;
  JLabel bravemove ;
  private static int damage;
  private static int bravedamage;


// 勇者
  Char(String a){
    name = a;
    HP = 200;
    // maxHP = HP;
    braveatack = 100;

  }

//モンスター
  Char(String name, int a,int c){
    enemyName = name;
    enemyHP = a;
    enemymaxHP = a;
    atack = c;
  }
  
  public static String getName(){
    return name;
  }

  public static String getEnemyName(){
    return enemyName;
  }

  

  public static int getHP(){
    return HP;
  }

  public static int getenemyHP(){
    return enemyHP;
  }

  public static int getenemymaxHP(){
    return enemymaxHP;
  }

  public static void setenemyHP(int n){
    enemyHP = n;
  }

  public static int getDamage(){
     Random rnd2 = new Random();
     damage = atack + rnd2.nextInt(10);

     //Playerへのダメージ
     HP = HP - damage;
     if(HP < 0){
       HP = 0;

     }

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
