import java.util.ArrayList;

public class Task {
    public String name;
    private ArrayList<User> contributors;
    public String description;

    public Task(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.contributors.add(user);
    }

    public void addContributor(User user){
        this.contributors.add(user);
    }

    public void removeContributor(User user){
        if(this.contributors.contains(user)){
            this.contributors.remove(user);
        }else {
            System.out.println("Could not find user in contributors\n");
        }
    }

    public ArrayList<User> getContributors() {
        return contributors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("\n");
        for (User contributor : contributors) {
            sb.append(contributor.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
