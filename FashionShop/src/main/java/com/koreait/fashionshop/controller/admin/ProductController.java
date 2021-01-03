package com.koreait.fashionshop.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.common.FileManager;
import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.SubCategory;
import com.koreait.fashionshop.model.product.service.ProductService;
import com.koreait.fashionshop.model.product.service.SubCategoryService;
import com.koreait.fashionshop.model.product.service.TopCategoryService;

@Controller
// 관리자 모드에서의 상품에 대한 요청처리
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private TopCategoryService topCategoryService;
	@Autowired
	private SubCategoryService subCategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private FileManager fileManager;
	
	// 상위카테고리
	@RequestMapping(value="/admin/product/registform",method = RequestMethod.GET)
	public ModelAndView getTopList() {
		// 3단계 : 로직 객체에 일을 시킨다.
		List topList = topCategoryService.selectAll();
		// 4단계 : 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList", topList);
		mav.setViewName("admin/product/regist_form");
		return mav;
	}
	// 하위카테고리
	// spring에서는 java 객체와 json간 변환(converting)을 자동으로 처리해주는 라이브러리를 지원한다.
	@RequestMapping(value="/admin/product/sublist",method = RequestMethod.GET)
	@ResponseBody
	public List getSubList(int topcategory_id) {
		List<SubCategory> subList = subCategoryService.selectAllById(topcategory_id);
		return subList;
	}
	
/* --- 고생하는 방법
@RequestMapping(value="/admin/product/sublist",method = RequestMethod.GET,produces="application/json;charset=utf8") // application/json : json을 파싱할 필요없이 문자열로 안보게 됨..
@ResponseBody		// jsp와 같은 view 페이지가 아닌, 단순 데이터만 전송시..
public String getSubList(int topcategory_id) {// 반환형을 String 으로 하면은.. 상품등록 폼의 regist_form을 유지할려고 한다..먹통이 되버림.. = return "ha haha"
	logger.debug("topcategory_id:"+topcategory_id);
	
	List<SubCategory> subList = subCategoryService.selectAllById(topcategory_id);
	// 리스트를 json으로 변형하여 보내줘야 함..
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	sb.append("\"subList\" : [");
	for(int i =0;i<subList.size();i++) {
		SubCategory subCategory = subList.get(i);
		sb.append("{");
		sb.append("\"subcategory_id\":"+subCategory.getSubcategory_id()+",");
		sb.append("\"topcategory_id\":"+subCategory.getTopcategory_id()+",");			
		sb.append("\"name\":\""+subCategory.getName()+"\"");
		if(i<subList.size()-1) {
			sb.append("},");				
		}else {
			sb.append("}");				
		}
	}
	sb.append("]");
	sb.append("}");
	return sb.toString();
}
*/
	
	// 상품목록
	@RequestMapping(value="/admin/product/list", method=RequestMethod.GET)
	public ModelAndView getProductList() {
		ModelAndView mav = new ModelAndView("admin/product/product_list");		
		return mav;
	}
	
	// 상품등록 폼
	@RequestMapping(value="/admin/product/registform")
	public String registForm() {
		return "admin/product/regist_form";
	}
	
	// 상품 등록
	@RequestMapping(value="/admin/product/regist",method = RequestMethod.POST)
	public String registProduct(Product product) {// 상품, 사이즈(배열), 이미지(배열), 색상(배열)
		logger.debug("하위카테고리 "+product.getSubcategory_id());
		logger.debug("상품명 "+product.getProduct_name());
		logger.debug("가격 "+product.getPrice());
		logger.debug("브랜드 "+product.getBrand());
		logger.debug("상세내용 "+product.getDetail());
		logger.debug("업로드 이미지명 "+product.getRepImg().getOriginalFilename());
		
		for(int i =0;i<product.getFit().length;i++) {// fit 확인차..
			String fit = product.getFit()[i];
			logger.debug("지원 사이즈는  "+fit);			
		}
		
		// 대표이미지업로드(현재날짜로처리)
		// 어떤파일명으로, 어디에 저장할지 결정
		Long time = System.currentTimeMillis();
		String ext = FileManager.getExtend(product.getRepImg().getOriginalFilename());
		String filename = time+"."+ext;
		try {
			product.getRepImg().transferTo(new File(fileManager.getSaveDir()+filename));
			logger.debug(filename);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// db 에 넣기
		productService.regist(product);
		return "redirect:/admin/product/list";	// 등록 -> 목록
	}
	
	// 상품상세	
	
	// 상품수정

	// 상품삭제
	
}
