package io.github.mobileteacher.apisample;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class GithubOwnerDeserializer implements JsonDeserializer<Owner> {


    @Override
    public Owner deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//        Log.d("XABUUUUUU", json.getAsString());
        JsonObject jsonObject = json.getAsJsonObject();
//        GithubRepo repo = new GithubRepo();
//
//        repo.setName(jsonObject.get("name").getAsString());
//        repo.setDescription(jsonObject.get("description").getAsString());
//        repo.setId(jsonObject.get("id").getAsInt());
//        JsonObject owner = jsonObject.get("owner").getAsJsonObject();
//        repo.setOwner(owner.get("login").getAsString());
        Owner owner = new Owner();
        owner.setName(jsonObject.get("login").getAsString());
        return owner;
    }
}
