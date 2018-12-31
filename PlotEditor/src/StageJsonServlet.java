

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
import entities.Stage;

/*-----------------------------------------------------*/
/*                                                     */
/* stagesテーブル                                      */
/* 編集（更新）、全件抽出の処理を行う                  */
/*                                                     */
/*-----------------------------------------------------*/

/**
 * Servlet implementation class StageJsonServlet
 */
@WebServlet("/StageJsonServlet")
public class StageJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StageJsonServlet() {
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

		//抽出したstagesテーブルの中身を格納する配列
		ArrayList<Stage> stages = new ArrayList<Stage>();

		//取得した舞台情報のパラメーターをエンティティにセット
		Stage s = new Stage();
		String no = (String)request.getParameter("no");
		s.setPlot( (String)request.getParameter("plot") );
		s.setStage( (String)request.getParameter("stage") );

		//DBに接続
		DataAccess da = null;
		try {
			da = new DataAccess();

			//UPDATE
			if(!"".equals(no)) {
				System.out.println("主キーは" + no);
				s.setNo(no);
				da.UpdateStage(s);
			}

			//stagesテーブルから全件抽出
			stages = da.SelectStages( s.getPlot() );

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

		request.setAttribute("STAGES", stages);
		RequestDispatcher rd = request.getRequestDispatcher("stages_json.jsp");
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
