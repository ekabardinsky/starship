package ru.ekabardinsky.humaninterface.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ekabardinsky.humaninterface.model.Growth;

/**
 * Created by ekabardinsky on 2/25/17.
 */
@Controller
public class BodyController {
    private Gson gson = new GsonBuilder().create();

    @RequestMapping(method = RequestMethod.POST, value = "/api/body")
    @ResponseBody
    public String getBody(@RequestParam(value = "addTime") double time, @RequestParam(value = "renew") boolean renew) {
        return gson.toJson(Growth.growth(time, renew));
    }
}
