package info.hccis.SoccorJersey.dao;

import info.hccis.SoccorJersey.jpa.entity.SoccorJerseys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SoccorJerseyDAO {

    private static ResultSet rs;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(SoccorJerseyDAO.class);

    public SoccorJerseyDAO() {
        String propFileName = "application";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String connectionString = rb.getString("spring.datasource.url");
        String userName = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            logger.error(e.toString());
        }
    }
    public ArrayList<SoccorJerseys> selectSoccorJerseyByCountry(String country){

        String nameCountry = "%"+country+"%";
        PreparedStatement stmt;
        ArrayList<SoccorJerseys> soccorJerseys = new ArrayList();


        try {
            String query = "SELECT * FROM soccorjerseys "
                    + "WHERE nameCountry LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nameCountry);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                SoccorJerseys sj = new SoccorJerseys();

                sj.setId(rs.getInt("id"));
                sj.setJerseyCode(rs.getInt("jerseyCode"));
                sj.setNameCountry(rs.getString("nameCountry"));
                sj.setNameClub(rs.getString("nameClub"));
                sj.setNameType(rs.getString("nameType"));
                sj.setNamePlayer(rs.getString("namePlayer"));
                soccorJerseys.add(sj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Found orders:  " + soccorJerseys.size());
        return soccorJerseys;
    }

    public ArrayList<SoccorJerseys> selectPlayerJerseys(String players){

        String nameCountry = "%"+players+"%";
        PreparedStatement stmt;
        ArrayList<SoccorJerseys> soccorJerseys = new ArrayList();


        try {
            String query = "SELECT * FROM soccorjerseys "
                    + "WHERE namePlayer LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nameCountry);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                SoccorJerseys sj = new SoccorJerseys();

                sj.setId(rs.getInt("id"));
                sj.setJerseyCode(rs.getInt("jerseyCode"));
                sj.setNameCountry(rs.getString("nameCountry"));
                sj.setNameClub(rs.getString("nameClub"));
                sj.setNameType(rs.getString("nameType"));
                sj.setNamePlayer(rs.getString("namePlayer"));
                soccorJerseys.add(sj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Found orders:  " + soccorJerseys.size());
        return soccorJerseys;
    }


    public ArrayList<SoccorJerseys> getSoccorJerseys(){

        PreparedStatement stmt;
        ArrayList<SoccorJerseys> soccorJerseys = new ArrayList();


        try {
            String query = "SELECT * FROM soccorjerseys ";
            stmt = conn.prepareStatement(query);
//            stmt.setString(1, nameCountry);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                SoccorJerseys sj = new SoccorJerseys();

                sj.setId(rs.getInt("id"));
                sj.setJerseyCode(rs.getInt("jerseyCode"));
                sj.setNameCountry(rs.getString("nameCountry"));
                sj.setNameClub(rs.getString("nameClub"));
                sj.setNameType(rs.getString("nameType"));
                sj.setNamePlayer(rs.getString("namePlayer"));
                soccorJerseys.add(sj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Found orders:  " + soccorJerseys.size());
        return soccorJerseys;
    }

    //Get specfic jersey
    public SoccorJerseys selectSoccorJerseyByCountry(int id) {

        SoccorJerseys sj = new SoccorJerseys();

        PreparedStatement stmt;
        ArrayList<SoccorJerseys> soccorJerseys = new ArrayList();

        //https://stackoverflow.com/questions/2857164/cannot-use-a-like-query-in-a-jdbc-preparedstatement
        //Bitbucket Issue#5
        try {
            String query = "SELECT * FROM soccorjerseys sast WHERE sast.id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {

                sj.setId(rs.getInt("id"));
                sj.setJerseyCode(rs.getInt("jerseyCode"));
                sj.setNameCountry(rs.getString("nameCountry"));
                sj.setNameClub("nameClub");
                sj.setNameType(rs.getString("nameType"));
                sj.setNamePlayer(rs.getString("namePlayer"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Found assessment:  " + sj.toString());
        return sj;

    }

    public ArrayList<SoccorJerseys> SearchJerseyCodes(String id){

        String nameCountry = "%"+id+"%";
        PreparedStatement stmt;
        ArrayList<SoccorJerseys> soccorJerseys = new ArrayList();


        try {
            String query = "SELECT * FROM soccorjerseys "
                    + "WHERE jerseyCode LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nameCountry);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                SoccorJerseys sj = new SoccorJerseys();

                sj.setId(rs.getInt("id"));
                sj.setJerseyCode(rs.getInt("jerseyCode"));
                sj.setNameCountry(rs.getString("nameCountry"));
                sj.setNameClub(rs.getString("nameClub"));
                sj.setNameType(rs.getString("nameType"));
                sj.setNamePlayer(rs.getString("namePlayer"));
                soccorJerseys.add(sj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Found orders:  " + soccorJerseys.size());
        return soccorJerseys;
    }

    public ArrayList<SoccorJerseys> searchNameClub(String nameClub){

        String nameCountry = "%"+nameClub+"%";
        PreparedStatement stmt;
        ArrayList<SoccorJerseys> soccorJerseys = new ArrayList();


        try {
            String query = "SELECT * FROM soccorjerseys "
                    + "WHERE nameClub LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nameCountry);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                SoccorJerseys sj = new SoccorJerseys();

                sj.setId(rs.getInt("id"));
                sj.setJerseyCode(rs.getInt("jerseyCode"));
                sj.setNameCountry(rs.getString("nameCountry"));
                sj.setNameClub(rs.getString("nameClub"));
                sj.setNameType(rs.getString("nameType"));
                sj.setNamePlayer(rs.getString("namePlayer"));
                soccorJerseys.add(sj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Found orders:  " + soccorJerseys.size());
        return soccorJerseys;
    }
    public ArrayList<SoccorJerseys> searchJerseys(String search) {

        ArrayList<SoccorJerseys> countryList = selectSoccorJerseyByCountry(search);
        ArrayList<SoccorJerseys> playerList = selectPlayerJerseys(search);
        ArrayList<SoccorJerseys> JerseyCodeList = SearchJerseyCodes(search);
        ArrayList<SoccorJerseys> clubList = searchNameClub(search);
        //result array
        ArrayList<SoccorJerseys> listOfResults = new ArrayList<>();
        for (SoccorJerseys soccorJerseys : countryList) {
            if (!listOfResults.contains(soccorJerseys)) {
                listOfResults.add(soccorJerseys);
            }
        }
        for (SoccorJerseys soccorJerseys : playerList) {
            if (!listOfResults.contains(soccorJerseys)) {
                listOfResults.add(soccorJerseys);
            }
        }
        for (SoccorJerseys soccorJerseys : JerseyCodeList) {
            if (!listOfResults.contains(soccorJerseys)) {
                listOfResults.add(soccorJerseys);
            }
        }
        for (SoccorJerseys soccorJerseys : clubList) {
            if (!listOfResults.contains(soccorJerseys)) {
                listOfResults.add(soccorJerseys);
            }
        }
        return listOfResults;

    }

}
