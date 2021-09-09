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

    /*private void make1P(int[][] mas) {
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
*/
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
        masComp[i][j] += 7;
        testUbit(masComp, i, j);
        testEndGame();
        if (masComp[i][j] < 8) {
            compHod = true; // передаем ход компьютеру
            while (compHod == true) compHod = compHodit();
        }
    }

    private void testEndGame() {
        int testNumber = 330;
        int kolComp = 0; // Сумма убитых палуб компьютера
        int kolPlay = 0; // Сумма убитых палуб игрока
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (masPlay[i][j] >= 15) {
                    kolPlay += masPlay[i][j];
                }
                if (masComp[i][j] >= 15) {
                    kolComp += masComp[i][j];
                }
            }
        }
        if (kolPlay == testNumber) {
            endg = 2; // Если победил игрок
        } else if (kolComp == testNumber) {
            endg = 1; // Если победил компьютер
        }
    }

    private void setOkrPodbit(int[][] mas, int i, int j) {
        if (testMasPoz(i, j) == true) {
            if ((mas[i][j] == -1) || (mas[i][j] == 6)) {
                mas[i][j]--;
            }
        }
    }

    private void okrPodbit(int[][] mas, int i, int j) {
        setOkrPodbit(mas, i - 1, j - 1); // сверху слева
        setOkrPodbit(mas, i - 1, j); // сверху
        setOkrPodbit(mas, i - 1, j + 1); // сверху справа
        setOkrPodbit(mas, i, j + 1); // справа
        setOkrPodbit(mas, i + 1, j + 1); // снизу справа
        setOkrPodbit(mas, i + 1, j); // снизу
        setOkrPodbit(mas, i + 1, j - 1); // снизу слева
        setOkrPodbit(mas, i, j - 1); // слева
    }

    private boolean compHodit() {
        boolean rez = false;
        boolean flag = false;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((masPlay[i][j] >= 9) && (masPlay[i][j] <= 11)) {
                    flag = true;
                    if (testMasPoz(i - 1, j) && (masPlay[i - 1][j] <= 4) && (masPlay[i - 1][j] != -2)) {
                        masPlay[i - 1][j] += 7;
                        testUbit(masPlay, i - 1, j);
                        if (masPlay[i - 1][j] >= 8) rez = true;
                        break;
                    } else if (testMasPoz(i + 1, j) && (masPlay[i + 1][j] <= 4) && (masPlay[i + 1][j] != -2)) {
                        masPlay[i + 1][j] += 7;
                        testUbit(masPlay, i + 1, j);
                        if (masPlay[i + 1][j] >= 8) rez = true;
                        break;
                    }
                    if (testMasPoz(i, j - 1) && (masPlay[i][j - 1] <= 4) && (masPlay[i][j - 1] != -2)) {
                        masPlay[i][j - 1] += 7;
                        testUbit(masPlay, i, j - 1);
                        if (masPlay[i][j - 1] >= 8) rez = true;
                        break;
                    } else if (testMasPoz(i, j + 1) && (masPlay[i][j + 1] <= 4) && (masPlay[i][j + 1] != -2)) {
                        masPlay[i][j + 1] += 7;
                        testUbit(masPlay, i, j + 1);
                        if (masPlay[i][j + 1] >= 8) rez = true;
                        break;
                    }
                }
            }
        }
        if (flag == false) {
            for (int l = 1; l <= 100; l++) {
                int i = (int) (Math.random() * 10);
                int j = (int) (Math.random() * 10);
                if ((masPlay[i][j] <= 4) && (masPlay[i][j] != -2)) {
                    masPlay[i][j] += 7;
                    testUbit(masPlay, i, j);
                    if (masPlay[i][j] >= 8)
                        rez = true;
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if ((masPlay[i][j] <= 4) && (masPlay[i][j] != -2)) {
                            masPlay[i][j] += 7;
                            testUbit(masPlay, i, j);
                            if (masPlay[i][j] >= 8)
                                rez = true;
                            break;
                        }
                    }
                }
            }
        }
        testEndGame();
        return rez;
    }

    private void testUbit(int[][] mas, int i, int j) {
        if (mas[i][j] == 8) {
            mas[i][j] += 7;
            okrPodbit(mas, i, j);
        } else if (mas[i][j] == 9) analizUbit(mas, i, j, 2);
        else if (mas[i][j] == 10) analizUbit(mas, i, j, 3);
        else if (mas[i][j] == 11) analizUbit(mas, i, j, 4);
    }

    private void analizUbit(int[][] mas, int i, int j, int kolPalub) {
        int kolRanen = 0;
        for (int k = i - (kolPalub - 1); k <= i + (kolPalub - 1); k++) {
            for (int g = j - (kolPalub - 1); g <= j + (kolPalub - 1); g++) {
                if (testMasPoz(k, g) && (mas[k][g] == kolPalub + 7)) kolRanen++;
            }
        }

        if (kolRanen == kolPalub) {
            for (int k = i - (kolPalub - 1); k <= i + (kolPalub - 1); k++) {
                for (int g = j - (kolPalub - 1); g <= j + (kolPalub - 1); g++) {
                    if (testMasPoz(k, g) && (mas[k][g] == kolPalub + 7)) {
                        mas[k][g] += 7;
                        okrPodbit(mas, k, g);
                    }
                }
            }
        }
    }
}

