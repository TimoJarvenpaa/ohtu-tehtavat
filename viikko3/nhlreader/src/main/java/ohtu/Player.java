
package ohtu;

public class Player {
    private int goals;
    private int assists;
    private String name;
    private int penalties;
    private String team;
    private String nationality;
    private String birthdate;

    public Player(int goals, int assists, String name, int penalties, String team, String nationality, String birthdate) {
        this.goals = goals;
        this.assists = assists;
        this.name = name;
        this.penalties = penalties;
        this.team = team;
        this.nationality = nationality;
        this.birthdate = birthdate;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getPoints() {
        return goals + assists;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-3s %3d %s %2d %s %2d", name, team, goals, "+", assists, "=", this.getPoints());
    }
}
