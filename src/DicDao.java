
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DicDao {
	private Connection connection;
	String sql=null;

	// コンストラクター
	// java_web_systemデータベースとの接続を行う
	public DicDao() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/java_web_system";
		String user = "root";
		String password = "";
		connection = DriverManager.getConnection(url, user, password);
	}

	// java_web_systemデータベースとの切断するメソッド
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 引数に検索文字列を受け取り、検索結果を返すメソッド。
	public DicBean getDicData(String str) throws SQLException {
		DicBean bean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持するPreparedStatementオブジェクトの生成
			sql = "SELECT * FROM dictionary WHERE keyword1 LIKE ? OR keyword2 LIKE ?";
			pstatement = connection.prepareStatement(sql);
			// INパラメーターの設定
			pstatement.setString(1, str);
			pstatement.setString(2, str);
			// SQLの発行し、抽出結果が格納されたResultSetオブジェクトを取得
			rs = pstatement.executeQuery();
			if (rs.next()) {
				// 列名を指定してResultSetオブジェクトから値を取得
				bean = new DicBean();
				bean.setId(rs.getInt("id"));
				bean.setGenre(rs.getString("genre"));
				bean.setKeyword1(rs.getString("keyword1"));
				bean.setKeyword2(rs.getString("keyword2"));
			}
			// ResultSetオブジェクトの解放
			rs.close();
		} finally {
			// PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return bean;
	}

	public ArrayList<DicBean> dispAll() throws SQLException{
		DicBean bean=null;
		ArrayList<DicBean> array=new ArrayList<>();
		PreparedStatement pstatement=null;
		ResultSet rs=null;
		try {
			sql="SELECT * FROM dictionary";
			pstatement=connection.prepareStatement(sql);
			rs=pstatement.executeQuery();
			while(rs.next()) {
				bean=new DicBean();
				bean.setId(rs.getInt("id"));
				bean.setGenre(rs.getString("genre"));
				bean.setKeyword1(rs.getString("keyword1"));
				bean.setKeyword2(rs.getString("keyword2"));
				array.add(bean);
			}
			rs.close();
		}finally{
			pstatement.close();
		}
		return array;
	}

	// 引数に指定された文字を受け取り、dictionary表に登録するメソッド
	public int insert(DicBean bean) throws SQLException {
		int numRow = 0;
		PreparedStatement pstatement = null;
		try {
			// トランザクション開始
			connection.setAutoCommit(false);
			// SQLを保持するPreparedStatementオブジェクトの生成
			sql = "INSERT INTO dictionary(genre,keyword1,keyword2) VALUES (?,?,?)";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1, bean.getGenre());
			pstatement.setString(2, bean.getKeyword1());
			pstatement.setString(3, bean.getKeyword2());
			// SQLの発行し、dictionary表に登録された行数を取得
			numRow = pstatement.executeUpdate();
		} finally {
			if(numRow > 0){
				// 登録成功時はコミット
				connection.commit();
			}else{
				// 登録失敗時はロールバック
				connection.rollback();
			}
			// PreparedStatementオブジェクトの解放
			pstatement.close();
		}
		return numRow;
	}


	// 引数に指定された従業員IDを受け取り、employee表から削除するメソッド
	public int delete(String str) throws SQLException  {
		int numRow = 0;
		PreparedStatement pstatement = null;
		try {
			// トランザクション開始
			connection.setAutoCommit(false);
			// SQLを保持するPreparedStatementオブジェクトの生成
			String sql = "DELETE FROM dictionary WHERE keyword1=?";
			pstatement = connection.prepareStatement(sql);
			// INパラメーターの設定
			pstatement.setString(1, str);
			// SQLの発行し、employee表に登録された行数を取得
			numRow = pstatement.executeUpdate();
		} finally {
			if(numRow > 0){
				connection.commit();
			}else{
				connection.rollback();
			}
			pstatement.close();
		}
		return numRow;
	}
}