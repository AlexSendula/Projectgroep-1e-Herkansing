package Rewards.Jobs;

public abstract class Job {
    private String name;
    private String knowledge;

    protected Job(String name){
        this.name = name;
    }

    public void addKnowledge(String knowledge){
        this.knowledge = knowledge;
    }

    public String getName() {
        return name;
    }

    public String getKnowledge() {
        return knowledge;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
