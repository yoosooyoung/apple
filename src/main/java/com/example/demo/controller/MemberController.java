package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.memberService;
import com.example.demo.vo.memberVo;

@RestController
@RequestMapping("/api")
public class MemberController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
    private memberService memberSvc;
    
    @Autowired
    private PasswordEncoder pwEncoder;

    /**
     * 회원가입 체크
     * @param model
     * @param vo
     * @return
     */
	@RequestMapping("/join")
	@ResponseBody
	public Map<String, String> signUpCheck(Model model, memberVo vo) {
    	Map<String, String>result = new HashMap<>();
    	
    	//아이디 중복 체크
    	int checkId = memberSvc.checkId(vo);
    	if(checkId > 0) {
    		result.put("result", "1");
    		return result;
    	}
    	
    	try {
        	//패스워드암호화진행
        	vo.setUser_pw(pwEncoder.encode(vo.getUser_pw()));
        	//회원가입서비스 실행
        	memberSvc.insertMember(vo);
        	result.put("result", "0");
		} catch (Exception e) {
			result.put("result", "1");
		}
    	
        return result;
    }
	
	/**
     * 로그인체크
     * @param request
     * @param member
     * @param rttr
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/signin/check")
    public Map<String, Object> signInCheck(Model model,HttpServletRequest request, memberVo member, RedirectAttributes rttr) throws Exception {

    	 HttpSession session = request.getSession();
         Map<String, Object> sess = new HashMap<String, Object>();
         String rawPw = "";
         String encodePw = "";

         memberVo lvo = memberSvc.memberLogin(member);

         if (lvo != null) {  // 일치하는 아이디 존재시
             rawPw = member.getUser_pw();   // 사용자가 제출한 비밀번호
             encodePw = lvo.getUser_pw();   // 데이터베이스에 저장한 인코딩 비밀번호

             if (true == pwEncoder.matches(rawPw, encodePw)) {   //비밀번호 일치 여부 판단
                 lvo.setUser_pw("");    // 인코딩된 비밀번호 정보 지움
                 session.setAttribute("member", lvo);    // session에 사용자 정보 저장
                 sess.put("member",lvo);
                 sess.put("result", "success");
                 return sess;
             } else {
             	sess.put("result", 0);
                 return sess;
             }
         } else {        // 일치하는 아이디가 존재하지 않을 시
         	sess.put("result", 1);
             return sess;
         }
    }   
    
    
    @GetMapping("/signout")
    public String logout(HttpServletRequest request) throws Exception{
        
    	log.info("----------logout---------");
        
        HttpSession session = request.getSession();
        
        session.invalidate();
        
        return "redirect:/";        
        
    }    
	
	
}
