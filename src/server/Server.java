package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Game Server is Running");
        try {
            while (true) {
                Game game = new Game();
                Game.Player playerX = game.new Player(listener.accept());
                Game.Player playerO = game.new Player(listener.accept());
                System.out.println("hello");
                playerX.setOpponent(playerO);
                playerO.setOpponent(playerX);
                
                playerX.start();
                playerO.start();
            }
        } finally {
            listener.close();
        }
    }
}

class Game {
    
    int current=0;
    // winner
//    public boolean hasWinner() {
//        if (current>=5){
//            int count=0;
//            Player x=board[0];
//            for (int i=0;i<current;i++){
//                if (board[i]==x) count++;
//            }
//            if (count==5||current-count==5) return true;
//        }
//        return false;
//    }

    
    public synchronized boolean chat(String a,Player player){
        player.opponent.output.println("CHAT "+a);
        player.output.println("SELF "+a);
        return true;
    }
    public synchronized boolean disconnect(Player player){
        player.opponent.output.println("OPPONENT_QUIT");
        try {
			player.opponent.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return true;
    }
    public synchronized boolean legalMove(String type, Player player) {
        player.currentmove=type;
        String [] types={"Scissor","Paper","Rock"};
        String [] win={"Paper","Rock","Scissor"};
        String [] lose={"Rock","Scissor","Paper"};
        if (player.opponent.currentmove!=""){
            if (player.currentmove==player.opponent.currentmove){
                    System.out.println("Tie");
                    player.output.println("Tie");
                    player.opponent.output.println("Tie");
                    player.currentmove="";
                    player.opponent.currentmove="";
            }else
            for (int i=0;i<3;i++){
                if (player.currentmove==types[i]){
                    if (player.opponent.currentmove==win[i]){
                        System.out.println(player.name+" win");
                        player.point++;
                        if (player.point==5) {
                        	player.output.println("You victory "+player.point+" "+player.opponent.point);
	                        player.opponent.output.println("You defeated"+player.opponent.point+" "+player.point);
	                        try {
								player.socket.close();
								player.opponent.socket.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
	                        
                        }
                        else {
	                        player.output.println("You win "+player.point+" "+player.opponent.point);
	                        player.opponent.output.println("You lose "+player.opponent.point+" "+player.point);
                        }
                        player.currentmove="";
                        player.opponent.currentmove="";
                    }else
                    if (player.opponent.currentmove==lose[i]){
                        System.out.println(player.opponent.name+" win");
                        player.opponent.point++;
                        if (player.opponent.point==5) {
                        	player.output.println("You defeated"+player.point+" "+player.opponent.point);
                            player.opponent.output.println("You victory"+player.opponent.point+" "+player.point);
                            try {
								player.socket.close();
								player.opponent.socket.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                            
                        }else {
                        	player.output.println("You lose "+player.point+" "+player.opponent.point);
                            player.opponent.output.println("You win "+player.opponent.point+" "+player.point);
                            
                        }
                        
                        player.currentmove="";
                        player.opponent.currentmove="";
                    }
                    break;
                }
            }
        }
//        if ()
//        if (player == currentPlayer && board[location] == null) {
//            board[location] = currentPlayer;
//            currentPlayer = currentPlayer.opponent;
//            currentPlayer.otherPlayerMoved(location);
//            return true;
//        }
        return true;
    }
   

    class Player extends Thread {
        String name;
        Player opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;
        String currentmove="";
        int point=0;
        // thread handler to initialize stream fields
        public Player(Socket socket) {
            this.socket = socket;
//            this.mark = mark;
            name=socket.getInetAddress().getHostAddress();
            try {
                input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                output.println("WELCOME " + name);
                output.println("System: Waiting for opponent to connect");
            } catch (IOException e) {
//                System.out.println("Player died: " + e);
            }
        }
        //Accepts notification of who the opponent is.
        public void setOpponent(Player opponent) {
            this.opponent = opponent;
        }

        
         //Handles the otherPlayerMoved message. 
        
    
        public void run() {
            try {
                output.println("System: All players connected");
                while (true) {
                    String command = input.readLine();
                    System.out.println("Server receive: "+command);
                    if (command.startsWith("Paper")){
                        legalMove("Paper", this);
                        System.out.println(currentmove);
                    }else if (command.startsWith("Rock")){
                        legalMove("Rock", this);
                        System.out.println(currentmove);
                    }else if (command.startsWith("Scissor")){
                        legalMove("Scissor", this);
                        
                    }else if (command.startsWith("Chat")){
                        chat(command.substring(5),this);
                    }
                }
            } catch (IOException e) {
            	disconnect(this);
            } finally {
                try {socket.close();} catch (IOException e) {}
            }
        }

//        private void chat(String substring) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
    
    }
}