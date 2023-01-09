package org.acme.controller;

import io.vertx.core.json.JsonObject;
import org.acme.model.Game;
import org.acme.model.Genre;
import org.acme.model.Mode;
import org.acme.model.Platform;
import org.acme.service.GameService;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Path("/api")
public class GameController {

    @GET
    public Response list(){
        List<Game> gameList = Game.listAll();
        return Response.ok().entity(gameList).build();
    }
    @GET
    @Path("/{path}")
    public Response getGame(@QueryParam("name") String name,@PathParam("path")String path) {
//        Game game = Game.find("name = ?1", name).firstResult();
        Game game = Game.find("name = ?1", path).firstResult();
        return Response.ok().entity(game).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(JsonObject payload) throws ParseException {
        Game game = new Game();
        Genre genre = new Genre();
        Platform platform = new Platform();
        Mode mode = new Mode();
        game.name = payload.getString("name");
        game.description = payload.getString("description");
        Object dateObject = payload.getValue("releaseDate");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = simpleDateFormat.parse(dateObject.toString());
        game.releaseDate = date;
        game.developer = payload.getString("developer");
        game.rating = payload.getString("rating");
        game.status = payload.getString("status");

        genre.name = payload.getString("genre");
        game.genre = genre.name.split(",");
        game.genreData = Arrays.toString(game.genre);

        platform.name = payload.getString("platform");
        game.platform = platform.name.split(",");
        game.platformData = Arrays.toString(game.platform);

        mode.name = payload.getString("mode");
        game.mode = mode.name.split(",");
        game.modeData = Arrays.toString(game.mode);

        game.persist();
        genre.id = game.id;
        platform.id = game.id;
        mode.id = game.id;
        mode.persist();
        platform.persist();
        genre.persist();
        return Response.ok().entity(game).build();
    }

    @DELETE
    @Transactional
    public JsonObject delete(@QueryParam("name") String name) {
        Game.delete("name = ?1",name);
        return new JsonObject();
    }

    @PUT
    @Transactional
    public JsonObject update(@QueryParam("id") String id, @QueryParam("newName") String newName,
                             @QueryParam("description") String description) {
        Game.update("description = ?1, name = ?2 where id = ?3", description, newName, id);
        return new JsonObject();
    }

    @PATCH
    @Transactional
    public JsonObject update(@QueryParam("oldName") String oldName, @QueryParam("newName") String newName) {
        GameService.updateGame(newName,oldName);
        return new JsonObject();
    }

}
