package com.gt.common.controller;

import com.gt.common.entity.Result;
import com.gt.common.entity.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.SqlResultSetMapping;

@RestController
public class ErrorController {

    //公共错误跳转
    @RequestMapping(value = "autherror")
    public Result autherror(int code) {
        return code == 1 ? new Result(ResultCode.UNAUTHENTICATED) : new Result((ResultCode.UNAUTHORISE));
    }
}
