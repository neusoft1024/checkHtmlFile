package model;

/**
 * @author 痞老板
 * @Project: checkFile
 * @Package:model
 * @date 2018/5/20 11:29
 * @description
 **/
public class util {
    public static StringBuilder builderSet(StringBuilder s,String value){
        s.delete(0,s.length());
        s.append(value);
        return  s;
    }
}
