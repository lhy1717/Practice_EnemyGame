import java.util.Random;

public class Knight extends Character{
    public Knight(String name) {
        super(name);
        double randomNumber = Math.round(new Random().nextDouble(0.5,0.9)*10)/10.0;
        setDefense(randomNumber);
        int hp = new Random().nextInt(100,150);
        setHp(hp);
        int strength = new Random().nextInt(10,20);
        setAttack(strength);
    }
}
