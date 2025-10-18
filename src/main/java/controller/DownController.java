package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.FileUtil;

import java.io.IOException;

import dao.MVCBoardDAO;

/**
 * Servlet implementation class DownController
 */
@WebServlet("/download.do")
public class DownController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String sDirectory = getServletContext().getRealPath("/Uploads");
		String ofile = request.getParameter("ofile");
		String sfile = request.getParameter("sfile");
		FileUtil.downFile(request, response, sDirectory, sfile, ofile);
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		MVCBoardDAO dao = new MVCBoardDAO();
		dao.updateDownCount(idx);
		
		dao.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
