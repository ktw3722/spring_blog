package test;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import spring.model.inter.DAOMyBatisInter;

public interface ITestDAO extends DAOMyBatisInter {
	
	public int dd(Map map, SqlSessionTemplate mybatis) throws Exception;

}
