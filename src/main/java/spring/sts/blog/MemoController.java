package spring.sts.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.memo.memoDAO;
import spring.model.memo.memoVO;
import spring.utility.blog.Paging;
import spring.utility.blog.Utility;


@Controller
public class MemoController {
	
	@Autowired
	memoDAO dao;
	
	@RequestMapping(value="/memo/delete",method=RequestMethod.POST)
	public String delete(int memono){

		if(dao.delete(memono)>0){

			return "redirect:/memo/list";
		}else{
			return "/error/error";
		}

	}
	@RequestMapping(value="/memo/delete",method=RequestMethod.GET)
	public String delete(){
		
		return "/memo/delete";
	}
	@RequestMapping(value="/memo/update",method=RequestMethod.POST)
	public String update(memoVO vo,HttpServletRequest request,Model model,String col,String word,int nowPage){	
		vo.setContent(request.getParameter("content"));
		vo.setMemono(Integer.parseInt(request.getParameter("memono")));
		vo.setTitle(request.getParameter("title"));
		
		if(dao.update(vo)>0){
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			return "redirect:/memo/list";
		}else{
			return "/error/error";
		}
	}
	@RequestMapping(value="/memo/update",method=RequestMethod.GET)
	public String update(int memono,Model model){
		
		memoVO vo = dao.read(memono);
		model.addAttribute("vo", vo);
		return "/memo/update";
	}
	@RequestMapping(value="/memo/create",method=RequestMethod.POST)
	public String create(memoVO vo,HttpServletRequest request){
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		if(dao.create(vo)>0){
			return "redirect:/memo/list";
		}else{
			return "/error/error";
		}
	}
	@RequestMapping(value="/memo/create",method=RequestMethod.GET)
	public String create(){
		
		return "/memo/create";
	}
	@RequestMapping("/memo/read")
	public String read(int memono,Model model){
		dao.upViewcnt(memono);//조회수 증가
		memoVO vo=dao.read(memono);//한건의 레코드 가져오기
		String content= vo.getContent().replaceAll("\r\n","<br>");
		String wdate=vo.getWdate().substring(0,10);
		vo.setContent(content);
		vo.setWdate(wdate);
		model.addAttribute("vo", vo);
		return "/memo/read";
	}
	@RequestMapping("/memo/list")
	public String list(HttpServletRequest request,Model model){
		int nowPage=1;
		int recordPerPage=5;
		if(request.getParameter("nowPage")!=null){
			nowPage=Integer.parseInt(request.getParameter("nowPage"));
		}
		int sno=((nowPage-1)*recordPerPage)+1;
		int eno=nowPage*recordPerPage;
		String col=Utility.nullCheck(request.getParameter("col"));
		String word=Utility.nullCheck(request.getParameter("word"));

		if(col.equals("total")) word="";

		Map map =new HashMap();
		map.put("sno",sno);
		map.put("eno",eno);
		map.put("col",col);
		map.put("word",word);

		List<memoVO> list=dao.list(map);

		int total=dao.total(col,word);
		String paging=new Paging().paging2(total, nowPage, recordPerPage, col, word);
		
		model.addAttribute("col", col);
		model.addAttribute("list", list);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("paging", paging);
		return "/memo/list";
	}
}
