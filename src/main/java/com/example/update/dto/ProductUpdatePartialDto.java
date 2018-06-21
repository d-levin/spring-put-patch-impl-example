package com.example.update.dto;

import java.util.HashMap;
import java.util.Map;

public class ProductUpdatePartialDto {

  private static final String KEY_NAME = "name";
  private static final String KEY_DESCRIPTION = "description";

  private final Map<String, Object> fields = new HashMap<>();

  public boolean containsName() {
    return this.fields.containsKey(KEY_NAME);
  }

  public void setName(final String name) {
    this.fields.put(KEY_NAME, name);
  }

  public String getName() {
    return (String) this.fields.get(KEY_NAME);
  }

  public boolean containsDescription() {
    return this.fields.containsKey(KEY_DESCRIPTION);
  }

  public void setDescription(final String description) {
    this.fields.put(KEY_DESCRIPTION, description);
  }

  public String getDescription() {
    return (String) this.fields.get(KEY_DESCRIPTION);
  }

}
