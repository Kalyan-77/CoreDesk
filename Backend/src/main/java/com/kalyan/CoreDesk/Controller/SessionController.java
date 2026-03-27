package com.kalyan.CoreDesk.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
    @GetMapping("/create")
    public String createSession(HttpSession session){
        session.setAttribute("username" , "Kalyan");

        String sessionId = session.getId();
        return "Session Created with Id : " + sessionId;
    }

    @GetMapping("/get")
    public String getSession(HttpSession session){
        String username = (String) session.getAttribute("username");
        if(username == null){
            return "No session found";
        }
        String sessionId = session.getId();
        return "Session Id: " + username + "\n value: " + sessionId;
    }

    @GetMapping("/delete")
    public String deleteSession(HttpSession session){
        session.invalidate();
        return "Session Deleted..";
    }
}
