package cn.sy.demo.web;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("session")
@Api(value = "redis-session")
@Slf4j
public class RedisSessionTestController {

    @ApiOperation("createSession")
    @RequestMapping(value = "/createSession", method = RequestMethod.POST)
    public Object createSession(HttpServletRequest request, HttpSession session, String name) {
        session.setAttribute("name", name);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("port", request.getLocalPort());
        jsonObject.put("sessionid", session.getId());
        jsonObject.put("name", session.getAttribute("name"));
        log.info("createSession {}", jsonObject.toString());
        return jsonObject.toString();
    }

    @ApiOperation("getSession")
    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public Object getSession(HttpServletRequest request, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("port", request.getLocalPort());
        jsonObject.put("sessionid", session.getId());
        jsonObject.put("name", session.getAttribute("name"));
        log.info("getsession {}", jsonObject.toString());
        return jsonObject.toString();
    }

}