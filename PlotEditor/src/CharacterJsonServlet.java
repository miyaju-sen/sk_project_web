

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataAccess;
import entities.Character;

/*-----------------------------------------------------*/
/*                                                     */
/* charactersテーブル                                  */
/* 新規作成、編集（更新）、全件抽出の処理を行う        */
/*                                                     */
/*-----------------------------------------------------*/

/**
 * Servlet implementation class CharacterJsonServlet
 */
@WebServlet("/CharacterJsonServlet")
public class CharacterJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharacterJsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//文字化け対策
		request.setCharacterEncoding("utf-8");

		//新規登録した、あるいは更新したレコードの主キーを格納する変数
		String newId = "nothing";
		System.out.println("地点A");

		//抽出したplotsテーブルの中身を格納する配列
		ArrayList<Character> characters = new ArrayList<Character>();
		System.out.println("地点B");

		//取得したプロット情報のパラメーターをエンティティにセット
		Character c = new Character();
		String no = (String)request.getParameter("no");
		c.setPlot( (String)request.getParameter("plot") );
		c.setPhonetic( (String)request.getParameter("phonetic") );
		c.setName( (String)request.getParameter("name") );
		c.setAnother( (String)request.getParameter("another") );
		c.setImagePath( (String)request.getParameter("image_path") );
		c.setAge( (String)request.getParameter("age") );
		c.setGender( (String)request.getParameter("gender") );
		c.setBirthday( (String)request.getParameter("birthday") );
		c.setHeight( (String)request.getParameter("height") );
		c.setWeight( (String)request.getParameter("weight") );
		c.setFirstPerson( (String)request.getParameter("firstPerson") );
		c.setSecondPerson( (String)request.getParameter("secondPerson") );
		c.setBelongs( (String)request.getParameter("belongs") );
		c.setSkill( (String)request.getParameter("skill") );
		c.setProfile( (String)request.getParameter("profile") );
		c.setLivedProcess( (String)request.getParameter("lived_process") );
		c.setPersonality( (String)request.getParameter("personality") );
		c.setAppearance( (String)request.getParameter("appearance") );
		c.setOther( (String)request.getParameter("other") );

		//DBに接続
		DataAccess da = null;
		try {
			System.out.println("地点E");
			da = new DataAccess();
			System.out.println(c.toString());

			//charactersテーブルに新規登録（noが空＆名前に値が入っている場合は新規登録の意）
			if("".equals(no) && !"".equals( c.getName() )) {
				da.InsertCharacter(c);
				newId = da.SelectLastInsert();
			}
			//UPDATE
			else if(null != c.getName()){ //NULL回避
				c.setNo(no);
				da.UpdateCharacter(c);
				newId = no;
			}

			//登場人物sテーブルから全件抽出
			characters = da.SelectCharacters();

			da.Close();
		}
		catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("地点C");
		}
		catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("地点D");
		}

		request.setAttribute("NEWID", newId);
		request.setAttribute("CHARACTERS", characters);
		RequestDispatcher rd = request.getRequestDispatcher("characters_json.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
