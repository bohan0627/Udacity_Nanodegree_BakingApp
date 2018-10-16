package com.bohan.android.bakingapp.Data.source;

import com.bohan.android.bakingapp.BaseModel.Recipe;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RecipeListService {
    public String BASE_URL = "https://d17h27t6h515a5.cloudfront.net";

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Observable<List<List<Recipe>>> recipesFromRemote();
}
