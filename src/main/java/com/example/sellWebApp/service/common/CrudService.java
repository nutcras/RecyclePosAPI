package com.example.sellWebApp.service.common;

import java.io.IOException;

public interface CrudService<T, K> {
    public T getById(K id) throws IOException;

    public T create(T model);

    public T update(T model);

    public void deleteById(K id);
}
