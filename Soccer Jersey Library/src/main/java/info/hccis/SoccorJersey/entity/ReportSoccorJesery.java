package info.hccis.SoccorJersey.entity;

import info.hccis.SoccorJersey.jpa.entity.SoccorJerseys;
import info.hccis.SoccorJersey.jpa.entity.TicketOrder;
import java.util.ArrayList;

/**
 * Entity class to hold the attributes of the order related reports.
 * @author bjmaclean
 * @since 20220621
 */
public class ReportSoccorJesery {

    private boolean isSubmitted = false;
    private String countryName;
    private ArrayList<SoccorJerseys> SoccorJerseys;
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName.trim();
    }

    public ArrayList<SoccorJerseys> getSoccorJerseys() {
        return SoccorJerseys;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public void setSoccorJerseys(ArrayList<SoccorJerseys> ticketOrders) {
        this.SoccorJerseys = ticketOrders;
    }
    
    
}
