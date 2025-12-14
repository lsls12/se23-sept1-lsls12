package cn.edu.whut.sept.zuul;

/**
 * 指令类，包含指令和方向
 */
public class Command
{
    private String commandWord;
    private String secondWord;

    /**
     * 创建指令，包括指令及方向
     * @param firstWord 指令
     * @param secondWord 如方向等的附加信息
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * 得到指令内容
     * @return firstword中的指令内容
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * 得到附加信息内容
     * @return secondWord中的附加信息
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * 判定指令是否为空
     * @return 判断式
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * 判定附加信息是否为空
     * @return 判断式
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}
