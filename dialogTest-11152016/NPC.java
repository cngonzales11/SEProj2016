import java.util.Random;
import java.util.ArrayList;

public class NPC
{

   //
   //ATTRIBUTES:
   //

   private String className;
   private int health;
   private int max_health;
   private weapon equipped;
   private ArrayList<feat> feats = new ArrayList<feat>();
   private Random r = new Random();
   
   //These variables are used in battle.
   private int initiative;
   private boolean damaged = false;
   private boolean mult_attack;
   private int defense;
   private int spellpower;
   
   //
   //CONSTRUCTOR:
   //
   
   //The constructor for a Non-Player Character.
   public NPC(String c, int h, int m, weapon e, int i, int d, int s)
   {
      className = c;
      health = h;
      max_health = m;
      equipped = e;
      initiative = i;
      defense = d;
      spellpower = s;
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
      initiative = amount;
   }
   
   //Sets the NPC's defense.
   public void setDef(int amount)
   {
      defense = amount;
   }
   
   //Sets the NPC's spellpower, if the NPC is a mage.
   public void setSP(int amount)
   {
      spellpower = amount;
   }
   
   //Sets the NPC's damaged attribute.
   public void setDamaged(boolean value)
   {
      damaged = value;
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
   
   //Returns the NPC's spellpower, if the NPC is a mage.
   public int getSP()
   {
      return spellpower;
   }
   
   //Returns if the NPC is damaged by poison, burn, etc.
   public boolean getDamaged()
   {
      return damaged;
   }
   
   public weapon getEquipped()
   {
      return equipped;
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
      System.out.println((atroll+weapon_bonus) + " vs. " + p.getDef());
      if(atroll+weapon_bonus > p.getDef())
      {
         System.out.println("Hit! Player suffers " + w.getDamage() + " damage!");
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
