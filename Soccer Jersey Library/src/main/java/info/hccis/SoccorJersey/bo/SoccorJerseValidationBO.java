package info.hccis.SoccorJersey.bo;

import info.hccis.SoccorJersey.jpa.entity.SoccorJerseys;

public class SoccorJerseValidationBO {


    public synchronized boolean isValid(SoccorJerseys soccorJerseys) {
//        ArrayList<Boolean> errorMessage = new ArrayList<Boolean>();
        boolean isValid = true;

        if (soccorJerseys.getNamePlayer().isEmpty()){
            isValid = false;
        }
        if (soccorJerseys.getNameCountry().isEmpty()){
            isValid = false;
        }
        if (soccorJerseys.getNameClub().isEmpty()){
            isValid = false;
        }
        if (soccorJerseys.getNameType().isEmpty()){
            isValid = false;
        }
        if (soccorJerseys.getJerseyCode() > 0 || soccorJerseys.getJerseyCode() == 0){
            isValid = false;

        }
        System.out.println(soccorJerseys);
        return isValid;
    }
}
