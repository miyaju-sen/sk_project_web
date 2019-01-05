

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
import entities.Plot;

/*-----------------------------------------------------*/
/*                                                     */
/* plotsテーブル                                       */
/* 新規作成、編集（更新）、全件抽出の処理を行う        */
/* stagesテーブルへの新規作成も行う                    */
/*                                                     */
/*-----------------------------------------------------*/

/**
 * Servlet implementation class PlotJsonServlet
 */
@WebServlet("/PlotJsonServlet")
public class PlotJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlotJsonServlet() {
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

		//抽出したplotsテーブルの中身を格納する配列
		ArrayList<Plot> plots = new ArrayList<Plot>();

		//取得したプロット情報のパラメーターをエンティティにセット
		Plot p = new Plot();
		String no = (String)request.getParameter("no");
		p.setTitle( (String)request.getParameter("title") );
		p.setSlogan( (String)request.getParameter("slogan") );
		p.setSummary( (String)request.getParameter("summary") );

		//DBに接続
		DataAccess da = null;
		try {
			da = new DataAccess();

			//plotsテーブルに新規登録（noが空の場合は新規登録の意）
			if("".equals(no) && !"".equals( p.getTitle() ) ) {
				da.InsertPlot(p);
				newId = da.SelectLastInsert();

				//ここでstagesとideasにレコードを作成しておく
				da.InsertStage(newId);
				for(int i = 1; i <= 4; i++) {
					//起承転結の四つ分新規作成
					da.InsertIdea(newId, i);
				}
			}
			//UPDATE
			else if(null != p.getTitle()){ //NULL回避
				p.setNo(no);
				da.UpdatePlot(p);
				newId = no;
			}

			//plotsテーブルから全件抽出
			plots = da.SelectPlots();

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
		request.setAttribute("PLOTS", plots);
		RequestDispatcher rd = request.getRequestDispatcher("plots_json.jsp");
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
