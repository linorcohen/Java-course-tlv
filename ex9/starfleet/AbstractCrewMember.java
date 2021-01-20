package Exercises.ex9.starfleet;

import java.util.Objects;

public abstract class AbstractCrewMember implements CrewMember{

    private String name;
    private int yearsInService;
    private int age;

    public AbstractCrewMember(String name, int age, int yearsInService ){
        this.name = name;
        this.age = age;
        this.yearsInService = yearsInService;

    }

    public int getAge(){
        return age;
    }

    public int getYearsInService(){
        return yearsInService;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "Name="+getName()+"\n\t" +
                "Age="+getAge()+"\n\t" +
                "YearsInService="+getYearsInService();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCrewMember that = (AbstractCrewMember) o;
        return yearsInService == that.yearsInService && age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearsInService, age);
    }
}
