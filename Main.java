package com.craftilandia.witchpotions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
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

import net.minecraft.server.v1_9_R1.NBTTagCompound;


public class Main extends JavaPlugin implements Listener {
	
	   
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults();
		getConfig().options().copyHeader();
		if (getConfig().getString("potionmsg") == null) {
			getConfig().set("potionmsg", "&aYou just mix a new potion");
		}
		if (getConfig().getString("potionname") == null) {
			getConfig().set("potionname", "&fWitch Potions");
		}
		
		saveConfig();
	}


	List<String> lore = new ArrayList<String>();
	ArrayList<String> potions = new ArrayList<String>();
	ArrayList<String> mixedpotions = new ArrayList<String>();
	int lapotaes =1;


	@SuppressWarnings("deprecation")
	@EventHandler
	public void clickpotion(PlayerInteractEvent e) {
Player p = e.getPlayer();
if (p.hasPermission("witchpotions.create")) {
if (e.getAction() == Action.LEFT_CLICK_BLOCK|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
Block b = e.getClickedBlock();
if ((b.getType().equals(Material.CAULDRON))&& (p.getInventory().getItemInMainHand().getType().equals(Material.POTION)||p.getInventory().getItemInMainHand().getType().equals(Material.SPLASH_POTION)||p.getInventory().getItemInMainHand().getType().equals(Material.LINGERING_POTION))) {
int a = b.getData();
if (a <= 3) {
a++;
b.setData((byte) a);
net.minecraft.server.v1_9_R1.ItemStack stack = CraftItemStack.asNMSCopy(p.getInventory().getItemInMainHand());
NBTTagCompound tagCompound = stack.getTag();
String tag = tagCompound.getString("Potion").replace("minecraft:", "");
potions.add(""+tag.toString());

PotionMeta metapo = (PotionMeta) p.getInventory().getItemInMainHand().getItemMeta();
mixedpotions.add(metapo.getCustomEffects().toString()); //mix of custom potions

if(p.getInventory().getItemInMainHand().getType().equals(Material.SPLASH_POTION)&&lapotaes!=3){
	lapotaes = 2;
}
if(p.getInventory().getItemInMainHand().getType().equals(Material.LINGERING_POTION)){
	lapotaes = 3;
}

p.getInventory().setItemInMainHand(null);
}
if (potions.size() == 3) {
String getmsg = getConfig().getString("potionmsg");
String msg = ChatColor.translateAlternateColorCodes('&', getmsg);
p.sendMessage(msg);
ItemStack potion = new ItemStack(Material.POTION, 1);
PotionMeta meta = (PotionMeta) potion.getItemMeta();
if(lapotaes==2){
potion = new ItemStack(Material.SPLASH_POTION, 1);
meta = (PotionMeta) potion.getItemMeta();}
if(lapotaes==3){
potion = new ItemStack(Material.LINGERING_POTION, 1);
meta = (PotionMeta) potion.getItemMeta();}
for (int num = 0; num <= 2; num++) {

	
if (potions.get(num).equals("fire_resistance")||mixedpotions.toString().contains("FIRE_RESISTANCE:(3600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3600, 0), true);
}
if (potions.get(num).contentEquals("long_fire_resistance")||mixedpotions.toString().contains("FIRE_RESISTANCE:(9600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9600,0), true);
}
if (potions.get(num).equals("luck")||mixedpotions.toString().contains("LUCK:(6000t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 6000, 0), true);
}
if (potions.get(num).contentEquals("harming")||mixedpotions.toString().contains("HARM:(20t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 0), true);
}
if (potions.get(num).contentEquals("strong_harming")||mixedpotions.toString().contains("HARM:(20t-x1)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 20, 1), true);
}
if (potions.get(num).contentEquals("healing")||mixedpotions.toString().contains("HEAL:(20t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 0), true);
}
if (potions.get(num).contentEquals("strong_healing")||mixedpotions.toString().contains("HEAL:(20t-x1)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 20, 1), true);
}
if (potions.get(num).contentEquals("strength")||mixedpotions.toString().contains("INCREASE_DAMAGE:(3600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600,0), true);
}
if(potions.get(num).contentEquals("strong_strength")||mixedpotions.toString().contains("INCREASE_DAMAGE:(1800t-x1)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1800,1), true);
}
if (potions.get(num).contentEquals("long_strength")||mixedpotions.toString().contains("INCREASE_DAMAGE:(9600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9600,0), true);
}
if (potions.get(num).contentEquals("invisibility")||mixedpotions.toString().contains("INVISIBILITY:(3600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY,3600, 0), true);
}
if (potions.get(num).contentEquals("long_invisibility")||mixedpotions.toString().contains("INVISIBILITY:(9600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY,9600, 0), true);
}
if (potions.get(num).contentEquals("night_vision")||mixedpotions.toString().contains("NIGHT_VISION:(3600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,3600, 0), true);
}
if (potions.get(num).contentEquals("long_night_vision")||mixedpotions.toString().contains("NIGHT_VISION:(9600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,9600, 0), true);
}
if (potions.get(num).contentEquals("poison")||mixedpotions.toString().contains("POISON:(900t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 900, 0), true);
}
if (potions.get(num).contentEquals("strong_poison")||mixedpotions.toString().contains("POISON:(440t-x1)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 440, 1), true);
}
if (potions.get(num).contentEquals("long_poison")||mixedpotions.toString().contains("POISON:(1800t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 1800, 0), true);
}
if (potions.get(num).contentEquals("regeneration")||mixedpotions.toString().contains("REGENERATION:(900t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 900, 0),true);
}
if (potions.get(num).contentEquals("strong_regeneration")||mixedpotions.toString().contains("REGENERATION:(440t-x1)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 440, 1),true);
}
if (potions.get(num).contentEquals("long_regeneration")||mixedpotions.toString().contains("REGENERATION:(1800t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION,1800, 0), true);
}
if (potions.get(num).contentEquals("leaping")||mixedpotions.toString().contains("JUMP:(3600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 3600, 0), true);
}
if (potions.get(num).contentEquals("strong_leaping")||mixedpotions.toString().contains("JUMP:(1800t-x1)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 1800, 1), true);
}
if (potions.get(num).contentEquals("long_leaping")||mixedpotions.toString().contains("JUMP:(9600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 9600, 0), true);
}
if (potions.get(num).contentEquals("slowness")||mixedpotions.toString().contains("SLOW:(1800t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 1800, 0), true);
}
if (potions.get(num).contentEquals("long_slowness")||mixedpotions.toString().contains("SLOW:(4800t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 4800, 0), true);
}
if (potions.get(num).contentEquals("swiftness")||mixedpotions.toString().contains("SPEED:(3600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 3600, 0), true);
}
if (potions.get(num).contentEquals("strong_swiftness")||mixedpotions.toString().contains("SPEED:(1800t-x1)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 1800, 1), true);
}
if (potions.get(num).contentEquals("long_swiftness")||mixedpotions.toString().contains("SPEED:(9600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 9600, 0), true);
}
if (potions.get(num).contentEquals("water_breathing")||mixedpotions.toString().contains("WATER_BREATHING:(3600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 3600,0), true);
}
if (potions.get(num).contentEquals("long_water_breathing")||mixedpotions.toString().contains("WATER_BREATHING:(9600t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 9600,0), true);
}
if (potions.get(num).contentEquals("weakness")||mixedpotions.toString().contains("WEAKNESS:(1800t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1800, 0),true);
}
if (potions.get(num).contentEquals("long_weakness")||mixedpotions.toString().contains("WEAKNESS:(4800t-x0)")) {
meta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 4800, 0),true);
}
}
String getname = getConfig().getString("potionname");
String name = ChatColor.translateAlternateColorCodes('&', getname);
meta.setDisplayName(name);
meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
for(int hu=0;hu<meta.getCustomEffects().size();hu++){
lore.add(""+meta.getCustomEffects().get(hu));
}
meta.setLore(lore);
potion.setItemMeta(meta);
p.getInventory().addItem(potion);
potions.clear();
mixedpotions.clear();
lore.clear();
a = 0;
b.setData((byte) 0);
return;
}
}
}
}
}
}
