package br.ufjf.robert.cadastro.controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ufjf.robert.cadastro.view.Tela;

public class SelecionarCadastro implements ListSelectionListener {

    private final Tela tela;

    public SelecionarCadastro(Tela tela) {
        this.tela = tela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Valor ajustando: " + e.getValueIsAdjusting());
        tela.atualizaFormulario(); 
    }

}
