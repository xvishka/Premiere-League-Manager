package lk.oop.cw;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class PremierLeagueManager implements LeagueManager {
    private final ArrayList<FootballClub> premierLeague = new ArrayList<>();
    private final ArrayList<Match> leagueMatch = new ArrayList<>();

    final static public int maxTeams = 20; //global static var to limit clubs.
    private int freeSlots = maxTeams;

    public ArrayList<FootballClub> getFootballList() {
        return premierLeague;
    }

    public ArrayList<Match> getMatchArrayList() {
        return leagueMatch;
    }

    @Override
    public void createClub(FootballClub newClub) { //DONE!!

        boolean already = false;

        for (FootballClub footballClub : premierLeague) {
            if (footballClub.equals(newClub)){
                already = true;
                break;
            }
        }

        if (already) {
            System.out.println("This club is already in the premier league!");
        }else if (freeSlots == 0) {
            System.out.println("------- Premiere league is full -------");
        } else {
            premierLeague.add(newClub);
            System.out.println("------- '"+ newClub.getClubName() + "' - added to premiere league successfully -------");
            freeSlots = freeSlots - 1;
            System.out.println(freeSlots > 0 ? ("------- Free slots remaining : " + freeSlots) : "------- LEAGUE COMPLETED !");
        }
    }


    @Override
    public void printStatistics(String newClub) { //DONE!!

        FootballClub club = null;

        for (FootballClub newClubVar : premierLeague) {
            if (newClubVar.getClubName().equals(newClub)) {
                club = newClubVar;
                break;
            }
        }

        if(club!=null){
            System.out.println("------- Statistics -------");
            System.out.println("Club name      : " + club.getClubName());
            System.out.println("Matches played : " + club.getMatchesPlayed());
            System.out.println("Wins           : " + club.getWins());
            System.out.println("Draws          : " + club.getDraws());
            System.out.println("Lost           : " + club.getDefeats());
            System.out.println("Goals received : " + club.getGoalsReceived());
            System.out.println("Goals scored   : " + club.getGoalsScored());
            System.out.println("Points         : " + club.getClubPoints());
        }else{
            System.out.println("Please enter a valid Club !");
        }
    }

    @Override
    public void deleteClub(String clubName) {  //almost done, delete kraddi mulma eka mulin delete kre nattan delete krnna denne na

        boolean already = false;

        for (FootballClub club : premierLeague) {
            if (club.getClubName().equals(clubName)) {
                already = true;
                premierLeague.remove(club);

                System.out.println("Club name      : " + club.getClubName());
                System.out.println("Matches played : " + club.getMatchesPlayed());
                System.out.println("Wins           : " + club.getWins());
                System.out.println("Draws          : " + club.getDraws());
                System.out.println("Lost           : " + club.getDefeats());
                System.out.println("Goals received : " + club.getGoalsReceived());
                System.out.println("Goals scored   : " + club.getGoalsScored());
                System.out.println("Points         : " + club.getClubPoints());

                System.out.println("\nClub '" + club.getClubName() + "' deleted from the premiere league.");
                freeSlots = freeSlots + 1;
                break;
            }
        }

        if (!already) {
            System.out.println("club not found!");
        }

    }

    @Override
    public void premiereLeagueTable() { //almost done in console, add goal difference
        Collections.sort(premierLeague);

        int position = 1;
        for (FootballClub footballClub : premierLeague) {
            System.out.println("Position " + position + " Name : " + footballClub.getClubName() + " Points : " + footballClub.getClubPoints() + " Goals Score : " + footballClub.getGoalsScored() + " Goals Received : " + footballClub.getGoalsReceived());
            position++;
        }

    }


    @Override
    public void addMatch(String firstClub, int club1GoalsScored, String secondClub, int club2GoalsScored, String date) {
        if (firstClub.equals(secondClub)) {
            System.out.println("Cannat same"); //
        } else {
            FootballClub footballClub1 = null;
            FootballClub footballClub2 = null;
            boolean club1 = false;
            boolean club2 = false;

            for (FootballClub footballClub : premierLeague) {
                if (footballClub.getClubName().equals(firstClub)) {
                    club1 = true;
                    footballClub1 = footballClub;
                }
            }

            for (FootballClub footballClub : premierLeague) {
                if (footballClub.getClubName().equals(secondClub)) {
                    club2 = true;
                    footballClub2 = footballClub;
                }
            }

            if (club1 && club2) {

                if (club1GoalsScored > club2GoalsScored) {

                    footballClub1.setWins(footballClub1.getWins() + 1);
                    footballClub1.setClubPoints(footballClub1.getClubPoints() + 3);
                    footballClub1.setGoalsScored(footballClub1.getGoalsScored() + club1GoalsScored);
                    footballClub1.setGoalsReceived(footballClub1.getGoalsReceived() + club2GoalsScored);

                    footballClub2.setDefeats(footballClub2.getDefeats() + 1);
                    footballClub2.setGoalsScored(footballClub2.getGoalsScored() + club2GoalsScored);
                    footballClub2.setGoalsReceived(footballClub2.getGoalsReceived() + club1GoalsScored);

                } else if (club1GoalsScored < club2GoalsScored) {

                    footballClub2.setWins(footballClub2.getWins() + 1);
                    footballClub2.setClubPoints(footballClub2.getClubPoints() + 3);
                    footballClub2.setGoalsScored(footballClub2.getGoalsScored() + club2GoalsScored);
                    footballClub2.setGoalsReceived(footballClub2.getGoalsReceived() + club1GoalsScored);

                    footballClub1.setDefeats(footballClub1.getDefeats() + 1);
                    footballClub1.setGoalsScored(footballClub1.getGoalsScored() + club1GoalsScored);
                    footballClub1.setGoalsReceived(footballClub1.getGoalsReceived() + club2GoalsScored);

                } else {

                    footballClub1.setDraws(footballClub1.getDraws() + 1);
                    footballClub1.setClubPoints(footballClub1.getClubPoints() + 1);
                    footballClub1.setGoalsScored(footballClub1.getGoalsScored() + club1GoalsScored);
                    footballClub1.setGoalsReceived(footballClub1.getGoalsReceived() + club2GoalsScored);

                    footballClub2.setDraws(footballClub2.getDraws() + 1);
                    footballClub2.setClubPoints(footballClub2.getClubPoints() + 1);
                    footballClub2.setGoalsScored(footballClub2.getGoalsScored() + club2GoalsScored);
                    footballClub2.setGoalsReceived(footballClub2.getGoalsReceived() + club1GoalsScored);

                }

                footballClub1.setMatchesPlayed(footballClub1.getMatchesPlayed() + 1);
                footballClub2.setMatchesPlayed(footballClub2.getMatchesPlayed() + 1);

                Match match = new Match(firstClub, club1GoalsScored, secondClub, club2GoalsScored, date);

                leagueMatch.add(match); //add step to merge match into premiere league
                System.out.println("added successfully");

            } else {
                System.out.println("club not found!");
            }
         }
    }

    @Override
    public void loadFile(String PremierLeague,String Match) throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream=new FileInputStream(PremierLeague);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);

        for (;;){

            try {
                FootballClub footballClubs=(FootballClub)objectInputStream.readObject();
                premierLeague.add(footballClubs);
            } catch (EOFException e){
                break;
            }

        }

        objectInputStream.close();
        fileInputStream.close();
        System.out.println("Football clubs registered have been loaded successfully");


        //load matches
        FileInputStream fileInputStream1=new FileInputStream(Match);
        ObjectInputStream objectInputStream1=new ObjectInputStream(fileInputStream1);

        for (;;){

            try {
                Match match=(Match) objectInputStream1.readObject();
                leagueMatch.add(match);
            } catch (EOFException e){
                break;
            }

        }

        objectInputStream1.close();
        fileInputStream1.close();
        System.out.println("Matches have been loaded successfully");

//        for (Match match : leagueMatch) {
//            System.out.println(match);
//        }

    }
//
    @Override
    public void saveFile(String PremierLeague,String Match) throws IOException  {

        FileOutputStream fileOutputStream = new FileOutputStream(PremierLeague);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        //write objects to the PremierLeague.txt
        for(FootballClub footballClub:premierLeague){
            objectOutputStream.writeObject(footballClub);
        }

        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println("Football clubs registered have been saved successfully");

        FileOutputStream fileOutputStream1 = new FileOutputStream(Match);
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);

        //write objects to the PremierLeague.txt
        for(Match match:leagueMatch){
            objectOutputStream1.writeObject(match);
        }

        objectOutputStream1.close();
        fileOutputStream1.close();
        System.out.println("Matches have been saved successfully");

    }


}