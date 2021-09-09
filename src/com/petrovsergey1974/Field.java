package com.petrovsergey1974;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Field extends JPanel {
    private Game myGame;
    private Timer tmDraw;
    private Image fon;
    private Image ubit;
    private Image paluba;
    private Image ranen;
    private Image end1;
    private Image end2;
    private Image bomba;
    private JButton btn1;
    private JButton btn2;
    private int mX;
    private int mY;

    public class myMouse1 implements MouseListener {
        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            if ((e.getButton() == 1) && (e.getClickCount() == 1)) {
                mX = e.getX();
                mY = e.getY();
                if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
                    if ((myGame.endg == 0) && (!myGame.compHod)) {
                        int i = (mY - 100) / 30;
                        int j = (mX - 100) / 30;
                        if (myGame.masComp[i][j] <= 4)
                            myGame.vistrelPlay(i, j);
                    }
                }
            }
        }

        public void mouseReleased(MouseEvent e) {
        }


        public void mouseEntered(MouseEvent e) {
        }


        public void mouseExited(MouseEvent e) {
        }

    }

    public class myMouse2 implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            mX = e.getX();
            mY = e.getY();
            if ((mX >= 100) && (mY >= 100) && (mX <= 400) && (mY <= 400))
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            else
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public Field() {
        addMouseListener(new myMouse1());
        addMouseMotionListener(new myMouse2());
        setFocusable(true); // Передаем фокус панели

        myGame = new Game();
        myGame.start();

        try {
            fon = ImageIO.read(new File("C:\\Users\\Ольга\\Pictures\\battlesea\\fon.png"));
            paluba = ImageIO.read(new File("C:\\Users\\Ольга\\Pictures\\battlesea\\paluba.png"));
            ranen = ImageIO.read(new File("C:\\Users\\Ольга\\Pictures\\battlesea\\ranen.png"));
            ubit = ImageIO.read(new File("C:\\Users\\Ольга\\Pictures\\battlesea\\ubit.png"));
            end1 = ImageIO.read(new File("C:\\Users\\Ольга\\Pictures\\battlesea\\end1.png"));
            end2 = ImageIO.read(new File("C:\\Users\\Ольга\\Pictures\\battlesea\\end2.png"));
            bomba = ImageIO.read(new File("C:\\Users\\Ольга\\Pictures\\battlesea\\bomba.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tmDraw = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                repaint();
            }
        });
        tmDraw.start();
        setLayout(null);
        btn1 = new JButton();
        btn1.setText("Новая игра");
        btn1.setForeground(Color.BLUE);
        btn1.setFont(new Font("serif", 0, 30));
        btn1.setBounds(130, 450, 200, 80);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                myGame.start();
            }
        });
        add(btn1);
        btn2 = new JButton();
        btn2.setText("Выход");
        btn2.setForeground(Color.RED);
        btn2.setFont(new Font("serif", 0, 30));
        btn2.setBounds(530, 450, 200, 80);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        add(btn2);
    }

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        gr.drawImage(fon, 0, 0, 900, 600, null);
        gr.setFont(new Font("serif", 3, 40));
        gr.setColor(Color.BLUE);
        gr.drawString("Компьютер", 150, 50);
        gr.drawString("Игрок", 590, 50);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (myGame.masComp[i][j] != 0) {
                    if ((myGame.masComp[i][j] >= 8) && (myGame.masComp[i][j] <= 11)) {
                        gr.drawImage(ranen, 100 + j * 30, 100 + i * 30, 30, 30, null);
                    } else if (myGame.masComp[i][j] >= 15) {
                        gr.drawImage(ubit, 100 + j * 30, 100 + i * 30, 30, 30, null);
                    }
                    if (myGame.masComp[i][j] >= 5) {
                        gr.drawImage(bomba, 100 + j * 30, 100 + i * 30, 30, 30, null);
                    }
                }
                if (myGame.masPlay[i][j] != 0) {
                    if ((myGame.masPlay[i][j] >= 1) && (myGame.masPlay[i][j] <= 4)) {
                        gr.drawImage(paluba, 500 + j * 30, 100 + i * 30, 30, 30, null);
                    } else if ((myGame.masPlay[i][j] >= 8) && (myGame.masPlay[i][j] <= 11)) {
                        gr.drawImage(ranen, 500 + j * 30, 100 + i * 30, 30, 30, null);
                    } else if (myGame.masPlay[i][j] >= 15) {
                        gr.drawImage(ubit, 500 + j * 30, 100 + i * 30, 30, 30, null);
                    }
                    if (myGame.masPlay[i][j] >= 5) {
                        gr.drawImage(bomba, 500 + j * 30, 100 + i * 30, 30, 30, null);
                    }
                }
            }
        }
        gr.setColor(Color.RED); // Красный цвет
        if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
            if ((myGame.endg == 0) && (!myGame.compHod)) {
                int i = (mY - 100) / 30;
                int j = (mX - 100) / 30;
                if (myGame.masComp[i][j] <= 4)
                    gr.fillRect(100 + j * 30, 100 + i * 30, 30, 30);
            }
        }
        gr.setColor(Color.BLUE);
        for (int i = 0; i <= 10; i++) {
            gr.drawLine(100 + i * 30, 100, 100 + i * 30, 400);
            gr.drawLine(100, 100 + i * 30, 400, 100 + i * 30);
            gr.drawLine(500 + i * 30, 100, 500 + i * 30, 400);
            gr.drawLine(500, 100 + i * 30, 800, 100 + i * 30);
        }
        gr.setFont(new Font("serif", 0, 20));
        gr.setColor(Color.RED);
        for (int i = 1; i <= 10; i++) {
            gr.drawString("" + i, 73, 93 + i * 30);
            gr.drawString("" + i, 473, 93 + i * 30);
            gr.drawString("" + (char) ('A' + i - 1), 78 + i * 30, 93);
            gr.drawString("" + (char) ('A' + i - 1), 478 + i * 30, 93);
        }
        if (myGame.endg == 1) // Если победил Игрок
        {
            gr.drawImage(end1, 300, 200, 300, 100, null);
        } else if (myGame.endg == 2) // Если победил Компьютер
        {
            gr.drawImage(end2, 300, 200, 300, 100, null);
        }
    }
}


