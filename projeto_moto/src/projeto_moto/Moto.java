package projeto_moto;

// Classe Moto - substitui a Nave. Controlada pelo usuário.
public class Moto extends DesenhoMovel {

	public Moto() {}

	public Moto(int x, int y, String path) {
		super(x, y, path);
	}
	@Override
    public void moverDireita() {
        this.setX(this.getX() + 10);
    }

    @Override
    public void moverEsquerda() {
        this.setX(this.getX() - 1);
    }
public void moverCima() {
    this.setY(this.getY() - 4);
}

public void moverBaixo() {
    this.setY(this.getY() + 4);
} //teste
}