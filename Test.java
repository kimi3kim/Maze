
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	public static void main(String[] args) {
		int mazeSize;
		long printRezult;
		int levelChoice;

		 String errMes = "You can not move there.";

		System.out.println("迷路を作成しクリアしてください。");
		System.out.println("まず4以上かつ奇数の数字を入力してください。");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
		try {
		       String a = reader.readLine();
		        if (a.length() > 0) {
		        	mazeSize = Integer.parseInt(a);
		        	if (mazeSize > 4 ) {
		        		if(mazeSize % 2 != 0) {
		        			break;
		        		}else {
			        		System.out.println("4以上の数値(奇数)を入力してください。");
		        		}
		        	}else {
		        		System.out.println("4以上の数値(奇数)を入力してください。");
		        	}
		        } else {
		          /* Enterキーのみを押した場合   */
		        	mazeSize = 'l';
		        }

		        //なんらかの入出力例外の発生を通知するシグナルを発生させます。
		        //このクラスは、入出力処理の失敗、または割り込みの発生によって生成される例外の汎用クラスです。
			} catch (IOException e){
				System.out.println("e");

			//文字列を数値型に変換しようとしたとき、文字列の形式が正しくない場合にスローされます。
			} catch (NumberFormatException e){
			System.out.println("入力ミスです。4以上の数字(奇数)を入力してください。");
			}
		}
		System.out.println("迷路の難易度を選択してください。");

		System.out.println("\n"+"1:初級レベル");
		System.out.println("初級レベルは,完成された迷路が表示されます。");

		System.out.println("\n"+"2:中級レベル");
		System.out.println("中級レベルは,動かすキャラの周囲１マスが表示されます。");
		System.out.println("また,動かすとマッピングされます。");

		System.out.println("\n"+"3:上級レベル");
		System.out.println("上級レベルは,動かすキャラの周囲１マスのみ表示します。");

		System.out.println("\n"+"4:超級レベル");
		System.out.println("チャレンジしてみてください。");

		while(true) {
			System.out.println("\n"+"1から4の数字を入力してください。");


			try {
				String b = reader.readLine();
				if (b.length() > 0 && b.length() < 5)  {
					 levelChoice = Integer.parseInt(b);
					if (levelChoice == 1|| levelChoice == 2|| levelChoice == 3|| levelChoice == 4) {
						break;
					}else {
						System.out.println("1から4の数字を入力してください");
					}
				} else {
					levelChoice = 'l';

				}

			} catch (IOException e){
				System.out.println("e");

				//文字列を数値型に変換しようとしたとき、文字列の形式が正しくない場合にスローされます。
			} catch (NumberFormatException e){
				System.out.println("入力ミスです。1から4の数字を入力してください。");
			}
		}


//		迷路を作成るするクラスをインスタンス化
//		操作方法プログラムクラスをインスタンス化
		OperationMethod r1 = new OperationMethod(mazeSize,levelChoice);
//		MakeMaze r1 = new MakeMaze(mazeSize);

		System.out.println("『**』がスタート地点です。");
		System.out.println("『**』を操作し,ゴールを目指してください。");
		System.out.println("");
		System.out.println("操作方法は、上: w + Enterキー, 下: s + Enterキー ,右: d + Enterキー ,左: a + Enterキー です。");
		System.out.println("");
		System.out.println("Game Start!");
		System.out.println("");

		long start = System.currentTimeMillis(), end;

		while (true){
			char move ;
			try {
				System.out.println("上: w , 下: s ,右: d ,左: a ");
				String n = reader.readLine();

				/* 一文字目を得るため。間違って入力しなかった場合の対処 */
				if (n.length() > 0) {
					move = n.charAt(0);
				}else{
					move = 'l';
					System.out.println(errMes);
				}

				if (levelChoice == '2') {
					r1.moveActionMaping(move
							);
				}else {
					r1.moveAction(move);
				}

				if(r1.t == true) {
					end = System.currentTimeMillis();
    				printRezult = ((end - start) / 1000);
					break;
				}
			}catch (IOException e){
				System.out.println("e");

			}
		}
		System.out.println();
		System.out.println("+-+-+-+-+-+-+-+-+-+");
		System.out.println("|c|o|n|g|r|a|t|s|!|");
		System.out.println("+-+-+-+-+-+-+-+-+-+");
		System.out.println();

		System.out.println("Your time is " + printRezult + " seconds.");
		System.out.println();

	}
}
