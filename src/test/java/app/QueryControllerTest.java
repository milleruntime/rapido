package app;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import net.bytebuddy.implementation.bytecode.Throw;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMissingRange() throws Exception {

        try {
            this.mockMvc.perform(
                    get("/query").param("userName", "root")
                            .param("password", "secret")
                            .param("row", "blah")
                            .param("tableName", "test"))
                    .andDo(print()).andExpect(status().isOk())
                    .andExpect(jsonPath("$.content").value("Hello, World!"));
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
    }


}
