import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Hangman extends JFrame implements ActionListener {
    private int incorrectGuesses;

    private String[] wordChallenge;

    private final WordDB wordDB;

    private JLabel hangmanImage , categoryLabel , hiddenLabel;

    private JButton[] letterButtons;

    
    public Hangman(){
        super("Hangman Game");
        setSize(CommonConstants.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(CommonConstants.BACKGROUND_COLOR);

        wordDB = new WordDB();
        letterButtons = new JButton[26];
        wordChallenge = wordDB.loadChallenge();

        addGuiComponents();
    }



    private void addGuiComponents(){
        hangmanImage= CustomTools.loadImage(CommonConstants.IMAGE_PATH);
        hangmanImage.setBounds(0,0,hangmanImage.getPreferredSize().width,hangmanImage.getPreferredSize().height);

        categoryLabel = new JLabel(wordChallenge[0]);
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setOpaque(true);
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setBackground(CommonConstants.SECONDARY_COLOR);
        categoryLabel.setBorder(BorderFactory.createLineBorder(CommonConstants.SECONDARY_COLOR));
        categoryLabel.setBounds(
            0,
            hangmanImage.getPreferredSize().height - 28,
            CommonConstants.FRAME_SIZE.width,
            categoryLabel.getPreferredSize().height
        );

        
        
        hiddenLabel = new JLabel(CustomTools.hideWords(wordChallenge[1]));
        hiddenLabel.setForeground(Color.white);
        hiddenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hiddenLabel.setBounds(
            0,
            categoryLabel.getY()+categoryLabel.getPreferredSize().height +50,
            CommonConstants.FRAME_SIZE.width,
            hiddenLabel.getPreferredSize().height
            );


            GridLayout gridLayout = new GridLayout(4,7);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBounds(
                -5,
                hiddenLabel.getY() + hiddenLabel.getPreferredSize().height,
                CommonConstants.BUTTON_PANEL_SIZE.width,
                CommonConstants.BUTTON_PANEL_SIZE.height
            );
            buttonPanel.setLayout(gridLayout);

            for(char c = 'A'; c <='Z'; c++){
                JButton button = new JButton(Character.toString(c));
                button.setBackground(CommonConstants.PRIMARY_COLOR);
                button.setForeground(Color.WHITE);
                button.addActionListener(this);

                int currentIndex = c - 'A';

                letterButtons[currentIndex] = button;
                buttonPanel.add(letterButtons[currentIndex]);
            }

            JButton resetButton = new JButton("Reset");
            resetButton.setForeground(Color.WHITE);
            resetButton.setBackground(CommonConstants.SECONDARY_COLOR);
            resetButton.addActionListener(this);
            buttonPanel.add(resetButton);

            JButton quitButton = new JButton("Quit");
            quitButton.setForeground(Color.WHITE);
            quitButton.setBackground(CommonConstants.SECONDARY_COLOR);
            quitButton.addActionListener(this);
            buttonPanel.add(quitButton);

            
            getContentPane().add(categoryLabel);
            getContentPane().add(hangmanImage);
            getContentPane().add(hiddenLabel);
            getContentPane().add(buttonPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.equals("Reset")){
            resetGame();
        }
        else if(command.equals("Quit")){
            dispose();
            return;
        }
        else{
            
        }
    }

    private void resetGame(){
        wordChallenge = wordDB.loadChallenge();
        incorrectGuesses = 0 ;

        CustomTools.updateImage(hangmanImage , CommonConstants.IMAGE_PATH);

        categoryLabel.setText(wordChallenge[0]);

        String hiddenWord = CustomTools.hideWords(wordChallenge[1]);
        hiddenLabel.setText(hiddenWord);

        for(int i = 0; i < letterButtons.length ; i++){
            letterButtons[i].setEnabled(true);
            letterButtons[i].setBackground(CommonConstants.PRIMARY_COLOR);
        }
        
    }

}
