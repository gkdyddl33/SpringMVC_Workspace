package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.Product;

public interface ProductDAO {
	public List selectAll();		// 모든상품
	public List selectById(int subcategory_id);	// 하위 카테고리에 소속된 모든 상품
	public Product select(int product_id);	
	public void insert(Product product);			// 상품등록
	public void update(Product product);		// 상품수정
	public void delete(int product_id);		// 상품삭제
}
