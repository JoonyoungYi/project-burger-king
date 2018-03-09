package kr.yearnning.hamburger.api;

import android.app.Application;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kr.yearnning.hamburger.model.Food;
import kr.yearnning.hamburger.utils.Argument;
import kr.yearnning.hamburger.utils.RequestManager;

public class FoodListApi extends ApiBase {

    private final static String TAG = "FoodListApi";
    private JSONObject jsonObject = null;

    /**
     * Init
     */
    public FoodListApi(Application application) {

        /*

         */
        RequestManager requestManager = new RequestManager("/food/list/set_menu.json", "GET", "http");
        requestManager.doRequest();

        response = requestManager.getResponse_body();
    }

    /**
     * @return
     */
    public int getRequestCode() {

        try {
            jsonObject = new JSONObject(response);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "response: " + response);
            return Argument.REQUEST_CODE_FAIL;
        }

        return Argument.REQUEST_CODE_SUCCESS;
    }

    /**
     * @return
     */
    public ArrayList<Food> getResult() {

        ArrayList<Food> foods = new ArrayList<Food>();
        try {

            JSONArray jsonArray = jsonObject.getJSONArray("foods");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                Food food = new Food();

                if (!obj.isNull("name")) {
                    String name = obj.getString("name");
                    food.setName(name);
                }

                if (!obj.isNull("price")) {
                    int price = obj.getInt("price");
                    food.setPrice(price);
                }

                if (!obj.isNull("kcal")) {
                    int kcal = obj.getInt("kcal");
                    food.setKcal(kcal);
                }

                if (!obj.isNull("img_url")) {
                    String img_url = obj.getString("img_url");
                    food.setImg_url(img_url);
                }

                foods.add(food);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            foods = null;
        }

        return foods;

    }
}
