package com.resat.project;

import com.resat.project.command.*;
import com.resat.project.exceptions.CommandNotFoundException;
import com.resat.project.exceptions.InvalidCommandParametersException;
import com.resat.project.exceptions.KeyAlreadyExistsException;
import com.resat.project.exceptions.KeyNotFoundException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {

    private final Map<String, Command> commandMap =  new HashMap<>();

    public Controller() {
        initializeCommands();
    }


    private void initializeCommands(){
        commandMap.put("CREATE", new Create());
        commandMap.put("UPDATE", new Update());
        commandMap.put("GET", new Get());
        commandMap.put("DELETE", new Delete());
        commandMap.put("GETALL", new GetAll());
    }


    public void executeCommand(String s) {
        try {
            List<String> input = validateAndParseInput(s);
            processResult(processInput(input));
        } catch (KeyNotFoundException | CommandNotFoundException |
                            InvalidCommandParametersException | KeyAlreadyExistsException ex) {
            processError(ex);
        }
    }


    public Object processInput(List<String> input) throws KeyNotFoundException, KeyAlreadyExistsException {
        return commandMap.get(input.get(0).toUpperCase()).execute(input);
    }


    /**
     * Parses input string, validates and returns command & params (if any) in a list
     * @param s
     * @return
     * @throws CommandNotFoundException
     * @throws InvalidCommandParametersException
     */
    public List<String> validateAndParseInput(String s)
                    throws CommandNotFoundException, InvalidCommandParametersException {
        //extract command & validate
        String[] parsedCommands = s.trim().split("\\s+");
        String command = parsedCommands[0];
        if (command == null || command.isEmpty() || !commandMap.containsKey(command.toUpperCase())){
            throw new CommandNotFoundException();
        }
        //pList holds command & all of it's parameters
        List<String> pList = new ArrayList<>();
        pList.add(command);

        String[] p = Arrays.copyOfRange(parsedCommands, 1, parsedCommands.length );
        Stream<String> str = Arrays.stream(p);
        String params = str.filter(c -> !c.isEmpty()).collect(Collectors.joining(""));
        //validate command params
        if(command.equalsIgnoreCase("CREATE") || command.equalsIgnoreCase("UPDATE")) {
            StringTokenizer st = new StringTokenizer(params.trim(), "=");
            if (st.countTokens() != 2){
                throw new InvalidCommandParametersException();
            }
            while(st.hasMoreTokens()){
                pList.add(st.nextToken());
            }
        }

        if(command.equalsIgnoreCase("GET") || command.equalsIgnoreCase("DELETE")) {
            if(p.length > 1){
                throw new InvalidCommandParametersException();
            }
            pList.add(params);
        }

        return pList;
    }

    /**
     * Displays the results of executed command to std.out
     * @param result
     */
    public void processResult(Object result){
        if(result != null){
            if(result instanceof String) {
                System.out.println(result);
            } else if(result instanceof Map) {
                Map<String,String> cache = (Map<String,String>)result;
                for (Map.Entry<String,String> entry : cache.entrySet())
                    System.out.println(entry.getKey() + " -> " + entry.getValue());
            }

        }
    }

    /**
     * Displays a clean error msg to std.out
     * @param e
     */
    public void processError(Exception e) {
        System.out.println(e.getMessage());
    }

}

