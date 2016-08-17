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
import org.springframework.web.multipart.MultipartFile;

import spring.model.img.ImgDAO;
import spring.model.img.ImgDTO;
import spring.model.img.ImgReplyDAO;
import spring.model.img.ImgReplyDTO;
import spring.model.img.ImgService;
import spring.utility.blog.Paging;
import spring.utility.blog.Utility;

@Controller
public class ImgController {
	 
	@Autowired
	private ImgService service; //추가
	@Autowired
	private ImgReplyDAO rdao;
	 
	@RequestMapping("/image/rdelete")
	public String rdelete(int rnum,int imgno, int nowPage,int nPage, String col, String word,Model model){ 
	 
	int total = rdao.total(imgno);//댓글전체레코드값을 가져와서
	int totalPage = (int)(Math.ceil((double)total/3)); // 전체 페이지  
	if(rdao.delete(rnum)){
	if(nPage!=1&&nPage==totalPage&&total%3==1){//마지막페이지의 마지막레코드이면(3은 한페이지당보여줄 레코드 갯수)
	nPage=nPage-1;  //현재의 페이지값에서 1을 빼자 
	}
	model.addAttribute("imgno", imgno);
	model.addAttribute("nowPage", nowPage);
	model.addAttribute("nPage", nPage);
	model.addAttribute("col", col);
	model.addAttribute("word", word);
	 
	}else{
	return "error/error";
	}
	 
	return "redirect:./read";
	}
	 
	@RequestMapping("/image/rupdate")
	public String rupdate(ImgReplyDTO dto,int nowPage,int nPage, String col, String word,Model model){
	if(rdao.update(dto)){
	model.addAttribute("imgno", dto.getImgno());
	model.addAttribute("nowPage", nowPage);
	model.addAttribute("nPage", nPage);
	model.addAttribute("col", col);
	model.addAttribute("word", word);
	}else{
	return "error/error";
	}
	 
	return "redirect:./read";
	}
	@RequestMapping("/image/rcreate")
	public String rcreate(ImgReplyDTO dto,int nowPage,String col, String word,Model model){
	 
	if(rdao.create(dto)){
	model.addAttribute("imgno", dto.getImgno());
	model.addAttribute("nowPage", nowPage);
	model.addAttribute("col", col);
	model.addAttribute("word", word);
	}else{
	return "error/error";
	}
	 
	return "redirect:./read";
	}
	@Autowired
	ImgDAO dao;

	@RequestMapping(value = "/image/delete", method = RequestMethod.POST)
	public String delete(int imgno, String oldfile, String passwd, HttpServletRequest request,Model model) {
		String url = "./error/passwdError";
		if (dao.passwdCheck(imgno, passwd) > 0) {
			try {
			if (oldfile != null && !oldfile.equals("imgfornull.jpg")) {
					service.delete(imgno,oldfile,request);
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				return "redirect:/image/list";
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				url = "/error/error";
			}
		}
			return url;
	}

	@RequestMapping(value = "/image/delete", method = RequestMethod.GET)
	public String delete() {
		return "/image/delete";
	}

	@RequestMapping(value = "/image/reply", method = RequestMethod.POST)
	public String reply(HttpServletRequest request, ImgDTO dto, String filename, MultipartFile filenameMF,
			Model model) {
		String basePath = request.getRealPath("/img");

		int size = (int) dto.getFilenameMF().getSize();
		filename = "imgfornull.jpg";
		if (size > 0) {
			filename = Utility.saveFile(filenameMF, basePath);
			}
		dto.setFilename(filename);
		dao.addAnsnum(dto.getGrpno(), dto.getAnsnum());
			if(dao.reply(dto)>0){
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			return "redirect:/image/list";
		} else {
			return "/error/error";
		}

	}

	@RequestMapping(value = "/image/reply", method = RequestMethod.GET)
	public String reply(int imgno, Model model) {

		ImgDTO dto = dao.readReply(imgno);
		model.addAttribute("dto", dto);
		return "/image/reply";
	}

	@RequestMapping(value = "/image/update", method = RequestMethod.POST)
	public String updateTotal(String oldfile, HttpServletRequest request, ImgDTO dto, Model model,String col,String word,int nowPage) {
		String basePath = request.getRealPath("/img");
		String filename = "imgfornull.jpg";
		int size = (int) dto.getFilenameMF().getSize();
		if (dao.passwdCheck(dto.getImgno(), dto.getPasswd()) > 0) {
			if (size > 0) {
				if (oldfile != null && !oldfile.equals("imgfornull.jpg")) {
					Utility.deleteFile(basePath, oldfile);
					filename = Utility.saveFile(dto.getFilenameMF(), basePath);
				}
			}
			dto.setFilename(filename);
			if (dao.update(dto) > 0) {
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				return "redirect:/image/list";
			} else {
				model.addAttribute("nowPage",nowPage);
				model.addAttribute("col", col);
				model.addAttribute("word", word);
				return "/error/error";
			}

		} else {
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			return "/error/passwdError";
		}
	}

	@RequestMapping(value = "/image/update", method = RequestMethod.GET)
	public String update(int imgno, Model model) {

		ImgDTO dto = dao.read(imgno);

		model.addAttribute("dto", dto);

		return "/image/update";
	}

	@RequestMapping(value = "/image/create", method = RequestMethod.POST)
	public String create(HttpServletRequest request, ImgDTO dto, MultipartFile filenameMF) {
		int filesize = (int) dto.getFilenameMF().getSize();
		String basePath = request.getRealPath("/img");
		String filename = "imgfornull.jpg";
		if (filesize > 0) {
			filename = Utility.saveFile(filenameMF, basePath);}
			dto.setFilename(filename);
			if(dao.create(dto)>0){
			return "redirect:/image/list";
		} else {
			return "/error/error";
		}

	}

	@RequestMapping(value = "/image/create", method = RequestMethod.GET)
	public String create() {

		return "/image/create";
	}

	@RequestMapping("/image/read")
	public String read(int nowPage, String col, String word,int imgno, Model model, HttpServletRequest request) {
		ImgDTO dto2 = dao.imgRead(imgno);
		ImgDTO dto = dao.read(imgno);
		model.addAttribute("dto2", dto2);
		model.addAttribute("dto", dto);
		/* 댓글 관련  시작 */
		String url = "read";
		int nPage= 1; //시작 페이지 번호는 1부터 
		 
		if (request.getParameter("nPage") != null) { 
		nPage= Integer.parseInt(request.getParameter("nPage"));  
		}
		int recordPerPage = 3; // 한페이지당 출력할 레코드 갯수
		 
		int sno = ((nPage-1) * recordPerPage) + 1; // 
		int eno = nPage * recordPerPage;
		 
		Map map = new HashMap();
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("imgno", imgno);
		 
		List<ImgReplyDTO> list = rdao.list(map);
		 
		int total = rdao.total(imgno);
		 
		String paging = Utility.paging(total, nPage, recordPerPage, url,imgno,nowPage, col,word);
		 
		model.addAttribute("rlist",list);
		model.addAttribute("paging",paging);
		model.addAttribute("nPage",nPage);
		 
		/* 댓글 관련 끝 */ 
		return "/image/read";
	}

	@RequestMapping("/image/list")
	public String list(HttpServletRequest request, Model model) {
		String col = Utility.nullCheck(request.getParameter("col"));
		String word = Utility.nullCheck(request.getParameter("word"));

		if (col.equals("total"))
			word = "";

		int nowPage = 1;
		int recordPerPage = 5;

		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}

		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("col", col);
		map.put("word", word);

		List<ImgDTO> list = dao.list(map);
		int total = dao.total(col, word);

		String paging = new Paging().paging3(total, nowPage, recordPerPage, col, word);
		
		model.addAttribute("rdao", rdao);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		return "/image/list";
	}

}
