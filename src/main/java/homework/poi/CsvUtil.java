package homework.poi;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/7/10.
 */
public class CsvUtil {
    @Test
    public void readCsv() throws  Exception{
//        BufferedReader br = new BufferedReader(new FileReader("F:\\dj.csv"));
        BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream("F:\\dj.csv"),"UTF-8"));
        Pattern pattern = Pattern.compile("(,)?((\"[^\"]*(\"{2})*[^\"]*\")*[^,]*)");
        String strLine = null;
        while((strLine = br.readLine()) != null) {
            Matcher matcher = pattern.matcher(strLine);
            while(matcher.find()) {
                String cell = matcher.group(2);//group(2) is ((\"[^\"]*(\"{2})*[^\"]*\")*[^,]*)
                Pattern pattern2 = Pattern.compile("\"((.)*)\"");
                Matcher matcher2 = pattern2.matcher(cell);
                if(matcher2.find()) {
                    cell = matcher2.group(1);
                }
                System.out.println(new String(cell.getBytes(),"UTF-8"));
            }
        }

    }
    /**
     * 导入
     *
     * @return
     */
    @Test
    public void  importCsv(){
        File file=new File("F:\\2.csv");
        List<String> dataList=new ArrayList<String>();

        BufferedReader br=null;
        try {
             br =new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
            String line="";
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        }catch (Exception e) {
        }finally{
            if(br!=null){
                try {
                    br.close();
                    br=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Joiner joiner=Joiner.on("\n");
        System.out.println(joiner.join(dataList));
//        return dataList;
    }
}
