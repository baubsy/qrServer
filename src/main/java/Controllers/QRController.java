package Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class QRController {
    private int tempCounter = 0;
    @GetMapping("/qr/1")
    @CrossOrigin
    ResponseEntity<Integer> counter(){
        tempCounter++;
        return new ResponseEntity<>(tempCounter, HttpStatus.OK);
    }
}
