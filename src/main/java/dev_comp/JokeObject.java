package dev_comp;

import java.util.List;

/**
 * Created by a.kutakov on 22.09.2016.
 *
 */
public class JokeObject {
  private long count;
  private String next;
  private String previous;
  private List<Joke> results;

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public String getNext() {
    return next;
  }

  public void setNext(String next) {
    this.next = next;
  }

  public String getPrevious() {
    return previous;
  }

  public void setPrevious(String previous) {
    this.previous = previous;
  }

  public List<Joke> getResults() {
    return results;
  }

  public void setResults(List<Joke> results) {
    this.results = results;
  }
}
