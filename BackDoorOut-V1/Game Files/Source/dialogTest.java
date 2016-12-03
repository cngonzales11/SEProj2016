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
   private final int EXIT = 11;
   private int ini_test = 0;
   private int item_add = 0;
   
   //These booleans relate to choices in the game.
   private boolean blob_exm = false; //Examine the code blobs.
   private boolean par_exm = false; //Examine the parity.
   private boolean par_fix = false; //Choose between fixing the parity and leaving it.
   private boolean code = false; //Examine the blobs before battle.
   private boolean npc_lives = true; //Talk to or ambush npcs.
   private boolean help_npc = false; //Helped the npc with her job.
   private boolean needtoleave = false; //Select need to leave dialog.
   private boolean help_webroot = false; //Helped the officer?
   private boolean kill_webroot = false; //Attacked the officer?
   private boolean find_main = false; //Went to find the main parity.
   private boolean fix_main = false; //Decided to fix the main parity.
   private boolean hack_main = false; //Decided to hack the main parity.
   private boolean fullhackend = false; //The full dark side end.
   private boolean fullfixend = false; //The full light side end.
   private boolean rest_desc = false; //Found a restorative.
   private boolean boost_desc = false; //Found a boost potion.

   //The Player
   playerObject p = new playerObject("None", 0, 16, 12, 0);
   
   //Assorted Weapons
   weapon dagger = new weapon("Dagger", "A small blade used by most rogues.", "Physical", 14, 8, 0, 0);
   weapon corruption = new weapon("Data Miner", "Hacks through most data with ease.", "Physical", 12, 18, 3, 0);
   weapon whitesteel = new weapon("White Steel Staff", "A bladed staff of unerring sharpness.", "Physical", 8, 21, 3, 5);
   weapon test_blade = new weapon("Test Blade", "A simple weapon comprised of Java code.", "Physical", 4, 16, 3, 0);
   
   weapon dagger_player = new weapon("Dagger", "A small blade that can slip by most defenses.", "Physical", 5, 12, 6, 0);
   
   weapon greatsword = new weapon("Greatsword", "A large and heavy sword with a long grip.", "Physical", 2, 20, 4, 0);
   
   weapon staff = new weapon("Staff", "A long stick that conducts code well.", "Magic", 3, 14, 12, 9);
   weapon ice_staff = new weapon("Cube Staff","A rod that conducts cold magic.", "Cold", 3, 18, 7, 12);
   weapon fire_staff = new weapon("Torch","A burning staff.", "Fire", 7, 22, 15, 6);
   weapon lightning_staff = new weapon("Charge Rod","Lightning runs along the runes.", "Electric", 5, 20, 6, 20);
      
   //Assorted Items
   item restorative = new item("Restorative", "A compact version of a Malicious Software Removal Tool.\nHeals 53 HP.", "Healing", 53);
   item boost = new item("Boost Potion", "Task Manager: Helps clear up those congested systems!\nIncreases Attack stat and Damage output by 4.", "Boost", 4);
   
   //Rogue: Starting
   feat flurry = new feat("Flurry", "The user speeds up their attacks,\nallowing them to attack twice a round.", 0, 2, 0, 0, 0, "Attack Twice", true);
   //Rogue: Unlockable
   feat killer_instinct = new feat("Killer Instinct", "You find the weakness in an enemy and strike fast.\nDealing damage equal to half the enemy's health.", 0, 0, 0, 0, 0, "Half Damage",true);
   
   //Warrior: Starting
   feat power_attack = new feat("Power Attack", "The user puts all their strength into one swing,\nmaking it more likely to miss but deal more damage.", -3, 8, 0, 0, 0, "Power Attack", true);
   //Warrior: Unlockable
   feat parry = new feat("Parry", "After attacking, the user assumes\na defensive stance, ready for the next attack.", -4, 5, 0, 10, 10, "Defensive", true);
   
   //Mage: Starting
   feat arcane_blast = new feat("Arcane Burst","Waves of code lash out at your opponents.", 5, 15, 0, 0, 0, "Full Attack", false);
   
   //Mage: Weapon-Dependent
   feat lightning = new feat("Lightning", "A charged blast fires from the user's hands,\ndealing moderate damage to two enemies.", -10, 20, 0, 0, 0, "Area Attack", false);
   feat fire = new feat("Ignition", "Your enemies spark and burst into flames,\ndealing burn damage over time.", -1, 9, 3, 0, 0, "Over-Time", false);
   feat freeze = new feat("Chill", "You target an enemy with a cold blast,\nlowering their intiative and attack", 0, 5, 0, 0, 0, "Slow", false);
   
   //Mage: Unlockable
   
   
   //An NPC ArrayList for battles.
   NPC testEnemy = new NPC("Code Snippet", 100, 100, dagger, 13, 10);
   NPC testEnemy2 = new NPC("Code Snippet 2", 100, 100, dagger, 18, 10);
   NPC testEnemy3 = new NPC("Code Snippet 3", 100, 100, dagger, 9, 10);
   NPC lieutenant = new NPC("Corrupted Snippet", 180, 180, corruption, 22, 18);
   NPC lieutenant2 = new NPC("Corrupted Snippet 2", 250, 180, corruption, 22, 20);
   NPC lieutenant3 = new NPC("Corrupted Snippet 3", 300, 180, corruption, 22, 25);
   NPC webroot = new NPC("Web Officer", 180, 180, corruption, 22, 20);
   ArrayList<NPC> enemies = new ArrayList<>();
   
   //
   //METHODS:
   //
   
   //Opens the game's debug mode.  Accessible if testing is active.
   public void open()
   {
      int select, s, sc;
      
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
      {
         p.addFeat(flurry);
         p.addFeat(killer_instinct);
      }
      if(p.getType() == "Warrior")
      {
         p.addFeat(power_attack);
         p.addFeat(parry);
      }
      if(p.getType() == "Mage")
      {
         p.addFeat(arcane_blast);
         p.addFeat(lightning);
         p.addFeat(fire);
         p.addFeat(freeze);
         p.setSP(25);
      }
      
      //The main menu, with respective switch cases.
      do
      {
         System.out.println("Select an option");
         System.out.println("1 - Set HP to 100\n2 - Show Class\n3 - Show Stats\n4 - Show Inventory\n5 - Equip Weapon\n6 - Use Item\n7 - Battle Demo\n8 - Open Test Chest\n9 - Roll D20\n10 - Describe Feats\n11 - Exit");
         select = kb.nextInt();

         switch(select)
         {
            case 1:
               p.setHealth(100);
               System.out.println("Player Health = " + p.getHealth());
               break;
            case 2:
               System.out.println("Player Class = " + p.getType());
               break;
            case 3:
               System.out.println("Player Class: " + p.getType());
               System.out.println("Player Health = " + p.getHealth());
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
            case 4:
               p.showInventory();
               break;
            case 5:
               if(p.getWeaponSize() != 0)
               {
                  p.equipWeapon();
               }
               else
               {
                  System.out.println("No weapons found.");
               }
               break;
            case 6:
               if(p.getItemSize() != 0)
               {
                  System.out.println("Select an item...");
                  p.showInventory_item();
                  s = kb.nextInt();
                  p.use(p.getItemAt(s));
               }
               else
               {
                  System.out.println("No items found.");
               }
               break;
            case 7:
               if(enemies.size() == 0)
               {
                  enemies.add(testEnemy);
                  enemies.add(testEnemy2);
                  enemies.add(testEnemy3); //Adds enemy(ies) to opposing side.
               }
                              
               battleSystem(p, enemies);
               break;      
            case 8:
               int i = 4;
               int j = 1;
               int k = 2;
               System.out.println("You got the " + test_blade.getName() + "!");
               System.out.print("In addition to "+ i +" Restorative" + multiples(i));
               System.out.print(", " + j + " Boost Potion" + multiples(j));
               System.out.println("!");
               p.addToInventory(test_blade);
               for(int num = 0; num < i; num++)
                  p.addToInventory_item(restorative);
               for(int num = 0; num < j; num++)
                  p.addToInventory_item(boost);
               break;
            case 9:
               int roll = p.makeRoll();
               System.out.print("Rolled a "+roll+". ");
               if(roll == 1)
                  System.out.println("Automatic Miss!");
               if(roll == 20)
                  System.out.println("Automatic Hit!");
               else
                  System.out.println();
               break;
            case 10:
               p.describeFeat();
               break;
            case 11:
               System.out.println("Exiting Method Test...");
               break;
         }
         EntertoContinue();
      }
      while(select != EXIT);
      
      p.removeFeats();
      p.removeItems();
      p.resetHealth();
   }
   
   public void run() //The game storyline.  Accessible in normal and debug mode. //Remove references to morality.
   {
       int selection;
       String command;
       boolean entered = false;
       //System.out.println("Run Method Called"); DEBUG: Shows what method is called.
       System.out.print("June 06, 2996: A young programmer is tasked to create a new \nsecure server for the World Wide Web.");
       EntertoContinue_silent();
       System.out.print("All is going well until a storm rolls into the area and their computer is struck by lightning.");
       EntertoContinue_silent();
       System.out.println("The surge of electricity goes through their computer into their nervous system,\ntransferring their consciousness into their newly constructed server...");
       EntertoContinue_silent(); //The starting blurb.
       
       System.out.println("You come to after what seems like ages.  It's hard to remember anything.");
       System.out.println("You try to remember who you are...");
       EntertoContinue_silent();
       
       do
       {
       System.out.println("(Type \"select\" to choose your class or \"describe\" to read a description of each class)");
       command = kb.nextLine();
       if(command.equals("describe") && !entered)
       {
          System.out.println("Warrior - A fighter.  The warrior class has a low initiative but high defense\nallowing them to easily shrug off attacks.");
          System.out.println("Starting Feats:\n" + power_attack + ":\n" + power_attack.getDesc());
          System.out.println("\nRogue - A stealthy killer.  The rogue class has a high initiative but low defense\nallowing them to make the first attack more often.");
          System.out.println("Starting Feats:\n" + flurry + ":\n" + flurry.getDesc());
          System.out.println("\nMage - A magic user.  The mage class has good initiative and low defense,\nbut has powerful attacks and spells.");
          System.out.println("Starting Feats:\n" + arcane_blast + ":\n" + arcane_blast.getDesc() + "\n");
       }
       else
       {
          if(command.equals("select"))
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
                p.addFeat(arcane_blast);
                p.setSP(25);
                System.out.println("your skills in coding, particularly with creating objects...");
                break;
          }
       }
       while(selection > 3 || selection < 0);
       }
          else
       {
           System.out.println("You try to remember something...But nothing comes to mind...");
       }
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
               System.out.print("You can't recall any specifics.  You remember a white box,\na flash, and then...nothing...");
               break;
           case 2:
               System.out.print("The area around is a light blue.  Lines of ones and zeros float around everywhere.");
               break;
           case 3:
               System.out.print("You start walking forward...");
               break;
       }
       if(selection != 3)
       {
          EntertoContinue_silent();
       }
       }while(selection != 3);
       EntertoContinue_silent();
       System.out.print("From behind you, a loud buzzing can be heard...");
       EntertoContinue_silent();
       System.out.print("You see black and red blobs of ones and zeros...\n");
       EntertoContinue_silent();
       System.out.println("1 - Try to talk with the blobs\n2 - Take a close look at the blobs\n3 - Try to attack the blobs");
       
       selection = kb.nextInt();
       switch(selection)
       {
           case 1:
               System.out.print("You greet the blobs...");
               EntertoContinue_silent();
               System.out.print("All you get in return is an ear-gratting buzz.");
               break;
           case 2:
               code = true;
               System.out.print("Looking closer at the blobs, you notice the ones and zeros\nemulate a certain pattern...");
               EntertoContinue_silent();
               System.out.print("0010...1001...1001...0011");
               EntertoContinue_silent();
               System.out.print("The pattern seems familiar to you somehow...");
               break;
           case 3:
               System.out.print("You charge at the blobs, but are pushed back by some force.");
               break;
       }
       EntertoContinue_silent();
       System.out.println("You see some text above you: \"player.exe CPU Usage: 0%\"");
       EntertoContinue_silent();
       System.out.println("\"CPU Usage? player.exe?...IS THAT ME?!\"");
       EntertoContinue_silent();
       System.out.println("The blobs attack!\n\"CPU Usage: 4%\"");
       p.setHealth(4);
       EntertoContinue_silent();
       System.out.print("\"Guess that's my health, then...If that goes...I go...\"");
       EntertoContinue_silent();
       System.out.println("\"I need to take these guys out...But how?\"");
       EntertoContinue_silent();
       System.out.println("You think of a weapon you could use...");
       do
       {
       System.out.println("1 - Dagger\n2 - Greatsword\n3 - Staff");
       
       selection = kb.nextInt();
       }while(selection > 3 || selection < 1);
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
       
       if(p.getHealth() < 100)
       {
           System.out.println("Well...That's taken care of...\nNow to find out what's going on...");
           EntertoContinue_silent();
           
           do
           {
           if(!par_exm)
           {
              System.out.println("1 - Look around");
           }
           else
           {
              System.out.println("1 - Examine the break");
           }
           if(!blob_exm)
           {
              System.out.println("2 - Examine the code blobs\n3 - Continue moving");
           }
           else
           {
              System.out.println("2 - Continue moving");
           }
           selection = kb.nextInt();
           switch(selection)
           {
               case 1:
                   if(!par_exm)
                   {
                      par_exm = true;
                      System.out.print("The area is the same as before.");
                      EntertoContinue_silent();
                      System.out.print("The only difference is that there is a large break\nin the ones and zeros up ahead.");
                      EntertoContinue_silent();
                      System.out.println("Reality around that area seems distorted.");
                      EntertoContinue_silent();
                      System.out.println("1 - Walk to the break\n2 - Ignore the break");
                      selection = kb.nextInt();
                   
                      switch(selection)
                      {
                         case 1:
                             System.out.print("You arrive at the break.");
                             EntertoContinue_silent();
                             System.out.print("Whatever caused this, it's not helping the area.");
                             EntertoContinue_silent();
                             System.out.print("It seems to be expanding to the rest of the area,\ndissolving it into nothing.");
                             EntertoContinue_silent();
                             System.out.println("You notice the blobs are moving towards the break, filling it in.");
                             EntertoContinue_silent();
                             par_fix = true;
                             break;
                         case 2:
                             System.out.println("You ignore the break for now.");
                             break;
                      }
                   }
                   else
                   {
                       System.out.print("You arrive at the break.");
                       EntertoContinue_silent();
                       System.out.print("Whatever caused this, it's not helping the area.");
                       EntertoContinue_silent();
                       System.out.print("It seems to be expanding to the rest of the area,\ndissolving it into nothing.");
                       EntertoContinue_silent();
                       System.out.println("You notice the blobs are moving towards the break, filling it in.");
                       par_fix = true;
                       EntertoContinue_silent();
                   }
                   if(par_exm && par_fix)
                   {
                       System.out.print("As the blobs fill the break, you notice a familiar line of code.");
                       EntertoContinue_silent();
                       System.out.print("There is a resource statement that has a corrupted reference.");
                       EntertoContinue_silent();
                       System.out.println("You feel like if you try to fix it, it'll help somehow...");
                       System.out.println("1 - Attempt to fix the code\n2 - Leave the code alone.");
                       selection = kb.nextInt();
                       switch(selection)
                       {
                           case 1:
                               System.out.print("You stretch out your hand and code flows out into the break.");
                               EntertoContinue_silent();
                               System.out.print("You rewrite the code and the reference fixes itself.");
                               EntertoContinue_silent();
                               System.out.print("The line of code fades into the ones and zeros.");
                               EntertoContinue_silent();
                               System.out.println("You see text float in the air: \"Parity Fixed\"\nAfter which the ones and zeros fade into what looks like a forest.");
                               break;
                           case 2:
                               System.out.println("You decide to let the code stay corrupted.");
                               EntertoContinue_silent();
                               System.out.println("The line dissolves and seems to collapse on itself.");
                               EntertoContinue_silent();
                               System.out.println("You see text float in the air: \"Parity Deleted\"\nAfter which the ones and zeros fade into what looks like a forest.");
                               break;
                       }
                       selection = 3;
                   }
                   
                   break;
               case 2:
                   if(!blob_exm)
                   {
                      blob_exm = true;
                      System.out.println("The blobs seem to be carrying some kind of items.");
                      System.out.println("(Items Received: 5 Restoratives)");
                      item_add = 5;
                      for(int i = 0; i < item_add; i++)
                      {
                          p.addToInventory_item(restorative);
                      }
                      if(!rest_desc)
                      {
                          rest_desc = true;
                          System.out.println(restorative + ": " + restorative.getDesc());
                      }
                   }
                   else
                   {
                      System.out.println("You walk on, noticing a large break in the ones\nand zeros as you move on.");
                      EntertoContinue_silent();
                      System.out.print("It seems to be expanding to the rest of the area,\ndissolving it into nothing.");
                      EntertoContinue_silent();
                      System.out.print("You notice the blobs are moving towards the break, filling it in.");
                      EntertoContinue_silent();
                      System.out.println("The break, however, closes in on itself and\nexpands to reveal the world around you: a large forest.");
                      selection = 3;
                   }
                   break;
               case 3:
                   if(!par_exm)
                   {
                      System.out.print("You walk on, noticing a large break in the ones\nand zeros as you move on.");
                      EntertoContinue_silent();
                      System.out.print("It");
                   }
                   else
                   {
                      System.out.print("The break");
                   }
                   System.out.print(" seems to be expanding to the rest of the area,\ndissolving it into nothing.");
                   EntertoContinue_silent();
                   System.out.print("You notice the blobs are moving towards the break, filling it in.");
                   EntertoContinue_silent();
                   System.out.println("The break, however, closes in on itself and\nexpands to reveal the world around you: a large forest.");
                   break;
           }
           }while(selection != 3);
       }
       
       EntertoContinue();
       
       if(p.getHealth() < 100) //The Outer Hub.
       {
           System.out.print("While you are walking, you happen upon a young woman in the forest.");
           EntertoContinue_silent();
           System.out.println("1 - Talk to her\n2 - Ambush her");
           
           selection = kb.nextInt();
           switch(selection)
           {
               case 1:
                   System.out.print("You greet the girl.  She is a little surprised.");
                   EntertoContinue_silent();
                   System.out.println("After a long silence, she speaks up...");
                   EntertoContinue_silent();
                   System.out.println("\"Is there something you need?\"");
                   System.out.println("1 - \"What are you doing?\"\n2 - \"Where am I?\"");
                   
                   selection = kb.nextInt();
                   switch(selection)
                   {
                       case 1:
                           System.out.print("\"There have been Parities popping up everywhere,\nripping the world apart.\"");
                           EntertoContinue_silent();
                           System.out.println("1 - \"Parities?\"");
                           while(kb.nextInt() != 1)
                           {
                               kb.nextInt();
                           }
                           System.out.print("\"Yeah...the big breaches in the world that consume everything they touch.\"");
                           EntertoContinue_silent();
                           System.out.print("\"I'm going to check on the central hub.\"");
                           EntertoContinue_silent();
                           System.out.println("\"I'm worried this might spread to the rest of the Web.\"");
                           EntertoContinue_silent();
                           break;
                       case 2:
                           System.out.print("\"You seem confused...Are you from here?\"");
                           EntertoContinue_silent();
                           System.out.println("\"Although with the parities and all, I'll bet we're all confused.\"");
                           EntertoContinue_silent();
                           break;
                   }
                   break;
               case 2:
                   npc_lives = false;
                   System.out.print("You sneak up on the woman and take her out with relative ease.");
                   EntertoContinue_silent();
                   System.out.print("You notice a note on her body.");
                   EntertoContinue_silent();
                   System.out.println("\"I need you to go to the central hub and check on things there.\"");
                   System.out.println("\"Whatever you do, keep away from the Parities.\"");
                   EntertoContinue_silent();
                   break;
           }
           
           System.out.print("If you weren't sure already, this confirmed it.");
           EntertoContinue_silent();
           System.out.print("You were inside the Web, or part of it at least.");
           EntertoContinue_silent();
           System.out.print("You recall ");
           if(code)
           {
              System.out.print("the binary pattern you saw from the blobs.");
           }
           else
           {
              System.out.print("the blobs from before having a certain pattern of binary code.");
           }
           EntertoContinue_silent();
           System.out.print("0010...two...1001...nine...1001...nine...0011...six...");
           EntertoContinue_silent();
           System.out.print("2996?");
           EntertoContinue_silent();
           System.out.println("Was this your server?  That would explain the flash from before.");
           EntertoContinue_silent();
           if(!npc_lives)
           {
               System.out.print("At any rate, you should get going.  Who knows what could happen...");
               EntertoContinue_silent();
               System.out.print("She was headed for the central hub...Maybe that's your way out.");
               EntertoContinue_silent();
               System.out.print("Along your journey, you find a ");
               
               if(p.getType().equals("Mage"))
               {
               System.out.print(ice_staff + ". (Attack: " + (ice_staff.getBase()+ice_staff.getAtkB()) + " Damage: " + ice_staff.getDamage() + ")\n" + ice_staff.getDesc());
               p.addToInventory(ice_staff);
               }
               else
               {
               System.out.print(corruption + ". (Attack: " + (corruption.getBase()+corruption.getAtkB()) + " Damage: " + corruption.getDamage() + ")\n" + corruption.getDesc());
               p.addToInventory(corruption);
               }
               
               do
               {
                   System.out.println("Equip? 1 - Yes  2 - No");
                   
                   selection = kb.nextInt();
               }while(selection < 1 || selection > 2);
               switch(selection)
               {
                   case 1:
                       p.unequip();
                       p.equipWeapon();
                       break;
                   case 2:
                       break;
               }
               if(p.getType().equals("Mage"))
               {
                  System.out.print("You feel the magic flowing through this weapon.");
                  EntertoContinue_silent();
                  p.addFeat(freeze);
                  System.out.println("New Feat Gained: " + freeze);
                  System.out.println(freeze.getDesc());
               }
           }
           else
           {
               do
               {
               System.out.println("1 - \"Do you need any help?\"\n2 - \"How do I get out of here?\"");
               
               selection = kb.nextInt();
               switch(selection)
               {
                   case 1:
                       System.out.println("\"I think I'll be okay.  I need to...\"");
                       EntertoContinue_silent();
                       System.out.println("She pauses for a moment...");
                       EntertoContinue_silent();
                       System.out.print("\"Actually, I do...\"");
                       EntertoContinue_silent();
                       System.out.println("\"I probably won't make it to the central hub.\"");
                       EntertoContinue_silent();
                       System.out.println("She looks away with a defeated look on her face.");
                       EntertoContinue_silent();
                       System.out.print("\"It was foolish of me to think I could with all the rogue code.\"");
                       EntertoContinue_silent();
                       System.out.println("\"But someone has to do something.  So, I thought I would at least try...\"");
                       
                       do
                       {
                       System.out.println("1 - \"I came across a break in the world not to long ago.\n    I was able to fix it...or something...\"\n2 - \"I can fight whatever blocks my way.\"");
                       
                       selection = kb.nextInt();
                       switch(selection)
                       {
                           case 1:
                               System.out.print("\"So you fixed a parity?  I didn't know that was possible...\"");
                               EntertoContinue_silent();
                               System.out.print("\"Well, if you can do that, we might have a chance...\"");
                               EntertoContinue_silent();
                               System.out.print("\"If I can gather people to fix the parities here, can you go fix the parity in the central hub?\"");
                               EntertoContinue_silent();
                               System.out.println("1 - \"Sure thing.  Might as well help while I'm here.\"\n2 - \"Do what you want.  I need to get out of here now.\"");
                               
                               selection = kb.nextInt();
                               switch(selection)
                               {
                                   case 1:
                                       help_npc = true;
                                       System.out.print("\"Thank you.  I'll do my best here; good luck.\"");
                                       EntertoContinue_silent();
                                       System.out.println("\"Here.  I'm not sure if it'll help, but I think you can use this...\"");
                                       EntertoContinue_silent();
                                       System.out.print("You got the ");
                                       if(p.getType().equals("Mage"))
                                       {
                                           System.out.println(lightning_staff + "! (Attack: " + (lightning_staff.getBase()+lightning_staff.getAtkB()) + " Damage: " + lightning_staff.getDamage() + ")");
                                           p.addToInventory(lightning_staff);
                                       }
                                       else
                                       {
                                           System.out.println(whitesteel + "! (Attack: " + (whitesteel.getBase()+whitesteel.getAtkB()) + " Damage: " + whitesteel.getDamage() + ")");
                                           p.addToInventory(whitesteel);
                                       }
                                       do
                                       {
                                       System.out.println("Equip? 1 - Yes  2 - No");
                                       
                                       selection = kb.nextInt();
                                       }while(selection < 1 || selection > 2);
                                       switch(selection)
                                       {
                                           case 1:
                                               p.unequip();
                                               p.equipWeapon();
                                               break;
                                           case 2:
                                               break;
                                       }
                                       if(p.getType().equals("Mage"))
                                       {
                                          System.out.print("You feel the magic flowing through this weapon.");
                                          EntertoContinue_silent();
                                          p.addFeat(lightning);
                                          System.out.println("New Feat Gained: " + lightning);
                                          System.out.println(lightning.getDesc());
                                       }
                                       break;
                                   case 2:
                                       System.out.print("\"Well, I'll do what I can to help but my friends and family need me.\"");
                                       EntertoContinue_silent();
                                       System.out.print("\"Here.  I'm not sure if it'll help, but I think you can use this...\"");
                                       EntertoContinue_silent();
                                       System.out.println("You got the ");
                                       if(p.getType().equals("Mage"))
                                       {
                                           System.out.println(fire_staff + "! (Attack: " + (fire_staff.getBase()+fire_staff.getAtkB()) + " Damage: " + fire_staff.getDamage() + ")");
                                           p.addToInventory(fire_staff);
                                       }
                                       else
                                       {
                                           System.out.println(whitesteel + "! (Attack: " + (whitesteel.getBase()+whitesteel.getAtkB()) + " Damage: " + whitesteel.getDamage() + ")");
                                           p.addToInventory(whitesteel);
                                       }
                                       do
                                       {
                                       System.out.println("Equip? 1 - Yes  2 - No");
                                       
                                       selection = kb.nextInt();
                                       }while(selection < 1 || selection > 2);
                                       switch(selection)
                                       {
                                           case 1:
                                               p.unequip();
                                               p.equipWeapon();
                                               break;
                                           case 2:
                                               break;
                                       }
                                       if(p.getType().equals("Mage"))
                                       {
                                          System.out.print("You feel the magic flowing through this weapon.");
                                          EntertoContinue_silent();
                                          p.addFeat(lightning);
                                          System.out.println("New Feat Gained: " + lightning);
                                          System.out.println(lightning.getDesc());
                                       }
                                       break;
                               }
                               break;
                           case 2:
                               System.out.print("\"Fighting is only half the battle.  There's still the parities...\"");
                               EntertoContinue_silent();
                               System.out.println("\"If those aren't fixed, this world will collapse in on itself.\"");
                               break;
                       }
                       }while(selection != 1);
                       break;
                   case 2:
                       System.out.print("\"You're not making sense...This is the world we live in...\"");
                       EntertoContinue_silent();
                       System.out.println("\"And with our current state of affairs,\nI don't think now is the time for jokes.\"");
                       break;
               }
               }while(selection != 1);
           }
           
           if(!p.getType().equals("Mage"))
           {
              System.out.print("While you were traveling, you were able\nto get some practice with your weapon.");
              EntertoContinue_silent();
              if(p.getType().equals("Rogue"))
              {
                 p.addFeat(killer_instinct);
                 System.out.println("New Feat Gained: " + killer_instinct);
                 System.out.println(killer_instinct.getDesc());
              }
              else
              {
                 p.addFeat(parry);
                 System.out.println("New Feat Gained: " + parry);
                 System.out.println(parry.getDesc());
              }
           }
       }
       
       EntertoContinue();
       
       if(p.getHealth() < 100) //The Central Hub.
       {
           if(npc_lives)
           {
               System.out.print("While she is fixing the smaller parities, you");
           }
           else
           {
               System.out.print("You");
           }
                      
           System.out.print(" reach the entrance to the central hub.");
           EntertoContinue_silent();
           System.out.println("You are immediately greeted by what seems to be a guard.");
           EntertoContinue_silent();
           System.out.println("\"Halt!  This is the central hub; what is your business here?\"");
           
           do
           {
           if(!needtoleave)
           {
              System.out.println("1 - \"I heard you need help fixing parities.\"\n2 - \"I'm just looking to get out of here.\"\n3 - [Attack the guard]");
           }
           else
           {
              System.out.println("1 - \"I heard you need help fixing parities.\"\n2 - [Attack the guard]"); 
           }
           
           selection = kb.nextInt();
           switch(selection)
           {
               case 1:
                   help_webroot = true;
                   System.out.println("\"We might...and you do look like a capable person...\"");
                   EntertoContinue_silent();
                   System.out.println("You hear a familiar buzz, but much louder...");
                   EntertoContinue_silent();
                   System.out.print("\"Wait...What was that...\"");
                   EntertoContinue_silent();
                   System.out.print("\"Rogue code!  On your guard!\"");
                   EntertoContinue_silent();
                   p.resetHealth();
                   System.out.println("\"Here, take these.\"");
                   System.out.println("(Items Received: 10 Restoratives and 3 Boost Potions)");
                   for(int i = 0; i < 10; i++)
                   {
                       p.addToInventory_item(restorative);
                   }
                   if(!rest_desc)
                   {
                          rest_desc = true;
                          System.out.println(restorative + ": " + restorative.getDesc());
                   }
                   for(int i = 0; i < 3; i++)
                   {
                       p.addToInventory_item(boost);
                   }
                   if(!boost_desc)
                   {
                          boost_desc = true;
                          System.out.println(boost + ": " + boost.getDesc());
                   }
                   EntertoContinue_silent();
                   if(enemies.size() == 0)
                   {
                       enemies.add(testEnemy);
                       enemies.add(lieutenant);
                       enemies.add(testEnemy2);
                       enemies.add(testEnemy3);
                   }
                   battleSystem(p, enemies);
                   break;
               case 2:
                   if(!needtoleave)
                   {
                       needtoleave = true;
                       System.out.println("\"Then you might as well forget it...The way ahead is blocked by a large parity.\"");
                   }
                   else
                   {
                       kill_webroot = true;
                       p.setHealth(webroot.getEquipped().getDamage());
                       System.out.print("You charge at the guard, but are stabbed with his data miner. (CPU Usage: " + p.getHealth() + ")");
                       EntertoContinue_silent();
                       System.out.print("You quickly pull back, holding you chest...");
                       EntertoContinue_silent();
                       System.out.println("Rogue code surronds the two of you.");
                       EntertoContinue_silent();
                       System.out.print("\"So you want to fight, huh?  Then bring it on.\"");
                       EntertoContinue_silent();
                       if(enemies.size() == 0)
                       {
                           enemies.add(webroot);
                           enemies.add(testEnemy);
                           enemies.add(lieutenant);
                           enemies.add(testEnemy2);
                           enemies.add(testEnemy3);
                       }
                       battleSystem(p, enemies);
                       selection = 1;
                   }
                   break;
               case 3:
                   if(!needtoleave)
                   {
                      kill_webroot = true;
                      p.setHealth(webroot.getEquipped().getDamage());
                      System.out.print("You charge at the guard, but are stabbed with his data miner. (CPU Usage: " + p.getHealth() + ")");
                      EntertoContinue_silent();
                      System.out.print("You quickly pull back, holding you chest...");
                      EntertoContinue_silent();
                      System.out.println("Rogue code surronds the two of you.");
                      EntertoContinue_silent();
                      System.out.print("\"So you want to fight, huh?  Then bring it on.\"");
                      EntertoContinue_silent();
                      if(enemies.size() == 0)
                      {
                          enemies.add(webroot);
                          enemies.add(testEnemy);
                          enemies.add(lieutenant);
                          enemies.add(testEnemy2);
                          enemies.add(testEnemy3);
                      }
                      battleSystem(p, enemies);
                      selection = 1;
                   }   
                   break;
           }
           }while(selection != 1);
       }
       
       EntertoContinue();
       
       if(p.getHealth() < 100) //The final battle.
       {
           if(kill_webroot)
           {
              System.out.print("Rogue code seems to be crawling out of everywhere.");
              EntertoContinue_silent();
              System.out.print("The central hub looks to be crumbling to pieces.");
              EntertoContinue_silent();
              System.out.println("You need to do something.");
              System.out.println("1 - Charge at the rogue code\n2 - Run to the parity.");
              selection = kb.nextInt();
              switch(selection)
              {
                  case 1:
                      System.out.print("Despite your better judgement, you pull your weapon\nand charge at the rogue snippets.");
                      EntertoContinue_silent();
                      System.out.print("You quickly realize you are vastly outnumbered.");
                      EntertoContinue_silent();
                      System.out.print("As the rogue snippets consume you, everything fades to black...");
                      EntertoContinue_silent();
                      System.exit(0);
                      break;
                  case 2:
                      break;
              }
           }
           else
           {
              System.out.print("The central hub looks to be crumbling to pieces.");
              EntertoContinue_silent();
              System.out.println("More code snippets close in on you and the officer.");
              EntertoContinue_silent();
              System.out.print("\"That was unexpected.  It looks like the hub is getting worse.\"");
              EntertoContinue_silent();
              System.out.println("\"If we don't fix it soon...We're done...\"");
              System.out.println("1 - \"If I try to fix the central parity, can you keep the rogue code busy?\"\n2 - \"This is crazy!  I need to leave and fast!\" [Run]");
              
              selection = kb.nextInt();
              switch(selection)
              {
                  case 1:
                      find_main = true;
                      System.out.print("\"I think so; but make it quick.\"");
                      EntertoContinue_silent();
                      System.out.println("\"The way the hub's looking, it doesn't look like we have much time.\"");
                      EntertoContinue_silent();
                      System.out.println("Rogue code closes in on the two of you.");
                      EntertoContinue_silent();
                      System.out.println("\"Go!  I'll hold them off.\"");
                      EntertoContinue_silent();
                      break;
                  case 2:
                      System.out.print("You take off, leaving the officer confused and at the mercy\nof the rogue code.");
                      EntertoContinue_silent();
                      break;
              }
           }
           
           System.out.print("You run towards the parity, seeing no other way to go.");
           EntertoContinue_silent();
           System.out.println("Rogue code snippets quickly block your way.");
           EntertoContinue_silent();
           if(enemies.size() == 0)
           {
               enemies.add(testEnemy);
               enemies.add(lieutenant);
               enemies.add(testEnemy2);
               enemies.add(lieutenant2);
               enemies.add(testEnemy3);
               enemies.add(lieutenant3);
           }
           
           battleSystem(p, enemies);
       }
       
       EntertoContinue();
       
       if(p.getHealth() < 100) //The final choice.
       {
           System.out.print("You notice something familiar about the code in the parity.");
           EntertoContinue_silent();
           System.out.println("This code pertains to the integrity of the World Wide Web!");
           EntertoContinue_silent();
           System.out.println("If it collapses, there could be severe consequences to the world and to you...");
           EntertoContinue_silent();
           System.out.print("If you hack the parity, you can ensure that you get out.");
           EntertoContinue_silent();
           System.out.println("However, the Web may be left completely vulnerable to hacking...");
           EntertoContinue_silent();
           System.out.print("If you fix the parity, your new server will be complete and the Web will be safe.");
           EntertoContinue_silent();
           System.out.println("However, you may not be able to leave...");
           EntertoContinue_silent();
           System.out.println("1 - Fix the parity\n2 - Hack the parity\n3 - Leave the parity alone.");
           selection = kb.nextInt();
           switch(selection)
           {
               case 1:
                   fix_main = true;
                   System.out.println("\"I might as well finish my work.\"");
                   EntertoContinue_silent();
                   System.out.print("You reach both hands out to the parity, letting your\nthoughts become code and fixing the parity.");
                   EntertoContinue_silent();
                   System.out.print("The parity settles down as does the rogue code...");
                   EntertoContinue_silent();
                   System.out.print("The rogue code wraps itself around you...");
                   EntertoContinue_silent();
                   System.out.print("The colors change from red and black to blue and white...");
                   EntertoContinue_silent();
                   System.out.print("You hear an explosion around you before the world quickly turns to black...");
                   EntertoContinue_silent();
                   System.out.print("You wake up to find yourself back home.\nYour computer is turned off due to the power surge.");
                   EntertoContinue_silent();
                   System.out.println("You turn on the computer and check the status of your server...");
                   break;
               case 2:
                   hack_main = true;
                   System.out.println("\"This server is hopeless.  I better close it down.\"");
                   EntertoContinue_silent();
                   System.out.print("You reach both hands out to the parity, letting your\nthoughts become code.");
                   EntertoContinue_silent();
                   System.out.print("The parity sparks and crackles, destroying all around it...");
                   EntertoContinue_silent();
                   System.out.print("The parity turns into a bright hole in the middle of nowhere...");
                   EntertoContinue_silent();
                   System.out.print("You are sucked in to the parity...");
                   EntertoContinue_silent();
                   System.out.print("You hear an explosion around you...");
                   EntertoContinue_silent();
                   System.out.print("You wake up to find yourself back home.\nYour computer is turned off due to the power surge.");
                   EntertoContinue_silent();
                   System.out.println("You turn on the computer and check the status of your server...");
                   break;
               case 3:
                   System.out.println("\"...\"");
                   EntertoContinue_silent();
                   System.out.print("For some reason, you find yourself unable to act...");
                   EntertoContinue_silent();
                   System.out.print("Suddenly, the rogue code and the parity charge towards you...");
                   EntertoContinue_silent();
                   System.out.print("Unable to move, you feel the rogue code and the parity\ncollide on top of you...");
                   EntertoContinue_silent();
                   System.out.print("You hear and feel an explosion directly centered on you...\nEverything fades...");
                   EntertoContinue_silent();
                   System.out.print("You wake up surrounded by nothing but black space...");
                   EntertoContinue_silent();
                   System.out.println("You see bits of familiar code float away and fade into nothing...");
                   break;
           }
       }
       
       EntertoContinue();
       p.removeFeats();
       p.removeItems();
       p.resetHealth();
   }
   
   //Stops the output until the user presses Enter.
   public static void EntertoContinue()
   {
      Scanner c = new Scanner(System.in);
      System.out.println("Press Enter to continue.");
      c.nextLine();
   }
   
   //EntertoContinue, without a printed message.
   public static void EntertoContinue_silent()
   {
      Scanner sl = new Scanner(System.in);
      sl.nextLine();
   }
   
   //Checks if there are multiples of an object.
   public static String multiples(int d)
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
   
   //Resets the enemies after a battle.
   public static void resetEnemies(ArrayList<NPC> en)
   {
      int size = en.size();
      for(int i = 0; i < size; i++)
      {
         en.remove(0);
      }      
   }
   
   //The battle system, used to cover general battle mechanics.
   public static void battleSystem(playerObject p, ArrayList<NPC> en)
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
            //p.resetHealth();
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
         ini_test = 0;
         for(NPC n: en)
         {
            if(n.getIni() > ini_test && n.getHealth() > 0)
            {
               ini_test = n.getIni();
               starter = n;
            }
            //System.out.println("Highest Initiative: " + ini_test); //DEBUG: Shows what the highest initiative is.
         } //Checks each enemy for their initiative.
      }
      resetEnemies(en);
      if(p.getUnarmed())
      {
         p.resetUnarmed();
      }   
   }
   
   //Resets the choice variables for a new game.
   public void resetChoices()
   {
      blob_exm = false; //Examine the code blobs.
      par_exm = false; //Examine the parity.
      par_fix = false; //Choose between fixing the parity and leaving it.
      code = false; //Examine the blobs before battle.
      npc_lives = true; //Talk to or ambush npcs.
      help_npc = false; //Helped the npc with her job.
      needtoleave = false; //Select need to leave dialog.
      help_webroot = false; //Helped the officer?
      kill_webroot = false; //Attacked the officer?
      find_main = false; //Went to find the main parity.
      fix_main = false; //Decided to fix the main parity.
      hack_main = false; //Decided to hack the main parity.
      fullhackend = false; //The full dark side ending.
      fullfixend = false; //The full light side ending.
      rest_desc = false; //Found a restorative.
      boost_desc = false; //Found a boost potion.
   }
   
   //Displays text relevant to the ending.
   public void endingDialog()
   {
      if(!fix_main && !hack_main) //Neutral End.
      {
          System.out.print("As you float by yourself, you wonder about what happened outside of where you are...");
          EntertoContinue_silent();
          System.out.print("Whatever it was, you won't see it...");
          EntertoContinue_silent();
          System.out.print("This realization leaves you ill at ease...");
          EntertoContinue_silent();
          System.out.println("Your left wondering what you could have done...");
      }
      else
      {
         System.out.print("As your server opens, you wonder about\nwhat effects your actions may or may not have had.");
         EntertoContinue_silent();
         if(npc_lives)
         {
             if(help_npc)
             {
                System.out.print("The server seems to be fully operational...");
                EntertoContinue_silent();
                System.out.print("The best internet can be found even around the corners of the globe.");
                EntertoContinue_silent();
                System.out.print("You are reminded of the young woman you saw...");
                EntertoContinue_silent();
                System.out.println("Motivated by your kindness, she must have stopped the parities...");
                EntertoContinue_silent();
             }
             else
             {
                System.out.print("The server seems to be operational...");
                EntertoContinue_silent();
                System.out.print("The internet is still very spotty in many parts of the world.");
                EntertoContinue_silent();
                System.out.print("You are reminded of the young woman you saw...");
                EntertoContinue_silent();
                System.out.println("Even though she tried, she must have been stopped by the parities...");
                EntertoContinue_silent();
             }
         }
         else
         {
             System.out.print("The server has limited operation outside of heavily-populated countries...");
             EntertoContinue_silent();
             System.out.print("For some reason, you are reminded of the young woman you ambushed...");
             EntertoContinue_silent();
             System.out.println("You wonder why she came to mind...");
             EntertoContinue_silent();
         }
         
         System.out.print("With the internet ");
         if(help_npc)
         {
            System.out.print("being everywhere");
         }
         else
         {
            System.out.print("being accessible again");
         }
         
         if(help_webroot)
         {
            System.out.print(", you are surprised there aren't any hacking reports...");
            EntertoContinue_silent();
            System.out.print("The officer you helped, for some reason, came to mind...");
            EntertoContinue_silent();
            System.out.print("\"Maybe he made it...Maybe I saved him...\"");
            EntertoContinue_silent();
            System.out.println("Or maybe you were just thinking out loud...");
            EntertoContinue_silent();
         }
         else
         {
            if(kill_webroot)
            {
               System.out.print(", it would be assumed that people are being hacked...");
               EntertoContinue_silent();
               System.out.print("But you never thought it would be this prevalent...");
               EntertoContinue_silent();
               System.out.print("Almost everyone's computers have been hacked...");
               EntertoContinue_silent();
               System.out.print("The officer you killed, for some reason, came to mind...");
               EntertoContinue_silent();
               System.out.println("You quickly dismiss that thought...He couldn't have been that important...");
               EntertoContinue_silent();
            }
            else
            {
               System.out.print(", it would be assumed that people are being hacked.");
               EntertoContinue_silent();
               System.out.print("But it seems fine, not so much as to cause worry...");
               EntertoContinue_silent();
               System.out.print("The officer you fought with, for some reason, came to mind...");
               EntertoContinue_silent();
               System.out.println("You can't help but wonder why...");
               EntertoContinue_silent();
            }
         }
         
         if(find_main)
         {
             System.out.print("After everything, you hope the parities won't come back...");
         }
         else
         {
             System.out.print("You still feel a little winded from everything that happened...");
             EntertoContinue_silent();
             System.out.println("But, at least your back home...");
         }
         
         EntertoContinue_silent();
         
         if(fix_main)
         {
             if(find_main)
             {
                System.out.print("Thankfully, you ");
             }
             else
             {
                System.out.print("You ");
             }
             System.out.print("remember the code you put into the central parity...");
             EntertoContinue_silent();
             System.out.print("With that, ");
             if(help_npc)
             {
                System.out.print("the young woman's work, and ");
             }
             if(help_webroot)
             {
                if(!help_npc)
                {
                   System.out.print("and ");
                }
                System.out.print("the officer's work...");
             }
             System.out.println("you feel everything should be alright...");
             EntertoContinue_silent();
         }
         else
         {
             System.out.print("Although you got back home, hacking the parity may have inadvertedly cause some security leaks...");
             EntertoContinue_silent();
             System.out.println("You feel concerned for what may or may not happen...");
             EntertoContinue_silent();
         }
         
         if(!npc_lives)
         {
             if(kill_webroot)
             {
                 if(hack_main)
                 {
                     fullhackend = true;
                 }    
             }
         }
         
         if(npc_lives)
         {
             if(help_webroot)
             {
                 if(fix_main)
                 {
                     fullfixend = true;
                 }
             }
         }
         
         if(fullhackend)
         {
             System.out.print("Suddenly, you notice a power outage all over the area...");
             EntertoContinue_silent();
             System.out.print("You pull out your phone and notice that all wifi and other connections are gone...");
             EntertoContinue_silent();
             System.out.print("Without any protection, the server must have not been able\nto handle all the hacking...");
             EntertoContinue_silent();
             System.out.print("As such, the server must have overloaded and shut down the World Wide Web...");
             EntertoContinue_silent();
             System.out.println("You can't imagine how much this will set back humanity...");
             EntertoContinue_silent();
         }
         
         if(fullfixend)
         {
             System.out.print("As you look on the web, you notice something...");
             EntertoContinue_silent();
             System.out.print("Everyone is grateful that they can all communicate safely...");
             EntertoContinue_silent();
             System.out.print("You think to yourself...");
             EntertoContinue_silent();
             System.out.print("\"I made life better for all these people...\"");
             EntertoContinue_silent();
             System.out.println("You take some comfort knowing that you did something to help the world...");
             EntertoContinue_silent();
         }
         
      }
      
      System.out.println("THE END");
      EntertoContinue();
      
   }

   //The main statement, responsible for activating the dialogTest.
   public static void main(String[] args)
   {
      Scanner main_sys = new Scanner(System.in);
      dialogTest d = new dialogTest();
      int selection;
      boolean testing = false;
      
      do
      {
      if(!testing)
      {
         do
         {
            System.out.println("Welcome to Back-Door Out: A Text Based Adventure Game!\nPlease select your option\n1 - Start Game   2 - Exit");
            selection = main_sys.nextInt();
         }while(selection > 2 || selection < 1);
      }
      else
      {
         do
         {
            System.out.println("Welcome to Back-Door Out: A Text Based Adventure Game!\nPlease select your option\n1 - Start Game   2 - Run Method Tests   3 - Exit");
            selection = main_sys.nextInt();
         }while(selection > 3 || selection < 1);
      }
      
      switch(selection)
      {
          case 1:
             d.p.setHealth(0);
             d.run();
             d.endingDialog();
             break;
          case 2:
             if(testing)
             {
                d.open();
             }
             else
             {
                System.out.println("Ending...");
                selection = 3;
                System.exit(0);
             }
             break;
          case 3:
             System.out.println("Ending...");
             System.exit(0);
             break;
      }
      d.resetChoices();
      }while(selection != 3);
   }
}
