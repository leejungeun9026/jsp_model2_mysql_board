package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.MVCBoardDAO;
import dto.MVCBoardDTO;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> search = new HashMap<>();
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		search.put("searchField", searchField);
		search.put("searchWord", searchWord);
		
		MVCBoardDAO dao = new MVCBoardDAO();
		ArrayList<MVCBoardDTO> boardList = dao.selectBoard(search);
		dao.close();

		request.setAttribute("boardList", boardList);		
		request.getRequestDispatcher("/board/List.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
