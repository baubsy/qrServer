package com.qrcraze.qrcraze.Controllers;

import com.qrcraze.qrcraze.Data.QrRepository;
import com.qrcraze.qrcraze.Models.QR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class QRController {
    @Autowired
    private QrRepository qrRepository;
    QR retQR = new QR(0);
    @CrossOrigin(origins = "*")
    @GetMapping("qr/{qrId}")
    public QR qrGet(@PathVariable int qrId){
        Optional<QR> qr = qrRepository.findById(qrId);
        if(qr.isPresent()){
            QR qrUpdate = qr.get();
            qrUpdate.incScore();
            qrRepository.save(qrUpdate);
            return qrUpdate;
        }
        return retQR;
    }
}




