package com.imilkaeu.sprcrp;

import com.imilkaeu.sprcrp.dao.SentenceDAO;
import com.imilkaeu.sprcrp.dao.WordDAO;
import com.imilkaeu.sprcrp.models.input.BigramInputData;
import com.imilkaeu.sprcrp.models.output.BigramCombination;
import com.imilkaeu.sprcrp.models.output.BigramCombinationInfo;
import com.imilkaeu.sprcrp.models.output.OutputSentenceInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by imilka on 10.12.13.
 */
@Controller
@RequestMapping("/stats")
public class StatsController {

    private static final Logger logger = Logger.getLogger(StatsController.class);
    @Autowired
    private WordDAO wordDAO;
    @Autowired
    private SentenceDAO sentenceDAO;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }

    @RequestMapping(value="bigram", method = RequestMethod.POST)
    public @ResponseBody
    BigramCombinationInfo bigram(
            @RequestBody final BigramInputData inputData) {
        return wordDAO.getBigramStats(inputData);
    }

    @RequestMapping(value="bigram", method = RequestMethod.GET)
    public @ResponseBody
    BigramCombinationInfo bigram(
            @RequestParam(value = "hash") String hash) {
        return wordDAO.getBigramStats(hash);
    }

    @RequestMapping(value="getsentence", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    public @ResponseBody
    List<OutputSentenceInfo> getsentence(
            @RequestBody final BigramCombination inputData) {
        return sentenceDAO.getSentenceByBigram(inputData);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.warn("Returning HTTP 400 Bad Request", e);
    }
}
