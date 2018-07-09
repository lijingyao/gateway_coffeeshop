package com.lijingyao.microservice.coffee.gateway.api.assemblers;

import com.lijingyao.microservice.coffee.gateway.api.models.OrderCreateDetailModel;
import com.lijingyao.microservice.coffee.gateway.api.models.OrderCreateModel;
import com.lijingyao.microservice.coffee.gateway.api.models.OrderModel;
import com.lijingyao.microservice.coffee.template.items.OrderItemDTO;
import com.lijingyao.microservice.coffee.template.items.OrderItemDetailPriceDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderCreateDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lijingyao on 2018/7/9 22:02.
 */
@Component
public class OrderAssembler {

    public OrderItemDTO assembleOrderItem(List<OrderCreateDetailModel> detailModels) {
        return null;
    }

    public OrderCreateDTO assembleOrderCreateDTO(List<OrderItemDetailPriceDTO> priceDTOS, OrderCreateModel createModel) {
        return null;
    }

    public OrderModel assembleOrderModel(OrderDTO o) {
        return null;
    }
}
