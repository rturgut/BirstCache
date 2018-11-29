package com.resat.project;

import com.resat.project.exceptions.CommandNotFoundException;
import com.resat.project.exceptions.InvalidCommandParametersException;
import com.resat.project.exceptions.KeyAlreadyExistsException;
import com.resat.project.exceptions.KeyNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class ControllerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void validateCommandProcessing(){
        Controller c = new Controller();
        try {
            List<String> c1 = c.validateAndParseInput("create foo=bar");
            assertTrue("Create results do not match", c1.size() == 3);

            List<String> g1 = c.validateAndParseInput("get foo");
            assertTrue("Get results do not match", g1.size() == 2);

            List<String> d1 = c.validateAndParseInput("getall");
            assertTrue("Get results do not match", d1.size() == 1);


        } catch (CommandNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidCommandParametersException e) {
            e.printStackTrace();
        }
    }

    @Test(expected=CommandNotFoundException.class)
    public void testInvalidCommand() throws InvalidCommandParametersException, CommandNotFoundException {
        Controller c = new Controller();
        List<String> c1 = c.validateAndParseInput("hi foo=bar");
    }

    @Test(expected=CommandNotFoundException.class)
    public void testInvalidCreateCommand() throws InvalidCommandParametersException, CommandNotFoundException {
        Controller c = new Controller();
        List<String> c1 = c.validateAndParseInput("creates foo=bar");
    }


    @Test
    public void testOutput(){
        Controller c = new Controller();
        try{
            List<String> c1 = c.validateAndParseInput("create foo=bar");
            List<String> g1 = c.validateAndParseInput("get foo");
            c.processResult(c.processInput(g1));
            assertEquals("bar", outContent.toString());
        } catch (CommandNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidCommandParametersException e) {
            e.printStackTrace();
        } catch (KeyAlreadyExistsException e) {
            e.printStackTrace();
        } catch (KeyNotFoundException e) {
            e.printStackTrace();
        }


    }

}
