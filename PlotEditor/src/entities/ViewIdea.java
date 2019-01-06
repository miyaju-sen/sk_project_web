package entities;

/**
 * v_ideasテーブルのエンティティクラス
 *
 * @author ohs60224
 *
 */
public class ViewIdea {
	/**
	 * フィールド
	 */
	private Idea _idea; //Ideaエンティティクラス
	private Story _story; //Storyエンティティクラス

	/**
	 * コンストラクタ
	 */
	public ViewIdea() {
		this._idea = new Idea();
		this._story = new Story();
	}

//セッター++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	/**
	 * 構想Noのセッター
	 * @param ideaNo 構想No
	 */
	public void setIdeaNo(int ideaNo) {
		this._idea.setNo(ideaNo);
	}
	/**
	 * 構想Noのセッター
	 * @param ideaNo 構想No
	 */
	public void setIdeaNo(String ideaNo) {
		this._idea.setNo(ideaNo);
	}
	/**
	 * 作品Noのセッター
	 * @param plot 作品No
	 */
	public void setPlot(int plot) {
		this._idea.setPlot(plot);
	}
	/**
	 * 作品Noのセッター
	 * @param plot 作品No
	 */
	public void setPlot(String plot) {
		this._idea.setPlot(plot);
	}
	/**
	 * 起承転結のセッター
	 * @param idea 起承転結番号
	 */
	public void setIdea(int idea) {
		this._idea.setIdea(idea);
	}
	/**
	 * 起承転結のセッター
	 * @param idea 起承転結番号
	 */
	public void setIdea(String idea) {
		this._idea.setIdea(idea);
	}
	/**
	 * 構想内容のセッター
	 * @param note 構想内容
	 */
	public void setNote(String note) {
		this._idea.setNote(note);
	}
	/**
	 * ストーリーNoのセッター
	 * @param no ストーリーNo
	 */
	public void setStoryNo(int no) {
		this._story.setNo(no);
	}
	/**
	 * ストーリーNoのセッター
	 * @param no ストーリーNo
	 */
	public void setStoryNo(String no) {
		this._story.setNo(no);
	}
	/**
	 * ストーリーのセッター
	 * @param story ストーリー
	 */
	public void setStory(String story) {
		this._story.setStory(story);
	}
	/**
	 * 削除フラグのセッター
	 * @param deleted 削除フラグ
	 */
	public void setDeleted(boolean deleted) {
		this._story.setDeleted(deleted);
	}

//ゲッター++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * 構想Noのゲッター
	 * @return 構想No
	 */
	public int getIdeaNo() {
		return this._idea.getNo();
	}
	/**
	 * 作品Noのゲッター
	 * @return 作品No
	 */
	public int getPlotNo() {
		return this._idea.getPlot();
	}
	/**
	 * 起承転結のゲッター
	 * @return 起承転結番号
	 */
	public int getIdea() {
		return this._idea.getIdea();
	}
	/**
	 * 構想内容のゲッター
	 * @return 構想内容
	 */
	public String getNote() {
		return this._idea.getNote();
	}
	/**
	 * ストーリーNoのゲッター
	 * @return ストーリーNo
	 */
	public int getStoryNo() {
		return this._story.getNo();
	}
	/**
	 * ストーリーのゲッター
	 * @return ストーリー
	 */
	public String getStory() {
		return this._story.getStory();
	}

	/**
	 * 削除フラグのゲッター
	 * @return 削除フラグ
	 */
	public boolean isDeleted() {
		return this._story.isDeleted();
	}
}
