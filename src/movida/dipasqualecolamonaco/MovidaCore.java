package movida.dipasqualecolamonaco;

import java.io.File;
import java.io.FileNotFoundException;

import movida.commons.MovidaFileException;

public class MovidaCore {
	public static void main(String[] args) throws MovidaFileException, FileNotFoundException {
		Database db = new Database();
		db.load(new File("/Users/alessiodipasquale/Projects/Movida/src/movida/dipasqualecolamonaco/test.txt"));

	}
}
