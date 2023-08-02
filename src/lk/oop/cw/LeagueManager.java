package lk.oop.cw;

import java.io.IOException;
import java.util.Date;

public interface LeagueManager {

    void createClub(FootballClub club);

    void deleteClub(String club);

    void printStatistics(String club);

    void premiereLeagueTable();

    void addMatch(String firstClub, int club1GoalsScored, String secondClub, int club2GoalsScored, String date);

    void saveFile(String PremierLeague,String Match) throws IOException;

    void loadFile(String PremierLeague,String Match) throws IOException, ClassNotFoundException;
}
