import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    private ArrayList<Category> categories;
    private User currentUser;
    private Scanner cli = new Scanner();

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        if (currentUser == null){
            TextInput firstnamePrompt = new TextInput(cli, "Welcome, please enter your firstname: \n");
            String firstname = firstnamePrompt.ask().trim();
            TextInput namePrompt = new TextInput(cli, "please enter your name: \n");
            String name = namePrompt.ask().trim();
            currentUser = new User(firstname, name);
        }else {
            displayCategories();
        }
        if(categories.isEmpty()){
            TextInput creationPrompt = new TextInput(cli, "There is no categories, would you like to create a new one? (Y/N)\n", "Y", "N");
            if (creationPrompt.ask().trim() == "Y"){
                TextInput categoryNamePrompt = new TextInput(cli, "Please enter a name for your category: \n");
                categories.add(new Category(categoryNamePrompt.ask().trim()));
            }else {
                System.out.println("Closing app...");
            }
        }
    }

    private void displayCategories(){
        for (Category category: categories) {
            System.out.println(category.toString());
        }
    }
}
