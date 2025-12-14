package cn.edu.whut.sept.zuul;

import java.util.Set;
import java.util.HashMap;


public class User {
    private String name;
    private HashMap<String,Integer> kg;
    private int maxkg ;

    public User(String description)
    {
        this.name = description;
        kg = new HashMap<>();
        setKg("手机",3);
        maxkg = 20;
    }
    public void setKg(String itemname, Integer height)
    {
        kg.put(itemname, height);
    }

    public String getLongDescription()
    {
        return "您是" + name + "\n" + getItemString()+ "\n" ;
    }
    private String getItemString()
    {
        int sum=0;
        String returnString = "您携带的的物品信息如下:";
        /*【修改点2】对下述输出进行了汉化
        String returnString = "Exits:";
        */
        Set<String> keys = kg.keySet();
        for(String item1 : keys) {
            returnString +="\n" + item1+" ";
            sum += kg.get(item1);

        }
        returnString +="\n" + "总共" + sum + "kg";
        return returnString;
    }

    public boolean Isout(int i)
    {
        int sum=0;

        Set<String> keys = kg.keySet();
        for(String item1 : keys) {
            sum += kg.get(item1);

        }
        if(sum+i<=maxkg)
            return true;
        else  return false;

    }
    public String drop(String i)
    {
        if(kg.remove(i)!=null)
        {
            return i + "已丢弃";
        }
        else
        {
            return "未找到该物品";
        }
    }
}
