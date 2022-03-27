import java.util.ArrayList;

public class Category {
    public String name;
    private ArrayList<Task> subtasks;

    public Category(String name) {
        this.name = name;
    }

    public void addTask(Task task){
        this.subtasks.add(task);
    }

    public void removeTask(Task task){
        if(this.subtasks.contains(task)){
            this.subtasks.remove(task);
        }else {
            System.out.println("Could not find task in this category\n");
        }
    }

    public void migrateTask(Task task, Category target){
        if(this.subtasks.contains(task)) {
            target.addTask(task);
            this.subtasks.remove(task);
        }else {
            System.out.println("Error trying to migrate the task, make sure this task exists in the parent category\n");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("\n");
        for (Task task : subtasks) {
            sb.append(task.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
