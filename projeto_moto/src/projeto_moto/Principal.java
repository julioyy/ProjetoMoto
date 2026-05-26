package projeto_moto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

// ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO.
public class Principal extends JFrame implements KeyListener, Runnable {

	private Desenho pista;
	private Moto moto;
	private Carro carro;
	private static final long serialVersionUID = 1L;

	// Thread dedicada para redesenhar a tela continuamente
	private Thread loopRenderizacao = new Thread(this);

	public Principal() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pista = new Desenho(0, 0, "pista.png");       // fundo: imagem de uma pista/estrada
		moto  = new Moto(100, 750, "moto.png");        // moto controlada pelo usuário
		carro = new Carro(100, 500, "carro.png");        // carro que se move automaticamente
		this.addKeyListener(this);
	}

	// EVITAR ALTERAR ESSE MÉTODO
	public static void main(String[] args) {
		Principal t = new Principal();
		t.setSize(1000, 700);
		t.createBufferStrategy(1);
		t.setVisible(true);
		t.createBufferStrategy(2);

		// Inicia o loop de renderização APÓS a janela estar visível
		t.loopRenderizacao.setDaemon(true);
		t.loopRenderizacao.start();
	}

	// Loop de renderização contínua (~60 FPS)
	@Override
	public void run() {
		while (true) {
			renderizarGraphics();
			try {
				Thread.sleep(16); // ~60 FPS
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// EVITAR ALTERAR ESSE MÉTODO
	public void renderizarGraphics() {
		if (getBufferStrategy() == null) return;
		if (!getBufferStrategy().contentsLost()) getBufferStrategy().show();
		Graphics g = getBufferStrategy().getDrawGraphics();

		Graphics g2 = g.create();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		renderizarImagens(g2);

		g.dispose();
		g2.dispose();
	}

	// ESSE DEVE SER O MÉTODO QUE DEVE SER ADAPTADO AO PROJETO
	public void renderizarImagens(Graphics g2) {
		g2.drawImage(pista.getImg(), 0, 0, getWidth(), getHeight(), null);
		moto.desenhar(g2);
		carro.desenhar(g2);
	}

	// EVITAR ALTERAR ESSE MÉTODO — renderização feita pelo loop manual
	@Override
	public void paint(Graphics g) {
		// Deixado vazio intencionalmente: a renderização é feita pelo loop
	}

	public void keyPressed(KeyEvent evt) {
		try {
		if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			moto.moverDireita();
		} else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
			moto.moverEsquerda();
		} else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            moto.moverCima();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            moto.moverBaixo();
		}  else if (evt.getKeyCode() == KeyEvent.VK_E) {
		    moto.moverDiagonalSuperiorDireito();
		} else if (evt.getKeyCode() == KeyEvent.VK_D) {
		    moto.moverDiagonalInferiorDireito();
		}
	} catch (PosicaoInvalidaException e) {  // Reposiciona a moto e o carro
		if (e.getMessage().equals("MOTO")) {
		moto.setX(-320);
        moto.setY(750);
		 } else if (e.getMessage().equals("CARRO")) {
        carro.setX(-400);
        carro.setY(500);
		 }
	   }
	}	
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
}