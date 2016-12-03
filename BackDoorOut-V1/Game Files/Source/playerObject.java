import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class playerObject
{
   //
   //ATTRIBUTES:
   //
   
   private String className;
   private int health;
   private ArrayList<weapon> inventory = new ArrayList<>();
   private ArrayList<item> inventory_item = new ArrayList<>();
   private ArrayList<feat> feats = new ArrayList<>();
   private weapon equipped;
   private weapon unarmed = new weapon("No Weapon", "", "Physical", 2, 6, 2, 0);
   private Random r = new Random();
   
   //These variables are used in battle.
   private int initiative;
   private boolean mult_attack;
   private boolean area_attack;
   private boolean full_attack;
   private boolean power_attack;
   private boolean over_time;
   private boolean half_damage;
   private boolean slow;
   private boolean defensive;
   private boolean spell;
   private boolean item_used = false;
   private int defense;
   private int spellpower;
   private int damage = 0;
   private int attack = 0;
   private int damage_ot = 0;
   private int item_bonus = 0;
   private int defense_bonus = 0;
   
   Scanner player_input = new Scanner(System.in);
   
   //
   //CONSTRUCTOR:
   //
   
   //The constructor for the playerObject.
   public playerObject(String c, int h, int i, int d, int sp)
   {
      className = c;
      health = h;
      initiative = i;
      defense = d;
      spellpower = sp;
   }
   
   //
   //SETTERS:
   //
   
   //Sets the player class.
   public void setType(String s)
   {
      className = s;
   }
   
   //Adds an amount to the player's health.
   public void setHealth(int amount)
   {
      if(health+amount <= 100) //Check for positive health addition (Healing, etc.)
      {
         health = health+amount; //If the health and amount is below 100, add the two.
      }
      else
      { 
         if(amount > 0)
         {
            health = 100; //Otherwise, set the health to 100.
         }
      }
   }
   
   //Resets the health variable.
   public void resetHealth()
   {
      health = 0;
   }
   
   //Sets the player's initiative.
   public void setInitiative(int amount)
   {
      initiative = amount;
   }
   
   //Sets the player's defense rating.
   public void setDef(int amount)
   {
      defense = amount;
   }
   
   //Sets the player's spellpower, if the player is a mage.
   public void setSP(int amount)
   {
      spellpower = amount;
   }
   
   //Sets the equipped weapon to a weapon in the player's inventory.
   public void setEquipped(int a)
   {
      equipped = getWeaponAt(a);
      inventory.remove(getWeaponAt(a));
   }
   
   //Equips the unarmed weapon when in battle.
   public void setUnarmed()
   {
      equipped = unarmed;
   }
   
   //Resets the unarmed status after battle.
   public void resetUnarmed()
   {
      equipped = null;
   }
      
   //Resets feats after use in battle.
   public void resetFeats()
   {
      mult_attack = false;
      area_attack = false;
      full_attack = false;
      power_attack = false;
      over_time = false;
      half_damage = false;
      slow = false;
      defensive = false;
   }
   
   //
   //GETTERS:
   //
   
   //Returns the player class.
   public String getType()
   {
      return className;
   }
  
   //Returns the player's health.
   public int getHealth()
   {
      return health;
   }
   
   //These getters relate to battle.
   
   //Returns the player's initiative.
   public int getIni()
   {
      return initiative;
   }
   
   //Returns the player's defense rating.
   public int getDef()
   {
      return defense;
   }
   
   //Returns the player's spellpower, if the player is a mage.
   public int getSP()
   {
      return spellpower;
   }
   
   public int getDefB()
   {
      return defense_bonus;
   }

   //Returns the size of the Item Inventory.
   public int getItemSize()
   {
      return inventory_item.size();
   }
   
   //Returns the size of the Weapon Inventory.
   public int getWeaponSize()
   {
      return inventory.size();
   }
   
   //Returns what item is at the given reference.
   public item getItemAt(int a)
   {
      item selection = null;
      boolean breakloop = false;
      for(item i: inventory_item)
      {
          if(i.getNum() == a && !breakloop)
          {
              selection = i;
              breakloop = true;
          }
      }
      return selection;
   }
   
   //Returns what item is at the given reference.
   public weapon getWeaponAt(int a)
   {
      return inventory.get(a-1);
   }
   
   //Returns the feat at the selected place in the Feat List.
   public feat getFeatAt(int a)
   {
      return feats.get(a-1);
   }
   
   public weapon getEquipped()
   {
      return equipped;
   }
   
   public boolean getUnarmed()
   {
       return equipped == unarmed;
   }
   
   //
   //ADD METHODS:
   //
   
   //Adds weapons to the Weapon Inventory.
   public void addToInventory(weapon w)
   {
      inventory.add(w);
   }
   
   //Adds items to the Item Inventory.
   public void addToInventory_item(item i)
   {
      boolean added = false;
      int size = inventory_item.size();
      int add_position = -1;
      for(int a = 0; a < size; a++)
      {
         if(inventory_item.get(a).getName().equals(i.getName()) && !added)
         {
            add_position = inventory_item.indexOf(inventory_item.get(a));
            //System.out.println(i + " will be added at " + add_position); DEBUG: Shows what position the item is entered in.
            added = true;
         }
      }
      if(added)
      {
         inventory_item.add(add_position, i);
      }
      else
      {
         //System.out.println("New Item! " + i + " will be added at " + (size+1)); DEBUG: New item message.
         inventory_item.add(i);
      }
   }
   
   //Adds a feat to the Feat List.
   public void addFeat(feat f)
   {
      feats.add(f);
   }
   
   //Removes all feats from the player.
   public void removeFeats()
   {
      int size = feats.size();
      for(int i = 0; i < size; i++)
      {
          //System.out.println("Removed " + feats.get(0)); //DEBUG: Shows what feat was removed.
          feats.remove(0);
      }
   }
   
   //Removes all weapons and items from the player's inventory.
   public void removeItems()
   {
      int size_items = inventory_item.size();
      int size_weapons = inventory.size();
      for(int i = 0; i < size_items; i++)
      {
          //System.out.println("Removed " + inventory_item.get(0)); //DEBUG: Shows what item was removed.
          inventory_item.remove(0);
      }
      for(int i = 0; i < size_weapons; i++)
      {
          //System.out.println("Removed " + inventory.get(0)); //DEBUG: Shows what weapon was removed.
          inventory.remove(0);
      }
   }
   
   //
   //LIST METHODS:
   //
   
   //Shows what the inventory has.
   public void showInventory()
   {
      if(inventory.size() == 0 && inventory_item.size() == 0)
      {
         System.out.println("No Items! Try opening the Test Chest.");
      }
      else
      {
         showInventory_weapon();
         showInventory_item();
      }
   }
   
   //Shows the Weapon Inventory only.
   public void showInventory_weapon()
   {
      int pos = 1;
      System.out.println("Weapons:");
      if(getWeaponSize() == 0)
      System.out.println("None");
      else
      {
         for(weapon w: inventory)
         {
            System.out.println(pos + " - " + w);
            pos++;
         }
      }
   }
   
   //Shows the Item Inventory only.
   public void showInventory_item()
   {
      int pos = 1;
      System.out.println("Items:");
      if(getItemSize() == 0)
      System.out.println("None");
      else
      {
         String previous = inventory_item.get(0).getName();
         int numberofitems = 0;
         for(item i: inventory_item)
         {
            if(i.getName() == previous)
            {
                i.setNum(pos);
                numberofitems++;
            }
            else
            {
                System.out.println(pos + " - " + previous + " (" + numberofitems + ")");
                numberofitems = 1;
                pos++;
                i.setNum(pos);
            }
            previous = i.getName();
            //System.out.println(inventory_item.indexOf(i) + " " + (inventory_item.size()-numberofitems)); DEBUG: Shows the index of the item and the size of the modified item inventory.
            if(inventory_item.indexOf(i) == inventory_item.size()-numberofitems)
            {
                System.out.println(pos + " - " + previous + " (" + numberofitems + ")");
                numberofitems = 0;
                pos = 1;
            }
            //System.out.println(pos + " - " + i);
            //pos++;
         }
      }
   }
   
   //Lists the player's available feats.
   public void listFeat()
   {
      int pos = 1;
      System.out.println("Feats: ");
      for (feat f: feats)
      {
         System.out.println(pos + " - " + f);
         pos++;
      }
   }
   
   public void describeFeat()
   {
      int select;
      listFeat();
      select = player_input.nextInt();
      System.out.println(getFeatAt(select) + "\n" + getFeatAt(select).getDesc());
   }
   
   //
   //USE METHODS:
   //
   
   //Gives the player a specific feat value, depending on the feat's type specifier.
   public void use_feat(feat f)
   {
      System.out.println("You attacked with " + f.getName());
      if(f.getContact() == true)
      {
         damage += f.getDmg();
         attack += f.getAB();
      }
      else
      {
         damage = item_bonus;
         attack = item_bonus;
      }
      if(f.getType() == "Attack Twice")
      {
         mult_attack = true;
      }
      if(f.getType() == "Area Attack")
      {
         area_attack = true;
      }
      if(f.getType() == "Full Attack")
      {
         full_attack = true;
      }
      if(f.getType() == "Power Attack")
      {
         power_attack = true;
      }
      if(f.getType() == "Over-Time")
      {
         over_time = true;
         damage_ot = f.getTD();
      }
      if(f.getType() == "Half Damage")
      {
         half_damage = true;
      }
      if(f.getType() == "Slow")
      {
          slow = true;
      }
      if(f.getType() == "Defensive")
      {
          defensive = true;
          defense_bonus = f.getDB();
          setHealth(-f.getHB());
      }
      if(f.getContact() == false)
      {
         spell = true;
      }
      else
      {
         spell = false;
      }
   }
   
   //Decides what effects and item has based on its type specifier.
   public void use(item i)
   {
      System.out.println("You used the "+i.getName()+".");
      if(i.getType() == "Healing")
      {
         int remainder;
         if(health == 0)
         {
            System.out.println("It won't have any effect.");
            item_used = false;
         }
         else
         {
            if(health>i.getValue())
            {
               health = health-i.getValue();
               System.out.println("You recovered "+i.getValue()+" HP.");
               inventory_item.remove(i);
            }
            else
            {
               remainder = health;
               health = 0;
               System.out.println("You recovered "+remainder+" HP.");
               inventory_item.remove(i);
            }
            item_used = true;
         }
      }
      
      if(i.getType() == "Boost")
      {
         item_bonus += i.getValue();
         System.out.println("Attack and Damage Boosted by " + i.getValue() + ".");
         inventory_item.remove(i);
         item_used = true;
      }
   }
   
   //
   //ACTION METHODS:
   //
   
   public void unequip()
   {
      inventory.add(equipped);
      equipped = null;
   }
      
   //Simulates rolling a d20 for attacks and skill checks.
   public int makeRoll()
   {
      return r.nextInt(20)+1;
   }
   
   //Displays a menu for equipping a weapon.
   public void equipWeapon()
   {
      int s_w;
      showInventory_weapon();
      if(getWeaponSize() != 0)
      {
         System.out.println("Select weapon");
         s_w = player_input.nextInt();
         setEquipped(s_w);
         System.out.println("You equipped the " + equipped.getName());
      }
   }
   
   //Displays the Attack Menu for the player.
   public boolean attackMenu(ArrayList<NPC> en, weapon w)
   {
      boolean f = false; //This is returned by the method to show when to end the battle.
      int sb_p = 0; //Used by the switch statement to select which option the player wants.
      int s_feat, s_item; //Used to select feats and/or items in another switch statement.
      
      if(!f)//"If the loop is still repeating..."
      {
         for(int a = 0; a < en.size(); a++) //"For each enemy..."
         {
            if(sb_p !=4)   //This will only run if the player did not select "Run".
            {
               if(en.get(a).getHealth() > 0) //"If the enemy still has health..."
               {
                  for(int c = 0; c < en.size(); c++)
                  {
                     if(en.get(c).getDamaged() && en.get(c).getHealth() > 0)
                     {
                        System.out.print(en.get(c).getType() + " suffers " + damage_ot + " burn damage. (Health: ");
                        en.get(c).setHealth(damage_ot, false);
                        System.out.println(en.get(c).getHealth() + ")");
                     }
                  }
                  do
                  {
                     System.out.println("CPU Usage: " + getHealth()); //Displays the player's health.
                     System.out.println(en.get(a) + " selected (Health: " + en.get(a).getHealth() + ")"); //Displays the selected enemy.
                     System.out.println("1 - Attack\n2 - Use Feat\n3 - Use Item"); //Prints the menu for the player.
                     sb_p = player_input.nextInt(); //Asks for the player's input.
                     
                     switch(sb_p)
                     {
                        case 1: //The base Attack case.
                           damage = item_bonus;
                           attack = item_bonus;
                           attack(en.get(a), w);
                           break;
                        case 2: //The Use Feat case.
                           damage = item_bonus;
                           attack = item_bonus;
                           listFeat();
                           s_feat = player_input.nextInt();
                           use_feat(getFeatAt(s_feat));
                           //Test for Flurry
                           if(mult_attack)
                           {
                              for(int i = 0; i < 2; i++)
                                 attack(en.get(a), w);
                           }
                           if(power_attack || half_damage)
                           {
                              attack(en.get(a), w);
                           }
                           if(defensive)
                           {
                              attack(en.get(a), w);
                           }
                           //Test for Spells
                           if(spell)
                           {
                              if(full_attack)
                              {
                                 int range = en.size();
                                 int selected = a;
                                 for(int b = 0; b < en.size(); b++)
                                 {
                                    selected = a+b;
                                    //System.out.println(selected + " " + range);  DEBUG: Shows what the selected element is and what range can be selected.
                                    if(selected < range)
                                       attack_spell(en.get(selected), getFeatAt(s_feat));
                                    else
                                    {
                                       selected = 0;
                                       attack_spell(en.get(selected), getFeatAt(s_feat));
                                    }
                                 }
                                 a = en.size()-1;
                              }
                              if(area_attack)
                              {
                                 int range = en.size();
                                 int selected = a;
                                 for(int b = 0; b < 2; b++)
                                 {
                                    selected = a+b;
                                    int c = 0;
                                    //System.out.println(selected + " " + range);  DEBUG: Shows what the selected element is and what range can be selected.
                                    if(selected < range)
                                       attack_spell(en.get(selected), getFeatAt(s_feat));
                                    else
                                    {
                                       selected = 0;
                                       attack_spell(en.get(selected), getFeatAt(s_feat));
                                    }
                                 }
                                 a = selected;
                                 resetFeats();
                              }
                              else
                              {
                                 if(!full_attack)
                                    attack_spell(en.get(a), getFeatAt(s_feat));
                              }
                           }
                           resetFeats();
                           break;
                        case 3: //The Use Item case.
                           showInventory_item();
                           if(getItemSize() != 0)
                           {
                              System.out.println("Select an item...");
                              s_item = player_input.nextInt();
                                  if(getItemAt(s_item) != null)
                                  {
                                      use(getItemAt(s_item));
                                  }
                                  else
                                  {
                                      System.out.println("Not a valid number.");
                                  }
                              //use(getItemAt(s_item), en); The original code.
                           }
                           break;
                        case 4: //The Run case.
                           //End of BattleSim code.
                           System.out.println("EXITING BATTLE SIMULATION...");
                           f = true;
                           break;
                     }
                     //System.out.println(sb_p + " " + item_used); DEBUG: Shows the switch case number and the item_used value.
                  }while(sb_p == 3 && !item_used); //"While the "Use Item" option is selected and an item is not used to some effect..."
               }
            }
         }
         resetFeats();
      }
         
      int remaining = 0; //Counts the remaining enemies after one round.
      
      if(sb_p != 4) //This will only run if the player did not select "Run".
      {
         for(NPC n: en) //Checks each enemies health.
         {
            if(n.getHealth() > 0) //"If the current enemy still has health..."
            {
               remaining++; //"...the enemy is accounted for."
            }
         }
         
         if(remaining == 0) //"If there are no remaining enemies..."
         {
            f = true; //"...the loop is finished."
         }
         else //"If not..."
         {
            f = false; //"...the loop continues."
         }
         
         if(f && remaining == 0) //"If the loop finish variable is satisfied and no enemies remain..."
         {
            System.out.println("You win!"); //"...display the winning message."
            damage = 0;
            attack = 0;
            item_bonus = 0;
         }
      
         System.out.println("Enemies Remaining: " + remaining); //Shows how many enemies remain.
      }
      
      return f; //Returns the finish variable.
   }
   
   //Calculates if an attack hits and what damage it gives.
   public void attack(NPC n, weapon w) //"If the attack is a weapon..."
   {
      int atroll;
      atroll = makeRoll();
      int tot_attack;
      if(equipped.getDamageType().equals("Electric") || equipped.getDamageType().equals("Physical"))
      {
         tot_attack = atroll+w.getBase()+w.getAtkB()+attack+w.getStB();
      }
      else
      {
         tot_attack = atroll+w.getBase()+w.getAtkB()+attack;
      }
      if(tot_attack > n.getDef() && atroll != 1)
      {
         int tot_damage;
         if(half_damage)
         {
            tot_damage = n.getMaxHealth()/2 + damage;
         }
         else
         {
            if(equipped.getDamageType().equals("Fire") || equipped.getDamageType().equals("Physical"))
            {
               tot_damage = w.getDamage() + damage + w.getStB();
            }
            else
            {
               tot_damage = w.getDamage() + damage;
            }
         }
         System.out.print("Hit! " + n.getType() + " suffers " + tot_damage + " damage! (Health: ");
         n.setHealth(tot_damage, false); //"...the weapon's damage will be put into the damage attribute of the respective weapon class."
         System.out.println(n.getHealth() + ")");
         if(over_time)
         {
            n.setDamaged(true);
            System.out.println(n.getType() + " is burned!");
         }
      }
      else
      {
         System.out.println("Miss. " + n.getType() + " suffers no damage. (Health: " + n.getHealth() + ")");
      }
   }
   
   //Calculates if a spell hits and what damage it gives.
   public void attack_spell(NPC n, feat f) //"If the attack is a spell..."
   {
      int atroll;
      atroll = makeRoll();
      int tot_attack;
      if(equipped.getDamageType().equals("Electric"))
      {
         tot_attack = atroll+f.getAB()+spellpower+attack+equipped.getStB();
      }
      else
      {
         tot_attack = atroll+f.getAB()+spellpower+attack;
      }
      int damage_s = f.getDmg()+r.nextInt(12);
      if(tot_attack > n.getDef() && atroll != 1)
      {
         int tot_damage;
         if(equipped.getDamageType().equals("Fire"))
         {
            tot_damage = damage_s + damage + equipped.getStB();
         }
         else
         {
            tot_damage = damage_s + damage;
         }
         //System.out.println(damage + " " + " " + damage_s + " " + tot_damage); //DEBUG: Prints the damage variables inputted.
         System.out.print("Hit! " + n.getType() + " suffers " + tot_damage + " damage! (Health: ");
         n.setHealth(tot_damage, false); //"...the spell's damage will be put into the damage attribute of the respective feat class."
         System.out.println(n.getHealth() + ")");
         if(over_time)
         {
         n.setDamaged(true);
         System.out.println(n.getType() + " is burned!");
         }
         if(slow)
         {
             n.setInitiative(-3);
             n.setATR(2);
             System.out.println(n.getType() + " is chilled!");
             System.out.println("Intiative: " + n.getIni() + " Attack Reduction: " + n.getATR());
         }
      }
      else
      {
         System.out.println("Miss. " + n.getType() + " suffers no damage.");
      }
   }   
}
