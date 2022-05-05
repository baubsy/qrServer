package com.qrcraze.qrcraze.Data;

import com.qrcraze.qrcraze.Models.QR;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrRepository extends CrudRepository<QR, Integer> {
}
