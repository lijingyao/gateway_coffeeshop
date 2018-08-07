package com.lijingyao.coffee.gateway.api.restapi;

import com.lijingyao.coffee.gateway.api.adapter.UserCenterAdapter;
import com.lijingyao.coffee.gateway.api.models.UserCenterModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户个人中心
 * <p>
 * Created by lijingyao on 2018/7/9 19:27.
 */
@RestController
@RequestMapping("/users/center")
public class UserCenterController {


    @Autowired
    private UserCenterAdapter userCenterAdapter;

    @ApiOperation(value = "获取用户个人中心数据", response = UserCenterModel.class)
    @RequestMapping(value = "/{userId}", method = {RequestMethod.GET})
    public ResponseEntity userSelfCenter(@ApiParam(value = "查询的用户Id") @PathVariable("userId") Long userId) {
        return new ResponseEntity(userCenterAdapter.userSelfCenter(userId), HttpStatus.OK);
    }


}
