package com.lijingyao.microservice.coffee.gateway.api.adapter;

import com.lijingyao.microservice.coffee.gateway.api.assemblers.OrderAssembler;
import com.lijingyao.microservice.coffee.gateway.api.base.GatewayErrors;
import com.lijingyao.microservice.coffee.gateway.api.base.RestExceptionHandler;
import com.lijingyao.microservice.coffee.gateway.api.base.Result;
import com.lijingyao.microservice.coffee.gateway.api.models.OrderCreateDetailModel;
import com.lijingyao.microservice.coffee.gateway.api.models.OrderCreateModel;
import com.lijingyao.microservice.coffee.gateway.api.models.OrderModel;
import com.lijingyao.microservice.coffee.gateway.api.translator.ItemTranslator;
import com.lijingyao.microservice.coffee.gateway.api.translator.OrderTranslator;
import com.lijingyao.microservice.coffee.template.items.OrderItemDTO;
import com.lijingyao.microservice.coffee.template.items.OrderItemDetailPriceDTO;
import com.lijingyao.microservice.coffee.template.items.OrderItemPriceDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderCreateDTO;
import com.lijingyao.microservice.coffee.template.trade.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import rx.Observable;

import java.util.List;

/**
 * Created by lijingyao on 2018/7/9 18:55.
 */
@Component
public class OrderAdapter {

    @Autowired
    private OrderTranslator orderTranslator;
    @Autowired
    private ItemTranslator itemTranslator;
    @Autowired
    private OrderAssembler orderAssembler;


    public Result<OrderModel> createOrder(OrderCreateModel createModel) {
        Result<OrderModel> result = new Result<>();

        List<OrderCreateDetailModel> detailModels = createModel.getDetails();
        if(CollectionUtils.isEmpty(detailModels))return result.setErrors(GatewayErrors.ILLEGAL_PARAM_ERROR);

        OrderItemDTO orderItemDTO = orderAssembler.assembleOrderItem(detailModels);
        Observable<OrderItemPriceDTO> priceDTOObs = itemTranslator.buildItemOrderPriceObs(orderItemDTO).cache();

        Observable<OrderDTO> orderDTOObs = getOrderCreativeObs(priceDTOObs, createModel).cache();

        Observable<Result<OrderModel>> observable = orderDTOObs.flatMap(orderDTO -> {
            result.setResult(orderAssembler.assembleOrderModel(orderDTO));
            return Observable.just(result);
        }).onErrorReturn(e -> RestExceptionHandler.onErrorReturn(e, result));

        return observable.toBlocking().first();
    }


    private Observable<OrderDTO> getOrderCreativeObs(Observable<OrderItemPriceDTO> obs, OrderCreateModel createModel) {
        return obs.flatMap(itemPriceDTO -> {

            List<OrderItemDetailPriceDTO> priceDTOS = itemPriceDTO.getPriceDTOS();
            OrderCreateDTO createDTO = orderAssembler.assembleOrderCreateDTO(priceDTOS, createModel);

            return orderTranslator.orderCreativeObs(createDTO);
        });
    }


}
