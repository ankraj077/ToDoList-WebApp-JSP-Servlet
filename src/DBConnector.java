import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBConnector
 */
@WebServlet("/DBConnector")
public class DBConnector extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	ArrayList tName = new ArrayList();
	ArrayList tDate = new ArrayList();
	String c= null;

	public DBConnector() {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		tName = new ArrayList();
		tDate = new ArrayList();
	    c = null;
	    
	    
		if (request.getParameter("n") != null) {
			String taskName = request.getParameter("n");
			String taskDate = request.getParameter("d");

			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager
						.getConnection("jdbc:sqlserver://localhost:1433;database=todolistdb;user=sa;password=Conestoga1");
				Statement statement = conn.createStatement();

				String insertQuery = "insert into task(taskName, taskdate) values('"
						+ taskName + "','" + taskDate + "')";
				statement.execute(insertQuery);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;database=todolistdb;user=sa;password=Conestoga1");
			Statement statement = conn.createStatement();

			// String insertQuery =
			// "insert into task(taskName, taskdate) values('','');"

			String sql = "SELECT * FROM task";
			ResultSet rs = (ResultSet) statement.executeQuery(sql);

			while (rs.next()) {
				// Retrieve by column name
				tName.add(rs.getString("taskName").toString());
				tDate.add(rs.getString("taskdate").toString());
			}

			int s = tName.size() - 1;
		    c = ""+s;
			request.setAttribute("tName", tName);
			request.setAttribute("tDate", tDate);
			request.setAttribute("counter", tName.size()-1);
			System.out.println(tName.size());

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/index.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
}
