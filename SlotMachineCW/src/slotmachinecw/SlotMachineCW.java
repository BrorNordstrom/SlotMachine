/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachinecw;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Formatter;

/**
 *
 * @author Andreas Nordstrom
 */
public class SlotMachineCW extends JFrame {
 static int credits = 10;
 // Initial credits given to users
 static int bet = 0;
 // Given amount users bet
 static int win = 0;
 // Amount of rounds users win
 static int lost = 0;
 // Amount of rounds users lost
 static int totalCreditsWon;
 // Total amount of credits won by users
 static Symbol[] symbolsSpun = new Symbol[3];
 // stops the symbols spun
 static double average=0;
 
// Java Labels 
 
 static JLabel titleT = new JLabel("Slot Machine");
 // Shows the title
 static JLabel creditT = new JLabel("Credits: " + credits);
 // Displays the amount of credits
 static JLabel betT = new JLabel("Bet: " + bet);
 // Shows amount the users bet
 static JLabel result = new JLabel();
 // Shows result, if users win or lose
 
 
 // Java Buttons for the reel symbols

 static JButton button1 = new JButton();
 static JButton button2 = new JButton();
 static JButton button3 = new JButton();
 
 static JButton betbutton = new JButton("Bet one");
 static JButton mbetbutton = new JButton("Bet max");
 static JButton resetbutton = new JButton("Reset");
 static JButton spinbutton = new JButton("Spin reel");
 static JButton addbutton = new JButton("Add coin");
 static JButton statsbutton = new JButton("Statistics");
 static JButton savestatsbutton = new JButton("Save Statistics");
 
 

 static Thread rt1, rt2, rt3 = new Thread();
//Clarifying threadnames
 
public static void updateLabels() {
    creditT.setText("Credits: " + credits);
    titleT.setText("Slot Machine");
    betT.setText("Bet: " + bet);
}
 // Updating the credit and bet text
 
public synchronized static void winnings (Symbol s) {
    for (int i = 0; i < symbolsSpun.length; i++) {
    if (symbolsSpun[i] == null) {
        symbolsSpun[i] = s;
    break;
    }}
    // If symbol array has space, then it will add the symbols
    
    if (symbolsSpun[0] == null || symbolsSpun[1] == null || symbolsSpun[2] == null) {
        return;
    }
    //If symbol array is not full, then exit method
    
    if (symbolsSpun[0].getValue()==symbolsSpun[1].getValue()){
        
        int value = bet * symbolsSpun[0].getValue();
        credits += value;
        bet = 0;
        updateLabels();
        result.setText("You won: " + value + " credits");
        win++;
        totalCreditsWon += value;
    }
    else if (symbolsSpun[1].getValue() == symbolsSpun[2].getValue()) {
        int value = bet * symbolsSpun[1].getValue();
        credits += value;
        bet = 0;
        updateLabels();
        result.setText("You won: " + value + " credits");
        win++;
        totalCreditsWon += value;
    }
    else if (symbolsSpun[0].equals(symbolsSpun[2])) {
        int value = bet * symbolsSpun[0].getValue();
        credits += value;
        bet = 0;
        updateLabels();
        result.setText("You won: " + value + " credits");
        win++;
        totalCreditsWon += value;
    }
    else {
        result.setText("You lost: " + bet);
        bet = 0;
        updateLabels();
        lost++;
        
    }
    
    for (int i = 0; i < symbolsSpun.length; i++) {
        symbolsSpun[i] = null;
    }
    betbutton.setEnabled (true);
    mbetbutton.setEnabled (true);
    resetbutton.setEnabled (true);
    spinbutton.setEnabled (true);
    
    // Enables the buttons
}

// A window has been created and widgets attached
 public static void main(String[] args){
     JFrame guiBAN = new JFrame();
     guiBAN.setLayout(new GridLayout(0,3));
     Symbol randomSymbol = new Symbol();
     randomSymbol.setImage("cherry");
     //Initital image displayed set as cheery
     button1.setIcon(randomSymbol.getImage());
     button2.setIcon(randomSymbol.getImage());
     button3.setIcon(randomSymbol.getImage());
     
     // A window has been created and widgets attached
     
     guiBAN.add(creditT);
     guiBAN.add(titleT);
     guiBAN.add(betT);
     guiBAN.add(result);
     
     guiBAN.add(new JLabel());
     guiBAN.add(new JLabel());
     
     guiBAN.add(button1);
     guiBAN.add(button2);
     guiBAN.add(button3);
     
     guiBAN.add(new JLabel());
     guiBAN.add(new JLabel());
     guiBAN.add(new JLabel());
     
     guiBAN.add(spinbutton);
     
     guiBAN.add(betbutton);
     guiBAN.add(mbetbutton);
     guiBAN.add(resetbutton);
     guiBAN.add(addbutton);
     guiBAN.add(statsbutton);
     
     // Action listeners below have lambdas to handle the events
     
     button1.addActionListener((ActionEvent e) -> {
         if ( rt1.isAlive()) {
             rt1.interrupt();
             rt2.interrupt();
             rt3.interrupt();
         }
     });
     
     button2.addActionListener((ActionEvent e) -> {
         if (rt2.isAlive()) {
             rt1.interrupt();
             rt2.interrupt();
             rt3.interrupt();
         }
     });
     
     button3.addActionListener((ActionEvent e) -> {
         if (rt3.isAlive()) {
             rt1.interrupt();
             rt2.interrupt();
             rt3.interrupt();
         }
     });
     
    spinbutton.addActionListener((ActionEvent e) -> {
        if (bet == 0) {
            JOptionPane.showMessageDialog(guiBAN, "ERROR! You need to add a bet!");
            return;
        }
        
        betbutton.setEnabled(false);
        mbetbutton.setEnabled(false);
        resetbutton.setEnabled(false);
        result.setText("Press reel to stop");
        // This will disable the buttons as the reel spins
        
        
rt1 = new Thread((new Runnable() {
  
    
    // A thread is been created with inner class as thread
    
    public void run () {
        Reel reel1 = new Reel();
        Symbol[] symbols = reel1.spin();
        boolean spin = true;
        while (spin) {
            for (Symbol s : symbols) {
                button1.setIcon(s.getImage());
                if (Thread.interrupted()) {
                winnings(s);
                spin = false;
                break;
                
            }}}}
}));
rt2 = new Thread((new Runnable() {
    
    public void run () {
        Reel reel1 = new Reel();
        Symbol[] symbols = reel1.spin();
        boolean spin = true;
        while (spin) {
            for (Symbol s : symbols) {
                button2.setIcon(s.getImage());
                if (Thread.interrupted()) {
                winnings(s);
                spin = false;
                break;
                
            }}}}
}));
rt3 = new Thread ((new Runnable() {
    
    public void run () {
        Reel reel1 = new Reel();
        Symbol[] symbols = reel1.spin();
        boolean spin = true;
        while (spin) {
            for (Symbol s : symbols) {
                button3.setIcon(s.getImage());
                if (Thread.interrupted()) {
                winnings(s);
                spin = false;
                break;
                
            }}}}
}));

rt1.start();
rt2.start();
rt3.start();

// Start the threads


spinbutton.setEnabled(false);

    });

betbutton.addActionListener((ActionEvent e) -> {
    if (credits > 0) {
        credits --;
        bet ++;
        updateLabels();
        
    }
    else {
        JOptionPane.showMessageDialog(guiBAN, "ERROR! You don't have enough credits!");
    }
});
mbetbutton.addActionListener((ActionEvent e) -> {
    if ( credits > 2) {
        credits -= 3;
        bet += 3;
        updateLabels();
        mbetbutton.setEnabled(false);
    }
    else {
     JOptionPane.showMessageDialog(guiBAN, "ERROR! You don't have 3 credits to bet!");
    }
});

resetbutton.addActionListener((ActionEvent e) -> {
   if( bet > 0) {
       credits += bet;
       bet = 0;
       updateLabels();
   }
   });

addbutton.addActionListener((ActionEvent e) -> {
    credits++;
    updateLabels();
});

statsbutton.addActionListener((ActionEvent e) -> {
    JFrame statistics = new JFrame();
    statistics.setLayout(new GridLayout(0,2));
    
   
    JTextField noOfWin = new JTextField();
    JTextField noOfLoss = new JTextField();
 
    //Text fields created to represent the statistics of win/loss
    
    
    if (win + lost == 0) {
        average = totalCreditsWon / (1);
    }
    else {
        average = totalCreditsWon / (win + lost);
    }
    
    noOfWin.setPreferredSize(new Dimension(15 * win, 75));
    noOfWin.setBackground(Color.GREEN);
    
    // Creates the size of the proportions
    
    noOfLoss.setPreferredSize(new Dimension(15 * win, 75));
    noOfLoss.setBackground(Color.RED);
    
    statistics.add(new JLabel ("Total number of rounds won: " + win));
    statistics.add(noOfWin);
    
    statistics.add(new JLabel ("Total number of rounds lost: " + lost));
    statistics.add(noOfLoss);
    
    statistics.add(new JLabel ("Average credits won: " + average));
    
    
    savestatsbutton.addActionListener((ActionEvent ae) -> {
    String statethestats = "Rounds won: " + win + "\n Rounds lost: " + lost + "\n Average credits won: " + average;
    
    try {
        new File ("Statistics").mkdir();
        
        //This creates a new file called stats
        
    Formatter f = new Formatter("Statistics\\" + new Date().toString().replace(':', '-'));
    
    //Statistics will be the file name created and the colons will be replaced to a suitable file name symbol
    
    f.format("%s", statethestats);
    f.close();
    }
    catch (IOException ae2) {
        ae2.printStackTrace();
    }
    });
    statistics.add(savestatsbutton);
    
    statistics.setSize(600,300);
    
    statistics.setVisible(true);
    
});
   
guiBAN.setTitle(" SLOT MACHINE - by Bror Andreas Nordstrom");
    
guiBAN.setSize(740, 520);

// Creates the windows properties

guiBAN.getContentPane().setBackground(Color.white); 
//Background color

guiBAN.setVisible(true);

guiBAN.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    }
    
 
 

