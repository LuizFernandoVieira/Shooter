package br.unb.shooter;

import java.io.IOException;

import com.esotericsoftware.minlog.Log;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;

public class ClientTest {
	private static State state = new State();

	private static Input input = new Input();

	private static String IP_ADDRESS = "127.0.0.1";

	private static Integer counter = 0;

	public static void main(String[] args) {
		Log.set(Log.LEVEL_DEBUG);

		Log.debug("START CLIENT");

		GameController.getInstance().createServerPlayer("Luiz");

		NetController.getInstance().createClient();

		NetController.getInstance().connectClient(IP_ADDRESS);

		NetController.getInstance().addClientListener();

		input.start();

		while (state.getIsRunning()) {
			// Update input.
			if (input.getStop()) {
				state.setIsRunning(false);
			}

			if (counter >= 10 && counter <= 20) {
				GameController.getInstance().getPlayer().setMoveRight(true);
				GameController.getInstance().getPlayer().update();
				NetController.getInstance().sendPlayerInput(GameController.getInstance().getPlayer());
			}
			
			// Execute messages.
			NetController.getInstance().executeMessages();

			try {
				Thread.sleep(200L);
			} catch (InterruptedException e) {
				Log.error(e.getMessage());
			}

			Log.debug("Ciclo cliente: " + counter + " player x: "
					+ GameController.getInstance().getPlayer().getPositionX() + " player y: "
					+ GameController.getInstance().getPlayer().getPositionY());

			counter++;
		}

		Log.debug("END CLIENT");
	}

	private static class Input extends Thread {

		private Boolean stop;

		public Input() {
			stop = false;
		}

		@Override
		public void run() {
			while (!stop) {
				try {
					try {
						int key = System.in.read();

						if (key == 48) {
							stop = true;
						}
					} catch (IOException e) {
						Log.error(e.getMessage());
					}

					Thread.sleep(100L);
				} catch (InterruptedException e) {
					Log.error(e.getMessage());
				}
			}

			Log.debug("END CLIENT INPUT LISTENER");
		}

		public Boolean getStop() {
			return stop;
		}

	}

	private static class State {
		private Boolean isRunning;

		public State() {
			isRunning = true;
		}

		public Boolean getIsRunning() {
			return isRunning;
		}

		public void setIsRunning(Boolean isRunning) {
			this.isRunning = isRunning;
		}
	}

}
