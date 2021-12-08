package controller;

import exception.ItemNotFoundException;
import exception.ItemQuantityErrorException;
import exception.OrderNotFoundException;
import exception.RefundNotFoundException;
import model.Refund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.RefundService;

import java.util.List;

@RestController
@RequestMapping ("/refund")
public class RefundController {
    @Autowired
    RefundService refundService;


    @GetMapping(path="/{idOrder}")
    public ResponseEntity<List<Refund>> getRefunds(@PathVariable int idOrder) throws OrderNotFoundException {
        return ResponseEntity.ok(refundService.getRefundsByOrder(idOrder));
    }

    @PostMapping
    public ResponseEntity<Refund> create(@RequestBody final Refund refund)
            throws ItemNotFoundException, RefundNotFoundException, ItemQuantityErrorException, OrderNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(refundService.createRefund(refund));
    }
}

