package movida.dipasqualecolamonaco;

import java.io.File;

public class Main {
	
	static MovidaCore mc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mc = new MovidaCore();
		mc.loadFromFile(new File("/Users/alessiodipasquale/Projects/Movida/src/movida/dipasqualecolamonaco/test.txt"));
		//System.out.println(mc.movieData.length());
		mc.deleteMovieByTitle("scarface");
		//System.out.println(mc.movieData.length());
		mc.saveToFile(new File("/Users/alessiodipasquale/Projects/Movida/src/movida/dipasqualecolamonaco/output.txt"));

	}

}
