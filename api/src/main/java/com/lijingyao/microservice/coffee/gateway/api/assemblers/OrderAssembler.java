package com.lijingyao.microservice.coffee.gateway.api.assemblers;

import com.lijingyao.microservice.coffee.gateway.api.models.OrderCreateDetailModel;
import com.lijingyao.microservice.coffee.gateway.api.models.OrderCreateModel;
import com.lijingyao.microservice.coffee.gateway.api.models.OrderDetailModel;
import com.lijingyao.microservice.coffee.gateway.api.models.OrderModel;
import com.lijingyao.microservice.coffee.template.items.OrderItemDTO;
import com.lijingyao.microservice.coffee.template.items.OrderItemDetailDTO;
import com.lijingyao.microservice.coffee.template.items.OrderItemDetailPriceDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderCreateDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderDetailCreateDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderDetailDTO;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lijingyao on 2018/7/9 22:02.
 */
@Component
public class OrderAssembler {


    private BeanCopier orderItemDetailCopier = BeanCopier.create(OrderCreateDetailModel.class, OrderDetailCreateDTO.class, false);

    private BeanCopier orderModelCopier = BeanCopier.create(OrderDTO.class, OrderModel.class, false);
    private BeanCopier orderModelDetailCopier = BeanCopier.create(OrderDetailDTO.class, OrderDetailModel.class, false);


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

        OrderCreateDTO createDTO = new OrderCreateDTO();
        createDTO.setUserId(createModel.getUserId());

        Map<Integer, OrderItemDetailPriceDTO> itemPriceMap = priceDTOS.stream().collect(Collectors.toMap(p -> p.getItemId(), p -> p));

        List<OrderDetailCreateDTO> details = createModel.getDetails().stream().map(detail -> wrapDetailCreateDTO(detail, itemPriceMap)).collect(Collectors.toList());
        createDTO.setDetails(details);

        return createDTO;
    }

    private OrderDetailCreateDTO wrapDetailCreateDTO(OrderCreateDetailModel detail, Map<Integer, OrderItemDetailPriceDTO> itemPriceMap) {
        OrderDetailCreateDTO detailCreateDTO = new OrderDetailCreateDTO();

        OrderItemDetailPriceDTO priceDTO = itemPriceMap.get(detail.getItemId());

        orderItemDetailCopier.copy(detail, detailCreateDTO, null);
        detailCreateDTO.setItemName(priceDTO.getItemName());
        detailCreateDTO.setPrice(priceDTO.getPrice());

        return detailCreateDTO;
    }

    public OrderModel assembleOrderModel(OrderDTO orderDTO) {
        OrderModel orderModel = new OrderModel();

        orderModelCopier.copy(orderDTO, orderModel, null);

        List<OrderDetailModel> details = orderDTO.getDetails().stream().map(d -> wrapDetailOrderModel(d)).collect(Collectors.toList());

        orderModel.setDetails(details);
        return orderModel;
    }

    private OrderDetailModel wrapDetailOrderModel(OrderDetailDTO dto) {
        OrderDetailModel detailModel = new OrderDetailModel();
        orderModelDetailCopier.copy(dto, detailModel, null);
        return detailModel;
    }
}
