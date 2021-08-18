import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> todoList;

    public static void main(String[] args) {
        String separator = "------------------------------------------------------------------";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke :)");
        System.out.println("What can I do for you?");
        System.out.println(separator);

        todoList = new ArrayList<>();

        String endCmd = "bye";
        String listCmd = "list";
        String doneCmd = "done";
        String todoCmd = "todo";
        String deadlineCmd = "deadline";
        String eventCmd = "event";

        Scanner sc = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            try {
                String input = sc.nextLine();
                String[] inputs = input.split(" ", 2);
                String cmd = inputs[0];
                String description = inputs.length > 1 ? inputs[1] : "";
                System.out.println(separator);

                if (cmd.equals(endCmd)) {
                    System.out.println("Bye bye! See you again soon!");
                    end = true;
                } else if (cmd.equals(listCmd)) {
                    displayList();
                } else if (cmd.equals(doneCmd)) {
                    markTaskDone(Integer.parseInt(description));
                } else if (cmd.equals(todoCmd)) {
                    addTask(todoCmd, description);
                } else if (cmd.equals(deadlineCmd)) {
                    addTask(deadlineCmd, description);
                } else if (cmd.equals(eventCmd)) {
                    addTask(eventCmd, description);
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            } finally {
                System.out.println(separator);
            }
        }
    }

    private static void displayList() {
        System.out.println("Your task list:");
        for (int i = 0; i < todoList.size(); i++) {
            Task task = todoList.get(i);
            int num = i+1;
            System.out.println(num + "." + task.toString());
        }
    }

    private static void addTask(String taskType, String details) throws DukeException {
        Task task;
        if (taskType == "todo") {
            task = new ToDo(details);
        } else if (taskType == "deadline") {
            int position = details.indexOf("/by");
            String description, by;
            if (position >= 0) {
                description = details.substring(0, position);
                by = details.substring(position + 3);
            } else {
                throw new DukeException("Please indicate the deadline eg \"/by Sunday\" ");
            }
            task = new Deadline(description.trim(), by.trim());
        } else if (taskType == "event") {
            int position = details.indexOf("/at");
            String description, at;
            if (position >= 0) {
                description = details.substring(0, position);
                at = details.substring(position + 3);
            } else {
                throw new DukeException("Please indicate the event time eg \"/at Mon 2-4pm\" ");
            }
            task = new Event(description.trim(), at.trim());
        } else {
            return;
        }
        todoList.add(task);
        System.out.println("I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + todoList.size() + " tasks in the list.");
    }

    private static void markTaskDone(Integer taskNum) {
        Task task = todoList.get(taskNum-1);
        task.markAsDone();
        System.out.println("Good work! I've marked this task as done:");
        System.out.println(task.toString());
    }
}
