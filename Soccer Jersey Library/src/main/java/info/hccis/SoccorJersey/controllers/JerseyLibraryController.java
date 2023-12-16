package info.hccis.SoccorJersey.controllers;

import info.hccis.SoccorJersey.bo.SoccorJerseValidationBO;
import info.hccis.SoccorJersey.dao.SoccorJerseyDAO;
import info.hccis.SoccorJersey.entity.ReportSoccorJesery;
import info.hccis.SoccorJersey.jpa.entity.SoccorJerseys;
import info.hccis.SoccorJersey.repositories.SoccorJerseysRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
/**
 * @author Joshua Mckenna
 * @since 12142023
 * Controls the List page and add/edit pages'
 */
@Controller
@RequestMapping("/jerseylibrary")
public class JerseyLibraryController {
    private final SoccorJerseysRepositories _rsj;
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    //A list of countrys for the edit and add page
    private ArrayList<String> countries = new ArrayList<>(Arrays.asList(
            "Afghanistan", "Albania", "Algeria", "Andorra", "Angola",
            "Antigua and Barbuda", "Argentina", "Armenia", "Australia", "Austria",
            "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus",
            "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina",
            "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi",
            "Côte d'Ivoire", "Cabo Verde", "Cambodia", "Cameroon", "Canada",
            "Central African Republic", "Chad", "Chile", "China", "Colombia",
            "Comoros", "Congo", "Costa Rica", "Croatia", "Cuba",
            "Cyprus", "Czechia", "Democratic Republic of the Congo",
            "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt",
            "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini",
            "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia",
            "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea",
            "Guinea-Bissau", "Guyana", "Haiti", "Holy See", "Honduras", "Hungary",
            "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy",
            "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait",
            "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
            "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi",
            "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania",
            "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia",
            "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia",
            "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger",
            "Nigeria", "North Korea", "North Macedonia", "Norway",
            "Oman", "Pakistan", "Palau", "Palestine State", "Panama", "Papua New Guinea",
            "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania",
            "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia",
            "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles",
            "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
            "Somalia", "South Africa", "South Korea", "South Sudan", "Spain",
            "Sri Lanka", "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Tajikistan",
            "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tonga", "Trinidad and Tobago",
            "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine",
            "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay",
            "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
    ));
    @Autowired
    private MessageSource messageSource;

    @Autowired
    public JerseyLibraryController(SoccorJerseysRepositories rsj) {
        _rsj = rsj;
    }


    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        ReportSoccorJesery rsj = new ReportSoccorJesery();
        rsj.setSoccorJerseys((ArrayList<SoccorJerseys>) _rsj.findAll());
        model.addAttribute("reportInput",rsj);
        model.addAttribute("soccorJerseys", _rsj.findAll());

        logger.info("Running the reports controller base method");
        return "jerseylibrary/list";

    }

    /**
     * The Add page
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model, HttpSession session) {

        model.addAttribute("soccorJerseys", new SoccorJerseys());
        model.addAttribute("countries", countries);

        logger.info("Running the reports controller base method");
        return "jerseylibrary/add";

    }

    /**
     * The Submit page for Add and edit pages
     * @param model
     * @param request
     * @param soccorJerseys
     * @param bindingResult
     * @return
     */
    @RequestMapping("/submit")
    public String submit(Model model, HttpServletRequest request, @Valid @ModelAttribute("soccorJerseys") SoccorJerseys soccorJerseys, BindingResult bindingResult) {
        SoccorJerseValidationBO sjvbo = new SoccorJerseValidationBO();
        boolean isSubmitted = sjvbo.isValid(soccorJerseys);

        /*|| soccorJerseys.getJerseyCode() <= 0
        * This is in the statment below because there is a weird java bug*/
        if (bindingResult.hasErrors() || sjvbo.isValid(soccorJerseys) || soccorJerseys.getJerseyCode() <= 0){

            model.addAttribute("isSubmitted", isSubmitted);
            return "jerseylibrary/add";
        }


/*        if (bindingResult.hasErrors()) {
*//*
            System.out.println("--------------------------------------------");
            System.out.println("Validation error - BJM");
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getObjectName() + "-" + error.toString() + "-" + error.getDefaultMessage());
            }
            System.out.println("--------------------------------------------");
*//*

            return "jerseylibrary/add";
        }*/

        _rsj.save(soccorJerseys);
        /*ticketOrderBO.processCost(ticketOrder);*/
        return "redirect:/jerseylibrary";
    }


    /**
     * The Edit Page
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        System.out.println("request mapping matched for edit");

        Optional assessmentOptional = _rsj.findById(id);
        if (assessmentOptional.isPresent()) {
            SoccorJerseys soccorJerseys = (SoccorJerseys) assessmentOptional.get();
            model.addAttribute("soccorJerseys", soccorJerseys);
            model.addAttribute("countries", countries);
            return "jerseylibrary/add";
        }
        //possibly not found for edit id.  todo set a message to the user.
        return "redirect:/jerseylibrary";

    }

    /**
     *
     * @param id ID
     * @return directions to the list page
     *
     * @since 2023/10/26
     * @author Joshua Mckenna
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        System.out.println(id);
        _rsj.deleteById(id);
        return "redirect:/jerseylibrary";
    }

    /**
     * The Search function on the list page
     * @param model
     * @param reportSoccorJesery
     * @return
     */
    @RequestMapping("/search")
    public String search(Model model, @ModelAttribute("reportInput") ReportSoccorJesery reportSoccorJesery) {
        //todo 1 use dao class to get the assessments for that player
        SoccorJerseyDAO sjDAO =  new SoccorJerseyDAO();
        ArrayList<SoccorJerseys> soccorJerseys = sjDAO.searchJerseys(reportSoccorJesery.getSearch());
        model.addAttribute("soccorJerseys", soccorJerseys);
        reportSoccorJesery.setSubmitted(true);
        System.out.println("The Country entered by the user is: "+reportSoccorJesery.getCountryName());

        return "jerseylibrary/list";
    }

}
