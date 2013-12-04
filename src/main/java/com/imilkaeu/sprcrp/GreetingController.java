package com.imilkaeu.sprcrp;

import com.imilkaeu.sprcrp.models.Text;
import com.imilkaeu.sprcrp.repos.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by imilka on 04.12.13.
 */
@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private TextRepository textRepository;

    @RequestMapping("/greeting")
    public @ResponseBody
    List<Text> greeting(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
        /*ArrayList<Greeting> greetings = new ArrayList();
        for(int i = 0; i < 10; i++) {
            greetings.add(new Greeting(counter.incrementAndGet(),
                    String.format(template, name + " " + i)));
        }*/
        return textRepository.findAll();
    }
}
