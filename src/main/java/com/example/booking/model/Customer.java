package com.example.booking.model;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Customer {

    @DynamoDBAttribute
    private String firstName;

    @DynamoDBAttribute
    private  String lastName;

    @DynamoDBAttribute
    private  String saluation;
}