package com.resat.project.command;

import com.resat.project.Cache;
import com.resat.project.exceptions.KeyAlreadyExistsException;

import java.util.List;

public class Create implements Command<String> {

    @Override
    public String execute(List<String> params) throws KeyAlreadyExistsException {
        String k = params.get(1);
        String v = params.get(2);
        Cache.put(k, v);
        return null;
    }
}
