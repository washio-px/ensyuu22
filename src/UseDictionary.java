
import java.util.Scanner;

public class UseDictionary {

	public static void main(String[] args) {
		Scanner scan=null;
		SearchDic searchDic=new SearchDic();
		String str=null;
		String input1="";
		String input2="";
		String input3="";
		InsertDic insertDic=null;
		DeleteDic deleteDic=null;
		try {
			if(args.length==0) {		//コマンドライン引数未入力だったら
				System.out.println("何か入力してください");
			}
			scan=new Scanner(System.in);
			while((str=scan.next())!=null) {
				//履歴を出すとき『his』
				if(str.equals("his")) {
					searchDic.hisDisp();

				//単語を追加するとき『insert』
				}else if(str.equals("insert")){
					insertDic=new InsertDic();
					System.out.println("ジャンルを入力してください");
					input1=scan.next();
					System.out.println("登録したい単語を入力してください");
					input2=scan.next();
					System.out.println("意味を日本語で入力してください");
					input3=scan.next();
					insertDic.insertWord(input1,input2,input3);

				//単語の削除するとき『delete』
				}else if(str.equals("delete")) {
					System.out.println("削除するキーワードを入力してください");
					deleteDic = new DeleteDic();
					deleteDic.deleteWord(scan.next());

				}else if(str.equals("ini")) {
					System.out.println("本当に初期化しますか y or それ以外");
					if(scan.next().equals("y")) {

					}else {
						System.out.println("他に操作はありますか");
					}

				//それ以外のコマンド入力時は検索
				}else {
					searchDic.execute(str);		//入力文字読み込んで、引数に渡す。
				}
			}
		}catch(Exception e) {}
	}

}
