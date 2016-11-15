public class item
{

   //
   //ATTRIBUTES:
   //
   
   private String name;
   private String desc;
   private String type;
   private int value;
   private int position;
   
   //
   //CONSTRUCTORS:
   //
   
   //The constructor for the item class.
   public item(String n, String d, String t, int v)
   {
      name = n;
      desc = d;
      type = t;
      value = v;
   }
   
   //
   //SETTERS:
   //
   public void setPos(int pos)
   {
      position = pos;
   }   
   
   //
   //GETTERS:
   //
   
   //Returns the name of the item.
   public String getName()
   {
      return name;
   }
   
   //Returns the description of the item.
   public String getDesc()
   {
      return desc;
   }
   
   //Returns the type of the item.  
   public String getType()
   {
      return type;
   }
   
   //Returns the value the item uses.
   public int getValue()
   {
      return value;
   }
   
   public int getPos()
   {
      return position;
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
