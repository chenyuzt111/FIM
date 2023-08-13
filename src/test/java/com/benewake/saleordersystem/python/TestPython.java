package com.benewake.saleordersystem.python;

import com.benewake.saleordersystem.SaleOrderSystemApplication;
import com.benewake.saleordersystem.utils.PythonUtils;
import org.apache.tomcat.jni.Proc;
import org.junit.jupiter.api.Test;
import org.python.core.*;
import org.python.util.PythonInterpreter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.*;

@SpringBootTest
@ContextConfiguration(classes = SaleOrderSystemApplication.class)
public class TestPython {

    @Test
    public void testOne() throws IOException {

        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        pythonInterpreter.execfile("F:\\study\\pythonStudy\\case1\\order_data_organization_fengzhuang.py");

        PyFunction pyFunction = pythonInterpreter.get("jisuan", PyFunction.class);
        String str1 = "each_product_people.csv",str2 = "data_process.csv",str3 = "history_product.csv";
        PyObject pyobj = pyFunction.__call__(new PyString("each_product_people.csv"),new PyString("data_process.csv"),new PyString("history_product.csv"));
        String res = pyobj.toString();
        System.out.println("result: "+res);
        pythonInterpreter.cleanup();
        pythonInterpreter.close();
    }

    @Test
    public void testTwo() throws InterruptedException, IOException {
        String py = "F:\\study\\pythonStudy\\FJSP2\\main.py";
        String[] args = {"test_data/origin_data/history_input_time.csv","test_data/origin_data/Order_processing_time_sequence.csv","test_data/origin_data/order_history_product.csv"};
        System.out.println(PythonUtils.doPython(py,args));
    }

}
