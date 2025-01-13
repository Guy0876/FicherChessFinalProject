package com.example.ficherchess;

public class Presenter {

    private Model model;
    private IView view;


    public Presenter(IView controller) {
        this.model = new Model();
        this.view = controller;
    }
}

