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
import org.bukkit.inventory.ItemFlag;
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
		}if(getConfig().getString("potionname") == null){
			getConfig().set("potionname", "&fWitch Potions");
		}saveConfig();
	}
	List<String> lore = new ArrayList<String>();
	ArrayList<String> potions = new ArrayList<String>();
	ArrayList<String> potions2 = new ArrayList<String>();
	@SuppressWarnings("deprecation")
	@EventHandler
	public void clickpotion(PlayerInteractEvent e){
	    Player p = e.getPlayer();
	    if(p.hasPermission("witchpotions.create")){
	    if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK){
	    	 Block b = e.getClickedBlock();
	    	 if(b.getType().equals(Material.CAULDRON) && p.getItemInHand().getType().equals(Material.POTION)) {
	    		 int a = b.getData();
	    		 if(a <= 3){a++;
		    		 b.setData((byte) a);
		    		 //potions.add(p.getItemInHand().getData().toString().replace("POTION(", "").replace(")", ""));
		    		 potions.add(String.valueOf(p.getItemInHand().getDurability()));//mix of regular potions
		    		 potions2.add(p.getItemInHand().getItemMeta().toString());//mix of custom potions
		    		 p.sendMessage(p.getItemInHand().getItemMeta().toString());
		    		 p.setItemInHand(null);
		    		 }

		    		 if(potions.size() == 3){
		    			 String getmsg = getConfig().getString("potionmsg");	
		    			 String msg = ChatColor.translateAlternateColorCodes('&', getmsg);
		    			 p.sendMessage(msg);
		    			 ItemStack potion = new ItemStack(Material.POTION, 1);
		    			 //if(p.getInventory().contains(Material.SULPHUR)){potion = new ItemStack(Material.POTION, 1, (short)16384);}
		    			 //if(!p.getInventory().contains(Material.SULPHUR)){potion = new ItemStack(Material.POTION, 1);}
		    			 PotionMeta meta = (PotionMeta) potion.getItemMeta();	
		    			 //mix of regular ones
		    			 for(int num = 0; num <= 2 ; num++){
		    				 if(potions.get(num).contentEquals("8227")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Fire Resistence 8:00") == -1 && lore.indexOf(ChatColor.GRAY + "Fire Resistence 2:15 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Fire Resistence 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Fire Resistence 3:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("8259")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9600, 0), true);		
					    		 if(lore.indexOf(ChatColor.GRAY + "Fire Resistence 2:15 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Fire Resistence 6:00 splash") == -1){lore.add(ChatColor.GRAY + "Fire Resistence 8:00");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Fire Resistence 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Fire Resistence 3:00"));}
					    		 }
		    				 if(potions.get(num).contentEquals("16419")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 2700, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Fire Resistence 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Fire Resistence 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Fire Resistence 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Fire Resistence 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Fire Resistence 6:00 splash") == -1){lore.add(ChatColor.GRAY + "Fire Resistence 2:15 splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16451")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 7200, 0), true);
					    		 lore.add("Fire Resistence 6:00 splash");
					    		 if(lore.indexOf(ChatColor.GRAY + "Fire Resistence 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Fire Resistence 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Fire Resistence 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Fire Resistence 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Fire Resistence 2:15 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Fire Resistence 2:15 splash"));}
					    		 }
		    				 
		    				 if(potions.get(num).contentEquals("8268")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "HARM II") == -1 && lore.indexOf(ChatColor.GRAY + "HARM I splash") == -1 && lore.indexOf(ChatColor.GRAY + "HARM II splash") == -1){
					    			 lore.add(ChatColor.GRAY + "HARM I");}
					    		 }
		    				 if(potions.get(num).contentEquals("8236")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 1), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "HARM I splash") == -1 && lore.indexOf(ChatColor.GRAY + "HARM II splash") == -1){lore.add(ChatColor.GRAY + "HARM II");}
					    		 if(lore.indexOf(ChatColor.GRAY + "HARM I") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HARM I"));}
					    		 }
		    				 if(potions.get(num).contentEquals("16460")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "HARM I") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HARM I"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "HARM II") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HARM II"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "HARM II splash") == -1){lore.add(ChatColor.GRAY + "HARM I splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16428")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 1), true);
					    		 lore.add(ChatColor.GRAY + "HARM II splash");
					    		 if(lore.indexOf(ChatColor.GRAY + "HARM I") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HARM I"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "HARM II") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HARM II"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "HARM I splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HARM I splash"));}
					    		 }
		    				 
		    				 if(potions.get(num).contentEquals("8261")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "HEAL II") == -1 && lore.indexOf(ChatColor.GRAY + "HEAL I splash") == -1 && lore.indexOf(ChatColor.GRAY + "HEAL II splash") == -1){
					    			 lore.add(ChatColor.GRAY + "HEAL I");}
					    		 }
		    				 if(potions.get(num).contentEquals("8229")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 1), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "HEAL I splash") == -1 && lore.indexOf(ChatColor.GRAY + "HEAL II splash") == -1){lore.add(ChatColor.GRAY + "HEAL II");}
					    		 if(lore.indexOf(ChatColor.GRAY + "HEAL I") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HEAL I"));}
					    		 }
		    				 if(potions.get(num).contentEquals("16453")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "HEAL I") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HEAL I"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "HEAL II") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HEAL II"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "HEAL II splash") == -1){lore.add(ChatColor.GRAY + "HEAL I splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16421")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 1), true);
					    		 lore.add("HEAL II splash");
					    		 if(lore.indexOf(ChatColor.GRAY + "HEAL I") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HEAL I"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "HEAL II") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HEAL II"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "HEAL I splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "HEAL I splash"));}
					    		 }
		    				 
		    				 if(potions.get(num).contentEquals("8201")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600, 0), true);
					    		 if(
					    				 lore.indexOf(ChatColor.GRAY + "Strength II 1:30") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Strength 8:00") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Strength 2:15 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Strength II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Strength 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Strength 3:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("8233")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1800, 1), true);
					    		 if(
					    				 lore.indexOf(ChatColor.GRAY + "Strength 8:00") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Strength 2:15 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Strength II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Strength 6:00 splash") == -1
					    				 ){lore.add(ChatColor.GRAY + "Strength II 1:30");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength 3:00"));}
					    		 }
		    				 if(potions.get(num).contentEquals("8265")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9600, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength II 1:30"));}
					    		 if( 
					    				 lore.indexOf(ChatColor.GRAY + "Strength 2:15 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Strength II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Strength 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Strength 8:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("16393")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2700, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength II 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Strength 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Strength 2:15 splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16425")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1340, 1), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength 6:00 splash") == -1){lore.add(ChatColor.GRAY + "Strength II 1:07 splash");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength II 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength 2:15 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength 2:15 splash"));}
					    		 
					    		 }
		    				 if(potions.get(num).contentEquals("16457")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 7200, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength II 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength 2:15 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength 2:15 splash"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Strength II 1:07 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Strength II 1:07 splash"));}
					    		 lore.add(ChatColor.GRAY + "Strength 6:00 splash");
					    		 }
		    				 
		    				 if(potions.get(num).contentEquals("8238")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3600, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Invisibility 8:00") == -1 && lore.indexOf(ChatColor.GRAY + "Invisibility 2:15 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Invisibility 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Invisibility 3:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("8270")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9600, 0), true);		
					    		 if(lore.indexOf(ChatColor.GRAY + "Invisibility 2:15 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Invisibility 6:00 splash") == -1){lore.add(ChatColor.GRAY + "Invisibility 8:00");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Invisibility 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Invisibility 3:00"));}
					    		 }
		    				 if(potions.get(num).contentEquals("16430")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2700, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Invisibility 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Invisibility 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Invisibility 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Invisibility 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Invisibility 6:00 splash") == -1){lore.add(ChatColor.GRAY + "Invisibility 2:15 splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16462")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 7200, 0), true);
					    		 lore.add(ChatColor.GRAY + "Invisibility 6:00 splash");
					    		 if(lore.indexOf(ChatColor.GRAY + "Invisibility 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Invisibility 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Invisibility 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Invisibility 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Invisibility 2:15 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Invisibility 2:15 splash"));}
					    		 }
		    				 
		    				 if(potions.get(num).contentEquals("8230")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 3600, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Night Vision 8:00") == -1 && lore.indexOf(ChatColor.GRAY + "Night Vision 2:15 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Night Vision 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Night Vision 3:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("8262")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9600, 0), true);		
					    		 if(lore.indexOf(ChatColor.GRAY + "Night Vision 2:15 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Night Vision 6:00 splash") == -1){lore.add(ChatColor.GRAY + "Night Vision 8:00");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Night Vision 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Night Vision 3:00"));}
					    		 }
		    				 if(potions.get(num).contentEquals("16422")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2700, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Night Vision 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Night Vision 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Night Vision 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Night Vision 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Night Vision 6:00 splash") == -1){lore.add(ChatColor.GRAY + "Night Vision 2:15 splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16454")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 7200, 0), true);
					    		 lore.add(ChatColor.GRAY + "Night Vision 6:00 splash");
					    		 if(lore.indexOf(ChatColor.GRAY + "Night Vision 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Night Vision 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Night Vision 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Night Vision 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Night Vision 2:15 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Night Vision 2:15 splash"));}
					    		 }
		    				 
		    				 if(potions.get(num).contentEquals("8196")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 900, 0), true);
					    		 if(
					    				 lore.indexOf(ChatColor.GRAY + "Poison II 0:22") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Poison 2:00") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Poison 0:33 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Poison II 0:16 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Poison 1:30 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Poison 0:45");}
					    		 }
		    				 if(potions.get(num).contentEquals("8228")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 440, 1), true);
					    		 if(
					    				 lore.indexOf(ChatColor.GRAY + "Poison 2:00") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Poison 0:33 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Poison II 0:16 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Poison 1:30 splash") == -1
					    				 ){lore.add(ChatColor.GRAY + "Poison II 0:22");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison 0:45") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison 0:45"));}
					    		 }
		    				 if(potions.get(num).contentEquals("8260")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 2400, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison 0:45") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison 0:45"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison II 0:22") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison II 0:22"));}
					    		 if( 
					    				 lore.indexOf(ChatColor.GRAY + "Poison 0:33 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Poison II 0:16 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Poison 1:30 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Poison 2:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("16388")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 660, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison 0:45") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison 0:45"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison II 0:22") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison II 0:22"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison 2:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison 2:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison II 0:16 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Poison 1:30 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Poison 0:33 splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16420")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 320, 1), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison 1:30 splash") == -1){lore.add(ChatColor.GRAY + "Poison II 0:16 splash");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison 0:45") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison 0:45"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison II 0:22") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison II 0:22"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison 2:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison 2:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison 0:33 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison 0:33 splash"));}
					    		 
					    		 }
		    				 if(potions.get(num).contentEquals("16452")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 1800, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison 0:45") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison 0:45"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison II 0:22") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison II 0:22"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison 2:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison 2:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison 0:33 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison 0:33 splash"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Poison II 0:16 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Poison II 0:16 splash"));}
					    		 lore.add(ChatColor.GRAY + "Poison 1:30 splash");
					    		 }
		    				 
		    				 if(potions.get(num).contentEquals("8193")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 900, 0), true);
					    		 if(
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration II 0:22") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration 2:00") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration 0:33 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration II 0:16 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration 1:30 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Regeneration 0:45");}
					    		 }
		    				 if(potions.get(num).contentEquals("8225")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 440, 1), true);
					    		 if(
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration 2:00") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration 0:33 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration II 0:16 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration 1:30 splash") == -1
					    				 ){lore.add(ChatColor.GRAY + "Regeneration II 0:22");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration 0:45") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration 0:45"));}
					    		 }
		    				 if(potions.get(num).contentEquals("8257")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 2400, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration 0:45") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration 0:45"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration II 0:22") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration II 0:22"));}
					    		 if( 
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration 0:33 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration II 0:16 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration 1:30 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Regeneration 2:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("16385")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 660, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration 0:45") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration 0:45"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration II 0:22") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration II 0:22"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration 2:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration 2:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration II 0:16 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Regeneration 1:30 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Regeneration 0:33 splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16417")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 320, 1), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration 1:30 splash") == -1){lore.add(ChatColor.GRAY + "Regeneration II 0:16 splash");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration 0:45") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration 0:45"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration II 0:22") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration II 0:22"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration 2:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration 2:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration 0:33 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration 0:33 splash"));}
					    		 
					    		 }
		    				 if(potions.get(num).contentEquals("16449")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 1800, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration 0:45") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration 0:45"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration II 0:22") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration II 0:22"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration 2:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration 2:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration 0:33 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration 0:33 splash"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Regeneration II 0:16 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Regeneration II 0:16 splash"));}
					    		 lore.add(ChatColor.GRAY + "Regeneration 1:30 splash");
					    		 }

		    				 if(potions.get(num).contentEquals("8203")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 3600, 0), true);
					    		 if(
					    				 lore.indexOf(ChatColor.GRAY + "Jump II 1:30") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Jump 8:00") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Jump 2:15 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Jump II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Jump 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Jump 3:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("8235")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 1800, 1), true);
					    		 if(
					    				 lore.indexOf(ChatColor.GRAY + "Jump 8:00") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Jump 2:15 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Jump II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Jump 6:00 splash") == -1
					    				 ){lore.add(ChatColor.GRAY + "Jump II 1:30");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump 3:00"));}
					    		 }
		    				 if(potions.get(num).contentEquals("8267")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 9600, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump II 1:30"));}
					    		 if( 
					    				 lore.indexOf(ChatColor.GRAY + "Jump 2:15 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Jump II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Jump 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Jump 8:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("16395")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 2700, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump II 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Jump 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Jump 2:15 splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16427")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 1340, 1), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump 6:00 splash") == -1){lore.add(ChatColor.GRAY + "Jump II 1:07 splash");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump II 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump 2:15 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump 2:15 splash"));}
					    		 
					    		 }
		    				 if(potions.get(num).contentEquals("16459")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 7200, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump II 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump 2:15 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump 2:15 splash"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Jump II 1:07 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Jump II 1:07 splash"));}
					    		 lore.add(ChatColor.GRAY + "Jump 6:00 splash");
					    		 }
		    				 
		    				 if(potions.get(num).contentEquals("8234")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 1800, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Slowness 4:00") == -1 && lore.indexOf(ChatColor.GRAY + "Slowness 1:07 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Slowness 3:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Slowness 1:30");}
					    		 }
		    				 if(potions.get(num).contentEquals("8266")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 4800, 0), true);		
					    		 if(lore.indexOf(ChatColor.GRAY + "Slowness 1:07 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Slowness 3:00 splash") == -1){lore.add(ChatColor.GRAY + "Slowness 4:00");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Slowness 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Slowness 1:30"));}
					    		 }
		    				 if(potions.get(num).contentEquals("16426")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 1340, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Slowness 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Slowness 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Slowness 4:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Slowness 4:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Slowness 3:00 splash") == -1){lore.add(ChatColor.GRAY + "Slowness 1:07 splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16458")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 3600, 0), true);
					    		 lore.add(ChatColor.GRAY + "Slowness 3:00 splash");
					    		 if(lore.indexOf(ChatColor.GRAY + "Slowness 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Slowness 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Slowness 4:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Slowness 4:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Slowness 1:07 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Slowness 1:07 splash"));}
					    		 }

		    				 if(potions.get(num).contentEquals("8194")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 0), true);
					    		 if(
					    				 lore.indexOf(ChatColor.GRAY + "Speed II 1:30") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Speed 8:00") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Speed 2:15 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Speed II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Speed 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Speed 3:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("8226")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 1800, 1), true);
					    		 if(
					    				 lore.indexOf(ChatColor.GRAY + "Speed 8:00") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Speed 2:15 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Speed II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Speed 6:00 splash") == -1
					    				 ){lore.add(ChatColor.GRAY + "Speed II 1:30");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed 3:00"));}
					    		 }
		    				 if(potions.get(num).contentEquals("8258")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 9600, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed II 1:30"));}
					    		 if( 
					    				 lore.indexOf(ChatColor.GRAY + "Speed 2:15 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Speed II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Speed 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Speed 8:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("16386")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 2700, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed II 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed II 1:07 splash") == -1 && 
					    				 lore.indexOf(ChatColor.GRAY + "Speed 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Speed 2:15 splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16418")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 1340, 1), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed 6:00 splash") == -1){lore.add(ChatColor.GRAY + "Speed II 1:07 splash");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed II 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed 2:15 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed 2:15 splash"));}
					    		 
					    		 }
		    				 if(potions.get(num).contentEquals("16450")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 7200, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed II 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed II 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed 2:15 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed 2:15 splash"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Speed II 1:07 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Speed II 1:07 splash"));}
					    		 lore.add(ChatColor.GRAY + "Speed 6:00 splash");
					    		 }
		    				 

		    				 if(potions.get(num).contentEquals("8237")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 3600, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Water Breathing 8:00") == -1 && lore.indexOf(ChatColor.GRAY + "Water Breathing 2:15 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Water Breathing 6:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Water Breathing 3:00");}
					    		 }
		    				 if(potions.get(num).contentEquals("8269")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 9600, 0), true);		
					    		 if(lore.indexOf(ChatColor.GRAY + "Water Breathing 2:15 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Water Breathing 6:00 splash") == -1){lore.add("Water Breathing 8:00");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Water Breathing 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Water Breathing 3:00"));}
					    		 }
		    				 if(potions.get(num).contentEquals("16429")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2700, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Water Breathing 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Water Breathing 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Water Breathing 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Water Breathing 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Water Breathing 6:00 splash") == -1){lore.add(ChatColor.GRAY + "Water Breathing 2:15 splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16461")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 7200, 0), true);
					    		 lore.add(ChatColor.GRAY + "Water Breathing 6:00 splash");
					    		 if(lore.indexOf(ChatColor.GRAY + "Water Breathing 3:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Water Breathing 3:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Water Breathing 8:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Water Breathing 8:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Water Breathing 2:15 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Water Breathing 2:15 splash"));}
					    		 }

		    				 if(potions.get(num).contentEquals("8232")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1800, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Weakness 4:00") == -1 && lore.indexOf(ChatColor.GRAY + "Weakness 1:07 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Weakness 3:00 splash") == -1){
					    			 lore.add(ChatColor.GRAY + "Weakness 1:30");}
					    		 }
		    				 if(potions.get(num).contentEquals("8264")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 4800, 0), true);		
					    		 if(lore.indexOf(ChatColor.GRAY + "Weakness 1:07 splash") == -1 && lore.indexOf(ChatColor.GRAY + "Weakness 3:00 splash") == -1){lore.add(ChatColor.GRAY + "Weakness 4:00");}
					    		 if(lore.indexOf(ChatColor.GRAY + "Weakness 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Weakness 1:30"));}
					    		 }
		    				 if(potions.get(num).contentEquals("16424")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1340, 0), true);
					    		 if(lore.indexOf(ChatColor.GRAY + "Weakness 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Weakness 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Weakness 4:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Weakness 4:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Weakness 3:00 splash") == -1){lore.add(ChatColor.GRAY + "Weakness 1:07 splash");}
					    		 }
		    				 if(potions.get(num).contentEquals("16456")){
					    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 3600, 0), true);
					    		 lore.add(ChatColor.GRAY + "Weakness 3:00 splash");
					    		 if(lore.indexOf(ChatColor.GRAY + "Weakness 1:30") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Weakness 1:30"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Weakness 4:00") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Weakness 4:00"));}
					    		 if(lore.indexOf(ChatColor.GRAY + "Weakness 1:07 splash") != -1){lore.remove(lore.indexOf(ChatColor.GRAY + "Weakness 1:07 splash"));}
					    		 }
		    				 //comienzo de mezcla de pociones mixtas
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
		    	   					if(potions2.get(num).contains("2700")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 2700, 0), true);
		    	   						lore.add(ChatColor.GRAY + "Fire Resistance 2:15 splash");
		    	   					 }
		    	   					if(potions2.get(num).contains("7200")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 7200, 0), true);
		    	   						lore.add(ChatColor.GRAY + "Fire Resistance 6:00 splash");
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
		    	   					 if(potions2.get(num).contains("2700")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2700, 0), true);
		    	   						lore.add(ChatColor.GRAY + "Strength 2:15 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("7200")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 7200, 0), true);
		    	   						lore.add(ChatColor.GRAY + "Strength 6:00 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("1340")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1345, 1), true);
		    	   						lore.add(ChatColor.GRAY + "Strength II 1:07 splash");
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
		    	   					if(potions2.get(num).contains("2700")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2700, 0), true);
		    	   						lore.add(ChatColor.GRAY + "Invisibility 2:15 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("7200")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 7200, 0), true);
		    	    						lore.add(ChatColor.GRAY + "Invisibility 6:00 splash");
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
		    	   					if(potions2.get(num).contains("2700")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2700, 0), true);
		    	    						lore.add(ChatColor.GRAY + "Night Vision 2:15 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("7200")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 7200, 0), true);
		    	 						lore.add(ChatColor.GRAY + "Night Vision 6:00 splash");
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
		    	   					 if(potions2.get(num).contains("320")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 320, 1), true);
		    	   						lore.add(ChatColor.GRAY + "Poison II 0:16 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("660")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 660, 0), true);
		    	   						lore.add(ChatColor.GRAY + "Poison 0:33 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("1800")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 1800, 0), true);
		    	   						lore.add(ChatColor.GRAY + "Poison 1:30 splash");
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
		    	   					if(potions2.get(num).contains("320")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 320, 1), true);
		    	   						lore.add(ChatColor.GRAY + "Regeneration II 0:16 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("660")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 660, 0), true);
		    	    						lore.add(ChatColor.GRAY + "Regeneration 0:33 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("1800")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 1800, 0), true);
		    	 						lore.add(ChatColor.GRAY + "Regeneration 1:30 splash");
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
			    	   					if(potions2.get(num).contains("2700")){
				    	   		    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 2700, 0), true);
				    							lore.add(ChatColor.GRAY + "Jump 2:15 splash");

				    	   		    		 }
				    	   					if(potions2.get(num).contains("1340")){
				    	   		    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 1340, 1), true);
				    							lore.add(ChatColor.GRAY + "Jump II 1:07 splash");

				    	   		    		 }
				    	   					if(potions2.get(num).contains("7200")){
				    	   		    		 meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 7200, 0), true);
				    	   		    		lore.add(ChatColor.GRAY + "Jump 6:00 splash");
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
		    	   					if(potions2.get(num).contains("3600")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 3600, 0), true);
		    	   						lore.add(ChatColor.GRAY + "Slowness 3:00 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("1340")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 1340, 0), true);
		    	    						lore.add(ChatColor.GRAY + "Slowness 1:07 splash");

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
		    	   					if(potions2.get(num).contains("2700")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 2700, 0), true);
		    	   						lore.add(ChatColor.GRAY + "Speed 2:15 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("7200")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 7200, 0), true);
		    	   						lore.add(ChatColor.GRAY + "Speed 6:00 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("1340")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 1340, 1), true);
		    	   						lore.add(ChatColor.GRAY + "Speed II 1:07 splash");
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
		    	   					if(potions2.get(num).contains("2700")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2700, 0), true);
		    	 						lore.add(ChatColor.GRAY + "Water Breathing 2:15 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("7200")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 7200, 0), true);
		    	    						lore.add(ChatColor.GRAY + "Water Breathing 6:00 splash");
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
		    	   					if(potions2.get(num).contains("1340")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1340, 0), true);
		    	  						lore.add(ChatColor.GRAY + "Weakness 1:07 splash");
		    	   					 }
		    	   					 if(potions2.get(num).contains("3600")){
		    	   						 meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 3600, 0), true);
		    	   						lore.add(ChatColor.GRAY + "Weakness 3:00 splash");
		    	   					 }
		    	   					 }}}
		    		
	    			 String getname = getConfig().getString("potionname");	
	    			 String name = ChatColor.translateAlternateColorCodes('&', getname);
		    		 meta.setDisplayName(name);
		    		 meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		    		 meta.setLore(lore);
		    		 if(meta.getLore().toString().contains("splash")){
		    			 ItemStack potion2 = new ItemStack(Material.POTION, 1, (short)16384);
		    			 meta.setLore(null);
		    			 lore.remove(" splash");
		    			 meta.setLore(lore);
		    			 potion2.setItemMeta(meta);
		    			 p.getInventory().addItem(potion2);
		    			 potions.clear();
			    		 potions2.clear();
			    		 lore.clear();
			    		 a=0; 
			    		 b.setData((byte) 0);
			    		 return;}
		    			 potion.setItemMeta(meta);
		    			 p.getInventory().addItem(potion);
		    			 potions.clear();
			    		 potions2.clear();
			    		 lore.clear();
			    		 a=0; 
			    		 b.setData((byte) 0);
			    		 return;
		    		 }}}}}}
