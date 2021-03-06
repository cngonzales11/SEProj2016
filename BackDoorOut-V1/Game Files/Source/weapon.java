public class weapon
{

   //
   //ATTRIBUTES:
   //
   
   private String name;
   private String desc;
   private String damage_type;
   private int damage;
   private int base_attack;
   private int attack_bonus;
   private int stat_bonus;
   
   //
   //CONSTRUCTORS:
   //
   
   //The constructor for the weapon class.
   public weapon(String n, String ds, String dt, int b, int d, int ab, int sb)
   {
      name = n;
      desc = ds;
      damage_type = dt;
      base_attack = b;
      damage = d;
      attack_bonus = ab;
      stat_bonus = sb;
   }
      
   //
   //GETTERS:
   //
   
   //Returns the name of the weapon.
   public String getName()
   {
      return name;
   }
   
   //Returns the description of the weapon.
   public String getDesc()
   {
      return desc;
   }
   
   //Returns the base attack of the weapon.
   public int getBase()
   {
      return base_attack;
   }

   //Returns the damage the weapon gives.
   public int getDamage()
   {
      return damage;
   }
   
   //Returns the attack bonus the weapon gives.
   public int getAtkB()
   {
      return attack_bonus;
   }

   //Returns the stat bonus the weapon gives.
   public int getStB()
   {
      return stat_bonus;
   }
   
   public String getDamageType()
   {
      return damage_type;
   }
   
   //
   //OTHER METHODS:
   //
      
   //Tells the system what to print if asked to print the object.
   public String toString()
   {
      return getName();
   }
}
