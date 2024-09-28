package com.cbidici.site.service;

import groovy.lang.Tuple;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

  private static final List<Tuple<String>> QUOTES = List.of(
      Tuple.tuple("May the source be with you.","Obi-Wan Kenobi"),
      Tuple.tuple("'It depends' is almost always the right answer in any big questions.", "Linus Torvalds"),
      Tuple.tuple("With great power comes great responsibility.", "Uncle Ben"),
      Tuple.tuple("It works on my machine.", "Any developer"),
      Tuple.tuple("Before software can be reusable it first has to be usable.", "Ralph Johnson"),
      Tuple.tuple("If it doesn’t work, it doesn’t matter how fast it doesn’t work.", "Mich Ravera"),
      Tuple.tuple("There’s nothing more permanent than a temporary hack.", "Kyle Simpson"),
      Tuple.tuple("Architecture is the tension between coupling and cohesion.", "Neal Ford"),
      Tuple.tuple("If you think good architecture is expensive, try bad architecture.", "Brian Foote and Joseph Yoder"),
      Tuple.tuple("Weeks of programming can save you hours of planning.", "Anonymous"),
      Tuple.tuple("6 Hours Of Debugging Can Save You 5 Minutes Of Reading Documentation.", "Anonymous"),
      Tuple.tuple("Never spend 6 minutes doing something by hand when you can spend 6 hours failing to automate it.", "Zhuowei Zhang"),
      Tuple.tuple("It’s not a bug; it’s an undocumented feature.","Anonymous"),
      Tuple.tuple("Code is like humor. When you have to explain it, it’s bad.", "Cory House"),
      Tuple.tuple("Clean code always looks like it was written by someone who cares.", "Michael Feathers"),
      Tuple.tuple("Any fool can write code that a computer can understand. Good programmers write code that humans can understand.", "Martin Fowler"),
      Tuple.tuple("Experience is the name everyone gives to their mistakes.","Oscar Wilde"),
      Tuple.tuple("If debugging is the process of removing bugs, then programming must be the process of putting them in.","Edsger W. Dijkstra"),
      Tuple.tuple("There are two ways to write code: write code so simple there are obviously no bugs in it, or write code so complex that there are no obvious bugs in it.","Tony Hoare"),
      Tuple.tuple("Simplicity is the soul of efficiency.","Austin Freeman"),
      Tuple.tuple("Every time you write a comment, you should grimace and feel the failure of your ability of expression.","Uncle Bob"),
      Tuple.tuple("Any code of your own that you haven’t looked at for six or more months might as well have been written by someone else.", "Eagleson's Law"),
      Tuple.tuple("Truth can only be found in one place: the code.","Uncle Bob"),
      Tuple.tuple("It’s harder to read code than to write it.","Joel Spolsky"),
      Tuple.tuple("Talk is cheap. Show me the code.","Linus Torvalds"),
      Tuple.tuple("Legacy code is code without tests.","Michael Feathers"),
      Tuple.tuple("One man’s crappy software is another man’s full time job.","Jessica Gaston"),
      Tuple.tuple("Nothing makes a system more flexible than a suite of tests.","Uncle Bob"),
      Tuple.tuple("There is no better way to learn than to teach.", "Benjamin Whichcote")
  );

  public Tuple<String> getQuote() {
    return QUOTES.get((int) (Math.random() * QUOTES.size()));
  }
}
