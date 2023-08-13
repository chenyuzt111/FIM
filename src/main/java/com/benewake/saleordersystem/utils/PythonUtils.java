package com.benewake.saleordersystem.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lcs
 * 描 述：调用python脚本的工具类
 * 注 意：其中python脚本中涉及到的文件路径要设置成绝对路径
 */
public class PythonUtils {

    private static String[] tansfer(String script,String url,String[] args){
        String[] res = new String[2+args.length];
        res[0] = script;
        res[1] = url;
        for(int i=0;i<args.length;++i){
            res[2+i] = args[i];
        }
        return res;
    }

    /**
     * 执行python脚本 并只会打印python中的输出（需要获取返回值请另写对result的处理逻辑）
     * @param pyScript python脚本文件的绝对地址
     * @param args 传入的参数 可为空
     * @return 只有返回值为0时才表示成功，其他数值均表示失败
     * @throws IOException
     * @throws InterruptedException
     */
    public static int doPython(String pyScript,String[] args) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(tansfer("python",pyScript,args));
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String result = "";
        while((line = in.readLine())!=null){
            result += line;
        }
        System.out.println(result);
        return process.waitFor();
    }
}
