import java.util.ArrayList;
import java.util.Scanner;

public class Application{

    private ArrayList<Category> categories = new ArrayList<Category>();
    private User currentUser;
    private Scanner cli;

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        cli = SingletonScanner.getInstance().getScanner();
        if (currentUser == null){
            TextInput firstnamePrompt = new TextInput(cli, "Welcome, please enter your firstname: \n");
            String firstname = firstnamePrompt.ask().trim();
            TextInput namePrompt = new TextInput(cli, "please enter your name: \n");
            String name = namePrompt.ask().trim();
            currentUser = new User(firstname, name);
            System.out.println("Welcome " + currentUser + " !\n");
        }else {
            displayCategories();
        }
        if(categories.isEmpty()){
            TextInput categoryCreationPrompt = new TextInput(cli, "There is no categories, would you like to create a new one? (Y/N)\n", "Y", "N");
            if (categoryCreationPrompt.ask().trim().equals("Y")){
                TextInput categoryNamePrompt = new TextInput(cli, "Please enter a name for your category: \n");
                categories.add(new Category(categoryNamePrompt.ask().trim()));
                TextInput taskCreationPrompt = new TextInput(cli, "Would you like to create a new task? (Y/N)\n");

            }else {
                System.out.println("Closing app...");
            }
            run();
        }
    }

    private void displayCategories(){
        for (Category category: categories) {
            System.out.println(category.toString());
        }
    }
}
