package org.acme;

public class Tag {

  public String name;

  public Tag(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Tag{" +
            "name='" + name + '\'' +
            '}';
  }
}
