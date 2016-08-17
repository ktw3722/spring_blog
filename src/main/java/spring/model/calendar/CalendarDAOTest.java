package spring.model.calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

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

public class CalendarDAOTest {
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
		CalendarDAO dao=(CalendarDAO) beans.getBean("calendardao");
		CalendarDTO dto=new CalendarDTO();
		dto.setLabeldate("2016-07-15");
		dto.setLabel("합격자 발표");
		dto.setTitle("정보처리기사 실기 합격자 발표");
		dto.setContent("확인 할것");
		assertEquals(1, dao.create(dto));
	}

	@Test @Ignore
	public void testList() {
		CalendarDAO dao=(CalendarDAO) beans.getBean("calendardao");
		List list=(List) dao.list();
		assertEquals(5, list.size());
	}

	@Test @Ignore
	public void testRead() {
		CalendarDAO dao=(CalendarDAO) beans.getBean("calendardao");
		CalendarDTO dto=(CalendarDTO) dao.read(8);
		assertEquals("2016-07-15", dto.getLabeldate());
		assertEquals("합격자 발표", dto.getLabel());
		assertEquals("정보처리기사 실기 합격자 발표", dto.getTitle());
		assertEquals("확인 할것", dto.getContent());
	}

	@Test @Ignore
	public void testUpdate() {
		CalendarDAO dao=(CalendarDAO) beans.getBean("calendardao");
		CalendarDTO dto=new CalendarDTO();
		dto.setLabeldate("2016-07-15");
		dto.setLabel("합격자 발표 수정");
		dto.setTitle("정보처리기사 실기 합격자 발표 수정");
		dto.setContent("확인 할것");
		dto.setCalendarno(8);
		assertEquals(1, dao.update(dto));
	}

	@Test @Ignore
	public void testDelete() {
		CalendarDAO dao=(CalendarDAO) beans.getBean("calendardao");
		assertEquals(1, dao.delete(8));
	}

	@Test @Ignore
	public void testLabelList() {
		CalendarDAO dao=(CalendarDAO) beans.getBean("calendardao");
		List list=(List) dao.labelList("2016-06-15");
		assertEquals(2, list.size());
		
	}

}
