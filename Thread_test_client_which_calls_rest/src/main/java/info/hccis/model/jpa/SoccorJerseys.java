/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.hccis.model.jpa;

import info.hccis.student.util.CisUtility;

/**
 *
 * @author joshua
 */
public class SoccorJerseys {
    private Integer id;
    private int jerseyCode;
    private String nameCountry;
    private String nameClub;
    private String nameType;
    private String namePlayer;
  

    public SoccorJerseys() {
    }
    
    public SoccorJerseys(Integer id) {
        this.id = id;
    }

    public SoccorJerseys(Integer id, int jerseyCode, String nameCountry, String nameClub, String nameType, String namePlayer) {
        this.id = id;
        this.jerseyCode = jerseyCode;
        this.nameCountry = nameCountry;
        this.nameClub = nameClub;
        this.nameType = nameType;
        this.namePlayer = namePlayer;
    }
    public void getInformation() {
        getInformation(false);
    }
    public void getInformation(boolean guiIndicator){
            
         CisUtility cisUtility = new CisUtility();
         cisUtility.setIsGUI(guiIndicator);
        jerseyCode = cisUtility.getInputInt("Jersey Code");
        nameCountry = cisUtility.getInputString("Country Name");
        nameClub = cisUtility.getInputString("Club Name");
        boolean isValid = false;
        String option;
               while (!isValid) {
                   option = cisUtility.getInputString("Type of Jersey (Home or Away)");
                   switch(option.toLowerCase()){
                       case "home":
                           this.nameType = "home";
                           isValid = true;
                           break;
                       case "away":
                           this.nameType = "away";
                           isValid = true;
                           break;
                       default:
                           cisUtility.display("invalid option");
                   }
               }
        namePlayer = cisUtility.getInputString("Player Name");
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getJerseyCode() {
        return jerseyCode;
    }

    public void setJerseyCode(int jerseyCode) {
        this.jerseyCode = jerseyCode;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getNameClub() {
        return nameClub;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoccorJerseys)) {
            return false;
        }
        SoccorJerseys other = (SoccorJerseys) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: " + id +
                "\tjerseyCode: " + jerseyCode +
                "\tnameCountry: " + nameCountry +
                "\tnameClub: " + nameClub +
                "\tnameType: " + nameType +
                "\tnamePlayer: " + namePlayer;
    }
    
}
