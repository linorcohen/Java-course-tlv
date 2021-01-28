package Exercises.ex10.enumRiddles;

enum TLight {
    // Each instance provides its implementation to abstract method
    RED(30),
    AMBER(10),
    GREEN(30);


    private final int seconds;     // Private variable

    TLight(int seconds) {          // Constructor
        this.seconds = seconds;
    }

    int getSeconds() {             // Getter
        return seconds;
    }

    public Object next() {
        TLight[] val = TLight.values();
        int i = this.ordinal() - 1;
        if (i < 0){
            return val[val.length-1];
        }
        if (i>= val.length){
            return val[0];
        }
        return val[i];
    }

}

public class TLightTest {
    public static void main(String[] args) {
        for (TLight light : TLight.values()) {
            System.out.printf("%s: %d seconds, next is %s\n", light,
                    light.getSeconds(), light.next());
        }
    }
}