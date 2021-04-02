package com.example.booking.controller;

import com.example.booking.model.Booking;
import com.example.booking.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/booking")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;
    
    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "Booking is up!";
    }
    
    @GetMapping("/{bookingCode}")
    public Booking findByBookingReferenceCode(@PathVariable("bookingCode")  String bookingCode) {
        return bookingRepository.findByBookingReferenceCode(bookingCode);
    }

    @PostMapping
    public Booking addBooking(@RequestBody Booking booking) {
        return bookingRepository.addBooking(booking);
        
    }

    @DeleteMapping("/{bookingCode}")
    public String deleteBooking(@PathVariable("bookingCode") String bookingCode) {
        return  bookingRepository.deleteBooking(bookingCode);
    }

    @PutMapping("/{bookingCode}")
    public String updateBooking(@PathVariable("bookingCode") String bookingCode, @RequestBody Booking booking) {
        return bookingRepository.update(bookingCode,booking);
    }

}



