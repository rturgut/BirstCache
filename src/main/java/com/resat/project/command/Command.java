package com.resat.project.command;

import com.resat.project.exceptions.KeyAlreadyExistsException;
import com.resat.project.exceptions.KeyNotFoundException;

import java.util.List;


public interface Command<T> {

    T execute(List<String> params) throws KeyNotFoundException, KeyAlreadyExistsException;

}
