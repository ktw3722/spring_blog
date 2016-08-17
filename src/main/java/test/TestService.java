package test;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TestService {
	@Autowired
	private DAO dao;
	private CDAO cdao;
	private SqlSessionTemplate mybatis;

}
