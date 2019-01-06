package entities;

/**
 * ストーリーのエンティティクラス
 *
 * @author ohs60224
 *
 */
public class Story {
	/**
	 * フィールド
	 */
	private int _no; //主キー
	private Idea _idea; //構想No
	private String _title; //タイトル
	private String _story; //内容
	private boolean _deleted; //削除フラグ

	/**
	 * コンストラクタ
	 */
	public Story() {
		this._no = 0;
		this._idea = new Idea();
		this._title = "";
		this._story = "";
		this._deleted = false;
	}

//セッター++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * 主キーのセッター
	 * @param no 主キー
	 */
	public void setNo(int no) {
		this._no = no;
	}
	/**
	 * 主キーのセッター
	 * @param no 主キー
	 */
	public void setNo(String no) {
		this._no = Integer.valueOf(no);
	}
	/**
	 * 構想Noのセッター
	 * @param idea 構想No
	 */
	public void setIdea(int idea) {
		this._idea.setNo(idea);
	}
	/**
	 * 構想Noのセッター
	 * @param idea 構想No
	 */
	public void setIdea(String idea) {
		if(null != idea) {
			this._idea.setNo(idea);
		}
	}
	/**
	 * タイトルのセッター
	 * @param title タイトル
	 */
	public void setTitle(String title) {
		this._title = title;
	}
	/**
	 * 内容のセッター
	 * @param story 内容
	 */
	public void setStory(String story) {
		this._story = story;
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
	 * 主キーのゲッター
	 * @return 主キー
	 */
	public int getNo() {
		return this._no;
	}
	/**
	 * 構想Noのゲッター
	 * @return 構想No
	 */
	public int getIdea() {
		return this._idea.getNo();
	}
	/**
	 * タイトルのゲッター
	 * @return タイトル
	 */
	public String getTitle() {
		return this._title;
	}
	/**
	 * 内容のゲッター
	 * @return 内容
	 */
	public String getStory() {
		return this._story;
	}
	/**
	 * 削除フラグのゲッター
	 * @return 削除フラグ
	 */
	public boolean isDeleted() {
		return this._deleted;
	}
}
