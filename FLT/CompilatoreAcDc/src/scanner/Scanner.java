package scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import token.*;

public class Scanner {
	final char EOF = (char) -1; 
	private int riga;
	private PushbackReader buffer;
	private String log;

	// skpChars: insieme caratteri di skip (include EOF) e inizializzazione
	ArrayList<Character> skpChars;
	// letters: insieme lettere e inizializzazione
	ArrayList<Character> letters;
	// digits: cifre e inizializzazione
	ArrayList<Character> digits;

	// char_type_Map: mapping fra caratteri '+', '-', '*', '/', ';', '=', ';' e il
	HashMap<Character, TokenType> operatori;
	// TokenType corrispondente

	// keyWordsMap: mapping fra le stringhe "print", "float", "int" e il
	HashMap<String, TokenType> keyWords;
	// TokenType  corrispondente



	public Scanner(String fileName) throws FileNotFoundException {

		this.buffer = new PushbackReader(new FileReader(fileName));
		riga = 1;
		// inizializzare campi che non hanno inizializzazione
		skpChars = new ArrayList<>();
		skpChars.add(' ');
		skpChars.add('\n');
		skpChars.add('\t');
		skpChars.add('\r');
		skpChars.add(this.EOF);

		letters = new ArrayList<>(Arrays.asList(new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k','l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
																'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}));

		digits = new ArrayList<>(Arrays.asList(new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}));

		operatori = new HashMap<>();
		operatori.put('+', TokenType.PLUS);
		operatori.put('-', TokenType.MINUS);
		operatori.put('*', TokenType.TIMES);
		operatori.put('/', TokenType.DIV);
		operatori.put('=', TokenType.ASSIGN);
		operatori.put(';', TokenType.SEMI);

		keyWords = new HashMap<>();

		keyWords.put("print", TokenType.PRINT);
		keyWords.put("float", TokenType.TYFLOAT);
		keyWords.put("int", TokenType.TYINT);

	}

	public Token nextToken() throws LexicalException {

		// nextChar contiene il prossimo carattere dell'input (non consumato).
		char nextChar = peekChar(); //Catturate l'eccezione IOException e 
		       // ritornate una LexicalException che la contiene

		// Avanza nel buffer leggendo i carattere in skipChars
		// incrementando riga se leggi '\n'.
		// Se raggiungi la fine del file ritorna il Token EOF
		while(skpChars.contains(nextChar)){
			if(nextChar == '\n'){
				this.riga++;
			}
			if(nextChar == EOF){
				return new Token(TokenType.EOF, this.riga);
			}
			readChar();
			nextChar=peekChar();
		}

		// Se nextChar e' in letters
		// return scanId()
		// che legge tutte le lettere minuscole e ritorna un Token ID o
		// il Token associato Parola Chiave (per generare i Token per le
		// parole chiave usate l'HaskMap di corrispondenza
		if(letters.contains(nextChar)){
			return scanId();
		}


		// Se nextChar e' o in operators oppure 
		// ritorna il Token associato con l'operatore o il delimitatore

		// Se nextChar e' in numbers
		// return scanNumber()
		// che legge sia un intero che un float e ritorna il Token INUM o FNUM
		// i caratteri che leggete devono essere accumulati in una stringa
		// che verra' assegnata al campo valore del Token

		// Altrimenti il carattere NON E' UN CARATTERE LEGALE sollevate una
		// eccezione lessicale dicendo la riga e il carattere che la hanno
		// provocata. 

		return null;
	}

	// private Token scanNumber()

	// private Token scanId()

	private char readChar() throws LexicalException {
		try {
			return ((char) this.buffer.read());
		}catch (IOException io){
			throw new LexicalException("catturata eccezione di tipo IOException" + this.riga, io);
		}
		}

	private char peekChar() throws LexicalException {
		try {
			char c = (char) buffer.read();
			buffer.unread(c);
			return c;
		}catch (IOException io){
			throw new LexicalException("catturata eccezione di tipo IOException" + this.riga, io);
		}
	}

	private Token scanId() throws LexicalException {
		StringBuilder saveString = new StringBuilder("" + readChar());
		while(letters.contains(peekChar())){
			saveString.append(readChar());
		}
		if(digits.contains(peekChar())){
			throw new LexicalException("trovato numedo al posto di un ID in riga " + this.riga, null);
		}
		if(keyWords.containsKey(saveString.toString())){
			return new Token(this.keyWords.get(saveString.toString()), this.riga);
		}

		return new Token(TokenType.ID, this.riga, saveString.toString());
	}
}
