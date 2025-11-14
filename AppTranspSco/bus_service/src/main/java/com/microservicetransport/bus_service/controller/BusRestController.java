package com.microservicetransport.bus_service.controller;

import com.microservicetransport.bus_service.entity.Bus;
import com.microservicetransport.bus_service.service.BusService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buses")
@AllArgsConstructor
@NoArgsConstructor
public class BusRestController {
    private BusService busService;

    @GetMapping
    public List<Bus> getBusService() {
        return busService.getBuses();
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Bus> deleteBus(@PathVariable Long id) {
        Bus deletedBus = busService.deleteBus(id);
        if (deletedBus == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedBus);
    }


}
