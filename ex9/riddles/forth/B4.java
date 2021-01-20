package Exercises.ex9.riddles.forth;


import java.util.Iterator;

public class B4 implements Iterator<String> {

    private int k;
    private int index;
    private final String[] strings;

    public B4(String[] strings, int k){
        this.strings = strings;
        this.k = k;
        this.index = -1;
    }

    @Override
    public boolean hasNext() {
        return k > 0;
    }

    @Override
    public String next() {
        index++;
        if (index == strings.length) {
            index =0;
            k--;
        }
        return strings[index];
    }

}
