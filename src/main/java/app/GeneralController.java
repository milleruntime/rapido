package app;

import org.apache.accumulo.core.client.Accumulo;
import org.apache.accumulo.core.client.AccumuloClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import config.RapidoConfig;

/**
 * General purpose controller.
 */
@RestController
public class GeneralController {
  private static Logger log = LoggerFactory.getLogger(QueryController.class);

  @RequestMapping("/test")
  public boolean testConnection() {
    RapidoConfig config = Rapido.getConfig();

    log.info("Testing connection to Accumulo...");
    try {
      AccumuloClient c = Accumulo.newClient().to(config.instance(), config.zk()).as(config.username(), config.passowrd())
              .zkTimeout(config.timeout()).build();
      if (c.instanceOperations().getTabletServers().size() < 1) {
        log.error("No tablet servers found.");
        return false;
      }
      c.close();
    } catch (Exception e) {
      log.error("There was a problem connecting to Accumulo", e);
      return false;
    }
    return true;
  }

}
