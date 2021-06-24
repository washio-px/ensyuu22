
public class MakeMessage {
	int len=0;
	String message="";
	String str="";
	String str2="";

	//
	public String waku(String genre) {
		len = genre.getBytes().length;
		message="\n"+"+";
		for(int i=0; i<(len+1); i++) {
			message+="+";
		}
		message+="\n|"+genre+"|\n";
		for(int i=0; i<(len+2); i++) {
			message+="+";
		}
		return message+"\n";
	}

	//文字列の右に空白を埋めるメソッド
	public String rPad(String str) throws Exception{
		len=str.getBytes("UTF-8").length;
		if(len<9) {
			for(int i=0;i<(9-len);i++) {
				str+=" ";
			}
		}
		message=str;
		return message;
	}

	//文字列の左に空白を埋めるメソッド
	public String lPad(String str) throws Exception {
		str2=str;
		len=str.getBytes("UTF-8").length;
		if(len<9) {
			for(int i=0;i<(9-len);i++) {
				str2+=" ";
			}
		}
		message=str2;
		return message;
	}
}
