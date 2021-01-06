package com.koreait.fashionshop.model.payment.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.Cart;

public interface CartDAO {
	public List selectAll();	// 회원 구분없이 모든 데이터 가져오기
	public List selectAll(int member_id);	// 특정 회원의 구분 있는 장바구니 내역
	public Cart select(int cart_id);
	
	public void insert(Cart cart);
	public void update(Cart cart);
	public void delete(int cart_id);
}