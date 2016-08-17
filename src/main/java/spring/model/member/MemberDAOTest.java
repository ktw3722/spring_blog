package spring.model.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MemberDAOTest {
	private static BeanFactory beans;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Resource resource = new ClassPathResource("blog.xml");
		beans=new XmlBeanFactory(resource);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test @Ignore
	public void testPwFind() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		assertEquals("1234", dao.pwFind("ktw3722", "김성빈"));
	}

	@Test @Ignore
	public void testIdFind() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		assertEquals("ktw3722", dao.idFind("김성빈", "ktw3722@naver.com"));
	}

	@Test @Ignore
	public void testGetGrade() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		assertEquals("A", dao.getGrade("admin"));
	}

	@Test @Ignore
	public void testLoginCheck() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		assertEquals(1, dao.loginCheck("ktw3722", "1234"));
	}

	@Test @Ignore
	public void testDelete() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		assertEquals(1, dao.delete("kkkk"));
	}

	@Test @Ignore
	public void testGetFname() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		assertEquals("(2)다운로드 (1).png", dao.getFname("ktw3722"));
	}

	@Test @Ignore
	public void testUpdate() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		MemberDTO dto=new MemberDTO();
		dto.setId("kkkk");
		dto.setPasswd("1234");
		dto.setMname("홍길동");
		dto.setTel("010-1234-5678");
		dto.setEmail("kkkk@naver.com");
		dto.setZipcode("12345");
		dto.setAddress1("여기수정");
		dto.setAddress2("저기수정");
		dto.setJob("무직");
		dto.setFname("파일수정.jpg");
		assertEquals(1, dao.update(dto));
	}

	@Test @Ignore
	public void testUpdatePasswd() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		assertEquals(1, dao.updatePasswd("ktw3722", "1234"));
	}

	@Test @Ignore
	public void testUpdateFile() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		assertEquals(1, dao.updateFile("ktw3722", "(2)다운로드 (1).png"));

	}

	@Test @Ignore
	public void testRead() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		MemberDTO dto=dao.read("kkkk");
		assertEquals("kkkk", dto.getId());
		assertEquals("1234", dto.getPasswd());
		assertEquals("홍길동", dto.getMname());
		assertEquals("010-1234-5678", dto.getTel());
		assertEquals("kkkk@naver.com", dto.getEmail());
		assertEquals("12345", dto.getZipcode());
		assertEquals("여기", dto.getAddress1());
		assertEquals("저기", dto.getAddress2());
		assertEquals("무직", dto.getJob());
		assertEquals("파일.jpg", dto.getFname());
	}

	@Test @Ignore
	public void testTotal() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		assertEquals(8, dao.total("", ""));
	}

	@Test @Ignore
	public void testList() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		Map map=new HashMap();
		map.put("word", "");
		map.put("col", "");
		map.put("sno", 1);
		map.put("eno", 5);
		List<MemberDTO> list=dao.list(map);
		assertEquals(5, list.size());
	}

	@Test @Ignore
	public void testCreate() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		MemberDTO dto=new MemberDTO();
		dto.setId("kkkk");
		dto.setPasswd("1234");
		dto.setMname("홍길동");
		dto.setTel("010-1234-5678");
		dto.setEmail("kkkk@naver.com");
		dto.setZipcode("12345");
		dto.setAddress1("여기");
		dto.setAddress2("저기");
		dto.setJob("무직");
		dto.setFname("파일.jpg");
		assertEquals(1, dao.create(dto));
	}

	@Test @Ignore
	public void testDuplicateEmail() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		assertEquals(1, dao.duplicateEmail("ktw3722@naver.com"));
	}

	@Test @Ignore
	public void testDuplicateID() {
		MemberDAO dao=(MemberDAO) beans.getBean("memberdao");
		assertEquals(1, dao.duplicateID("ktw3722"));
	}

}
