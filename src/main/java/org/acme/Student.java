package org.acme;
import javax.mail.Message;
public class Student {

  private String name;
  private String id;
  private int year;
  private String rating;

  public Student() {

  }

  public Student(String name, String id, int year, String rating) {
    this.name = name;
    this.id = id;
    this.year = year;
    this.rating = rating;
  }

  public String tostring() {
    String str_year = Integer.toString(this.year);
    return id + "--" + name + "--" + str_year + "--" + rating;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }
}

