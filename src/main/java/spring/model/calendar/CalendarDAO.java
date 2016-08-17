package spring.model.calendar;
 
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component
public class CalendarDAO {
  @Autowired
  private SqlSessionTemplate mybatis;
  
  public void setMybatis(SqlSessionTemplate mybatis) {
	this.mybatis = mybatis;
}

public CalendarDAO(){
    System.out.println("--> CalendarDAO created.");
  }
  
  /**
   * 등록
   * @param dto 저장할 데이터 객체
   * @return 성공시 PK(calendarno 컬럼) 값을 Object 타입으로 리턴
   */
  public Object create(Object dto){
    return mybatis.insert("calendar.create", dto);
  }
  
  /**
   * 목록, ArrayList return
   * @return SELECT의 레코드수가 2개이상이면 queryForList() 메소드 이용
   */
  public Object list(){
    return mybatis.selectList("calendar.list");
  }
  
  /**
   * 조회
   * @param calendarno
   * @return queryForObject 한건의 레코드 Select
   * @throws SQLException
   */
  public Object read(Object calendarno){
    return mybatis.selectOne("calendar.read", calendarno);
  }
  
  /**
   * 수정
   * @param dto
   * @return obj 변경된 레코드 갯수
   * @throws SQLException
   */
  public Object update(Object dto){
    return mybatis.update("calendar.update", dto);
  }
  
  public Object delete(Object calendarno){
    return mybatis.delete("calendar.delete", calendarno);
  }
  
  /**
   * 레이블 목록
   * @return ArrayList가 Object 타입으로 저장되어 리턴
   * @throws SQLException
   */
  public Object labelList(Object labeldate){
    Object obj = mybatis.selectList("calendar.label", labeldate);
    
    return obj;
  }  
}