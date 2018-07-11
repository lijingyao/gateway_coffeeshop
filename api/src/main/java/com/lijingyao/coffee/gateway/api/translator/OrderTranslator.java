package com.lijingyao.coffee.gateway.api.translator;

import com.lijingyao.coffee.gateway.api.base.BaseTranslator;
import com.lijingyao.coffee.gateway.api.translator.clients.OrderClient;
import com.lijingyao.microservice.coffee.template.trade.OrderCreateDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.List;

/**
 * Created by lijingyao on 2018/7/9 18:56.
 */
@Component
public class OrderTranslator extends BaseTranslator {

    @Autowired
    private OrderClient orderClient;


    private OrderDTO orderCreative(OrderCreateDTO orderCreateDTO) {
        ResponseEntity result = orderClient.orderCreative(orderCreateDTO);
        return (OrderDTO) valueOfResult(result, OrderDTO.class);
    }

    public Observable<OrderDTO> orderCreativeObs(OrderCreateDTO orderCreateDTO) {
        return Observable.unsafeCreate((Subscriber<? super OrderDTO> s) -> {
            s.onNext(orderCreative(orderCreateDTO));
            s.onCompleted();
        }).subscribeOn(Schedulers.io());
    }

    private List<OrderDTO> getUserNewOrders(Long userId, Integer mainOrderSize, Integer detailShowSize) {
        ResponseEntity result = orderClient.getUserNewOrders(userId, mainOrderSize, detailShowSize);
        return (List<OrderDTO>) valueOfResult(result, OrderDTO.class);
    }

    public Observable<List<OrderDTO>> getUserNewOrdersObs(Long userId, Integer mainOrderSize, Integer detailShowSize) {
        return Observable.unsafeCreate((Subscriber<? super List<OrderDTO>> s) -> {
            s.onNext(getUserNewOrders(userId, mainOrderSize, detailShowSize));
            s.onCompleted();
        }).subscribeOn(Schedulers.io());
    }


}
