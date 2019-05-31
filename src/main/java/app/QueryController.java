package app;

import java.util.Collections;
import java.util.Map;

import org.apache.accumulo.core.client.Accumulo;
import org.apache.accumulo.core.client.AccumuloClient;
import org.apache.accumulo.core.client.AccumuloException;
import org.apache.accumulo.core.client.AccumuloSecurityException;
import org.apache.accumulo.core.client.BatchScanner;
import org.apache.accumulo.core.client.TableNotFoundException;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.security.Authorizations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bean.Query;

@RestController
public class QueryController {

    @RequestMapping("/query")
    public Query query(@RequestParam(value = "userName") String userName,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "row") String row,
                       @RequestParam(value = "tableName") String tableName) {
        System.out.println("QueryController saw query row = " + row);

        String instance = "uno";
        String zk = "localhost:2181";

        Query q = null;
        try (AccumuloClient client = Accumulo.newClient().to(instance, zk)
                .as(userName, password).build()) {
            // use the client
            BatchScanner bs = client.createBatchScanner(tableName, Authorizations.EMPTY);
            bs.setRanges(Collections.emptyList());
            int count = 0;
            for (Map.Entry<Key, Value> entry : bs) {
                count++;
            }

            bs.close();
            q = new Query(row, tableName, count);
        } catch (TableNotFoundException e) {
            e.printStackTrace();
        }

        return q;
    }
}
