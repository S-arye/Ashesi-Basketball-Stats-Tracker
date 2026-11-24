public class PlayerStats{
    private Player player;

    private int twoPointMade;
    private int twoPointAttempted;

    private int threePointMade;
    private int threePointAttempted;

    private int freeThrowMade;
    private int freeThrowAttempted;

    private int rebounds;
    private int assists;
    private int steals;
    private int blocks;
    
    public PlayerStats(Player player, int twoPointMade, int twoPointAttempted, int threePointMade,
            int threePointAttempted, int freeThrowMade, int freeThrowAttempted, int rebounds, int assists, int steals,
            int blocks) {

        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        if (twoPointMade < 0 || twoPointAttempted < 0 ||
            threePointMade < 0 || threePointAttempted < 0 ||
            freeThrowMade < 0 || freeThrowAttempted < 0 ||
            rebounds < 0 || assists < 0 || steals < 0 || blocks < 0) {

            throw new IllegalArgumentException("Statistics cannot be negative");
        }

        if (twoPointMade > twoPointAttempted ||
            threePointMade > threePointAttempted ||
            freeThrowMade > freeThrowAttempted) {

            throw new IllegalArgumentException("Made shots cannot exceed attempts");
        }
        this.player = player;
        this.twoPointMade = twoPointMade;
        this.twoPointAttempted = twoPointAttempted;
        this.threePointMade = threePointMade;
        this.threePointAttempted = threePointAttempted;
        this.freeThrowMade = freeThrowMade;
        this.freeThrowAttempted = freeThrowAttempted;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
    }


    public Player getPlayer() {
        return player;}


    public int getTwoPointMade() {
        return twoPointMade;
    }


    public int getTwoPointAttempted() {
        return twoPointAttempted;
    }


    public int getThreePointMade() {
        return threePointMade;
    }


    public int getThreePointAttempted() {
        return threePointAttempted;
    }


    public int getFreeThrowMade() {
        return freeThrowMade;
    }


    public int getFreeThrowAttempted() {
        return freeThrowAttempted;
    }


    public int getRebounds() {
        return rebounds;
    }


    public int getAssists() {
        return assists;
    }


    public int getSteals() {
        return steals;
    }


    public int getBlocks() {
        return blocks;
    }

    public int getPoints() {
    return (twoPointMade * 2) + (threePointMade * 3) + freeThrowMade;
}

    public double getShootingPercentage() {

    int totalMade = twoPointMade + threePointMade +freeThrowMade;
    int totalAttempted = twoPointAttempted + threePointAttempted + freeThrowAttempted;

    if (totalAttempted == 0) {
        return 0.0;
    }

    return ((double) totalMade / totalAttempted) * 100;
}
   
}
    
