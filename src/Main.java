

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private BorderPane mainLayout;
    private Label pageTitle;

    @Override
    public void start(Stage stage) {

        // Main application layout
        mainLayout = new BorderPane();

        // Create navigation bar
        VBox sidebar = createSidebar();

        // Create dashboard content
        VBox dashboard = createDashboard();

        mainLayout.setLeft(sidebar);
        mainLayout.setCenter(dashboard);

        // Create the scene
        Scene scene = new Scene(mainLayout, 1100, 700);

        stage.setTitle("Ashesi Basketball Statistics Tracker");
        stage.setScene(scene);
        stage.show();
    }

    // Creates the sidebar navigation
    private VBox createSidebar() {

        Label appName = new Label("ASHESI BASKETBALL");
        appName.setStyle(
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );

        Button dashboardButton = new Button("Dashboard");
        Button playersButton = new Button("Players");
        Button statsButton = new Button("Player Statistics");
        Button gamesButton = new Button("Games");

        dashboardButton.setMaxWidth(Double.MAX_VALUE);
        playersButton.setMaxWidth(Double.MAX_VALUE);
        statsButton.setMaxWidth(Double.MAX_VALUE);
        gamesButton.setMaxWidth(Double.MAX_VALUE);

        dashboardButton.setOnAction(e -> showDashboard());
        playersButton.setOnAction(e -> showPage("Players"));
        statsButton.setOnAction(e -> showPage("Player Statistics"));
        gamesButton.setOnAction(e -> showPage("Games"));

        VBox sidebar = new VBox(
            20,
            appName,
            dashboardButton,
            playersButton,
            statsButton,
            gamesButton
        );

        sidebar.setPadding(new Insets(25));
        sidebar.setPrefWidth(230);
        sidebar.setStyle("-fx-background-color: #202B45;");

        return sidebar;
    }

    // Creates the dashboard homepage
    private VBox createDashboard() {

        Label heading = new Label("Dashboard");
        heading.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Label welcome = new Label(
            "Welcome to the Ashesi Basketball Statistics Tracker."
        );

        Label playersCard = new Label("Players\nManage registered players");
        Label statsCard = new Label("Statistics\nView player performance");
        Label gamesCard = new Label("Games\nManage basketball games");

        HBox cards = new HBox(
            20,
            createCard(playersCard),
            createCard(statsCard),
            createCard(gamesCard)
        );

        VBox dashboard = new VBox(20, heading, welcome, cards);
        dashboard.setPadding(new Insets(35));

        return dashboard;
    }

    // Creates a reusable dashboard card
    private VBox createCard(Label content) {

        VBox card = new VBox(content);
        card.setPadding(new Insets(25));
        card.setPrefSize(230, 140);
        card.setAlignment(Pos.CENTER_LEFT);

        card.setStyle(
            "-fx-background-color: #F1F3F8;" +
            "-fx-background-radius: 10;"
        );

        content.setStyle("-fx-font-size: 16px;");

        return card;
    }

    // Displays the dashboard
    private void showDashboard() {

        mainLayout.setCenter(createDashboard());
    }

    // Displays placeholder pages
    private void showPage(String pageName) {

        Label heading = new Label(pageName);
        heading.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Label message = new Label(
            "The " + pageName + " section will be implemented here."
        );

        VBox content = new VBox(20, heading, message);
        content.setPadding(new Insets(35));

        mainLayout.setCenter(content);
    }

    public static void main(String[] args) {

        launch(args);
    }
}
