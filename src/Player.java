public class Player{
    private String name;
    private int jerseyNumber;
    private String position;

    public Player(){
        
    }
    public Player(String name, int jerseyNumber, String position) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public String getPosition() {
        return position;
    }
    
}