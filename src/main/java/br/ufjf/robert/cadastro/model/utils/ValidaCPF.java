package br.ufjf.robert.cadastro.model.utils;

import br.ufjf.robert.cadastro.exceptions.CPFException;

public class ValidaCPF {

    private String CPF;
    private String CPFSemVirgula;

    public ValidaCPF(String cpf) throws CPFException {
        CPF = cpf;
        
        if(!CPF.matches("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}"))
        throw new CPFException();
        
        if(CPF.length() != 14)
        throw new CPFException();
        
        if(CPF.charAt(3) != '.' || CPF.charAt(7) != '.' || CPF.charAt(11) != '-')
        throw new CPFException();
        
        if(CPF.equals("000.000.000-00") || CPF.equals("111.111.111-11") || CPF.equals("222.222.222-22") || CPF.equals("333.333.333-33") || CPF.equals("444.444.444-44") || CPF.equals("555.555.555-55") || CPF.equals("666.666.666-66") || CPF.equals("777.777.777-77") || CPF.equals("888.888.888-88") || CPF.equals("999.999.999-99"))
        throw new CPFException();
        
        CPFSemVirgula = CPF.substring(0, 3) + CPF.substring(4, 7) + CPF.substring(8, 11) + CPF.substring(12, 14);
        verificaDigitos();
    }

    private void verificaDigitos() throws CPFException {
        int primeiroDigito = calculaDigito(9);
        if (primeiroDigito != Character.getNumericValue(CPFSemVirgula.charAt(9))) {
            throw new CPFException();
        }

        int segundoDigito = calculaDigito(10);
        if (segundoDigito != Character.getNumericValue(CPFSemVirgula.charAt(10))) {
            throw new CPFException();
        }
    }

    private int calculaDigito(int tam) {
        int soma = 0;
        
        for (int i = 0; i < tam; i++) {
            soma += (CPFSemVirgula.charAt(i) - '0') * (tam + 1 - i);
        }

        int val = soma % 11;

        return val < 2 ? 0 : 11 - val;
    }
}
