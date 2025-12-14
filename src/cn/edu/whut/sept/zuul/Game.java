/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 1.0
 */
package cn.edu.whut.sept.zuul;

import java.util.Random;

/**
 * 游戏类，包含整个游戏的主体程序
 */
public class Game
{
    private Parser parser;
    private Room currentRoom;
    private User user;

    /**
     * 创建游戏并初始化内部数据和解析器.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * 创建所有房间对象并连接其出口用以构建迷宫.
     * 结构图如下所示
     *                     north
     *
     * west          pub<-outside->theater      east
     *                       ↓
     *                      lab-> office
     *
     *                     south
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;

        /*【修改点2】对下述函数进行了汉化
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
         */
        // create the rooms
        outside = new Room("在大学主入口外");
        theater = new Room("在讲堂里");
        pub = new Room("在校园酒吧");
        lab = new Room("在计算机实验室里");
        office = new Room("在计算机管理办公室");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setItem("猫","草丛里面竟然有一只猫");
        outside.setItem("大门","看上去很适合拍照");
        outside.setKg("猫",10);
        outside.setKg("大门",1000);

        theater.setExit("west", outside);
        theater.setItem("座位","做这么满感觉导员又在群里疯狂摇人");
        theater.setKg("座位",15);

        pub.setExit("east", outside);
        pub.setItem("吉他","我从来没有觉得组乐队开心过");
        pub.setKg("吉他",10);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.setItem("计算机","学校的计算机卡的要命，一台传三代");
        lab.setKg("计算机",10);

        office.setExit("west", lab);
        office.setItem("报名表","你想打ACM吗");
        office.setKg("报名表",1);

        user = new User("一名普普通通的大学生");
        currentRoom = outside;  // start game outside
    }

    /**
     *  游戏主控循环，直到用户输入退出命令后结束整个程序.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("感谢您的游玩");
        /*【修改点2】对下述输出进行了汉化
        System.out.println("Thank you for playing. Good bye.");
         */
    }

    /**
     * 向用户输出欢迎信息.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("欢迎游玩 World of Zuul!");
        System.out.println("World of Zuul 是一款全新、极其无聊的冒险游戏.");
        System.out.println("如果您需要帮助请输入 'help' ");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        /*【修改点2】对下述输出进行了汉化
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
         */
    }

    /**
     * 执行用户输入的游戏指令.
     * @param command 待处理的游戏指令，由解析器从用户输入内容生成.
     * @return 如果执行的是游戏结束指令，则返回true，否则返回false.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;
        CommandWords commandwords = new CommandWords();

        if(command.isUnknown()) {
            System.out.println("您输入的指令有误，请重新输入");
            /*【修改点2】对下述输出进行了汉化
            System.out.println("I don't know what you mean...");
            */
            return false;
        }

        String commandWord = command.getCommandWord();
        switch(commandwords.isCommand(commandWord)){
            case 0:
                goRoom(command);
                break;
            case 1:
                wantToQuit = quit(command);
                break;
            case 2:
                printHelp();
                break;
            case 3:
                back();
                break;
            case 4:
                roll();
                break;
            case 5:
                get(command);
                break;
            case 6:
                drop(command);
                break;
            default:
                break;
        }
        /*【维护点1】对下述if-else语句进行了switch优化
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
         */
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * 执行help指令，在终端打印游戏帮助信息.
     * 此处会输出游戏中用户可以输入的命令列表
     */
    private void printHelp()
    {
        System.out.println(user.getLongDescription());
        System.out.println("您可以输入的指令如下");
        parser.showCommands();
        /*【修改点2】对下述输出进行了汉化
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
         */
    }

    /**
     * 执行go指令，向房间的指定方向出口移动，如果该出口连接了另一个房间，则会进入该房间，
     * 否则打印输出错误提示信息.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("去哪？");
            /*【修改点2】对下述输出进行了汉化
            System.out.println("Go where?");
            return;
            */
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("此路不通！");
            /*【修改点2】对下述输出进行了汉化
            System.out.println("There is no door!");
            */
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * 执行Quit指令，用户退出游戏。如果用户在命令中输入了其他参数，则进一步询问用户是否真的退出.
     * @return 如果游戏需要退出则返回true，否则返回false.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("真的要离开吗？");
            /*【修改点2】对下述输出进行了汉化
            System.out.println("Quit What?");
             */
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    private void back()
    {
        switch(currentRoom.getShortDescription()){
            case "在讲堂里":
                Command t1 = new Command("go","west");
                goRoom(t1);
                break;
            case "在校园酒吧":
                Command t2 = new Command("go","east");
                goRoom(t2);
                break;
            case "在计算机实验室里":
                Command t3 = new Command("go","north");
                goRoom(t3);
                break;
            case "在计算机管理办公室":
                Command t4 = new Command("go","west");
                goRoom(t4);
                break;
            default:
                System.out.println("您已经没有退路了");
                break;
        }
    }
    private void roll()
    {
        Room outside, theater, pub, lab, office;

        /*【修改点2】对下述函数进行了汉化
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
         */
        // create the rooms
        outside = new Room("在大学主入口外");
        theater = new Room("在讲堂里");
        pub = new Room("在校园酒吧");
        lab = new Room("在计算机实验室里");
        office = new Room("在计算机管理办公室");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setItem("猫","草丛里面竟然有一只猫");
        outside.setItem("大门","看上去很适合拍照");
        outside.setKg("猫",10);
        outside.setKg("大门",1000);

        theater.setExit("west", outside);
        theater.setItem("座位","做这么满感觉导员又在群里疯狂摇人");
        theater.setKg("座位",15);

        pub.setExit("east", outside);
        pub.setItem("吉他","我从来没有觉得组乐队开心过");
        pub.setKg("吉他",10);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.setItem("计算机","学校的计算机卡的要命，一台传三代");
        lab.setKg("计算机",10);

        office.setExit("west", lab);
        office.setItem("报名表","你想打ACM吗");
        lab.setKg("报名表",1);
        Random rand = new Random();
        int randomNum = rand.nextInt(5);
        switch(randomNum) {
            case 0:
                currentRoom = outside;
                System.out.println(currentRoom.getLongDescription());
                break;
            case 1:
                currentRoom = pub;
                System.out.println(currentRoom.getLongDescription());
                break;
            case 2:
                currentRoom = lab;
                System.out.println(currentRoom.getLongDescription());
                break;
            case 3:
                currentRoom = theater;
                System.out.println(currentRoom.getLongDescription());
                break;
            case 4:
                currentRoom = office;
                System.out.println(currentRoom.getLongDescription());
                break;
            default:
                break;

        }
    }
    private void get(Command command)
    {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("拿取哪个物品？");
            /*【修改点2】对下述输出进行了汉化
            System.out.println("Go where?");
            return;

            */
            return;
        }

        String name = command.getSecondWord();
        int kg = currentRoom.getItemkg(name);
        if(user.Isout(kg))
        {
            user.setKg(name,kg);
            System.out.println(user.getLongDescription());
        }
        else {
            System.out.println("您拿不下这么多东西啦！");
        }

    }
    private void drop(Command command)
    {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("丢弃哪个物品？");
            /*【修改点2】对下述输出进行了汉化
            System.out.println("Go where?");
            return;

            */
            return;


        }
        String name = command.getSecondWord();
        System.out.println(user.drop(name));
        System.out.println(user.getLongDescription());
    }
}