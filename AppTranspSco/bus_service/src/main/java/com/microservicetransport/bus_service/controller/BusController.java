package com.microservicetransport.bus_service.controller;

import com.microservicetransport.bus_service.entity.Bus;
import com.microservicetransport.bus_service.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buses")
@RequiredArgsConstructor
public class BusController {

    private final BusRepository busRepository;

    // Get all buses
    @GetMapping
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    // Create new bus
    @PostMapping
    public ResponseEntity<Bus> createBus(@RequestBody Bus bus) {
        return ResponseEntity.ok(busRepository.save(bus));
    }

    // Update bus
    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus(@PathVariable Long id, @RequestBody Bus updatedBus) {
        return busRepository.findById(id)
                .map(bus -> {
                    bus.setRegistrationNumber(updatedBus.getRegistrationNumber());
                    bus.setCapacity(updatedBus.getCapacity());
                    bus.setCapacityLimit(updatedBus.getCapacityLimit());
                    bus.setStatus(updatedBus.getStatus());
                    bus.setUrlImage(updatedBus.getUrlImage());
                    bus.setLatitude(updatedBus.getLatitude());
                    bus.setLongitude(updatedBus.getLongitude());
                    bus.setDriver(updatedBus.getDriver());
                    return ResponseEntity.ok(busRepository.save(bus));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete bus
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        if (!busRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        busRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
