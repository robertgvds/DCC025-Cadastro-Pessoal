package br.ufjf.robert.cadastro.model;

import br.ufjf.robert.cadastro.exceptions.CPFException;
import br.ufjf.robert.cadastro.exceptions.DataException;
import br.ufjf.robert.cadastro.model.utils.ValidaCPF;

public class Cadastro {
    private String nome;
    private String CPF;
    private String dataNascimento;
    private int idade;

    public Cadastro(String nome, String CPF, String dataNascimento) throws DataException, CPFException {
        new ValidaCPF(CPF);
        
        this.nome = nome;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        this.idade = calcularIdade();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) throws CPFException {
        new ValidaCPF(CPF);
        this.CPF = CPF;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) throws DataException {
        this.dataNascimento = dataNascimento;
        this.idade = calcularIdade();
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(String dataNascimento) throws DataException {
        this.idade = calcularIdade();
    }

    private int calcularIdade() throws DataException {
        Data dataNasc = Data.parser(this.dataNascimento);
        Data dataAtual = new Data(28, 9, 2024);

        if (dataNasc.getAno() > dataAtual.getAno()
                || (dataNasc.getAno() == dataAtual.getAno() && dataNasc.getMes() > dataAtual.getMes())
                || (dataNasc.getAno() == dataAtual.getAno() && dataNasc.getMes() == dataAtual.getMes()
                        && dataNasc.getDia() > dataAtual.getDia())) {
            throw new DataException();
        }

        if (dataNasc.getMes() > dataAtual.getMes()
                || (dataNasc.getMes() == dataAtual.getMes() && dataNasc.getDia() > dataAtual.getDia())) {
            return dataAtual.getAno() - dataNasc.getAno() - 1;
        }

        return dataAtual.getAno() - dataNasc.getAno();
    }
}
