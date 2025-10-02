package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import dto.MVCBoardDTO;

public class MVCBoardDAO extends DBConnPool {
	public ArrayList<MVCBoardDTO> selectBoard(Map<String, Object> search) {
		ArrayList<MVCBoardDTO> boardList = new ArrayList<>();		
		String sql = "select * from mvcboard ";
		if (search.get("searchWord") != null) {
			if(search.get("searchField").equals("all")) {				
				sql += " where title like '%" + search.get("searchWord") + "%' "
					+ " or content like '%" + search.get("searchWord") + "%' ";
			} else {
				sql += " where " + search.get("searchField") + " like '%" + search.get("searchWord") + "%' ";
			}
		}				
			   sql += "order by idx desc";	
		
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MVCBoardDTO board = new MVCBoardDTO();
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setOfile(rs.getString("ofile"));
				board.setSfile(rs.getString("sfile"));
				board.setPostdate(rs.getTimestamp("postdate"));
				board.setVisitcount(rs.getInt("visitcount"));
				board.setDowncount(rs.getInt("downcount"));
				boardList.add(board);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return boardList;
	}

	public MVCBoardDTO selectOne(int idx) {
		MVCBoardDTO board = new MVCBoardDTO();
		String sql = "select * from mvcboard where idx = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, idx);
			rs = psmt.executeQuery();
			if(rs.next()) {
				board.setIdx(rs.getInt("idx"));
				board.setName(rs.getString("name"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setOfile(rs.getString("ofile"));
				board.setSfile(rs.getString("sfile"));
				board.setPostdate(rs.getTimestamp("postdate"));
				board.setVisitcount(rs.getInt("visitcount"));
				board.setDowncount(rs.getInt("downcount"));
				System.out.println("1개 가져오기 완료");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}		
		return board;
	}
	
	public int insertBoard(MVCBoardDTO board) {
		int result = 0;
		String sql = "insert into mvcboard(name, pass, title, content, ofile, sfile) values(?,?,?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, board.getName());
			psmt.setString(2, board.getPass());
			psmt.setString(3, board.getTitle());
			psmt.setString(4, board.getContent());
			psmt.setString(5, board.getOfile());
			psmt.setString(6, board.getSfile());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}
	
	public int passCheck(int idx, String name, String pass) {
		int result = 0;
		String sql = "select count(*) from mvcboard where name = ? and pass = ? and idx = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, pass);
			psmt.setInt(3, idx);
			rs = psmt.executeQuery();
			rs.next();
			System.out.println(rs.getInt(1));
			if(rs.getInt(1) == 0) {
				System.out.println("일치항목 없음");
			} else {
				result = 1;
				System.out.println("일치항목 있음");
			}
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}
}
