package com.benewake.saleordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.benewake.saleordersystem.entity.View;
import com.benewake.saleordersystem.mapper.ViewMapper;
import com.benewake.saleordersystem.mapper.Vo.SalesOrderVoMapper;
import com.benewake.saleordersystem.service.ViewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 * @since 2023年07月12 13:55
 * 描 述： TODO
 */
@Service
public class ViewServiceImpl implements ViewService {

    @Autowired
    private ViewMapper viewMapper;
    @Override
    public List<View> getUserView(Long userId, Long tableId) {
        return viewMapper.getUserView(userId, tableId);
    }

    @Override
    public int saveView(View view) {
        //return viewMapper.insertView(view);
        return viewMapper.insert(view);
    }

    @Override
    public List<Map<String, Object>> getAllCols(Long tableId) {
        if(tableId==null) return new ArrayList<>();
        return viewMapper.getAllCols(tableId);
    }

    @Override
    public int updateView(View view) {
        if(view.getViewId()==null) return 0;
        LambdaUpdateWrapper<View> luw = new LambdaUpdateWrapper<>();
        luw.eq(View::getViewId,view.getViewId())
                .set(StringUtils.isNotBlank(view.getViewName()),View::getViewName,view.getViewName())
                .set(view.getTableId()!=null,View::getTableId,view.getTableId())
                .set(view.getUserId()!=null,View::getUserId,view.getUserId());
        return viewMapper.update(view,luw);
    }

    @Override
    public boolean isExist(Long tableId, Long id, String viewName) {
        if(StringUtils.isBlank(viewName)){
            return true;
        }
        LambdaQueryWrapper<View> lqw = new LambdaQueryWrapper<>();
        lqw.select(View::getViewName)
                .eq(View::getViewName,viewName)
                .eq(View::getTableId,tableId)
                .eq(View::getUserId,id);
        return viewMapper.selectOne(lqw)!=null;
    }

    @Override
    public boolean deleteView(Long viewId) {
        return viewMapper.deleteById(viewId)!=0;
    }
}
