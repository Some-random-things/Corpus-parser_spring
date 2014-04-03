package com.imilkaeu.sprcrp.controllers;

import com.imilkaeu.sprcrp.dao.WordDAO;
import com.imilkaeu.sprcrp.models.output.BigramCombination;
import com.imilkaeu.sprcrp.models.output.DecisionTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: imilka
 * Date: 02.04.14
 * Time: 16:36
 */
@Controller
@RequestMapping("/tree")
public class DecisionTreeController {

    @Autowired
    private WordDAO wordDAO;

    @RequestMapping(value="visualize", method = RequestMethod.POST, consumes="application/json", produces="application/json")
    public @ResponseBody
    DecisionTreeNode visualize(
            @RequestBody final BigramCombination inputData) {
        return wordDAO.getParamsData(inputData);
    }

}
