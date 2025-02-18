import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineExaminationSystem extends JFrame implements ActionListener {
    private String subject = "Core Java";
    private String[][] questions = {
            {"What is the default value of a boolean variable in Java?", "true", "false", "null", "undefined", "2"},

            {"Which of the following is used to define a class in Java?", "def", "class", "struct", "function", "2"},

            {"What is the size of an int in Java?", "8 bytes", "16 bytes", "32 bytes", "64 bytes", "3"},

            {"Which of the following is NOT a valid identifier in Java?", "1variable", "_variable", "$variable", "variable1", "1"},

            {"Which collection type in Java does NOT allow duplicate values?", "List", "Set", "Map", "Queue", "2"},

            {"Which of the following classes is used for reading input in Java?", "FileReader", "Scanner", "BufferedReader", "InputStreamReader", "2"},

            {"What is the purpose of the 'this' keyword in Java?", "It refers to the current object", "It refers to the parent class", "It is used to define constructors", "It refers to a static method", "1"},
    };

    private int totalQuestions = questions.length;
    private int currentQuestion = 0;
    private int score = 0;
    private int timeLeft = 15; // Time per question in seconds

    private JLabel subjectLabel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionsGroup;
    private JButton nextButton, submitButton;
    private JLabel timerLabel;
    private Timer timer;

    public OnlineExaminationSystem() {
        setTitle("Online Examination System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(10));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(30));

        subjectLabel = new JLabel(subject, SwingConstants.LEFT);
        subjectLabel.setForeground(Color.CYAN);
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(subjectLabel, BorderLayout.WEST);

        timerLabel = new JLabel("Time Left: " + timeLeft + "s", SwingConstants.RIGHT);
        timerLabel.setForeground(Color.RED);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(timerLabel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(60));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        centerPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBackground(new Color(987654323));
        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBackground(new Color(23578750));
            options[i].setForeground(Color.WHITE);
            options[i].setFont(new Font("Arial", Font.PLAIN, 14));
            optionsGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        centerPanel.add(optionsPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(new Color(50, 50, 50));

        nextButton = new JButton("Next");
        nextButton.setBackground(new Color(0, 150, 0));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.addActionListener(this);
        bottomPanel.add(nextButton);

        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(200, 100, 0));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.addActionListener(this);
        bottomPanel.add(submitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        loadQuestion();
        startTimer();
        setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestion < totalQuestions) {
            questionLabel.setText("Q" + (currentQuestion + 1) + ": " + questions[currentQuestion][0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(questions[currentQuestion][i + 1]);
            }
            optionsGroup.clearSelection();
        } else {
            showResult();
        }
    }

    private void startTimer() {
        timeLeft = 15;
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                timer.stop();
                nextQuestion();
            }
        });
        timer.start();
    }

    private void nextQuestion() {
        if (timer != null) {
            timer.stop();
        }
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected() && (i + 1) == Integer.parseInt(questions[currentQuestion][5])) {
                score++;
            }
        }
        currentQuestion++;
        if (currentQuestion < totalQuestions) {
            loadQuestion();
            startTimer();
        } else {
            showResult();
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Exam Completed!\nYour Score: " + score + "/" + totalQuestions);
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            nextQuestion();
        } else if (e.getSource() == submitButton) {
            showResult();
        }
    }

    public static void main(String[] args) {
        new OnlineExaminationSystem();
    }
}
