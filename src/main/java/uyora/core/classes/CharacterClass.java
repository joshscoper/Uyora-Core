package uyora.core.classes;

public enum CharacterClass {

    FIGHTER("Fighter", 10, 10),
    PALADIN("Paladin", 10, 10),
    RANGER("Ranger", 10, 10),
    ROGUE("Rogue", 10, 10),
    CLERIC("Cleric", 10, 10),
    SORCERER("Sorcerer", 10, 10);

    private String path;

    CharacterClass(String path, double baseHealth, int mana){
        this.path = path;
    }

    public String getPath(){return path;}


}
