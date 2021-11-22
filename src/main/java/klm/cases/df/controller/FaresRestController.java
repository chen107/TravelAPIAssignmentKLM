package klm.cases.df.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static klm.cases.df.config.ApiConfiguration.*;

@Controller
@RequestMapping(END_FARES)
public class FaresRestController {

    /**
     *
     * @param origin
     * @param destination
     * @return
     */
    @GetMapping(END_FARES_ORIGIN_DEST)
    public HttpEntity<?> getFare(@PathVariable String origin,@PathVariable String destination){
        String uri = URI_FARES_MOCK_SYS+"/"+origin+"/"+destination;
        String result = RestTemplateCommonUtil.restTemplateCommonUtil(uri,true);
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }
}
