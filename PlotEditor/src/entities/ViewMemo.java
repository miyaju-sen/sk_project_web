package entities;

/**
 * v_memosのエンティティクラス
 *
 * @author ohs60224
 *
 */
public class ViewMemo {
	/**
	 * フィールド
	 */
	private Memo _memo;
	private MemoImage _image;

	/**
	 * コンストラクタ
	 */
	public ViewMemo() {
		this._memo = new Memo();
		this._image = new MemoImage();
	}

	/**
	 * メモNoのセッター
	 * @param memoNo
	 */
	public void setMemoNo(int memoNo) {
		this._memo.setNo(memoNo);
	}
	/**
	 * メモNoのセッター
	 * @param memoNo
	 */
	public void setMemoNo(String memoNo) {
		this._memo.setNo(memoNo);
	}
}
