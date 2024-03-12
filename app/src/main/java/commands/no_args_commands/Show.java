package commands.no_args_commands;

import commands.Command;
import manager.CollectionManager;
import manager.ConsoleManager;

public class Show extends Command {

    public Show(ConsoleManager consoleManager, CollectionManager collectionManager) {
        super("show", "Выводит список элементов коллекции", consoleManager, collectionManager);
    }

    public void execute(String... args)  {
        if (this.collectionManager.size() > 0){
            this.consoleManager.print("Элементы коллекции:");
            this.collectionManager.getCollection().forEach(el -> this.consoleManager.print(el));
        } else {
            this.consoleManager.print("В коллекции пока нет элементов(");
        }
    }
}
