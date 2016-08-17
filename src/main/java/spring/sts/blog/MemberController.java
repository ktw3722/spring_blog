package spring.sts.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.model.member.MemberDAO;
import spring.model.member.MemberDTO;
import spring.utility.blog.Paging;
import spring.utility.blog.Utility;



@Controller
public class MemberController {
	@Autowired
	MemberDAO dao;
	
	@RequestMapping(value="/member/pwFind",method=RequestMethod.POST)
	public String pwFind(String mname,String id,Model model){

		String passwd=dao.pwFind(id, mname);
		model.addAttribute("passwd", passwd);
		return "/member/pwFindProc";
	}
	@RequestMapping(value="/member/pwFind",method=RequestMethod.GET)
	public String pwFind(){
		
		return "/member/pwFind";
	}
	@RequestMapping(value="/member/idFind",method=RequestMethod.POST)
	public String idFind(String mname,String email,Model model){
		
		String id=dao.idFind(mname, email);
		model.addAttribute("id", id);
		return "/member/idFindProc";
	}
	@RequestMapping(value="/member/idFind",method=RequestMethod.GET)
	public String idFind(){
		
		return "/member/idFind";
	}
	@RequestMapping(value="/member/delete",method=RequestMethod.POST)
	public String delete(String id,String oldfile,HttpServletRequest request,HttpSession session){
		String basePath=request.getRealPath("/storage");
		
		if(dao.delete(id)>0){
			if(oldfile!=null&&!oldfile.equals("member.jpg")){
				Utility.deleteFile(basePath, oldfile);
				session.invalidate();}
				return "redirect:/";
			}else{
				return "/error/error";}
			
	
}
	@RequestMapping(value="/member/delete",method=RequestMethod.GET)
	public String delete(String id,String oldfile,HttpSession session,Model model){
		if(id==null){
			id=(String)session.getAttribute("id");
		}

		if(oldfile==null){
			oldfile=dao.getFname(id);
		}
		model.addAttribute("id", id);
		model.addAttribute("oldfile", oldfile);
		return "/member/delete";
	}
	@RequestMapping(value="/member/updatePw",method=RequestMethod.POST)
	public String updatePw(String id,String passwd){
		
		if(dao.updatePasswd(id, passwd)>0){
			return "redirect:/";
		}else{
			return "/error/error";
		}
	}
	@RequestMapping(value="/member/updatePw",method=RequestMethod.GET)
	public String updatePw(){
		
		return "/member/updatePw";
	}
	@RequestMapping(value="/member/updateFile",method=RequestMethod.POST)
	public String updateFile(HttpServletRequest request,String id,String oldfile,MultipartFile fnameMF){
		String basePath=request.getRealPath("/storage");
		String fname="member.jpg";
		int size = (int)fnameMF.getSize();
		
		if(size>0){
			if(oldfile!=null&&!oldfile.equals("member.jpg")){
				Utility.deleteFile(basePath, oldfile);
			}
			fname=Utility.saveFile(fnameMF, basePath);
		}
		 if(dao.updateFile(id, fname)>0){
			 return "redirect:/";
		 }else{
			 return "/error/error";
		 }
	
	}
	@RequestMapping(value="/member/updateFile",method=RequestMethod.GET)
	public String updateFile(){
		
		return "/member/updateFile";
	}
	
	@RequestMapping(value="/member/update",method=RequestMethod.POST)
	public String update(String id,MemberDTO dto,String oldemail,HttpSession session){
		 
		if(dao.update(dto)>0){
			
			return "redirect:/";
		}else{
			return "/error/error";
		}
		
	}
	@RequestMapping(value="/member/update",method=RequestMethod.GET)
	public String update(String id,HttpSession session,Model model){
		
		if(id==null){
			id = (String)session.getAttribute("id");
		}
		
		MemberDTO dto = dao.read(id);
		
		model.addAttribute("dto", dto);
		return "/member/update";
	}
	@RequestMapping("/member/read")
	public String read(String id,HttpSession session,Model model){
		 
	    //일반사용자가 로그인후 나의정보를 클릭하고 올때
	    if(id==null){
	        id = (String)session.getAttribute("id");
	    }
	    MemberDTO dto = dao.read(id);
	    model.addAttribute("dto", dto);
		return "/member/read";
	}
	@RequestMapping("/member/logout")
	public String logout(HttpSession session){
		session.invalidate();//모든 세션변수 제거
		return "redirect:/";
	}
	
	@RequestMapping(value="/member/login",method=RequestMethod.POST)
	public String login(String id,String passwd,HttpServletRequest request,String c_id,
			int imgno,
			int bbsno,
			int nowPage, 
			int nPage,
			String col, 
			String word,
			String flag,
			String imgflag,
			HttpSession session,HttpServletResponse response,Model model){
	
		int cnt = 0;
		String grade = ""; //회원 등급
		
		cnt = dao.loginCheck(id, passwd);
		
		if(cnt==1){
			
			grade = dao.getGrade(id);
			session.setAttribute("id", id);
			session.setAttribute("grade", grade);
		    // ---------------------------------------------- 
		    // Cookie 저장, Checkbox는 선택하지 않으면 null 임 
		    // ---------------------------------------------- 
		    Cookie cookie = null; 
		       
		    if (c_id != null){  // 처음에는 값이 없음으로 null 체크로 처리
		      cookie = new Cookie("c_id", "Y");    // 아이디 저장 여부 쿠키 
		      cookie.setMaxAge(120);               // 2 분 유지 
		      response.addCookie(cookie);          // 쿠키 기록 
		   
		      cookie = new Cookie("c_id_val", id); // 아이디 값 저장 쿠키  
		      cookie.setMaxAge(120);               // 2 분 유지 
		      response.addCookie(cookie);          // 쿠키 기록  
		         
		    }else{ 
		      cookie = new Cookie("c_id", "");     // 쿠키 삭제 
		      cookie.setMaxAge(0); 
		      response.addCookie(cookie); 
		         
		      cookie = new Cookie("c_id_val", ""); // 쿠키 삭제 
		      cookie.setMaxAge(0); 
		      response.addCookie(cookie); 
		    } 
		    // --------------------------------------------- 
		 
		}
		
		model.addAttribute("cnt", cnt);
		String url = "./error/passwdError";
		if(cnt==1){
			url = "redirect:/";
			if(!flag.equals("")){
				model.addAttribute("bbsno", bbsno);
				model.addAttribute("nowPage", nowPage);
				model.addAttribute("nPage", nPage);
				model.addAttribute("col", col);
				model.addAttribute("word", word);
				url = "redirect:"+flag;
			}else if(!imgflag.equals("")){
				model.addAttribute("imgno", imgno);
				model.addAttribute("nowPage", nowPage);
				model.addAttribute("nPage", nPage);
				model.addAttribute("col", col);
				model.addAttribute("word", word);
				url = "redirect:"+imgflag;
			}
		}
		 
		return url;
	}
	@RequestMapping(value="/member/login",method=RequestMethod.GET)
	public String login(HttpServletRequest request,Model model,
			@RequestParam(value="bbsno",defaultValue="0")int bbsno,
			@RequestParam(value="imgno",defaultValue="0")int imgno,
			@RequestParam(value="nowPage",defaultValue="0")int nowPage, 
			@RequestParam(value="nPage",defaultValue="0")int nPage
			){
		String c_id = "";     // ID 저장 여부를 저장하는 변수, Y
		String c_id_val = ""; // ID 값
		
		Cookie[] cookies = request.getCookies(); 
		Cookie cookie=null; 
		
		if (cookies != null){ 
			for (int i = 0; i < cookies.length; i++) { 
				cookie = cookies[i]; 
				
				if (cookie.getName().equals("c_id")){ 
					c_id = cookie.getValue();     // Y 
				}else if(cookie.getName().equals("c_id_val")){ 
					c_id_val = cookie.getValue(); // user1... 
				} 
			} 
		}
		model.addAttribute("c_id", c_id);
		model.addAttribute("c_id_val", c_id_val);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("nPage", nPage);
		request.setAttribute("bbsno", bbsno);
		request.setAttribute("imgno", imgno);
		return "/login";
	}
	@RequestMapping("/member/email_form")
	public String email_form(){
		
		return "popup/email_form";
	}
	
	@RequestMapping("/member/email_proc")
	public String email_proc(String email,Model model){
		int cnt=dao.duplicateEmail(email);
		model.addAttribute("cnt", cnt);
		return "popup/email_proc";
	}
	@RequestMapping("/member/id_form")
	public String id_form(String id,Model model){

		return "popup/id_form";
	}
	@RequestMapping("/member/id_proc")
	public String id_proc(String id,Model model){
		
		int cnt=dao.duplicateID(id);
		model.addAttribute("cnt",cnt);
		return "popup/id_proc";
	}
	@RequestMapping("/member/agree")
	public String agree(){
		return "/member/agree";
	}
	@RequestMapping("/member/createProc")
	public String create(MemberDTO dto,HttpServletRequest request){
		boolean flag=false;
		if(dao.duplicateID(dto.getId())>0||dao.duplicateEmail(dto.getEmail())>0){
			flag=true;
		}
		int filesize=(int)dto.getFnameMF().getSize();
		String fname="member.jpg";
		String basePath=request.getRealPath("/storage");
		if(filesize>0&&!flag){
			fname=Utility.saveFile(dto.getFnameMF(), basePath);
		}
		dto.setFname(fname);
		
		    int cnt=0;
		    if(!flag){	    	
		    	cnt=dao.create(dto);
		    }
		    request.setAttribute("cnt", cnt);
		    request.setAttribute("flag", flag);
		    return "/member/createProc";
}
	@RequestMapping("/member/create")
	public String create(){
		return "/member/create";
	}
	@RequestMapping("/admin/list")
	public String list(HttpServletRequest request,Model model){
		String col = Utility.nullCheck(request.getParameter("col"));
		   String word = Utility.nullCheck(request.getParameter("word"));
		 
		   if(col.equals("total"))word="";
		   
		   //paging 관련
		   int nowPage =1; //현재페이지
		   int recordPerPage = 5; //한페이지당 보여줄 레코드 갯수
		   if(request.getParameter("nowPage")!=null){
		  nowPage = Integer.parseInt(request.getParameter("nowPage"));
		   }
		   
		   int sno = ((nowPage -1) * recordPerPage) +1;    
		   int eno =  nowPage * recordPerPage;
		   
		   Map map = new HashMap();
		   map.put("col", col);
		   map.put("word", word);
		   map.put("sno", sno);
		   map.put("eno", eno);
		   
		   int total = dao.total(col,word);
		   
		   String paging = new Paging().paging3(total, nowPage, recordPerPage, col, word);
		   List<MemberDTO>list=dao.list(map);
		   model.addAttribute("paging", paging);
		   model.addAttribute("col", col);
		   model.addAttribute("word", word);
		   model.addAttribute("nowPage", nowPage);
		   model.addAttribute("list", list);
		return "/member/list";
	}
}
