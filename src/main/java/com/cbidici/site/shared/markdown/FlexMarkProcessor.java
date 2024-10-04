package com.cbidici.site.shared.markdown;
import com.cbidici.site.shared.MarkdownProcessor;
import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.resizable.image.ResizableImageExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import java.util.Arrays;
import org.springframework.stereotype.Component;

@Component
public class FlexMarkProcessor implements MarkdownProcessor {
  private final Parser parser;
  private final HtmlRenderer renderer;

  private FlexMarkProcessor() {
    MutableDataSet options = new MutableDataSet();
    options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), ResizableImageExtension.create(),
        AttributesExtension.create()));
    parser = Parser.builder(options).build();
    renderer = HtmlRenderer.builder(options).build();
  }

  @Override
  public String getHtml(String markdown) {
    Node document = parser.parse(markdown);
    return renderer.render(document);
  }
}