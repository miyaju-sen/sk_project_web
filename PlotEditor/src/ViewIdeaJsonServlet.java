

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
import entities.ViewIdea;

/*-----------------------------------------------------*/
/*                                                     */
/* v_ideasテーブル                                     */
/* 全件抽出の処理を行う                                */
/*                                                     */
/*-----------------------------------------------------*/

/**
 * Servlet implementation class ViewIdeaJsonServlet
 */
@WebServlet("/ViewIdeaJsonServlet")
public class ViewIdeaJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewIdeaJsonServlet() {
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

		//抽出したv_ideasテーブルの中身を格納する配列
		ArrayList<ViewIdea> viewIdeas = new ArrayList<ViewIdea>();

		//取得したv_ideas情報のパラメーターをエンティティにセット
		ViewIdea vi = new ViewIdea();
//		vi.setIdeaNo( (String)request.getParameter("idea_no") );
//		vi.setPlot( (String)request.getParameter("plot") );
//		vi.setIdea( (String)request.getParameter("idea") );
//		vi.setNote( (String)request.getParameter("note") );
		vi.setStoryNo( (String)request.getParameter("story_no") );
//		vi.setTitle( (String)request.getParameter("title") );
//		vi.setStory( (String)request.getParameter("story") );

		//DBに接続
		DataAccess da = null;
		try {
			da = new DataAccess();

			//v_ideasテーブルから全件抽出
			viewIdeas = da.SelectViewIdea( vi.getStoryNo() );

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

		request.setAttribute("VIEWIDEAS", viewIdeas);
		RequestDispatcher rd = request.getRequestDispatcher("view_ideas_json.jsp");
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
