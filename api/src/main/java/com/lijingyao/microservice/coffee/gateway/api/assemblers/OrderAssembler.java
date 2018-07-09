package com.lijingyao.microservice.coffee.gateway.api.assemblers;

import com.lijingyao.microservice.coffee.gateway.api.models.OrderCreateDetailModel;
import com.lijingyao.microservice.coffee.gateway.api.models.OrderCreateModel;
import com.lijingyao.microservice.coffee.gateway.api.models.OrderModel;
import com.lijingyao.microservice.coffee.template.items.OrderItemDTO;
import com.lijingyao.microservice.coffee.template.items.OrderItemDetailDTO;
import com.lijingyao.microservice.coffee.template.items.OrderItemDetailPriceDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderCreateDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderDTO;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lijingyao on 2018/7/9 22:02.
 */
@Component
public class OrderAssembler {


    private BeanCopier orderItemDetailCopier = BeanCopier.create(OrderCreateDetailModel.class, OrderItemDetailDTO.class, false);


    public OrderItemDTO assembleOrderItem(List<OrderCreateDetailModel> detailModels) {
        OrderItemDTO itemDTO = new OrderItemDTO();
        List<OrderItemDetailDTO> detailDTOS = detailModels.stream().map(o -> wrapDetailInfo(o)).collect(Collectors.toList());
        itemDTO.setDetailDTOS(detailDTOS);
        return itemDTO;
    }

    private OrderItemDetailDTO wrapDetailInfo(OrderCreateDetailModel o) {
        OrderItemDetailDTO detailDTO = new OrderItemDetailDTO();
        detailDTO.setItemId(o.getItemId());
        detailDTO.setEspresso(o.getEspresso());
        detailDTO.setQuantity(o.getQuantity());
        return detailDTO;
    }

    public OrderCreateDTO assembleOrderCreateDTO(List<OrderItemDetailPriceDTO> priceDTOS, OrderCreateModel createModel) {
        return null;
    }

    public OrderModel assembleOrderModel(OrderDTO o) {
        return null;
    }
}
