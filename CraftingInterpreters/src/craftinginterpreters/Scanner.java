/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftinginterpreters;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vidumini
 */
public class Scanner
{
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    public Scanner(String source) {
        this.source = source;
    }

    List<Token> scanTokens()
    {
        while(!isAtEnd()) {
            start = current;
            scanToken();
        }
        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private boolean isAtEnd()
    {
        return current >= source.length();
    }

    private void scanToken()
    {
        char c = advance();
        switch (c) {
            case '(': addToken(TokenType.LEFT_PAREN);
            case ')': addToken(TokenType.RIGHT_PAREN);
            case '{': addToken(TokenType.LEFT_BRACE);
            case '}': addToken(TokenType.RIGHT_BRACE);
            case ',': addToken(TokenType.COMMA);
            case '.': addToken(TokenType.DOT);
            case '-': addToken(TokenType.MINUS);
            case '+': addToken(TokenType.PLUS);
            case ';': addToken(TokenType.SEMICOLON);
            case '*': addToken(TokenType.STAR);
        }
    }

    private char advance()
    {
        current++;
        return source.charAt(current - 1);
    }

    private void addToken(TokenType type)
    {
        addToken(type, null);
    }    
}
