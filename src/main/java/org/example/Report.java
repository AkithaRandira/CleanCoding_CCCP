package org.example;

public abstract class Report {
    protected abstract void getData();
    protected abstract  void createReport();


    //building the template method

    public final void reportGenerator(){
        getData();

        createReport();

    }
}
