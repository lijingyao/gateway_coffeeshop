package com.lijingyao.microservice.coffee.gateway.api.translator;

import com.lijingyao.microservice.coffee.gateway.api.base.BaseTranslator;
import com.lijingyao.microservice.coffee.gateway.api.translator.clients.UserClient;
import com.lijingyao.microservice.coffee.template.users.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by lijingyao on 2018/7/9 19:27.
 */
@Component
public class UserTranslator extends BaseTranslator {


    @Autowired
    private UserClient userClient;

    private UserDTO getUserDTO(Long userId) {
        ResponseEntity result = userClient.getUser(userId);
        return (UserDTO) valueOfResult(result, UserDTO.class);
    }

    public Observable<UserDTO> getUserDTOObs(Long userId) {
        return Observable.unsafeCreate((Subscriber<? super UserDTO> s) -> {
            s.onNext(getUserDTO(userId));
            s.onCompleted();
        }).subscribeOn(Schedulers.io());
    }




}

