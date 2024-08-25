package models;

import javax.naming.Name;

public class OrderInput {
    public String Name;
    public String LastName;
    public String Address;
    public String MetroStation;
    public String PhoneNumber;
    public String Date;
    public String Duration;
    public String Colour;
    public String Comment;

    public OrderInput(
            String name,
            String lastName,
            String address,
            String metroStation,
            String phoneNumber,
            String date,
            String duration,
            String colour,
            String comment
    ){
        this.Name = name;
        this.LastName = lastName;
        this.Address = address;
        this.MetroStation = metroStation;
        this.PhoneNumber = phoneNumber;
        this.Date = date;
        this.Duration = duration;
        this.Colour = colour;
        this.Comment = comment;
    }
}
