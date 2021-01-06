package com.koreait.fashionshop.model.payment.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.CartException;
import com.koreait.fashionshop.model.domain.Cart;

@Repository
public class MybatisCartDAO implements CartDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List selectAll(int member_id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Cart select(int cart_id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void insert(Cart cart) throws CartException{
		int result = sqlSessionTemplate.insert("Cart.insert", cart);
		if(result==0) {
			throw new CartException("장바구니에 담지 못했습니다.");
		}
		
	}

	
	public void update(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(int cart_id) {
		// TODO Auto-generated method stub
		
	}

}
