package com.sinse.memberapp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sinse.memberapp.model.Dept;
import com.sinse.memberapp.mybatis.MybatisConfig;

public class DeptDAO {

	MybatisConfig config = MybatisConfig.getInstance();
	
	// 부서 정보 가져오기
	public List<Dept> selectAll() {
		SqlSession sqlSession = config.getSqlSession();
		List<Dept> list = sqlSession.selectList("Dept.selectAll");
		sqlSession.close();
		return list;
	}
}
