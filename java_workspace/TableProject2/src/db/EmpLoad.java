package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EmpLoad extends JFrame{
	JTable table;
	JScrollPane scroll;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String[][] data; // 몇건인지 알수없으므로
	String[] columns = {
			"empno", "ename", "job", "mgr", "hiredate",  "sal", "comm", "deptno"
	};
	public void loadData() {
		String url = "jdbc:mysql://localhost:3306/dev";
		String user = "java";
		String pass= "1234";
		
		con = null;
		pstmt = null;
		rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Load Success");
			
			con = DriverManager.getConnection(url, user, pass);
			if(con != null) {
				System.out.println("접속 성공");
				
				String sql = "select * from emp order by empno asc";
				
				//TYPE_SCROLL_INSENSITIVE : 커서가 전방향, 후방향 및 건너뛰기 가능한
				//즉 커서의 위치를 자유자재로 조절 가능한 옵션
				//CONCUR_READ_ONLY : ResultSet에 담은 레코드를 읽기 전용으로만 쓰겠다
				pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
				rs = pstmt.executeQuery();
				
				rs.last(); // 레코드 내에서 마지막 행으로 강제 이동
				// rs가 데이터베이스 연동 결과이므로, 바로 이시점 이후부터 배열의 크기를 결정 지을수 있다.
				int total = rs.getRow();
				System.out.println(total);
				data = new String[total][8];

				rs.beforeFirst();
				
				while(rs.next()) {
					for(int i =0; i<8; i++) {
						data[rs.getRow()-1][i] = rs.getString(columns[i]);
					}					
				}
//					
//				data[0][0] = rs.getString("empno");
//				data[0][1] = rs.getString("ename");
//				data[0][2] = rs.getString("job");
//				data[0][3] = rs.getString("mgr");
//				data[0][4] = rs.getString("hiredate");
//				data[0][5] = rs.getString("sal");
//				data[0][6] = rs.getString("comm");
//				data[0][7] = rs.getString("deptno");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public EmpLoad() {
		// mysql 에서 이미 사원 정보를 가져와야함
		loadData();
		
		table = new JTable(data, columns);
		scroll = new JScrollPane(table);
		add(scroll);
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new EmpLoad();
	}
}
