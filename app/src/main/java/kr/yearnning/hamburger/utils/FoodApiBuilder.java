package kr.yearnning.hamburger.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.yearnning.hamburger.model.Food;

/**
 * Created by yearnning on 14. 10. 24..
 */
public class FoodApiBuilder {

    public String build() {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {

            jsonArray.put(createFoodJSONObject("크레미페퍼와퍼세트", "https://delivery.burgerking.co.kr/files/product/009686F29A4D4DAE9080D2BDC9D93B37.png",8600, 1233));
            jsonArray.put(createFoodJSONObject("크레미페퍼와퍼주니어세트", "https://delivery.burgerking.co.kr/files/product/92D6C926A92C4790A60D1C9FC240CFB6.png",6900, 1233));
            jsonArray.put(createFoodJSONObject("와퍼세트", "https://delivery.burgerking.co.kr/files/product/E7FC6D7FA4F44CCB99939C90173874DD.png",7600, 1122));
            jsonArray.put(createFoodJSONObject("불고기와퍼세트", "https://delivery.burgerking.co.kr/files/product/8582FFBC2231460B88766141916F54D5.png", 7600, 1185));
            jsonArray.put(createFoodJSONObject("치즈와퍼세트", "https://delivery.burgerking.co.kr/files/product/1D75B27285484690B37B9B0D8554F4FF.png", 8200, 1219));
            jsonArray.put(createFoodJSONObject("불고기치즈와퍼세트", "https://delivery.burgerking.co.kr/files/product/91066A1C0D824F99B567EBB10AEF9ABB.png", 8200, 1722));
            jsonArray.put(createFoodJSONObject("콰트로치즈와퍼세트", "https://delivery.burgerking.co.kr/files/product/1108D5B44723494D96CF9D2CAF1FDA69.png", 8600, 1272));
            jsonArray.put(createFoodJSONObject("베이컨치즈와퍼세트", "https://delivery.burgerking.co.kr/files/product/F333DB0AFF834687875763F1D7142539.png", 9400, 1283));
            jsonArray.put(createFoodJSONObject("더블와퍼세트", "https://delivery.burgerking.co.kr/files/product/AFA77E452CCA4579A6DDCD76098BD5C4.png", 10000, 1437));
            jsonArray.put(createFoodJSONObject("더블치즈와퍼세트", "https://delivery.burgerking.co.kr/files/product/2C152E613EF64989A6A25AACFBA80EBF.png", 10600, 1515));
            jsonArray.put(createFoodJSONObject("와퍼주니어세트", "https://delivery.burgerking.co.kr/files/product/7C55030DEAED4A7E9BE05F1ECEBBAFB6.png", 6100, 902));
            jsonArray.put(createFoodJSONObject("불고기와퍼주니어세트", "https://delivery.burgerking.co.kr/files/product/D7F2609560E44A24A30560E019E45C7D.png", 6100, 883));
            jsonArray.put(createFoodJSONObject("치즈와퍼주니어세트", "https://delivery.burgerking.co.kr/files/product/1BECE2B8FACC45B38F6FFAE17E9CE03E.png", 6400, 941));
            jsonArray.put(createFoodJSONObject("콰트로치즈와퍼주니어세트", "https://delivery.burgerking.co.kr/files/product/CD42D9E2ACEB46B3ACE1B1B2EFF060CE.png", 6900, 949));
            jsonArray.put(createFoodJSONObject("치킨크리스피버거세트", "https://delivery.burgerking.co.kr/files/product/3CBAA6F5F7C1457AB6E3168C767CBE57.png", 7500, 1064));
            jsonArray.put(createFoodJSONObject("롱치킨버거세트", "https://delivery.burgerking.co.kr/files/product/9D681ED49E994CD58E0823CF434C7F36.png", 6900, 1174));
            jsonArray.put(createFoodJSONObject("더블치즈버거세트", "https://delivery.burgerking.co.kr/files/product/7D14C078734842AA90709E405DE313FB.png", 7100, 1023));
            jsonArray.put(createFoodJSONObject("베이컨더블치즈세트", "https://delivery.burgerking.co.kr/files/product/697AE1D4A125418EA6508B535066CB87.png", 8300, 1071));
            jsonArray.put(createFoodJSONObject("치즈버거세트", "https://delivery.burgerking.co.kr/files/product/8A6C99946B20457FBE023FFFDB83E6EA.png", 5300, 869));
            jsonArray.put(createFoodJSONObject("불고기버거세트", "https://delivery.burgerking.co.kr/files/product/F484DA31AB4F40FCA921C9DE1EB60F1F.png", 5300, 874));
            jsonArray.put(createFoodJSONObject("불고기치킨버거세트", "https://delivery.burgerking.co.kr/files/product/965155D8E4284AA19E06847FEA6A28C8.png", 6900, 895));
            jsonArray.put(createFoodJSONObject("갈릭스테이크세트", "https://delivery.burgerking.co.kr/files/product/3804B0B153B9417EB71BF390D93DB5F7.png", 8600, 1140));
            jsonArray.put(createFoodJSONObject("치즈갈릭스테이크세트", "https://delivery.burgerking.co.kr/files/product/640D36DD572F435B9C773685BBDFBC08.png", 9200, 1179));
            jsonArray.put(createFoodJSONObject("베이컨치즈갈릭세트", "https://delivery.burgerking.co.kr/files/product/502A34952EF140C2A7535B1006CCB450.png", 10400, 1277));

            jsonObject.put("foods", jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private JSONObject createFoodJSONObject(String name, String img_url, int price, int kcal) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("img_url", img_url);
        jsonObject.put("price", price);
        jsonObject.put("kcal", kcal);

        return jsonObject;
    }

}
