import java.awt.Color;
import javax.swing.*;

public class Hangman extends JFrame {
    private int incorrectGuesses;

    private String[] wordChallenge;

    private final WordDB wordDB;

    private JLabel hangmanImage , categoryLabel , hiddenLabel;


    
    public Hangman(){
        super("Hangman Game");
        setSize(CommonConstants.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        wordDB = new WordDB();
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
        hiddenLabel.setBounds(
            0,
            categoryLabel.getY()+categoryLabel.getPreferredSize().height +50,
            CommonConstants.FRAME_SIZE.width,
            hiddenLabel.getPreferredSize().height
            );
            
            getContentPane().add(categoryLabel);
            getContentPane().add(hangmanImage);
    }


}
