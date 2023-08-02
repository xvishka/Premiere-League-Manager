package lk.oop.cw;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class GUIApplication {
    private PremierLeagueManager leagueManager = new PremierLeagueManager();
    private ArrayList<FootballClub> premierLeague;
    private ArrayList<Match> leagueMatch;
    Random random = new Random();

    public Stage createGUI(PremierLeagueManager leagueManager, ArrayList<FootballClub> premierLeague, ArrayList<Match> leagueMatch) {
        this.leagueManager = leagueManager;
        this.premierLeague = premierLeague;
        this.leagueMatch = leagueMatch;

        Stage stage = new Stage();
        BorderPane borderPane = new BorderPane();

        Label label1 = new Label("View Table By");
        Button button1 = new Button("Points");
        Button button2 = new Button("Goals Score");
        Button button3 = new Button("Wins");
        Button button4 = new Button("Date");

        Button button5 = new Button("Add Random Match");

        Label label2 = new Label("Date");
        TextField textField1 = new TextField();
        Button button6 = new Button("Search");

        HBox hBox = new HBox();
        hBox.getChildren().addAll(label1, button1, button2, button3, button4);
        hBox.setSpacing(10);

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(label2, textField1, button6);
        hBox1.setSpacing(10);

        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(button5, hBox1);
        hBox2.setSpacing(100);

        //styles
        button1.setStyle("-fx-pref-width: 120; -fx-pref-height: 30");
        button2.setStyle("-fx-pref-width: 120; -fx-pref-height: 30");
        button3.setStyle("-fx-pref-width: 120; -fx-pref-height: 30");
        button4.setStyle("-fx-pref-width: 120; -fx-pref-height: 30");

        borderPane.setCenter(viewAccordingToPoints()); //by default display table according to points

        //button clicks
        button1.setOnAction(event -> {
             borderPane.setCenter(null);
             borderPane.setCenter(viewAccordingToPoints());
        });

        button2.setOnAction(event -> {
            borderPane.setCenter(null);
            borderPane.setCenter(viewAccordingToScore());
        });

        button3.setOnAction(event -> {
            borderPane.setCenter(null);
            borderPane.setCenter(viewAccordingToWins());
        });

        button4.setOnAction(event -> {
            borderPane.setCenter(null);
            try {
                borderPane.setCenter(viewAccordingToDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        button5.setOnAction(event -> {
            if (premierLeague.size() >= 2) {
                while (true) {
                    //for select football club
                    int index1 = random.nextInt(premierLeague.size());
                    int index2 = random.nextInt(premierLeague.size());

                    //assume that maximum goal count is 10
                    int score1 = random.nextInt(10);
                    int score2 = random.nextInt(10);

                    Date d = new Date();
                    String date = new SimpleDateFormat("yyyy/mm/dd").format(d);
                    System.out.println(date);

                    if (index1 == index2) {
                        continue;
                    } else {
                        leagueManager.addMatch(premierLeague.get(index1).getClubName(), score1, premierLeague.get(index2).getClubName(), score2, date);
                        borderPane.setCenter(null);
                        borderPane.setCenter(viewRandomMatch());
                        break;
                    }
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Less club count to play match", ButtonType.OK);
                alert.showAndWait();
            }
        });

        button6.setOnAction(event -> {
            String date = textField1.getText();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
            try {
                Date d = formatter.parse(date);
                borderPane.setCenter(viewOnSpecificDate(date));
            } catch (ParseException e) {
                textField1.clear();
                Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Date", ButtonType.OK);
                alert.showAndWait();
            }
        });

        borderPane.setTop(hBox2);
        borderPane.setBottom(hBox);

        stage.setScene(new Scene(borderPane, 1000, 700));

        return stage;
    }

    private TableView viewRandomMatch() {
        TableView tableView = new TableView();

        TableColumn<FootballClub, String> column1 = new TableColumn<>("Club 1 Name");
        TableColumn<FootballClub, String> column2 = new TableColumn<>("Club 1 Goals Score");
        TableColumn<FootballClub, Integer> column3 = new TableColumn<>("Club 2 Name");
        TableColumn<FootballClub, Integer> column4 = new TableColumn<>("Club 2 Goals Score");
        TableColumn<FootballClub, Integer> column5 = new TableColumn<>("Date");

        column1.setCellValueFactory(new PropertyValueFactory<>("firstClub"));
        column2.setCellValueFactory(new PropertyValueFactory<>("club1GoalsScored"));
        column3.setCellValueFactory(new PropertyValueFactory<>("secondClub"));
        column4.setCellValueFactory(new PropertyValueFactory<>("club2GoalsScored"));
        column5.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableView.getColumns().addAll(column1, column2, column3, column4, column5);

        Match match = leagueMatch.get(leagueMatch.size()-1);

        tableView.getItems().add(new Match(match.getFirstClub(), match.getClub1GoalsScored(), match.getSecondClub(), match.getClub2GoalsScored(), match.getDate()));

        return tableView;
    }

    private TableView viewOnSpecificDate(String date) {

        TableView tableView = new TableView();

        TableColumn<FootballClub, String> column1 = new TableColumn<>("Club 1 Name");
        TableColumn<FootballClub, String> column2 = new TableColumn<>("Club 1 Goals Score");
        TableColumn<FootballClub, Integer> column3 = new TableColumn<>("Club 2 Name");
        TableColumn<FootballClub, Integer> column4 = new TableColumn<>("Club 2 Goals Score");
        TableColumn<FootballClub, Integer> column5 = new TableColumn<>("Date");

        column1.setCellValueFactory(new PropertyValueFactory<>("firstClub"));
        column2.setCellValueFactory(new PropertyValueFactory<>("club1GoalsScored"));
        column3.setCellValueFactory(new PropertyValueFactory<>("secondClub"));
        column4.setCellValueFactory(new PropertyValueFactory<>("club2GoalsScored"));
        column5.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableView.getColumns().addAll(column1, column2, column3, column4, column5);

        for (Match match : leagueMatch) {
            if (match.getDate().equals(date)) {
                tableView.getItems().add(new Match(match.getFirstClub(), match.getClub1GoalsScored(), match.getSecondClub(), match.getClub2GoalsScored(), match.getDate()));
            }
        }

        return tableView;
    }

    private TableView viewAccordingToDate() throws ParseException {
        for (int i=0; i<leagueMatch.size(); i++) {
            for (int j=0; j<leagueMatch.size()-1-i; j++) {

                Date date1=new SimpleDateFormat("yyyy/mm/dd").parse(leagueMatch.get(j).getDate());
                Date date2=new SimpleDateFormat("yyyy/mm/dd").parse(leagueMatch.get(j+1).getDate());

                if (date1.before(date2)) {
                    Match match = leagueMatch.get(j);
                    leagueMatch.set(j, leagueMatch.get(j+1));
                    leagueMatch.set(j+1, match);
                }
            }
        }

        TableView tableView = new TableView();

        TableColumn<FootballClub, String> column1 = new TableColumn<>("Club 1 Name");
        TableColumn<FootballClub, String> column2 = new TableColumn<>("Club 1 Goals Score");
        TableColumn<FootballClub, Integer> column3 = new TableColumn<>("Club 2 Name");
        TableColumn<FootballClub, Integer> column4 = new TableColumn<>("Club 2 Goals Score");
        TableColumn<FootballClub, Integer> column5 = new TableColumn<>("Date");

        column1.setCellValueFactory(new PropertyValueFactory<>("firstClub"));
        column2.setCellValueFactory(new PropertyValueFactory<>("club1GoalsScored"));
        column3.setCellValueFactory(new PropertyValueFactory<>("secondClub"));
        column4.setCellValueFactory(new PropertyValueFactory<>("club2GoalsScored"));
        column5.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableView.getColumns().addAll(column1, column2, column3, column4, column5);

        for (Match match : leagueMatch) {
            tableView.getItems().add(new Match(match.getFirstClub(), match.getClub1GoalsScored(), match.getSecondClub(), match.getClub2GoalsScored(), match.getDate()));
        }

        return tableView;
    }

    private TableView viewAccordingToWins() {
        for (int i=0; i<premierLeague.size(); i++) {
            for (int j=0; j<premierLeague.size()-1-i; j++) {
                if (premierLeague.get(j).getWins() < premierLeague.get(j+1).getWins()) {
                    FootballClub footballClub = premierLeague.get(j);
                    premierLeague.set(j, premierLeague.get(j+1));
                    premierLeague.set(j+1, footballClub);
                }
            }
        }

        TableView tableView = new TableView();

        TableColumn<FootballClub, String> column1 = new TableColumn<>("Club Name");
        TableColumn<FootballClub, String> column2 = new TableColumn<>("Club Location");
        TableColumn<FootballClub, Integer> column3 = new TableColumn<>("Wins");
        TableColumn<FootballClub, Integer> column4 = new TableColumn<>("Draws");
        TableColumn<FootballClub, Integer> column5 = new TableColumn<>("Defeats");
        TableColumn<FootballClub, Integer> column6 = new TableColumn<>("Goals Received");
        TableColumn<FootballClub, Integer> column7 = new TableColumn<>("Goals Scored");
        TableColumn<FootballClub, Integer> column8 = new TableColumn<>("Points");
        TableColumn<FootballClub, Integer> column9 = new TableColumn<>("Matches Played");

        column1.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        column2.setCellValueFactory(new PropertyValueFactory<>("clubLocation"));
        column3.setCellValueFactory(new PropertyValueFactory<>("wins"));
        column4.setCellValueFactory(new PropertyValueFactory<>("draws"));
        column5.setCellValueFactory(new PropertyValueFactory<>("defeats"));
        column6.setCellValueFactory(new PropertyValueFactory<>("goalsReceived"));
        column7.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));
        column8.setCellValueFactory(new PropertyValueFactory<>("clubPoints"));
        column9.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));

        tableView.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7,column8, column9);

        for (FootballClub footballClub : premierLeague) {
            tableView.getItems().add(new FootballClub(footballClub.getClubName(), footballClub.getClubLocation(), footballClub.getWins(), footballClub.getDraws(), footballClub.getDefeats(),footballClub.getGoalsReceived(),footballClub.getGoalsScored(),footballClub.getClubPoints(),footballClub.getMatchesPlayed()));
        }

        return tableView;
    }

    private TableView viewAccordingToScore() {
        for (int i=0; i<premierLeague.size(); i++) {
            for (int j=0; j<premierLeague.size()-1-i; j++) {
                if (premierLeague.get(j).getGoalsScored() < premierLeague.get(j+1).getGoalsScored()) {
                    FootballClub footballClub = premierLeague.get(j);
                    premierLeague.set(j, premierLeague.get(j+1));
                    premierLeague.set(j+1, footballClub);
                }
            }
        }

        TableView tableView = new TableView();

        TableColumn<FootballClub, String> column1 = new TableColumn<>("Club Name");
        TableColumn<FootballClub, String> column2 = new TableColumn<>("Club Location");
        TableColumn<FootballClub, Integer> column3 = new TableColumn<>("Wins");
        TableColumn<FootballClub, Integer> column4 = new TableColumn<>("Draws");
        TableColumn<FootballClub, Integer> column5 = new TableColumn<>("Defeats");
        TableColumn<FootballClub, Integer> column6 = new TableColumn<>("Goals Received");
        TableColumn<FootballClub, Integer> column7 = new TableColumn<>("Goals Scored");
        TableColumn<FootballClub, Integer> column8 = new TableColumn<>("Points");
        TableColumn<FootballClub, Integer> column9 = new TableColumn<>("Matches Played");

        column1.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        column2.setCellValueFactory(new PropertyValueFactory<>("clubLocation"));
        column3.setCellValueFactory(new PropertyValueFactory<>("wins"));
        column4.setCellValueFactory(new PropertyValueFactory<>("draws"));
        column5.setCellValueFactory(new PropertyValueFactory<>("defeats"));
        column6.setCellValueFactory(new PropertyValueFactory<>("goalsReceived"));
        column7.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));
        column8.setCellValueFactory(new PropertyValueFactory<>("clubPoints"));
        column9.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));

        tableView.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7,column8, column9);

        for (FootballClub footballClub : premierLeague) {
            tableView.getItems().add(new FootballClub(footballClub.getClubName(), footballClub.getClubLocation(), footballClub.getWins(), footballClub.getDraws(), footballClub.getDefeats(),footballClub.getGoalsReceived(),footballClub.getGoalsScored(),footballClub.getClubPoints(),footballClub.getMatchesPlayed()));
        }

        return tableView;

    }

    private TableView viewAccordingToPoints() {
        Collections.sort(premierLeague);

        TableView tableView = new TableView();

        TableColumn<FootballClub, String> column1 = new TableColumn<>("Club Name");
        TableColumn<FootballClub, String> column2 = new TableColumn<>("Club Location");
        TableColumn<FootballClub, Integer> column3 = new TableColumn<>("Wins");
        TableColumn<FootballClub, Integer> column4 = new TableColumn<>("Draws");
        TableColumn<FootballClub, Integer> column5 = new TableColumn<>("Defeats");
        TableColumn<FootballClub, Integer> column6 = new TableColumn<>("Goals Received");
        TableColumn<FootballClub, Integer> column7 = new TableColumn<>("Goals Scored");
        TableColumn<FootballClub, Integer> column8 = new TableColumn<>("Points");
        TableColumn<FootballClub, Integer> column9 = new TableColumn<>("Matches Played");

        column1.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        column2.setCellValueFactory(new PropertyValueFactory<>("clubLocation"));
        column3.setCellValueFactory(new PropertyValueFactory<>("wins"));
        column4.setCellValueFactory(new PropertyValueFactory<>("draws"));
        column5.setCellValueFactory(new PropertyValueFactory<>("defeats"));
        column6.setCellValueFactory(new PropertyValueFactory<>("goalsReceived"));
        column7.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));
        column8.setCellValueFactory(new PropertyValueFactory<>("clubPoints"));
        column9.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));

        tableView.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7,column8, column9);

        for (FootballClub footballClub : premierLeague) {
            tableView.getItems().add(new FootballClub(footballClub.getClubName(), footballClub.getClubLocation(), footballClub.getWins(), footballClub.getDraws(), footballClub.getDefeats(),footballClub.getGoalsReceived(),footballClub.getGoalsScored(),footballClub.getClubPoints(),footballClub.getMatchesPlayed()));
        }

        return tableView;
    }

}
