import java.util.Random;

 class Char{
  private static String  name;
  private static String  enemyName;
  private static int HP;
  private static int maxHP;
  private static int mP;
  private static int enemyHP;
  private static int enemymaxHP;

  private String enemyname;
  private int enemyHp;
  private int enemyAttack;
  private int enemyMaxHp;

  // final static int  maxHP;
  
  private static int braveatack;
 
  static int atack;
 
  
  private static int damage;
  private static int bravedamage;
  static Random rnd1;
  private static int defaultbraveatack ;

// 勇者
  Char(String a){
    name = a;
    HP = 350;
    mP = 250;
    maxHP = HP;
    braveatack = 60;//50ぐらいが良い
    defaultbraveatack = 60;

  }

//モンスター
  Char(String name, int a,int c){
    enemyName = name;
    enemyHP = a;
    enemymaxHP = a;
    atack = c;

    // 継承クラスのため
    enemyname = name;
    enemyHp = a;
    enemyMaxHp= a;
    enemyAttack = c;

  }
  public String getEnemyname(){
    return enemyname;
  }

  public int getEnemyHp(){
    return enemyHp;
  }
  public int getEnemymaxHp(){
    return enemyMaxHp;
  }
  public int getEnemyAttack(){
    return enemyAttack;
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
  public static int getmaxHP(){
    return maxHP;
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
      bravedamage = 80 + rnd1.nextInt(30)+10;
      
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
        if(HP>maxHP){
          HP = maxHP;
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
      if(HP>maxHP){
        HP = maxHP;
      }
      mP = mP - 20;
      
    }else{
      
    }
 }

}
