package com.benewake.saleordersystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.benewake.saleordersystem.entity.Notice;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 * @since 2023年08月02 16:31
 * 描 述： TODO
 */
public interface NoticeService extends IService<Notice> {
    /**
     * 获取全部有效消息
     *
     * @return
     */
    List<Map<String,Object>> getAllList(Long id,Integer type);
}
