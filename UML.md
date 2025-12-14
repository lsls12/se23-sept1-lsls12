~~~mermaid
classDiagram
CommandWords --> Command
Command --o Game
Parser ..> Command
Parser --o Game
Room --o Game
      class CommandWords{
        -String[] validCommands
        +isCommand(String aString) boolean
        +showAll() void
      }
      class Command{
         -String CommandWord
         -String secondWord
         +Command(String firstword,String secondword)
         +getCommandWord() String
         +getSecondWord() String
         +hasSecondWord() boolean
         +isUnknown() boolean
      }
      class Room{
          -String description
          -HashMap<String, Room> exits
          +getExit(String direction) Room
          +getlongDescription() String
          +getShortDescription() String
          +setExit(String direction,Room neighbor) void
      }
      class Parser{
         -CommandWords commands
         -Scanner reader
         +getCommand() Command
         +showCommands() void
      }
      class Game{
         -Parser parser
         -Room currentRoom
         +play()
         +createRooms() void
         +printWelcome() void
         +processCommand boolean
         +printHelp() void
         +goRoom(Command command) boolean
         +quit(Command command)　boolean
      }

~~~

