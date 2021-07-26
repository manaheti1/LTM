package ltm;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Client {

    private Game frame = new Game();
  

    a a=new a();
    public class a implements ActionListener{
        public a(){}
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("Chat")){
                out.println("Chat "+frame.getTxt());
            }
            else
             out.println(e.getActionCommand());
        }
        
    }


    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
  
    public Client(String serverAddress) throws Exception {
        
        // Setup networking
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(
        socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        frame.setActionListener(a);
    }

  
    public void play() throws Exception {
        String response;
        try {
            System.out.println("nope");
            response = in.readLine();
            if (response.startsWith("WELCOME")) {
                char mark = response.charAt(8);
                frame.append("Welcome\n");
                frame.setTitle("Tic Tac Toe" );
            }
            while (true) {
                response = in.readLine();
                System.out.println("Client receive: " + response);
                if (response.startsWith("You win")) {
                    frame.append("You won");
                    frame.win();
//                    messageLabel.append(response.substring(8)+"\n");
                } else if (response.startsWith("You lose")) {
                    frame.append("You lose");
                    frame.lose();
                } else if (response.startsWith("You defeated")) {
                	int input = JOptionPane.showOptionDialog(null, "You defeated", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

    				if(input == JOptionPane.OK_OPTION)
    				{
    					socket.close();
    					frame.dispose();
    					return;
    				    // do something
    				}
                } else if (response.startsWith("You victory")) {
                	int input = JOptionPane.showOptionDialog(null, "You victoried", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

    				if(input == JOptionPane.OK_OPTION)
    				{
    					socket.close();
    					
    					frame.dispose();
    					return;
    				    // do something
    				}
//                    break;
                } else if (response.startsWith("Tie")) {
                    frame.append("Tie");
//                  messageLabel.append(response.substring(8)+"\n");
//                    break;
                } else if (response.startsWith("CHAT")) {
                    frame.append("Opponent: "+response.substring(5));
//                    messageLabel.append(response.substring(8)+"\n");
                } else if (response.startsWith("SELF")) {
                    frame.append("You: "+response.substring(5));
                    
//                    messageLabel.append(response.substring(8)+"\n");
                } else if (response.startsWith("OPPONENT_QUIT")){
                    
                    int input = JOptionPane.showOptionDialog(null, "Opponent Quited", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

    				if(input == JOptionPane.OK_OPTION)
    				{
    					socket.close();
    					frame.dispose();
    					return;
    				    // do something
    				}
                    
                }
                
            }
//            System.out.println("ENDED\n");
//            out.println("QUIT");
        }catch(Exception e){
            System.out.println(e);
        }
        finally {
            socket.close();
        }
    }

    public static void main(String[] args) throws Exception {
//        while (true) {
            String serverAddress = "192.168.1.7" ;
            Client client = new Client(serverAddress);
            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            client.frame.setVisible(true);
            client.play();
//        }
    }
}
