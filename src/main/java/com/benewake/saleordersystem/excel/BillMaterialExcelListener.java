package com.benewake.saleordersystem.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import com.benewake.saleordersystem.excel.model.BillMaterialModel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lcs
 */
@Slf4j
public class BillMaterialExcelListener extends AnalysisEventListener<BillMaterialModel> {

    List<BillMaterialModel> cache = new ArrayList<>();
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     */
    @Override
    public void invoke(BillMaterialModel data, AnalysisContext context) {
        //log.info("解析到一条数据:{}", JSON.toJSONString(data));l
        //解析到的数据进行实体类封装，并传入到list集合中
        System.out.println(data.toString());
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
//        log.info("{}条产能数据，，开始存储数据库！", cachedBillMaterialDataList.size());
//        //todo 批量添加的操作
//        if (!cachedBillMaterialDataList.isEmpty()) {
//            billMaterialMapper.batchInsertBillMaterial(cachedBillMaterialDataList);
//            cachedBillMaterialDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//        }
//        log.info("存储数据库成功！");
    }



}
