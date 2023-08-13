package com.benewake.saleordersystem.file;

import com.benewake.saleordersystem.SaleOrderSystemApplication;
import com.benewake.saleordersystem.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.*;

@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class FileTest {
    String filePath = "F:\\study\\pythonStudy\\FJSP2\\src\\config.py";
    @Test
    public void deleteFiles(){
        System.out.println(FileUtils.removeFile(new File(filePath)));
    }

    @Test
    public void updateFiles(){
        String[] keys = {"Number_cycles","this_week"};
        String[] values = {"1","2"};
        System.out.println(FileUtils.updateFile(filePath,keys,values));
    }

}
