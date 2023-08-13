package com.benewake.saleordersystem.service.impl;

import com.benewake.saleordersystem.entity.Past.PastOrder;
import com.benewake.saleordersystem.entity.Past.SaleOut;
import com.benewake.saleordersystem.entity.Past.Withdraw;
import com.benewake.saleordersystem.mapper.PastOrderMapper;
import com.benewake.saleordersystem.mapper.StoredProceduresMapper;
import com.benewake.saleordersystem.service.KingDeeService;
import com.benewake.saleordersystem.service.PastOrderService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lcs
 * @since 2023年07月15 16:20
 * 描 述： TODO
 */
@Service
public class PastOrderServiceImpl implements PastOrderService {
    @Autowired
    private PastOrderMapper pastOrderMapper;
    @Autowired
    private KingDeeService kingDeeService;
    @Autowired
    private StoredProceduresMapper storedProceduresMapper;

    volatile private static Long updateTime = 0L;

    @Override
    public List<PastOrder> transferSaleOutToPastOrder(List<SaleOut> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<PastOrder> pastOrders = new ArrayList<>(list.size());
        list.forEach(l->{
            try {
                pastOrders.add(new PastOrder(l.getFMaterialID(),l.getFRealQty(),
                        l.getFCustomerID(),l.getFSalesManID(),sdf.parse(l.getFDate()),l.getFSoorDerno()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
        return pastOrders;
    }

    @Override
    public List<PastOrder> transferWithdrawToPastOrder(List<Withdraw> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<PastOrder> pastOrders = new ArrayList<>(list.size());
        list.forEach(l->{
            try {
                pastOrders.add(new PastOrder(l.getFMaterialId(),l.getFRealQty(),
                        l.getFRetcustId(),l.getFSalesManId(),sdf.parse(l.getFDate()),l.getFOrderNo()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
        return pastOrders;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int savePastOrder(boolean reloadAll) {
        // 取数限制
        int num = Integer.MAX_VALUE;

        int res = 0;
        // type = 0 重新导入数据
        ReentrantLock lock = new ReentrantLock();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        lock.lock();
        try {
            if(reloadAll || updateTime.equals(0L)){
                pastOrderMapper.reloadLocalOrders();
                updateTime = 0L;
            }
            String d = sdf.format(new Date(updateTime));
            val saleOuts1 = kingDeeService.searchSaleOutList1(num,d);
            saleOuts1.addAll(kingDeeService.searchSaleOutList2(num,d));
            val withdraws1 = kingDeeService.searcWithdrawList1(num,d);
            withdraws1.addAll(kingDeeService.searcWithdrawList2(num,d));

            List<PastOrder> list = new ArrayList<>();
            list.addAll(transferSaleOutToPastOrder(saleOuts1));
            list.addAll(transferWithdrawToPastOrder(withdraws1));
            // 替换空值
            list.forEach(p->{
                if(null == p.getFSalesManID()){
                    p.setFSalesManID("");
                }
                if(null == p.getFMaterialID()){
                    p.setFMaterialID("");
                }
                if(null == p.getFSoorDerno()){
                    p.setFSoorDerno("");
                }
                if(null == p.getFRealQty()){
                    p.setFRealQty("0");
                }
                if(null == p.getFCustomerID()){
                    p.setFCustomerID("");
                }
            });

            res = pastOrderMapper.insertPastOrders(list);
            updateTime = System.currentTimeMillis();
            storedProceduresMapper.doReloadPastOrdersAnalysisTempTables(365,1.5,2);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            lock.unlock();
        }
        return res;
    }
}
