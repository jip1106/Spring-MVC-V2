package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetDeliveryCode {
    private SetDeliveryCode(){

    }

    private static final List<DeliveryCode> deliveryCodeList = Arrays.asList(
            new DeliveryCode("FAST","빠른 배송"),
            new DeliveryCode("NORMAL","일반 배송"),
            new DeliveryCode("SLOW","느린 배송")
    );

    public static List<DeliveryCode> getCodeList(){
        return deliveryCodeList;
    }
}
