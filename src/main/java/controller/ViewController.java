package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import dao.MVCBoardDAO;
import dto.MVCBoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewController
 */
@WebServlet("/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		MVCBoardDAO dao = new MVCBoardDAO();
		dao.updateVisitCount(Integer.parseInt(request.getParameter("idx")));
		MVCBoardDTO board = dao.selectOne(Integer.parseInt(request.getParameter("idx")));
		dao.close();

		// 줄바꿈 처리
		board.setContent(board.getContent().replace("\r\n", "<br>"));
		
		// 첨부파일 확장자 추출 및 이미지 타입 확인
		// 이미지일 경우에만 보여줌
		String ext = null;
		String fileName = board.getSfile();
		if(fileName!=null) {
			ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		String[] mimeStr = {"png", "jpg", "gif"};
		List<String> mimeList = Arrays.asList(mimeStr);
		boolean isImage = false;
		if(mimeList.contains(ext)) {
			isImage = true;
		}
		
		request.setAttribute("board", board);
		request.setAttribute("isImage", isImage);
		request.getRequestDispatcher("/board/View.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
