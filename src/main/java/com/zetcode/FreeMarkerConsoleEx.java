package com.zetcode;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerConsoleEx {

  public static void main(String[] args) throws IOException,
          TemplateException {

    Configuration cfg = new Configuration(new Version("2.3.23"));

    cfg.setClassForTemplateLoading(FreeMarkerConsoleEx.class, "/");
    cfg.setDefaultEncoding("UTF-8");

    Template template = cfg.getTemplate("templates/test.ftl");

    Map<String, Object> templateData = new HashMap<>();
    templateData.put("msg", "Today is a beautiful day");

    try (StringWriter out = new StringWriter()) {

      template.process(templateData, out);
      System.out.println(out.getBuffer().toString());

      out.flush();
    }
  }
}