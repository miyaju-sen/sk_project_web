package entities;

/**
 * メモ一覧のエンティティクラス
 *
 * @author ohs60224
 *
 */
public class Memo {
	/**
	 * フィールド
	 */
	private int _no; //主キー
	private Plot _plot; //作品No
	private String _note; //内容
	private String _updatedAt; //更新日時
	private boolean _deleted; //削除フラグ

	/**
	 * コンストラクタ
	 */
	public Memo() {
		this._no = 0;
		this._plot = new Plot();
		this._note = "";
		this._updatedAt = "";
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
	 * 内容のセッター
	 * @param note 内容
	 */
	public void setNote(String note) {
		this._note = note;
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
	 * 内容のゲッター
	 * @return 内容
	 */
	public String getNote() {
		return this._note;
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
	 * @return
	 */
	public boolean isDeleted() {
		return this._deleted;
	}
}
