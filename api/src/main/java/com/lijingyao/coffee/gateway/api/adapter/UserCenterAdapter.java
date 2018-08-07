package com.lijingyao.coffee.gateway.api.adapter;

import com.lijingyao.coffee.gateway.api.assemblers.UserCenterAssembler;
import com.lijingyao.coffee.gateway.api.base.RestExceptionHandler;
import com.lijingyao.coffee.gateway.api.base.Result;
import com.lijingyao.coffee.gateway.api.models.UserCenterModel;
import com.lijingyao.coffee.gateway.api.translator.ItemTranslator;
import com.lijingyao.coffee.gateway.api.translator.OrderTranslator;
import com.lijingyao.coffee.gateway.api.translator.UserTranslator;
import com.lijingyao.microservice.coffee.template.trade.OrderDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderDetailDTO;
import com.lijingyao.microservice.coffee.template.users.UserDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lijingyao on 2018/7/9 16:44.
 */
@Component
public class UserCenterAdapter {


    @Autowired
    private UserTranslator userTranslator;

    @Autowired
    private OrderTranslator orderTranslator;

    @Autowired
    private ItemTranslator itemTranslator;

    @Autowired
    private UserCenterAssembler userCenterAssembler;

    private static final int ORDER_SIZE = 5;


    public Result<UserCenterModel> userSelfCenter(Long userId) {
        Result<UserCenterModel> result = new Result<>();

        Observable<UserDTO> userDTOObs = userTranslator.getUserDTOObs(userId).cache();

        Observable<List<OrderDTO>> ordersObs = orderTranslator.getUserNewOrdersObs(userId, ORDER_SIZE).cache();

        Observable<List<OrderDetailDTO>> detailOrderObs = getDetailObs(ordersObs, ORDER_SIZE);

        List<Observable<?>> obsList = Arrays.asList(userDTOObs, ordersObs, detailOrderObs);
        Observable<Result<UserCenterModel>> observable = Observable.zip(obsList, results -> {
            UserDTO userDTO = (UserDTO) results[0];
            List<OrderDTO> orderDTOS = (List<OrderDTO>) results[1];
            List<OrderDetailDTO> detailDTOS = (List<OrderDetailDTO>) results[2];

            UserCenterModel model = userCenterAssembler.wrapUserModel(userDTO, orderDTOS, detailDTOS);
            return result.setResult(model);
        }).onErrorReturn(e -> RestExceptionHandler.onErrorReturn(e, result));

        return observable.toBlocking().first();
    }


    private Observable<List<OrderDetailDTO>> getDetailObs(Observable<List<OrderDTO>> obs, Integer mainOrderSize) {
        return obs.flatMap(orderDTOS -> {

            if (CollectionUtils.isEmpty(orderDTOS)) {
                return Observable.just(null);
            }
            List<String> orderIds = orderDTOS.stream().map(o -> o.getOrderId()).collect(Collectors.toList());

            return orderTranslator.getBatchDetailOrdersObs(orderIds, mainOrderSize);
        });
    }

}
