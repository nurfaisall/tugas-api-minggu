package org.acme.service;

import io.vertx.core.json.JsonObject;
import org.acme.model.Game;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class GameService {
    @Transactional
    public static JsonObject updateGame(String newGame, String oldGame){
        Game.update("name = ?1 where name = ?2",newGame,oldGame);
        return new JsonObject();
    }

}
