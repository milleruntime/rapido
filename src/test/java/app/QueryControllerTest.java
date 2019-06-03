package app;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
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
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.jayway.jsonpath.PathNotFoundException;

import bean.Query;
import config.RapidoConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QueryControllerTest {
    private static final String instance = "uno";
    private static final String zk = "localhost:2181";
    private static final String userName = "root";
    private static final String password = "secret";

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
                .andDo(print()).andExpect(status().isOk());
                //.andExpect(jsonPath("$.tables").value("accumulo.metadata:!0,accumulo.replication:+rep,accumulo.root:+r,trace:1"));
    }


    /*@Test
    public void accumuloClientTest() throws Exception {
        String tableName = "test";
        String row = "row1";

        Query q = null;
        try (AccumuloClient client = Accumulo.newClient().to(instance, zk)
                .as(userName, password).zkTimeout(5000).build()) {

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
            q = new Query(tableName, row, count);

            deleteTable(client, tableName);
        }
        assertNotNull(q);
        assertEquals(0, q.getCount());
    }*/

    /*@Test
    public void basicTest() throws Exception {

        this.mockMvc.perform(
                get("/query").param("userName", "root")
                        .param("password", "secret")
                        .param("row", "blah")
                        .param("tableName", "test"))
                .andDo(print()).andExpect(status().isOk())
                //.andExpect(jsonPath("$.row").value("blah"))
                .andExpect(jsonPath("$.tableName").value("test"))
                .andExpect(jsonPath("$.count").value("0"));
    }*/

    /*@Test
    public void testMissingRange() throws Exception {

        try {
            this.mockMvc.perform(
                    get("/query").param("userName", "root")
                            .param("password", "secret")
                            .param("row", "blah")
                            .param("tableName", "test"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.row").value("blah"))
                    .andExpect(jsonPath("$.tableName").value("test"))
                    .andExpect(jsonPath("$.count").value("1"));
        } catch (Exception e) {
            Throwable t = e.getCause();
            if (t.getClass().equals(IllegalArgumentException.class)){
                // s'all Goodman
                if (t.getMessage().startsWith("ranges"))
                    System.out.println("Saw IllegalArgument for = " + e.getCause().getMessage());
                else
                    throw e;
            } else {
                throw e;
            }
        }
    }*/

    /*@Test
    public void testBadContent() throws Exception {

        try {
            this.mockMvc.perform(
                    get("/query").param("userName", "root")
                            .param("password", "secret")
                            .param("row", "blah")
                            .param("tableName", "test"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.tableName").value("Hello, World!"));
        } catch (AssertionError e) {
            Throwable t = e.getCause();
            if (t.getClass().equals(PathNotFoundException.class)){
                // s'all Goodman
            } else {
                throw e;
            }
        }
    }*/


}
