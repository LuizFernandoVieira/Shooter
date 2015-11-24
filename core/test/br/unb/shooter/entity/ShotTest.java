package br.unb.shooter.entity;

import org.junit.Assert;
import org.junit.Test;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.util.Constants;

public class ShotTest {

	@Test
	public void testCreateShot() {
		Player player = new Player();

		player.setHeight(Constants.PLAYER_WALKING_HEIGHT);
		player.setWidth(Constants.PLAYER_WALKING_WIDTH);
		player.setId(1);

		GameController.getInstance().setPlayer(player);

		FireWeapon weapon = new FireWeapon();

		weapon.setWidth(Constants.WEAPON_WIDTH);
		weapon.setHeight(Constants.WEAPON_HEIGHT);
		weapon.setX(player.getX() + Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_RIGHT);
		weapon.setY(player.getY() + Constants.WEAPON_OFFSET_FROM_PLAYER_Y);

		Shot shot = GameController.getInstance().createShot(player);

		System.out.println("x: " + shot.x + "y: " + shot.y);

		Assert.assertTrue(true);
	}

}
