public class feat
{

   //
   //ATTRIBUTES:
   //
   
   private final String name;
   private final String desc;
   private final int attack_bonus;
   private final int damage;
   private final int time_damage;
   private final int health_bonus;
   private final int defense_bonus;
   private final String type;
   private final boolean contact;
   
   //
   //CONSTRUCTORS:
   //
   
   //The constructor for the feat class.
   public feat(String n, String d, int ab, int dmg, int td, int hb, int db, String t, boolean c)
   {
      name = n;
      desc = d;
      attack_bonus = ab;
      damage = dmg;
      time_damage = td;
      health_bonus = hb;
      defense_bonus = db;
      type = t;
      contact = c;
   }
   
   //
   //GETTERS:
   //
   
   //Returns the name of the feat.
   public String getName()
   {
      return name;
   }
   
   //Returns the description of the feat.
   public String getDesc()
   {
      return desc;
   }
   
   //Returns the attack bonus associated with the feat.
   public int getAB()
   {
      return attack_bonus;
   }
   
   //Returns the damage bonus associated with the feat.
   public int getDmg()
   {
      return damage;
   }
   
   //Returns the damage dealt over time for a spell or poison.
   public int getTD()
   {
      return time_damage;
   }
   
   //Returns the health bonus associated with the feat.
   public int getHB()
   {
      return health_bonus;
   }
   
   //Returns the damage bonus associated with the feat.
   public int getDB()
   {
      return defense_bonus;
   }
   
   //Returns the type of feat.
   public String getType()
   {
      return type;
   }

   public boolean getContact()
   {
      return contact;
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
