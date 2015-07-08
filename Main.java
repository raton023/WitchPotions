package com.craftilandia.witchpotions;

import java.util.ArrayList;
import java.util.List;

import net.milkbowl.vault.economy.Economy;

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
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener {
	public static Economy econ = null;

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
		if (getConfig().getBoolean("economy")) {

			if (!setupEconomy()) {
				getServer().getLogger().severe(
						String.format(
								"[%s] - ERROR No Vault dependency found!",
								getDescription().getName()));
				return;
			}

			getConfig().set("potionname", "&fWitch Potions");
		}
		saveConfig();
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	List<String> lore = new ArrayList<String>();
	ArrayList<String> potions = new ArrayList<String>();
	ArrayList<String> potions2 = new ArrayList<String>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void clickpotion(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("witchpotions.create")) {
			if (e.getAction() == Action.LEFT_CLICK_BLOCK
					|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Block b = e.getClickedBlock();
				if (b.getType().equals(Material.CAULDRON)
						&& p.getItemInHand().getType().equals(Material.POTION)) {
					if (getConfig().getBoolean("economy")) {
						if(econ.getBalance(p)<getConfig().getInt("potioncost"));
						p.sendMessage("You need at least "+getConfig().getInt("potioncost")+" coins to mix potions");
						return;
					}
					int a = b.getData();
					if (a <= 3) {
						a++;
						b.setData((byte) a);
						potions.add(String.valueOf(p.getItemInHand()
								.getDurability()));// mix of regular potions
						PotionMeta metapo = (PotionMeta) p.getItemInHand()
								.getItemMeta();
						potions2.add(metapo.getCustomEffects().toString());// mix
																			// of
																			// custom
																			// potions
						p.setItemInHand(null);
					}
					if (potions.size() == 3) {
						String getmsg = getConfig().getString("potionmsg");
						String msg = ChatColor.translateAlternateColorCodes(
								'&', getmsg);
						p.sendMessage(msg);
						ItemStack potion = new ItemStack(Material.POTION, 1);
						PotionMeta meta = (PotionMeta) potion.getItemMeta();
						for (int num = 0; num <= 2; num++) {
							if (potions.get(num).contentEquals("8227")
									|| potions2.toString().contains(
											"FIRE_RESISTANCE:(3600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.FIRE_RESISTANCE, 3600,
										0), true);
								p.sendMessage("fire 3600");
								if (lore.indexOf(ChatColor.GRAY
										+ "Fire Resistence 3:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Fire Resistence 8:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Fire Resistence 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Fire Resistence 6:00 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Fire Resistence 3:00");
								}
							}
							if (potions.get(num).contentEquals("8259")
									|| potions2.toString().contains(
											"FIRE_RESISTANCE:(9600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.FIRE_RESISTANCE, 9600,
										0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Fire Resistence 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Fire Resistence 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Fire Resistence 8:00") == -1) {
									lore.add(ChatColor.GRAY
											+ "Fire Resistence 8:00");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Fire Resistence 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Fire Resistence 3:00"));
								}
							}
							if (potions.get(num).contentEquals("16419")
									|| potions2.toString().contains(
											"FIRE_RESISTANCE:(2700t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.FIRE_RESISTANCE, 2700,
										0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Fire Resistence 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Fire Resistence 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Fire Resistence 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Fire Resistence 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Fire Resistence 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Fire Resistence 2:15 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Fire Resistence 2:15 splash");
								}
							}
							if (potions.get(num).contentEquals("16451")
									|| potions2.toString().contains(
											"FIRE_RESISTANCE:(7200t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.FIRE_RESISTANCE, 7200,
										0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Fire Resistence 6:00 splash") != -1) {
									lore.add(ChatColor.GRAY
											+ "Fire Resistence 6:00 splash");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Fire Resistence 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Fire Resistence 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Fire Resistence 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Fire Resistence 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Fire Resistence 2:15 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Fire Resistence 2:15 splash"));
								}
							}

							if (potions.get(num).contentEquals("8268")
									|| potions2.toString().contains(
											"HARM:(20t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.HARM, 20, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "HARM II") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "HARM I") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "HARM I splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "HARM II splash") == -1) {
									lore.add(ChatColor.GRAY + "HARM I");
								}
							}
							if (potions.get(num).contentEquals("8236")
									|| potions2.toString().contains(
											"HARM:(20t-x1)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.HARM, 20, 1), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "HARM I splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "HARM II splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "HARM II") == -1) {
									lore.add(ChatColor.GRAY + "HARM II");
								}
								if (lore.indexOf(ChatColor.GRAY + "HARM I") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HARM I"));
								}
							}
							if (potions.get(num).contentEquals("16460")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.HARM, 20, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "HARM I") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HARM I"));
								}
								if (lore.indexOf(ChatColor.GRAY + "HARM II") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HARM II"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "HARM II splash") == -1) {
									if (lore.indexOf(ChatColor.GRAY
											+ "HARM I splash") == -1) {
										lore.add(ChatColor.GRAY
												+ "HARM I splash");
									}
								}
							}
							if (potions.get(num).contentEquals("16428")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.HARM, 20, 1), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "HARM II splash") == -1) {
									lore.add(ChatColor.GRAY + "HARM II splash");
								}
								if (lore.indexOf(ChatColor.GRAY + "HARM I") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HARM I"));
								}
								if (lore.indexOf(ChatColor.GRAY + "HARM II") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HARM II"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "HARM I splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HARM I splash"));
								}
							}

							if (potions.get(num).contentEquals("8261")
									|| potions2.toString().contains(
											"HEAL:(20t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.HEAL, 20, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "HEAL II") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "HEAL I splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "HEAL II splash") == -1) {
									if (lore.indexOf(ChatColor.GRAY + "HEAL I") != -1) {
										lore.add(ChatColor.GRAY + "HEAL I");
									}
								}
							}
							if (potions.get(num).contentEquals("8229")
									|| potions2.toString().contains(
											"HEAL:(20t-x1)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.HEAL, 20, 1), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "HEAL I splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "HEAL II splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "HEAL II") == -1) {
									lore.add(ChatColor.GRAY + "HEAL II");
								}
								if (lore.indexOf(ChatColor.GRAY + "HEAL I") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HEAL I"));
								}
							}
							if (potions.get(num).contentEquals("16453")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.HEAL, 20, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "HEAL I") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HEAL I"));
								}
								if (lore.indexOf(ChatColor.GRAY + "HEAL II") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HEAL II"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "HEAL II splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "HEAL I splash") == -1) {
									lore.add(ChatColor.GRAY + "HEAL I splash");
								}
							}
							if (potions.get(num).contentEquals("16421")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.HEAL, 20, 1), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "HEAL II splash") == -1) {
									lore.add(ChatColor.GRAY + "HEAL II splash");
								}
								if (lore.indexOf(ChatColor.GRAY + "HEAL I") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HEAL I"));
								}
								if (lore.indexOf(ChatColor.GRAY + "HEAL II") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HEAL II"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "HEAL I splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "HEAL I splash"));
								}
							}

							if (potions.get(num).contentEquals("8201")
									|| potions2.toString().contains(
											"INCREASE_DAMAGE:(3600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.INCREASE_DAMAGE, 3600,
										0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength II 1:30") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength 8:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength 3:00") == -1) {
									lore.add(ChatColor.GRAY + "Strength 3:00");
								}
							}
							if (potions.get(num).contentEquals("8233")
									|| potions2.toString().contains(
											"INCREASE_DAMAGE:(1800t-x1)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.INCREASE_DAMAGE, 1800,
										1), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 8:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength II 1:30") == -1) {
									lore.add(ChatColor.GRAY
											+ "Strength II 1:30");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength 3:00"));
								}
							}
							if (potions.get(num).contentEquals("8265")
									|| potions2.toString().contains(
											"INCREASE_DAMAGE:(9600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.INCREASE_DAMAGE, 9600,
										0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength 8:00") == -1) {
									lore.add(ChatColor.GRAY + "Strength 8:00");
								}
							}
							if (potions.get(num).contentEquals("16393")
									|| potions2.toString().contains(
											"INCREASE_DAMAGE:(2700t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.INCREASE_DAMAGE, 2700,
										0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength 2:15 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Strength 2:15 splash");
								}
							}
							if (potions.get(num).contentEquals("16425")
									|| potions2.toString().contains(
											"INCREASE_DAMAGE:(1340t-x1)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.INCREASE_DAMAGE, 1340,
										1), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Strength II 1:07 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Strength II 1:07 splash");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 2:15 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength 2:15 splash"));
								}

							}
							if (potions.get(num).contentEquals("16457")
									|| potions2.toString().contains(
											"INCREASE_DAMAGE:(7200t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.INCREASE_DAMAGE, 7200,
										0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 2:15 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength 2:15 splash"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength II 1:07 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Strength II 1:07 splash"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Strength 6:00 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Strength 6:00 splash");
								}
							}

							if (potions.get(num).contentEquals("8238")
									|| potions2.toString().contains(
											"INVISIBILITY:(3600t-x0)")) {
								meta.addCustomEffect(
										new PotionEffect(
												PotionEffectType.INVISIBILITY,
												3600, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Invisibility 8:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Invisibility 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Invisibility 6:00 splash") == -1) {
									if (lore.indexOf(ChatColor.GRAY
											+ "Invisibility 3:00") != -1) {
										lore.add(ChatColor.GRAY
												+ "Invisibility 3:00");
									}
								}
							}
							if (potions.get(num).contentEquals("8270")
									|| potions2.toString().contains(
											"INVISIBILITY:(9600t-x0)")) {
								meta.addCustomEffect(
										new PotionEffect(
												PotionEffectType.INVISIBILITY,
												9600, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Invisibility 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Invisibility 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Invisibility 8:00") == -1) {
									lore.add(ChatColor.GRAY
											+ "Invisibility 8:00");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Invisibility 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Invisibility 3:00"));
								}
							}
							if (potions.get(num).contentEquals("16430")
									|| potions2.toString().contains(
											"INVISIBILITY:(2700t-x0)")) {
								meta.addCustomEffect(
										new PotionEffect(
												PotionEffectType.INVISIBILITY,
												2700, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Invisibility 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Invisibility 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Invisibility 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Invisibility 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Invisibility 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Invisibility 2:15 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Invisibility 2:15 splash");
								}
							}
							if (potions.get(num).contentEquals("16462")
									|| potions2.toString().contains(
											"INVISIBILITY:(7200t-x0)")) {
								meta.addCustomEffect(
										new PotionEffect(
												PotionEffectType.INVISIBILITY,
												7200, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Invisibility 6:00 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Invisibility 6:00 splash");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Invisibility 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Invisibility 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Invisibility 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Invisibility 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Invisibility 2:15 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Invisibility 2:15 splash"));
								}
							}

							if (potions.get(num).contentEquals("8230")
									|| potions2.toString().contains(
											"NIGHT_VISION:(3600t-x0)")) {
								meta.addCustomEffect(
										new PotionEffect(
												PotionEffectType.NIGHT_VISION,
												3600, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Night Vision 8:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Night Vision 3:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Night Vision 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Night Vision 6:00 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Night Vision 3:00");
								}
							}
							if (potions.get(num).contentEquals("8262")
									|| potions2.toString().contains(
											"NIGHT_VISION:(9600t-x0)")) {
								meta.addCustomEffect(
										new PotionEffect(
												PotionEffectType.NIGHT_VISION,
												9600, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Night Vision 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Night Vision 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Night Vision 8:00") == -1) {
									lore.add(ChatColor.GRAY
											+ "Night Vision 8:00");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Night Vision 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Night Vision 3:00"));
								}
							}
							if (potions.get(num).contentEquals("16422")
									|| potions2.toString().contains(
											"NIGHT_VISION:(2700t-x0)")) {
								meta.addCustomEffect(
										new PotionEffect(
												PotionEffectType.NIGHT_VISION,
												2700, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Night Vision 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Night Vision 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Night Vision 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Night Vision 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Night Vision 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Night Vision 2:15 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Night Vision 2:15 splash");
								}
							}
							if (potions.get(num).contentEquals("16454")
									|| potions2.toString().contains(
											"NIGHT_VISION:(7200t-x0)")) {
								meta.addCustomEffect(
										new PotionEffect(
												PotionEffectType.NIGHT_VISION,
												7200, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Night Vision 6:00 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Night Vision 6:00 splash");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Night Vision 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Night Vision 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Night Vision 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Night Vision 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Night Vision 2:15 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Night Vision 2:15 splash"));
								}
							}

							if (potions.get(num).contentEquals("8196")
									|| potions2.toString().contains(
											"POISON:(900t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.POISON, 900, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison II 0:22") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison 2:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison 0:33 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison II 0:16 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison 1:30 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison 0:45") == -1) {
									lore.add(ChatColor.GRAY + "Poison 0:45");
								}
							}
							if (potions.get(num).contentEquals("8228")
									|| potions2.toString().contains(
											"POISON:(440t-x1)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.POISON, 440, 1), true);
								if (lore.indexOf(ChatColor.GRAY + "Poison 2:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison 0:33 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison II 0:16 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison 1:30 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison II 0:22") == -1) {
									lore.add(ChatColor.GRAY + "Poison II 0:22");
								}
								if (lore.indexOf(ChatColor.GRAY + "Poison 0:45") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison 0:45"));
								}
							}
							if (potions.get(num).contentEquals("8260")
									|| potions2.toString().contains(
											"POISON:(2400t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.POISON, 2400, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "Poison 0:45") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison 0:45"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison II 0:22") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison II 0:22"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison 0:33 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison II 0:16 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison 1:30 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison 2:00") == -1) {
									lore.add(ChatColor.GRAY + "Poison 2:00");
								}
							}
							if (potions.get(num).contentEquals("16388")
									|| potions2.toString().contains(
											"POISON:(660t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.POISON, 660, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "Poison 0:45") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison 0:45"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison II 0:22") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison II 0:22"));
								}
								if (lore.indexOf(ChatColor.GRAY + "Poison 2:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison 2:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison II 0:16 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison 1:30 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison 0:33 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Poison 0:33 splash");
								}
							}
							if (potions.get(num).contentEquals("16420")
									|| potions2.toString().contains(
											"POISON:(320t-x1)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.POISON, 320, 1), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison 1:30 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Poison II 0:16 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Poison II 0:16 splash");
								}
								if (lore.indexOf(ChatColor.GRAY + "Poison 0:45") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison 0:45"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison II 0:22") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison II 0:22"));
								}
								if (lore.indexOf(ChatColor.GRAY + "Poison 2:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison 2:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison 0:33 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison 0:33 splash"));
								}

							}
							if (potions.get(num).contentEquals("16452")
									|| potions2.toString().contains(
											"POISON:(1800t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.POISON, 1800, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "Poison 0:45") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison 0:45"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison II 0:22") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison II 0:22"));
								}
								if (lore.indexOf(ChatColor.GRAY + "Poison 2:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison 2:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison 0:33 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison 0:33 splash"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison II 0:16 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Poison II 0:16 splash"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Poison 1:30 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Poison 1:30 splash");
								}
							}

							if (potions.get(num).contentEquals("8193")
									|| potions2.toString().contains(
											"REGENERATION:(900t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.REGENERATION, 900, 0),
										true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration II 0:22") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration 2:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration 0:33 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration II 0:16 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration 1:30 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration 0:45") == -1) {
									lore.add(ChatColor.GRAY
											+ "Regeneration 0:45");
								}
							}
							if (potions.get(num).contentEquals("8225")
									|| potions2.toString().contains(
											"REGENERATION:(440t-x1)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.REGENERATION, 440, 1),
										true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 2:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration 0:33 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration II 0:16 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration 1:30 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration II 0:22") == -1) {
									lore.add(ChatColor.GRAY
											+ "Regeneration II 0:22");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 0:45") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration 0:45"));
								}
							}
							if (potions.get(num).contentEquals("8257")
									|| potions2.toString().contains(
											"REGENERATION:(2400t-x0)")) {
								meta.addCustomEffect(
										new PotionEffect(
												PotionEffectType.REGENERATION,
												2400, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 0:45") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration 0:45"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration II 0:22") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration II 0:22"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 0:33 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration II 0:16 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration 1:30 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration 2:00") == -1) {
									lore.add(ChatColor.GRAY
											+ "Regeneration 2:00");
								}
							}
							if (potions.get(num).contentEquals("16385")
									|| potions2.toString().contains(
											"REGENERATION:(660t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.REGENERATION, 660, 0),
										true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 0:45") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration 0:45"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration II 0:22") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration II 0:22"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 2:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration 2:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration II 0:16 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration 1:30 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration 0:33 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Regeneration 0:33 splash");
								}
							}
							if (potions.get(num).contentEquals("16417")
									|| potions2.toString().contains(
											"REGENERATION:(320t-x1)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.REGENERATION, 320, 1),
										true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 1:30 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Regeneration II 0:16 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Regeneration II 0:16 splash");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 0:45") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration 0:45"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration II 0:22") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration II 0:22"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 2:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration 2:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 0:33 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration 0:33 splash"));
								}

							}
							if (potions.get(num).contentEquals("16449")
									|| potions2.toString().contains(
											"REGENERATION:(1800t-x0)")) {
								meta.addCustomEffect(
										new PotionEffect(
												PotionEffectType.REGENERATION,
												1800, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 0:45") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration 0:45"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration II 0:22") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration II 0:22"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 2:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration 2:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 0:33 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration 0:33 splash"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration II 0:16 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Regeneration II 0:16 splash"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Regeneration 1:30 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Regeneration 1:30 splash");
								}
							}

							if (potions.get(num).contentEquals("8203")
									|| potions2.toString().contains(
											"JUMP:(3600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.JUMP, 3600, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump II 1:30") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump 8:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump 3:00") == -1) {
									lore.add(ChatColor.GRAY + "Jump 3:00");
								}
							}
							if (potions.get(num).contentEquals("8235")
									|| potions2.toString().contains(
											"JUMP:(1800t-x1)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.JUMP, 1800, 1), true);
								if (lore.indexOf(ChatColor.GRAY + "Jump 8:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump II 1:30") == -1) {
									lore.add(ChatColor.GRAY + "Jump II 1:30");
								}
								if (lore.indexOf(ChatColor.GRAY + "Jump 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump 3:00"));
								}
							}
							if (potions.get(num).contentEquals("8267")
									|| potions2.toString().contains(
											"JUMP:(9600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.JUMP, 9600, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "Jump 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump 8:00") == -1) {
									lore.add(ChatColor.GRAY + "Jump 8:00");
								}
							}
							if (potions.get(num).contentEquals("16395")
									|| potions2.toString().contains(
											"JUMP:(2700t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.JUMP, 2700, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "Jump 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY + "Jump 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump 2:15 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Jump 2:15 splash");
								}
							}
							if (potions.get(num).contentEquals("16427")
									|| potions2.toString().contains(
											"JUMP:(1340t-x1)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.JUMP, 1340, 1), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Jump II 1:07 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Jump II 1:07 splash");
								}
								if (lore.indexOf(ChatColor.GRAY + "Jump 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY + "Jump 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump 2:15 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump 2:15 splash"));
								}

							}
							if (potions.get(num).contentEquals("16459")
									|| potions2.toString().contains(
											"JUMP:(7200t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.JUMP, 7200, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "Jump 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY + "Jump 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump 2:15 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump 2:15 splash"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump II 1:07 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Jump II 1:07 splash"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Jump 6:00 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Jump 6:00 splash");
								}
							}

							if (potions.get(num).contentEquals("8234")
									|| potions2.toString().contains(
											"SLOW:(1800t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.SLOW, 1800, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Slowness 4:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Slowness 1:30") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Slowness 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Slowness 3:00 splash") == -1) {
									lore.add(ChatColor.GRAY + "Slowness 1:30");
								}
							}
							if (potions.get(num).contentEquals("8266")
									|| potions2.toString().contains(
											"SLOW:(4800t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.SLOW, 4800, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Slowness 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Slowness 3:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Slowness 4:00") == -1) {
									lore.add(ChatColor.GRAY + "Slowness 4:00");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Slowness 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Slowness 1:30"));
								}
							}
							if (potions.get(num).contentEquals("16426")
									|| potions2.toString().contains(
											"SLOW:(1340t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.SLOW, 1340, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Slowness 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Slowness 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Slowness 4:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Slowness 4:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Slowness 3:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Slowness 1:07 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Slowness 1:07 splash");
								}
							}
							if (potions.get(num).contentEquals("16458")
									|| potions2.toString().contains(
											"SLOW:(3600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.SLOW, 3600, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Slowness 3:00 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Slowness 3:00 splash");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Slowness 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Slowness 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Slowness 4:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Slowness 4:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Slowness 1:07 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Slowness 1:07 splash"));
								}
							}

							if (potions.get(num).contentEquals("8194")
									|| potions2.toString().contains(
											"SPEED:(3600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.SPEED, 3600, 0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed II 1:30") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed 8:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed 3:00") == -1) {
									lore.add(ChatColor.GRAY + "Speed 3:00");
								}
							}
							if (potions.get(num).contentEquals("8226")
									|| potions2.toString().contains(
											"SPEED:(1800t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.SPEED, 1800, 1), true);
								if (lore.indexOf(ChatColor.GRAY + "Speed 8:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed II 1:30") == -1) {
									lore.add(ChatColor.GRAY + "Speed II 1:30");
								}
								if (lore.indexOf(ChatColor.GRAY + "Speed 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed 3:00"));
								}
							}
							if (potions.get(num).contentEquals("8258")
									|| potions2.toString().contains(
											"SPEED:(9600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.SPEED, 9600, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "Speed 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed 8:00") == -1) {
									lore.add(ChatColor.GRAY + "Speed 8:00");
								}
							}
							if (potions.get(num).contentEquals("16386")
									|| potions2.toString().contains(
											"SPEED:(2700t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.SPEED, 2700, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "Speed 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY + "Speed 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed II 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed 2:15 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Speed 2:15 splash");
								}
							}
							if (potions.get(num).contentEquals("16418")
									|| potions2.toString().contains(
											"SPEED:(1340t-x1)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.SPEED, 1340, 1), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Speed II 1:07 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Speed II 1:07 splash");
								}
								if (lore.indexOf(ChatColor.GRAY + "Speed 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY + "Speed 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed 2:15 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed 2:15 splash"));
								}

							}
							if (potions.get(num).contentEquals("16450")
									|| potions2.toString().contains(
											"SPEED:(7200t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.SPEED, 7200, 0), true);
								if (lore.indexOf(ChatColor.GRAY + "Speed 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed II 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed II 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY + "Speed 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed 2:15 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed 2:15 splash"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed II 1:07 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Speed II 1:07 splash"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Speed 6:00 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Speed 6:00 splash");
								}
							}

							if (potions.get(num).contentEquals("8237")
									|| potions2.toString().contains(
											"WATER_BREATHING:(3600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.WATER_BREATHING, 3600,
										0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Water Breathing 3:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Water Breathing 8:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Water Breathing 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Water Breathing 6:00 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Water Breathing 3:00");
								}
							}
							if (potions.get(num).contentEquals("8269")
									|| potions2.toString().contains(
											"WATER_BREATHING:(9600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.WATER_BREATHING, 9600,
										0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Water Breathing 2:15 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Water Breathing 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Water Breathing 8:00") == -1) {
									lore.add(ChatColor.GRAY + "Water Breathing 8:00");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Water Breathing 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Water Breathing 3:00"));
								}
							}
							if (potions.get(num).contentEquals("16429")
									|| potions2.toString().contains(
											"WATER_BREATHING:(2700t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.WATER_BREATHING, 2700,
										0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Water Breathing 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Water Breathing 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Water Breathing 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Water Breathing 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Water Breathing 6:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Water Breathing 2:15 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Water Breathing 2:15 splash");
								}
							}
							if (potions.get(num).contentEquals("16461")
									|| potions2.toString().contains(
											"WATER_BREATHING:(7200t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.WATER_BREATHING, 7200,
										0), true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Water Breathing 6:00 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Water Breathing 6:00 splash");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Water Breathing 3:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Water Breathing 3:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Water Breathing 8:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Water Breathing 8:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Water Breathing 2:15 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Water Breathing 2:15 splash"));
								}
							}

							if (potions.get(num).contentEquals("8232")
									|| potions2.toString().contains(
											"WEAKNESS:(1800t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.WEAKNESS, 1800, 0),
										true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Weakness 1:30") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Weakness 4:00") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Weakness 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Weakness 3:00 splash") == -1) {
									lore.add(ChatColor.GRAY + "Weakness 1:30");
								}
							}
							if (potions.get(num).contentEquals("8264")
									|| potions2.toString().contains(
											"WEAKNESS:(4800t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.WEAKNESS, 4800, 0),
										true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Weakness 1:07 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Weakness 3:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Weakness 4:00") == -1) {
									lore.add(ChatColor.GRAY + "Weakness 4:00");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Weakness 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Weakness 1:30"));
								}
							}
							if (potions.get(num).contentEquals("16424")
									|| potions2.toString().contains(
											"WEAKNESS:(1340t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.WEAKNESS, 1340, 0),
										true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Weakness 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Weakness 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Weakness 4:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Weakness 4:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Weakness 3:00 splash") == -1
										&& lore.indexOf(ChatColor.GRAY
												+ "Weakness 1:07 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Weakness 1:07 splash");
								}
							}
							if (potions.get(num).contentEquals("16456")
									|| potions2.toString().contains(
											"WEAKNESS:(3600t-x0)")) {
								meta.addCustomEffect(new PotionEffect(
										PotionEffectType.WEAKNESS, 3600, 0),
										true);
								if (lore.indexOf(ChatColor.GRAY
										+ "Weakness 3:00 splash") == -1) {
									lore.add(ChatColor.GRAY
											+ "Weakness 3:00 splash");
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Weakness 1:30") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Weakness 1:30"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Weakness 4:00") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Weakness 4:00"));
								}
								if (lore.indexOf(ChatColor.GRAY
										+ "Weakness 1:07 splash") != -1) {
									lore.remove(lore.indexOf(ChatColor.GRAY
											+ "Weakness 1:07 splash"));
								}
							}

						}

						String getname = getConfig().getString("potionname");
						String name = ChatColor.translateAlternateColorCodes(
								'&', getname);
						meta.setDisplayName(name);
						meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
						meta.setLore(lore);
						if (meta.getLore().toString().contains("splash")) {
							ItemStack potion2 = new ItemStack(Material.POTION,
									1, (short) 16384);
							meta.setLore(lore);
							potion2.setItemMeta(meta);
							p.getInventory().addItem(potion2);
							econ.withdrawPlayer(p, getConfig().getInt("potioncost"));
							potions.clear();
							potions2.clear();
							lore.clear();
							a = 0;
							b.setData((byte) 0);
							return;
						}
						potion.setItemMeta(meta);
						econ.withdrawPlayer(p, getConfig().getInt("potioncost"));
						p.getInventory().addItem(potion);
						potions.clear();
						potions2.clear();
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
