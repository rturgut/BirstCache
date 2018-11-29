package com.resat.project.command;

import com.resat.project.Cache;
import com.resat.project.exceptions.KeyNotFoundException;

import java.util.List;

public class Delete implements Command<String> {

    @Override
    public String execute(List<String> params) throws KeyNotFoundException {
        String k = params.get(1);
        return Cache.delete(k);
    }
}
