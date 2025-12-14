package cn.edu.whut.sept.zuul;

/**
 *  命令指令类，包含具体的指令及判定
 */
public class CommandWords
{
    /*【修改点3】对下述编码中的命名进行了规范
    private static final String[] validCommands
     */
    private static final String[] ValidCommands = {
            "go", "quit", "help","back","roll","get","drop"
    };

    /**
     * 指令单词无需初始化，内部固定
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * 判断指令是否存在
     * @param aString 玩家输入的指令
     * @return 指令存在则返回ture，指令不存在则返回false
     */
    /* 【维护点1】对下述函数进行了修改，将布尔值变为int型方便进行switch操作
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return ture;
        }
        return false;
    }
     */
    public int isCommand(String aString)
    {
        for(int i = 0; i < ValidCommands.length; i++) {
            if(ValidCommands[i].equals(aString))
                return i;
        }
        return -1;
    }

    /**
     * 输出存在的所有指令
     */
    public void showAll()
    {
        for(String command: ValidCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
