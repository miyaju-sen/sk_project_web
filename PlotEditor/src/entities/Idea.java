package entities;

/**
 * 構想（起承転結）のエンティティクラス
 *
 * @author ohs60224
 *
 */
public class Idea {
	/**
	 * フィールド
	 */
	private int _no; //主キー
	private Plot _plot; //作品No
	private int _idea; //起承転結
	private String _note; //大まかな流れ

	/**
	 * コンストラクタ
	 */
	public Idea() {
		this._no = 0;
		this._plot = new Plot();
		this._idea = 0;
		this._note = "";
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
		if(!"".equals(no)) {
			this._no = Integer.valueOf(no);
		}
	}
	/**
	 * 作品Noのセッター
	 * @param plot 作品No
	 */
	public void setPlot(int plot) {
		this._plot.setNo(plot);
	}
	/**
	 * 作品Noのセッター
	 * @param plot 作品No
	 */
	public void setPlot(String plot) {
		if(null != plot) {
			this._plot.setNo(plot);
		}
	}
	/**
	 * 起承転結のセッター
	 * @param idea 起承転結
	 */
	public void setIdea(int idea) {
		this._idea = idea;
	}
	/**
	 * 起承転結のセッター
	 * @param idea 起承転結
	 */
	public void setIdea(String idea) {
		if(!"".equals(idea)) {
			this._idea = Integer.valueOf(idea);
		}
	}
	/**
	 * 大まかな流れのセッター
	 * @param note 大まかな流れ
	 */
	public void setNote(String note) {
		this._note = note;
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
	 * 作品Noのゲッター
	 * @return 作品No
	 */
	public int getPlot() {
		return this._plot.getNo();
	}
	/**
	 * 起承転結のゲッター
	 * @return 起承転結
	 */
	public int getIdea() {
		return this._idea;
	}
	/**
	 * 大まかな流れのゲッター
	 * @return 大まかな流れ
	 */
	public String getNote() {
		return this._note;
	}
}
