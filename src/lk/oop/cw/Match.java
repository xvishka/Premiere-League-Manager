package lk.oop.cw;


import java.io.Serializable;

public class Match implements Serializable{
    private String firstClub;
    private int club1GoalsScored;
    private String secondClub;
    private int club2GoalsScored;
    private String date;

    //constructors
    public Match(String firstClub, int club1GoalsScored, String secondClub, int club2GoalsScored, String date) {
        this.firstClub = firstClub;
        this.secondClub = secondClub;
        this.club1GoalsScored = club1GoalsScored;
        this.club2GoalsScored = club2GoalsScored;
        this.date = date;
    }

    public String getFirstClub() {
        return firstClub;
    }

    public void setFirstClub(String firstClub) {
        this.firstClub = firstClub;
    }

    public int getClub1GoalsScored() {
        return club1GoalsScored;
    }

    public void setClub1GoalsScored(int club1GoalsScored) {
        this.club1GoalsScored = club1GoalsScored;
    }

    public String getSecondClub() {
        return secondClub;
    }

    public void setSecondClub(String secondClub) {
        this.secondClub = secondClub;
    }

    public int getClub2GoalsScored() {
        return club2GoalsScored;
    }

    public void setClub2GoalsScored(int club2GoalsScored) {
        this.club2GoalsScored = club2GoalsScored;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Match{" +
                "firstClub='" + firstClub + '\'' +
                ", club1GoalsScored=" + club1GoalsScored +
                ", secondClub='" + secondClub + '\'' +
                ", club2GoalsScored=" + club2GoalsScored +
                ", date='" + date + '\'' +
                '}';
    }

}
