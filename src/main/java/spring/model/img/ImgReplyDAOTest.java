package spring.model.img;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

public class ImgReplyDAOTest {
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
	public void testRcount() {
		ImgReplyDAO dao=(ImgReplyDAO) beans.getBean("imgreplydao");
		assertEquals(2, dao.rcount(9));
	}

	@Test @Ignore
	public void testCreate() {
		ImgReplyDAO dao=(ImgReplyDAO) beans.getBean("imgreplydao");
		ImgReplyDTO dto=new ImgReplyDTO();
		dto.setContent("내용");
		dto.setId("ktw3722");
		dto.setImgno(9);
		assertTrue(dao.create(dto));
	}

	@Test @Ignore
	public void testRead() {
		ImgReplyDAO dao=(ImgReplyDAO) beans.getBean("imgreplydao");
		ImgReplyDTO dto=dao.read(10);
		assertEquals("내용", dto.getContent());
		assertEquals("ktw3722", dto.getId());
		assertEquals(9, dto.getImgno());
	}

	@Test @Ignore
	public void testUpdate() {
		ImgReplyDAO dao=(ImgReplyDAO) beans.getBean("imgreplydao");
		ImgReplyDTO dto=new ImgReplyDTO();
		dto.setContent("내용");
		dto.setId("ktw3722");
		dto.setImgno(9);
		dto.setRnum(10);
		assertTrue(dao.update(dto));
	}

	@Test @Ignore
	public void testList() {
		ImgReplyDAO dao=(ImgReplyDAO) beans.getBean("imgreplydao");
		Map map=new HashMap();
		map.put("imgno", 9);
		map.put("sno", 1);
		map.put("eno", 5);
		List<ImgReplyDTO> list=dao.list(map);
		assertEquals(2, list.size());

	}

	@Test @Ignore
	public void testTotal() {
		ImgReplyDAO dao=(ImgReplyDAO) beans.getBean("imgreplydao");
		assertEquals(2, dao.total(9));
	}

	@Test @Ignore
	public void testDelete() {
		ImgReplyDAO dao=(ImgReplyDAO) beans.getBean("imgreplydao");
		assertTrue(dao.delete(10));
	}

	@Test @Ignore
	public void testBdelete() {
		ImgReplyDAO dao=(ImgReplyDAO) beans.getBean("imgreplydao");
		try {
			assertEquals(2, dao.bdelete(9));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
