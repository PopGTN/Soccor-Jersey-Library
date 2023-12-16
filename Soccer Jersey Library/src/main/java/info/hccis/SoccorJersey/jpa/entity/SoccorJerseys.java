/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package info.hccis.SoccorJersey.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @since 11302023
 * @author joshua Mckenna
 */
@Entity
@Table(name = "soccorjerseys")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SoccorJerseys.findAll", query = "SELECT s FROM SoccorJerseys s"),
    @NamedQuery(name = "SoccorJerseys.findById", query = "SELECT s FROM SoccorJerseys s WHERE s.id = :id"),
    @NamedQuery(name = "SoccorJerseys.findByJerseyCode", query = "SELECT s FROM SoccorJerseys s WHERE s.jerseyCode = :jerseyCode"),
    @NamedQuery(name = "SoccorJerseys.findByNameCountry", query = "SELECT s FROM SoccorJerseys s WHERE s.nameCountry = :nameCountry"),
    @NamedQuery(name = "SoccorJerseys.findByNameClub", query = "SELECT s FROM SoccorJerseys s WHERE s.nameClub = :nameClub"),
    @NamedQuery(name = "SoccorJerseys.findByNameType", query = "SELECT s FROM SoccorJerseys s WHERE s.nameType = :nameType"),
    @NamedQuery(name = "SoccorJerseys.findByNamePlayer", query = "SELECT s FROM SoccorJerseys s WHERE s.namePlayer = :namePlayer")})
public class SoccorJerseys implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jerseyCode")
    private int jerseyCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nameCountry")
    private String nameCountry;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nameClub")
    private String nameClub;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nameType")
    private String nameType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "namePlayer")
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
