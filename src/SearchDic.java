
import java.util.ArrayList;

public class SearchDic {
	DicDao dao = null;
	DicBean bean = null;
	String message1 = "";
	String message2 = "";
	String input = "";
	String output = "";
	String genre = "";
	ArrayList<DicBean> array = null;
	MakeMessage mMessage =null;

	public void execute(String str) throws Exception {
		String language = null;
		try {
			dao = new DicDao();
			mMessage = new MakeMessage();

			//全件検索コマンド『al』が押された場合
			if (str.equals("al")) {
				array = new ArrayList<DicBean>();
				array = dao.dispAll(); //全件検索の結果、beanがarrayに入る。
				for (DicBean dBean : array) {
					if (!genre.equals(dBean.getGenre())) {
						genre = dBean.getGenre();
						message1+=mMessage.waku(genre);
					}
					message2 = dBean.getKeyword1();
					message2 = mMessage.rPad(message2);
					message1 += message2 + " : " + dBean.getKeyword2() + "\n";
				}
				System.out.println(message1);

			} else {
				//和訳・英訳(変換したい文字列をdaoに渡し、beanを取得)
				bean = dao.getDicData(str);
				if (str.equals(bean.getKeyword1())) {
					language = "　日本語　";
					input = bean.getKeyword1();
					output = bean.getKeyword2();
				} else {
					language = "　英　語　";
					input = bean.getKeyword2();
					output = bean.getKeyword1();
				}
				message1 = "変換ワードは『" + input + "』です\n"
						+ "【" + bean.getGenre() + "】を変換します\n"
						+ "\n入力ワード:" + input;
				message1 += "\n" + language + ":" + output;
				System.out.println(message1);
				if(array==null) {
					array=new ArrayList<DicBean>();
				}
				array.add(bean); //コレクションにbeanを追加。
				System.out.println(array.size());
			}
		} catch (Exception e) {
			System.out.println("検索できません");
			e.printStackTrace();

		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}

	//履歴表示コマンド『his』が入力されたとき、
	public void hisDisp() throws Exception {
		if(array!=null) {
			System.out.println(array.size());
			for (DicBean hBean : array) {
				message1 = "入力:" + mMessage.rPad(hBean.getKeyword1());
				message1 += "意味:" + mMessage.lPad(hBean.getKeyword2());
				message1 += "分野:" + hBean.getGenre();
				System.out.println(message1);
			}

		}else {
			message1="履歴はありません";
		}

	}
}

		/*String empAge1 = request.getParameter("paramAge1");
		String empAge2 = request.getParameter("paramAge2");
		try {
			if (empAge1 != null && empAge2 != null && !empAge1.isEmpty() && !empAge2.isEmpty()) {
				daoTest = new EmpDao();
				int age1 = Integer.parseInt(empAge1);
				int age2 = Integer.parseInt(empAge2);
				ArrayList<EmpBean> empList = daoTest.getEmpDataByAge(age1, age2);
				if (empList.isEmpty()) {
					request.setAttribute("message", "該当者はいません");
				} else {
					request.setAttribute("empList", empList);
				}
			} else {
				request.setAttribute("message", "検索年齢を入力してください");
			}
		} finally {
			if (daoTest != null) {
				daoTest.close();
			}
		}*/