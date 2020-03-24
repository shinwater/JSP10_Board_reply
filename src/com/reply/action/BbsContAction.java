package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get방싯ㄱㅇㅇ,로 넘어온 글번호에 해당하는 상세네ㅐ역을 조회하는 킁애그~~
		int board_no=Integer.parseInt(request.getParameter("no"));
		
		BbsDAO dao= BbsDAO.getInstance();
		dao.BoardHit(board_no);
		BbsDTO dto =dao.getCont(board_no);
		
		//키로 저장하여 viewPage로 이동
		request.setAttribute("cont", dto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_cont.jsp");
		
		return forward;
	}

}
