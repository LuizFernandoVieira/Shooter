package br.unb.shooter;

import java.io.IOException;

import com.esotericsoftware.minlog.Log;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;

public class ServerTest {
	private static State state = new State();

	private static Input input = new Input();

	private static Integer counter = 0;

	public static void main(String[] args) {
		GameController.getInstance().createServerPlayer("Bruno");

		NetController.getInstance().createServerAndListener();

		GameController.getInstance().setServerName("Servidor");

		input.start();

		while (state.getIsRunning()) {
			// Update input.
			if (input.getStop()) {
				state.setIsRunning(false);
			}
			if (input.getStartGame() && !GameController.getInstance().getIsStarted()) {
				NetController.getInstance().startGame();
				GameController.getInstance().setIsStarted(true);
			}

			// Execute messages.
			NetController.getInstance().executeMessages();

			if (GameController.getInstance().getIsStarted()) {
				// Update clients.
				NetController.getInstance().updateGame();
			}

			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				Log.error(e.getMessage());
			}

			counter++;
		}

		NetController.getInstance().stopServer();
	}

	private static class Input extends Thread {

		private Boolean stop;

		private Boolean startGame;

		public Input() {
			stop = false;
			startGame = false;
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
						if (key == 49) {
							startGame = true;
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

		public Boolean getStartGame() {
			return startGame;
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