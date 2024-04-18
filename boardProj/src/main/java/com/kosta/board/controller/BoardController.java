package com.kosta.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.board.dto.Board;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService brdService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/boardList") // 밑에 줄이랑 같음
//	@RequestMapping(value="/boardList", method=methodRequest.GET)
	// page를 가지고 오지 않았으면(required=false) 1로 대체(defaultValue)한다... 
	public ModelAndView boardList(@RequestParam(value="page", required=false, defaultValue="1") Integer page
								  , @RequestParam(value="type", required=false) String type
								  , @RequestParam(value="word", required=false) String word) {
		ModelAndView mav = new ModelAndView();
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(page);
			List<Board> boardList = brdService.boardListByPage(pageInfo, type, word);
			mav.addObject("pageInfo", pageInfo);
			mav.addObject("brdList", boardList);
			
			if(word!=null && word!=null) {
				mav.addObject("type", type);
				mav.addObject("word", word);
			} 
			
			mav.setViewName("boardList");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", "게시글 목록 조회 실패");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "writeform";
	}
	
	@PostMapping("/boardWrite")
	public String boardWrite(@ModelAttribute Board board, @RequestPart(value="file",required=false) MultipartFile file) {
		try {
			brdService.boardWrite(board, file);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/boardList";
	}
	
	@GetMapping("/boardDetail")
	public ModelAndView boardDetail(Integer num, Model model) {
		ModelAndView mav = new ModelAndView();
		
		try {
			Board brd = brdService.brdDetail(num);
			mav.addObject("brd", brd);
			// like
			String userId = (String)session.getAttribute("user");
			if(userId!=null) {
				Boolean like = brdService.isSelectBoardLike(userId, num);
				mav.addObject("like", String.valueOf(like));
			}
			mav.setViewName("boardDetail");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", "게시물 상세 조회 실패");
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/image")
	public void imageView(Integer num, HttpServletResponse response) {
		try {
			String path = "C:/lhm/spring_upload/";
			FileInputStream fis = new FileInputStream(new File(path, num+""));
			FileCopyUtils.copy(fis, response.getOutputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/boardModify")
	public ModelAndView boardModify(Integer num) {
		ModelAndView mav = new ModelAndView();
		
		try {
			Board board = brdService.brdDetail(num);
			mav.addObject("board", board);
			mav.setViewName("modifyForm");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", "게시글 수정 실패");
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	@PostMapping("/boardModify")
	public ModelAndView boardModify(@ModelAttribute Board board, @RequestPart(value="file",required=false) MultipartFile file) {
		ModelAndView mav = new ModelAndView();
		
		try {
			brdService.boardModify(board, file);
			mav.setViewName("redirect:/boardDetail?num="+board.getNum());
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", "게시글 수정 오류");
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	@ResponseBody // 비동기 (return 되는 것이 view가 아니라 data)
	@PostMapping("/boardLike")
	public String boardLike(@RequestParam("like") String like) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			// json 형태의 문자열을 파싱하여 map에 넣어준다.
			Map<String, String> param = mapper.readValue(like, Map.class);
			System.out.println(param);
			// map에 있는 데이터를 json 형태의 문자열로 변환해준다.
			// String json = mapper.writeVaueAsSTring(map)
			Boolean checkLike = brdService.checkBoardLike(
									param.get("memberId"), 
									Integer.parseInt(param.get("boardNum")));
			return String.valueOf(checkLike);
		} catch(Exception e) {
			e.printStackTrace();
			return "none";
		}
	}
	
}
