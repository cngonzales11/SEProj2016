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
   playerObject p = new playerObject("None", 0, 84, 16, 12, 0);
   
   //Assorted Weapons
   weapon dagger = new weapon("Dagger", "A small blade used by most rogues.", 3, 8, 1, 0);
   weapon test_blade = new weapon("Test Blade", "A simple weapon comprised of Java code.", 4, 16, 3, 0); //Possibly secret weapon in the game?
   weapon dagger_player = new weapon("Dagger", "A small blade that can slip by most defenses.", 5, 12, 6, 0);
   weapon greatsword = new weapon("Greatsword", "A large and heavy sword with a long grip.", 2, 20, 4, 0);
   weapon staff = new weapon("Staff", "A long stick that conducts code well.", 3, 10, 12, 0);
      
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
   NPC testEnemy = new NPC("Code Snippet", 100, 100, dagger, 13, 10, 0);
   NPC testEnemy2 = new NPC("Code Snippet 2", 100, 100, dagger, 18, 10, 0);
   NPC testEnemy3 = new NPC("Code Snippet 3", 100, 100, dagger, 9, 10, 0);
   ArrayList<NPC> enemies = new ArrayList<>();
   
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
         EntertoContinue();
      }
      while(select != EXIT);
   }
   
   public void run()
   {
       int selection;
       String command;
       boolean entered = false;
       System.out.println("Run Method Called");
       System.out.println("June 06, 2996: A young programmer is tasked to create a new \nsecure server for the World Wide Web.");
       EntertoContinue_silent();
       System.out.println("All is going well until a storm rolls into the area and their computer is struck by lightning.");
       EntertoContinue_silent();
       System.out.println("The surge of electricity goes through their computer into their nervous system,\ntransferring their consciousness into their newly constructed server...");
       EntertoContinue_silent(); //The starting blurb.
       
       System.out.println("You come to after what seems like ages.  It's hard to remember anything.");
       System.out.println("You try to remember who you are...");
       
       do
       {
       System.out.println("(Type \"select\" to choose your class or \"describe\" to read a description of each class)");
       command = kb.nextLine();
       if(command.equals("describe") && !entered)
       {
          System.out.println("Warrior - A fighter.  The warrior class has a low initiative but high defense\nallowing them to easily shrug off attacks.");
          System.out.println("Rogue - A stealthy killer.  The rogue class has a high initiative but low defense\nallowing them to make the first attack more often.");
          System.out.println("Mage - A magic user.  The mage class has good initiative and low defense,\nbut has powerful attacks and spells.");
       }
       else
       {
          entered = true;
          do
          {
          System.out.println("1 - Warrior\n2 - Rogue\n3 - Mage");
          selection = kb.nextInt();
          System.out.print("Some memories are coming back...You remember ");
          switch(selection)
          {
             case 1:
                p.setType("Warrior");
                p.setDef(35);
                p.addFeat(power_attack);
                System.out.println("your strength and skills in fighting...");
                break;
             case 2:
                p.setType("Rogue");
                p.setDef(25);
                p.setInitiative(28);
                p.addFeat(flurry);
                System.out.println("your quick, stealthy personality...");
                break;
             case 3:
                p.setType("Mage");
                p.setDef(12);
                p.setInitiative(20);
                p.addFeat(lightning);
                p.addFeat(fire);
                p.setSP(25);
                System.out.println("your skills in coding, particularly with creating objects...");
                break;
          }
       }
       while(selection > 3 || selection < 0);
       }
       }while(!entered);
       EntertoContinue_silent();
       System.out.println("\"What just happened?...\"");
       do
       {
       System.out.println("1 - Try to remember what happened.\n2 - Examine your surroundings\n3 - Try to move around.");
       selection = kb.nextInt();
       
       switch(selection)
       {
           case 1:
               System.out.println("You can't recall any specifics.  You remember a white box,\na flash, and then...nothing...");
               break;
           case 2:
               System.out.println("The area around is a light blue.  Lines of ones and zeros float around everywhere.");
               break;
           case 3:
               System.out.println("You start walking forward...");
               break;
       }
       }while(selection != 3);
       EntertoContinue_silent();
       System.out.println("From behind you, a loud buzzing can be heard...");
       EntertoContinue_silent();
       System.out.println("You see black and red blobs of ones and zeros...\n");
       EntertoContinue_silent();
       System.out.println("1 - Try to talk with the blobs\n2 - Take a close look at the blobs\n3 - Try to attack the blobs");
       
       selection = kb.nextInt();
       switch(selection)
       {
           case 1:
               System.out.println("You greet the blobs...");
               System.out.println("All you get in return is an ear-gratting buzz.");
               break;
           case 2:
               System.out.println("Looking closer at the blobs, you notice the ones and zeros\nemulate a certain pattern...");
               EntertoContinue_silent();
               System.out.println("1...0...1...0...0...1...1...0...0...1...0...1...0...0");
               System.out.println("The pattern seems familiar to you somehow...");
               break;
           case 3:
               System.out.println("You charge at the blobs, but are pushed back by some force.");
               break;
       }
       System.out.println("You see some text above you: \"player.exe CPU Usage: 0%\"");
       EntertoContinue_silent();
       System.out.println("\"CPU Usage? player.exe?...IS THAT ME?!\"");
       EntertoContinue_silent();
       System.out.println("The blobs attack!\n\"CPU Usage: 4%\"");
       p.setHealth(4);
       EntertoContinue_silent();
       System.out.println("\"Guess that's my health, then...If that goes...I go...\"");
       EntertoContinue_silent();
       System.out.println("\"I need to take these guys out...But how?\"\nYou think of a weapon you could use...");
       System.out.println("1 - Dagger\n2 - Greatsword\n3 - Staff");
       
       selection = kb.nextInt();
       switch(selection)
       {
           case 1:
               p.addToInventory(dagger_player);
               break;
           case 2:
               p.addToInventory(greatsword);
               break;
           case 3:
               p.addToInventory(staff);
               break;
       }
       p.setEquipped(1);
       System.out.println("You visualize a " + p.getEquipped() + ".");
       EntertoContinue_silent();
       System.out.println("\"This'll work...\"");
       EntertoContinue_silent();
       
       if(enemies.size() == 0)
               {
                  enemies.add(testEnemy);
                  enemies.add(testEnemy2);
                  enemies.add(testEnemy3); //Adds enemy(ies) to opposing side.
               }
       
       battleSystem(p, enemies);
       
       if(p.getHealth() > 99)
       {
           EntertoContinue();
       }
       else
       {
           System.out.println("Well...That's taken care of...\nNow to find out what's going on...");
       }
       
       EntertoContinue();
   }
   
   public static void EntertoContinue() //Stops the output until the user presses Enter.
   {
      Scanner c = new Scanner(System.in);
      System.out.println("Press Enter to continue.");
      c.nextLine();
   }
   
   public static void EntertoContinue_silent() //EntertoContinue, without a printed message.
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
      System.out.println("You encountered the " + en.get(0).getType() + "s!"); //Encounter message.
      
      int ini_test = 0; //Holds the highest initiative of an NPC.
      NPC starter = null; //Holds the NPC with the highest initiative.
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
               starter = n;
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
               EntertoContinue_silent();
            }
            else
            {
                if(starter.getHealth() > 0)
                  {
                     if(!breakloop)
                     {
                        System.out.println(starter + " attacks!");
                        starter.attack(p, starter.getEquipped());
                     }
                  }
                breakloop = p.attackMenu(en, p.getEquipped());
                EntertoContinue_silent();
            }
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
      if(p.getUnarmed())
      {
         p.resetUnarmed();
      }   
   }

   public static void main(String[] args) //The main statement, responsible for activating the dialogTest.
   {
      Scanner main_sys = new Scanner(System.in);
      dialogTest d = new dialogTest();
      int selection;
      boolean testing = true;
      
      if(!testing)
      {
      do
      {
      System.out.println("Welcome to Back-Door Out: A Text Based Adventure Game!\nPlease press Enter to Continue.");
      EntertoContinue_silent();
      selection = 1;
      }
      while(selection != 1);
      }
      else
      {
      do
      {
      System.out.println("Welcome to Back-Door Out: A Text Based Adventure Game!\nPlease select your option\n1 - Start Game   2 - Run Method Tests");
      selection = main_sys.nextInt();
      }
      while(selection < 1 && selection > 2);
      }
      
      switch(selection)
      {
          case 1:
             d.p.setHealth(0);
             d.run();
             break;
          case 2:
             d.open();
             break;
      }
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
