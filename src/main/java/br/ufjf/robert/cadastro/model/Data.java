package br.ufjf.robert.cadastro.model;

import br.ufjf.robert.cadastro.exceptions.DataException;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public static Data parser(String data) throws DataException {

        if (!data.contains("/")) {
            throw new DataException();
        }

        String[] splitData = data.split("/");

        if ((splitData[0].length() != 1 && splitData[0].length() != 2)
                || (splitData[1].length() != 1 && splitData[1].length() != 2)
                || splitData[2].length() != 4 || splitData.length != 3
                || (Integer.parseInt(splitData[1]) < 1 || Integer.parseInt(splitData[1]) > 12)) {

            throw new DataException();
        }

        int dd = Integer.parseInt(splitData[0]);
        int mm = Integer.parseInt(splitData[1]);
        int aa = Integer.parseInt(splitData[2]);

        // Fevereiro
        if (mm == 2) {
            if (dd > 29) {
                throw new DataException();
            }

            // Ano bissexto
            if (dd == 29 && aa % 4 != 0) {
                throw new DataException();
            }
        }

        // Meses com 30 dias
        if (mm == 4 || mm == 6 || mm == 9 || mm == 11) {
            if (dd > 30) {
                throw new DataException();
            }
        }

        // Meses com 31 dias
        if (mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
            if (dd > 31) {
                throw new DataException();
            }
        }

        return new Data(dd, mm, aa);
    }
}
