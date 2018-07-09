package com.lijingyao.microservice.coffee.gateway.api.assemblers;

import com.lijingyao.microservice.coffee.gateway.api.models.UserCenterModel;
import com.lijingyao.microservice.coffee.template.users.UserDTO;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

/**
 * Created by lijingyao on 2018/7/9 16:50.
 */
@Component
public class UserCenterAssembler {

    private BeanCopier userDTOCopier = BeanCopier.create(UserDTO.class, UserCenterModel.class, false);

    public UserCenterModel wrapUserModel(UserDTO userDTO) {
        UserCenterModel model = new UserCenterModel();

        userDTOCopier.copy(userDTO, model, null);
        return model;
    }
}
