package app.it;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.accumulo.core.client.Accumulo;
import org.apache.accumulo.core.client.AccumuloClient;
import org.apache.accumulo.core.client.BatchScanner;
import org.apache.accumulo.core.client.TableExistsException;
import org.apache.accumulo.core.client.TableNotFoundException;
import org.apache.accumulo.core.data.Key;
import org.apache.accumulo.core.data.Range;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.security.Authorizations;
import org.apache.hadoop.shaded.javax.ws.rs.core.MediaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import bean.Query;
import config.RapidoConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
/**
 * Query Controller Integration Test - requires a running Accumulo
 */
public class QueryControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private void createTable(AccumuloClient client, String tableName) throws Exception{
        try {
            client.tableOperations().create(tableName);
        } catch (TableExistsException  e) {}
    }
    private void deleteTable(AccumuloClient client, String tableName) throws Exception {
        try {
            client.tableOperations().delete(tableName);
        } catch (TableNotFoundException e) {}
    }

    // Tests that require an active Accumulo running

    @Test
    public void getTablesTest() throws Exception {
        this.mockMvc.perform(
                get("/getTables"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.tables").exists());
                //.andExpect(jsonPath("$.tables.accumulo.metadata").value("!0"));
                //.andExpect(jsonPath("$").value("accumulo.metadata:!0,accumulo.replication:+rep,accumulo.root:+r,trace:1"));
    }


    @Test
    /**
     * Test creating and deleting table with the configured user.
     * TODO: make this check permissions
     */
    public void accumuloClientTest() throws Exception {
        String tableName = "test";
        //String rowQuery = "test";
        RapidoConfig conf = new RapidoConfig();

        Query q = null;
        try (AccumuloClient client = Accumulo.newClient().to(conf.instance(), conf.zk())
                .as(conf.username(), conf.passowrd()).zkTimeout(5000).build()) {

            deleteTable(client, tableName);
            createTable(client, tableName);

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
            q = new Query(new HashMap<>(), tableName, "", count);

            deleteTable(client, tableName);
        }
        assertNotNull(q);
        assertEquals(0, q.getCount());
    }
}
