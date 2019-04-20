/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftinginterpreters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import craftinginterpreters.Token;
import craftinginterpreters.Scanner;

/**
 *
 * @author Vidumini
 */
public class Lox {

    static boolean hadError = false;    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    private static void runFile(String path) throws IOException
    {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
    }

    private static void runPrompt() throws IOException
    {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        
        for(;;) {
            System.out.print("> ");
            run(reader.readLine());
        }
    }    
    
    private static void run(String source)
    {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();
        
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    static void error(int line, String message)
    {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) 
    {
        System.err.println(
         "[line " + line + "]" + where + ": " + message);
        hadError = true;
    }    
}
