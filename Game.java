import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Game extends JFrame {
    private JPanel gamePanel;// Панель на которой будет отображаться шар
    private JButton startButton, ballButton;// Кнопки запуска игры и кнопки шара
    private JLabel timerLabel, scoreLabel;// метки для отображения времени игры и набранных очков
    private JComboBox<String> modeComboBox;//  выпадающий список для выбора уровня сложности
    private Timer timer, ballMoveTimer;// таймеры для отслеживания времени игры и перемещения шара
    public int score, ballSize = 50, delay = 5000;//  score - количество набранных очков, размер шара и задержка его перемещения по умолчанию
    private int time = 0; // время с начала игры (тут в секундах delay сверху в милисекундах)
    private int currentMode;
    private boolean isRedBall = true;
    private boolean ballWasPressed = false;

    public Game() {// конструктор
        setTitle("Game"); // задаем название окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// эта строка определяет то что будет делать программа после закрытия окна (в данном случае она прекратит работу)
        setSize(2400, 1800);// размеры окна
        setLocationRelativeTo(null);// центрирование окна

        gamePanel = new JPanel(null);// Панель для отображения поля
        startButton = new JButton("Start");
        timerLabel = new JLabel("Time: 0");
        scoreLabel = new JLabel("Score: 0");
        modeComboBox = new JComboBox<>(new String[]{"Easy", "Medium", "Hard", "Color Switch"});
        // Тут создали кнопки и выпадающий список для уровня выбора уровня сложности
        setupComponents();
        setupTimers();
        // Эти две строчки нужны чтобы добавить все что было создано на панель
    }

    private void setupComponents() {
        int baseFontSize = 70;// Задаем размер шрифта
        startButton.setFont(new Font("Arial", Font.BOLD, baseFontSize));
        timerLabel.setFont(new Font("Arial", Font.PLAIN, baseFontSize));
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, baseFontSize));
        modeComboBox.setFont(new Font("Arial", Font.PLAIN, baseFontSize));
        // В 4 строках выше установили шрифт "Arial" , стиль шрифта обычный или жирный , а также размер шрифта для соответствующих кнопок.\
        startButton.setPreferredSize(new Dimension(300, 200));
        modeComboBox.setPreferredSize(new Dimension(300, 100));
        // Тут устанавливаются начальные размеры для выпадающегосписка и кнопки старт
        startButton.addActionListener(e -> startGame());
        // Это означает что при нажатии кнопки старт будет вызван метод StartGame()
        modeComboBox.addActionListener(e -> setMode(modeComboBox.getSelectedIndex()));
        // Это означает что при изменении выбранного режима будет вызван метод setMode(modeComboBox.getSelectedIndex())

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // Увеличиваем отступы и создаем новую панель
        topPanel.add(startButton);
        topPanel.add(timerLabel);
        topPanel.add(scoreLabel);
        // Добавляем на нее соответствующую кнопку и 2 метки
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // Создаем еще одну панель
        bottomPanel.add(new JLabel());
        // Тут можно подписать что эта штука для выбора режима
        bottomPanel.add(modeComboBox);// Добавляем на нее выпадающий список

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(gamePanel, BorderLayout.CENTER);
        // Данные панели добавляются на текущую панель и их положение на ней определяется с помощью второго переданного параметра.
        // Например BorderLayout.CENTER означает что gamePanel по центру

        gamePanel.addMouseListener(new MouseAdapter() { // Если кликаем вне зоны шара то отнимаются очки
            public void mouseClicked(MouseEvent e) {
                if (!ballButton.getBounds().contains(e.getPoint())) {
                    if (currentMode != 3 || !isRedBall) {
                        score--;
                        scoreLabel.setText("Score: " + score);
                    }
                }
            }
        });
    }


    private void setupTimers() {
        timer = new Timer(1000, e -> {
            time++;
            timerLabel.setText("Time: " + time);
        });
        // Создали счетчик времени

        ballMoveTimer = new Timer(delay, e -> {
            if (isRedBall && !ballWasPressed) {
                score--;
            }
            ballWasPressed = false;
            moveBall();
            scoreLabel.setText("Score: " + score);
        });
        // Если шар красный и не был нажат уменьшаем счет
    }

    private void startGame() {
        score = 0;
        time = 0;
        // Сброс времени и счета
        timerLabel.setText("Time: 0");
        scoreLabel.setText("Score: 0");
        //  Сброс метки времени и метки счета
        timer.start();
        ballMoveTimer.start();
        // Запуск таймеров времени и перемещения шара
        setMode(modeComboBox.getSelectedIndex());
        // Выбор размера шара (на основе текущего выбранного в выпадающем списке)
        moveBall();
        // Начально рандомное размещение шара
    }

    private void setMode(int mode) {
        switch (mode) {
            case 0: ballSize = 200; delay = 15000; break;
            case 1: ballSize = 150; delay = 8000; break;
            case 2: ballSize = 100; delay = 4000; break;
            case 3: ballSize = 120; delay = 5000; break;
        }
        ballMoveTimer.setDelay(delay);
        currentMode = mode;
    }
    //Тут выбирается размер шарика в соответствии с выбранным режимом игры.

    private void moveBall() {
        if (ballButton != null) {
            gamePanel.remove(ballButton);
        }
        // Проверка существует ли уже шар или нет,если да то удаляем, нужно чтобы не появлялось больше одного шара
        Random rand = new Random();
        int x = rand.nextInt(gamePanel.getWidth() - ballSize);
        int y = rand.nextInt(gamePanel.getHeight() - ballSize);
        // Генерируются рандомные координаты шара (вычитается ballsize для того чтобы шар всегда отображался в игровой области целиком)
        ballButton = new CircleButton();
        // Создание шара
        if (currentMode == 3) {
            isRedBall = rand.nextBoolean();
            ballButton.setForeground(isRedBall ? Color.RED : Color.BLACK);
        } else {
            ballButton.setForeground(Color.RED);
            isRedBall = true;
        }
        // Если мы в режиме "Color Switch" (currentMode == 3), то цвет шара выбирается случайно между красным и черным. Иначе, цвет шара .

        ballButton.addActionListener(e -> {
            ballWasPressed = true;
            if (isRedBall) {
                score++;
            } else {
                score--;
            }
            scoreLabel.setText("Score: " + score);
            ballMoveTimer.stop();
            moveBall();
            ballMoveTimer.start();
        });
        // Определение кликнули на красный или на черный шар и в зависмости от этого манипуляции со счетом

        ballButton.setBounds(x, y, ballSize, ballSize);
        ballButton.setBorderPainted(false);
        ballButton.setContentAreaFilled(false);
        // Установка размера и положения кнопки, установка границ и фона прозрачными.
        gamePanel.add(ballButton);
        gamePanel.repaint();
        //Добавление на экран
    }

    static class CircleButton extends JButton {
        private Color color;

        public void setForeground(Color color) {
            this.color = color;
            super.setForeground(color);
        }//Установка цвета фона кнопки
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        }// Отрисовка круга на кнопке
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game().setVisible(true));
    }
}
