package com.example.haahooshop;

import java.util.ArrayList;

public class Specpojo {

  public String id;
  public String name;
  public ArrayList<String> values = new ArrayList<>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<String> getValues() {
    return values;
  }

  public void setValues(ArrayList<String> values) {
    this.values = values;
  }
}