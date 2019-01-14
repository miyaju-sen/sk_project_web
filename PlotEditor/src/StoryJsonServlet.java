

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
import entities.Idea;
import entities.Story;
import entities.ViewIdea;

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

		//抽出したideasテーブルの中身を格納する配列
		ArrayList<Idea> ideas = new ArrayList<Idea>();
		ArrayList<ViewIdea> vIdeas = new ArrayList<ViewIdea>();

		//取得したストーリー情報のパラメーターをエンティティにセット
		Story s = new Story();
		String plot = (String)request.getParameter("plot");
		String no = (String)request.getParameter("no");
		s.setIdea( (String)request.getParameter("idea") );
		s.setTitle( (String)request.getParameter("title") );
		s.setStory( (String)request.getParameter("story") );

		//DBに接続
		DataAccess da = null;
		try {
			da = new DataAccess();

			//新規登録
			if("".equals(no) && 0 != s.getIdea()) {
				s.setNo(no);
				da.InsertStory(s);
			}
			//UPDATE
			else if(null != s.getStory()){ //NULL回避
				s.setNo(no);
				da.UpdateStory(s);
			}

			//ideasからデータを抽出（指定したプロット内の起承転結情報が全て抽出される）
			ideas = da.SelectIdeas(plot);

			//v_ideasからデータを抽出（構想Noではなく作品Noでストーリーを抽出できる）
			vIdeas = da.SelectViewIdea(plot);

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

		request.setAttribute("IDEAS", ideas);
		request.setAttribute("VIDEAS", vIdeas);
		RequestDispatcher rd = request.getRequestDispatcher("ideas_json.jsp");
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
