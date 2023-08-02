package lk.oop.cw;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsoleApplication extends Application {
    final static Scanner sc = new Scanner(System.in);
    static PremierLeagueManager leagueManager = new PremierLeagueManager();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //auto loading when program start
        try {
            leagueManager.loadFile("PremiereLeague.txt", "Match.txt");
        } catch (IOException | ClassNotFoundException e ) {
            System.out.println(""); //
        }

        try {
            int option;

            System.out.println("\nWelcome to Premiere League");
            System.out.println("****************************");

            while (true) {
                System.out.println("\nChoose an option.\n");

                System.out.println("Press 1 to Create a new Football Club");
                System.out.println("Press 2 to Delete an existing Club");
                System.out.println("Press 3 to Display Statistics of a Club");
                System.out.println("Press 4 to Display Premiere league table");
                System.out.println("Press 5 to Add Match Data");
                System.out.println("Press 6 to load gui");
                System.out.println("Press 7 to Quit");


                System.out.print("\nPrompt your option : \n");
                option = sc.nextInt();
                System.out.println();

                switch (option) {
                    case 1:
                        createClub();
                        break;

                    case 2:
                        deleteClub();
                        break;

                    case 3:
                        printStatistics();
                        break;

                    case 4:
                        premiereLeagueTable();
                        break;

                    case 5:
                        addMatch();
                        break;

                    case 6:
                        GUIApplication guiApplication = new GUIApplication();
                        Stage stage = guiApplication.createGUI(leagueManager, leagueManager.getFootballList(), leagueManager.getMatchArrayList());
                        stage.showAndWait();
                        break;

                    case 7:
                        //save when exit
                        try {
                            leagueManager.saveFile("PremiereLeague.txt", "Match.txt");
                        } catch (IOException e) {
                            System.out.println(""); //
                        }

                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid input"); //doesnt work ..only works numbers
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input"); //doesnt work ..only works numbers
        }
    }


    private static void createClub() {

        System.out.println("Enter Club Name: ");
        String clubName = sc.next();

        System.out.println("Enter Club Location: ");
        String clubLocation = sc.next();

        System.out.println("Enter Wins : ");
        int wins = sc.nextInt();

        System.out.println("Enter Draws : ");
        int draws = sc.nextInt();

        System.out.println("Enter Defeats : ");
        int defeats = sc.nextInt();

        System.out.println("Enter Goals scored : ");
        int goalsScored = sc.nextInt();

        System.out.println("Enter Goals received : ");
        int goalsReceived = sc.nextInt();

        System.out.println();

        //points and total matches counted here
        int clubPoints = (wins*3)+(draws*1);
        int matchesPlayed = wins+draws+defeats;

        //sport club object created with football club super class.
        FootballClub newClub = new FootballClub(clubName, clubLocation, wins, draws, defeats, goalsScored, goalsReceived, clubPoints, matchesPlayed);

        leagueManager.createClub(newClub);

        //small delay before loop ends
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void printStatistics() {
        System.out.println("Enter the club name to see statistics: ");
        String club = sc.next();
        leagueManager.printStatistics(club);
    }

    private static void deleteClub(){
        System.out.println("Enter the club name to delete: ");
        String club = sc.next();
        leagueManager.deleteClub(club);
    }

    private static void premiereLeagueTable(){ //have to add more
        System.out.println("Premiere league table\n");
        leagueManager.premiereLeagueTable();
    }


    public static void addMatch() { //New
        System.out.println("Enter match details\n");
        Scanner input = new Scanner(System.in);

        System.out.print("Enter date as yyyy/mm/dd: "); //try one stepping these 3
        String date = sc.next();

        System.out.print("Enter club one name : ");
        String firstClub  = input.next();

        System.out.print("Scored Goals : ");
        int club1GoalsScored  = input.nextInt();

        System.out.print("Enter club two name : ");
        String secondClub  = input.next();

        System.out.print("Scored Goals : ");
        int club2GoalsScored  = input.nextInt();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
            Date d = formatter.parse(date);

            if (club1GoalsScored>=0 && club2GoalsScored>=0) {
                leagueManager.addMatch(firstClub, club1GoalsScored, secondClub, club2GoalsScored, date);
            } else {
                System.out.println("Invalid Scores");
            }
        } catch (Exception e) {
            System.out.println("Invalid Date"); //
        }
    }
}
