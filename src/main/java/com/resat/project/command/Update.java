package com.resat.project.command;

import com.resat.project.Cache;
import com.resat.project.exceptions.KeyNotFoundException;

import java.util.List;

public class Update implements Command<String> {

    @Override
    public String execute(List<String> params) throws KeyNotFoundException {
        String k = params.get(1);
        String v = params.get(2);
        Cache.update(k, v);
        return null;
    }
}
