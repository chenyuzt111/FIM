package com.benewake.saleordersystem.excel;

import com.alibaba.excel.EasyExcel;
import com.benewake.saleordersystem.SaleOrderSystemApplication;
import com.benewake.saleordersystem.entity.User;
import com.benewake.saleordersystem.excel.model.BillMaterialModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class TestExcel {

    @Test
    public void alpheExcel(){
        BillMaterialExcelListener listener = new BillMaterialExcelListener();
        File f = new File("F:\\study\\物料清单_2022121517171911_219769.xlsx");
        EasyExcel.read(f, BillMaterialModel.class,listener).sheet().doRead();
    }


    @Test
    public void excelWrite(){
        //1、创建一个文件对象
        File excelFile = new File("F:/销售出库单测试.xlsx");
        //2、判断文件是否存在，不存在则创建一个Excel文件
        if (!excelFile.exists()) {
            try {
                excelFile.createNewFile();//创建一个新的文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //3、指定需要那个class去写。然后写到第一个sheet，名字为模版，然后文件流会自动关闭
        EasyExcel.write(excelFile, User.class).sheet("订单模版").doWrite(data());
    }

    private List<User> data(){
        //创建一个List集合
        List excelOrderList = new ArrayList<>();

        /*
         *xls版本的Excel最多一次可写0 ...65535行
         * xlsx 版本的Excel最多一次可写0...1048575行
         */
        //超出报异常：java.lang.IllegalArgumentException: Invalid row number (65536) outside allowable range (0..65535)
        for (int i=0;i<65535;i++){
//            User data = new User();
//            data.setId(0L+i);
//            data.setUsername(CommonUtils.generateUUID());
//            data.setPassword("mima"+i);
//            data.setSalt("yan"+i*2);
//            data.setType(0L);
//            excelOrderList.add(data);
        }

        return excelOrderList;//返回list集合
    }



}
