

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DataAccess;

/*-----------------------------------------------------*/
/*                                                     */
/* 削除フラグを立てる処理を行う                        */
/*                                                     */
/*-----------------------------------------------------*/

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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

		//更新結果を取得する変数
		int result = 0;

		//主キー、テーブル名の取得
		String no = "";
		String table = "";
		no = (String)request.getParameter("no");
		table = (String)request.getParameter("table");

		//DBに接続
		DataAccess da = null;
		try {
			da = new DataAccess();

			//削除フラグを立てる処理
			result = da.DeletePlot(no, table);

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

		//更新が完了(0 != result)した場合にtrue、していない場合にはfalseを送信
		boolean bResult = false;
		if(0 != result) {
			bResult = true;
		}

		request.setAttribute("RESULT", bResult);
		request.setAttribute("No", no);
		request.setAttribute("TABLE", table);
		RequestDispatcher rd = request.getRequestDispatcher("delete.jsp");
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
