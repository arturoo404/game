package com.arturoo404.game.player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerStats {

    private int basicAttackRange = 200;
    private double attackSpeed = 1;
    private int maxHealth = 100;
    private double currentHealth = 100;
    private int maxMana = 100;
    private double currentMana = 40;
    private double healthRegen = 5;
    private double percentHealthRegen = (double) maxHealth / 200;
    private double manaRegen = 10;
    private double percentManaRegen = (double) maxMana / 200;
    private int armor = 0;
    private double cooldownReduction = 0;
    private int damage = 20;
    private int playerLvl = 1;
    private int currentLvl = 1;
    private int skillPoint = 50;
    private int abilityPoint = 0;

    public Integer basicAttackDamage(){
        return damage + currentLvl;
    }
}
