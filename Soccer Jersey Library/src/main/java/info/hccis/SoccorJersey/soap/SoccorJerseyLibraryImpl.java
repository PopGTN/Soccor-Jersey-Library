package info.hccis.SoccorJersey.soap;

import info.hccis.SoccorJersey.dao.SoccorJerseyDAO;
import info.hccis.SoccorJersey.jpa.entity.SoccorJerseys;
import java.util.ArrayList;
import javax.jws.WebService;

@WebService(endpointInterface = "info.hccis.SoccorJersey.soap.SoccorJerseyLibrary")
public class SoccorJerseyLibraryImpl implements SoccorJerseyLibrary {
    
    @Override
    public SoccorJerseys getJersesy(int id) {

        SoccorJerseyDAO soccorJerseyDAO = new SoccorJerseyDAO();
        SoccorJerseys sast = soccorJerseyDAO.selectSoccorJerseyByCountry(id);
        return sast;

    }

    @Override
    public int getCount() {
        SoccorJerseyDAO squashSkillsDAO = new SoccorJerseyDAO();
        ArrayList<SoccorJerseys> sasts = squashSkillsDAO.selectSoccorJerseyByCountry("");
        return sasts.size();
    }

    @Override
    public ArrayList<SoccorJerseys> getByCountry(String country) {
        SoccorJerseyDAO soccorJerseyDAO = new SoccorJerseyDAO();
        return soccorJerseyDAO.selectSoccorJerseyByCountry(country);
    }

}
