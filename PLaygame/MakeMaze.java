

import java.util.Random;

public class MakeMaze {
	private int[][] wall ;
	private int mazeSize ;
	private int level ;
	private int x ;
	private int y ;
	private boolean t ;
	private boolean s ;


	//	任意の数字を入力したら、入力したサイズの配列を定義する。
	//	配列に１を代入していく
	 public MakeMaze(int n) {
		 mazeSize = n;
		 wall = new int[mazeSize][mazeSize];
		 for (int i = 0; i < wall.length; i++) {
			for (int j = 0; j < wall.length; j++) {
				wall[i][j] = 1;
			}
		}
		 makerfPoint(); /* 迷路を作成する開始点の作成  */
		 makeRode(); /*  迷路を作成  */
		 findnewWay();
		 makeStartGoal();


		 // challengeLevel(level);
	 }


//	 スタート地点とゴール地点を決める。
	 public void makeStartGoal() {
		 Random rnd = new Random();
//	 ゴール地点は, wall[0][1からmazeSize-2]

		 x = 0;
		 y = 0;
		 while(y%2==0) {
			 y = rnd.nextInt(mazeSize-3)+1;
		 }
		 wall[x][y] = 3;

//	 スタート地点は, wall[maiSize][1からmazeSize-2]
		 x = mazeSize - 1;
		 y = 0;
		 while(y%2 == 0) {
			 y = rnd.nextInt(mazeSize-3)+1;
		 }
		 wall[x][y] = 2;
	 }


//迷路の道（数字の０）をランダムに入れたい。
//考え方：上下左右のいずれかの方向(ランダムに選択)に２マス先を見て、そこが通路でなければ道を延ばす。（1を０に交換する）
//道を延ばす事ができなくなれば、この時既にある道からランダムに点
//(但し、Ｘ座標とＹ座標が偶数の点）を選んで道を延ばします。

	 public void makerfPoint() {
//		 スタート地点固定
		 x =x+1;
		 y =y+1;
		 wall[x][y] = 0;

	 }

	 public void makeRode() {
		 Random rnd2 = new Random();
		 while(true) {

//			 printMaze();/* 迷路の作成過程を表示したい場合に 『//』を外してください */
			 int z = rnd2.nextInt(4);
//			 System.out.println(z);

			 switch(z) {
			 //上
			 case 0:
				 if (x-2 >= 0 && wall[x-2][y]==1 ) {
					 wall[x-1][y] = 0;
					 wall[x-2][y] = 0;
					 x = x-2;
					 continue;
				 } else {
					 if(checkWay() == false) {
						 break;
					 }else {
						 continue;
					 }
				 }

			//下
			 case 1:
				 if (x+2 <= mazeSize-1  && wall[x+2][y]==1 ) {
					 wall[x+1][y] = 0;
					 wall[x+2][y] = 0;
					 x = x+2;
					 continue;
				 } else {
					 if(checkWay() == false) {
						 break;
					 } else {
						 continue;
					 }
				 }

			//左
			 case 2:
				 if (y-2 >= 0  && wall[x][y-2]==1 ) {
					 wall[x][y-1] = 0;
					 wall[x][y-2] = 0;
					 y = y-2;
					 continue;
				 } else {
					 if(checkWay() == false) {
						 break;
					 } else {
						 continue;
					 }
				 }

			//右
			 case 3:
				 if (y+2 <= mazeSize-1  && wall[x][y+2]==1 ) {
					 wall[x][y+1] = 0;
					 wall[x][y+2] = 0;
					 y = y+2;
					 continue;
				 } else {
					 if(checkWay() == false) {
						 break;
					 } else {
						 continue;
					 }
				 }

			 default:
				 break;
			 }
			 break;
		 }
	 }

	 //	 既に道（０）であるところをランダムで選ぶ
	 public void findnewWay() {
		 //配列wall[][]を一つ一つ確認していく。外枠以外のかべに当たった時に
		 while(true) {
//			 if(checkWaysec()==false) {
				 for(int i = 1; i < mazeSize - 1; i++ ) {
					 for(int j = 1; j < mazeSize - 1; j++ ) {
						 if(i%2!=0&& j%2!=0) {
							 if(wall[i][j] == 0 ) {
								 x = i;
								 y= j;
								 if(checkWaysec()==true) {
									 makeRodesc();
									 i--;
								 }
							 }
						 }
					 }
				 }
				 break;
			 }
		 }
//	 }

//findnewWay()で道が作成せきると判断できた場合、ランダムで道を選択して新たに道を作る。
	 public void makeRodesc() {
		 Random rxs = new Random();
		 while(true) {
			 x = rxs.nextInt(mazeSize-2)+1;
			 y = rxs.nextInt(mazeSize-2)+1;
			 if(x%2 != 0 && y%2 != 0) {
				 if(checkWaysec()==true) {
					 if( wall[x][y] == 0) {
						 makeRode();
						 break;
					 } else {
						 continue;
					 }
				 }
				 continue;
			 }
			 continue;
		 }
	 }

	 public boolean checkWay(){
		 while(true) {
			 try {
				 if(y+2<= mazeSize-1 && wall[x][y+2]==1) {
						 s = true;
				 }else if(x+2 <= mazeSize-1 && wall[x+2][y]==1) {
						 s = true;
				 } else if(x-2 >= 0 && wall[x-2][y]==1) {
						 s = true;
				 } else {
					 s = false;
				 }
			 } catch (ArrayIndexOutOfBoundsException e) {
				 e.printStackTrace();
				 s = false;
			 }
			 break;
		 }
			 return s;
	}

	 public boolean checkWaysec(){
		 try {
			 if(x+2 <= mazeSize-1 && wall[x+2][y]==1) {
					 t = true;
			 } else if(x-2 >= 0 && wall[x-2][y]==1) {
					 t = true;

			 }else if(y+2 <= mazeSize-1 && wall[x][y+2]==1) {
					 t = true;

			 }else if(y-2 >= 0 && wall[x][y-2]==1) {
					 t = true;
			 }else {
				 t = false;
			 }
		 } catch (ArrayIndexOutOfBoundsException e) {
			 e.printStackTrace();
			 t = false;
		 }

		 return t;
	 }

//迷路の描写
	 public void printMaze() {
		 for (int i = 0; i <wall.length ;i++ ) {
			 for(int j = 0; j<wall.length;j++) {
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



	public int[][] getWall() {
		return wall;
	}

	public void setWall(int[][] wall) {
		this.wall = wall;
	}

	public int getMazeSize() {
		return mazeSize;
	}

	public void setMazeSize(int mazeSize) {
		this.mazeSize = mazeSize;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}

}
