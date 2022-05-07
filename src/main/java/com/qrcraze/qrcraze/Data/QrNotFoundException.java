package com.qrcraze.qrcraze.Data;

public class QrNotFoundException extends RuntimeException{
    public QrNotFoundException(int id){
        super("Could not find qr " + id);
    }
}
