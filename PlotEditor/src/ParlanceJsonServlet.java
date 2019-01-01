

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
import entities.Parlance;

/*-----------------------------------------------------*/
/*                                                     */
/* parlancesテーブル                                   */
/* 新規作成、編集（更新）、全件抽出の処理を行う        */
/*                                                     */
/*-----------------------------------------------------*/

/**
 * Servlet implementation class ParlanceJsonServlet
 */
@WebServlet("/ParlanceJsonServlet")
public class ParlanceJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParlanceJsonServlet() {
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

		//抽出したparlancesテーブルの中身を格納する配列
		ArrayList<Parlance> parlances = new ArrayList<Parlance>();

		//取得したプロット情報のパラメーターをエンティティにセット
		Parlance p = new Parlance();
		String no = (String)request.getParameter("no");
		p.setPlot( (String)request.getParameter("plot") );
		p.setName( (String)request.getParameter("name") );
		p.setExplanation( (String)request.getParameter("explanation") );

		//DBに接続
		DataAccess da = null;
		try {
			da = new DataAccess();

			//parlancesテーブルに新規登録（noが空＆名称に値が入っている場合は新規登録の意）
			if("".equals(no) && !"".equals( p.getName() )) {
				da.InsertParlance(p);
				newId = da.SelectLastInsert();
			}
			//UPDATE
			else if(null != p.getName()){ //NULL回避
				p.setNo(no);
				da.UpdateParlance(p);
				newId = no;
			}

			//parlancesテーブルから全件抽出
			parlances = da.SelectParlances(p.getPlot());

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
		request.setAttribute("PARLANCES", parlances);
		RequestDispatcher rd = request.getRequestDispatcher("parlances_json.jsp");
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
