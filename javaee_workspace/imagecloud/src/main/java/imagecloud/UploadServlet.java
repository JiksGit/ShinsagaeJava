package imagecloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class UploadServlet extends HttpServlet {

    private String savePath; // 서버 실제 경로
    private final int maxLimit = 2 * 1024 * 1024; // 2MB 제한

    // init()에서 ServletConfig로부터 realPath를 받아 초기화
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // webapp 폴더 기준으로 public 폴더 경로를 realPath로 받아옴
        savePath = config.getServletContext().getRealPath("/public");
        System.out.println("파일 저장 경로: " + savePath);
    }

    // 외부에서 저장 경로를 얻을 수 있도록 public 메서드 제공
    public String getSavePath() {
        return savePath;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        PrintWriter out = response.getWriter();
        
    	//[파일처리 관련]
		//1) 이미 서버에 저장되어버린, 이미지를 개발자가 원하는 이미지로 교체.
		//2) 클라이언트 측에서 파일명을 결정짓고 업로드하면, 이 과정은 불필요..
		
		/*
		collection Framework란?
		- 객체를 모아서 처리할 때, 효율적으로 처리할 수 있도록 지원되는 java util 패키지에서 지원하는 api
		
		모아진 모습은? 
			1) 순서있는 모음 - 대표적인 모습은 배열이지만, 배열은 기본 자료형도 제어하므로, 즉 객체만을 다루진 않음
						   따라서 컬렉션 프레임웍은 아니다
						   List
						   
			2) 순서없는 모음 - Map => Key-value
						   Set
		 */

        try {
            MultipartRequest multi = new MultipartRequest(request, savePath, maxLimit, "utf-8");
            System.out.println("업로드 성공");

            String title = multi.getParameter("title");
            out.print("전송된 제목은 " + title + "<br>");

            Enumeration<String> en = multi.getFileNames();
            while (en.hasMoreElements()) {
                String param = en.nextElement();
                out.print("업로드 된 파라미터명은 : " + param + "<br>");

                String filename = multi.getOriginalFileName(param);
                out.print("업로드된 파일명은 " + filename + "<br>");
                System.out.println("업로드된 파일명은 " + filename);
            }

        } catch (IOException e) {
            System.out.println("업로드 실패");
            e.printStackTrace();
            out.print("업로드 실패: " + e.getMessage());
        }
    }
}
