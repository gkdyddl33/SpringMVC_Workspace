package com.koreait.fashionshop.model.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.fashionshop.exception.CartException;
import com.koreait.fashionshop.model.domain.Cart;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.payment.repository.CartDAO;
@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private CartDAO cartDAO;
	
	public List selectCartList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectCartList(int member_id) {
		return cartDAO.selectAll(member_id);
	}

	@Override
	public Cart selectCart(int cart_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Cart cart) throws CartException{
		cartDAO.duplicateCheck(cart);	// 에러가 발생하면 아래 코드 삽입을 실행 안하고 예외처리를 발생시킨다.
		cartDAO.insert(cart);		
	}

	@Override
	public void update(List<Cart> cartList) throws CartException{
		// ------ 상품갯수만큼 수정요청
		for(Cart cart : cartList) {
			cartDAO.update(cart);			
		}
		
	}

	@Override
	public void delete(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Member member) throws CartException{
		cartDAO.delete(member);
	}

}
