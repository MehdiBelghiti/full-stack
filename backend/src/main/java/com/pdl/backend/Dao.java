package com.pdl.backend;

import java.util.Optional;
import java.util.List;

public interface Dao<T> {
  
  void create(final T t);

  Optional<T> retrieve(final long id);

  List<T> retrieveAll();

  void update(final T t, final String[] params);

  void delete(final T t);
}
