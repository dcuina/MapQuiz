import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URI;

public class ButtonFrame extends JFrame {
    public ButtonFrame() {
        setTitle("Map People Mainframe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Make the JFrame fixed size

        // Set the size of the JFrame to match the background image
        setSize(1178, 902);

        // Use our custom panel as the main content pane
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null); // Use null layout to place components absolutely

        // Create buttons
        JButton btnDaniel = new JButton("Daniel");
        JButton btnShifa = new JButton("Shifa");
        JButton btnTucker = new JButton("Tucker");
        JButton btnVideo = new JButton("Map Song");

        // Add team name
        JTextField team = new JTextField("Map People");
        team.setEditable(false);
        team.setBounds(539, 10, 70, 50);
   
        // Set bounds for each button to place them in the corners
        btnDaniel.setBounds(10, 10, 100, 75); // Top-left corner
        btnVideo.setBounds(1068, 10, 100, 75);
        btnShifa.setBounds(10, 790, 100, 75); // Bottom-left corner
        btnTucker.setBounds(1068, 790, 100, 75); // Bottom-right corner


        // Add action listeners for the buttons
        btnDaniel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AmericasMinefield panel = new AmericasMinefield();
                panel.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                panel.setLocationRelativeTo(null);
                panel.setVisible(true);
            }
        });

        btnVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    URI uri = new URI("https://www.youtube.com/watch?v=RN8-JSSVO_0&t=1s");
                    Desktop.getDesktop().browse(uri);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnShifa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EuropeMapQuiz quizGame = new EuropeMapQuiz();
                    quizGame.setVisible(true);
            }
        });

        btnTucker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open FlagQuizGame when Tucker is clicked
                SwingUtilities.invokeLater(() -> {
                    FlagQuizGame quizGame = new FlagQuizGame();
                    quizGame.setVisible(true);
                });
            }
        });
      

        // Add elements to the panel
        backgroundPanel.add(btnDaniel);
        backgroundPanel.add(btnVideo);
        backgroundPanel.add(btnShifa);
        backgroundPanel.add(btnTucker);
        backgroundPanel.add(team);

        setLocationRelativeTo(null); // Center the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ButtonFrame().setVisible(true);
            }
        });
    }

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            super();
            // Load the background image
            try {
                backgroundImage = ImageIO.read(new File("globe.png")); // Replace with your image path
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, this.getWidth(),this.getHeight(),this);
        }
    }

}
