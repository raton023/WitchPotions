package com.craftilandia.witchpotions;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults();
		getConfig().options().copyHeader();
		if(getConfig().getString("potionmsg") == null){
			getConfig().set("potionmsg", "&aYou just mix a new potion");
		}
		if(getConfig().getString("potiontype") == null){
			getConfig().set("potiontype", "splash");
		}
		if(getConfig().getString("potionname") == null){
			getConfig().set("potionname", "&7Witch Potions");
		}
		saveConfig();
	}
	 List<String> lore = new ArrayList<String>();

	ArrayList<String> potions = new ArrayList<String>();
	ArrayList<String> potions2 = new ArrayList<String>();
	@SuppressWarnings("deprecation")
	@EventHandler
	public void clickpotion(PlayerInteractEvent e){
	    Player p = e.getPlayer();
	    if(p.hasPermission("witchpotions.create")){
	    if (e.getAction() == Action.LEFT_CLICK_BLOCK){
	    	 Block b = e.getClickedBlock();
	    	 if(b.getType().equals(Material.CAULDRON) && p.getItemInHand().getType().equals(Material.POTION)) {
	    		 int a = b.getData();
		    		 if(a <= 3){a++;
		    		 b.setData((byte) a);
		    		 potions.add(p.getItemInHand().getData().toString().replace("POTION(", "").replace(")", ""));
		    		 potions2.add(p.getItemInHand().getItemMeta().toString());
		    		 p.setItemInHand(null);}
		    		 if(potions.size() == 3){
		    			 String getmsg = getConfig().getString("potionmsg");
		    				String msg = ChatColor.translateAlternateColorCodes('&', getmsg);
		    			 p.sendMessage(msg);
		    			 ItemStack potion = null;
		    			 if(getConfig().getString("potiontype").equalsIgnoreCase("potion")){
		    				 potion = new ItemStack(Material.POTION, 1);
		    				 
		    			 }
		    			 if(getConfig().getString("potiontype").equalsIgnoreCase("splash")){
		    				 potion = new ItemStack(Material.POTION, 1, (short)16384);	 
		    			 }
		    			 PotionMeta meta = (PotionMeta) potion.getItemMeta();	
				    		List<String> lore = new ArrayList<String>();

				    		for(int num = 0; num <= 2 ; num++){
		    			
		    			 if(potions.get(num).contentEquals("35")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0), true);
				    		 int a23 =  lore.indexOf("Fire Resistence 8:00");
				    		 if(a23 == -1){lore.add("Fire Resistence 3:00");}
				    		 }
		    			 if(potions.get(num).contentEquals("67")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9600, 0), true);		
				    		 int a23 =  lore.indexOf("Fire Resistence 3:00");
				    		 if(a23 != -1){lore.remove(a23);}
				    			 lore.add("Fire Resistence 8:00");
				    			 
				    			 }
		    			 if(potions.get(num).contentEquals("76")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 0), true);
				    		 int a23 =  lore.indexOf("HARM II");if(a23 == -1){lore.add("HARM I");}
		    			 }
		    			 if(potions.get(num).contentEquals("44")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 1), true);
				    		 int a23 =  lore.indexOf("HARM I");if(a23 != -1){lore.remove(a23);}lore.add("HARM II");
		    			 }
		    			 if(potions.get(num).contentEquals("69")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 0), true);
				    		 int a23 =  lore.indexOf("HEAL II");if(a23 == -1){lore.add("HEAL I");}
		    			 }
		    			 if(potions.get(num).contentEquals("37")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 1), true);
				    		 int a23 =  lore.indexOf("HEAL I");if(a23 != -1){lore.remove(a23);}lore.add("HEAL II");
				    		 }
		    			 if(potions.get(num).contentEquals("9")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("41")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1800, 1), true);
				    		 }
		    			 if(potions.get(num).contentEquals("73")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9600, 0), true);
				    		}
		    			 if(potions.get(num).contentEquals("46")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3600, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("78")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9600, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("38")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 3600, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("70")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9600, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("4")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 900, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("36")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 440, 1), true);
				    		 }
		    			 if(potions.get(num).contentEquals("68")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 2400, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("1")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 900, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("33")){
		    				 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 440, 1), true);
				    		 }
		    			 if(potions.get(num).contentEquals("65")){
		    				 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 2400, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("11")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 3600, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("43")){
		    				 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 1800, 1), true);
				    		 }
		    			 if(potions.get(num).contentEquals("75")){
		    				 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 9600, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("42")){
		    				 meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 1800, 0), true);
				    		}
		    			 if(potions.get(num).contentEquals("74")){
		    				 meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 4800, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("2")){ 
		    				 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("34")){
		    				 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 1800, 1), true);
				    		 }
		    			 if(potions.get(num).contentEquals("66")){
		    				 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 9600, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("45")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 3600, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("77")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 9600, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("40")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1800, 0), true);
				    		 }
		    			 if(potions.get(num).contentEquals("72")){
				    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 4800, 0), true);
				    		 }}


		    		
		    		for(int num = 0; num <= 2 ; num++){
		    			if(potions.get(num).contentEquals("0")){
		    		if(potions2.get(num).contains("FIRE_RESISTANCE")){
   					 if(potions2.get(num).contains("3600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0), true);
   						lore.add(ChatColor.GRAY + "Fire Resistance 3:00");
   					 }
   					 if(potions2.get(num).contains("9600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9600, 0), true);
   						lore.add(ChatColor.GRAY + "Fire Resistance 8:00");
   					 }
		    	}
   				 if(potions2.get(num).contains("HARM")){
   					 if(potions2.get(num).contains("x1")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 1), true);
   						lore.add(ChatColor.GRAY + "Harm II");
   					 }
   					 if(potions2.get(num).contains("x0")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 0), true);
   						lore.add(ChatColor.GRAY + "Harm");
   					 }
   				 }
   				 if(potions2.get(num).contains("HEAL")){
   					 if(potions2.get(num).contains("x1")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 1), true);
   						lore.add(ChatColor.GRAY + "Heal II");
   					 }
   					 if(potions2.get(num).contains("x0")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 0), true);
   						lore.add(ChatColor.GRAY + "Heal");
   					 }
   				 }
   				 if(potions2.get(num).contains("INCREASE_DAMAGE")){
   					 if(potions2.get(num).contains("3600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 0), true);
   						lore.add(ChatColor.GRAY + "Strength 3:00");
   					 }
   					 if(potions2.get(num).contains("9600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9600, 0), true);
   						lore.add(ChatColor.GRAY + "Strength 8:00");
   					 }
   					 if(potions2.get(num).contains("1800")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1800, 1), true);
   						lore.add(ChatColor.GRAY + "Strength II 1:30");
   					 }
   				 }
   				 if(potions2.get(num).contains("INVISIBILITY")){
   					 if(potions2.get(num).contains("3600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3600, 0), true);
   						lore.add(ChatColor.GRAY + "Invisibility 3:00");
   					 }
   					 if(potions2.get(num).contains("9600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9600, 0), true);
    						lore.add(ChatColor.GRAY + "Invisibility 8:00");
   					 }
   				 }
   				 if(potions2.get(num).contains("NIGHT_VISION")){
   					 if(potions2.get(num).contains("3600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 3600, 0), true);
    						lore.add(ChatColor.GRAY + "Night Vision 3:00");
   					 }
   					 if(potions2.get(num).contains("9600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9600, 0), true);
 						lore.add(ChatColor.GRAY + "Night Vision 8:00");
   					 }
   				 }
   				 if(potions2.get(num).contains("POISON")){
   					 if(potions2.get(num).contains("440")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 440, 1), true);
   						lore.add(ChatColor.GRAY + "Poison II 0:22");
   					 }
   					 if(potions2.get(num).contains("900")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 900, 0), true);
   						lore.add(ChatColor.GRAY + "Poison 0:45");
   					 }
   					 if(potions2.get(num).contains("2400")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 2400, 0), true);
   						lore.add(ChatColor.GRAY + "Poison 2:00");
   					 }
   				 }
   				 if(potions2.get(num).contains("REGENERATION")){
   					 if(potions2.get(num).contains("440")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 440, 1), true);
   						lore.add(ChatColor.GRAY + "Regeneration II 0:22");
   					 }
   					 if(potions2.get(num).contains("900")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 900, 0), true);
    						lore.add(ChatColor.GRAY + "Regeneration 0:45");
   					 }
   					 if(potions2.get(num).contains("2400")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 2400, 0), true);
 						lore.add(ChatColor.GRAY + "Regeneration 2:00");
   					 }
   				 }
   				 
   				if(potions2.get(num).contains("JUMP")){
   					if(potions2.get(num).contains("3600")){
   		    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 3600, 0), true);
						lore.add(ChatColor.GRAY + "Jump 3:00");

   		    		 }
   					if(potions2.get(num).contains("1800")){
   		    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 1800, 1), true);
						lore.add(ChatColor.GRAY + "Jump II 1:30");

   		    		 }
   					if(potions2.get(num).contains("9600")){
   		    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 9600, 0), true);
   		    		lore.add(ChatColor.GRAY + "Jump 8:00");
   				}
   				}
   				 if(potions2.get(num).contains("SLOW")){
   					 if(potions2.get(num).contains("4800")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 4800, 0), true);
   						lore.add(ChatColor.GRAY + "Slowness 4:00");
   					 }
   					 if(potions2.get(num).contains("1800")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 1800, 0), true);
    						lore.add(ChatColor.GRAY + "Slowness 1:30");

   					 }
   				 }
   				 if(potions2.get(num).contains("SPEED")){
   					 if(potions2.get(num).contains("3600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 0), true);
   						lore.add(ChatColor.GRAY + "Speed 3:00");
   					 }
   					 if(potions2.get(num).contains("9600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 9600, 0), true);
   						lore.add(ChatColor.GRAY + "Speed 8:00");
   					 }
   					 if(potions2.get(num).contains("1800")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 1800, 1), true);
   						lore.add(ChatColor.GRAY + "Speed II 1:30");
   					 }
   				 }
   				 if(potions2.get(num).contains("WATER_BREATHING")){
   					 if(potions2.get(num).contains("3600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 3600, 0), true);
 						lore.add(ChatColor.GRAY + "Water Breathing 3:00");
   					 }
   					 if(potions2.get(num).contains("9600")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 9600, 0), true);
    						lore.add(ChatColor.GRAY + "Water Breathing 8:00");
   					 }
   				 }
   				 if(potions2.get(num).contains("WEAKNESS")){
   					 if(potions2.get(num).contains("1800")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1800, 0), true);
  						lore.add(ChatColor.GRAY + "Weakness 1:30");
   					 }
   					 if(potions2.get(num).contains("4800")){
   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 4800, 0), true);
   						lore.add(ChatColor.GRAY + "Weakness 4:00");
   					 }
   				 }
		    		}}
		    		
		    		
	    			 String getname = getConfig().getString("potionname");
	    				String name = ChatColor.translateAlternateColorCodes('&', getname);
		    		meta.setDisplayName(name);
		    		
		    		if(getConfig().getString("potiontype").equalsIgnoreCase("potion")){
		    			meta.setLore(lore);
		    		}
		    		potion.setItemMeta(meta);
		    		p.getInventory().addItem(potion);
		    		potions.clear();
		    		potions2.clear();
		    		lore.clear();
		    		a=0; 
		    		b.setData((byte) 0);}}}}}}
