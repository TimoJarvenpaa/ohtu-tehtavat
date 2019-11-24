package ohtu;

public class TennisGame {

    private int p1Score = 0;
    private int p2Score = 0;
    private String p1Name;
    private String p2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.p1Name = player1Name;
        this.p2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(this.p1Name)) {
            p1Score += 1;
        }

        if (playerName.equals(this.p2Name)) {
            p2Score += 1;
        }
    }

    public String getScore() {
        if (gameIsTied()) {
            return getDrawScore();
        } else if (lateGameReached()) {
            return getLateGameScore();
        } else {
            return getRegularScore();
        }
    }

    public String getDrawScore() {
        switch (p1Score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }

    public boolean gameIsTied() {
        if (p1Score == p2Score) {
            return true;
        } else {
            return false;
        }
    }

    public boolean lateGameReached() {
        if (p1Score >= 4 || p2Score >= 4) {
            return true;
        } else {
            return false;
        }
    }

    public String getLateGameScore() {
        int pointDifference = p1Score - p2Score;

        if (pointDifference == 1) {
            return "Advantage player1";
        } else if (pointDifference == -1) {
            return "Advantage player2";
        } else if (pointDifference >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    public String getRegularScore() {
        return scoreToString(p1Score) + "-" + scoreToString(p2Score);
    }

    public String scoreToString(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "";
        }
    }
}
