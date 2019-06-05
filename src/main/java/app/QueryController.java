package app;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bean.Query;
import bean.Tables;
import config.RapidoConfig;

@RestController
public class QueryController {
    private static Logger log = LoggerFactory.getLogger(QueryController.class);

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
    public Query query(@RequestParam(value = "startRow") String startRow,
                       @RequestParam(value = "endRow") String endRow,
                       @RequestParam(value = "tableName") String tableName) {
        RapidoConfig config = Rapido.getConfig();

        Query q = null;
        Map<Key, Value> results = new HashMap<>();
        Text searchRowID = new Text(startRow);
        Range userRange = new Range(new Text(startRow), new Text(endRow));
        log.info("Connecting to Accumulo...");
        try (AccumuloClient client = Accumulo.newClient().to(config.instance(), config.zk())
                .as(config.username(), config.passowrd()).zkTimeout(config.timeout()).build()) {
            // use the client
            BatchScanner bs = client.createBatchScanner(tableName, Authorizations.EMPTY);
            ArrayList<Range> ranges = new ArrayList<>();
            ranges.add(userRange);
            bs.setRanges(ranges);

            log.info("Executing row query '{}-{}' for {} on {}", startRow, endRow, config.username(), config.instance());
            int count = 0;
            for (Map.Entry<Key, Value> entry : bs) {
                //Text rowID = entry.getKey().getRow();
                //log.info("Checking against RowID = " + rowID);
                //if (searchRowID.equals(rowID)) {
                    results.put(entry.getKey(), entry.getValue());
                    count++;
                //}
            }
            bs.close();
            log.info("Row query '{}-{}' for {} on {} found {}", startRow, endRow, config.username(), config.instance(), count);

            q = new Query(results, tableName, startRow, count);
        } catch (TableNotFoundException e) {
            log.error("Error executing query", e);
        }

        return q;
    }

    @RequestMapping("/countRows")
    public int countRows(@RequestParam(value = "rowRange") String rowRange,
                       @RequestParam(value = "tableName") String tableName) {
        log.debug("QueryController countRows saw rowRange = " + rowRange);
        RapidoConfig config = Rapido.getConfig();
        Range range = new Range();
        ArrayList<Range> ranges = new ArrayList<>();

        int count = 0;

        if (rowRange != null) {
            range = new Range(rowRange);
        }

        try (AccumuloClient c = Accumulo.newClient().to(config.instance(), config.zk())
                .as(config.username(), config.passowrd()).zkTimeout(config.timeout()).build()) {
            BatchScanner bs = c.createBatchScanner(tableName, Authorizations.EMPTY);
            ranges.add(range);
            bs.setRanges(ranges);
            for (Map.Entry<Key, Value> entry : bs) {
                count++;
            }
            bs.close();
        } catch (TableNotFoundException e) {
            log.error("Error executing query", e);
        }

        return count;
    }
}
