package spring.model.img;
 
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.utility.blog.Utility;
 
@Service 
public class ImgService {
       @Autowired
       // private BbsDAO dao;
          private ImgDAO dao;
       @Autowired
        private ImgReplyDAO rdao;
 
      public void delete(int imgno,String oldfile,HttpServletRequest request) throws Exception{
    	  String basePath = request.getRealPath("/img");
             rdao.bdelete(imgno);
             dao.delete(imgno);
             Utility.deleteFile(basePath, oldfile);
      }
}