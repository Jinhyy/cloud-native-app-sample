import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/call/{apiId}")
    public String apiCall(@PathVariable String apiId){
        return apiId;
    }

    @RequestMapping(value = "/call2/{apiId}", method = RequestMethod.GET)
    public String apiCall2(@PathVariable String apiId2){
        return apiId2;
    }
}
