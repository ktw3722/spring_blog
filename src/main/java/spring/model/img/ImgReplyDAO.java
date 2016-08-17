package spring.model.img;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImgReplyDAO {



	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public int rcount(int imgno){
	    return sqlSessionTemplate.selectOne("imgreply.rcount", imgno);
	}
	
	public boolean create(ImgReplyDTO dto) {
		boolean flag = false;

		int cnt = sqlSessionTemplate.insert("imgreply.create", dto);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	public ImgReplyDTO read(int rnum) {

		return  sqlSessionTemplate.selectOne("imgreply.read", rnum);

	}

	public boolean update(ImgReplyDTO dto) {
		boolean flag = false;

		int cnt = sqlSessionTemplate.update("imgreply.update", dto);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	public List<ImgReplyDTO> list(Map map) {

		return sqlSessionTemplate.selectList("imgreply.list", map);
	}

	public int total(int imgno) {
		return sqlSessionTemplate.selectOne("imgreply.total", imgno);
	}

	public boolean delete(int rnum) {
		boolean flag = false;
		int cnt = sqlSessionTemplate.delete("imgreply.delete", rnum);
		if (cnt > 0)
			flag = true;

		return flag;
	}

	/** 하나의 글의 여러댓글들 삭제 */
	public int bdelete(int imgno) throws Exception {
		return sqlSessionTemplate.delete("imgreply.bdelete", imgno);

	}
}