


public class OperationMethod extends MakeMaze  {
	String errMes = "You can not move there.";
	boolean t ;
	
	OperationMethod(int b,int c){
		super(b,c);
	}

	public void moveAction (char n){
        if (n == 'w') {
            if (getWall()[getX()-1][getY()] == 0) {
            	getWall()[getX()][getY()] = 0;
            	setX(getX()-1);
            	getWall()[getX()][getY()] =2;


            } else if (getWall()[getX()-1][getY()] == 3) {
              getWall()[getX()][getY()] = 0;
              setX(getX()-1);
              getWall()[getX()][getY()] =2;
              t = true;

            } else {
              System.out.println(errMes);
            }

          } else if (n == 's') {

            if ( getX()+1 < getMazeSize() && getWall()[getX()+1][getY()] == 0) {
                getWall()[getX()][getY()] = 0; /*現在の座標 */
                setX(getX()+1);
                getWall()[getX()][getY()] = 2; /*移動先の座標 */
            } else {
              System.out.println(errMes);
            }

          } else if (n == 'a'){
            if (getWall()[getX()][getY()-1] == 0) {
                getWall()[getX()][getY()] = 0;
                setY(getY()-1);
                getWall()[getX()][getY()] =2;
            }else {
              System.out.println(errMes);
            }

          } else if (n == 'd') {
            if (getWall()[getX()][getY()+1] == 0) {
              getWall()[getX()][getY()] = 0;
              setY(getY()+1);
              getWall()[getX()][getY()] =2;
            } else {
              System.out.println(errMes);
            }
          }
        challengeLevel(getLevel());
	}

	public void moveActionMaping (char n){
        if (n == 'w') {
        	exchangeWallnumber();
            if (getWall()[getX()-1][getY()] == 0 || getWall()[getX()-1][getY()] == 4) {
            	getWall()[getX()][getY()] = 4; /*現在の座標 */
            	setX(getX()-1);
            	getWall()[getX()][getY()] =2; /*移動先の座標 */

            	/* 横壁であれば（wall[x][y]=1）ならwall[x][y]=1をwall[x][y]=5を代入する。 */


            } else if (getWall()[getX()-1][getY()] == 3) {
              getWall()[getX()][getY()] = 0;
              setX(getX()-1);
              getWall()[getX()][getY()] =2;
              t = true;

            } else {
              System.out.println(errMes);
            }

          } else if (n == 's') {
        	  exchangeWallnumber();

            if ( getX()+1 < getMazeSize() && getWall()[getX()+1][getY()] == 0||getX()+1 < getMazeSize() && getWall()[getX()+1][getY()] == 4) {
                getWall()[getX()][getY()] = 4;
                setX(getX()+1);
                getWall()[getX()][getY()] = 2;
            } else {
              System.out.println(errMes);
            }

          } else if (n == 'a'){
        	  exchangeWallnumber();
            if (getWall()[getX()][getY()-1] == 0||getWall()[getX()][getY()-1] == 4) {
                getWall()[getX()][getY()] = 4;
                setY(getY()-1);
                getWall()[getX()][getY()] =2;
            }else {
              System.out.println(errMes);
            }

          } else if (n == 'd') {
        	 exchangeWallnumber();


            if (getWall()[getX()][getY()+1] == 0 || getWall()[getX()][getY()+1] == 4) {
              getWall()[getX()][getY()] = 4;
              setY(getY()+1);
              getWall()[getX()][getY()] =2;
            } else {
              System.out.println(errMes);
            }
          }
        challengeLevel(getLevel());
	}

	public void exchangeWallnumber() {
		 if(getWall()[getX()-1][getY()-1] == 1 ) {
	   		getWall()[getX()-1][getY()-1] = 5;
	   	}

	   	if( getWall()[getX()-1][getY()+1] == 1) {
	   		getWall()[getX()-1][getY()+1] = 5;
	   	}

		 if( getWall()[getX()-1][getY()] == 1) {
	   		getWall()[getX()-1][getY()] = 5;
		 }

	   	 if(getWall()[getX()][getY()+1] == 1) {
	   		getWall()[getX()][getY()+1] = 5;
	   	}

	   	 if(getWall()[getX()][getY()-1] == 1) {
	   		getWall()[getX()][getY()-1] = 5;
	   	}

	   	 if(getX()+1 <= getMazeSize()-1  && getWall()[getX()+1][getY()+1] == 1) {
	   		getWall()[getX()+1][getY()+1] = 5;
	   	}

	   	if(getX()+1 <= getMazeSize()-1  && getWall()[getX()+1][getY()-1] == 1) {
	   		getWall()[getX()+1][getY()-1] = 5;
	   	}

		if(getX()+1 <= getMazeSize()-1  && getWall()[getX()+1][getY()] == 1) {
	   		getWall()[getX()+1][getY()] = 5;
		}

	   	 if(getX()+2 <= getMazeSize()-1 && getWall()[getX()+2][getY()] == 1) {
	   		getWall()[getX()+2][getY()-1] = 5;
	   	}
	}


}
