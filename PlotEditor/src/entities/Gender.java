package entities;

/**
 * 性別のエンティティクラス
 *
 * @author ohs60224
 *
 */
public class Gender {
	/**
	 * フィールド
	 */
	private int _no; //主キー
	private String _gender; //性別

	/**
	 * コンストラクタ
	 */
	public Gender() {
		this._no = 0;
		this._gender = "";
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
	 * 性別のセッター
	 * @param gender 性別
	 */
	public void setGender(String gender) {
		this._gender = gender;
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
	 * 性別のゲッター
	 * @return 性別
	 */
	public String getGender() {
		return this._gender;
	}
}
