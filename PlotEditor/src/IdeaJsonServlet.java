

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

/*-----------------------------------------------------*/
/*                                                     */
/* v_ideasテーブル                                     */
/* 編集（更新）、全件抽出の処理を行う                  */
/*                                                     */
/*-----------------------------------------------------*/

/**
 * Servlet implementation class IdeaJsonServlet
 */
@WebServlet("/IdeaJsonServlet")
public class IdeaJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdeaJsonServlet() {
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

		//取得した起承転結の内容のパラメーターをエンティティにセット
		Idea i = new Idea();
		String no = (String)request.getParameter("no");
		i.setPlot( (String)request.getParameter("plot") );
		i.setIdea( (String)request.getParameter("idea") );
		i.setNote( (String)request.getParameter("note") );

		//DBに接続
		DataAccess da = null;
		try {
			da = new DataAccess();

			//UPDATE
			if(!"".equals(no)) {
				i.setNo(no);
				da.UpdateIdea(i);
			}

			//ideasテーブルから全件抽出
			ideas = da.SelectIdeas( i.getPlot() );

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
