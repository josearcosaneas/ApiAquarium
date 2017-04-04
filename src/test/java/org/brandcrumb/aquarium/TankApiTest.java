package org.brandcrumb.aquarium;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.grizzly.http.server.HttpServer;
import org.json.JSONObject;
import org.junit.*;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TankApiTest extends CommonTest {

    private static HttpServer server;
    private WebTarget itemsTarget;
    private WebTarget itemsTarget2;
    private ObjectMapper objectMapper;
    private List<Tank> tanks;
    @BeforeClass
    public static void beforeTankApiTestClass() {
        server = new Server().startServer();
    }

    @Before
    public void beforeTankApiTest() throws Exception {
        tanks = insertTanks(1);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/");
        itemsTarget = target.path("tank/");
        itemsTarget2  = target.path("tank/"+tanks.get(0).getId());
        objectMapper = new ObjectMapper();
    }

    @After
    public void afterUserResourceTest() throws Exception {
        tankDao.deleteAllTanks();
    }

    @AfterClass
    public static void afterUserResourceTestClass() {
        server.shutdown();
    }

    @Test
    public void v1TankShouldReturnStatus200() {
        assertThat(itemsTarget.request().head().getStatus(), is(200));
    }

    @Test
    public void v1TankIdShouldReturnStatus200() {
        assertThat(itemsTarget2.request().head().getStatus(), is(200));
    }

    @Test
    public void getV1ItemsShouldReturnTypeApplicationJson() {
        assertThat(itemsTarget.request().get().getMediaType().toString(), is("application/json"));
    }

    @Test
    public void getV1ItemsShouldReturnListOfTanks() throws Exception {
        String json = itemsTarget.request().get(String.class);
        List<Tank> actual = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Tank.class));
        assertThat(actual.size(), is(1));
    }

    @Test
    public void getV1ItemsShouldReturnTheCorrectJson() throws Exception {

        String json = itemsTarget.request().get(String.class);
        System.out.println(json);
        JSONAssert.assertEquals("[{id: 1}]", json, false);
        JSONAssert.assertEquals("[{name: \"" + name_tank + "\"}]", json, false);
    }


    @Test
    public void getV1ItemIdShouldReturnSpecifiedTank() throws Exception {
        String json = itemsTarget2.request().get(String.class);
        Tank actual = objectMapper.readValue(json, Tank.class);
        assertThat(actual.getId(), is(equalTo(tank.getId())));
    }

    @Test
    public void getV1ItemsIdShouldReturnCorrectJson() throws Exception {
        Tank expected = tanks.get(0);
        String json = itemsTarget.request().get(String.class);
        JSONObject jsonObject = new JSONObject(json.substring(1, json.length()-1));
        JSONAssert.assertEquals("{id: " + expected.getId() + "}", jsonObject, false);
        JSONAssert.assertEquals("{name: \"" + expected.getName() + "\"}", jsonObject, false);
    }

    @Test
    public void putV1ItemsIdShouldSaveNewTank() throws Exception {
        String json = new ObjectMapper().writeValueAsString(tank);
        itemsTarget.request().put(Entity.text(json));
        Tank actual = tankDao.getTank(id_tank);
        assertThat(actual, is(not(nullValue())));
        assertThat(actual.getVolumen(), is(equalTo(tank.getVolumen())));
        assertThat(tankDao.getAllTanks().size(), is(1));
    }

    @Test
    public void putV1ItemsShouldUpdateExistingTank() throws Exception {
        tank.setName("new name");
        String json = new ObjectMapper().writeValueAsString(tank);
        itemsTarget.request().put(Entity.text(json));
        Tank actual = tankDao.getTank(tank.getId());
        String json1 = new ObjectMapper().writeValueAsString(actual);
        assertThat(actual.getId(), is(equalTo(1)));
    }





}
