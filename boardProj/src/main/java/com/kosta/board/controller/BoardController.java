package com.kosta.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.Member;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService brdService;
	
	@GetMapping("/boardList") // 밑에 줄이랑 같음
//	@RequestMapping(value="/boardList", method=methodRequest.GET)
	public ModelAndView boardList(@RequestParam(value="page", required=false, defaultValue="1") Integer page) {
		ModelAndView mav = new ModelAndView();
		try {
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(page);
			List<Board> boardList = brdService.boardListByPage(pageInfo);
			mav.addObject("pageInfo", pageInfo);
			mav.addObject("brdList", boardList);
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
//			Member member = (Member) ;
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
	
}
