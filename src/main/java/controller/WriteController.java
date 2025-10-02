package controller;

import java.io.IOException;

import dao.MVCBoardDAO;
import dto.MVCBoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.FileUtil;
import utils.JSFunction;

/**
 * Servlet implementation class WriteController
 */
@WebServlet("/write.do")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024 * 10
	)
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/board/Write.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String saveDirectory = getServletContext().getRealPath("/Uploads");
		String originalFileName = "";
		try {
			originalFileName = FileUtil.uploadFile(request, saveDirectory);
		} catch (Exception e) {
			e.printStackTrace();
			JSFunction.alertLocation(response, "파일 업로드 오류입니다.", "./write.do");
		}		
		
		MVCBoardDTO board = new MVCBoardDTO();
		board.setName(request.getParameter("name"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setPass(request.getParameter("pass"));
		
		if(originalFileName != "") {
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			board.setOfile(originalFileName);
			board.setSfile(savedFileName);
		}
		
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.insertBoard(board);
		dao.close();
		
		if(result == 1) {
			response.sendRedirect("./list.do");
			System.out.println("게시글 저장 성공");
		} else {
			JSFunction.alertLocation(response, "등록 실패", "./write.do");
		}
		
	}

}
