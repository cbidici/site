package com.cbidici.site;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.modulith.core.ApplicationModules;

@Profile("integration")
public class ModularityIT {

  ApplicationModules modules = ApplicationModules.of(Application.class);

  @Test
  void verifyModularity() {
    System.out.println(modules.toString());
    modules.verify();
  }

}
