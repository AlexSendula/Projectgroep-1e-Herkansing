package Rewards.Jobs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Job {
    @JsonProperty("job")
    private String name;
    @JsonProperty("knowledge")
    private String knowledge;

    public Job() {

    }

    public void setName(String name) {
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
