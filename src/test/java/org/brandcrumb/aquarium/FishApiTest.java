package org.brandcrumb.aquarium;

import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.*;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FishApiTest extends CommonTest {

    private static HttpServer server;
    private WebTarget itemsTarget;
    private WebTarget itemsTarget2;
    private ObjectMapper objectMapper;
    List<Tank> tanks;

    @BeforeClass
    public static void beforeFishApiTestClass() {
        server = new Server().startServer();
    }

    @Before
    public void beforeFishApiTest() throws Exception {
        tanks = insertTanks(1);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/");
        itemsTarget = target.path("fish/");
        itemsTarget2 = target.path("fish/" + fish.getId());
        objectMapper = new ObjectMapper();
    }

    @AfterClass
    public static void afterUserResourceTestClass() {
        server.shutdown();
    }

    @Test
    public void v1ItemsShouldReturnStatus200() {
        assertThat(itemsTarget2.request().head().getStatus(), is(200));
    }

    @Test
    public void getV1ItemsShouldReturnTypeApplicationJson() {
        assertThat(itemsTarget2.request().get().getMediaType().toString(), is("application/json"));
    }


    @Test
    public void getV1ItemsShouldReturnTheCorrectJson() throws Exception {
        System.out.println(itemsTarget.getUri());
        String json = itemsTarget2.request().get(String.class);
        System.out.println(json);
        Fish actual = objectMapper.readValue(json, Fish.class);
        System.out.println(actual);

        JSONAssert.assertEquals("{id: 1}", json, false);
        JSONAssert.assertEquals("{name: \"" + name_fish + "\"}", json, false);
    }

    @Test
    public void deleteV1ItemsShouldDeleteAllFishes() {
        itemsTarget.request().delete();
        assertThat(fishDao.getAllFishes().size(), is(0));
    }

    @Test
    public void getV1ItemsIdShouldReturnSpecifiedFish() throws Exception {
        Fish expected = fish;


        String json = itemsTarget2.request().get(String.class);
        Fish actual = objectMapper.readValue(json, Fish.class);
        assertThat(actual, is(not(equalTo(expected))));
    }

    @Test
    public void getV1ItemIdShouldReturnCorrectJson() throws Exception {
        String json = itemsTarget2.request().get(String.class);
        JSONAssert.assertEquals("{id: " + id_fish + "}", json, false);
        JSONAssert.assertEquals("{name: \"" + name_fish + "\"}", json, false);
        JSONAssert.assertEquals("{type: \"" + type + "\"}", json, false);
        JSONAssert.assertEquals("{price: " + price + "}", json, false);
    }

    @Test
    public void putV1ItemsIdShouldSaveNewFish() throws Exception {
        String json = new ObjectMapper().writeValueAsString(fish);
        itemsTarget.request().put(Entity.text(json));
        Fish actual = fishDao.getFish(id_fish);
        assertThat(actual, is(not(nullValue())));
        System.out.println(fish.getName());
        System.out.println(actual.getName());
        assertThat(actual.getId(), is(equalTo(fish.getId())));
        assertThat(fishDao.getAllFishes().size(), is(2));
    }



    @Test
    public void deleteV1ItemsIdShouldDeleteExistingFish() throws Exception {

        itemsTarget.path("/" + 1).request().delete();
        assertThat(fishDao.getAllFishes().size(), is(1));
    }


}
