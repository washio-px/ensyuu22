
public class InsertDic {
	String message;
	DicBean bean=null;
	DicDao dao=null;
	public void insertWord(String str1,String str2,String str3) {
		try {
			if(!str1.isEmpty() && !str2.isEmpty() && !str3.isEmpty()) {
				bean=new DicBean();
				bean.setGenre(str1);
				bean.setKeyword1(str2);
				bean.setKeyword2(str3);
				dao=new DicDao();
				int numRow=dao.insert(bean);
				System.out.println(numRow);
				if(numRow>0) {
					message="登録しました。";
				}else {
					message="登録できません。";
				}
			}else {
				message="登録できません。(入力不足)";
			}
			System.out.println(message);
			if(dao!=null) {
				dao.close();
			}
		}catch(Exception e) {}
	}
}
