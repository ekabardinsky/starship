package ru.ekabardinsky.humaninterface.model;

import ru.ekabardinsky.humaninterface.template.Body;

/**
 * Created by ekabardinsky on 2/25/17.
 */
public class Growth {
    private static final double year = 365 * 24 * 3600.0;//a year in second
    private static Body body;


    public static Body growth(double time, boolean renew){
        if(renew) renew();
        return getBody().growth(time);
    }

    private static Body getBody() {
        if(body == null) body = new Body();
        return body;
    }

    public static void renew() {
        body = new Body();
    }
}
