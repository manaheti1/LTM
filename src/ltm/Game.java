/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 *
 * @author manah
 */
public class Game extends javax.swing.JFrame {
    int a=0,b=0;

    /**
     * Creates new form Gamer
     */
    public void setActionListener(ActionListener a){
        scissor.addActionListener(a);
        paper.addActionListener(a);
        rock.addActionListener(a);
        chat.addActionListener(a);
    }
    
    public void append(String a){
        messageLabel.append(a+"\n");
    }
    public void win(){
        you.setText("You "+(++a));
    }
    public void lose(){
        opp.setText("Opponent "+(++b));
    }
    public String getTxt(){
        String a=chat_context.getText().toString();
        chat_context.setText("");
        return a;
    }
    
    public Game() {
        initComponents();
        scissor.setActionCommand("Scissor");
        rock.setActionCommand("Rock");
        paper.setActionCommand("Paper");
        chat.setActionCommand("Chat");
    }
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scissor = new javax.swing.JButton();
        paper = new javax.swing.JButton();
        rock = new javax.swing.JButton();
        chat = new javax.swing.JButton();
        chat_context = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageLabel = new javax.swing.JTextArea();
        you = new javax.swing.JLabel();
        opp = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
       
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        scissor.setBackground(new java.awt.Color(0, 0, 0));
        scissor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/scissors (3).png"))); // NOI18N
        scissor.setToolTipText("");
        scissor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scissor.setOpaque(false);
        scissor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scissorActionPerformed(evt);
            }
        });

        paper.setBackground(new java.awt.Color(0, 0, 0));
        paper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/paper (1).png"))); // NOI18N
        paper.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        rock.setBackground(new java.awt.Color(51, 51, 51));
        rock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rock (1).png"))); // NOI18N
        rock.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        chat.setText("Chat");



        messageLabel.setColumns(20);
        messageLabel.setRows(5);
        jScrollPane1.setViewportView(messageLabel);

        you.setText("You: 0");

        opp.setText("Opponent: 0");

        jLabel3.setText("Win: 5");




        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chat_context, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chat, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(scissor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(paper)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rock)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(you)
                            
                            
                            
                            .addComponent(opp)
                            .addComponent(jLabel3))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rock)
                            .addComponent(paper)
                            .addComponent(scissor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(you)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(opp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                        .addGap(18, 18, 18)
                        
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chat)
                    .addComponent(chat_context, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void scissorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scissorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scissorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        messageLabel.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });

    }


    private javax.swing.JButton chat;
    private javax.swing.JTextField chat_context;
    private javax.swing.JLabel jLabel3;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messageLabel;
    private javax.swing.JLabel opp;
    private javax.swing.JButton paper;

    private javax.swing.JButton rock;
    private javax.swing.JButton scissor;
    private javax.swing.JLabel you;

}
