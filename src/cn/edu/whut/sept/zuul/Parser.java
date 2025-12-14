package cn.edu.whut.sept.zuul;

import java.util.Scanner;

/**
 * 指令解析器类
 */
public class Parser
{
    private CommandWords commands;
    private Scanner reader;

    /**
     *指令组成无需输入，由解析器读取并结构化
     */
    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * 构造指令，返回构造好的指令结构
     * @return 结构化后的command类，若指令存在则word1为指令，word2为附加信息，若不存在则word1为null，若未输入附加指令如方向等则word2为null
     */
    public Command getCommand()
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();   
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        /*【维护点1】对下述函数进行了修改，引用的函数由int改为布尔值后对此处修改为判断式
        if(commands.isCommand(word1)) {
         */
        if(commands.isCommand(word1)!=-1) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2);
        }
    }

    /**
     * 展示指令类内容
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
