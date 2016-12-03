import java.util.Random;

public class NPC
{

   //
   //ATTRIBUTES:
   //

   private String className;
   private int health;
   private int max_health;
   private weapon equipped;
   private Random r = new Random();
   
   //These variables are used in battle.
   private int initiative;
   private int attack_re;
   private boolean damaged = false;
   private int defense;
   
   //
   //CONSTRUCTOR:
   //
   
   //The constructor for a Non-Player Character.
   public NPC(String c, int h, int m, weapon e, int i, int d)
   {
      className = c;
      health = h;
      max_health = m;
      equipped = e;
      initiative = i;
      defense = d;
   }
   
   //
   //SETTERS:
   //
   
   //Sets the NPC's health.  Uses a reset boolean to verify a reset command.
   public void setHealth(int amount, boolean reset)
   {
      if(health-amount >= 0)
         health = health-amount;
      else if(amount > 0)
         health = 0;
      if(amount == max_health && reset == true)
         health = max_health;
   }
   
   //Sets the NPC's initiative.
   public void setInitiative(int amount)
   {
      initiative += amount;
   }
      
   //Sets the NPC's damaged attribute.
   public void setDamaged(boolean value)
   {
      damaged = value;
   }
   
   //Sets the NPC's attack reducer.
   public void setATR(int amount)
   {
      attack_re += amount;
   }
   
   //
   //GETTERS:
   //
   
   //Returns the NPC's class.
   public String getType()
   {
      return className;
   }
   
   //Returns the NPC's health.
   public int getHealth()
   {
      return health;
   }
   
   //Returns the NPC's maximum health.
   public int getMaxHealth()
   {
      return max_health;
   }

   //Returns the NPC's initiative.
   public int getIni() 
   {
      return initiative;
   }
   
   //Returns the NPC's defense.
   public int getDef()
   {
      return defense;
   }
   
   //Returns if the NPC is damaged by poison, burn, etc.
   public boolean getDamaged()
   {
      return damaged;
   }
   
   //Returns the NPC's equipped weapon.
   public weapon getEquipped()
   {
      return equipped;
   }
   
   //Returns the NPC's attack reducer.
   public int getATR()
   {
       return attack_re;
   }
   
   //
   //ACTION METHODS:
   //
   
   //Simulates rolling a d20 for attack and skill checks.
   public int makeRoll()
   {
      return r.nextInt(20)+1;
   }
   
   //Calculates if an attack hits and what damage it gives.
   public void attack(playerObject p, weapon w)
   {
      int atroll;
      atroll = makeRoll();
      int weapon_bonus = w.getBase() + w.getAtkB();
      int player_def;
      if(p.getEquipped().getDamageType().equals("Cold"))
      {
          player_def = p.getDef() + p.getEquipped().getStB() + p.getDefB();
      }
      else
      {
          player_def = p.getDef() + p.getDefB();
      }
      System.out.println((atroll+weapon_bonus-attack_re) + " vs. " + player_def);
      if(atroll+weapon_bonus-attack_re > player_def && atroll != 1)
      {
         int player_damage;
         if(p.getEquipped().getDamageType().equals("Cold"))
         {
            player_damage = w.getDamage()-p.getEquipped().getStB();
            if(player_damage < 0)
            {
               player_damage = 0;
            }
         }
         else
         {
            player_damage = w.getDamage();
         }
         System.out.println("Hit! Player suffers " + player_damage + " damage!");
         p.setHealth(w.getDamage());
         System.out.println("CPU Usage: " + p.getHealth());
      }
      else
      {
         System.out.println("Miss! Player suffers no damage.");
      }
   }
      
   //
   //OTHER METHODS:
   //
   
   //Tells the system what to print if asked to print the object.
   public String toString()
   {
      return getType();
   }
}
