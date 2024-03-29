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

    @DynamoDBAttribute
    private  String email;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSaluation() {
        return this.saluation;
    }

    public void setSaluation(String saluation) {
        this.saluation = saluation;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}