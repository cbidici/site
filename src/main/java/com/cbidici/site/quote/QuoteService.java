package com.cbidici.site.quote;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

  private static final List<Quote> QUOTES = List.of(
      new Quote("May the source be with you.","Obi-Wan Kenobi"),
      new Quote("'It depends' is almost always the right answer in any big questions.", "Linus Torvalds"),
      new Quote("With great power comes great responsibility.", "Uncle Ben"),
      new Quote("It works on my machine.", "Anonymous"),
      new Quote("Before software can be reusable it first has to be usable.", "Ralph Johnson"),
      new Quote("If it doesn’t work, it doesn’t matter how fast it doesn’t work.", "Mich Ravera"),
      new Quote("There’s nothing more permanent than a temporary hack.", "Kyle Simpson"),
      new Quote("Architecture is the tension between coupling and cohesion.", "Neal Ford"),
      new Quote("If you think good architecture is expensive, try bad architecture.", "Brian Foote and Joseph Yoder"),
      new Quote("Weeks of programming can save you hours of planning.", "Anonymous"),
      new Quote("6 Hours Of Debugging Can Save You 5 Minutes Of Reading Documentation.", "Anonymous"),
      new Quote("Never spend 6 minutes doing something by hand when you can spend 6 hours failing to automate it.", "Zhuowei Zhang"),
      new Quote("It’s not a bug; it’s an undocumented feature.","Anonymous"),
      new Quote("Code is like humor. When you have to explain it, it’s bad.", "Cory House"),
      new Quote("Clean code always looks like it was written by someone who cares.", "Michael Feathers"),
      new Quote("Any fool can write code that a computer can understand. Good programmers write code that humans can understand.", "Martin Fowler"),
      new Quote("Experience is the name everyone gives to their mistakes.","Oscar Wilde"),
      new Quote("If debugging is the process of removing bugs, then programming must be the process of putting them in.","Edsger W. Dijkstra"),
      new Quote("There are two ways to write code: write code so simple there are obviously no bugs in it, or write code so complex that there are no obvious bugs in it.","Tony Hoare"),
      new Quote("Simplicity is the soul of efficiency.","Austin Freeman"),
      new Quote("Every time you write a comment, you should grimace and feel the failure of your ability of expression.","Uncle Bob"),
      new Quote("Any code of your own that you haven’t looked at for six or more months might as well have been written by someone else.", "Eagleson's Law"),
      new Quote("Truth can only be found in one place: the code.","Uncle Bob"),
      new Quote("It’s harder to read code than to write it.","Joel Spolsky"),
      new Quote("Talk is cheap. Show me the code.","Linus Torvalds"),
      new Quote("Legacy code is code without tests.","Michael Feathers"),
      new Quote("One man’s crappy software is another man’s full time job.","Jessica Gaston"),
      new Quote("Nothing makes a system more flexible than a suite of tests.","Uncle Bob"),
      new Quote("There is no better way to learn than to teach.", "Benjamin Whichcote")
  );

  public Quote getRandom() {
    return QUOTES.get((int) (Math.random() * QUOTES.size()));
  }
}
