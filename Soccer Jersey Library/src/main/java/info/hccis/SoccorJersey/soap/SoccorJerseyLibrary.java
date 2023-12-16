package info.hccis.SoccorJersey.soap;

import info.hccis.SoccorJersey.jpa.entity.SoccorJerseys;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SoccorJerseyLibrary {
    @WebMethod
    SoccorJerseys getJersesy(int id);
    @WebMethod
    int getCount();
    @WebMethod
    ArrayList<SoccorJerseys> getByCountry(String country);
    
}