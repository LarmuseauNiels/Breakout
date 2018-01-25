package be.brickrevolution.model;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Player {

    private int idplayer;
    private String username;
    private String password;
    private String email;
    private int singleplayerhighscore;
    private int multiplayerhighscoreplayer1;
    private int multiplayerhighscoreplayer2;
    private int goals;
    private String uuid;

    public Player(int idplayer, String username, String password, String email, int singleplayerhighscore, int multiplayerhighscoreplayer1, int multiplayerhighscoreplayer2, String uuid) {
        setIdplayer(idplayer);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setSingleplayerhighscore(singleplayerhighscore);
        setMultiplayerhighscoreplayer1(multiplayerhighscoreplayer1);
        setMultiplayerhighscoreplayer2(multiplayerhighscoreplayer2);
        setUuid(uuid);
    }

    public Player(String username, String password, String email, String uuid) {
        this(-1, username, password, email, 0, 0, 0, uuid);
    }

    public Player(String username, int singleplayerhighscore) {
        this(-1, username,null,null,singleplayerhighscore,0,0,null);
    }

    public Player(String username, int multiplayerhighscoreplayer1, int multiplayerhighscoreplayer2) {
      this(-1,username,null,null,0,multiplayerhighscoreplayer1,multiplayerhighscoreplayer2,null);  
    }

    
    

    public int getIdplayer() {
        return idplayer;
    }

    public void setIdplayer(int idplayer) {
        this.idplayer = idplayer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSingleplayerhighscore() {
        return singleplayerhighscore;
    }

    public void setSingleplayerhighscore(int singleplayerhighscore) {
        this.singleplayerhighscore = singleplayerhighscore;
    }

    public int getMultiplayerhighscoreplayer1() {
        return multiplayerhighscoreplayer1;
    }

    public void setMultiplayerhighscoreplayer1(int multiplayerhighscoreplayer1) {
        this.multiplayerhighscoreplayer1 = multiplayerhighscoreplayer1;
    }

    public int getMultiplayerhighscoreplayer2() {
        return multiplayerhighscoreplayer2;
    }

    public void setMultiplayerhighscoreplayer2(int multiplayerhighscoreplayer2) {
        this.multiplayerhighscoreplayer2 = multiplayerhighscoreplayer2;
    }

    public void incrGoal() {
        goals++;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    @Override
    public String toString() {
        return "dit is player: " + this.getUsername();
    }

    public JsonObjectBuilder getJson() {
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("playername", this.username)
                .add("goals", this.goals);

        return json;
    }

    private void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

}
