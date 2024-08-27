package models;

public class OrderInput {
    private final String name;
    private final String  lastName;
    private final String  address;
    private final String  metroStation;
    private final String  phoneNumber;
    private final String  date;
    private final String  duration;
    private final String  colour;
    private final String  comment;

    public String getName(){
        return name;
    }

    public String getLastName(){
        return lastName;
    }

    public String getAddress(){
        return address;
    }

    public String getMetroStation(){
        return metroStation;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getDate(){
        return date;
    }

    public String getDuration(){
        return duration;
    }

    public String getColour(){
        return colour;
    }

    public String getComment(){
        return comment;
    }

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
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }
}
