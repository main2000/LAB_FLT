package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import token.Token;
import token.TokenType;

class TestToken {

	@Test
	void test() {
		Token token1 = new Token(TokenType.EOF, 1);
		Token token2 = new Token(TokenType.ID, 1, "splinter");
		Token token3 = new Token(TokenType.ASSIGN, 1);
		Token token4 = new Token(TokenType.INT, 1, "104");
		Token token5 = new Token(TokenType.FLOAT, 1, "11.09");
		Token token6 = new Token(TokenType.MINUS, 1);
		Token token7 = new Token(TokenType.OP_ASSIGN, 1);
		Token token8 = new Token(TokenType.PLUS, 1);
		Token token9 = new Token(TokenType.PRINT, 1);
		Token token10 = new Token(TokenType.SEMI, 1);
		Token token11 = new Token(TokenType.TYFLOAT, 1);
		Token token12 = new Token(TokenType.TYINT, 1);
		//int float e id hanno anche un valore, per esempio 104 o il valore opportunoin formato stringa
		assertEquals("<EOF, r:1>", token1.toString());
		assertEquals("<ID, r:1, splinter>", token2.toString());
		assertEquals("<ASSIGN, r:1>", token3.toString());
		assertEquals("<INT, r:1, 104>", token4.toString());
		assertEquals("<FLOAT, r:1, 11.09>", token5.toString());
		assertEquals("<MINUS, r:1>", token6.toString());
		assertEquals("<OP_ASSIGN, r:1>", token7.toString());
		assertEquals("<PLUS, r:1>", token8.toString());
		assertEquals("<PRINT, r:1>", token9.toString());
		assertEquals("<SEMI, r:1>", token10.toString());
		assertEquals("<TYFLOAT, r:1>", token11.toString()); //USATA PER LA KEYWORD float
		assertEquals("<TYINT, r:1>", token12.toString()); //USATA PER LA KEYWOARD int

	}

}
