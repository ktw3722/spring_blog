package spring.model.img;

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

public class ImgDAOTest {
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
	public void testCreate() {
		ImgDAO dao=(ImgDAO)beans.getBean("imgdao");
		ImgDTO dto=new ImgDTO();
		dto.setWname("홍길동");
		dto.setTitle("제목");
		dto.setContent("내용");
		dto.setPasswd("1234");
		dto.setFilename("파일.jpg");
		assertEquals(1, dao.create(dto));
	}

	@Test @Ignore
	public void testUpdate() {
		ImgDAO dao=(ImgDAO)beans.getBean("imgdao");
		ImgDTO dto=new ImgDTO();
		dto.setWname("홍길동");
		dto.setTitle("제목수정");
		dto.setContent("내용수정");
		dto.setPasswd("1234");
		dto.setFilename("파일수정.jpg");
		dto.setImgno(8);
		assertEquals(1, dao.update(dto));
	}

	@Test @Ignore
	public void testPasswdCheck() {
		ImgDAO dao=(ImgDAO)beans.getBean("imgdao");
		assertEquals(1, dao.passwdCheck(9, "1234"));
	}

	@Test @Ignore
	public void testList() {
		ImgDAO dao=(ImgDAO)beans.getBean("imgdao");
		Map map=new HashMap();
		map.put("col", "");
		map.put("word", "");
		map.put("sno", 1);
		map.put("eno", 5);
		List<ImgDTO> list=dao.list(map);
		assertEquals(5, list.size());
	}

	@Test @Ignore
	public void testRead() {
		ImgDAO dao=(ImgDAO)beans.getBean("imgdao");
		ImgDTO dto=dao.read(8);
		assertEquals("홍길동", dto.getWname());
		assertEquals("제목", dto.getTitle());
		assertEquals("내용", dto.getContent());
		assertEquals("1234", dto.getPasswd());
		assertEquals("파일.jpg", dto.getFilename());
		
	}

	@Test @Ignore
	public void testAddAnsnum() {
		ImgDAO dao=(ImgDAO)beans.getBean("imgdao");
		dao.addAnsnum(1, 0);
		ImgDTO dto=dao.readReply(8);
		assertEquals(2, dto.getAnsnum());
	}

	@Test @Ignore
	public void testTotal() {
		ImgDAO dao=(ImgDAO)beans.getBean("imgdao");
		assertEquals(7, dao.total("", ""));
	}

	@Test @Ignore
	public void testImgRead() {
		ImgDAO dao=(ImgDAO)beans.getBean("imgdao");
		ImgDTO dto=dao.imgRead(9);
		assertEquals(9, dto.getImgno());
		assertEquals("파일.jpg", dto.getFilename());
	}

	@Test @Ignore
	public void testDelete() {
		ImgDAO dao=(ImgDAO)beans.getBean("imgdao");
		assertEquals(1, dao.delete(8));
	}

	@Test @Ignore
	public void testReply() {
		ImgDAO dao=(ImgDAO)beans.getBean("imgdao");
		ImgDTO dto=new ImgDTO();
		dto.setWname("홍길동");
		dto.setTitle("제목");
		dto.setContent("내용");
		dto.setPasswd("1234");
		dto.setGrpno(1);
		dto.setIndent(0);
		dto.setAnsnum(0);
		dto.setFilename("파일.jpg");
		assertEquals(1, dao.reply(dto));
	}

	@Test @Ignore
	public void testReadReply() {
		ImgDAO dao=(ImgDAO)beans.getBean("imgdao");
		ImgDTO dto=dao.readReply(1);
		assertEquals(1, dto.getImgno());
		assertEquals("1", dto.getTitle());
		assertEquals(1, dto.getGrpno());
		assertEquals(0, dto.getIndent());
		assertEquals(0, dto.getAnsnum());
	}

}
