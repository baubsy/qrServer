package com.qrcraze.qrcraze.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class QR {
    @Id
    @GeneratedValue
    private int id;
    private int score;

    public QR(){}
    public QR(int score){
        this.score = 0;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incScore(){
        this.score++;
    }
}
