package cn.sy.demo.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class RedisSessionController {

    @RequestMapping("/createSession")
    public Object createSession(HttpServletRequest request, HttpSession session, String name) throws JSONException {
        session.setAttribute("name", name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("port", request.getLocalPort());
        jsonObject.put("sessionid", session.getId());
        jsonObject.put("name", session.getAttribute("name"));
        log.info("createSession {}", jsonObject.toString());
        return jsonObject.toString();
    }

    @RequestMapping("/getSession")
    public Object getSession(HttpServletRequest request, HttpSession session) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("port", request.getLocalPort());
        jsonObject.put("sessionid", session.getId());
        jsonObject.put("name", session.getAttribute("name"));
        log.info("getsession {}", jsonObject.toString());
        return jsonObject.toString();
    }

}