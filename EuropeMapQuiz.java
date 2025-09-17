import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EuropeMapQuiz extends JFrame {

    private JLabel questionLabel;
    public ArrayList<String> countriesmap = new ArrayList<>();

    private JButton[] optionButtons;
    private String[] options = {"portugal", "spain", "france", "italy", "germany", "poland", "russia", "ireland", "united kingdom", "ukraine","iceland", "norway", "sweden", "finland", "turkey", "switzerland","greece","romania"};
    private String correctOption;
    private JLabel mapLabel;

    private final String countriesDirectoryPath = "./countries"; // Path to the flags directory

    public EuropeMapQuiz() {

        countriesmappics();

        setLayout(new BorderLayout());
        setTitle("Europe Map Quiz");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questionLabel = new JLabel("Which country is pinned?");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER); 

        
        add(questionLabel, BorderLayout.NORTH);

        optionButtons = new JButton[18];    
        JPanel optionsPanel = new JPanel(new GridLayout(3, 6));
        for (int i = 0; i < options.length; i++) {
            optionButtons[i] = new JButton(options[i]);
            optionButtons[i].addActionListener(new OptionButtonListener());
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.SOUTH);

        mapLabel = new JLabel();
        add(mapLabel, BorderLayout.CENTER);

        mapselect();
    }

    private void countriesmappics() {
        //pick a random image from countries folder and keep the matched name of it
        File dir = new File(countriesDirectoryPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.getName().equals(".DS_Store")) {  
                    countriesmap.add(file.getName().replace(".png", ""));  
                }
            }
        }
        
        Collections.shuffle(countriesmap);
    }

    private void mapselect() {
        //pick a random image of country and show user to guess what country it is
        Random random = new Random();
        int index = random.nextInt(countriesmap.size());
        correctOption = countriesmap.get(index);

        ImageIcon mapIcon = new ImageIcon(countriesDirectoryPath + "/" + correctOption + ".png");
        mapLabel.setIcon(mapIcon);
        Image originalImage = mapIcon.getImage();
        int newWidth = 900; 
        int newHeight = 800; 
    
        // learned from stack overflow - help scale image
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        ImageIcon scaledMapIcon = new ImageIcon(scaledImage);
        
        mapLabel.setIcon(scaledMapIcon);
        

    }

    private class OptionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //tell user if they are right or wrong
            JButton buttonClicked = (JButton) e.getSource();
            if (buttonClicked.getText().equals(correctOption)) {
                JOptionPane.showMessageDialog(null, "Correct!");
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect. The country pinned is " + correctOption);
            }
            mapselect();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EuropeMapQuiz().setVisible(true);
        });
    }
}