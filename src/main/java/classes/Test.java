package classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Test {
    @JsonProperty("emp_id")
    private long empId;
    private String name;
    private String employeeName;


    public Test(long empId, String name, String employee) {
        this.empId = empId;
        this.name = name;
        this.employeeName = employee;
    }

    public Test() {

    }

}
