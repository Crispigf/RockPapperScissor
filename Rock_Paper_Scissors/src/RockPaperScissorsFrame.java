import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame{

    JPanel mainPnl;
    JPanel topPnl;
    JPanel middlePnl;
    JPanel bottomPnl;

    JLabel pWinLbl;
    JLabel cWinLbl;
    JLabel tiesLbl;
    JTextField pWinFld;
    JTextField cWinFld;
    JTextField tiesFld;

    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;
    JButton quitBtn;
    ImageIcon rockIcon;
    ImageIcon paperIcon;
    ImageIcon scissorsIcon;
    ImageIcon quitIcon;

    JTextArea resultTA;
    JScrollPane scroller;

    JLabel title;

    int playerWins = 0;
    int computerWins = 0;
    int ties = 0;

    public RockPaperScissorsFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        title = new JLabel("ROCK, PAPER, SCISSORS");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalTextPosition(JLabel.CENTER);
        mainPnl.add(title, BorderLayout.NORTH);

        createTopPanel();
        mainPnl.add(topPnl, BorderLayout.CENTER);

        createMiddlePanel();
        middlePnl.setBorder(new LineBorder(Color.BLACK, 2));
        mainPnl.add(middlePnl, BorderLayout.SOUTH);

        createBottomPanel();
        mainPnl.add(bottomPnl, BorderLayout.EAST);

        add(mainPnl);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Add action listeners for gesture buttons
        rockBtn.addActionListener((ActionEvent ae) -> playGame("Rock"));
        paperBtn.addActionListener((ActionEvent ae) -> playGame("Paper"));
        scissorsBtn.addActionListener((ActionEvent ae) -> playGame("Scissors"));

        // Add action listener for the Quit button
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
    }

    private void playGame(String playerChoice) {
        // Generate a random choice for the computer
        String[] choices = {"Rock", "Paper", "Scissors"};
        String computerChoice = choices[new Random().nextInt(choices.length)];

        // Determine the game result
        String result = determineGameResult(playerChoice, computerChoice);

        // Update the statistics and JTextArea
        updateStatistics(result);
        updateTextArea(result);
    }

    private String determineGameResult(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            ties++;
            return "Tie";
        } else if (playerChoice.equals("Rock") && computerChoice.equals("Scissors")) {
            playerWins++;
            return playerChoice + " breaks " + computerChoice + " (Player wins)";
        } else if (playerChoice.equals("Paper") && computerChoice.equals("Rock")) {
            playerWins++;
            return playerChoice + " Covers " + computerChoice + " (Player wins)";
        } else if (playerChoice.equals("Scissors") && computerChoice.equals("Paper")) {
            playerWins++;
            return playerChoice + " Cuts " + computerChoice + " (Player wins)";
        } else if (computerChoice.equals("Rock") && playerChoice.equals("Scissors")) {
            computerWins++;
            return computerChoice + " breaks " + playerChoice + " (Computer wins)";
        } else if (computerChoice.equals("Paper") && playerChoice.equals("Rock")) {
            computerWins++;
            return computerChoice + " Covers " + playerChoice + " (Computer wins)";
        } else if (computerChoice.equals("Scissors") && playerChoice.equals("Paper")) {
            computerWins++;
            return computerChoice + " Cuts " + playerChoice + " (Computer wins)";
        }
        return "wrong";

    }

    private void updateStatistics(String result) {
        if (result.contains("Player wins")) {
            pWinFld.setText(Integer.toString(playerWins));
        } else if (result.contains("Computer wins")) {
            cWinFld.setText(Integer.toString(computerWins));
        } else {
            tiesFld.setText(Integer.toString(ties));
        }
    }

    private void updateTextArea(String result) {
        resultTA.append(result + "\n");
    }


    public void createTopPanel(){
        topPnl = new JPanel();
        topPnl.setLayout(new GridLayout(3, 2));

        pWinLbl = new JLabel("Player Wins:");
        cWinLbl = new JLabel("Computer Wins:");
        tiesLbl = new JLabel("Ties:");

        topPnl.add(pWinLbl);
        topPnl.add(pWinFld = new JTextField());
        topPnl.add(cWinLbl);
        topPnl.add(cWinFld = new JTextField());
        topPnl.add(tiesLbl);
        topPnl.add(tiesFld = new JTextField());

        pWinFld.setEditable(false);
        cWinFld.setEditable(false);
        tiesFld.setEditable(false);

    }

    public void createMiddlePanel(){
        middlePnl = new JPanel();
        middlePnl.setLayout(new FlowLayout());

        rockIcon = new ImageIcon("src/stone.png");
        paperIcon = new ImageIcon("src/paper.png");
        scissorsIcon = new ImageIcon("src/scissor.png");
        quitIcon = new ImageIcon("src/exit.png");

        rockIcon = new ImageIcon(rockIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        paperIcon = new ImageIcon(paperIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        scissorsIcon = new ImageIcon(scissorsIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        quitIcon = new ImageIcon(quitIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        rockBtn = new JButton(rockIcon);
        paperBtn = new JButton(paperIcon);
        scissorsBtn = new JButton(scissorsIcon);
        quitBtn = new JButton(quitIcon);

        rockBtn.setPreferredSize(new Dimension(50, 50));
        paperBtn.setPreferredSize(new Dimension(50, 50));
        scissorsBtn.setPreferredSize(new Dimension(50, 50));
        quitBtn.setPreferredSize(new Dimension(50, 50));

        middlePnl.add(rockBtn);
        middlePnl.add(paperBtn);
        middlePnl.add(scissorsBtn);
        middlePnl.add(quitBtn);

        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
    }

    public void createBottomPanel()
    {
        bottomPnl =new JPanel();
        resultTA = new JTextArea(10,20);
        resultTA.setEditable(false);
        scroller = new JScrollPane(resultTA);
        bottomPnl.add(scroller);
    }


}
