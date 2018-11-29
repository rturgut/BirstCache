package com.resat.project.exceptions;

public class CommandNotFoundException extends  Exception {
    public CommandNotFoundException(){
        super("Invalid Command");
    }
}
