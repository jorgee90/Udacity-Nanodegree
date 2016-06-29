package com.samsrutidash.reportcard;

/**
 * Created by samsrutidash on 6/29/2016.
 */
public class ReportCard {
    public String subjectName;
    public int subjectIcon;
    public String grade;

    public ReportCard(){
        super();
    }

    public ReportCard(int icon, String subjectName, String grade) {
        super();
        this.subjectIcon = icon;
        this.subjectName = subjectName  ;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "You Child got \n" + grade+ " Grade in "+ subjectName ;
    }
}
