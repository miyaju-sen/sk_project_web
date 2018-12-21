package entities;

/**
 * 登場人物情報のエンティティクラス
 *
 * @author ohs60224
 *
 */
public class Character {
	/**
	 * フィールド
	 */
	private int _no; //主キー
	private Plot _plot; //作品No
	private String _phonetic; //フリガナ
	private String _name; //名前
	private String _another; //別名
	private String _imagePath; //画像パス
	private String _age; //年齢
	private Gender _gender; //性別
	private String _birthday; //誕生日
	private int _height; //身長
	private int _weight; //体重
	private String _firstPerson; //一人称
	private String _secondPerson; //二人称
	private String _belongs; //所属
	private String _skill; //能力
	private String _profile; //紹介文
	private String _livedProcess; //生い立ち
	private String _personality; //性格
	private String _appearance; //容姿
	private String _other; //その他
	private boolean _deleted; //削除フラグ

	/**
	 * コンストラクタ
	 */
	public Character() {
		this._no = 0;
		this._plot = new Plot();
		this._phonetic = "";
		this._name = "";
		this._another = "";
		this._imagePath = "";
		this._age = "";
		this._gender = new Gender();
		this._birthday = "";
		this._height = 0;
		this._weight = 0;
		this._firstPerson = "";
		this._secondPerson = "";
		this._belongs = "";
		this._skill = "";
		this._profile = "";
		this._livedProcess = "";
		this._personality = "";
		this._appearance = "";
		this._other = "";
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
	 * フリガナのセッター
	 * @param phonetic フリガナ
	 */
	public void setPhonetic(String phonetic) {
		this._phonetic = phonetic;
	}
	/**
	 * 名前のセッター
	 * @param name 名前
	 */
	public void setName(String name) {
		this._name = name;
	}
	/**
	 * 別名のセッター
	 * @param another 別名
	 */
	public void setAnother(String another) {
		this._another = another;
	}
	/**
	 * 画像パスのセッター
	 * @param imagePath 画像パス
	 */
	public void setImagePath(String imagePath) {
		this._imagePath = imagePath;
	}
	/**
	 * 年齢のセッター
	 * @param age 年齢
	 */
	public void setAge(String age) {
		this._age = age;
	}
	/**
	 * 性別のセッター
	 * @param gender 性別
	 */
	public void setGender(int gender) {
		this._gender.setNo(gender);
	}
	/**
	 * 性別のセッター
	 * @param gender 性別
	 */
	public void setGender(String gender) {
		if(null != gender) {
			this._gender.setNo(gender);
		}
	}
	/**
	 * 誕生日のセッター
	 * @param birthday 誕生日
	 */
	public void setBirthday(String birthday) {
		this._birthday = birthday;
	}
	/**
	 * 身長のセッター
	 * @param height 身長
	 */
	public void setHeight(int height) {
		this._height = height;
	}
	/**
	 * 身長のセッター
	 * @param height 身長
	 */
	public void setHeight(String height) {
		if(null != height) {
			this._height = Integer.valueOf(height);
		}
	}
	/**
	 * 体重のセッター
	 * @param weight 体重
	 */
	public void setWeight(int weight) {
		this._weight = weight;
	}
	/**
	 * 体重のセッター
	 * @param weight 体重
	 */
	public void setWeight(String weight) {
		if(null != weight) {
			this._weight = Integer.valueOf(weight);
		}
	}
	/**
	 * 一人称のセッター
	 * @param firstPerson 一人称
	 */
	public void setFirstPerson(String firstPerson) {
		this._firstPerson = firstPerson;
	}
	/**
	 * 二人称のセッター
	 * @param secondPerson 二人称
	 */
	public void setSecondPerson(String secondPerson) {
		this._secondPerson = secondPerson;
	}
	/**
	 * 所属のセッター
	 * @param belongs 所属
	 */
	public void setBelongs(String belongs) {
		this._belongs = belongs;
	}
	/**
	 * 能力のセッター
	 * @param skill 能力
	 */
	public void setSkill(String skill) {
		this._skill = skill;
	}
	/**
	 * 紹介文のセッター
	 * @param profile 紹介文
	 */
	public void setProfile(String profile) {
		this._profile = profile;
	}
	/**
	 * 生い立ちのセッター
	 * @param livedProcess 生い立ち
	 */
	public void setLivedProcess(String livedProcess) {
		this._livedProcess = livedProcess;
	}
	/**
	 * 性格のセッター
	 * @param personality 性格
	 */
	public void setPersonality(String personality) {
		this._personality = personality;
	}
	/**
	 * 容姿のセッター
	 * @param appearance 容姿
	 */
	public void setAppearance(String appearance) {
		this._appearance = appearance;
	}
	/**
	 * その他のセッター
	 * @param other その他
	 */
	public void setOther(String other) {
		this._other = other;
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
	 * フリガナのゲッター
	 * @return フリガナ
	 */
	public String getPhonetic() {
		return this._phonetic;
	}
	/**
	 * 名前のゲッター
	 * @return 名前
	 */
	public String getName() {
		return this._name;
	}
	/**
	 * 別名のゲッター
	 * @return 別名
	 */
	public String getAnother() {
		return this._another;
	}
	/**
	 * 画像パスのゲッター
	 * @return 画像パス
	 */
	public String getImagePath() {
		return this._imagePath;
	}
	/**
	 * 年齢のゲッター
	 * @return 年齢
	 */
	public String getAge() {
		return this._age;
	}
	/**
	 * 性別のゲッター
	 * @return 性別
	 */
	public int getGender() {
		return this._gender.getNo();
	}
	/**
	 * 誕生日のゲッター
	 * @return 誕生日
	 */
	public String getBirthday() {
		return this._birthday;
	}
	/**
	 * 身長のゲッター
	 * @return 身長
	 */
	public int getHeight() {
		return this._height;
	}
	/**
	 * 体重のゲッター
	 * @return 体重
	 */
	public int getWeight() {
		return this._weight;
	}
	/**
	 * 一人称のゲッター
	 * @return 一人称
	 */
	public String getFirstPerson() {
		return this._firstPerson;
	}
	/**
	 * 二人称のゲッター
	 * @return 二人称
	 */
	public String getSecondPerson() {
		return this._secondPerson;
	}
	/**
	 * 所属のゲッター
	 * @return 所属
	 */
	public String getBelongs() {
		return this._belongs;
	}
	/**
	 * 能力のゲッター
	 * @return 能力
	 */
	public String getSkill() {
		return this._skill;
	}
	/**
	 * 紹介文のゲッター
	 * @return 紹介文
	 */
	public String getProfile() {
		return this._profile;
	}
	/**
	 * 生い立ちのゲッター
	 * @return 生い立ち
	 */
	public String getLivedProcess() {
		return this._livedProcess;
	}
	/**
	 * 性格のゲッター
	 * @return 性格
	 */
	public String getPersonality() {
		return this._personality;
	}
	/**
	 * 容姿のゲッター
	 * @return 容姿
	 */
	public String getAppearance() {
		return this._appearance;
	}
	/**
	 * その他のゲッター
	 * @return その他
	 */
	public String getOther() {
		return this._other;
	}
	/**
	 * 削除フラグのゲッター
	 * @return 削除フラグ
	 */
	public boolean isDeleted() {
		return this._deleted;
	}
}
