package com.lijingyao.coffee.gateway.api.adapter;

import com.lijingyao.coffee.gateway.api.assemblers.UserCenterAssembler;
import com.lijingyao.coffee.gateway.api.base.RestExceptionHandler;
import com.lijingyao.coffee.gateway.api.base.Result;
import com.lijingyao.coffee.gateway.api.models.UserCenterModel;
import com.lijingyao.coffee.gateway.api.translator.UserTranslator;
import com.lijingyao.coffee.gateway.api.translator.ItemTranslator;
import com.lijingyao.coffee.gateway.api.translator.OrderTranslator;
import com.lijingyao.microservice.coffee.template.trade.OrderDTO;
import com.lijingyao.microservice.coffee.template.users.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

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

    private static final int ORDER_SIZE = 2;


    public Result<UserCenterModel> userSelfCenter(Long userId) {
        Result<UserCenterModel> result = new Result<>();

        Observable<UserDTO> userDTOObs = userTranslator.getUserDTOObs(userId).cache();

        Observable<List<OrderDTO>> ordersObs = orderTranslator.getUserNewOrdersObs(userId, ORDER_SIZE, ORDER_SIZE).cache();

        List<Observable<?>> obsList = Arrays.asList(userDTOObs, ordersObs);
        Observable<Result<UserCenterModel>> observable = Observable.zip(obsList, results -> {
            UserDTO userDTO = (UserDTO) results[0];
            List<OrderDTO> orderDTOS = (List<OrderDTO>) results[1];

            UserCenterModel model = userCenterAssembler.wrapUserModel(userDTO,orderDTOS);
            return result.setResult(model);
        }).onErrorReturn(e -> RestExceptionHandler.onErrorReturn(e, result));

        return observable.toBlocking().first();
    }


}
