public class Character {
    private String name;
    private int hp;
    private int attack;
    private double defense;
    private boolean alive;

    public Character(String name){
        this.name = name;
        hp = 100;
        attack = 10;
        defense = 0.5;
        alive = true;
    }

    public void print(){
        System.out.println("Name: " + name);
        System.out.println("Remaining HP: " + hp);
        System.out.println("Strength: "+ attack);
        System.out.println("Defense: " + defense);
        System.out.println("Status: "+ alive);
    }

    public void attack(Character opponent) {
        if (!opponent.alive) {
            System.out.println(opponent.name+" is not alive. No attack is done.");
            return;
        }
        double random = Math.random();
        if (random > opponent.getDefense()){
            opponent.setHp(opponent.getHp()-getAttack());
            System.out.println(name+ " successfully attacks "+ opponent.getName()+ ".");
        }
        else {
            System.out.println(opponent.getName()+ " is successfully defensed in this attack.");
        }
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        if (hp > 0){
            this.hp = hp;
        }
        else {
            this.hp = 0;
            alive = false;
            System.out.println(name+" is defeated.");
        }
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
