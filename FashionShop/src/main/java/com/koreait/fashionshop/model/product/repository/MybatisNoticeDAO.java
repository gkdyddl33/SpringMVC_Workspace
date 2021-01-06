package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.NoticeException;
import com.koreait.fashionshop.model.domain.Notice;

@Repository
public class MybatisNoticeDAO implements NoticeDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List selectAll() {
		List list = null;
		list = sqlSessionTemplate.selectList("Notice.selectAll");
		return list;
	}

	@Override
	public Notice select(int notice_id) {
		Notice notice = null;
		notice = sqlSessionTemplate.selectOne("Notice.select", notice_id);
		return notice;
	}

	@Override
	public void insert(Notice notice) throws NoticeException{
		int result = sqlSessionTemplate.insert("Notice.insert", notice);
		if(result==0) {
			throw new NoticeException("글 등록에 실패하였습니다.");
		}
	}

	@Override
	public void update(Notice notice) throws NoticeException{
		int result = sqlSessionTemplate.update("Notice.update", notice);
		if(result==0) {
			throw new NoticeException("글 수정이 실패하였습니다.");
		}
	}

	@Override
	public void delete(int notice_id) throws NoticeException{
		int result = sqlSessionTemplate.delete("Notice.delete", notice_id);
		if(result==0) {
			throw new NoticeException("글 삭제가 실패하였습니다.");
		}
	}

}
