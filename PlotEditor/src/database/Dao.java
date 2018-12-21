package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	private final String _db = "plot_editor"; //使用するデータベース名
	private final String _user = "root";
	private final String _pass = "";

	protected Connection cn = null;
	protected Statement st = null;
	protected PreparedStatement pStmt= null;
	protected ResultSet rs = null;

	/**
	 * コンストラクタ
	 *
	 * @throws Exception
	 * @throws SQLException
	 */
	public Dao() throws Exception, SQLException{
		try {
			//"jdbc:mysql://localhost:3306/practice?characterEncoding=utf8", "root", ""
			Class.forName("com.mysql.jdbc.Driver");
			this.cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + _db + "?characterEncoding=utf8", _user, _pass );
			this.st = cn.createStatement();
		}
		catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
		catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
	}


	/**
	 * データベース切断メソッド
	 *
	 * @throws SQLException
	 */
	public void Close() throws SQLException {
		if(rs != null) {
			try{
				rs.close();
			}
			catch(SQLException e) {
				//TODO: handle exception
			}
		}

		if(st != null) {
			try{
				st.close();
			}
			catch(SQLException e) {
				//TODO: handle exception
			}
		}
		if(cn != null) {
			try{
				cn.close();
			}
			catch(SQLException e) {
				//TODO: handle exception
			}
		}
	}
}
