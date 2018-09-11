package io.github.mobileteacher.apisample;
// based on: http://www.vogella.com/tutorials/Retrofit/article.html


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class GithubRepoDeserializer implements JsonDeserializer<GithubRepo>{


    @Override
    public GithubRepo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        GithubRepo repo = new GithubRepo();

        repo.setName(jsonObject.get("name").getAsString());
        repo.setDescription(jsonObject.get("description").getAsString());
        repo.setId(jsonObject.get("id").getAsInt());
        JsonObject owner = jsonObject.get("owner").getAsJsonObject();
        //repo.setOwner(owner.get("login").getAsString());

        return repo;
    }
}
