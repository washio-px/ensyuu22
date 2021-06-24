
public class DeleteDic {
	DicDao dao=null;
	String message=null;
	public void deleteWord(String str) {
		try {
			dao=new DicDao();
			int numRow=dao.delete(str);
			if(numRow>0) {
				message=str+"を削除しました";
			}else {
				message=str+"は削除できません";
			}
			System.out.println(message);
		}catch(Exception e) {}
	}
}
