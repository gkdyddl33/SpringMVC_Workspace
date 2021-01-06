package com.koreait.fashionshop.model.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.fashionshop.exception.NoticeException;
import com.koreait.fashionshop.model.domain.Notice;
import com.koreait.fashionshop.model.product.repository.NoticeDAO;
@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDAO noticeDAO;
	
	public List selectAll() {
		return noticeDAO.selectAll();
	}

	@Override
	public Notice select(int notice_id) {
		Notice notice = noticeDAO.select(notice_id);		
		return notice;
	}

	@Override
	public void insert(Notice notice) throws NoticeException{
		noticeDAO.insert(notice);
		
	}

	@Override
	public void update(Notice notice) throws NoticeException{
		noticeDAO.update(notice);
		
	}

	@Override
	public void delete(int notice_id) throws NoticeException{
		noticeDAO.delete(notice_id);
		
	}

}
