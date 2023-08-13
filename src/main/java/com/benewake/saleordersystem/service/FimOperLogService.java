package com.benewake.saleordersystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benewake.saleordersystem.entity.FimOperLog;
import com.benewake.saleordersystem.entity.VO.FimOperLogQueryVo;

import java.util.List;

/**
 * @author Lcs
 */
public interface FimOperLogService extends IService<FimOperLog> {
    /**
     * 条件查找操作日志
     * @param fimOperLogQueryVo
     * @return
     */
    List<FimOperLog> selectOperLogs(FimOperLogQueryVo fimOperLogQueryVo);
}
