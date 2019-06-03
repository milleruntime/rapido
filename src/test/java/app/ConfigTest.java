package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.Test;

import config.RapidoConfig;

public class ConfigTest {

    @Test
    public void testDefaults() throws IOException {
        RapidoConfig c = new RapidoConfig();

        assertEquals("root", c.username());
        assertEquals("secret", c.passowrd());
        assertEquals("uno", c.instance());
        assertEquals("localhost:2181", c.zk());
        assertEquals(5000, c.timeout());
    }
}
