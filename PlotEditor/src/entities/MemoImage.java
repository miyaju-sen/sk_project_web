package entities;

/**
 * メモ画像一覧のエンティティクラス
 *
 * @author ohs60224
 *
 */
public class MemoImage {
	/**
	 * フィールド
	 */
	private int _no; //主キー
	private Memo _memo; //メモNo
	private String _imagePath; //画像パス
	private int _position; //配置位置
	private boolean _deleted; //削除フラグ

	/**
	 * コンストラクタ
	 */
	public MemoImage() {
		this._no = 0;
		this._memo = new Memo();
		this._imagePath = "";
		this._position = 0;
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
	 * メモNoのセッター
	 * @param memo メモNo
	 */
	public void setMemo(int memo) {
		this._memo.setNo(memo);
	}
	/**
	 * メモNoのセッター
	 * @param memo メモNo
	 */
	public void setMemo(String memo) {
		this._memo.setNo(memo);
	}
	/**
	 * 画像パスのセッター
	 * @param imagePath 画像パス
	 */
	public void setImagePath(String imagePath) {
		this._imagePath = imagePath;
	}
	/**
	 * 配置位置のセッター
	 * @param position 配置位置
	 */
	public void setPosition(int position) {
		this._position = position;
	}
	/**
	 * 配置位置のセッター
	 * @param position 配置位置
	 */
	public void setPosition(String position) {
		if(!"".equals(position)) {
			this._position = Integer.valueOf(position);
		}
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
	 * メモNoのゲッター
	 * @return メモNo
	 */
	public int getMemo() {
		return this._memo.getNo();
	}
	/**
	 * 画像パスのゲッター
	 * @return 画像パス
	 */
	public String getImagePath() {
		return this._imagePath;
	}
	/**
	 * 配置位置のゲッター
	 * @return 配置位置
	 */
	public int getPosition() {
		return this._position;
	}
	/**
	 * 削除フラグのゲッター
	 * @return 削除フラグ
	 */
	public boolean isDeleted() {
		return this._deleted;
	}
}
