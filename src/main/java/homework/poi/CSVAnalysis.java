package homework.poi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/7/10.
 */
public class CSVAnalysis {
    private InputStreamReader fr = null;
    private BufferedReader br = null;

    public CSVAnalysis(String f) throws IOException {
        fr = new InputStreamReader(new FileInputStream(f));
    }

    /**
     * 解析csv文件 到一个list中
     * 每个单元个为一个String类型记录，每一行为一个list。
     * 再将所有的行放到一个总list中
     * @return
     * @throws IOException
     */
    public List<List<String>> readCSVFile() throws IOException {
        br = new BufferedReader(fr);
        String rec = null;//一行
        String str;//一个单元格
        List<List<String>> listFile = new ArrayList<List<String>>();
        try {
            //读取一行
            while ((rec = br.readLine()) != null) {
                System.out.println(rec);
                Pattern pCells = Pattern
                        .compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
                Matcher mCells = pCells.matcher(rec);
                List<String> cells = new ArrayList<String>();//每行记录一个list
                //读取每个单元格
                while (mCells.find()) {
                    str = mCells.group();
                    str = str.replaceAll(
                            "(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
                    str = str.replaceAll("(?sm)(\"(\"))", "$2");
                    System.out.println(str);
                    if(str==""){
                        break;
                    }
                    cells.add(str);
                }
                listFile.add(cells);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                fr.close();
            }
            if (br != null) {
                br.close();
            }
        }
        return listFile;
    }

    public static void main(String[] args) throws Throwable {
        CSVAnalysis parser = new CSVAnalysis("F:\\dj.csv");//welfaretmp_gjqt1-6
        List<List<String>> lists = parser.readCSVFile();
        System.out.println(lists.size());
    }
}
