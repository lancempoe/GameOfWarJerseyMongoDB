package com.lancep.controller.game;

import com.lancep.controller.WarResource;
import com.lancep.war.orm.War;
import com.lancep.service.WarService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.internal.routing.UriRoutingContext;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WarResourceTest {

    @Tested
    WarResource subject;
    @Injectable
    WarService warService;
    @Injectable PropertiesDelegate propertiesDelegate;
    @Injectable SecurityContext securityContext;

    public static final String BASE_URL = "www.chroniclesofprydain.com";
    public static final String WAR_ID = "1234";
    private static final int NUMBER_OF_RANK = 1;
    private static final int NUMBER_OF_SUITS = 1;

    private List<War> wars = new ArrayList();

    @Test
    public void getWarGamesRespondsOk() throws Exception {
        assertThat(subject.getWarGames().getStatusInfo(), is(Response.Status.OK));
    }

    @Test
    public void getWarGamesRespondsWithWarGames() throws Exception {
        new Expectations() {{
            warService.getAll(); result = wars;
        }};
        assertThat(subject.getWarGames().getEntity(), is(wars));
    }

    @Test
    public void getWarGamesRespondsWithTypeJson() throws Exception {
        assertThat(subject.getWarGames().getMediaType(), is(MediaType.APPLICATION_JSON_TYPE));
    }

    @Test
    public void createWarGameRespondsCreated() throws URISyntaxException {
        new Expectations() {{
            warService.create(anyInt, anyInt); result = WAR_ID;
        }};

        UriInfo uriInfo = getUriInfo();
        assertThat(subject.createWarGame(uriInfo, NUMBER_OF_SUITS, NUMBER_OF_RANK).getStatusInfo(), is(Response.Status.CREATED));
    }

    @Test
    public void createWarGameReturnsUrl() throws Exception {
        new Expectations() {{
            warService.create(anyInt, anyInt); result = WAR_ID;
        }};

        UriInfo uriInfo = getUriInfo();
        assertThat(subject.createWarGame(uriInfo, NUMBER_OF_SUITS, NUMBER_OF_RANK).getLocation().getPath(), is(String.format("%s/%s", BASE_URL, WAR_ID)));
    }

    @Test
    public void playReturnsOk() {
        assertThat(subject.quickGame(1,1).getStatusInfo(), is(Response.Status.OK));
    }

    @Test
    public void deleteReturnsOk() {
        assertThat(subject.deleteWar("1").getStatusInfo(), is(Response.Status.OK));
    }

    @Test
    public void deleteturnsOk() {
        assertThat(subject.deleteWar("12").getStatusInfo(), is(Response.Status.OK));
    }

    private UriInfo getUriInfo() throws URISyntaxException {
        URI uri = new URI(BASE_URL);
        ContainerRequest request = new ContainerRequest(uri, uri, "", securityContext, propertiesDelegate);
        return new UriRoutingContext(request);
    }

}