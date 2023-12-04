package token;

public class Token {

	private int riga;
	private TokenType tipo;
	private String val;
	
	public Token(TokenType tipo, int riga, String val) {
		this(tipo, riga); //this che fa riferimento al costruttore di sotto
		this.val = val;
	}
	
	public Token(TokenType tipo, int riga) {
		this.tipo = tipo;
		this.riga = riga;
	}

    // Getters per i campi


	public int getRiga() {
		return riga;
	}

	public TokenType getTipo() {
		return tipo;
	}

	public String getVal() {
		return val;
	}

	public String toString() {

		if(this.tipo == TokenType.ID || this.tipo == TokenType.INT|| this.tipo == TokenType.FLOAT)
			return "<" + this.tipo + ", r:" + this.riga + ", " + this.val + ">";

		return "<" + this.tipo + ", r:" + this.riga +">";
	}

     

}
