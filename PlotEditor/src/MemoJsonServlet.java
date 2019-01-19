

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
import entities.Memo;

/**
 * Servlet implementation class MemoJsonServlet
 */
@WebServlet("/MemoJsonServlet")
public class MemoJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemoJsonServlet() {
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

		//抽出したmemosテーブルの中身を格納する配列
		ArrayList<Memo> memos = new ArrayList<Memo>();

		//取得したメモ情報のパラメーターをエンティティにセット
		Memo m = new Memo();
		String no = (String)request.getParameter("no");
		m.setPlot( (String)request.getParameter("plot") );
		m.setNote( (String)request.getParameter("note") );

		//取得できなかった場合
		if(null == no) {
			no = "";
		}

		//DBに接続
		DataAccess da = null;
		try {
			da = new DataAccess();

			//UPDATE
			if(!"".equals(no)) {
				m.setNo(no);
				da.UpdateIdea(i);
			}

			//memosからデータを抽出
			memos = da.SelectIdeas( i.getPlot() );

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
