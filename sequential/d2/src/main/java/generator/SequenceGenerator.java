package generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

public class SequenceGenerator {

	public static final char[] SIGMA = {'A', 'C', 'G', 'T'};
	
	public static int MIN_LENGTH_SEQ = 50;
	public static int MAX_LENGTH_SEQ = 200;
	
	public static Random RNG = new Random();
	
	
	/**
	 * Genera una sequenza di lunghezza length sul file out
	 * @param length
	 * @param out
	 */
	public static void sequence(int length, PrintStream out){
		
		for( int i = 0; i < length; i++ ){
			int nextChoice = RNG.nextInt(SIGMA.length);
			char nextChar = SIGMA[nextChoice];		
			out.print( nextChar );
		}	
	}
	
	/**
	 * Genera un dataset composto da num_seq file differenti
	 * I file rappresentano sequenza avente lunghezza casuale comeprese tra [MIN_LENGHT_SEQ; MAX_LENGTH_SEQ]
	 * @param num_seq
	 * @throws FileNotFoundException
	 */
	public static void dataset(String namedataset, String out_dir, int num_seq) throws FileNotFoundException{
		
		for( int i = 0; i < num_seq; i++ ){		
			PrintStream ps = new PrintStream( new File(out_dir + "seq_" + i + ".fasta") );
			ps.printf("> %s_SEQ_%d\n", namedataset, i);
			int nextSeqLenght = MIN_LENGTH_SEQ + RNG.nextInt(MAX_LENGTH_SEQ - MIN_LENGTH_SEQ);				
			sequence(nextSeqLenght, ps);
			ps.close();		
		}
	}
	
	/**
	 * Genera un dataset composto da num_seq su un unico file
	 * I file rappresentano sequenza avente lunghezza casuale comeprese tra [MIN_LENGHT_SEQ; MAX_LENGTH_SEQ]
	 * @param num_seq
	 * @throws FileNotFoundException
	 */
	public static void datasetUnique(String namedataset, String out_dir, int num_seq) throws FileNotFoundException{
		
		PrintStream ps = new PrintStream( new File(out_dir + "dataset.fasta") );
		
		for( int i = 0; i < num_seq; i++ ){
			ps.printf(">gnl|%s|%d %s_SEQ_%d\n", namedataset, i, namedataset, i);
			int nextSeqLenght = MIN_LENGTH_SEQ + RNG.nextInt(MAX_LENGTH_SEQ - MIN_LENGTH_SEQ);
			sequence(nextSeqLenght, ps);
			ps.println("\n");
		}
		ps.close();
	}
	
	
	
}
