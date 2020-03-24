package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//글쓰기폼에서 받아온 데이터를 DBㅇㅔ 저장하는 클래스
		
		String board_writer= request.getParameter("writer").trim();
		String board_title= request.getParameter("title").trim();
		String board_cont= request.getParameter("cont").trim();
		String board_pwd= request.getParameter("pwd").trim();
		
		BbsDTO dto = new BbsDTO();
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		
		BbsDAO dao = BbsDAO.getInstance();
		int res=dao.BbsWrite(dto);
		
		
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		if(res == 1) {
			forward.setRedirect(true); //추가된 게시글도 보여야하니까 갱신!
			forward.setPath("board_list.do");
			
		}else {
			out.println("<script>");
			out.println("alert('글쓰기 실패~')");
			out.println("</script>");
			
			forward=null;
		}
	
		return forward;
	}
}
