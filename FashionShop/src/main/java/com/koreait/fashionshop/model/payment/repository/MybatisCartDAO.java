package com.koreait.fashionshop.model.payment.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.CartException;
import com.koreait.fashionshop.model.domain.Cart;
import com.koreait.fashionshop.model.domain.Member;

@Repository
public class MybatisCartDAO implements CartDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List selectAll(int member_id) {		
		return sqlSessionTemplate.selectList("Cart.selectAll",member_id); // 바인드변수 파라미터 받기
	}

	
	public Cart select(int cart_id) {
		// TODO Auto-generated method stub
		return null;
	}

	// 중복체크!!!
	public void duplicateCheck(Cart cart) throws CartException{
		List list = sqlSessionTemplate.selectList("Cart.duplicateCheck", cart);
		if(list.size()>0) {
			throw new CartException("장바구니에 이미 담겨진 상품입니다.");
		}
	}
	
	public void insert(Cart cart) throws CartException{
		int result = sqlSessionTemplate.insert("Cart.insert", cart);
		if(result==0) {
			throw new CartException("장바구니에 담지 못했습니다.");
		}
		
	}

	
	public void update(Cart cart) throws CartException{
		int result =  sqlSessionTemplate.update("Cart.update",cart);
		if(result==0) {
			throw new CartException("장바구니 수정 실패");
		}
		
	}

	
	public void delete(int cart_id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Cart cart) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Member member) throws CartException{
		int result = sqlSessionTemplate.delete("Cart.delete", member.getMember_id());
		if(result==0) {
			throw new CartException("장바구니 삭제 실패");
		}
	}
}
