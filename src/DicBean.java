

import java.io.Serializable;

public class DicBean implements Serializable {
	private int id; 			//辞書ID
	private String genre; 		//分野
	private String keyword1; 	//入力キーワード
	private String keyword2; 	//変換キーワード

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
}

