package app;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.accumulo.core.client.Accumulo;
import org.apache.accumulo.core.client.AccumuloClient;
import org.apache.accumulo.core.client.BatchScanner;
import org.apache.accumulo.core.client.TableNotFoundException;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Range;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.security.Authorizations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bean.Query;
import bean.Tables;
import config.RapidoConfig;

@RestController
public class QueryController {
    private static Logger log = LoggerFactory.getLogger(QueryController.class);

    private static final String instance = "uno";
    private static final String zk = "localhost:2181";
    private static final int zk_timeout = 5000; // 5 seconds

    @RequestMapping("/getTables")
    public Tables getTables() {
        RapidoConfig config = Rapido.getConfig();
        Tables tables = new Tables(config);
        try (AccumuloClient client = Accumulo.newClient().to(config.instance(), config.zk())
                .as(config.username(), config.passowrd()).zkTimeout(config.timeout()).build()) {
            tables.set(client.tableOperations().tableIdMap());
        }
        log.info("Found {} tables. ", tables.getTables().size());
        return tables;
    }

    @RequestMapping("/query")
    public Query query(@RequestParam(value = "userName") String userName,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "row") String row,
                       @RequestParam(value = "tableName") String tableName) {
        log.debug("QueryController saw query row = " + row);

        Query q = null;
        log.info("Connecting to Accumulo...");
        try (AccumuloClient client = Accumulo.newClient().to(instance, zk)
                .as(userName, password).zkTimeout(zk_timeout).build()) {
            // use the client
            BatchScanner bs = client.createBatchScanner(tableName, Authorizations.EMPTY);
            ArrayList<Range> ranges = new ArrayList<>();
            ranges.add(new Range());
            bs.setRanges(ranges);
            int count = 0;
            for (Map.Entry<Key, Value> entry : bs) {
                count++;
            }

            bs.close();
            q = new Query(tableName, row, count);
        } catch (TableNotFoundException e) {
            e.printStackTrace();
        }

        return q;
    }
}
