package database;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Character;
import entities.Idea;
import entities.Memo;
import entities.Parlance;
import entities.Plot;
import entities.Stage;
import entities.Story;
import entities.ViewIdea;

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
	public ArrayList<Plot> SelectPlots(int user) throws Exception, SQLException {
		this._sql = "SELECT "
				+ "no, "
				+ "title, "
				+ "slogan, "
				+ "summary, "
				+ "DATE_FORMAT(updated_at, '%Y-%m-%d %H:%i') AS updated_at, "
				+ "deleted "
				+ "FROM plots "
				+ "WHERE deleted = false "
				+ "AND user = " + user + ";";
		System.out.println(_sql);
		this.st = this.cn.createStatement();
		this.rs = this.st.executeQuery(this._sql);

		ArrayList<Plot> result = new ArrayList<Plot>();
		try {
			Plot p = null;
			while(rs.next()) {
				p = new Plot();
				p.setNo( rs.getInt("no") );
				p.setTitle( rs.getString("title") );
				p.setSlogan( rs.getString("slogan") );
				p.setSummary( rs.getString("summary") );
				p.setUpdatedAt( rs.getString("updated_at") );
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
	 * @param plot 作品No
	 * @return 登場人物一覧の情報が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<Character> SelectCharacters(int plot) throws Exception, SQLException {
		String where = "deleted = false AND plot = " + plot;
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

	/**
	 * 舞台一覧全件抽出
	 *
	 * @param plot 作品No
	 * @return 舞台一覧が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<Stage> SelectStages(int plot) throws Exception, SQLException {
		String where = "plot = " + plot;
		this.SelectWhere("stages", where);
		ArrayList<Stage> result = new ArrayList<Stage>();
		try {
			Stage s = null;
			while(rs.next()) {
				s = new Stage();
				s.setNo( rs.getInt("no") );
				s.setPlot( rs.getString("plot") );
				s.setStage( rs.getString("stage") );
				result.add(s);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 設定・用語一覧全件抽出
	 *
	 * @param plot 作品No
	 * @return 設定・用語一覧が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<Parlance> SelectParlances(int plot) throws Exception, SQLException {
		String where = "deleted = false AND plot = " + plot;
		this.SelectWhere("parlances", where);
		ArrayList<Parlance> result = new ArrayList<Parlance>();
		try {
			Parlance p = null;
			while(rs.next()) {
				p = new Parlance();
				p.setNo( rs.getInt("no") );
				p.setPlot( rs.getString("plot") );
				p.setName( rs.getString("name") );
				p.setExplanation( rs.getString("explanation") );
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
	 * 起承転結の内容一覧全件抽出
	 *
	 * @param plot 作品No
	 * @return 起承転結の内容一覧が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<Idea> SelectIdeas(int plot) throws Exception, SQLException {
		String where = "plot = " + plot;
		this.SelectWhere("ideas", where);
		ArrayList<Idea> result = new ArrayList<Idea>();
		try {
			Idea i = null;
			while(rs.next()) {
				i = new Idea();
				i.setNo( rs.getInt("no") );
				i.setPlot( rs.getString("plot") );
				i.setIdea( rs.getString("idea") );
				i.setNote( rs.getString("note") );

				result.add(i);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 起承転結の内容一覧全件抽出
	 *
	 * @param plot 作品No
	 * @return 起承転結の内容一覧が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<Idea> SelectIdeas(String plot) throws Exception, SQLException {
		String where = "plot = " + plot;
		this.SelectWhere("ideas", where);
		ArrayList<Idea> result = new ArrayList<Idea>();
		try {
			Idea i = null;
			while(rs.next()) {
				i = new Idea();
				i.setNo( rs.getInt("no") );
				i.setPlot( rs.getString("plot") );
				i.setIdea( rs.getString("idea") );
				i.setNote( rs.getString("note") );

				result.add(i);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * ストーリー一覧全件抽出
	 *
	 * @param idea 起承転結の主キー
	 * @return ストーリー一覧が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<Story> SelectStories(int idea) throws Exception, SQLException {
		String where = "deleted = false AND idea = " + idea;
		this.SelectWhere("stories", where);
		ArrayList<Story> result = new ArrayList<Story>();
		try {
			Story s = null;
			while(rs.next()) {
				s = new Story();
				s.setNo( rs.getInt("no") );
				s.setIdea( rs.getString("idea") );
				s.setTitle( rs.getString("title") );
				s.setStory( rs.getString("story") );
				s.setDeleted( rs.getBoolean("deleted") );

				result.add(s);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * v_ideasの一覧全件抽出
	 *
	 * @param plot 作品No
	 * @return v_ideasの一覧が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<ViewIdea> SelectViewIdea(int plot) throws Exception, SQLException {
		String where = "plot = " + plot + " AND deleted = false";
		this.SelectWhere("v_ideas", where);
		ArrayList<ViewIdea> result = new ArrayList<ViewIdea>();
		try {
			ViewIdea vi = null;
			while(rs.next()) {
				vi = new ViewIdea();
				vi.setIdeaNo( rs.getInt("idea_no") );
				vi.setPlot( rs.getString("plot") );
				vi.setIdea( rs.getString("idea") );
				vi.setNote( rs.getString("note") );
				vi.setStoryNo( rs.getString("story_no") );
				vi.setTitle( rs.getString("title") );
				vi.setStory( rs.getString("story") );
				vi.setDeleted( rs.getBoolean("deleted") );

				result.add(vi);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * v_ideasの一覧全件抽出
	 *
	 * @param plot 作品No
	 * @return v_ideasの一覧が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<ViewIdea> SelectViewIdea(String plot) throws Exception, SQLException {
		String where = "plot = " + plot + " AND deleted = false";
		this.SelectWhere("v_ideas", where);
		ArrayList<ViewIdea> result = new ArrayList<ViewIdea>();
		try {
			ViewIdea vi = null;
			while(rs.next()) {
				vi = new ViewIdea();
				vi.setIdeaNo( rs.getInt("idea_no") );
				vi.setPlot( rs.getString("plot") );
				vi.setIdea( rs.getString("idea") );
				vi.setNote( rs.getString("note") );
				vi.setStoryNo( rs.getString("story_no") );
				vi.setTitle( rs.getString("title") );
				vi.setStory( rs.getString("story") );
				vi.setDeleted( rs.getBoolean("deleted") );

				result.add(vi);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * メモの内容一覧全件抽出
	 *
	 * @param plot 作品No
	 * @return メモの内容一覧が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<Memo> SelectMemos(int plot) throws Exception, SQLException {
		this._sql = "SELECT "
				+ "no, "
				+ "plot, "
				+ "note, "
				+ "DATE_FORMAT(updated_at, '%Y-%m-%d %H:%i') AS updated_at, "
				+ "deleted "
				+ "FROM memos "
				+ "WHERE plot = " + plot + " AND deleted = false;";
		this.st = this.cn.createStatement();
		this.rs = this.st.executeQuery(this._sql);

		ArrayList<Memo> result = new ArrayList<Memo>();
		try {
			Memo m = null;
			while(rs.next()) {
				m = new Memo();
				m.setNo( rs.getInt("no") );
				m.setPlot( rs.getString("plot") );
				m.setNote( rs.getString("note") );
				m.setUpdatedAt( rs.getString("updated_at") );

				result.add(m);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * メモの内容一覧全件抽出
	 *
	 * @param plot 作品No
	 * @return メモの内容一覧が格納された配列
	 * @throws Exception
	 * @throws SQLException
	 */
	public ArrayList<Memo> SelectMemos(String plot) throws Exception, SQLException {
		this._sql = "SELECT "
				+ "no, "
				+ "plot, "
				+ "note, "
				+ "DATE_FORMAT(updated_at, '%Y-%m-%d %H:%i') AS updated_at, "
				+ "deleted "
				+ "FROM memos "
				+ "WHERE plot = " + plot + " AND deleted = false;";
		this.st = this.cn.createStatement();
		this.rs = this.st.executeQuery(this._sql);

		ArrayList<Memo> result = new ArrayList<Memo>();
		try {
			Memo m = null;
			while(rs.next()) {
				m = new Memo();
				m.setNo( rs.getInt("no") );
				m.setPlot( rs.getString("plot") );
				m.setNote( rs.getString("note") );
				m.setUpdatedAt( rs.getString("updated_at") );

				result.add(m);
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
			this._sql = "INSERT INTO plots(title, slogan, summary, user) "
					+ "VALUES(?, ?, ?, ?);";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setString(1, p.getTitle());
			this.pStmt.setString(2, p.getSlogan());
			this.pStmt.setString(3, p.getSummary());
			this.pStmt.setInt(4, p.getUser());

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
					+ "WHERE no = ?;";
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
			this.pStmt.setInt(19 , c.getNo());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 舞台情報新規登録
	 * プロット新規作成直後に行う
	 *
	 * @param plotNo 作品Noの主キー
	 * @throws Exception
	 * @throws SQLException
	 */
	public void InsertStage(String plotNo) throws Exception, SQLException {
		try {
			this._sql = "INSERT INTO stages(plot, stage) VALUES(?, ?);";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setString(1, plotNo);
			this.pStmt.setString(2, "");

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 舞台情報更新
	 *
	 * @param s 舞台エンティティクラス
	 * @throws Exception
	 * @throws SQLException
	 */
	public void UpdateStage(Stage s) throws Exception, SQLException {
		try {
			this._sql = "UPDATE stages SET stage = ? WHERE no = ?;";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setString(1, s.getStage());
			this.pStmt.setInt(2, s.getNo());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 設定・用語新規登録
	 *
	 * @param plotNo 作品Noの主キー
	 * @throws Exception
	 * @throws SQLException
	 */
	public void InsertParlance(Parlance p) throws Exception, SQLException {
		try {
			this._sql = "INSERT INTO parlances(plot, name, explanation) VALUES(?, ?, ?);";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setInt(1, p.getPlot());
			this.pStmt.setString(2, p.getName());
			this.pStmt.setString(3, p.getExplanation());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 設定・用語更新
	 *
	 * @param p 設定・用語エンティティクラス
	 * @throws Exception
	 * @throws SQLException
	 */
	public void UpdateParlance(Parlance p) throws Exception, SQLException {
		try {
			this._sql = "UPDATE parlances SET name = ?, explanation = ? WHERE no = ?;";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setString(1, p.getName());
			this.pStmt.setString(2, p.getExplanation());
			this.pStmt.setInt(3, p.getNo());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 起承転結の内容新規登録
	 * プロット新規作成直後に行う
	 *
	 * @param plotNo 作品No
	 * @param ideaNo 起承転結No
	 * @throws Exception
	 * @throws SQLException
	 */
	public void InsertIdea(String plotNo, int ideaNo) throws Exception, SQLException {
		try {
			this._sql = "INSERT INTO ideas(plot, idea, note) VALUES(?, ?, ?);";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setString(1, plotNo);
			this.pStmt.setInt(2, ideaNo);
			this.pStmt.setString(3, "");

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * 起承転結の内容更新
	 *
	 * @param i 起承転結エンティティクラス
	 * @throws Exception
	 * @throws SQLException
	 */
	public void UpdateIdea(Idea i) throws Exception, SQLException {
		try {
			this._sql = "UPDATE ideas SET note = ? WHERE no = ?;";
			this.pStmt = this.cn.prepareStatement(this._sql);

			System.out.println("**UPDATE**");

			this.pStmt.setString(1, i.getNote());
			this.pStmt.setInt(2, i.getNo());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * ストーリー新規登録
	 *
	 * @param idea 起承転結の主キー
	 * @throws Exception
	 * @throws SQLException
	 */
	public void InsertStory(Story s) throws Exception, SQLException {
		try {
			this._sql = "INSERT INTO stories(idea, title, story) VALUES(?, ?, ?);";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setInt(1, s.getIdea());
			this.pStmt.setString(2, s.getTitle());
			this.pStmt.setString(3, s.getStory());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * ストーリー更新
	 *
	 * @param s ストーリーエンティティクラス
	 * @throws Exception
	 * @throws SQLException
	 */
	public void UpdateStory(Story s) throws Exception, SQLException {
		try {
			this._sql = "UPDATE stories SET title = ?, story = ? WHERE no = ?;";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setString(1, s.getTitle());
			this.pStmt.setString(2, s.getStory());
			this.pStmt.setInt(3, s.getNo());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * メモ新規登録
	 *
	 * @param m メモエンティティクラス
	 * @throws Exception
	 * @throws SQLException
	 */
	public void InsertMemo(Memo m) throws Exception, SQLException {
		try {
			this._sql = "INSERT INTO memos(plot, note) VALUES(?, ?);";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setInt(1, m.getPlot());
			this.pStmt.setString(2, m.getNote());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

	/**
	 * メモ更新
	 *
	 * @param m メモエンティティクラス
	 * @throws Exception
	 * @throws SQLException
	 */
	public void UpdateMemo(Memo m) throws Exception, SQLException {
		try {
			this._sql = "UPDATE memos SET note = ? WHERE no = ?;";
			this.pStmt = this.cn.prepareStatement(this._sql);

			this.pStmt.setString(1, m.getNote());
			this.pStmt.setInt(2, m.getNo());

			this.pStmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
            throw e;
		}
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
