package com.example.demo;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CustomError implements ErrorController {
    // url 직접 접근할 경우 대체 경로 추가
    private final String ERROR_PATH = "/error";

    @GetMapping(ERROR_PATH)
    public RedirectView redirectRoot(){
    	RedirectView redirectView = new RedirectView();
    	
    	redirectView.setUrl("http://localhost:8081/error");
    	
        return redirectView;
    }

    public String getErrorPath(){
        return null;
    }
}
	