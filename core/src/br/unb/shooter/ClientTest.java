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

			// Execute messages.
			NetController.getInstance().getMessageQueue().executeMessages();

			if (GameController.getInstance().getIsStarted()) {
				if (counter >= 10 && counter <= 20) {
					GameController.getInstance().getPlayer().setMoveRight(true);
					GameController.getInstance().getPlayer().update();
					NetController.getInstance().sendPlayerInput(GameController.getInstance().getPlayer());
				}

				counter++;
			}

			try {
				Thread.sleep(200L);
			} catch (InterruptedException e) {
				Log.error(e.getMessage());
			}

		}

		NetController.getInstance().stopClient();
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
