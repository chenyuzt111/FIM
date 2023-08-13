package com.benewake.saleordersystem.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lcs
 * 描 述 ：文件处理工具类
 */
public class FileUtils {

    /**
     * 换行符号
     */
    public static final String SEPARATOR = System.getProperty("line.separator");

    /**
     * 修改配置文件中对应key值的value
     * 文件每个参数设置对应一行
     * 如 :
     *  key1 = value1
     *  key2 = value2
     * @param filePath
     * @param keys
     * @param values
     * @return 修改的行数
     */
    public static int updateFile(String filePath,String[] keys,String[] values){
        int updateRow = 0;
        Map<String,Integer> map = new HashMap<>(keys.length);
        for(int i=0;i<keys.length; i++){
            map.put(keys[i],i);
        }
        try (
                FileReader fis = new FileReader(filePath);
                BufferedReader reader = new BufferedReader(fis);
        ){
            String str = null;
            StringBuilder sb = new StringBuilder();
            int ind = 0;
            while((str = reader.readLine()) != null){
                if((ind = str.indexOf("="))!=-1){
                    String head = str.substring(0,ind).trim();
                    // keys中有则更新相应的value
                    if(map.containsKey(head)){
                        str = head + " = " + values[map.get(head)];
                        ++updateRow;
                    }
                }
                sb.append(str+SEPARATOR);
            }
            // 写文件
            FileWriter fout = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fout);
            bw.write(sb.toString());
            bw.close();
            fout.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return updateRow;
    }

    /**
     * 删除指定文件夹下的所有文件  (不删除选定的文件夹)
     * @param file
     * @return 删除的文件数量
     */
    public static int removeFile(File file){
        File[] subFiles = file.listFiles();
        int deleteCnt = 0;
        if(subFiles == null || subFiles.length == 0) {
            return deleteCnt;
        }

        for (File subFile : subFiles) {
            if(subFile.isFile()){
                subFile.delete();
                ++deleteCnt;
            }else if(file.isDirectory()){
                deleteCnt+=removeFile(subFile);
                subFile.delete();
                ++deleteCnt;
            }
        }
        return deleteCnt;
    }
}
