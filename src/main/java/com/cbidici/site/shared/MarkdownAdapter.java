package com.cbidici.site.shared;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class MarkdownAdapter {
  private final Parser parser = Parser.builder().build();
  private final HtmlRenderer renderer = HtmlRenderer.builder().build();

  public String convertToHtml(String markdown) {
    return renderer.render(parser.parse(markdown));
  }
}
