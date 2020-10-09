import javax.swing.ImageIcon;


class Monster extends Char {
    private String enemyName;  
    private int enemyHp;
    private int MP;
    private int enemymaxHP; 
    private int enemyAttack;
    


// String モンスター名、int モンスターのHP、int モンスター攻撃力
    Monster(String a , int b , int c){
        super( a, b, c);
        this.enemyName = super.getEnemyname();
        this.enemyHp = super.getEnemyHp();
        this.enemymaxHP =super.getEnemymaxHp();
        this.enemyAttack = super.getEnemyAttack();

    }

    public int getEnemyHp(){
        return enemyHp;
    }
    public int getEnemyAttack(){
        return enemyAttack;
    }
}
