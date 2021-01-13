package Exercises.ex7.software1.bufferedIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedIOTester {
	public static final String RESOURCES_FOLDER = "src/Exercises/ex7/resources/out/";

	public static void main(String[] args) throws IOException{
		String outputFileName = RESOURCES_FOLDER + "rocky1_out.txt";
		String outString1 = "Now somewhere in the Black mining Hills of Dakota\nThere lived a young boy named Rocky Raccoon,\n"; 
		String outString2 = "And one day his woman";
		String outString3 = " ran off with another guy,\nHit young Rocky in the eye.";
		FileWriter fWriter = new FileWriter(new File(outputFileName));
		IBufferedWriter bW = new MyBufferedWriter(fWriter, 60);
		bW.write(outString1);
		bW.write(outString2);
		bW.write(outString3);
		bW.close();
		MyFileWriter ff = new MyFileWriter(new File(outputFileName));
		IBufferedWriter bf = new MyBufferedWriter(ff, 56);
		bf.write(outString1);
		System.out.println(ff.getWritesCount());
		bf.write(outString2);
		System.out.println(ff.getWritesCount());
		bf.write(outString3);
		System.out.println(ff.getWritesCount());
		bf.close();
		System.out.println(ff.getWritesCount());
		
		/***
		 * The output file this tester creates should be identical to rocky1_correct.txt
		 * 
		 */
	}
}
