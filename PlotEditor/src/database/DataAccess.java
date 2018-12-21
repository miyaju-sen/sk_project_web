package database;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Character;
import entities.Plot;

/**
 * SQL文のクラス
 *
 * @author ohs60224
 *
 */
public class DataAccess extends Dao {

	private String _sql = "";

	/**
	 * コンストラクタ
	 *
	 * @throws Exception
	 * @throws SQLException
	 */
	public DataAccess() throws Exception, SQLException {
		super();
	}

	/**
	 * データベース切断
	 *
	 * @throws Exception
	 * @throws SQLException
	 */
	public void DataAccessClose() throws Exception, SQLException {
		super.Close();
	}

//基本構文+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * SELECT文
	 *
	 * @param table 抽出先のテーブル名
	 * @throws Exception
	 * @throws SQLException
	 */
	public void Select(String table) throws Exception, SQLException {
		try {
			this._sql = "SELECT * FROM " + table + ";";
			this.st = this.cn.createStatement();
			this.rs = this.st.executeQuery(_sql);
			System.out.println(_sql);
		}
		catch(Exception e){
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * WHERE付きのSELECT文
	 *
	 * @param table 抽出先のテーブル名
	 * @param where 条件
	 * @throws Exception
	 * @throws SQLException
	 */
	public void SelectWhere(String table, String where) throws Exception, SQLException {
		try {
			this._sql = "SELECT * FROM " + table + " WHERE " + where + ";";
			System.out.println(_sql);
			this.st = this.cn.createStatement();
			this.rs = this.st.executeQuery(_sql);
		}
		catch(Exception e){
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * WHERE付きのSELECT文（条件が配列）
	 *
	 * @param table 抽出先のテーブル名
	 * @param where 条件
	 * @throws Exception
	 * @throws SQLException
	 */
	public void SelectWhere(String table, ArrayList<String> where) throws Exception, SQLException {
		String strWhere = "";
		for(int i = 0; i < where.size(); i++) {
			strWhere += where.get(i);
		}

		System.out.println(strWhere);

		try {
			this._sql = "SELECT * FROM " + table + " WHERE " + strWhere + ";";
			System.out.println(_sql);
			this.st = this.cn.createStatement();
			this.rs = this.st.executeQuery(_sql);
		}
		catch(Exception e){
			e.printStackTrace();
            throw e;
		}
	}


	/**
	 * ORDER付きのSELECT文
	 *
	 * @param table 抽出先のテーブル名
	 * @param order 並べ替えの基準とするカラム名
	 * @throws Exception
	 * @throws SQLException
	 */
	public void SelectOrder(String table, String order) throws Exception, SQLException {
		try {
			this._sql = "SELECT * FROM " + table + " ORDER BY " + order + ";";
			System.out.println(_sql);
			this.st = this.cn.createStatement();
			this.rs = this.st.executeQuery(_sql);
		}
		catch(Exception e){
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 直前にINSERTしたレコードの主キー（オートインクリメントのみ）を取得するSELECT文
	 *
	 * @return 直前にINSERTしたレコードの主キー
	 * @throws Exception
	 * @throws SQLException
	 */
	public String SelectLastInsert() throws Exception, SQLException {
		String lastId = "";
		try {
			this._sql = "SELECT LAST_INSERT_ID();";
			this.st = this.cn.createStatement();
			this.rs = this.st.executeQuery(_sql);
			System.out.println(_sql);

			while(rs.next()) {
				lastId = String.valueOf( rs.getInt("LAST_INSERT_ID()") );
			}

			return lastId;
		}
		catch(Exception e){
			e.printStackTrace();
            throw e;
		}
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


//抽出系+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	/**
	 * プロット一覧全件抽出
	 *
	 * @return プロット一覧の情報が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<Plot> SelectPlots() throws Exception, SQLException {
		String where = "deleted = false";
		this.SelectWhere("plots", where);
		ArrayList<Plot> result = new ArrayList<Plot>();
		try {
			Plot p = null;
			while(rs.next()) {
				p = new Plot();
				p.setNo( rs.getInt("no") );
				p.setTitle( rs.getString("title") );
				p.setSlogan( rs.getString("slogan") );
				p.setSummary( rs.getString("summary") );
				p.setCreatedAt( rs.getString("created_at") );
				p.setDeleted( rs.getBoolean("deleted") );

				result.add(p);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 主キーでプロット情報抽出
	 *
	 * @param no 主キー
	 * @return 主キーと一致するプロットの情報
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<Plot> SelectPlots(int no) throws Exception, SQLException {
		String where = "no = " + no;
		this.SelectWhere("plots", where);
		ArrayList<Plot> result = new ArrayList<Plot>();
		try {
			Plot p = null;
			while(rs.next()) {
				p = new Plot();
				p.setNo( rs.getInt("no") );
				p.setTitle( rs.getString("title") );
				p.setSlogan( rs.getString("slogan") );
				p.setSummary( rs.getString("summary") );
				p.setCreatedAt( rs.getString("created_at") );
				p.setDeleted( rs.getBoolean("deleted") );

				result.add(p);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 登場人物一覧全件抽出
	 *
	 * @return 登場人物一覧の情報が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<Character> SelectCharacters() throws Exception, SQLException {
		String where = "deleted = false";
		this.SelectWhere("characters", where);
		ArrayList<Character> result = new ArrayList<Character>();
		try {
			Character c = null;
			while(rs.next()) {
				c = new Character();
				c.setNo( rs.getInt("no") );
				c.setPlot( rs.getInt("plot") );
				c.setPhonetic( rs.getString("phonetic") );
				c.setName( rs.getString("name") );
				c.setAnother( rs.getString("another") );
				c.setImagePath( rs.getString("image_path") );
				c.setAge( rs.getString("age") );
				c.setGender( rs.getInt("gender") );
				c.setBirthday( rs.getString("birthday") );
				c.setHeight( rs.getInt("height") );
				c.setWeight( rs.getInt("weight") );
				c.setFirstPerson( rs.getString("first_person") );
				c.setSecondPerson( rs.getString("second_person") );
				c.setBelongs( rs.getString("belongs") );
				c.setSkill( rs.getString("skill") );
				c.setProfile( rs.getString("profile") );
				c.setLivedProcess( rs.getString("lived_process") );
				c.setPersonality( rs.getString("personality") );
				c.setAppearance( rs.getString("appearance") );
				c.setOther( rs.getString("other") );
				c.setDeleted( rs.getBoolean("deleted") );

				result.add(c);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


//更新系+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * プロット新規登録
	 *
	 * @param p プロットエンティティクラス
	 * @throws Exception
	 * @throws SQLException
	 */
	public void InsertPlot(Plot p) throws Exception, SQLException {
		try {
			this._sql = "INSERT INTO plots(title, slogan, summary) "
					+ "VALUES(?, ?, ?);";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setString(1, p.getTitle());
			this.pStmt.setString(2, p.getSlogan());
			this.pStmt.setString(3, p.getSummary());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * プロット更新
	 *
	 * @param p プロットエンティティクラス
	 * @throws Exception
	 * @throws SQLException
	 */
	public void UpdatePlot(Plot p) throws Exception, SQLException {
		try {
			this._sql = "UPDATE plots SET title = ?, slogan = ?, summary = ? "
					+ "WHERE no = ?;";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setString(1, p.getTitle());
			this.pStmt.setString(2, p.getSlogan());
			this.pStmt.setString(3, p.getSummary());
			this.pStmt.setInt(4, p.getNo());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 指定したレコードの削除フラグをtrueに変更（削除処理）
	 *
	 * @param no 削除フラグを立てるレコードの主キー
	 * @param table 削除フラグを立てるレコードに対応するテーブル
	 * @return 更新行数
	 * @throws Exception
	 * @throws SQLException
	 */
	public int DeletePlot(String no, String table) throws Exception, SQLException {
		try {
			this._sql = "UPDATE " + table + " SET deleted = true WHERE no = " + no + ";";
			this.pStmt = this.cn.prepareStatement(this._sql);

			return this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 登場人物新規登録
	 *
	 * @param c 登場人物エンティティクラス
	 * @throws Exception
	 * @throws SQLException
	 */
	public void InsertCharacter(Character c) throws Exception, SQLException {
		try {
			this._sql = "INSERT INTO characters"
					+ "(plot, "
					+ "phonetic, "
					+ "name, "
					+ "another, "
					+ "image_path, "
					+ "age, "
					+ "gender, "
					+ "birthday, "
					+ "height, "
					+ "weight, "
					+ "first_person, "
					+ "second_person, "
					+ "belongs, "
					+ "skill, "
					+ "profile, "
					+ "lived_process, "
					+ "personality, "
					+ "appearance, "
					+ "other) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setInt(1, c.getPlot());
			this.pStmt.setString(2, c.getPhonetic());
			this.pStmt.setString(3, c.getName());
			this.pStmt.setString(4, c.getAnother());
			this.pStmt.setString(5, c.getImagePath());
			this.pStmt.setString(6, c.getAge());
			this.pStmt.setInt(7, c.getGender());
			this.pStmt.setString(8, c.getBirthday());
			this.pStmt.setInt(9, c.getHeight());
			this.pStmt.setInt(10, c.getWeight());
			this.pStmt.setString(11, c.getFirstPerson());
			this.pStmt.setString(12, c.getSecondPerson());
			this.pStmt.setString(13, c.getBelongs());
			this.pStmt.setString(14, c.getSkill());
			this.pStmt.setString(15, c.getProfile());
			this.pStmt.setString(16, c.getLivedProcess());
			this.pStmt.setString(17, c.getPersonality());
			this.pStmt.setString(18, c.getAppearance());
			this.pStmt.setString(19, c.getOther());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 登場人物更新
	 *
	 * @param c 登場人物エンティティクラス
	 * @throws Exception
	 * @throws SQLException
	 */
	public void UpdateCharacter(Character c) throws Exception, SQLException {
		try {
			this._sql = "UPDATE characters SET "
					+ "phonetic = ?, "
					+ "name = ?, "
					+ "another = ?, "
					+ "image_path = ?, "
					+ "age = ?, "
					+ "gender = ?, "
					+ "birthday = ?, "
					+ "height = ?, "
					+ "weight = ?, "
					+ "first_person = ?, "
					+ "second_person = ?, "
					+ "belongs = ?, "
					+ "skill = ?, "
					+ "profile = ?, "
					+ "lived_process = ?, "
					+ "personality = ?, "
					+ "appearance = ?, "
					+ "other = ? "
					+ "WHERE = ?;";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setString(1, c.getPhonetic());
			this.pStmt.setString(2, c.getName());
			this.pStmt.setString(3, c.getAnother());
			this.pStmt.setString(4, c.getImagePath());
			this.pStmt.setString(5, c.getAge());
			this.pStmt.setInt(6, c.getGender());
			this.pStmt.setString(7, c.getBirthday());
			this.pStmt.setInt(8, c.getHeight());
			this.pStmt.setInt(9, c.getWeight());
			this.pStmt.setString(10, c.getFirstPerson());
			this.pStmt.setString(11, c.getSecondPerson());
			this.pStmt.setString(12, c.getBelongs());
			this.pStmt.setString(13, c.getSkill());
			this.pStmt.setString(14, c.getProfile());
			this.pStmt.setString(15, c.getLivedProcess());
			this.pStmt.setString(16, c.getPersonality());
			this.pStmt.setString(17, c.getAppearance());
			this.pStmt.setString(18, c.getOther());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
