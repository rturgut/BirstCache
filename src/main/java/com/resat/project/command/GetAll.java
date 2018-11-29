package com.resat.project.command;

import com.resat.project.Cache;
import java.util.List;
import java.util.Map;

public class GetAll implements Command<Map> {

    @Override
    public Map<String,String> execute(List<String> params) {
        return Cache.getAll();
    }
}
