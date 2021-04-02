package com.example.booking.repository;

import com.example.booking.model.Booking;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class BookingRepository{

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Booking findByBookingReferenceCode(String bookingReferenceCode){
        return dynamoDBMapper.load(Booking.class, bookingReferenceCode);
    }

    public Booking addBooking(Booking booking) {
        dynamoDBMapper.save(booking);
        return booking;
    }

    public String deleteBooking(String bookingReferenceCode) {
        Booking booking = dynamoDBMapper.load(Booking.class, bookingReferenceCode);
        if (booking != null){
            dynamoDBMapper.delete(booking);
            return "Booking Cancelled!";
        }
        else{
            return "Sorry, There is no booking under this booking reference code.";
        }
            
    }

    public String update(String bookingReferenceCode, Booking booking) {
        Booking bookingRecord = dynamoDBMapper.load(Booking.class, bookingReferenceCode);
        if (bookingRecord != null){
            dynamoDBMapper.save(booking, new DynamoDBSaveExpression().withExpectedEntry("bookingReferenceCode",new ExpectedAttributeValue(new AttributeValue().withS(bookingReferenceCode))));
            return "Booking Updated!";
        }
        else{
            return "Sorry, There is no booking under this booking reference code.";
        }
    }

}
