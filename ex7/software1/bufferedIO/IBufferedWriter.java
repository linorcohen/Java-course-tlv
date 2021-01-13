package Exercises.ex7.software1.bufferedIO;

import java.io.Closeable;
import java.io.IOException;

public interface IBufferedWriter extends Closeable{

	public void write(String str) throws IOException;

}

