package Rewards;

public class Job {
    private String name;
    public Job(){
        this.name = "job";
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
