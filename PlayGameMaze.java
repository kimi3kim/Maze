import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PlayGameMaze {

  public static void main(String[] args) {
    int[][] wall ;

    wall = new int[][]{
      {1,1,1,3,1},
      {1,0,0,0,1},
      {1,0,1,1,1},
      {1,0,0,1,1},
      {1,2,1,1,1},
    };

//		フレームを作成する。

		JFrame game = new JFrame("");
		//　フレーム名
		game.setTitle("Maze");

		//フレームの大きさを決める。setBounds(x座標, y座標, width, height)
		game.setBounds(350, 150, 800, 600);

//	 	 ✖ボタンをクリックした際にプログラムを強制終了
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//		フレームに様々な物を追加するよ用のパネル
		JPanel jp = new JPanel();
		game.add(jp);

//		文字用ラベルの作成
		JLabel label1 = new JLabel("しりとり");
		label1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 50));
		jp.add(label1);

//		画像つきラベルを作成する。画像の中身を分割して行く作業。
		File file = new File("Denzi091024-1.png"); //ファイルを開く
		try {
			BufferedImage imageAll = ImageIO.read(file); //画像全体を読み込む
      BufferedImage imageWall = imageAll.getSubimage(65,32,32,32);  //画像の切り出し
      BufferedImage imageWay = imageAll.getSubimage(220,128,32,32); //画像の切り出し

			ImageIcon imageWalljlabel = new ImageIcon( imageWall ); //JLabel用に型変換
      ImageIcon imageWayjlabel = new ImageIcon( imageWay ); //JLabel用に型変換

      JLabel image1 = new JLabel(imageWalljlabel); //画像部品を作成
      JLabel image2 = new JLabel(imageWayjlabel); //画像部品を作成

      image1.setSize(544, 256); //画像サイズの設定(表示する画像の実サイズを入力)
      image2.setSize(544, 256); //画像サイズの設定(表示する画像の実サイズを入力)

      jp.add(image1);          //画像を貼り付け
      jp.add(image2);

      game.setVisible(true);


		} catch (IOException e) {

			e.printStackTrace();
		}




	}

}
