package com.lijingyao.coffee.gateway.api.translator;

import com.lijingyao.coffee.gateway.api.base.BaseTranslator;
import com.lijingyao.coffee.gateway.api.translator.clients.OrderClient;
import com.lijingyao.microservice.coffee.template.trade.OrderCreateDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

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

    private List<OrderDTO> getUserNewOrders(Long userId, Integer mainOrderSize) {
        ResponseEntity result = orderClient.getUserNewOrders(userId, mainOrderSize);
        return (List<OrderDTO>) valueOfResult(result, OrderDTO.class);
    }

    public Observable<List<OrderDTO>> getUserNewOrdersObs(Long userId, Integer mainOrderSize) {
        return Observable.unsafeCreate((Subscriber<? super List<OrderDTO>> s) -> {
            s.onNext(getUserNewOrders(userId, mainOrderSize));
            s.onCompleted();
        }).subscribeOn(Schedulers.io());
    }

    public Observable<List<OrderDetailDTO>> getBatchDetailOrdersObs(List<String> mainOrderIds, Integer mainOrderSize) {
        return Observable.unsafeCreate((Subscriber<? super List<OrderDetailDTO>> s) -> {
            s.onNext(getBatchDetailOrders(mainOrderIds,mainOrderSize));
            s.onCompleted();
        }).subscribeOn(Schedulers.io());
    }

    private List<OrderDetailDTO> getBatchDetailOrders(List<String> mainOrderIds, Integer mainOrderSize) {

        mainOrderIds = mainOrderIds.stream().distinct().collect(Collectors.toList());
        ResponseEntity result = orderClient.getDetailOrders(mainOrderIds.stream().toArray(String[]::new),mainOrderSize);
        return (List<OrderDetailDTO>) valueOfResult(result, OrderDetailDTO.class);
    }

}
