package com.petrovsergey1974;

public class Game {
    public int[][] masPlay;
    public int[][] masComp;
    public boolean compHod;
    public int endg;


    public Game() {
        masPlay = new int[10][10];
        masComp = new int[10][10];
    }

    public void start() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                masPlay[i][j] = 0;
                masComp[i][j] = 0;
            }
        }
        endg = 0;
        compHod = false;
        rasstanovkaKorabley(masPlay);
        rasstanovkaKorabley(masComp);
    }

    private void rasstanovkaKorabley(int[][] mas) {
        make4P(mas, 4);
        make4P(mas, 3);
        make4P(mas, 3);
        make4P(mas, 2);
        make4P(mas, 2);
        make4P(mas, 2);
        make4P(mas, 1);
        make4P(mas, 1);
        make4P(mas, 1);
        make4P(mas, 1);
    }

    private boolean testMasPoz(int i, int j) {
        if (((i >= 0) && (i <= 9)) && ((j >= 0) && (j <= 9))) {
            return true;
        } else
            return false;
    }

    private void setMasValue(int[][] mas, int i, int j, int val) {
        if (testMasPoz(i, j)) {
            mas[i][j] = val;
        }
    }

    private void setOkr(int[][] mas, int i, int j, int val) {
        if (testMasPoz(i, j) && (mas[i][j] == 0))
            setMasValue(mas, i, j, val);

    }

    private void okrBegin(int[][] mas, int i, int j, int val) {
        setOkr(mas, i - 1, j - 1, val);
        setOkr(mas, i - 1, j, val);
        setOkr(mas, i - 1, j + 1, val);
        setOkr(mas, i, j + 1, val);
        setOkr(mas, i + 1, j + 1, val);
        setOkr(mas, i + 1, j, val);
        setOkr(mas, i + 1, j - 1, val);
        setOkr(mas, i, j - 1, val);

    }

    private void make1P(int[][] mas) {
        for (int k = 1; k <= 4; k++) {
            while (true) {
                int i = (int) (Math.random() * 10);
                int j = (int) (Math.random() * 10);
                if (mas[i][j] == 0) {
                    mas[i][j] = 1;
                    okrBegin(mas, i, j, -1);
                    break;
                }
            }
        }
    }

    private void okrEnd(int[][] mas) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (mas[i][j] == -2)
                    mas[i][j] = -1;
            }
        }
    }

    private boolean testNewPaluba(int[][] mas, int i, int j) {
        if (!testMasPoz(i, j)) {
            return false;
        }
        if ((mas[i][j] == 0) || (mas[i][j] == -2)) {
            return true;
        }
        return false;
    }

    private void make4P(int[][] mas, int kolPaluba) {
        while (true) {
            boolean flag = false;
            int i = 0, j = 0;
            i = (int) (Math.random() * 10);
            j = (int) (Math.random() * 10);
            int napr = (int) (Math.random() * 4);
            if (testNewPaluba(mas, i, j) == true) {
                if (napr == 0) {
                    if (testNewPaluba(mas, i - (kolPaluba - 1), j) == true)
                        flag = true;
                } else if (napr == 1) {
                    if (testNewPaluba(mas, i, j + (kolPaluba - 1)) == true)
                        flag = true;
                } else if (napr == 2) {
                    if (testNewPaluba(mas, i + (kolPaluba - 1), j) == true)
                        flag = true;
                } else if (napr == 3) {
                    if (testNewPaluba(mas, i, j - (kolPaluba - 1)) == true)
                        flag = true;
                }
            }
            if (flag == true) {
                mas[i][j] = kolPaluba;
                okrBegin(mas, i, j, -2);
                if (napr == 0) {
                    for (int k = kolPaluba - 1; k >= 1; k--) {
                        mas[i - k][j] = kolPaluba;
                        okrBegin(mas, i - k, j, -2);
                    }
                } else if (napr == 1) {
                    for (int k = kolPaluba - 1; k >= 1; k--) {
                        mas[i][j + k] = kolPaluba;
                        okrBegin(mas, i, j + k, -2);
                    }
                } else if (napr == 2) {
                    for (int k = kolPaluba - 1; k >= 1; k--) {
                        mas[i + k][j] = kolPaluba;
                        okrBegin(mas, i + k, j, -2);
                    }
                } else if (napr == 3) {
                    for (int k = kolPaluba - 1; k >= 1; k--) {
                        mas[i][j - k] = kolPaluba;
                        okrBegin(mas, i, j - k, -2);
                    }
                }
                break;
            }
        }
        okrEnd(mas);
    }

    public void vistrelPlay(int i, int j) {

    }
}
