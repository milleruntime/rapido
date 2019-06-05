package app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.Test;

import config.RapidoConfig;

public class ConfigTest {

    @Test
    public void testReadProperties() throws IOException {
        RapidoConfig c = new RapidoConfig();
        assertFalse(c.username().isEmpty());
        assertFalse(c.passowrd().isEmpty());
        assertFalse(c.instance().isEmpty());
        assertFalse(c.zk().isEmpty());
        assertTrue(c.zk().contains(":"));
        assertTrue(c.timeout() > 0);
    }
}
