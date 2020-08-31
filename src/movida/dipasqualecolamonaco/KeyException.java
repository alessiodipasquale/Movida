package movida.dipasqualecolamonaco;


public class KeyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Chiave non trovata";
	}
}
