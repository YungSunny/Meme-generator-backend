package com.memeapp.repo;

import com.memeapp.Model.Meme;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemesRepo {

    private final int minWidth = 430;
    private final int maxWidth = 501;
    private final int minHeight = 299;
    private final int maxHeight = 501;

    public List<Meme> getMeme() throws IOException {

       List<Meme> meme = new ArrayList<>();

        JSONTokener tokener = new JSONTokener(new URL(
                "https://api.imgflip.com/get_memes").openStream());

        JSONObject root = new JSONObject(tokener);

        JSONObject data = root.getJSONObject("data");
        JSONArray memes = data.getJSONArray("memes");

        for (int i = 0; i < memes.length(); i++) {
           if(memes.getJSONObject(i).getInt("width") < maxWidth && memes.getJSONObject(i).getInt("width") > minWidth){
               if (memes.getJSONObject(i).getInt("height") < maxHeight && memes.getJSONObject(i).getInt("height") > minHeight){
                   meme.add(new Meme(memes.getJSONObject(i).getInt("width"), memes.getJSONObject(i).getInt("height"),
                           memes.getJSONObject(i).getString("url")));
               }
           }
        }
        return meme;
    }
}
