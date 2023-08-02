package lk.oop.cw;

import java.io.Serializable;
import java.util.Objects;

public class SportsClub implements Serializable {
    private String clubName;
    private String clubLocation;

    //constructor.
    public SportsClub(String clubName, String clubLocation) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
    }


    //getters and setters.
    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String clubLocation) {
        this.clubLocation = clubLocation;
    }

    @Override
    public String toString() {
        return "SportsClub{" +
                "clubName='" + clubName + '\'' +
                ", clubLocation='" + clubLocation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportsClub)) return false;
        SportsClub that = (SportsClub) o;
        return Objects.equals(getClubName(), that.getClubName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClubName());
    }
}
