package com.cbidici.site.view;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login(HttpServletRequest request, Model model) {
    if(request.getRequestURI().equals("/login") && Optional.ofNullable(request.getQueryString()).orElse("").equals("error")) {
      model.addAttribute("error", "true");
    }
    return "login";
  }
}
