package com.study.springfinal.controller.gallery;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.study.springfinal.common.FileManager;
import com.study.springfinal.domain.Gallery;
import com.study.springfinal.model.dao.GalleryDAO;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	// 표시를 할테니, 여기에 넣어주세요. 메모리에 올라온 컴포넌트들을 넣어준다..
	@Autowired
	private GalleryDAO galleryDAO;
	
	// 스프링 프레임웍은 업로드 컴포넌트로, apache fileupload를 사용함
	@RequestMapping(value="/regist", method = RequestMethod.POST)
	public String regist(Gallery gallery,HttpServletRequest request) {
		// 물리적 저장
		MultipartFile photo =  gallery.getPhoto();
		System.out.println("original is "+photo.getOriginalFilename());		
		System.out.println("getName is "+photo.getName());
		System.out.println("size is "+photo.getSize());
		System.out.println("contentType is "+photo.getContentType());	
		
		// 파일명 새로 만들어 저장하기
		String newName = Long.toString(System.currentTimeMillis());		
		String ext = FileManager.getExtend(photo.getOriginalFilename());
		String filename = newName+"."+ext;		// 최종 파일명
		gallery.setFilename(filename);				// 새로운 파일명 vo에 저장
		
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath("/data");
		System.out.println(realPath);
		// 이클립스 내부 톰켓인 경우, 실제 경로와는 틀린 경로에 저장..개발시엔 그 경로를 확인하려면 출력해보자,.
		try {
			photo.transferTo(new File(realPath+"/"+filename));	// dir 경로? 물리적 저장! 원하는 경로에 저장을 해라..
			int result = galleryDAO.insert(gallery);
			System.out.println("등록결과는 "+result);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}	
		
		return "redirect:/gallery/list";
	}	
	
	// 목록 가져오기
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView selectAll() {// 유지시 반환형..
		// 3단계
		List galleryList = galleryDAO.selectAll();
		
		// 4단계
		ModelAndView mav = new ModelAndView("gallery/list");
		mav.addObject("galleryList", galleryList);		
		return mav;
	}
	
	// 상세보기 요청처리
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ModelAndView select(int gallery_id) {
		System.out.println("gallery_id "+gallery_id);
		
		Gallery gallery = galleryDAO.select(gallery_id);
		
		ModelAndView mav = new ModelAndView("gallery/detail");
		mav.addObject("gallery", gallery);
		return mav;
	}
	
	// 글 수정 요청처리
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String edit(Gallery gallery) {// 끊어버릴 시 반환형..
		// 3단계 :
		galleryDAO.update(gallery);
		
		// 4단계 : 저장할 것이 없다. 끊을거다. 요청을 끊을거야. detail을 새로 접속할꺼니깐..
		
		return "redirect:/gallery/datail?gallery_id="+gallery.getGallery_id();
	}
	
	// 글 삭제 요청처리
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(int gallery_id) {
		galleryDAO.delete(gallery_id);
		return "redirect:/gallery/list";
	}
}
