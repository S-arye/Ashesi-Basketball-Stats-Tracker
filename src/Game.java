import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// This class represents a single basketball game between two teams.
// It tracks the teams, the score, and the game status.
// Written by: Antipas Malual Mabeny

public class Game {

    // A game can be in one of three stages
    public enum Status {
        SCHEDULED,    // game hasn't started yet
        IN_PROGRESS,  // game is currently being played
        FINAL         // game is over
    }

    // Basic game details
    private String gameId;
    private Team homeTeam;
    private Team awayTeam;
    private LocalDate date;

    // Scores for each team
    private int homeScore;
    private int awayScore;

    // Tracks the current stage of the game
    private Status status;

    // Stores a log of every scoring event during the game
    private List<String> playByPlay;


    // Constructor – sets up a new game with two teams and a date
    public Game(String gameId, Team homeTeam, Team awayTeam, LocalDate date) {

        if (gameId == null || gameId.trim().isEmpty()) {
            throw new IllegalArgumentException("Game ID cannot be empty.");
        }
        if (homeTeam == null || awayTeam == null) {
            throw new IllegalArgumentException("Both teams are required.");
        }
        if (homeTeam == awayTeam) {
            throw new IllegalArgumentException("A team cannot play against itself.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Game date cannot be null.");
        }

        this.gameId     = gameId;
        this.homeTeam   = homeTeam;
        this.awayTeam   = awayTeam;
        this.date       = date;
        this.homeScore  = 0;
        this.awayScore  = 0;
        this.status     = Status.SCHEDULED;
        this.playByPlay = new ArrayList<>();
    }


    // --- Getters ---

    public String getGameId() {
        return gameId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public LocalDate getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }


    // --- Game lifecycle ---

    // Call this to start the game
    public void startGame() {
        if (status != Status.SCHEDULED) {
            throw new IllegalStateException("Game has already started or finished.");
        }
        status = Status.IN_PROGRESS;
        playByPlay.add("--- Game started ---");
    }

    // Call this when the game is over
    public void finishGame() {
        if (status == Status.SCHEDULED) {
            throw new IllegalStateException("Cannot finish a game that has not started.");
        }
        if (status == Status.FINAL) {
            throw new IllegalStateException("Game is already finished.");
        }
        status = Status.FINAL;
        playByPlay.add("--- Game finished ---");
    }


    // --- Scoring ---

    // Add points for the home team (1 = free throw, 2 = field goal, 3 = three-pointer)
    public void addHomePoints(int points) {
        recordPoints(true, points);
    }

    // Add points for the away team (1 = free throw, 2 = field goal, 3 = three-pointer)
    public void addAwayPoints(int points) {
        recordPoints(false, points);
    }

    // Helper method used by addHomePoints and addAwayPoints
    private void recordPoints(boolean isHome, int points) {
        if (status != Status.IN_PROGRESS) {
            throw new IllegalStateException("Points can only be added while the game is in progress.");
        }
        if (points != 1 && points != 2 && points != 3) {
            throw new IllegalArgumentException("Points must be 1, 2, or 3.");
        }

        if (isHome) {
            homeScore += points;
            playByPlay.add("HOME +" + points + " | Score: " + homeScore + " - " + awayScore);
        } else {
            awayScore += points;
            playByPlay.add("AWAY +" + points + " | Score: " + homeScore + " - " + awayScore);
        }
    }


    // --- Result methods ---

    // Returns true if the game is over
    public boolean isFinished() {
        return status == Status.FINAL;
    }

    // Returns true if both teams finished with the same score
    public boolean isTie() {
        return isFinished() && homeScore == awayScore;
    }

    // Returns the team that won, or null if it's a tie or the game isn't over
    public Team getWinner() {
        if (!isFinished() || homeScore == awayScore) {
            return null;
        }
        return homeScore > awayScore ? homeTeam : awayTeam;
    }

    // Returns the combined score of both teams
    public int getTotalPoints() {
        return homeScore + awayScore;
    }

    // Returns how many points separate the two teams
    public int getMargin() {
        return Math.abs(homeScore - awayScore);
    }


    // --- Play-by-play ---

    // Returns the list of all scoring events that happened during the game
    public List<String> getPlayByPlay() {
        return playByPlay;
    }


    // --- Summary ---

    // Prints a simple summary of the game to the console
    public void printGameSummary() {
        System.out.println("========================================");
        System.out.println("  GAME SUMMARY: " + gameId);
        System.out.println("  Date   : " + date);
        System.out.println("  Status : " + status);
        System.out.println("----------------------------------------");
        System.out.println("  Home Team Score: " + homeScore);
        System.out.println("  Away Team Score: " + awayScore);
        System.out.println("----------------------------------------");

        if (isFinished()) {
            if (isTie()) {
                System.out.println("  RESULT: It's a tie! (" + homeScore + " - " + awayScore + ")");
            } else {
                String winnerSide = homeScore > awayScore ? "Home Team" : "Away Team";
                System.out.println("  WINNER: " + winnerSide + " (by " + getMargin() + " points)");
            }
        } else {
            System.out.println("  Game not finished yet.");
        }

        System.out.println("----------------------------------------");
        System.out.println("  Play-by-play:");
        for (String event : playByPlay) {
            System.out.println("    " + event);
        }
        System.out.println("========================================");
    }


    // Returns a quick one-line description of the game
    @Override
    public String toString() {
        return "Game " + gameId + " [" + status + "] " + homeScore + " - " + awayScore;
    }
}



