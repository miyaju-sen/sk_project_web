

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
import entities.ViewIdea;

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
		ArrayList<ViewIdea> vIdeas = new ArrayList<ViewIdea>();

		//遷移先
		String jsp = "";

		//ストーリーがあればtrue、なければfalseと格納（デフォルト：false）
		String storyFlag = "false";

		//取得した起承転結の内容のパラメーターをエンティティにセット
		Idea i = new Idea();
		String no = (String)request.getParameter("no");
		i.setPlot( (String)request.getParameter("plot") );
		String idea = (String)request.getParameter("idea");
		i.setNote( (String)request.getParameter("note") );

		//取得できなかった場合
		if(null == no) {
			no = "";
		}
		if(null == idea) {
			i.setIdea("");
		}

		//DBに接続
		DataAccess da = null;
		try {
			da = new DataAccess();

			//UPDATE
			if(!"".equals(no)) {
				System.out.println("*********" + no);
				i.setNo(no);
				da.UpdateIdea(i);
			}

			//ideasからデータを抽出（指定したプロット内の起承転結情報が全て抽出される）
			ideas = da.SelectIdeas( i.getPlot() );

			//v_ideasからデータを抽出（構想Noではなく作品Noでストーリーを抽出できる）
			vIdeas = da.SelectViewIdea( i.getPlot() );
			//データがあればstoryFlagにtrueと格納
			if(0 != vIdeas.size()) {
				storyFlag = "true";
			}

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
		request.setAttribute("FLAG", storyFlag);
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
