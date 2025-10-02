package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;

	public DBConnPool() {
		try {
			// 커넥션 풀 얻기
			Context initContext = new InitialContext();

			// 현재 웹 애플리케이션이 사용할 수 있는 자원의 위치 설정
			// java:/comp/env <- 루트 디렉토리
			Context envContext = (Context) initContext.lookup("java:/comp/env");

			// java:/comp/env에 위치한 jdbc_oracle를 사용
			DataSource ds = (DataSource) envContext.lookup("jdbc_mysql");

			// 커넥션 풀 통해 연결 얻기
			con = ds.getConnection();
			System.out.println("DB 연결 성공");
		} catch (Exception e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(psmt!=null) psmt.close();
			if(con!=null) con.close();
			System.out.println("DB 커넥션 풀 자원 반납");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
