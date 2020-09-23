package me.misleaded.test.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Commands implements CommandExecutor{


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can you that command!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("output")) {
			if (args.length == 0) {
				p.sendMessage("Usage: /output <method1> <method2> ...");
			}
			else {
				
				Object outputObject = p;
				
//				try {
//					p.sendMessage("declared");
////				p.sendMessage(outputObject.getClass().getDeclaredMethod("getInventory").toString());
//					p.sendMessage("not declared");
//					p.sendMessage(outputObject.getClass().getMethod("getInventory").toString());
//				} catch (NoSuchMethodException | SecurityException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					p.sendMessage("no");
//				}
//				for (Method m:outputObject.getClass().getDeclaredMethod()) {
//					p.sendMessage(m.toString());
//				}
				

				for (String methodName:args) {
					try {
//						String derivedClassNameString = outputObject.getClass().getName();
//						p.sendMessage(outputObject.getClass().getName());
////						outputObject = Class.forName(derivedClassNameString).cast(outputObject);
//						p.sendMessage(outputObject.getClass().toString());
//						
//						
//						Method method = Class.forName(derivedClassNameString).cast(outputObject).getClass().getDeclaredMethod(methodName);
//						outputObject = method.invoke(outputObject);
						
						Method method = outputObject.getClass().getMethod(methodName);
						outputObject = method.invoke(outputObject);
						
						
						
//						if (outputObject.getClass().isArray()) {
//							Object[] output = outputObject;
//							
//						}
						
//						p.sendMessage(outputObject.getClass().toString());
						
						if (outputObject.getClass().isArray()) {
							ArrayList<Object> output = new ArrayList<Object>();
//							if (outputObject.getClass().getComponentType().isPrimitive()) {
//								int length = Array.getLength(outputObject);
//								for (int i = 0; i < length; i++) {
//									Object obj = Array.get(outputObject, i);
//									output.add(obj);
//								}
//							} else {
//								Object[] objects = (Object[]) outputObject;
//								for (Object obj:objects) {
//									output.add(obj);
//								}
//							}
							Object[] outputObjectsArray = (Object[]) outputObject;
							
							for (Object item:outputObjectsArray) {
								output.add(item);
							}
							p.sendMessage(methodName + ": " + Arrays.toString(output.toArray()));					
						
						} else {
							p.sendMessage(methodName + ": " + outputObject.toString());
						}
				
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						p.sendMessage("bad");
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}
			}
		
		}
		
		return true;
	}

}
