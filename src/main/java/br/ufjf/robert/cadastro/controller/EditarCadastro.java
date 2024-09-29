package br.ufjf.robert.cadastro.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufjf.robert.cadastro.view.Tela;

public class EditarCadastro implements ActionListener {

    private final Tela tela;

    public EditarCadastro(Tela tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.editCadastro();
    }
}