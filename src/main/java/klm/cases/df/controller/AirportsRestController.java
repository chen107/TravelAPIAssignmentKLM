package klm.cases.df.controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static klm.cases.df.config.ApiConfiguration.*;

@Controller
@RequestMapping(END_AIRPORTS)
public class AirportsRestController {

    @GetMapping
    public HttpEntity<?> getAllAirports(@RequestParam(value=SIZE,required = false) int size,
                                        @RequestParam(value=PAGE,required = false) int pageNumber,
                                        @RequestParam(value=ORDERBY,required = false) String orderby) throws Exception {
        String uri = URI_AIRPORTS_MOCK_SYS;
        StringBuilder path = new StringBuilder("");
        if(size>0){
            path.append("size="+size+"&");
        }
        if(pageNumber>0){
            path.append("page="+pageNumber+"&");
        }

        if(!path.toString().isEmpty()){
            uri=uri+"?"+path.substring(0,path.length()-1);
        }

        String result = RestTemplateCommonUtil.restTemplateCommonUtil(uri,true);
        if(orderby!=null && !orderby.isEmpty()){
            result = handleOrderby(result,orderby);
        }
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(END_AIRPORTS_CODE)
    public HttpEntity<?> getAnAirport(@PathVariable String code){
        String uri = URI_AIRPORTS_MOCK_SYS+"/"+code;
        String result = RestTemplateCommonUtil.restTemplateCommonUtil(uri,true);
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    private String handleOrderby(String result,String orderby){
        JSONObject resultObj = new JSONObject(result);
        JSONObject embeddedObj = new JSONObject(result).getJSONObject(JSON_NODE_EMBEDDED);
        JSONArray locationsArr = embeddedObj.getJSONArray(JSON_NODE_LOCATIONS);
        JSONArray sortedLocationsArr = new JSONArray(locationsArr);
        String[] orderbyArr = orderby.split(",");
        for(String s: orderbyArr){
            String[] orderEles = s.split(" ");
            if(orderEles.length==2){
                String field = s.split(" ")[0];
                String order = s.split(" ")[1];
                if(order.equalsIgnoreCase(ORDERBY_ASC_OP)||order.equalsIgnoreCase(ORDERBY_DESC_OP)){
                    boolean isAsc = order.equalsIgnoreCase(ORDERBY_ASC_OP)?true:false;
                    sortedLocationsArr = RestTemplateCommonUtil.sortJSONArrayByField(sortedLocationsArr,field,isAsc);
                }

            }
        }
        embeddedObj.put(JSON_NODE_LOCATIONS,sortedLocationsArr);
        resultObj.put(JSON_NODE_EMBEDDED,embeddedObj);
        return resultObj.toString();
    }
}
