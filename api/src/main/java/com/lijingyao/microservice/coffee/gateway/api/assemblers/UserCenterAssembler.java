package com.lijingyao.microservice.coffee.gateway.api.assemblers;

import com.lijingyao.microservice.coffee.gateway.api.models.UserCenterModel;
import com.lijingyao.microservice.coffee.gateway.api.models.UserSimpleOrderModel;
import com.lijingyao.microservice.coffee.template.trade.OrderDTO;
import com.lijingyao.microservice.coffee.template.users.UserDTO;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lijingyao on 2018/7/9 16:50.
 */
@Component
public class UserCenterAssembler {

    private BeanCopier userDTOCopier = BeanCopier.create(UserDTO.class, UserCenterModel.class, false);

    public UserCenterModel wrapUserModel(UserDTO userDTO, List<OrderDTO> orderDTOS) {
        UserCenterModel model = new UserCenterModel();

        userDTOCopier.copy(userDTO, model, null);

        if (!CollectionUtils.isEmpty(orderDTOS)) {
            List<UserSimpleOrderModel> orderModels = orderDTOS.stream().map(o -> assembleSimpleOrder(o)).collect(Collectors.toList());
            model.setOrderModels(orderModels);
        }

        return model;
    }

    private UserSimpleOrderModel assembleSimpleOrder(OrderDTO o) {
        UserSimpleOrderModel model = new UserSimpleOrderModel();
        model.setCreateTime(o.getCreateTime());
        model.setOrderId(o.getOrderId());
        model.setOrderPrice(o.getTotalPrice());

        return model;
    }
}
