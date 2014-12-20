import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JButton;
import java.lang.Math;

public class Main extends JFrame {
    private int hp = 70;
    private JLabel countHp;
    private int game = 70;
    private JLabel countGame;
    private int money = 70;
    private JLabel countMoney;
    private int sleep = 70;
    private JLabel countSleep;

    public Main() {
        initComponents();
    }

    private void initComponents() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu FileMenu = new JMenu("Игра");
        menuBar.add(FileMenu);
        JMenuItem newGame = new JMenuItem("Новая игра");
        FileMenu.add(newGame);
        this.setJMenuBar(menuBar);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Main();
                Main.this.dispose();
            }
        });

        countHp = new JLabel("Сытость:" + hp);
        countGame = new JLabel("Настроение:" + game);
        countMoney = new JLabel("Деньги:" + game);
        countSleep = new JLabel("Сон:" + game);

        JButton eatButton = new JButton("  Поесть  ");
        JButton gameButton = new JButton("   Играть  ");
        JButton workButton = new JButton("Работать");
        JButton sleepButton = new JButton("   Спать   ");

        ImageIcon image = new ImageIcon("src\\images\\start.jpeg");
        final JLabel label = new JLabel(image);

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel TopPanel = new JPanel();
        TopPanel.setLayout(new BorderLayout());

        JPanel CenterPanel = new JPanel();
        CenterPanel.setLayout(new GridLayout(1, 2));

        Box pLeft = Box.createVerticalBox();
        pLeft.add(eatButton);
        pLeft.add(gameButton);
        pLeft.add(workButton);
        pLeft.add(sleepButton);

        Box pRight = Box.createVerticalBox();

        JLabel str1 = new JLabel("Восстанавливает здоровье");
        JLabel str2 = new JLabel("Повышает настроение");
        JLabel str3 = new JLabel("Получите немного денег");
        JLabel str4 = new JLabel("Дает вам сил для дел");

        pRight.add(Box.createVerticalStrut(5));
        pRight.add(str1);
        pRight.add(Box.createVerticalStrut(10));
        pRight.add(str2);
        pRight.add(Box.createVerticalStrut(10));
        pRight.add(str3);
        pRight.add(Box.createVerticalStrut(10));
        pRight.add(str4);

        CenterPanel.add(pLeft);
        CenterPanel.add(pRight);

        JPanel BottomPanel = new JPanel();
        BottomPanel.setLayout(new FlowLayout());

        TopPanel.add(label, BorderLayout.NORTH);

        BottomPanel.add(countHp);
        BottomPanel.add(countGame);
        BottomPanel.add(countMoney);
        BottomPanel.add(countSleep);

        panel.add(TopPanel, BorderLayout.NORTH);
        panel.add(CenterPanel);
        panel.add(BottomPanel, BorderLayout.SOUTH);

        eatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (hp==100) {
                    JOptionPane.showMessageDialog(Main.this, "Вы наелись!");
                }
                if ((hp < 100) && (hp > 0)) {
                    upgradeHp();
                }
                checkLife(panel,label);
            }
        });
        gameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (game==100) {
                    JOptionPane.showMessageDialog(Main.this, "У Вас отличное настроение!");
                }
                if ((game < 100) && (game > 0)) {
                    upgradeGame();
                }
                checkLife(panel,label);
            }
        });
        workButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                upgradeWork();
                checkLife(panel,label);
            }
        });
        sleepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sleep == 100) {
                    JOptionPane.showMessageDialog(Main.this, "Вы хорошо выспались!");
                }
                if ((sleep < 100) && (sleep >0)) {
                    upgradeSleep();
                }
                checkLife(panel,label);
            }
        });
        this.add(panel);
        this.setVisible(true);
        this.pack();

    }
    private void upgradeHp() {
        hp += (int) (Math.random() * 20 + 5);
        if (hp > 100)
            hp = 100;
        countHp.setText("Здоровье:" + hp);
        downgradeSleep();
        downgradeGame();
        downgradeMoney();
    }

    private void upgradeGame() {
        game += (int) (Math.random() * 15 + 5);
        if (game > 100)
            game = 100;
        countGame.setText("Настроение:" + game);
        downgradeHp();
        downgradeSleep();
    }

    private void upgradeWork() {
        money += (int) (Math.random() * 15 + 5);
        countMoney.setText("Деньги:" + money);
        downgradeHp();
        downgradeSleep();
        downgradeGame();
    }

    private void upgradeSleep() {
        sleep +=  (int) (Math.random() * 30 + 15);
        if (sleep > 100)
            sleep = 100;
        countSleep.setText("Сон:" + sleep);
        downgradeHp();
        downgradeGame();
    }

    private void downgradeHp() {
        hp -= (int) (Math.random() * 5 + 5);
        countHp.setText("Здоровье:" + hp);
    }

    private void downgradeGame() {
        game -= (int) (Math.random() * 5 + 5);
        countGame.setText("Настроение:" + game);
    }

    private void downgradeMoney() {
        money -=  7;
        countMoney.setText("Деньги:" + money);
    }

    private void downgradeSleep() {
        sleep -= (int) (Math.random() * 5 + 5);
        countSleep.setText("Сон:" + sleep);
    }
    private void checkLife(JPanel panel, JLabel label) {
        if ((hp <= 0) || (money <= 0) || (sleep <= 0) || (game <= 0)) {
            Main.this.remove(panel);
            Main.this.remove(label);
            Main.this.revalidate();
            Main.this.repaint();
            ImageIcon image = new ImageIcon("src//images//gameover.png");
            label = new JLabel(image);
            JLabel str = new JLabel("Вы проиграли");
            Box gameover = Box.createVerticalBox();
            gameover.add(label);
            gameover.add(str);
            Main.this.add(gameover);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}