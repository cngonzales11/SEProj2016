import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class dialogTest
{
   //
   //ATTRIBUTES:
   //
   
   Random r = new Random();
   Scanner kb = new Scanner(System.in);
   private final int EXIT = 12;
   private int ini_test = 0;

   //The Player
   playerObject p = new playerObject("None", 0, 84, 20, 12, 0);
   
   //Assorted Weapons
   weapon dagger = new weapon("Dagger", "A small blade used by most rogues", 3, 8, 1, 0);
   weapon test_blade = new weapon("Test Blade", "A simple weapon comprised of Java code.", 4, 16, 3, 0); //Possibly secret weapon in the game?
      
   //Assorted Items
   item restorative = new item("Restorative", "A compact version of a Malicious Software Removal Tool.", "Healing", 53);
   item boost = new item("Boost Potion", "Task Manager: Helps clear up those congested systems!", "Boost", 4);
   item firebomb = new item("Fire Bomb", "A firewall in a conveniently small package.", "Grenade", 12);
   
   //The testing feats.
   feat flurry = new feat("Flurry", "Allows the user to attack more than once a round.", 0, 2, 0, 0, 0, "Attack Twice", true);
   feat power_attack = new feat("Power Attack", "Puts all the user's strength into one swing.", -3, 8, 0, 0, 0, "Power Attack", true);
   feat lightning = new feat("Lightning", "A charged blast fires from the user's hands.", 2, 20, 0, 0, 0, "Area Attack", false);
   feat fire = new feat("Ignition", "Your enemies spark and burst into flames", -1, 9, 3, 0, 0, "Over-Time", false);
   
   //An NPC ArrayList for battles.
   NPC testEnemy = new NPC("Pop-Up", 100, 100, dagger, 13, 10, 0);
   NPC testEnemy2 = new NPC("Pop-Up2", 100, 100, dagger, 18, 10, 0);
   NPC testEnemy3 = new NPC("Pop-Up3", 100, 100, dagger, 9, 10, 0);
   ArrayList<NPC> enemies = new ArrayList<NPC>();
   
   //
   //METHODS:
   //
   
   public void open() //The method responsible for showing the menu and accepting user input.
   {
      int select, s, sb, sc;
      
      do
      {
      System.out.println("Select your class: \n1 - Warrior\n2 - Rogue\n3 - Mage");
      sc = kb.nextInt();
      
      //Select your class.
      switch(sc)
      {
         case 1:
            p.setType("Warrior");
            break;
         case 2:
            p.setType("Rogue");
            break;
         case 3:
            p.setType("Mage");
            break;
      }
      }
      while(sc <= 0 || sc > 3);
      
      //Adds a feat depending on class choice.
      if(p.getType() == "Rogue")
         p.addFeat(flurry);
      if(p.getType() == "Warrior")
         p.addFeat(power_attack);
      if(p.getType() == "Mage")
      {
         p.addFeat(lightning);
         p.addFeat(fire);
         p.setSP(25);
      }
      
      //The main menu, with respective switch cases.
      do
      {
         System.out.println("Select an option");
         System.out.println("1 - Set HP to 100\n2 - Set Morality to -10\n3 - Set Morality to +10\n4 - Show Class\n5 - Show Stats\n6 - Show Inventory\n7 - Equip Weapon\n8 - Use Item\n9 - Battle Demo\n10 - Open Test Chest\n11 - Roll D20\n12 - Exit");
         select = kb.nextInt();

         switch(select)
         {
            case 1:
               p.setHealth(100);
               System.out.println("Player Health = " + p.getHealth());
               break;
            case 2:
               p.setMorality(-10);
               System.out.println("Player Morality = " + p.getMorality());
               break;
            case 3:
               p.setMorality(10);
               System.out.println("Player Morality = " + p.getMorality());
               break;
            case 4:
               System.out.println("Player Class = " + p.getType());
               break;
            case 5:
               System.out.println("Player Class: " + p.getType());
               System.out.println("Player Health = " + p.getHealth());
               System.out.println("Player Morality = " + p.getMorality());
               System.out.print("Equipped Weapon: ");
               if(p.getEquipped() == null)
               {
                  System.out.println("None");
               }
               else
               {
                  System.out.println(p.getEquipped());
               }
            break;
            case 6:
               p.showInventory();
               break;
            case 7:
               if(p.getWeaponSize() != 0)
               {
                  p.equipWeapon();
               }
               else
               {
                  System.out.println("No weapons found.");
               }
               break;
            case 8:
               if(p.getItemSize() != 0)
               {
                  System.out.println("Select an item...");
                  p.showInventory_item();
                  s = kb.nextInt();
                  p.use(p.getItemAt(s), null);
               }
               else
               {
                  System.out.println("No items found.");
               }
               break;
            case 9:
            //Start of BattleSim Code. Try to put this into a separate method.               
               if(enemies.size() == 0)
               {
                  enemies.add(testEnemy);
                  enemies.add(testEnemy2);
                  enemies.add(testEnemy3); //Adds enemy(ies) to opposing side.
               }
                              
               battleSystem(p, enemies);
               
               /*boolean breakloop = false; //Used to keep the battle looping while two parties exist.
                              
               for(NPC n: enemies)
               {
                  if(n.getHealth() != n.getMaxHealth())
                  n.setHealth(n.getMaxHealth(), true);
               } //Sets health to max.
         
               while(!breakloop) //Combat loop.
               {
               
                  if(p.getEquipped() == null)
                  {
                     p.setUnarmed();
                  }
               
                  if(p.getHealth() > 99)
                  {
                     System.out.println("FATAL ERROR: player.exe has stopped working.");
                     resetEnemies(enemies);
                     breakloop = true;
                     p.resetHealth();
                  } //Player loss health condition.
                  
                  if(breakloop)
                  {
                     resetEnemies(enemies);
                  }
                  
                  for(NPC n: enemies)
                  {
                     if(n.getIni() > ini_test)
                     {
                        ini_test = n.getIni();
                     }
                  }
                  
                  if(!breakloop)
                     if(p.getHealth() < 100)
                     {
                        if(p.getIni() > ini_test)
                        {
                           breakloop = p.attackMenu(enemies, p.getEquipped());
                           for(NPC n: enemies)
                           {
                              if(n.getHealth() > 0)
                              {
                                 if(!breakloop)
                                 {
                                    System.out.println(n + " attacks!");
                                    n.attack(p, n.getEquipped());
                                 }
                              }
                           }
                        }
                     }
                  }
                  if(p.getUnarmed())
                  {
                     p.resetUnarmed();
                  }*/
               break;      
            case 10:
               int i = 4;
               int j = 1;
               int k = 2;
               System.out.println("You got the " + test_blade.getName() + "!");
               System.out.print("In addition to "+ i +" Restorative" + multiples(i));
               System.out.print(", " + j + " Boost Potion" + multiples(j));
               System.out.print(", and " + k + " Grenade" + multiples(k));
               System.out.println("!");
               p.addToInventory(test_blade);
               for(int num = 0; num < i; num++)
                  p.addToInventory_item(restorative);
               for(int num = 0; num < j; num++)
                  p.addToInventory_item(boost);
               for(int num = 0; num < k; num++)
                  p.addToInventory_item(firebomb);
               break;
            case 11:
               int roll = p.makeRoll();
               System.out.print("Rolled a "+roll+". ");
               if(roll == 1)
                  System.out.println("Automatic Miss!");
               if(roll == 20)
                  System.out.println("Automatic Hit!");
               else
                  System.out.println();
               break;
            case 12:
               System.out.println("Ending...");
               System.exit(0);
               break;
         }
         EntertoContinue_silent();
      }
      while(select != EXIT);
   }
   
   public void EntertoContinue() //Stops the output until the user presses Enter.
   {
      Scanner c = new Scanner(System.in);
      System.out.println("Press Enter to continue.");
      c.nextLine();
   }
   
   public void EntertoContinue_silent() //EntertoContinue, without a printed message.
   {
      Scanner sl = new Scanner(System.in);
      sl.nextLine();
   }
   
   public static String multiples(int d) //Checks if there are multiples of an object.
   {
      if(d > 1)
      {
         return "s";
      }
      else
      {
         return "";
      }
   }
   
   public static void resetEnemies(ArrayList<NPC> en) //Resets the enemies after a battle.
   {
      int size = en.size();
      for(int i = 0; i < size; i++)
      {
         en.remove(0);
      }      
   }
   
   public static void battleSystem(playerObject p, ArrayList<NPC> en) //The battle system, used to cover general battle mechanics.
   {
      System.out.println("Under Construction.  Partially Complete!  :)");
      System.out.println("You encountered the " + en.get(0).getType() + "!"); //Encounter message.
      
      int ini_test = 0; //Holds the highest initiative of an NPC.
      boolean breakloop = false; //Used to keep the battle looping while two parties exist.
         
      for(NPC n: en)
      {
         if(n.getHealth() != n.getMaxHealth())
         n.setHealth(n.getMaxHealth(), true);
         n.setDamaged(false);
      } //Sets enemy health to max.
         
      while(!breakloop) //Combat loop.
      {
         if(p.getEquipped() == null)
         {
            p.setUnarmed();
         } //Gives the player the "Unarmed" weapon if there is no equipped weapon.

         if(p.getHealth() > 99)
         {
            System.out.println("FATAL ERROR: player.exe has stopped working.");
            resetEnemies(en);
            breakloop = true;
            p.resetHealth();
         } //Player loss health condition.

         if(breakloop)
         {
            resetEnemies(en);
         } //Resets the enemies once the battle is ended.
                  
         for(NPC n: en)
         {
            if(n.getIni() > ini_test)
            {
               ini_test = n.getIni();
            }
            //System.out.println("Highest Initiative: " + ini_test); DEBUG: Shows what the highest initiative is.
         } //Checks each enemy for their initiative.

         if(!breakloop)
         if(p.getHealth() < 100)
         {
            if(p.getIni() > ini_test)
            {
               /*for(NPC n: enemies)
               {
                  System.out.println(n);
               }*/
               breakloop = p.attackMenu(en, p.getEquipped());
               for(NPC n: en)
               {
                  if(n.getHealth() > 0)
                  {
                     if(!breakloop)
                     {
                        System.out.println(n + " attacks!");
                        n.attack(p, n.getEquipped());
                     }
                  }
               }
            }
         }
      }
      if(p.getUnarmed())
      {
         p.resetUnarmed();
      }   
   }

   public static void main(String[] args) //The main statement, responsible for activating the dialogTest.
   {
      dialogTest d = new dialogTest();
      d.open();
   }
}



/* P//Rototype Battle Code. R
//Check initiative.
if(!breakloop)
   if(p.getIni() > test//Enemy.getIni() && p.getHealth() < 100) E
   {
      System.out.println("CPU Usage: " + p.getHealth());
      System.out.println("1 - A//Ttack\n2 - Use Feat\n3 - Use Item\n4 - run");  T
      sb = kb.nextInt();
      switch(sb)
      {
         case 1:
            if((p.makeRoll()+test_blade.getBase()+test_blade.getAtkB()) > testEnemy.getDef())
            {
               System.out.println("Hit! " + testEnemy.getType() + " suffe//Rs " + test_blade.getDamage() + " damage!"); R
               testEnemy.setHealth(test_blade.getDamage(), false);
               System.out.println(testEnemy.getHealth());
            }
            else
            {
               System.out.println("Miss. " + testEnemy.getType() + " suffers n//O damage."); O
            }
            break;
         case 2:
            p.listFeat();
            s = kb.nextInt();
            p.use_feat(p.getFeatAt(s));
            //Test for Flurry
            if(p.getMATK())
            {
               for(int i = 0; i < 2; i++)
                  if((p.makeRoll()+test_blade.getBase()+test_blade.getAtkB()) > testEnemy.getDef())
                  {
                     System.out.println("Hit! " + testEnemy.getType() + " suffers " + (test_blade.getDamage()+p.getFeatAt(s).getDmg()) + " damage!");
                     testEnemy.setHealth((test_blade.getDamage()+p.getFeatAt(s).getDmg()), false);
                     System.out.printl//N(testEnemy.getHealth()); N
                  }
                  else
                  {
                     System.out.println("Miss. " + testEnemy.getType() + " suffers no damage.");
                  }
            }
            //Test for Power Attack
            else
            {
               if((p.makeR//Oll()+test_blade.getBase()+test_blade.getAtkB()+p.getFeatAt(s).getAB()) > testEnemy.getDef()) O
               {
                  System.out.println("Hit! " + testEnemy.getType() + " suffers " + (test_blade.getDamage()+p.getFeatAt(s).getDmg()) + " damage!");
                  testEnemy.setHealth((test_blade.getDamage()+p.getFeatAt(s).getDmg()), false);
                  System.out.println(testEnemy.getHealth());
               }
               else
               {
                  System.out.println("Miss. " + testEnemy.getType() + " suffers no damage.");
               }
            }
            break;
         case 3:
            p.showInvento//Ry_item(); R
            if(p.getItemSize() != 0)
            {
               System.out.println("Select an item...");
               s = kb.nextInt();
               p.use(p.getItemAt(s));
            }
            break;
         //End of Bat//TleSim code. T
         case 4:
            System.out.println("EXITING BATTLE SIMULATION...");
            breakloop = true;
            break;
         }
      }
   }
*/
