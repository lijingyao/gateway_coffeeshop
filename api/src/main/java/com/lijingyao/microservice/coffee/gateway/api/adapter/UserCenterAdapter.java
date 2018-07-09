package com.lijingyao.microservice.coffee.gateway.api.adapter;

import com.lijingyao.microservice.coffee.gateway.api.assemblers.UserCenterAssembler;
import com.lijingyao.microservice.coffee.gateway.api.base.RestExceptionHandler;
import com.lijingyao.microservice.coffee.gateway.api.models.UserCenterModel;
import com.lijingyao.microservice.coffee.gateway.api.base.Result;
import com.lijingyao.microservice.coffee.gateway.api.translator.OrderTranslator;
import com.lijingyao.microservice.coffee.gateway.api.translator.UserTranslator;
import com.lijingyao.microservice.coffee.template.users.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;

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
    private UserCenterAssembler userCenterAssembler;


    public Result<UserCenterModel> userSelfCenter(Long userId) {
        Result<UserCenterModel> result = new Result<>();

        Observable<UserDTO> userDTOObs = userTranslator.getUserDTOObs(userId).cache();


        Observable<Result<UserCenterModel>> observable = userDTOObs.flatMap(userDTO ->
                Observable.just(result.setResult(userCenterAssembler.wrapUserModel(userDTO))))
                .onErrorReturn(e -> RestExceptionHandler.onErrorReturn(e, result));

        return observable.toBlocking().first();
    }



}
