package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import scanner.LexicalException;
import scanner.Scanner;
import token.Token;
import token.TokenType;

import java.io.FileNotFoundException;

class TestScanner {
    private static String path = "src/test/data/testScanner/";
    @Test
    void testEOF() throws FileNotFoundException, LexicalException {

        Scanner scanner = new Scanner(path + "testEOF.txt");
        Token t = new Token(TokenType.EOF, 4);
        assertEquals(t.toString(), scanner.nextToken().toString());
    }

    @Test
    void testId() throws LexicalException, FileNotFoundException {
        Scanner scanner = new Scanner(path + "testId.txt");
        String t1 = new Token(TokenType.ID, 1, "jskjdsfhkjdshkf").toString();
        assertEquals(t1, scanner.nextToken().toString());

        String t2 = new Token(TokenType.ID, 2, "printl").toString();
        assertEquals(t2, scanner.nextToken().toString());

        String t3 = new Token(TokenType.ID, 4, "ffloat").toString();
        assertEquals(t3, scanner.nextToken().toString());

        String t4 = new Token(TokenType.ID, 6, "hhhjj").toString();
        assertEquals(t4, scanner.nextToken().toString());

        String t5 = new Token(TokenType.EOF, 7).toString();
        assertEquals(t5, scanner.nextToken().toString());

    }


}

