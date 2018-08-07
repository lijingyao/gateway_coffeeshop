package com.lijingyao.coffee.gateway.api.assemblers;

import com.lijingyao.coffee.gateway.api.models.OrderDetailModel;
import com.lijingyao.coffee.gateway.api.models.UserCenterModel;
import com.lijingyao.coffee.gateway.api.models.UserSimpleOrderModel;
import com.lijingyao.microservice.coffee.template.trade.OrderDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderDetailDTO;
import com.lijingyao.microservice.coffee.template.users.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lijingyao on 2018/7/9 16:50.
 */
@Component
public class UserCenterAssembler {
    @Autowired
    private OrderAssembler orderAssembler;

    private BeanCopier userDTOCopier = BeanCopier.create(UserDTO.class, UserCenterModel.class, false);

    public UserCenterModel wrapUserModel(UserDTO userDTO, List<OrderDTO> orderDTOS, List<OrderDetailDTO> detailDTOS) {
        UserCenterModel model = new UserCenterModel();

        userDTOCopier.copy(userDTO, model, null);

        if (!CollectionUtils.isEmpty(orderDTOS)) {

            Map<String, OrderDetailDTO> detailDTOMap = detailDTOS.stream().collect(Collectors.toMap(x -> x.getMainOrderId(), x -> x));
            List<UserSimpleOrderModel> orderModels = orderDTOS.stream().map(o -> assembleSimpleOrder(o, detailDTOMap)).collect(Collectors.toList());
            model.setOrderModels(orderModels);
        }

        return model;
    }

    private UserSimpleOrderModel assembleSimpleOrder(OrderDTO o, Map<String, OrderDetailDTO> detailDTOMap) {
        UserSimpleOrderModel model = new UserSimpleOrderModel();
        model.setCreateTime(o.getCreateTime());
        model.setOrderId(o.getOrderId());
        model.setOrderPrice(o.getTotalPrice());

        if (detailDTOMap.get(o.getOrderId()) != null) {
            OrderDetailModel detailModel = orderAssembler.wrapDetailOrderModel(detailDTOMap.get(o.getOrderId()));
            model.setDetail(detailModel);
        }

        return model;
    }
}
