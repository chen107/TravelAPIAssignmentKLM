package klm.cases.df.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static klm.cases.df.config.ApiConfiguration.USER;
import static klm.cases.df.config.ApiConfiguration.PSD;

public class RestTemplateCommonUtil {

    public static String restTemplateCommonUtil(String uri,Boolean isAuth){
        RestTemplate restTemplate = new RestTemplate();
        if (isAuth) restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(USER,PSD));
        return restTemplate.getForObject(uri, String.class);
    }

    public static JSONArray sortJSONArrayByField(JSONArray inArr, String field,boolean isAsc){
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < inArr.length(); i++) {
            jsonValues.add(inArr.getJSONObject(i));
        }
        Collections.sort( jsonValues, new Comparator<JSONObject>() {

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.get(field);
                    valB = (String) b.get(field);
                }
                catch (JSONException e) {
                    //no-op
                }
                if(isAsc)
                    return valA.compareTo(valB);
                else
                    return -valA.compareTo(valB);
            }
        });

        for (int i = 0; i < inArr.length(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }
        return sortedJsonArray;
    }
}
