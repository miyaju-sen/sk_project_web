package entities;

/**
 * 設定・用語のエンティティクラス
 *
 * @author ohs60224
 *
 */
public class Parlance {
	/**
	 * フィールド
	 */
	private int _no; //主キー
	private Plot _plot; //作品No
	private String _name; //名称
	private String _explanation; //説明文
	private boolean _deleted; //削除フラグ

	/**
	 * コンストラクタ
	 */
	public Parlance() {
		this._no = 0;
		this._plot = new Plot();
		this._name = "";
		this._explanation = "";
		this._deleted = false;
	}

//セッター++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * 主キーのセッター
	 * @param no 主キーのセッター
	 */
	public void setNo(int no) {
		this._no = no;
	}
	/**
	 * 主キーのセッター
	 * @param no 主キーのセッター
	 */
	public void setNo(String no) {
		this._no = Integer.valueOf(no);
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
		this._plot.setNo(plot);
	}
	/**
	 * 名称のセッター
	 * @param name 名称
	 */
	public void setName(String name) {
		this._name = name;
	}
	/**
	 * 説明文のセッター
	 * @param explanation 説明文
	 */
	public void setExplanation(String explanation) {
		this._explanation = explanation;
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
	 * 作品Noのゲッター
	 * @return 作品No
	 */
	public int getPlot() {
		return this._plot.getNo();
	}
	/**
	 * 名称のゲッター
	 * @return 名称
	 */
	public String getName() {
		return this._name;
	}
	/**
	 * 説明文のゲッター
	 * @return 説明文
	 */
	public String getExplanation() {
		return this._explanation;
	}
	/**
	 * 削除フラグのゲッター
	 * @return 削除フラグ
	 */
	public boolean isDeleted() {
		return this._deleted;
	}

}
