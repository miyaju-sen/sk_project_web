

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
import entities.Story;

/*-----------------------------------------------------*/
/*                                                     */
/* storiesテーブル                                     */
/* 新規作成、編集（更新）、全件抽出の処理を行う        */
/*                                                     */
/*-----------------------------------------------------*/

/**
 * Servlet implementation class StoryJsonServlet
 */
@WebServlet("/StoryJsonServlet")
public class StoryJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoryJsonServlet() {
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

		//抽出したstoriesテーブルの中身を格納する配列
		ArrayList<Story> stories = new ArrayList<Story>();

		//取得したストーリー情報のパラメーターをエンティティにセット
		Story s = new Story();
		String no = (String)request.getParameter("no");
		s.setIdea( (String)request.getParameter("idea") );
		s.setStory( (String)request.getParameter("story") );

		//DBに接続
		DataAccess da = null;
		try {
			da = new DataAccess();

			//新規登録
			if("".equals(no) && 0 != s.getIdea()) {
				da.InsertStory( s.getIdea() );
				newId = da.SelectLastInsert();
			}
			//UPDATE
			else if(null != s.getStory()){ //NULL回避
				s.setNo(no);
				da.UpdateStory(s);
				newId = no;
			}

			//storiesテーブルから全件抽出
			stories = da.SelectStories( s.getIdea() );

			da.Close();
		}
		catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		request.setAttribute("NEWID", newId);
		request.setAttribute("STORIES", stories);
		RequestDispatcher rd = request.getRequestDispatcher("stories_json.jsp");
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
