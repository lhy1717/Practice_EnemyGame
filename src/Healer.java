public class Healer extends Character{
    public Healer(String name) {
        super(name);
        setDefense(0.3);
    }

    public void cure(Character character){
        if (!character.isAlive()){
            System.out.println(character.getName()+" is not alive. No cure is done.");
            return;
        }
        character.setHp(character.getHp()+getAttack());
        System.out.println(character.getName()+ " is cured by "+ getName() + ".");
    }
}
