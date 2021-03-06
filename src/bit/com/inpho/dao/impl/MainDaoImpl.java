package bit.com.inpho.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.inpho.dao.MainDao;
import bit.com.inpho.dto.MainPostDto;
import bit.com.inpho.dto.searchDto;

@Repository
public class MainDaoImpl implements MainDao{
	@Autowired
	SqlSessionTemplate sql;
	String ns = "Main.";
	@Override
	public List<MainPostDto> getNewFeed() {
		return sql.selectList(ns+"newFeedList");
	}
	@Override
	public List<MainPostDto> getNewFeed(int userSeq) {
		return sql.selectList(ns+"newFeedListLogin", userSeq);
	}
	@Override
	public List<MainPostDto> getSearchList(String search) {
		return sql.selectList(ns+"searchKeywordList", search);
	}
	@Override
	public List<MainPostDto> getSearchList(searchDto search) {
		return sql.selectList(ns+"searchKeywordListLogin", search);
	}
	@Override
	public List<MainPostDto> getNewMoreFeed(boolean cate, searchDto search) {
		if(cate) {return sql.selectList(ns+"newMoreFeedListLogin", search);}
		//false== 로그인이 되어있지 않음
		return sql.selectList(ns+"newMoreFeedList", search);
	}
	@Override
	public List<MainPostDto> getSearchMoreList(boolean cate, searchDto search) {
		if(cate) {return sql.selectList(ns+"searchKeywordMoreList", search);}
		//false== 로그인이 되어있지 않음
		return sql.selectList(ns+"searchKeywordMoreListLogin", search);
	}
	@Override //로그인을 안한경우 가져가는 리스트
	public List<MainPostDto> getHotFeedList() {
		return sql.selectList(ns+"hotFeedStartListNoLogin");
	}
	@Override //로그인을 한경우 인기게시글 가져가는 list
	public List<MainPostDto> getHotFeedList(searchDto search) {
		return sql.selectList(ns+"hotFeedStartListLogin", search);
	}
	@Override
	public List<MainPostDto> getHotFeedMoreList(boolean cate, searchDto search) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
