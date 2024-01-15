import java.util.Random;

public class Warrior extends Character{
    public Warrior(String name){
        super(name);
        int attack = new Random().nextInt(15,30);
        setAttack(attack);
    }
}
