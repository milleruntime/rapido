package console;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ConsoleExample {

  public static void main(String[] args) throws IOException,
          TemplateException {

    Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

    cfg.setClassForTemplateLoading(ConsoleExample.class, "/");
    cfg.setDefaultEncoding("UTF-8");

    Template template = cfg.getTemplate("templates/console.ftl");

    Map<String, Object> data = new HashMap<>();
    data.put("msg", "Put a shrimp on the barbie");

    try (StringWriter out = new StringWriter()) {
      template.process(data, out);
      System.out.println(out.getBuffer().toString());
      out.flush();
    }
  }
}