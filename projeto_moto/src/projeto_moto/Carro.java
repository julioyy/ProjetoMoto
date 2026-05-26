package projeto_moto;

// Classe Carro - substitui o Asteroide. Move-se automaticamente em loop.
public class Carro extends DesenhoAnimado {

	private int loopStatus = 0;
	private Thread t = new Thread(this);

	public Carro() {}

	public Carro(int x, int y, String path) {
		super(x, y, path);
		// Thread daemon: encerra junto com a JVM quando a janela fecha
		t.setDaemon(true);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			switch (loopStatus) {
				case 0: this.setX(this.getX() + 5); break;
				case 1: this.setY(this.getY() + 0); break;
				case 2: this.setX(this.getX() - 0); break;
				case 3: this.setY(this.getY() - 0); break;
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			loopStatus++;
			if (loopStatus == 4) loopStatus = 0;
		}
	}
}
