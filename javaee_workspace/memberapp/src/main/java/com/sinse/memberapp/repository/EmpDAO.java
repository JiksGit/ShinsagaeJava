package com.sinse.memberapp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sinse.memberapp.model.Emp;
import com.sinse.memberapp.mybatis.MybatisConfig;

public class EmpDAO {
	
	MybatisConfig config = MybatisConfig.getInstance();
	
	public List<Emp> selectAll(){
		SqlSession sqlSession = config.getSqlSession();
		List<Emp> list = sqlSession.selectList("Emp.selectAll");
		sqlSession.close();
		return list;
	}

}
