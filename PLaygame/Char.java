import javax.swing.JLabel;
import java.util.Random;


 class Char{
  private static String  name;
  private static String  enemyName;
  private static int HP;
  private static int mP;
  private static int enemyHP;
  private static int enemymaxHP;

  // final static int  maxHP;
  static JLabel[] brave;
  private static int braveatack;
 
  static int atack;
  static JLabel[] enemy;
  JLabel bravemove ;
  private static int damage;
  private static int bravedamage;
  static Random rnd1;
  private static int defaultbraveatack ;



// 勇者
  Char(String a){
    name = a;
    HP = 200;
    mP = 200;
    // maxHP = HP;
    braveatack = 50;
    defaultbraveatack = 50;

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

  public static int getMP(){
    return mP;
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

  public static int getDefaultbraveatack(){
    return defaultbraveatack ;
  }

  public static void setBraveatack(int m){
    braveatack = m ;
  }


  public static int getDamage(){
     rnd1 = new Random();
     damage = atack + rnd1.nextInt(10);

     //Playerへのダメージ
     HP = HP - damage;
     if(HP < 0){
       HP = 0;
     }
     return damage;
  }

  public static int getBraveDamage(){
     rnd1 = new Random();
     bravedamage = braveatack + rnd1.nextInt(10);

     //Enemyへのダメージ
     enemyHP = enemyHP - bravedamage;

     return bravedamage;
  }

  public static int getBraveMagicDamage(String a){
    rnd1 = new Random();
    if(a.equals("BREATHCARE")){
      if (mP - 30 >= 0){
      bravedamage = 70 + rnd1.nextInt(30);
      
      //Enemyへのダメージ
      enemyHP = enemyHP - bravedamage;
      mP = mP - 30;
      }
    } else if (a.equals("DRINKMILKUP")){
      if (mP - 40 >= 0){
        rnd1 = new Random();
        int heal = 40 + rnd1.nextInt(15);
         
        //Playerの回復
        HP = HP + heal;
        if(HP>200){
          HP = 200;
        }
        mP = mP - 40;
        braveatack = braveatack + rnd1.nextInt(65)+25;

      } else{

      }
    } else if (a.equals("DRINKMILKDOWN")){
      if (mP - 40 >= 0){
        rnd1 = new Random();
        
        bravedamage = 30 + rnd1.nextInt(20);
        HP = HP - bravedamage;

        if(HP < 0){
          HP = 0;
        }
        mP = mP - 40;
      }

    }else{
    }
    
    return bravedamage;
  }

  public static void getHeal(){
    if (mP - 20 >= 0){
      rnd1 = new Random();
      int healpoint = 20 + rnd1.nextInt(15);
       
      //Playerの回復
      HP = HP + healpoint;
      if(HP>200){
        HP = 200;
      }
      mP = mP - 20;
      
    }else{
      
    }
 }

}
