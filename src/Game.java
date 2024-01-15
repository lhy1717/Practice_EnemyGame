import javax.sql.rowset.Predicate;
import java.sql.SQLOutput;
import java.util.*;

public class Game {
    public static Character chooseType(int i){
        if (i == 0){
            return new Warrior("warrior");
        }
        else if (i == 1){
            return new Knight("knight");
        }
        return new Healer("healer");
    }

    public static int GameState(List<Character> player, List<Character> enemy){
        if (player.stream().noneMatch(Character::isAlive)){
            return -1;
        }
        else if (enemy.stream().noneMatch(Character::isAlive)){
            return 1;
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println("=====Welcome to [Game]=====");
        System.out.println("Before the game starts, you have to make up a team with maximum three members.");
        System.out.println("The number of your enemy is in a random basic, but the total number of your enemies will not more than the double of your team's member.");
        System.out.println("You can choose the type of your team member to fight against your opponent.");
        System.out.println("There are 3 types of characters in total: (Warrior, Knight, Healer)");
        System.out.println("1. Warrior has a higher attack attribute.");
        System.out.println("2. Knight has a higher defense value.");
        System.out.println("3. Healer can cure the team member who is still alive.");
        System.out.println("=====Game Setting=====");
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many team members you would like to have? (max:3)");
        int number = scanner.nextInt();
        while (number <= 0 || number > 3){
            System.out.print("Invalid number, please enter again.");
            number = scanner.nextInt();
            System.out.println();
        }
        List<Character> player = new ArrayList<>(number);
        for (int i = 0; i < number; i++){
            System.out.print("Choose the type for your team member "+i+": (0: Warrior, 1: Knight, 2/Invalid: Healer)");
            int type = scanner.nextInt();
            player.add(chooseType(type));
            System.out.println("=====Team member "+i+" is successfully created=====");
        }
        System.out.println("Here are your team member(s): ");
        for (int i = 0; i < number; i++){
            player.get(i).print();
            System.out.println("=======================");
        }
        System.out.println();
        System.out.println("=====Creating Enemy=====");
        int capacity = new Random().nextInt(1, number+1);
        List<Character> enemy = new ArrayList<>(capacity);
        Random num = new Random();
        for (int i = 0; i < capacity; i++){
            enemy.add(chooseType(num.nextInt(3)));
            enemy.get(i).print();
            System.out.println("=====Enemy "+i+" is successfully created=====");
        }
        System.out.println();
        int round = 0;
        System.out.println("=====Game Start=====");
        do {
            System.out.println("=====Round "+round+" Start=====");
            System.out.println("Attack (0) or Cure(1):");
            int choice = scanner.nextInt();
            System.out.println("Select the member: (if the member does not exist or is not alive, player is seen as giving up this round.)");
            int select = scanner.nextInt();
            System.out.println("Select the target: (if the target does not exist or is not alive, player is seen as giving up this round.)");
            int target = scanner.nextInt();
            System.out.println("Result of your turn:");
            if (select >= 0 || select < player.size()){
                if (!player.get(select).isAlive()){
                    System.out.println(player.get(select).getName()+ " is not alive. No action can be done.");
                }
                if (choice == 0) player.get(select).attack(enemy.get(target));
                else if (choice == 1 && player.get(select) instanceof Healer) ((Healer) player.get(select)).cure(player.get(target));
            }
            if (!enemy.stream().filter(Character::isAlive).findFirst().isPresent()){
                break;
            }
            System.out.println("Result of enemy's turn:");
            Character Enemy = enemy.stream().filter(Character::isAlive).findFirst().get();
            if (Enemy instanceof Healer){
                if (new Random().nextBoolean())
                    ((Healer) Enemy).cure(enemy.stream().filter(Character::isAlive).findFirst().get());
                else Enemy.attack(player.stream().filter(Character::isAlive).findFirst().get());
            }
            else {
                Enemy.attack(player.stream().filter(Character::isAlive).findFirst().get());
            }
            System.out.println("=====Your Team=====");
            player.forEach(Character::print);
            System.out.println("=====Enemy=====");
            enemy.forEach(Character::print);
            System.out.println("=====Round "+round+" End=====");
            round++;
        }while (GameState(player,enemy) == 0);

        scanner.close();
        if (GameState(player, enemy) == 1){
            System.out.println("Congratulations! You have defeated all the enemy!");
        }
        else {
            System.out.println("You are defeated.");
        }
        System.out.println("=====Game End=====");
    }

}
