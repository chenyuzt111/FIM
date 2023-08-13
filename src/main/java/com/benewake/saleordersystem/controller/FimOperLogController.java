package com.benewake.saleordersystem.controller;

import com.benewake.saleordersystem.annotation.AdminRequired;
import com.benewake.saleordersystem.annotation.LoginRequired;
import com.benewake.saleordersystem.entity.FimOperLog;
import com.benewake.saleordersystem.entity.VO.FimOperLogQueryVo;
import com.benewake.saleordersystem.service.FimOperLogService;
import com.benewake.saleordersystem.utils.HostHolder;
import com.benewake.saleordersystem.utils.IpUtil;
import com.benewake.saleordersystem.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Lcs
 * @since 2023年08月05 15:06
 * 描 述： TODO
 */
@Api(tags = "操作日志管理")
@RestController
@RequestMapping("/log")
public class FimOperLogController {
    @Autowired
    private FimOperLogService fimOperLogService;
    @Autowired
    private HostHolder hostHolder;

    @ApiOperation("条件查看日志")
    @PostMapping("operLog")
    @AdminRequired
    public Result findOperLog(@RequestBody FimOperLogQueryVo fimOperLogQueryVo){
        List<FimOperLog> list = fimOperLogService.selectOperLogs(fimOperLogQueryVo);
        return Result.success(list);
    }

    @ApiOperation("写入操作日志")
    @PostMapping("save")
    @LoginRequired
    public Result save(@RequestBody FimOperLog fimOperLog, HttpServletRequest request){
        String ip = IpUtil.getIpAddress(request);
        fimOperLog.setOperIp(ip);
        fimOperLog.setOperName(hostHolder.getUser().getUsername());
        fimOperLogService.save(fimOperLog);
        return Result.success();
    }

}
