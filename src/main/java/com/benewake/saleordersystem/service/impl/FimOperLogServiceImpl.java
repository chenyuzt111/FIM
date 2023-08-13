package com.benewake.saleordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.benewake.saleordersystem.entity.FimOperLog;
import com.benewake.saleordersystem.entity.VO.FimOperLogQueryVo;
import com.benewake.saleordersystem.mapper.FimOperLogMapper;
import com.benewake.saleordersystem.service.FimOperLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lcs
 * @since 2023年08月05 15:04
 * 描 述： TODO
 */
@Service
public class FimOperLogServiceImpl extends ServiceImpl<FimOperLogMapper, FimOperLog> implements FimOperLogService {
    @Override
    public List<FimOperLog> selectOperLogs(FimOperLogQueryVo fimOperLogQueryVo) {
        LambdaQueryWrapper<FimOperLog> lqw = new LambdaQueryWrapper<>();
        lqw.like(StringUtils.isNotEmpty(fimOperLogQueryVo.getTitle()),
                        FimOperLog::getTitle,fimOperLogQueryVo.getTitle())
                .like(StringUtils.isNotEmpty(fimOperLogQueryVo.getOperName()),
                        FimOperLog::getOperName,fimOperLogQueryVo.getOperName())
                .ge(StringUtils.isNotEmpty(fimOperLogQueryVo.getCreateTimeBegin()),
                        FimOperLog::getCreateTime,fimOperLogQueryVo.getCreateTimeBegin())
                .le(StringUtils.isNotEmpty(fimOperLogQueryVo.getCreateTimeEnd()),
                        FimOperLog::getCreateTime,fimOperLogQueryVo.getCreateTimeEnd());
        return baseMapper.selectList(lqw);
    }
}
