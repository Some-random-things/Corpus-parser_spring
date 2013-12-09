package com.imilkaeu.sprcrp;

import com.imilkaeu.sprcrp.models.Greeting;
import com.imilkaeu.sprcrp.models.input.BigramInputData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by imilka on 10.12.13.
 */
@Controller
@RequestMapping("/stats")
public class StatsController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }

    @RequestMapping(value="bigram", method = RequestMethod.POST)
    public @ResponseBody
    Greeting post(
            @RequestBody final BigramInputData inputData) {
        return new Greeting(1, inputData.getMain().get(0).getPartOfSpeech());
    }
}
