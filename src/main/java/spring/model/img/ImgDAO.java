package spring.model.img;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.MyBatisSystemException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.utility.blog.DBClose;
import spring.utility.blog.DBOpen;
@Repository
public class ImgDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}

	/**
	 * 1. create
	 * 
	 * @param dto
	 * @return
	 */
	public int create(ImgDTO dto) {
		return mybatis.insert("img.create", dto);
	}

	/**
	 * 2. update
	 * 
	 * @param dto
	 * @return
	 */
	public int update(ImgDTO dto) {
		return mybatis.update("img.update", dto);
	}

	/**
	 * 4.passwdCheck
	 * 
	 * @param dto
	 * @return
	 */
	public int passwdCheck(int imgno, String passwd) {
		Map map=new HashMap();
		map.put("imgno", imgno);
		map.put("passwd", passwd);
		return mybatis.selectOne("img.passwdCheck", map);
	}

	/**
	 * 5.list
	 * 
	 * @param dto
	 * @return
	 * 
	 */

	public List<ImgDTO> list(Map map) {
		return mybatis.selectList("img.list", map);
	}

	/**
	 * 6.read
	 * 
	 * @param dto
	 * @return
	 */
	
	public ImgDTO read(int imgno) {
		return mybatis.selectOne("img.read", imgno);
	}
	
	/**
	 * 7.addAnsnum
	 * 
	 * @param dto
	 * @return
	 */
	
	public void addAnsnum(int grpno, int ansnum){ 
		Map map=new HashMap();
		map.put("grpno", grpno);
		map.put("ansnum", ansnum);
	     mybatis.update("img.addAnsnum", map);
	  } 
	
	/**
	 * 8.total
	 * 
	 * @param dto
	 * @return
	 */	
	public int total(String col, String word){
		Map map=new HashMap();
		map.put("col", col);
		map.put("word", word);
		return mybatis.selectOne("img.total", map);
	}
	
	/**
	 * 9.imgRead
	 * 
	 * @param dto
	 * @return
	 */	
	
	public ImgDTO imgRead(int imgno){
//		List<String> list = new ArrayList<String>();
//		Connection con = DBOpen.getConnection();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		StringBuffer sql = new StringBuffer();
//
//		sql.append("  SELECT * FROM    ");
//		sql.append("    (    ");
//		sql.append("       select imgno, filename,     ");
//		sql.append("           lag(imgno,2)     over (order by imgno) pre_imgno2,     ");
//		sql.append("           lag(filename,2)  over (order by imgno) pre_file2,      ");
//		sql.append("           lag(imgno,1)     over (order by imgno ) pre_imgno1,     ");
//		sql.append("           lag(filename,1)  over (order by imgno ) pre_file1,     ");
//		sql.append("           lead(imgno,1)    over (order by imgno) nex_imgno1,     ");
//		sql.append("           lead(filename,1) over (order by imgno) nex_file1,   ");
//		sql.append("           lead(imgno,2)    over (order by imgno) nex_imgno2,     ");
//		sql.append("           lead(filename,2) over (order by imgno) nex_file2    ");
//		sql.append("           from (    ");
//		sql.append("                SELECT imgno, filename     ");
//		sql.append("                FROM img   ");
//		sql.append("                ORDER BY imgno DESC    ");		
//		sql.append("           )    ");
//		sql.append("    )    ");
//		sql.append("    WHERE imgno = ?   ");
//		
//		try {
//			pstmt = con.prepareStatement(sql.toString());
//			pstmt.setInt(1, imgno);
//			
//			rs = pstmt.executeQuery();
//			
//			
//			if(rs.next()){
//				String [] arr = new String [10];
//				
//				//01234 = imgno(2전 1전 자기자신 1다음 2다음)
//				//56789 = filename
//				arr[0] = rs.getString("pre_imgno2");
//				arr[5] = rs.getString("pre_file2");
//				
//				arr[1] = rs.getString("pre_imgno1");
//				arr[6] = rs.getString("pre_file1");
//				
//				arr[2] = rs.getString("imgno");
//				arr[7] = rs.getString("filename");
//				
//				arr[3] = rs.getString("nex_imgno1");
//				arr[8] = rs.getString("nex_file1");
//				
//				arr[4] = rs.getString("nex_imgno2");
//				arr[9] = rs.getString("nex_file2");
//				
//				for(int i = 0; i<10; i++){
//					list.add(arr[i]);
//				}
//								
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBClose.close(con, pstmt, rs);
//		}	
		return mybatis.selectOne("img.imgRead", imgno);
	}
	
	/**
	 * 10.delete
	 * 
	 * @param dto
	 * @return
	 */	
	
	public int delete(int imgno) {
		return mybatis.delete("img.delete", imgno);
	}
	
	/**
	 * 답변등록
	 * 
	 * @param dto
	 * @return
	 */
	public int reply(ImgDTO dto) {		
		return mybatis.insert("img.reply", dto);
	}

	/**
	 * 답변처리를 위해서 부모의 grpno, indent, ansnum을 가져오는 것
	 * title, imgno 포함해서 가져오기
	 * 
	 * @param imgno
	 * @return
	 */
	public ImgDTO readReply(int imgno) {
		return mybatis.selectOne("img.readReply", imgno);
	}
}

	




