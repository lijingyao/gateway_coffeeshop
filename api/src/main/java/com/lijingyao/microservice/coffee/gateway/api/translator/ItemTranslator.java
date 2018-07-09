package com.lijingyao.microservice.coffee.gateway.api.translator;

import com.lijingyao.microservice.coffee.gateway.api.base.BaseTranslator;
import com.lijingyao.microservice.coffee.gateway.api.translator.clients.ItemClient;
import com.lijingyao.microservice.coffee.template.items.OrderItemDTO;
import com.lijingyao.microservice.coffee.template.items.OrderItemPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by lijingyao on 2018/7/9 18:56.
 */
@Component
public class ItemTranslator extends BaseTranslator {

    @Autowired
    private ItemClient itemClient;


    private OrderItemPriceDTO buildItemOrderPrice(OrderItemDTO orderItemDTO) {
        ResponseEntity result = itemClient.buildItemOrderPrice(orderItemDTO);
        return (OrderItemPriceDTO) valueOfResult(result, OrderItemPriceDTO.class);
    }

    public Observable<OrderItemPriceDTO> buildItemOrderPriceObs(OrderItemDTO orderItemDTO) {
        return Observable.unsafeCreate((Subscriber<? super OrderItemPriceDTO> s) -> {
            s.onNext(buildItemOrderPrice(orderItemDTO));
            s.onCompleted();
        }).subscribeOn(Schedulers.io());
    }


}
