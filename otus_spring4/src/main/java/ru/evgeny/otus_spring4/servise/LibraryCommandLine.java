package ru.evgeny.otus_spring4.servise;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.evgeny.otus_spring4.servise.interfaces.IShellDataBaseServise;


@ShellComponent
public class LibraryCommandLine {
    private IShellDataBaseServise cli;

    @Autowired
    public LibraryCommandLine(IShellDataBaseServise cli) {
        this.cli = cli;
    }


    @ShellMethod("Start")
    public void start() {
        cli.initTables();
    }

    @ShellMethod("Search book by autor's name")
    public void searchn(@ShellOption String name) {
        cli.searchBookByAuthorName(name);
    }


    @ShellMethod("Search book by autor's lastname")
    public void searchl(@ShellOption String name) {
        cli.searchBookByAuthorLastName(name);
    }


    @ShellMethod("Search book by name")
    public void searchb(@ShellOption String name) {
        cli.searchBookByName(name);
    }


    @ShellMethod("Search book by publisher's name")
    public void searchp(@ShellOption String name) {
        cli.searchBookByPublisher(name);
    }
}
