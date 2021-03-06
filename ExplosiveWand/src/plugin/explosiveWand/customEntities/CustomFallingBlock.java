package plugin.explosiveWand.customEntities;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;

import net.minecraft.server.v1_16_R2.AxisAlignedBB;
import net.minecraft.server.v1_16_R2.Block;
import net.minecraft.server.v1_16_R2.Entity;
import net.minecraft.server.v1_16_R2.EntityFallingBlock;
import net.minecraft.server.v1_16_R2.EntityTypes;

public class CustomFallingBlock extends EntityFallingBlock{

	public CustomFallingBlock(Location loc, Block b) {
		super(EntityTypes.FALLING_BLOCK, ((CraftWorld) loc.getWorld()).getHandle());
		
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());		
		
		
	}
	
	
	public void removeHitbox() {

		Field hitbox = accessField("boundingBox", Entity.class); 
		setField(hitbox, new AxisAlignedBB(0, 100, 0, 0, 100, 0));

	}
	
	public Field accessField(String fieldName, Class<?> c) {
	
		try {
//			System.out.println(o.getClass().getField("boundingBox"));
			Field f = c.getDeclaredField(fieldName);
			f.setAccessible(true);
			return f;
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
	
	public void setField(Field f, Object v) {
		
		try {
			System.out.println("FIELD: " + f);
			f.set(this, v);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}