package app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bean.Query;

@RestController
public class QueryController {

    @RequestMapping("/query")
    public Query query(@RequestParam(value="userName") String userName,
                       @RequestParam(value="password") String password,
                       @RequestParam(value="row") String row,
                       @RequestParam(value="tableName") String tableName) {
        System.out.println("QueryController saw query row = " + row);
        return new Query(userName, password, row, tableName);
    }
}
