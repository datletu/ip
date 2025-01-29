package kunkka.storage;

import java.util.Scanner;

import kunkka.components.*;
import kunkka.parser.Parser;
import kunkka.tasklist.Tasklist;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    protected String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public Tasklist load() {
        //Load tasks from file
        Tasklist tasks = new Tasklist();
        try {
            File file = new File("./data/kunkka.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskLine = fileScanner.nextLine();
                tasks.addTask(Parser.parseTask(taskLine));
            }
            fileScanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            try {
                File file = new File("./data/kunkka.txt");
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            } catch (IOException ioException) {
                System.out.println("An error occurred while creating the file.");
                return null;
            }
        }
    }

    public void save(Tasklist taskslist) {
        //Save tasks to file
        try {
            FileWriter fileWriter = new FileWriter("./data/kunkka.txt");
            List<Task> tasks = taskslist.getTasks();
            for (Task task : tasks) {
                fileWriter.write(task.getType() + " | " + (task.getIsDone() ? "1" : "0") + " | " + task.getName());
                if (task.getType().equals("D")) {
                    fileWriter.write(" | " + ((Deadline) task).getBy());
                } else if (task.getType().equals("E")) {
                    fileWriter.write(" | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo());
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
        }
    }
}
