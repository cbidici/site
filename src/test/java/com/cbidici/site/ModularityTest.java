package com.cbidici.site;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTest {

  ApplicationModules modules = ApplicationModules.of(SiteApplication.class);

  @Test
  void verifyModularity() {
    System.out.println(modules.toString());
    modules.verify();
  }

}
