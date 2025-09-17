/**
 * Tucker Faircloth
 * 432004120
 * CSCE 111
 * 4/17/24
 */
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.util.*;
import java.util.List;

public class FlagQuizGame extends JFrame {
    private List<String> countries = new ArrayList<>();
    private JButton[] options = new JButton[4];
    private JLabel flagLabel = new JLabel();
    private final String flagsDirectoryPath = "./flags"; // Path to the flags directory
    private String correctAnswer;
    
    //loading a new flag quiz
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FlagQuizGame().setVisible(true);
        });
    }
    //creating a new JFrame with the title of Flag Quiz Game
    public FlagQuizGame() {
        super("Flag Quiz Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        //loading the countries from the flags directory 
        loadCountries();
        
        setLayout(new BorderLayout());
        
        //adding a button for the Victoria Falls youtube video
        JPanel northPanel = new JPanel();
        JButton videoButton = new JButton("Victoria Falls Video");
        videoButton.addActionListener(e -> openYouTubeVideo("https://www.youtube.com/watch?v=H0LG5rOo_9w"));
        northPanel.add(videoButton);
        northPanel.setPreferredSize(new Dimension(100,100));
        add(northPanel, BorderLayout.NORTH);
        
        //adding the image of the flag to the center panel 
        JPanel centerPanel = new JPanel();
        flagLabel.setPreferredSize(new Dimension(512, 512));
        centerPanel.add(flagLabel);
        add(centerPanel, BorderLayout.CENTER);
        
        //adding 3 random choices and the correct choice as buttons 
        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(100,200));
        for (int i = 0; i < options.length; i++) {
            options[i] = new JButton("Option " + (i + 1));
            int finalI = i;
            options[i].addActionListener(e -> checkAnswer(options[finalI].getText()));
            southPanel.add(options[i]);
        }
        
        add(southPanel, BorderLayout.SOUTH);
        
        //Starting the game 
        playGame();
    }

    //opening the youtube video in a new browser
    private void openYouTubeVideo(String url) {
        try {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(url));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to open the video: " + e.getMessage());
        }
    }

    //looping through the flags directory and creating a list of countries from the image names
    private void loadCountries() {
        File dir = new File(flagsDirectoryPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.getName().equals(".DS_Store")){  //ignoring .DS_Store
                    countries.add(file.getName().replace(".png", ""));  //removing .png from the file name 
                }
            }
        }
        Collections.shuffle(countries);  //adding the country to the array and shuffling 
    }
    
    //setting up the display 
    private void playGame() {
                
        Collections.shuffle(countries);  //shuffling countries 
        correctAnswer = countries.get(0);  //the first country will be the correct choice 
        ImageIcon icon = new ImageIcon(flagsDirectoryPath + "/" + correctAnswer + ".png");  //load the flag image 
        flagLabel.setIcon(icon);
        
        List<String> choices = new ArrayList<>(countries.subList(0, 4));  //loading the four choice for this round 
        Collections.shuffle(choices);  //shuffling the four choices
        
        //loading the four choices 
        for (int i = 0; i < options.length; i++) {
            options[i].setText(choices.get(i));
            options[i].setEnabled(true);
        }
    }
    
    //checking the users choice against the correct answer 
    private void checkAnswer(String answer) {
        if (answer.equals(correctAnswer)) {
            JOptionPane.showMessageDialog(this, "Correct!");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect. The correct answer was " + correctAnswer);
            
        }

        playGame();
    }
}
