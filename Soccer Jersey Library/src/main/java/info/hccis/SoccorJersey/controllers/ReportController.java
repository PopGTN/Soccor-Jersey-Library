package info.hccis.SoccorJersey.controllers;

import info.hccis.SoccorJersey.bo.SoccorJerseyBO;
import info.hccis.SoccorJersey.dao.SoccorJerseyDAO;
import info.hccis.SoccorJersey.entity.ReportSoccorJesery;
import info.hccis.SoccorJersey.jpa.entity.SoccorJerseys;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import info.hccis.SoccorJersey.repositories.SoccorJerseysRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to administer reports of the project.
 *
 * @since 20220616
 * @author BJM
 *
 * @Modified by Joshua mckenna
 * @since 12042023
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    private final SoccorJerseysRepositories _rsj;
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    public ReportController(SoccorJerseysRepositories rsj) {
        _rsj = rsj;
    }

    @Autowired
    private MessageSource messageSource;


    /**
     * Send the user to list of reports view.
     *
     * @param model
     * @param session
     * @return To the appropriate view
     * @since 20220624
     * @author BJM
     *
     * @Modified by Joshua mckenna
     * @since 12042023
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        //BJM 20200602 Issue#1 Set the current date in the session
        model.addAttribute("soccorJerseys", _rsj.findAll());


        logger.info("Running the reports controller base method");
        return "redirect:/report/jersey/countryname";

    }


    /**
     * Method to send user to the customer name report input.
     *
     * @param model
     * @return view for list
     * @since 2022-06-20
     * @author BJM
     *
     * @modified 2023/10/12 By Joshua Mckenna
     */
    @RequestMapping("/jersey/countryname")
    public String reportJerseyCountryName(Model model) {

        //**********************************************************************
        // Put the ticket order object in the model and send the user
        // to the report input view.
        //**********************************************************************

        model.addAttribute("reportInput", new ReportSoccorJesery());


        return "report/reportJerseyCountryName";
    }


    /**
     * Process the report
     *
     * @param model
     * @param reportSoccorJesery
     * @return view to show report
     * @since 2022-06-20
     * @author BJM
     *
     * @modified 2023/10/12 By Joshua Mckenna
     */
    @RequestMapping("/jersey/countryname/submit")
    public String reportsByCountryName(Model model, @ModelAttribute("reportInput") ReportSoccorJesery reportSoccorJesery) {
        //todo 1 use dao class to get the assessments for that player
        SoccorJerseyDAO sjDAO =  new SoccorJerseyDAO();

        ArrayList<SoccorJerseys> soccorJerseys = sjDAO.selectSoccorJerseyByCountry(reportSoccorJesery.getCountryName());

        SoccorJerseyBO.writeToFile("Report by Country's", soccorJerseys);
        model.addAttribute("soccorJerseys", soccorJerseys);

        reportSoccorJesery.setSubmitted(true);
        System.out.println("The Country entered by the user is: "+reportSoccorJesery.getCountryName());

        return "report/reportJerseyCountryName";
    }

}
