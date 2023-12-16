package info.hccis.SoccorJersey.controllers;


import info.hccis.SoccorJersey.util.api.ApiProcessor;
import info.hccis.SoccorJersey.util.api.ApiDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Base controller which control general functionality in the app.
 *
 * @since 20220624
 * @author Joshua Mckena
 */
@Controller
@RequestMapping("/callapi")
public class CallApiController {

    /**
     * Send the user to the view.
     *
     * @since 20231102
     * @author Joshua Mckenna
     */
    @RequestMapping("/joke")
    public String translateToPirate(Model model) {
        model.addAttribute("apiDetail", new ApiDetail());
        return "other/joke";
    }

    /**
     * Send the user to the view.
     *
     * @since 20231102
     * @author Joshua Mckena
     */
    @RequestMapping("/joke/submit")
    public String submit(Model model, HttpServletRequest request, @ModelAttribute("apiDetail") ApiDetail apiDetail){

        String joke = ApiProcessor.getJokeByTopic(apiDetail.getCategory(), apiDetail.getNumber());
        apiDetail.setOutput(joke);
        System.out.println(apiDetail.getOutput());
        
        model.addAttribute("apiDetail", apiDetail);
        return "other/joke";
    }

    
}
