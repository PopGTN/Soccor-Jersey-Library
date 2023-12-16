package info.hccis.SoccorJersey.rest;

import com.google.gson.Gson;
import info.hccis.SoccorJersey.exception.AllAttributesNeededException;
import info.hccis.SoccorJersey.jpa.entity.SoccorJerseys;
import info.hccis.SoccorJersey.repositories.SoccorJerseysRepositories;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;

@Path("/SoccorJerseyService/")
public class SoccorJerseyService {
    private final SoccorJerseysRepositories _sj;

    @Autowired
    public SoccorJerseyService(SoccorJerseysRepositories sj) {
        _sj = sj;
    }

    /**
     * Method to get all.
     *
     * @return customers
     * @author 2250
     * @since 20201116
     */
    @GET
    @Produces("application/json")
    public Response getAll() {
        ArrayList<SoccorJerseys> soccorJerseys = (ArrayList<SoccorJerseys>) _sj.findAll();

        if (soccorJerseys == null || soccorJerseys.isEmpty()) {
            return Response.status(404).build();
        } else {
            return Response
                    .status(200)
                    .entity(soccorJerseys).build();
        }
    }

    /**
     * Method to get by their id using REST.
     *
     * @return response
     * @author 2250
     * @since 20220201
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getSoccorJerseyById(@PathParam("id") Integer id) throws URISyntaxException {
        Optional<SoccorJerseys> soccorJerseyFound = _sj.findById(id);
        _sj.deleteAll();
        if (!soccorJerseyFound.isPresent()) {
            return Response.status(404).build();
        } else {
            return Response
                    .status(200)
                    .entity(soccorJerseyFound).build();
        }
    }

    /**
     * Method to create using REST.
     *
     * @return response
     * @author 2250
     * @since 20201116
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String soccorJerseyJson) {
        try {
            String temp = save(soccorJerseyJson);
            return Response.status(HttpURLConnection.HTTP_CREATED).entity(temp).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
        } catch (AllAttributesNeededException aane) {
            System.out.println("AANE Exception happened adding SoccorJersey.");
            //https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#successful_responses
            return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(aane.getMessage()).build();
        } catch (Exception e) {
            System.out.println("Exception happened adding SoccorJersey.");
            //https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#successful_responses

            return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }

    //
//    /**
//     * Method to update a customer using REST.
//     *
//     * @author PAAG
//     * @since 20201116
//     * @return response
//     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(@PathParam("id") int id, String assessmentJson) throws URISyntaxException {
        try {
            String temp = save(assessmentJson, id);
            return Response.status(201).entity(temp).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
        } catch (AllAttributesNeededException aane) {
            return Response.status(400).entity(aane.getMessage()).build();
        }

    }

    /**
     * Method to make sure all required inputs are present.
     *
     * @return string
     * @author 2250
     * @since 20220201
     */
    public String save(String json) throws AllAttributesNeededException {

        Gson gson = new Gson();
        SoccorJerseys jersey = gson.fromJson(json, SoccorJerseys.class);

        if (jersey.getId() == null) {
            jersey.setId(0);
        }

        jersey = _sj.save(jersey);

        String temp = "";
        temp = gson.toJson(jersey);

        return temp;
    }

    public String save(String json, int id) throws AllAttributesNeededException {

        Gson gson = new Gson();
        SoccorJerseys jersey = gson.fromJson(json, SoccorJerseys.class);
        jersey.setId(id);
        if (jersey.getId() == null) {
            jersey.setId(0);
        }

        jersey = _sj.save(jersey);

        String temp = "";
        temp = gson.toJson(jersey);

        return temp;
    }



    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Response deleteSoccorJerseyById(@PathParam("id") Integer id) throws URISyntaxException {
        try {
            Optional<SoccorJerseys> soccorJerseyFound = _sj.findById(id);

            if (!soccorJerseyFound.isPresent()) {
                return Response.status(404).build(); // Not Found
            }

            _sj.deleteById(id);

            return Response.status(200).build(); // No Content (successful delete)
        } catch (Exception e) {
            System.out.println("Exception happened while deleting SoccorJersey.");
            return Response.status(500).entity(e.getMessage()).build(); // Internal Server Error
        }
    }
}
