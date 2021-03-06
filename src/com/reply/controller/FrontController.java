package com.reply.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.action.Action;
import com.reply.action.ActionForward;

//서블릿으로 구현하기위해 HttpServlet클래스 상속
public class FrontController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//getRequestURI(): 현재 프로젝트명과 파일명을 문자열로 반환하는 메서드
		//"/10_Board_reply/board_list.do" 문자열로 반환하는 메서드
		String uri = request.getRequestURI();
		System.out.println("uri ==> " + uri);
		
		//getContextPath(): 현재 프로젝트명을 문자열로 반환하는 메서드
		//"/10_Board_reply" 문자열로 반환.
		String path=request.getContextPath();
		System.out.println("path ==> " + path);
		
		String command = uri.substring(path.length()+1); //결과값은 "*.do"만 반환
		System.out.println("command ==> " +command);
		
		
		Action action = null;
		ActionForward forward = null;
		
		
		/*
		 * java.util.Properties() 클래스
		 * Properties 클래스는 HashTable의 하위 클래스.
		 * 보통은 환경변수 및 속성 값을 Properties 파일에 저장하여 쉽게 접근할 수 있는 장점이있다.
		 * Properties 파일은 일련의 키-값 의 한 쌍으로 이루어져 있음. (키 = 값)
		 * 보통은 파일에 저장하여 사용됨. 파일 이름을 *.properties로 끝나게 함.
		 * 
		 * InputStream에 Properties 파일을 넣어서 그 스트림으로부터 파일을 가져온다.
		 * 인자로 들어온 Properties 파일을 읽게 된다.
		 * 읽어들일 때  사용하는 메서드는 load()메서드를 이용한다.
		 */
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream("C:\\NCS\\workspace(jsp)\\10_Board_reply\\src\\com\\reply\\controller\\mapping.properties");
		
		prop.load(fis);
		
		//command에 board_list.do가 들어가있으니까아....???? 키에 대한 value값 execute|com.reply.action.BbsListAction를 리턴해줌.
		String value = prop.getProperty(command);	//키값넣으면 value값나옴.유...
		System.out.println("value ==> " + value);
		
		if(value.substring(0,7).equals("execute")) {
			StringTokenizer st = new StringTokenizer(value,"|");
			String url_1 = st.nextToken(); 	//"execute"
			String url_2 = st.nextToken(); 	//"패키지명.클래스명"
			
			/*
			 *동적 객체 생성 : newInstance() /객체(인스턴스)를 생성(뉴)
			 *Class 객체를 이용하면 new 연산자의 사용 없이 동적으로 객체 생성이 가능함.
			 *코드 작성시에 클래스 이름을 결정할 수 없고, 런타임 시에 클래스의 이름이 결정되는 경우에 매우 유용하게 사용이 됨,.
			 *
			 * newInstance() 메서드는 기본 생성자를 호출해서 객체를 생성하기 떄문에 반드시 클래스에 기본생성자가 존재해야 함.
			 * 예외가 발생할 수 있는데 해당 클래스가 추상클래스이거나 인터페이스일 경우에 발생하고, 
			 * 또 하나의 예외는 클래스나 생성자가 접근 제한자로 인해 접근할 수 없는 경우에 발생 함.
			 * 따라서 예외 처리를 해주어야 함.
			 * 
			 * 반환 타입은 Object타입이므로 맞게 형변환을 해 주면 되지만, 클래스의 타입을 모르는 상태이므로 형변환을 해 줄수가 없음.
			 * 이러한 문제를 해결하기 위해 인터페이스를 사용함.
			 * Class.forName(class이름) 은 파라미터로 받은 class이름에 해당하는 클래스를 로딩한 후에, 
			 * 그 클래스에 해당하는 인스턴스를 리턴한다.
			 * newInstance() 메서드는 로딩한 클래스의 객체를 생성하는 메서드이고, 이렇게 생성된 객체를 동적 객체 생성이라고 한다.
			 */
			
			try {
				Class url = Class.forName(url_2);
				
				//다형성으로 객체 생성하는 방법~ url2에있는 객체 (BbsListAction)생성.
				//action = new BbsListAction()의 동적객체생성방법 ↓
				//부모객체참조변수   , 자식ㅋㄹ래스의 객체 생성ㅎ
				action = (Action)url.newInstance();//실행될 때 객체 생성하는 방법.->trycatch 해보면 예외가 2가지나옴!
				
				forward = action.execute(request, response);//반환타입:ActionForward ㅌㅏ입
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { //value값중에 "execute"가 없는 경우.
				 //viewPage로 이동시킨다.
			forward = new ActionForward();
			forward.setRedirect(false);			//*.jsp페이지로 이동.->ㅂ 페이지로 넘어가겠다/..
			forward.setPath(value);
			
		}
		
		if(forward != null) { //forward의 값이 있으면
			if(forward.isRedirect()) {//true인 경우 - *.do인 경우
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}
		
		
	}
	
}
