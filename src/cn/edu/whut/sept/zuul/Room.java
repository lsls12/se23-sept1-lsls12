package cn.edu.whut.sept.zuul;

import java.util.Set;
import java.util.HashMap;
/**
 * 房间类，包含整个房间的信息
 */
public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String,String> item;
    private HashMap<String,Integer> kg;
    /**
     * 初始化房间信息，包含房间信息和房间连接的其他房间的键值对信息
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        item = new HashMap<>();
        kg = new HashMap<>();
    }

    /**
     * 设定连接的房间
     * @param direction 该房间所在的方向
     * @param neighbor 邻近房间的名称
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }
    public void setItem(String itemname, String iteminformation)
    {
        item.put(itemname, iteminformation);
    }
    public void setKg(String itemname, Integer height)
    {
        kg.put(itemname, height);
    }

    /**
     * 获得当前房间的简略信息
     * @return 返回房间名
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * 获得当前房间的完整介绍
     * @return 返回房间名及连接的其他房间
     */
    public String getLongDescription()
    {
        return "您正" + description + "\n" + getItemString()+ "\n" + getExitString();
    }
    /*【修改点2】对下述函数进行了汉化
    public String getLongDescription()
    {
        return "You are  " + description + ".\n" + getExitString();
    }
     */

    /**
     * 得到该房间可以通往邻近房间的方向
     * @return 返回语句 出口为【可走方向】
     */
    private String getExitString()
    {
        String returnString = "可通向的地方:";
        /*【修改点2】对下述输出进行了汉化
        String returnString = "Exits:";
        */
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    private String getItemString()
    {
        String returnString = "此处的物品信息如下:";
        /*【修改点2】对下述输出进行了汉化
        String returnString = "Exits:";
        */
        Set<String> keys = item.keySet();
        for(String item1 : keys) {
            returnString +="\n" + item1+"----" + item.get(item1)+","+kg.get(item1)+"kg";
        }
        return returnString;
    }


    public int getItemkg(String i)
    {
        int sum = kg.get(i);
        return sum;
    }


    /**
     * 得到该方向的邻近的房间号
     * @param direction 离开房间的方向
     * @return 该方向邻接的房间名
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
}


