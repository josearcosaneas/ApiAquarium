package org.brandcrumb.aquarium;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.glassfish.jersey.server.JSONP;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("fish")
@Produces(MediaType.APPLICATION_JSON)
public class FishApi {

    private static final String ITEMS_URL = "/fish";

    @GET
    @JSONP(queryParam = "callback")
    public String getAllFishes(@QueryParam("tank_id") int tank_id,
                              @QueryParam("callback") String callback) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_DEFAULT);
        List<Fish> fishes = FishDao.getInstance().getAllFishesOfTank(tank_id);
        return mapper.writeValueAsString(fishes);
    }

    @DELETE
    @JSONP(queryParam = "callback")
    public void deleteAllFishes() throws Exception {
        FishDao.getInstance().deleteAllFishes();
    }

    @GET
    @Path("/{id}")
    @JSONP(queryParam = "callback")
    public String getFish(@PathParam("id") int id) throws Exception {
        Fish fish = FishDao.getInstance().getFish(id);
        return new ObjectMapper().writeValueAsString(fish);
    }

    @PUT
    @JSONP(queryParam = "callback")
    public void putFish(String fishJson) throws Exception {
        Fish fish = new ObjectMapper().readValue(fishJson, Fish.class);
        FishDao.getInstance().saveOrUpdateFish(fish);
    }

    @DELETE
    @Path("/{id}")
    @JSONP(queryParam = "callback")
    public void deleteFish(@PathParam("id") int id) throws Exception {
        FishDao.getInstance().deleteFish(id);
    }

}
