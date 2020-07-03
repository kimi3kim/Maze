import java.io.*;


public class Maze1 {
  public static void main(String[] args){
    String n ;
    String a ;
    char move;
    char mazeNumber;
    int mazeSize = 0 ;
    int x;
    int y = 1;

    /*迷路を配列で表現している。 0：道、1：壁 */
    int[][] wall ;
    wall = new int[][]{
      {1,1,1,3,1},
      {1,0,0,0,1},
      {1,0,1,1,1},
      {1,0,0,1,1},
      {1,2,1,1,1},
    };

    int[][] wall1;
    wall1 = new int [][]{
      {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1},
      {1,0,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1,0,0,1},
      {1,0,1,1,1,1,1,0,1,1,0,1,0,1,1,0,1,0,1,1},
      {1,0,0,0,0,1,1,0,1,1,0,0,0,0,1,0,0,0,1,1},
      {1,0,1,0,1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1},
      {1,0,1,0,1,1,1,0,1,1,1,1,1,0,0,0,1,1,1,1},
      {1,0,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1},
      {1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,0,0,0,0,1},
      {1,0,1,1,0,1,1,0,0,0,1,1,1,1,1,0,1,1,0,1},
      {1,0,0,0,0,1,1,0,1,1,0,0,0,0,1,0,1,1,0,1},
      {1,0,1,1,0,1,1,0,1,1,0,1,1,0,1,0,1,0,0,1},
      {1,0,1,1,0,1,1,1,1,1,0,0,1,0,1,1,1,0,1,1},
      {1,1,1,0,0,0,0,0,0,1,1,1,1,0,1,0,1,0,1,1},
      {1,0,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,0,0,1},
      {1,0,1,1,0,1,0,1,1,0,0,0,0,0,1,0,1,1,0,1},
      {1,0,1,0,0,1,0,1,1,0,1,1,0,0,1,0,1,1,0,1},
      {1,0,1,0,1,1,0,1,0,0,1,1,1,1,1,0,1,1,0,1},
      {1,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1},
      {1,0,0,0,1,1,0,0,0,0,1,1,1,0,1,1,1,1,0,1},
      {1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    };
    int [][] wallgo = new int[mazeSize][mazeSize];


    System.out.println("迷路のレベルを選択してください。");

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    try {
      /*     迷路の選択         */
      System.out.println("1"+":初級、"+"2"+":中級、");
      System.out.println("1,2の数字を選択してください。");

      while(true) {
        a = reader.readLine();
        if (a.length() > 0) {
          mazeNumber = a.charAt(0);

        } else {
          /* Enterキーのみを押した場合   */
          mazeNumber = 'l';
        }
          switch (mazeNumber) {
            case '1':
            wallgo  = wall;
            mazeSize = wall.length;
            x = mazeSize - 1;
            break;

            case '2':
            wallgo = wall1;
            mazeSize = wall1.length;
            x = mazeSize - 1;
            break;

            default:
            System.out.println("1,2を入力してください。");
            continue;
          }

        /*     迷路の選択の項目は終了       */

        /*     迷路の表示と操作の項目      */
        System.out.println("操作方法は、上: w + Enterキー, 下: s + Enterキー ,右: d + Enterキー ,左: a + Enterキー です。");
        System.out.println("");
        System.out.println("Game Start!");
        System.out.println("");

        String errMes = "You can not move there.";
        long start = System.currentTimeMillis(), end;

        printMaze(wallgo);/* 迷路を表示するためのメソッド */

        while (true){
          System.out.println("上: w , 下: s ,右: d ,左: a ");
           n = reader.readLine();
          /* 一文字目を得るため。間違って入力しなかった場合の対処 */
          if (n.length() > 0) {
            move = n.charAt(0);

          }else{
            move = 'l';
            System.out.println(errMes);
          }

          /* **を動かすためのプログラム  */
          if (move == 'w') {
            if (wallgo[x-1][y] == 0) {
              wallgo[x][y] = 0;
              x = x-1;
              wallgo[x][y] =2;
            } else if (wallgo[x-1][y] == 3) {
              wallgo[x][y] = 0;
              x = x-1;
              wallgo[x][y] =2;
            } else {
              System.out.println(errMes);
            }

          } else if (move == 's') {

            if ( x+1 < mazeSize && wallgo[x+1][y] == 0) {
                wallgo[x][y] = 0;
                x = x+1;
                wallgo[x][y] = 2;
            } else {
              System.out.println(errMes);
            }

          } else if (move == 'a'){
            if (wallgo[x][y-1] == 0) {
                wallgo[x][y] = 0;
                y = y-1;
                wallgo[x][y] =2;
            }else {
              System.out.println(errMes);
            }

          } else if (move == 'd') {
            if (wallgo[x][y+1] == 0) {
              wallgo[x][y] = 0;
              y = y+1;
              wallgo[x][y] =2;
            } else {
              System.out.println(errMes);
            }
          }

          printMaze(wallgo);

          /*  ゴールしたときの表示  */
          if (wallgo[0][mazeSize - 2] == 2 ) {
    				end = System.currentTimeMillis();
    				printRezult((end - start) / 1000);
    				break;
    			}
        }
      }

    } catch (IOException e){
        System.out.println("e");
    } catch (NumberFormatException e){
        System.out.println("入力ミス");
    }
  }

    // 迷路を表示するメソッド
    public static void printMaze(int [][] wallgo) {
      for (int i = 0; i <wallgo.length ;i++ ) {
        for(int j = 0; j<wallgo.length;j++) {
          if (wallgo[i][j]==0) {
            System.out.print("  ");
          } else if (wallgo[i][j]== 1) {
            System.out.print("[]");
          } else if (wallgo[i][j]== 2 ) {
            System.out.print("**");
          } else {
            System.out.print("++");
          }
        }
        System.out.println("");
      }
      System.out.println("");
    }

  // 結果を表示するメソッド
	public static void printRezult(long secondTime) {
		System.out.println();
		System.out.println("+-+-+-+-+-+-+-+-+-+");
		System.out.println("|c|o|n|g|r|a|t|s|!|");
		System.out.println("+-+-+-+-+-+-+-+-+-+");
		System.out.println();

		System.out.println("Your time is " + secondTime + " seconds.");
		System.out.println();
    System.exit(0);
	}
}
