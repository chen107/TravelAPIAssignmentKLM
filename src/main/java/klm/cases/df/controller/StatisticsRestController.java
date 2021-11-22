package klm.cases.df.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static klm.cases.df.config.ApiConfiguration.*;


import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(END_STATISTICS)
public class StatisticsRestController {

    @Autowired
    private ServerProperties serverProperties;

    @GetMapping(END_STATISTICS_OK)
    public HttpEntity<?> getStatisticsOk(){
        JSONObject results = new JSONObject();

            String uri200 = END_HEAD+serverProperties.getPort()+PATH_STATISTICS_OK;
            Map<String,Double> statistic200 = handleTheStatistics(uri200);
            results.put(TOTAL_COUNT_RS_OK,statistic200.get(TOTAL_COUNT));

        return  new ResponseEntity<>(results.toString(), HttpStatus.OK);
    }

    @GetMapping(END_STATISTICS_4XX)
    public HttpEntity<?> getStatistics4xx(){
        JSONObject results = new JSONObject();

        String uri404 = END_HEAD+serverProperties.getPort()+PATH_STATISTICS_4XX;
        Map<String,Double> statistic404 = handleTheStatistics(uri404);
        Double totalCount404 = statistic404.get(TOTAL_COUNT);
        results.put(TOTAL_COUNT_RS_4XX,totalCount404);

        return  new ResponseEntity<>(results.toString(), HttpStatus.OK);
    }

    @GetMapping(END_STATISTICS_5XX)
    public HttpEntity<?> getStatistics5xx(){
        JSONObject results = new JSONObject();

        String uri500 = END_HEAD+serverProperties.getPort()+PATH_STATISTICS_5XX;
        Map<String,Double> statistic500 = handleTheStatistics(uri500);
        Double totalCount500 = statistic500.get(TOTAL_COUNT);
        results.put(TOTAL_COUNT_RS_5XX,totalCount500);

        return  new ResponseEntity<>(results.toString(), HttpStatus.OK);
    }


    @GetMapping(END_STATISTICS_ALL)
    public HttpEntity<?> getStatisticsAll(){
        JSONObject results = new JSONObject();
        String uriAll = END_HEAD+serverProperties.getPort()+PATH_STATISTICS_ALL;
        Map<String,Double> statisticAll = handleTheStatistics(uriAll);
        results.put(TOTAL_COUNT_REQUEST,statisticAll.get(TOTAL_COUNT));
        results.put(MAX_RESPONSE_TIME,statisticAll.get(MAX_TIME));
        results.put(AVE_RESPONSE_TIME,statisticAll.get(AVE_TIME));
        return  new ResponseEntity<>(results.toString(), HttpStatus.OK);
    }

    /**
     *
     * @param uri uri to get the statistic metrics
     * @return
     */
    private Map<String,Double> handleTheStatistics(String uri){
        Map<String,Double> refactorMap = new HashMap<>();
        try {
            JSONObject resultObj = new JSONObject(RestTemplateCommonUtil.restTemplateCommonUtil(uri, false));
            JSONArray measurementsArr = resultObj.getJSONArray(JSON_NODE_MEASUREMENTS);
            Double countTotal = 0.0;
            Double totalTime = 0.0;
            for (Object obj : measurementsArr) {
                JSONObject jso = (JSONObject) obj;
                String cate = jso.getString(JSON_NODE_STATISTIC);
                if (cate.equalsIgnoreCase(JSON_NODE_COUNT)) {
                    countTotal  = Double.parseDouble(jso.get(JSON_NODE_VALUE).toString());
                    refactorMap.put(TOTAL_COUNT, countTotal);
                } else if (cate.equalsIgnoreCase(JSON_NODE_MAX)) {
                    refactorMap.put(MAX_TIME, Double.parseDouble(jso.get(JSON_NODE_VALUE).toString()));
                } else if (cate.equalsIgnoreCase(JSON_NODE_TOTAL_TIME)) {
                    totalTime = Double.parseDouble(jso.get(JSON_NODE_VALUE).toString());
                }
            }
            refactorMap.put(AVE_TIME, totalTime/countTotal);
        }catch(Exception e){
            refactorMap.put(TOTAL_COUNT, 0.0);
            refactorMap.put(MAX_TIME, 0.0);
            refactorMap.put(AVE_TIME, 0.0);
        }
            return refactorMap;
    }
}
