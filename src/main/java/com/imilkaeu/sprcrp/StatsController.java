package com.imilkaeu.sprcrp;

import com.imilkaeu.sprcrp.models.Greeting;
import com.imilkaeu.sprcrp.models.input.BigramInputData;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by imilka on 10.12.13.
 */
@Controller
@RequestMapping("/stats")
public class StatsController {

    private static final Logger logger = Logger.getLogger(StatsController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }

    @RequestMapping(value="bigram", method = RequestMethod.POST)
    public @ResponseBody
    Greeting bigram(
            @RequestBody final BigramInputData inputData) {
        return new Greeting(1, Boolean.toString(inputData.getMain().get(0).getContent().get(0).getValues().get(0).isSelected()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.warn("Returning HTTP 400 Bad Request", e);
    }
}
