package com.cbidici.site.shared;

import com.github.slugify.Slugify;
import org.springframework.stereotype.Component;

@Component
public class SlugService {
  private final Slugify slugify;

  public SlugService() {
    this.slugify = Slugify.builder().build();
  }

  public String getSlug(String str) {
    return slugify.slugify(str);
  }
}
