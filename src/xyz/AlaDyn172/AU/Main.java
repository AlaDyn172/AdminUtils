package xyz.AlaDyn172.AU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
  extends JavaPlugin
  implements Listener
{
  public void onEnable()
  {
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
    
    ConsoleCommandSender console = getServer().getConsoleSender();
    
    getConfig().options().copyDefaults(true);
    saveConfig();
    new Metrics(this);
    
    console.sendMessage(ChatColor.GREEN + "[AdminUtils] Plugin developed by Echo. You're using v2.4!");
    
    baneedPlayers = getConfig().getStringList("playersBanned");
  }
  
  public void onDisable()
  {
    saveConfig();
  }
  
  public List<String> baneedPlayers = getConfig().getStringList("playersBanned");
  public String NameCommand1 = getConfig().getString("NameCommand1").replaceAll("&", "§");
  public String NameCommand2 = getConfig().getString("NameCommand2").replaceAll("&", "§");
  public String NameCommand3 = getConfig().getString("NameCommand3").replaceAll("&", "§");
  public String NameCommand4 = getConfig().getString("NameCommand4").replaceAll("&", "§");
  public String NameCommand5 = getConfig().getString("NameCommand5").replaceAll("&", "§");
  public String NameCommand6 = getConfig().getString("NameCommand6").replaceAll("&", "§");
  public String NameCommand7 = getConfig().getString("NameCommand7").replaceAll("&", "§");
  public String ExecCommand1 = getConfig().getString("ExecCommand1");
  public String ExecCommand2 = getConfig().getString("ExecCommand2");
  public String ExecCommand3 = getConfig().getString("ExecCommand3");
  public String ExecCommand4 = getConfig().getString("ExecCommand4");
  public String ExecCommand5 = getConfig().getString("ExecCommand5");
  public String ExecCommand6 = getConfig().getString("ExecCommand6");
  public String ExecCommand7 = getConfig().getString("ExecCommand7");
  public boolean Guicloseaftercustomcommandexec = getConfig().getBoolean("Gui-close-after-customcommand-exec");
  public String noPermission = getConfig().getString("noPermission").replaceAll("&", "§");
  
  public void sendMessageEmpty(Player p, String Number)
  {
    p.sendMessage(ChatColor.RED + "You need to modify variable '" + Number + "' from config.yml to execute a command!");
  }
  
  public void setExecCMD(String ExecCommandNR, String NewExecCommandNR)
  {
    getConfig().set("ExecCommand" + ExecCommandNR, NewExecCommandNR);
    saveConfig();
    reloadConfig();
  }
  
  public void setNameCMD(String NameCommandNR, String NewCommandNR)
  {
    getConfig().set("NameCommand" + NameCommandNR, NewCommandNR);
    saveConfig();
    reloadConfig();
  }
  
  public void openGui(Player player, String nameGui)
  {
    if (nameGui == "default")
    {
      Inventory inv = Bukkit.createInventory(player, 9, ChatColor.WHITE + "Menu -- " + ChatColor.BLUE + "Admin Utils");
      
      ItemStack item = new ItemStack(Material.COMPASS);
      ItemMeta itemM = item.getItemMeta();
      itemM.setDisplayName(ChatColor.WHITE + "Close");
      item.setItemMeta(itemM);
      inv.setItem(inv.getSize() - 1, item);
      
      ItemStack item01 = new ItemStack(Material.ANVIL);
      ItemMeta itemM01 = item01.getItemMeta();
      itemM01.setDisplayName(ChatColor.GREEN + "Reload server");
      item01.setItemMeta(itemM01);
      inv.setItem(0, item01);
      
      ItemStack item02 = new ItemStack(Material.PAPER);
      ItemMeta itemM02 = item02.getItemMeta();
      itemM02.setDisplayName(ChatColor.RED + "Whitelist settings");
      item02.setItemMeta(itemM02);
      inv.setItem(1, item02);
      
      ItemStack item03 = new ItemStack(Material.REDSTONE_BLOCK);
      ItemMeta itemM03 = item03.getItemMeta();
      itemM03.setDisplayName(ChatColor.BLUE + "Difficulty settings");
      item03.setItemMeta(itemM03);
      inv.setItem(2, item03);
      
      ItemStack item04 = new ItemStack(Material.OBSIDIAN);
      ItemMeta itemM04 = item04.getItemMeta();
      itemM04.setDisplayName(ChatColor.GRAY + "Sanctions menu");
      item04.setItemMeta(itemM04);
      inv.setItem(3, item04);
      
      ItemStack item05 = new ItemStack(Material.ENCHANTED_BOOK);
      ItemMeta itemM05 = item05.getItemMeta();
      itemM05.setDisplayName(ChatColor.YELLOW + "Custom Commands");
      item05.setItemMeta(itemM05);
      inv.setItem(4, item05);
      
      ItemStack item06 = new ItemStack(Material.BEACON);
      ItemMeta itemM06 = item06.getItemMeta();
      itemM06.setDisplayName(ChatColor.RED + "Change gamemode");
      item06.setItemMeta(itemM06);
      inv.setItem(5, item06);
      
      player.openInventory(inv);
    }
    if (nameGui == "cgamemode")
    {
      Inventory inv = Bukkit.createInventory(player, 9, ChatColor.WHITE + "Gamemode Menu -- " + ChatColor.BLUE + "Admin Utils");
      
      ItemStack item = new ItemStack(Material.COMPASS);
      ItemMeta itemM = item.getItemMeta();
      itemM.setDisplayName(ChatColor.WHITE + "Back");
      item.setItemMeta(itemM);
      inv.setItem(inv.getSize() - 1, item);
      
      ItemStack item01 = new ItemStack(Material.GOLD_ORE);
      ItemMeta itemM01 = item01.getItemMeta();
      itemM01.setDisplayName(ChatColor.GOLD + "ADVENTURE");
      item01.setItemMeta(itemM01);
      inv.setItem(0, item01);
      
      ItemStack item02 = new ItemStack(Material.IRON_ORE);
      ItemMeta itemM02 = item02.getItemMeta();
      itemM02.setDisplayName(ChatColor.YELLOW + "CREATIVE");
      item02.setItemMeta(itemM02);
      inv.setItem(1, item02);
      
      ItemStack item03 = new ItemStack(Material.DIAMOND_ORE);
      ItemMeta itemM03 = item03.getItemMeta();
      itemM03.setDisplayName(ChatColor.BLUE + "SPECTATOR");
      item03.setItemMeta(itemM03);
      inv.setItem(2, item03);
      
      ItemStack item04 = new ItemStack(Material.LAPIS_ORE);
      ItemMeta itemM04 = item04.getItemMeta();
      itemM04.setDisplayName(ChatColor.DARK_BLUE + "SURVIVAL");
      item04.setItemMeta(itemM04);
      inv.setItem(3, item04);
      
      player.openInventory(inv);
    }
    if (nameGui == "whitelist")
    {
      Inventory inv = Bukkit.createInventory(player, 9, ChatColor.WHITE + "Whitelist Menu -- " + ChatColor.BLUE + "Admin Utils");
      
      ItemStack item = new ItemStack(Material.COMPASS);
      ItemMeta itemM = item.getItemMeta();
      itemM.setDisplayName(ChatColor.WHITE + "Back");
      item.setItemMeta(itemM);
      inv.setItem(inv.getSize() - 1, item);
      
      ItemStack item01 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM01 = item01.getItemMeta();
      itemM01.setDisplayName(ChatColor.GREEN + "Whitelist: OFF");
      item01.setItemMeta(itemM01);
      inv.setItem(0, item01);
      
      ItemStack item02 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM02 = item02.getItemMeta();
      itemM02.setDisplayName(ChatColor.RED + "Whitelist: ON");
      item02.setItemMeta(itemM02);
      inv.setItem(1, item02);
      
      ItemStack item03 = new ItemStack(Material.LEVER);
      ItemMeta itemM03 = item03.getItemMeta();
      itemM03.setDisplayName(ChatColor.GRAY + "Reload whitelist");
      item03.setItemMeta(itemM03);
      inv.setItem(2, item03);
      
      player.openInventory(inv);
    }
    if (nameGui == "difficulty")
    {
      Inventory inv = Bukkit.createInventory(player, 9, ChatColor.WHITE + "Difficulty Menu -- " + ChatColor.BLUE + "Admin Utils");
      
      ItemStack item = new ItemStack(Material.COMPASS);
      ItemMeta itemM = item.getItemMeta();
      itemM.setDisplayName(ChatColor.WHITE + "Back");
      item.setItemMeta(itemM);
      inv.setItem(inv.getSize() - 1, item);
      
      ItemStack item01 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM01 = item01.getItemMeta();
      itemM01.setDisplayName(ChatColor.GREEN + "Difficulty: Peaceful");
      item01.setItemMeta(itemM01);
      inv.setItem(0, item01);
      
      ItemStack item02 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM02 = item02.getItemMeta();
      itemM02.setDisplayName(ChatColor.RED + "Difficulty: Easy");
      item02.setItemMeta(itemM02);
      inv.setItem(1, item02);
      
      ItemStack item03 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM03 = item03.getItemMeta();
      itemM03.setDisplayName(ChatColor.BLUE + "Difficulty: Normal");
      item03.setItemMeta(itemM03);
      inv.setItem(2, item03);
      
      ItemStack item04 = new ItemStack(Material.WHITE_WOOL);
      ItemMeta itemM04 = item04.getItemMeta();
      itemM04.setDisplayName(ChatColor.YELLOW + "Difficulty: Hard");
      item04.setItemMeta(itemM04);
      inv.setItem(3, item04);
      
      player.openInventory(inv);
    }
    if (nameGui == "sanctions")
    {
      Inventory inv = Bukkit.createInventory(player, 9, ChatColor.WHITE + "Sanctions Menu -- " + ChatColor.BLUE + "Admin Utils");
      
      ItemStack item = new ItemStack(Material.COMPASS);
      ItemMeta itemM = item.getItemMeta();
      itemM.setDisplayName(ChatColor.WHITE + "Back");
      item.setItemMeta(itemM);
      inv.setItem(inv.getSize() - 1, item);
      
      ItemStack item01 = new ItemStack(Material.BEDROCK);
      ItemMeta itemM01 = item01.getItemMeta();
      itemM01.setDisplayName(ChatColor.GREEN + "Ban a player");
      item01.setItemMeta(itemM01);
      inv.setItem(0, item01);
      
      ItemStack item02 = new ItemStack(Material.END_PORTAL_FRAME);
      ItemMeta itemM02 = item02.getItemMeta();
      itemM02.setDisplayName(ChatColor.RED + "Kick a player");
      item02.setItemMeta(itemM02);
      inv.setItem(1, item02);
      
      ItemStack item03 = new ItemStack(Material.BARRIER);
      ItemMeta itemM03 = item03.getItemMeta();
      itemM03.setDisplayName(ChatColor.BLUE + "Mute a player");
      item03.setItemMeta(itemM03);
      inv.setItem(2, item03);
      
      ItemStack item04 = new ItemStack(Material.TNT);
      ItemMeta itemM04 = item04.getItemMeta();
      itemM04.setDisplayName(ChatColor.YELLOW + "Kick all players");
      item04.setItemMeta(itemM04);
      inv.setItem(3, item04);
      
      player.openInventory(inv);
    }
    ItemStack item01;
    if (nameGui == "customcommands")
    {
      Inventory inv = Bukkit.createInventory(player, 9, ChatColor.WHITE + "Custom Commands -- " + ChatColor.BLUE + "Admin Utils");
      
      ItemStack item = new ItemStack(Material.COMPASS);
      ItemMeta itemM = item.getItemMeta();
      itemM.setDisplayName(ChatColor.WHITE + "Back");
      item.setItemMeta(itemM);
      inv.setItem(inv.getSize() - 1, item);
      
      item01 = new ItemStack(Material.PAPER);
      ItemMeta itemM01 = item01.getItemMeta();
      itemM01.setDisplayName(this.NameCommand1);
      item01.setItemMeta(itemM01);
      inv.setItem(0, item01);
      
      ItemStack item02 = new ItemStack(Material.PAPER);
      ItemMeta itemM02 = item02.getItemMeta();
      itemM02.setDisplayName(this.NameCommand2);
      item02.setItemMeta(itemM02);
      inv.setItem(1, item02);
      
      ItemStack item03 = new ItemStack(Material.PAPER);
      ItemMeta itemM03 = item03.getItemMeta();
      itemM03.setDisplayName(this.NameCommand3);
      item03.setItemMeta(itemM03);
      inv.setItem(2, item03);
      
      ItemStack item04 = new ItemStack(Material.PAPER);
      ItemMeta itemM04 = item04.getItemMeta();
      itemM04.setDisplayName(this.NameCommand4);
      item04.setItemMeta(itemM04);
      inv.setItem(3, item04);
      
      ItemStack item05 = new ItemStack(Material.PAPER);
      ItemMeta itemM05 = item05.getItemMeta();
      itemM05.setDisplayName(this.NameCommand5);
      item05.setItemMeta(itemM05);
      inv.setItem(4, item05);
      
      ItemStack item06 = new ItemStack(Material.PAPER);
      ItemMeta itemM06 = item06.getItemMeta();
      itemM06.setDisplayName(this.NameCommand6);
      item06.setItemMeta(itemM06);
      inv.setItem(5, item06);
      
      ItemStack item07 = new ItemStack(Material.PAPER);
      ItemMeta itemM07 = item07.getItemMeta();
      itemM07.setDisplayName(this.NameCommand7);
      item07.setItemMeta(itemM07);
      inv.setItem(6, item07);
      
      player.openInventory(inv);
    }
    ItemStack item;
    if (nameGui == "banListPlayers")
    {
      Inventory inv = Bukkit.createInventory(player, 54, ChatColor.WHITE + "Ban Player -- " + ChatColor.BLUE + "Admin Utils");
      List<String> players = new ArrayList<String>();
      for (Player p : Bukkit.getOnlinePlayers()) {
        players.add(p.getName());
      }
      for (int x = 0; x < players.size(); x++)
      {
        item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta)item.getItemMeta();
        skull.setDisplayName(ChatColor.RED + (String)players.get(x));
        
        String playerul = players.get(x);
        Player psld = getServer().getPlayerExact(playerul);
        
        skull.setOwningPlayer(getServer().getOfflinePlayer(psld.getUniqueId()));
        skull.setLore(Arrays.asList(new String[] { "", ChatColor.GRAY + "Click here to ban " + ChatColor.RED + (String)players.get(x) }));
        item.setItemMeta(skull);
        inv.addItem(new ItemStack[] { item });
      }
      player.openInventory(inv);
    }
    if (nameGui == "kickListPlayers")
    {
      Inventory inv = Bukkit.createInventory(player, 54, ChatColor.WHITE + "Kick Player -- " + ChatColor.BLUE + "Admin Utils");
      List<String> players = new ArrayList<String>();
      for (Player p : Bukkit.getOnlinePlayers()) {
        players.add(p.getName());
      }
      for (int x = 0; x < players.size(); x++)
      {
        item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta)item.getItemMeta();
        skull.setDisplayName(ChatColor.RED + (String)players.get(x));
        
        String playerul = players.get(x);
        Player psld = getServer().getPlayerExact(playerul);
        
        skull.setOwningPlayer(getServer().getOfflinePlayer(psld.getUniqueId()));
        skull.setLore(Arrays.asList(new String[] { "", ChatColor.GRAY + "Click here to kick " + ChatColor.RED + (String)players.get(x) }));
        item.setItemMeta(skull);
        inv.addItem(new ItemStack[] { item });
      }
      player.openInventory(inv);
    }
    if (nameGui == "muteListPlayers")
    {
      Inventory inv = Bukkit.createInventory(player, 54, ChatColor.WHITE + "Mute Player -- " + ChatColor.BLUE + "Admin Utils");
      List<String> players = new ArrayList<String>();
      for (Player p : Bukkit.getOnlinePlayers()) {
        players.add(p.getName());
      }
      for (int x = 0; x < players.size(); x++)
      {
        item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta)item.getItemMeta();
        skull.setDisplayName(ChatColor.RED + (String)players.get(x));
        
        String playerul = players.get(x);
        Player psld = getServer().getPlayerExact(playerul);
        
        skull.setOwningPlayer(getServer().getOfflinePlayer(psld.getUniqueId()));
        skull.setLore(Arrays.asList(new String[] { "", ChatColor.GRAY + "Click here to mute " + ChatColor.RED + (String)players.get(x) + ChatColor.GRAY + " for 5 minutes." }));
        item.setItemMeta(skull);
        inv.addItem(new ItemStack[] { item });
      }
      player.openInventory(inv);
    }
  }
  
  @EventHandler
  public void onClickItem(InventoryClickEvent event)
  {
	  String invName = event.getView().getTitle();
	  
    if ((event.getWhoClicked() instanceof Player))
    {
      Player player = (Player)event.getWhoClicked();
      if ((invName != null) && 
        (invName.equalsIgnoreCase(ChatColor.WHITE + "Menu -- " + ChatColor.BLUE + "Admin Utils")))
      {
        event.setCancelled(true);
        if (event.getCurrentItem() != null)
        {
          ItemStack item = event.getCurrentItem();
          if (item.getType() == Material.COMPASS) {
            player.closeInventory();
          } else if (item.getType() == Material.ANVIL)
          {
            if (player.hasPermission("adminutils.reload"))
            {
              Bukkit.broadcastMessage(ChatColor.GOLD + "Reloading server... ");
              player.closeInventory();
              Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "reload");
            }
            else
            {
              player.sendMessage(this.noPermission);
              player.closeInventory();
            }
          }
          else if (item.getType() == Material.PAPER)
          {
            if (player.hasPermission("adminutils.whitelist"))
            {
              openGui(player, "whitelist");
            }
            else
            {
              player.sendMessage(this.noPermission);
              player.closeInventory();
            }
          }
          else if (item.getType() == Material.REDSTONE_BLOCK)
          {
            if (player.hasPermission("adminutils.difficulty"))
            {
              openGui(player, "difficulty");
            }
            else
            {
              player.sendMessage(this.noPermission);
              player.closeInventory();
            }
          }
          else if (item.getType() == Material.OBSIDIAN)
          {
            if (player.hasPermission("adminutils.sanctions"))
            {
              openGui(player, "sanctions");
            }
            else
            {
              player.sendMessage(this.noPermission);
              player.closeInventory();
            }
          }
          else if (item.getType() == Material.ENCHANTED_BOOK) {
            if (player.hasPermission("adminutils.customcommands"))
            {
              openGui(player, "customcommands");
            }
            else
            {
              player.sendMessage(this.noPermission);
              player.closeInventory();
            }
          }
          else if (item.getType() == Material.BEACON) {
	          if (player.hasPermission("adminutils.cgamemode"))
	          {
	            openGui(player, "cgamemode");
	          }
	          else
	          {
	            player.sendMessage(this.noPermission);
	            player.closeInventory();
	          }
	       }
        }
      }
    }
    if ((event.getWhoClicked() instanceof Player))
    {
      Player player = (Player)event.getWhoClicked();
      if ((invName != null) && 
        (invName.equalsIgnoreCase(ChatColor.WHITE + "Whitelist Menu -- " + ChatColor.BLUE + "Admin Utils")))
      {
        event.setCancelled(true);
        if (event.getCurrentItem() != null)
        {
          ItemStack item = event.getCurrentItem();
          if (item.getType() == Material.COMPASS)
          {
            openGui(player, "default");
          }
          else if (item.getType() == Material.WHITE_WOOL)
          {
            if ((item.hasItemMeta()) && (item.getItemMeta().hasDisplayName()))
            {
              if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Whitelist: OFF"))
              {
                Bukkit.getServer().setWhitelist(false);
                player.sendMessage(ChatColor.GOLD + "Whitelist set to OFF.");
              }
              if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Whitelist: ON"))
              {
                Bukkit.getServer().setWhitelist(true);
                player.sendMessage(ChatColor.GOLD + "Whitelist set to ON.");
              }
            }
          }
          else if (item.getType() == Material.LEVER)
          {
            player.sendMessage(ChatColor.GOLD + "Whitelist reloaded!");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "whitelist reload");
          }
        }
      }
    }
    if ((event.getWhoClicked() instanceof Player))
    {
      Player player = (Player)event.getWhoClicked();
      if ((invName != null) && 
        (invName.equalsIgnoreCase(ChatColor.WHITE + "Gamemode Menu -- " + ChatColor.BLUE + "Admin Utils")))
      {
        event.setCancelled(true);
        if (event.getCurrentItem() != null)
        {
          ItemStack item = event.getCurrentItem();
          if (item.getType() == Material.COMPASS)
          {
            openGui(player, "default");
          }
          else if (item.getType() == Material.GOLD_ORE)
          {
              player.setGameMode(GameMode.ADVENTURE);
              player.sendMessage(ChatColor.GOLD + "Your GAMEMODE has been set to " + ChatColor.GREEN + "ADVENTURE");
              player.closeInventory();
          }
          else if (item.getType() == Material.IRON_ORE)
          {
              player.setGameMode(GameMode.CREATIVE);
              player.sendMessage(ChatColor.GOLD + "Your GAMEMODE has been set to " + ChatColor.GREEN + "CREATIVE");
              player.closeInventory();
          }
          else if (item.getType() == Material.DIAMOND_ORE)
          {
              player.setGameMode(GameMode.SPECTATOR);
              player.sendMessage(ChatColor.GOLD + "Your GAMEMODE has been set to " + ChatColor.GREEN + "SPECTATOR");
              player.closeInventory();
          }
          else if (item.getType() == Material.LAPIS_ORE)
          {
              player.setGameMode(GameMode.SURVIVAL);
              player.sendMessage(ChatColor.GOLD + "Your GAMEMODE has been set to " + ChatColor.GREEN + "SURVIVAL");
              player.closeInventory();
          }
        }
      }
    }
    if ((event.getWhoClicked() instanceof Player))
    {
      Player player = (Player)event.getWhoClicked();
      if ((invName != null) && 
        (invName.equalsIgnoreCase(ChatColor.WHITE + "Difficulty Menu -- " + ChatColor.BLUE + "Admin Utils")))
      {
        event.setCancelled(true);
        if (event.getCurrentItem() != null)
        {
          ItemStack item = event.getCurrentItem();
          if (item.getType() == Material.COMPASS)
          {
            openGui(player, "default");
          }
          else if ((item.getType() == Material.WHITE_WOOL) && 
            (item.hasItemMeta()) && (item.getItemMeta().hasDisplayName()))
          {
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Difficulty: Peaceful"))
            {
              Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "difficulty 0");
              player.sendMessage(ChatColor.GOLD + "Difficulty set to Peaceful.");
            }
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Difficulty: Easy"))
            {
              Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "difficulty 1");
              player.sendMessage(ChatColor.GOLD + "Difficulty set to Easy.");
            }
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Difficulty: Normal"))
            {
              Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "difficulty 2");
              player.sendMessage(ChatColor.GOLD + "Difficulty set to Normal.");
            }
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Difficulty: Hard"))
            {
              Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "difficulty 3");
              player.sendMessage(ChatColor.GOLD + "Difficulty set to Hard.");
            }
          }
        }
      }
    }
    if ((event.getWhoClicked() instanceof Player))
    {
      Player player = (Player)event.getWhoClicked();
      if ((invName != null) && 
        (invName.equalsIgnoreCase(ChatColor.WHITE + "Sanctions Menu -- " + ChatColor.BLUE + "Admin Utils")))
      {
        event.setCancelled(true);
        if (event.getCurrentItem() != null)
        {
          ItemStack item = event.getCurrentItem();
          if (item.getType() == Material.COMPASS) {
            openGui(player, "default");
          } else if (item.getType() == Material.BEDROCK)
          {
            if (player.hasPermission("adminutils.banplayer"))
            {
              openGui(player, "banListPlayers");
            }
            else
            {
              player.sendMessage(this.noPermission);
              player.closeInventory();
            }
          }
          else if (item.getType() == Material.END_PORTAL_FRAME)
          {
            if (player.hasPermission("adminutils.kickplayer"))
            {
              openGui(player, "kickListPlayers");
            }
            else
            {
              player.sendMessage(this.noPermission);
              player.closeInventory();
            }
          }
          else if (item.getType() == Material.BARRIER)
          {
            if (player.hasPermission("adminutils.muteplayer"))
            {
              openGui(player, "muteListPlayers");
            }
            else
            {
              player.sendMessage(this.noPermission);
              player.closeInventory();
            }
          }
          else if (item.getType() == Material.TNT) {
            if (player.hasPermission("adminutils.kickallplayers"))
            {
              for (Player AllPlayers : Bukkit.getOnlinePlayers()) {
                if (!AllPlayers.isOp()) {
                  AllPlayers.kickPlayer(ChatColor.RED + "You have been kicked from the server by an Admin!");
                }
              }
              player.sendMessage(ChatColor.GOLD + "You kicked all players from the server!");
            }
            else
            {
              player.sendMessage(this.noPermission);
              player.closeInventory();
            }
          }
        }
      }
    }
    if ((event.getWhoClicked() instanceof Player))
    {
      Player player = (Player)event.getWhoClicked();
      if ((invName != null) && 
        (invName.equalsIgnoreCase(ChatColor.WHITE + "Custom Commands -- " + ChatColor.BLUE + "Admin Utils")))
      {
        event.setCancelled(true);
        if (event.getCurrentItem() != null)
        {
          ItemStack item = event.getCurrentItem();
          if (item.getType() == Material.COMPASS) {
            openGui(player, "default");
          } else if (item.getType() == Material.PAPER) {
            if (player.hasPermission("adminutils.customcommands"))
            {
              if ((item.hasItemMeta()) && (item.getItemMeta().hasDisplayName()))
              {
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(this.NameCommand1)) {
                  if (this.ExecCommand1.equals("empty"))
                  {
                    sendMessageEmpty(player, "ExecCommand1");
                  }
                  else
                  {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), this.ExecCommand1);
                    if (this.Guicloseaftercustomcommandexec) {
                      player.closeInventory();
                    }
                  }
                }
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(this.NameCommand2)) {
                  if (this.ExecCommand2.equals("empty"))
                  {
                    sendMessageEmpty(player, "ExecCommand2");
                  }
                  else
                  {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), this.ExecCommand2);
                    if (this.Guicloseaftercustomcommandexec) {
                      player.closeInventory();
                    }
                  }
                }
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(this.NameCommand3)) {
                  if (this.ExecCommand3.equals("empty"))
                  {
                    sendMessageEmpty(player, "ExecCommand3");
                  }
                  else
                  {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), this.ExecCommand3);
                    if (this.Guicloseaftercustomcommandexec) {
                      player.closeInventory();
                    }
                  }
                }
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(this.NameCommand4)) {
                  if (this.ExecCommand4.equals("empty"))
                  {
                    sendMessageEmpty(player, "ExecCommand4");
                  }
                  else
                  {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), this.ExecCommand4);
                    if (this.Guicloseaftercustomcommandexec) {
                      player.closeInventory();
                    }
                  }
                }
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(this.NameCommand5)) {
                  if (this.ExecCommand5.equals("empty"))
                  {
                    sendMessageEmpty(player, "ExecCommand5");
                  }
                  else
                  {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), this.ExecCommand5);
                    if (this.Guicloseaftercustomcommandexec) {
                      player.closeInventory();
                    }
                  }
                }
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(this.NameCommand6)) {
                  if (this.ExecCommand6.equals("empty"))
                  {
                    sendMessageEmpty(player, "ExecCommand6");
                  }
                  else
                  {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), this.ExecCommand6);
                    if (this.Guicloseaftercustomcommandexec) {
                      player.closeInventory();
                    }
                  }
                }
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(this.NameCommand7)) {
                  if (this.ExecCommand7.equals("empty"))
                  {
                    sendMessageEmpty(player, "ExecCommand7");
                  }
                  else
                  {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), this.ExecCommand7);
                    if (this.Guicloseaftercustomcommandexec) {
                      player.closeInventory();
                    }
                  }
                }
              }
            }
            else
            {
              player.sendMessage(this.noPermission);
              player.closeInventory();
            }
          }
        }
      }
    }
    if ((event.getWhoClicked() instanceof Player))
    {
      Player player = (Player)event.getWhoClicked();
      if ((invName != null) && 
        (invName.equalsIgnoreCase(ChatColor.WHITE + "Ban Player -- " + ChatColor.BLUE + "Admin Utils")))
      {
        event.setCancelled(true);
        if (event.getCurrentItem() != null)
        {
          ItemStack item = event.getCurrentItem();
          if (item.getType() == Material.COMPASS)
          {
            openGui(player, "default");
          }
          else if (item.getType() == Material.PLAYER_HEAD)
          {
            String player2 = item.getItemMeta().getDisplayName();
            String withoutColors = ChatColor.stripColor(player2);
            Player caca = getServer().getPlayer(withoutColors);
            
            if(player.getName() == caca.getPlayer().getName()) {
            	player.sendMessage(ChatColor.RED + "You can't ban yourself!");
            } else {
          		player.sendMessage(ChatColor.GREEN + "The player " + caca.getPlayer().getName() + " is now banned!");
                caca.getPlayer().kickPlayer(ChatColor.RED + "AdminUtils:" + ChatColor.GREEN + " You have been banned!");
                baneedPlayers.add(caca.getPlayer().getName());
                getConfig().set("playersBanned", baneedPlayers);
                saveConfig();
            }
          }
        }
      }
    }
    if ((event.getWhoClicked() instanceof Player))
    {
      Player player = (Player)event.getWhoClicked();
      if ((invName != null) && 
        (invName.equalsIgnoreCase(ChatColor.WHITE + "Kick Player -- " + ChatColor.BLUE + "Admin Utils")))
      {
        event.setCancelled(true);
        if (event.getCurrentItem() != null)
        {
          ItemStack item = event.getCurrentItem();
          if (item.getType() == Material.COMPASS)
          {
            openGui(player, "default");
          }
          else if (item.getType() == Material.PLAYER_HEAD)
          {
            String player2 = item.getItemMeta().getDisplayName();
            String withoutColors = ChatColor.stripColor(player2);
            Player caca = getServer().getPlayer(withoutColors);
            caca.getPlayer().kickPlayer(ChatColor.RED + "AdminUtils:" + ChatColor.GREEN + " You have been kicked!");
          }
        }
      }
    }
    if ((event.getWhoClicked() instanceof Player))
    {
      Player player = (Player)event.getWhoClicked();
      if ((invName != null) && 
        (invName.equalsIgnoreCase(ChatColor.WHITE + "Mute Player -- " + ChatColor.BLUE + "Admin Utils")))
      {
        event.setCancelled(true);
        if (event.getCurrentItem() != null)
        {
          ItemStack item = event.getCurrentItem();
          if (item.getType() == Material.COMPASS) {
            openGui(player, "default");
          } else if (item.getType() == Material.PLAYER_HEAD) {
            player.sendMessage("Mute a player feature will be working in future updates!");
          }
        }
      }
    }
    if ((event.getWhoClicked() instanceof Player))
    {
      Player player = (Player)event.getWhoClicked();
      if ((invName != null) && 
        (invName.equalsIgnoreCase(ChatColor.WHITE + "Gamemode Menu -- " + ChatColor.BLUE + "Admin Utils")))
      {
        event.setCancelled(true);
        if (event.getCurrentItem() != null)
        {
          ItemStack item = event.getCurrentItem();
          if (item.getType() == Material.COMPASS)
          {
            openGui(player, "default");
          }
          else if ((item.getType() == Material.WHITE_WOOL) && 
            (item.hasItemMeta()) && (item.getItemMeta().hasDisplayName()))
          {
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Difficulty: Peaceful"))
            {
              Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "difficulty 0");
              player.sendMessage(ChatColor.GOLD + "Difficulty set to Peaceful.");
            }
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Difficulty: Easy"))
            {
              Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "difficulty 1");
              player.sendMessage(ChatColor.GOLD + "Difficulty set to Easy.");
            }
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Difficulty: Normal"))
            {
              Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "difficulty 2");
              player.sendMessage(ChatColor.GOLD + "Difficulty set to Normal.");
            }
            if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Difficulty: Hard"))
            {
              Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "difficulty 3");
              player.sendMessage(ChatColor.GOLD + "Difficulty set to Hard.");
            }
          }
        }
      }
    }
  }
  
@EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerLogin(AsyncPlayerPreLoginEvent  e)
  {
      for(String string : baneedPlayers)
      {
    	 if(string.equalsIgnoreCase(e.getName()))
    	 {
    		 e.disallow(Result.KICK_OTHER, ChatColor.RED + "AdminUtils: " + ChatColor.GREEN + "You are banned on this server!");
    	 }
      }
  }
  
  public boolean onCommand(CommandSender sender, Command cmdLabel, String cmd, String[] args)
  {
	  
    if(!(sender instanceof Player)) {
    	Bukkit.getConsoleSender().sendMessage("You are not able to use /au command in Console!");
    	return true;
    }
	  
    Player p = (Player)sender;
    
    if (cmd.equalsIgnoreCase("au")) {
      openGui(p, "default");
    }
    if ((cmd.equalsIgnoreCase("setcc")) && 
      (p.hasPermission("adminutils.setcustomcommand"))) {
      if (args.length == 0)
      {
        p.sendMessage("USAGE: /setcc <1-7> <NewCommand>");
      }
      else if (args.length == 1)
      {
        p.sendMessage("USAGE: /setcc <1-7> <NewCommand>");
      }
      else if (getConfig().getString("ExecCommand" + args[0]) != null)
      {
        String forSend = "";
        for (int i = 1; i < args.length; i++)
        {
          String arguments = args[i] + " ";
          forSend = forSend + arguments;
          setExecCMD(args[0], forSend);
        }
        p.sendMessage(ChatColor.RED + "To enable " + ChatColor.GREEN + "/" + forSend + ChatColor.RED + " for " + ChatColor.GREEN + "ExecCommand" + args[0] + ChatColor.RED + " the server requires a reload! [/au --> Reload server].");
      }
      else
      {
        p.sendMessage(ChatColor.RED + "ExecCommand" + args[0] + " doesn't exist!");
      }
    }
    if ((cmd.equalsIgnoreCase("setnc")) && 
      (p.hasPermission("adminutils.setnamecommand"))) {
      if (args.length == 0)
      {
        p.sendMessage("USAGE: /setnc <1-7> <NameCommand>");
      }
      else if (args.length == 1)
      {
        p.sendMessage("USAGE: /setnc <1-7> <NameCommand>");
      }
      else if (getConfig().getString("NameCommand" + args[0]) != null)
      {
        String forSend = "";
        for (int i = 1; i < args.length; i++)
        {
          String arguments = args[i] + " ";
          forSend = forSend + arguments;
          setNameCMD(args[0], forSend);
        }
        p.sendMessage(ChatColor.RED + "To enable message: " + ChatColor.GREEN + forSend.replaceAll("&", "§") + ChatColor.RED + " for " + ChatColor.GREEN + "NameCommand" + args[0] + ChatColor.RED + " the server requires a reload! [/au --> Reload server].");
      }
      else
      {
        p.sendMessage(ChatColor.RED + "NameCommand" + args[0] + " doesn't exist!");
      }
    }
    if(cmd.equalsIgnoreCase("setunban") && p.hasPermission("adminutils.setunban"))
    {
    	if(args.length == 0)
    	{
    		p.sendMessage("USAGE: /setunban <player>");
    	}
    	else
    	{
    		int azo = 0;
    		for(String strings : baneedPlayers)
    		{
    			if(strings.equalsIgnoreCase(args[0]))
    			{
    				baneedPlayers.remove(args[0]);
    				getConfig().set("playersBanned", baneedPlayers);
    				saveConfig();
    				p.sendMessage(ChatColor.RED + "The player has been unbanned!");
    				azo = 1;
    				return false;
    			}
    		}
    		
    		if(azo == 0)
    		{
    			p.sendMessage(ChatColor.RED + "The player is not banned!");
    			return false;
    		}
    	}
    }
    return false;
  }
}
