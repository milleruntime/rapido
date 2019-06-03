package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A config object read once at startup and never again.
 */
public class RapidoConfig {
    private static final Logger log = LoggerFactory.getLogger(RapidoConfig.class);

    public static final String filePath = "config/rapido.properties";

    private final String username;
    private final String password;
    private final String instance;
    private final String zk;
    private final int timeout;

    public RapidoConfig() throws IOException {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            Properties prop = new Properties();
            prop.load(input);

            username = prop.getProperty("rapido.username");
            password = prop.getProperty("rapido.password");
            instance = prop.getProperty("rapido.instance");
            zk = prop.getProperty("rapido.zk");
            timeout = Integer.parseInt(prop.getProperty("rapido.timeout"));
            log.info("Read the following properties:");
            printConfig();
        }
    }

    public void printConfig() {
        log.info("rapido.username=" + username);
        log.info("rapido.instance=" + instance);
        log.info("rapido.zk=" + zk);
        log.info("rapido.timeout=" + timeout);
    }

    public String username() {
        return username;
    }

    public String passowrd() {
        return password;
    }

    public String instance() {
        return instance;
    }

    public String zk() {
        return zk;
    }

    public int timeout() {
        return timeout;
    }
}
