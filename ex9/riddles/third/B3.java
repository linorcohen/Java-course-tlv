package Exercises.ex9.riddles.third;

public class B3 extends A3 {


    public B3(String s){
        super(s);

    }

    public String getMessage(){
        return s;
    }

    @Override
    public void foo(String s) throws Exception {
        if (s.equals(this.s)) {
            throw new B3(s);
        }
    }
}