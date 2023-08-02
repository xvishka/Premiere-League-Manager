package lk.oop.cw;


import java.io.Serializable;

public class FootballClub  extends SportsClub implements Comparable<FootballClub>, Serializable {

    private int wins;
    private int draws;
    private int defeats;
    private int goalsReceived;
    private int goalsScored;
    private int clubPoints;
    private int matchesPlayed;

    //constructor.
    public FootballClub(String clubName, String clubLocation, int wins, int draws, int defeats, int goalsScored, int goalsReceived, int clubPoints, int matchesPlayed) {
        super(clubName, clubLocation);
        this.wins = wins;
        this.draws = draws;
        this.defeats = defeats;
        this.goalsReceived = goalsReceived;
        this.goalsScored = goalsScored;
        this.clubPoints = clubPoints;
        this.matchesPlayed = matchesPlayed;
    }


    //getters and setters.
    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getClubPoints() {
        return clubPoints;
    }

    public void setClubPoints(int clubPoints) {
        this.clubPoints = clubPoints;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    @Override  ////added
    public int compareTo(FootballClub club) {
        int club1GoalDiff = goalsScored-goalsReceived;
        int club2GoalDiff = club.goalsScored-club.goalsReceived;

        if(clubPoints==club.clubPoints) {
            if (club1GoalDiff < club2GoalDiff) {
                return 1;
            } else {
                return -1;
            }
        }
        else if(clubPoints<club.clubPoints) {
            return 1;
        }
        else {
            return -1;
        }
    }
}
