import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsGame extends JFrame implements ActionListener {

    // GUI components
    private JLabel titleLabel, userChoiceLabel, computerChoiceLabel, resultLabel;
    private JButton rockButton, paperButton, scissorsButton;
    private JLabel userImageLabel, computerImageLabel;

    // Icons for choices
    private ImageIcon rockIcon = new ImageIcon("rock.png");
    private ImageIcon paperIcon = new ImageIcon("paper.png");
    private ImageIcon scissorsIcon = new ImageIcon("scissors.png");

    public RockPaperScissorsGame() {
        // Set up the frame
        setTitle("Rock Paper Scissors");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        titleLabel = new JLabel("Rock Paper Scissors", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Main panel for buttons and images
        JPanel mainPanel = new JPanel(new GridLayout(2, 3));
        add(mainPanel, BorderLayout.CENTER);

        // User choice
        userImageLabel = new JLabel();
        userImageLabel.setHorizontalAlignment(JLabel.CENTER);
        userChoiceLabel = new JLabel("Your Choice", JLabel.CENTER);
        mainPanel.add(userImageLabel);
        mainPanel.add(new JLabel("VS", JLabel.CENTER)); // Center text for "VS"
        computerImageLabel = new JLabel();
        computerImageLabel.setHorizontalAlignment(JLabel.CENTER);

        computerChoiceLabel = new JLabel("Computer's Choice", JLabel.CENTER);
        mainPanel.add(userChoiceLabel);
        mainPanel.add(computerImageLabel);

        // Buttons for user input
        JPanel buttonPanel = new JPanel(new FlowLayout());
        add(buttonPanel, BorderLayout.SOUTH);

        rockButton = new JButton("Rock", rockIcon);
        paperButton = new JButton("Paper", paperIcon);
        scissorsButton = new JButton("Scissors", scissorsIcon);

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        // Result Label
        resultLabel = new JLabel("Make your choice!", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(resultLabel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int computerChoice = random.nextInt(3);

        String userChoice = "";
        if (e.getSource() == rockButton) userChoice = "Rock";
        else if (e.getSource() == paperButton) userChoice = "Paper";
        else if (e.getSource() == scissorsButton) userChoice = "Scissors";

        // Update images
        userImageLabel.setIcon(getIcon(userChoice));
        computerImageLabel.setIcon(getIcon(choices[computerChoice]));

        // Game logic
        String result = determineWinner(userChoice, choices[computerChoice]);
        resultLabel.setText(result);
    }

    private ImageIcon getIcon(String choice) {
        switch (choice) {
            case "Rock":
                return rockIcon;
            case "Paper":
                return paperIcon;
            case "Scissors":
                return scissorsIcon;
            default:
                return null;
        }
    }

    private String determineWinner(String user, String computer) {
        if (user.equals(computer)) {
            return "It's a Tie!";
        } else if ((user.equals("Rock") && computer.equals("Scissors")) ||
                   (user.equals("Paper") && computer.equals("Rock")) ||
                   (user.equals("Scissors") && computer.equals("Paper"))) {
            return "You Win!";
        } else {
            return "You Lose!";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsGame game = new RockPaperScissorsGame();
            game.setVisible(true);
        });
    }
}
