package entities;

/**
 * 舞台情報のエンティティクラス
 *
 * @author ohs60224
 *
 */
public class Stage {
	/**
	 * フィールド
	 */
	private int _no; //主キー
	private Plot _plot; //作品No
	private String _stage; //内容

	/**
	 * コンストラクタ
	 */
	public Stage() {
		this._no = 0;
		this._plot = new Plot();
		this._stage = "";
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
		if(null != no) {
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
	 * @param stage 内容
	 */
	public void setStage(String stage) {
		this._stage = stage;
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
	public String getStage() {
		return this._stage;
	}
}
