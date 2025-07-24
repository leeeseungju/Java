package egovframework.example.sample.board.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.sample.board.service.BoardService;
import egovframework.example.sample.board.service.BoardVO;

@Controller
public class BoardController {

	@RequestMapping(value ="/mainList.do")
	public String list(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request) throws Exception{
	/** EgovPropertyService.sample*/
	/** resource -> egov->spring ->properties.xml **/
	boardVO.setPageUnit(propertiesService.getInt("pageUnit"));
	boardVO.setPageSize(propertiesService.getInt("pageSize"));
	/** pageingsetting*/
	PaginationInfo paginationInfo= new PaginationInfo();
	paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
	paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
	paginationInfo.setPageSize(boardVO.getPageSize());
	boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
	boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage()); List<?> list =boardService.selectBoardList(boardVO);
	// resultlist에list를넣음
	model.addAttribute("resultList",list);
	int totCnt= boardService.selectBoardListTotCnt(boardVO);
	paginationInfo.setTotalRecordCount(totCnt);
	model.addAttribute("paginationInfo", paginationInfo); 
	
	// 세션에서 메시지 가져오기
	String msg = (String) request.getSession().getAttribute("msg");
	System.out.println("mainList.do - 세션 메시지: " + msg);
	if (msg != null) {
		model.addAttribute("msg", msg);
	}
	
	return"/board/mainList";
	}

	@RequestMapping(value = "/mgmt.do", method = RequestMethod.POST)
	public String mgmt2(@ModelAttribute("boardVO") BoardVO boardVO, @RequestParam("mode") String mode, ModelMap model)
			throws Exception {
		if ("add".equals(mode)) {
			boardService.insertBoard(boardVO);
		} else if ("modify".equals(mode)) {
			boardService.updateBoard(boardVO);
		} else if ("del".equals(mode)) {
			boardService.deleteBoard(boardVO);
		}
		return "redirect:/mainList.do";
	}

	@RequestMapping(value = "/mgmt.do", method =RequestMethod.GET)
	public String mgmt(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request) throws Exception {
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	     Calendar c1 = Calendar.getInstance();
	     String strToday = sdf.format(c1.getTime());
	     System.out.println("Today" + strToday);
	
		// 서버에서 가져오기
		boardVO = boardService.selectBoard(boardVO);
			
	     //BoardVO boardVO = new BoardVO();
	     boardVO.setIndate(strToday);
	     boardVO.setWriter(request.getSession().getAttribute("userId").toString());
	     boardVO.setWriterNm(request.getSession().getAttribute("userName").toString());
	
		// 서버에서 가져오기
//		if(request.getAttribute("idx") != null) {
//		     boardVO = boardService.selectBoard(boardVO);
		     model.addAttribute("boardVO", boardVO);
//		}
	
	// 서버에서 가져온값을 화면에 맵핑
	
		return "/board/mgmt";
	}
	

	@RequestMapping(value = "/view.do")
	public String view(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		System.out.println("Today" + strToday);
		boardVO = boardService.selectBoard(boardVO);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("strToday", strToday);
		boardService.updateBoardCount(boardVO);
		List<?> list = boardService.selectReplyList(boardVO);
		// resultlist에 list를 넣음
		model.addAttribute("resultList", list);
		return "/board/view";
	}

	@RequestMapping(value = "/login.do")
	public String login(@RequestParam("user_id") String user_id, @RequestParam("password") String password,
			ModelMap model, HttpServletRequest request) throws Exception {
		// HttpServletRequest request
		// String aa = request.getParameter("user_id");
		System.out.println("userid:" + user_id);
		System.out.println("password:" + password);

		BoardVO boardVO = new BoardVO();
		boardVO.setUserId(user_id);
		boardVO.setPassword(password);
		String user_name = boardService.selectLoginCheck(boardVO);

		if (user_name != null && !"".equals(user_name)) {
			request.getSession().setAttribute("userId", user_id);
			request.getSession().setAttribute("userName", user_name);
		} else {
			request.getSession().setAttribute("userId", "");
			request.getSession().setAttribute("userName", "");
			request.getSession().setAttribute("msg", "아이디와 비밀번호를 확인하세요.");
		}
		return "redirect:mainList.do";
//		return "/board/mainList";
	}
	
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String joinForm() {
		return "/board/join"; // 회원가입 화면 JSP
	}

	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public String join(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model, HttpServletRequest request) throws Exception {
		String exists = boardService.checkUserIdDuplicate(boardVO.getUserId());
		if (exists != null) {
			model.addAttribute("msg", "이미 존재하는 아이디입니다.");
			return "/board/join";
		}
		boardVO.setUseYn("Y");
		boardService.insertMember(boardVO);
		request.getSession().setAttribute("msg", "회원가입이 완료되었습니다.");
		return "redirect:/mainList.do";
	}


	@RequestMapping(value = "/logout.do")
	public String logout(ModelMap model, HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
		return "redirect:mainList.do";
	}

	@RequestMapping(value = "/mypage.do", method = RequestMethod.GET)
	public String mypage(ModelMap model, HttpServletRequest request) throws Exception {
		// 로그인 체크
		String userId = (String) request.getSession().getAttribute("userId");
		if (userId == null || userId.isEmpty()) {
			request.getSession().setAttribute("msg", "로그인이 필요합니다.");
			return "redirect:mainList.do";
		}
		return "/board/mypage";
	}

	@RequestMapping(value = "/updatePassword.do", method = RequestMethod.POST)
	public String updatePassword(@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword") String newPassword, ModelMap model, HttpServletRequest request) throws Exception {
		
		String userId = (String) request.getSession().getAttribute("userId");
		if (userId == null || userId.isEmpty()) {
			request.getSession().setAttribute("msg", "로그인이 필요합니다.");
			return "redirect:mainList.do";
		}

		// 현재 비밀번호 확인
		BoardVO boardVO = new BoardVO();
		boardVO.setUserId(userId);
		boardVO.setPassword(currentPassword);
		String user_name = boardService.selectLoginCheck(boardVO);

		if (user_name == null || user_name.isEmpty()) {
			model.addAttribute("msg", "현재 비밀번호가 올바르지 않습니다.");
			return "/board/mypage";
		}

		// 비밀번호 변경
		boardVO.setPassword(newPassword);
		boardService.updatePassword(boardVO);
		
		request.getSession().setAttribute("msg", "비밀번호가 성공적으로 변경되었습니다.");
		return "redirect:mainList.do";
	}

	@Resource(name = "boardService")
	private BoardService boardService;

	@RequestMapping(value = "/reply.do", method = RequestMethod.POST)
	public String reply(@ModelAttribute("boardVO") BoardVO boardVO, ModelMap model) throws Exception {
		boardService.insertReply(boardVO);
		return "redirect:/view.do?idx=" + boardVO.getIdx();
	}

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@RequestMapping(value = "/removeMessage.do", method = RequestMethod.POST)
	public void removeMessage(HttpServletRequest request) {
		request.getSession().removeAttribute("msg");
	}
}