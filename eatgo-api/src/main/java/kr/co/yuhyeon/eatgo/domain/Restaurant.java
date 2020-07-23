package kr.co.yuhyeon.eatgo.domain;

public class Restaurant {

    private static String name;
    private static String address;


    public static String getName() {
        return name;
    }

    public static String getAddress() {
        return address;
    }

    public static String getInformation() {
        return name + " in " + address;
    }

    public Restaurant (String name, String address) {
        this.name = name;
        this.address = address;
    }

}
