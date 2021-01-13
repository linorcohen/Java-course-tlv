package Exercises.ex7.software1.bufferedIO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class MyBufferedWriter implements IBufferedWriter {

    private final FileWriter fWriter;
    private final int bufferSize;
    private final List<Character> buffer;


    public MyBufferedWriter(FileWriter fWriter, int bufferSize) {
        this.fWriter = fWriter;
        this.bufferSize = bufferSize;
        this.buffer = new ArrayList<>();
    }


    private void initialBuffer(String str) {
        char[] chars = str.toCharArray();
        for (char i : chars) {
            buffer.add(i);
        }
    }



    @Override
    public void write(String str) throws IOException {
        initialBuffer(str);
        for (int index = 0; index < bufferSize; index++) {
            if (buffer.size() != 0) {
                fWriter.write(buffer.get(0));
                buffer.remove(0);
            }
        }
    }

    @Override
    public void close() throws IOException {
        while (buffer.size() != 0) {
            fWriter.write(buffer.get(0));
            buffer.remove(0);
        }
        fWriter.close();
    }

}