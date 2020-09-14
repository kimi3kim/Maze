
import java.awt.image.BufferedImage;
import java.awt.Color;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PlayGameMaze  {
  public static void main(String[] args) {

    int[][] wall ;
    JLabel[][] dungeon;

    // mazeSizeは奇数
    int mazeSize = 15;



    MakeMaze maze = new MakeMaze(mazeSize);
    wall = maze.getWall();



    dungeon = new JLabel[mazeSize][mazeSize];

//		フレームに様々な物を追加するよ用のパネル
		JPanel jp = new JPanel();
    //背景を黒くする。
    jp.setBackground(Color.BLACK);

    JPanel jp2 = new JPanel();
    
    jp2.setBounds(100, 10, 39  * mazeSize, 45 * mazeSize);
    jp2.setBackground(Color.BLACK);

//		画像つきラベルを作成する。画像の中身を分割して行く作業。
		File file = new File("png/Denzi091024-1.png"); //ファイルを開く
		try {
			BufferedImage imageAll = ImageIO.read(file); //画像全体を読み込む
      BufferedImage imageWall = imageAll.getSubimage(65,32,32,32);  //画像の切り出し
      BufferedImage imageWay = imageAll.getSubimage(220,128,32,32); //画像の切り出し

			ImageIcon imageWalljlabel = new ImageIcon( imageWall ); //JLabel用に型変換
      ImageIcon imageWayjlabel = new ImageIcon( imageWay ); //JLabel用に型変換
      ImageIcon imageUPlabel = new ImageIcon( "png/Denzi091024-1UP.png" );
      ImageIcon imageDOWNlabel = new ImageIcon( "png/Denzi091024-1DOWN.png" );
      ImageIcon imageRIGHTlabel = new ImageIcon( "png/Denzi091024-1REIGHT.png" );
      ImageIcon imageLEFTlabel = new ImageIcon( "png/Denzi091024-LEFT.png" );

      JLabel imagewall = new JLabel(imageWalljlabel);
      JLabel way = new JLabel(imageWayjlabel);
      JLabel uP = new JLabel(imageUPlabel);


      for (int i = 0; i < maze.getWall().length ;i++ ) {
        for(int j = 0; j< maze.getWall().length;j++) {
          if (wall[i][j]== 0) {
            System.out.print("  ");
            way = new JLabel(imageWayjlabel);
            jp2.add(way);

            dungeon[i][j]= way;

          } else if (wall[i][j]== 1) {
            System.out.print("[]");
            imagewall = new JLabel(imageWalljlabel);
            jp2.add(imagewall);
            dungeon[i][j] = imagewall;

          } else if (wall[i][j]== 2 ) {
            System.out.print("**");
            uP = new JLabel(imageUPlabel);
            jp2.add(uP);
            dungeon[i][j] = uP;

          } else {
            System.out.print("  ");
            

              way = new JLabel(imageWayjlabel);
              jp2.add(way);

              dungeon[i][j]= way;
          }
        }
        System.out.println("");
      }
      System.out.println("");

      //		フレームを作成する。
      JFrame game = new KeyOperation( mazeSize,wall , dungeon ,jp2, jp ,imageWalljlabel, imageWayjlabel,
        imageUPlabel, imageDOWNlabel, imageRIGHTlabel,imageLEFTlabel, maze.getX(),maze.getY());

      //フレーム名
      game.setTitle("Maze");

      //フレームの大きさを決める。setBounds(x座標, y座標, width, height)
      game.setBounds(350, 150, 800, 600);

      //	 	 ✖ボタンをクリックした際にプログラムを強制終了
      game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      


      game.add(jp2);
      game.add(jp);




      game.setVisible(true);
      Music  main   = new Music();


    } catch (IOException e) {
      e.printStackTrace();
    }



  }
}
