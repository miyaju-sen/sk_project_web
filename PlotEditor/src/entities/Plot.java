package entities;

/**
 * プロット一覧のエンティティクラス
 *
 * @author ohs60224
 *
 */
public class Plot {
	/**
	 * フィールド
	 */
	private int _no; //作品No
	private String _user; //ユーザNo
	private String _title; //作品タイトル
	private String _slogan; //キャッチコピー
	private String _summary; //あらすじ
	private String _updatedAt; //更新日時
	private boolean _deleted; //削除フラグ

	/**
	 * コンストラクタ
	 */
	public Plot() {
		this._no = 0;
		this._user = "";
		this._title = "";
		this._slogan = "";
		this._summary = "";
		this._updatedAt = "";
		this._deleted = false;
	}

//セッター++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * 作品Noのセッター
	 * @param no 作品No
	 */
	public void setNo(int no) {
		this._no = no;
	}
	/**
	 * 作品Noのセッター
	 * @param no 作品No
	 */
	public void setNo(String no) {
		this._no = Integer.valueOf(no);
	}
	/**
	 * ユーザNoのセッター
	 * @param user ユーザNo
	 */
	public void setUser(String user) {
		this._user = user;
	}
	/**
	 * 作品タイトルのセッター
	 * @param title 作品タイトル
	 */
	public void setTitle(String title) {
		this._title = title;
	}
	/**
	 * キャッチコピーのセッター
	 * @param slogan キャッチコピー
	 */
	public void setSlogan(String slogan) {
		this._slogan = slogan;
	}
	/**
	 * あらすじのセッター
	 * @param summary あらすじ
	 */
	public void setSummary(String summary) {
		this._summary = summary;
	}
	/**
	 * 更新日時のセッター
	 * @param updatedAt 更新日時
	 */
	public void setUpdatedAt(String updatedAt) {
		this._updatedAt = updatedAt;
	}
	/**
	 * 削除フラグのセッター
	 * @param deleted 削除フラグ
	 */
	public void setDeleted(boolean deleted) {
		this._deleted = deleted;
	}

//ゲッター++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * 作品Noのゲッター
	 * @return 作品No
	 */
	public int getNo() {
		return this._no;
	}
	/**
	 * ユーザNoのゲッター
	 * @return ユーザNo
	 */
	public String getUser() {
		return this._user;
	}
	/**
	 * 作品タイトルのゲッター
	 * @return 作品タイトル
	 */
	public String getTitle() {
		return this._title;
	}
	/**
	 * キャッチコピーのゲッター
	 * @return キャッチコピー
	 */
	public String getSlogan() {
		return this._slogan;
	}
	/**
	 * あらすじのゲッター
	 * @return あらすじ
	 */
	public String getSummary() {
		return this._summary;
	}
	/**
	 * 更新日時のゲッター
	 * @return 更新日時
	 */
	public String getUpdatedAt() {
		return this._updatedAt;
	}
	/**
	 * 削除フラグのゲッター
	 * @return 削除フラグ
	 */
	public boolean isDeleted() {
		return this._deleted;
	}

}
