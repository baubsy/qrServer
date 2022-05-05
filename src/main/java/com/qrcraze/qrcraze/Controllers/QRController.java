package com.qrcraze.qrcraze.Controllers;

import com.qrcraze.qrcraze.Data.QrRepository;
import com.qrcraze.qrcraze.Models.QR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class QRController {
    @Autowired
    private QrRepository qrRepository;
    QR retQR = new QR(0);
    @CrossOrigin(origins = "*")
    @GetMapping("qr")
    public QR qr(){
        retQR.incScore();
        qrRepository.save(retQR);
        return retQR;
    }
}




