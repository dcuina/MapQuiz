/*
* Daniel Cuina
* UIN: 834001589
* Section 506
* 4/21/2024
*/

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.ImageIO;

public class AmericasMinefield extends JFrame implements ActionListener{
    private JButton[] buttonGrid = new JButton[20];
    private ArrayList<String> countries = new ArrayList<>();
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();
    int index = 0;
    private JTextField instructions = new JTextField("Select the country that matches the question.");
    private JTextField questionBox = new JTextField();

    public AmericasMinefield() {
        // Area of frame with background image
        BackgroundPanel background = new BackgroundPanel();
        GridBagConstraints positionConst = null;
        setContentPane(background);
        setSize(1000,500);
        setTitle("Americas Minefield");
        setLayout(new GridBagLayout());
        positionConst = new GridBagConstraints();

        // Source: https://www.w3schools.com/java/java_files_read.asp
        // Reading text file to get questions and answers
        try {
            int count = 0;
            File textbase = new File("minefieldText.txt");
            Scanner sc = new Scanner(textbase);
            while (count < 40) {
                if (count < 20) {
                    answers.add(sc.nextLine());
                }
                else {
                    questions.add(sc.nextLine());
                }
                count++;
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        for (String a : answers) {
            countries.add(a);
        }

        // Source: https://www.javatpoint.com/how-to-sort-arraylist-in-java
        // Sorting countries so buttons can be in alphabetical order
        Collections.sort(countries);
        

        int j = 1;
        int k = 5;
        positionConst.insets = new Insets(10, 10, 10, 10);
        // Puts buttons in a 5 by 4 grid
        for (int i = 0; i < countries.size(); i++) {
            buttonGrid[i] = new JButton(countries.get(i));
            // Source: https://stackoverflow.com/questions/2536873/how-can-i-set-size-of-a-button
            buttonGrid[i].setPreferredSize(new Dimension(120,30));
            buttonGrid[i].addActionListener(this);
            if (j > 5) {
                j = 1;
                k++;
            }
            positionConst.gridx = j;
            positionConst.gridy = k;
            background.add(buttonGrid[i], positionConst);
            j++;
        }
        // Unmodifiable text box for questions
        questionBox.setEditable(false);
        questionBox.setText(questions.get(0));
        questionBox.setPreferredSize(new Dimension(400, 50));

        positionConst.gridx = 3;
        positionConst.gridy = 1;
        background.add(questionBox, positionConst);

        instructions.setEditable(false);
        instructions.setPreferredSize(new Dimension(300, 50));

        positionConst.gridx = 3;
        positionConst.gridy = 0;
        background.add(instructions, positionConst);


        
    }


    public void actionPerformed(ActionEvent event) {
        String name = event.getActionCommand();
        // Checks if the button title is the answer to the given question
        if (name.equals(answers.get(index))) {
            if (index == answers.size()-1) {
                // Shows if the last question was answered correctly
                JOptionPane.showMessageDialog(this, "You Won!");
                int choice = JOptionPane.showConfirmDialog(this, "Would you like to play again?", "Replay", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    index = 0;
                    questionBox.setText(questions.get(index));
                }
            }
            else {
                questionBox.setText(questions.get(index+1));
                index++;
            }
        }
        else {
            // Restarts if a question is answered incorrectly
            JOptionPane.showMessageDialog(this, "Too bad! The game will now restart.");
            index = 0;
            questionBox.setText(questions.get(index));
        }
        
        
    }


    // Source: https://stackoverflow.com/questions/19125707/simplest-way-to-set-image-as-jpanel-background
    // Adds background image to frame
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            super();
            try {
                backgroundImage = ImageIO.read(new File ("americas.png"));
            }
            catch (Exception e){
                System.out.println(e.getMessage());;        
            }
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this.getWidth(),this.getHeight(),this);
        }
    }

}
