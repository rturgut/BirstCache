package com.resat.project;

import com.resat.project.exceptions.KeyAlreadyExistsException;
import com.resat.project.exceptions.KeyNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    public static final Map<String, String> cache =  new HashMap<>();

    private Cache(){}

    public static void put(String k, String v) throws KeyAlreadyExistsException{
        if(cache.containsKey(k)){
            throw new KeyAlreadyExistsException();
        }
        cache.put(k, v);
    }

    public static void update(String k, String v) throws KeyNotFoundException {
        if (!cache.containsKey(k)){
            throw new KeyNotFoundException();
        }
        cache.put(k, v);
    }

    public static String get(String k) throws KeyNotFoundException {
        if (!cache.containsKey(k)){
            throw new KeyNotFoundException();
        }
        return cache.get(k);
    }

    public static String delete(String k) throws KeyNotFoundException {
        if (!cache.containsKey(k)){
            throw new KeyNotFoundException();
        }
        return cache.remove(k);
    }

    public static Map<String, String> getAll() {
        return cache;
    }

}
