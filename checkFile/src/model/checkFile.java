package model;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 痞老板
 * @Project: checkFile
 * @Package:model
 * @date 2018/5/20 11:27
 * @description
 **/
public class checkFile {
    public boolean checkfile(File file1) throws IOException {
//        File file1 =new File("G:\\software\\IntelliJ IDEA 2017.2\\project\\demo\\src\\testH5.html");
        System.out.println(file1);
        boolean bl = false;
        StringBuilder s = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file1), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                s.append(line + "\n") ;
            }
            reader.close();
            String b = "<coco>([\\s\\S]+?)</coco>";
            Pattern p = Pattern.compile(b);
            Matcher m = p.matcher(s.toString());
            StringBuilder sb = new StringBuilder();
            int start = 0;
            while (m.find()) {
                bl = true;
                start += m.start();
                sb = util.builderSet(sb, m.group());
                int oldlength = sb.length();
                sb = util.builderSet(sb, sb.substring(sb.indexOf(">") + 1, sb.lastIndexOf("<")).replaceAll("\n", "").trim());
                Pattern p2 = Pattern.compile("\\.\\./");
                Matcher m2 = p2.matcher(sb);
                int i = 0;
                File pare = file1.getParentFile();
                while (m2.find()) {
                    i++;
                    pare = pare.getParentFile();
                }
                if (i == 0) {
                    pare = new File(pare, sb.toString());
                } else {
                    sb = util.builderSet(sb, m2.replaceAll(""));
                    pare = new File(pare, sb.toString());
                }
                BufferedReader b2 = new BufferedReader(new InputStreamReader(
                        new FileInputStream(pare), "utf-8"));
                StringBuilder sq = new StringBuilder();
                String sa;
                while ((sa = b2.readLine()) != null) {
                    sq.append(sa + "\n");
                }
                sq.deleteCharAt(sq.length() - 1);
                b2.close();
                s.replace(start, start + oldlength, sq.toString());
                start = sq.length() - oldlength;
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
        catch (IOException e){
            throw e;
        }
        try {
            System.out.println(bl);
            if (bl) {
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file1), "utf-8");
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(s.toString());
                bw.close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
