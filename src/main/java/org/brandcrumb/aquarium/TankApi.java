package org.brandcrumb.aquarium;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.glassfish.jersey.server.JSONP;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("tank")
@Produces(MediaType.APPLICATION_JSON)
public class TankApi {

    private static final String ITEMS_URL = "/tank";

    @GET
    @JSONP(queryParam = "callback")
    public String getAllTanks(@QueryParam("callback") String callback) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);
        List<Tank> tanks = TankDao.getInstance().getAllTanks();
        return mapper.writeValueAsString(tanks);
    }

    @DELETE
    @JSONP(queryParam = "callback")
    public void deleteAllTanks() throws Exception {
        TankDao.getInstance().deleteAllTanks();
    }

    @GET
    @Path("/{id}")
    @JSONP(queryParam = "callback")
    public String getTank(@PathParam("id") int id) throws Exception {
        Tank tank = TankDao.getInstance().getTank(id);
        String json = new ObjectMapper().writeValueAsString(tank);
        return json;
    }

    @GET
    @Path("/{tank_id}/fish/{id}")
    @JSONP(queryParam = "callback")
    public String getFish(@PathParam("tank_id") int tank_id) throws Exception {
        Fish fish = TankDao.getInstance().getFish(tank_id);
        String json = new ObjectMapper().writeValueAsString(fish);
        return json;
    }

    @PUT
    @JSONP(queryParam = "callback")
    public void putUser(String tankJson) throws Exception {
        Tank tank = new ObjectMapper().readValue(tankJson, Tank.class);
        TankDao.getInstance().saveOrUpdateTank(tank);
    }

    @DELETE
    @Path("/{id}")
    @JSONP(queryParam = "callback")
    public void deleteTank(@PathParam("id") int id) throws Exception {
        TankDao.getInstance().deleteTank(id);
    }

}
