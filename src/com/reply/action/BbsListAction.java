package com.reply.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.ActionMapUIResource;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// DB의 전체 레코드를 화면으로 이동시켜서 출력시키는 킁애ㅡ
		
		BbsDAO dao = BbsDAO.getInstance();
		List<BbsDTO> list =dao.getBbsList();
		
		request.setAttribute("List", list);
		
		//view page로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_list.jsp");
		
		return forward;
	}

}
