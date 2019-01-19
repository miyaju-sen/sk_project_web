

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

/*-----------------------------------------------------*/
/*                                                     */
/* v_memosテーブル                                     */
/* 新規登録、編集（更新）、全件抽出の処理を行う        */
/*                                                     */
/*-----------------------------------------------------*/

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

		//新規登録した、あるいは更新したレコードの主キーを格納する変数
		String newId = "nothing";

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

			//INSERT
			if("".equals(no) && !"".equals( m.getNote() )) {
				da.InsertMemo(m);
				newId = da.SelectLastInsert();
			}
			//UPDATE
			else if(null != m.getNote()){ //NULL回避
				m.setNo(no);
				da.UpdateMemo(m);
				newId = no;
			}

			//memosからデータを抽出
			memos = da.SelectMemos( m.getPlot() );

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

		request.setAttribute("MEMOS", memos);
		RequestDispatcher rd = request.getRequestDispatcher("memos_json.jsp");
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
