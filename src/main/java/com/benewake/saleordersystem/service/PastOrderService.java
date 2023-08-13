package com.benewake.saleordersystem.service;

import com.benewake.saleordersystem.entity.Past.PastOrder;
import com.benewake.saleordersystem.entity.Past.SaleOut;
import com.benewake.saleordersystem.entity.Past.Withdraw;

import java.util.List;

public interface PastOrderService {

    List<PastOrder> transferSaleOutToPastOrder(List<SaleOut> list);
    List<PastOrder> transferWithdrawToPastOrder(List<Withdraw> list);

    /**
     * 更新历史订单表
     * @param reloadAll  true-删除当前数据并将历史数据和金蝶中至今的数据全部导入数据库  false-导入上一次导入的时间之后的全部数据
     * @return
     */
    int savePastOrder(boolean reloadAll);
}
