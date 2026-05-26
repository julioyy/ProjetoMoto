package projeto_moto;

// Classe Moto - substitui a Nave. Controlada pelo usuário.
public class Moto extends DesenhoMovel {

	public Moto() {}

	public Moto(int x, int y, String path) {
		super(x, y, path);
	}
	@Override
    public void moverDireita() throws PosicaoInvalidaException {
		if (this.getX() >= 1900) throw new PosicaoInvalidaException("MOTO");
		this.setX(this.getX() + 10);
    }

    @Override
    public void moverEsquerda() throws PosicaoInvalidaException {
    	if (this.getX() <= -320) throw new PosicaoInvalidaException("MOTO");
    	this.setX(this.getX() - 5);
    }
    public void moverCima() throws PosicaoInvalidaException {
    	if (this.getY() <= 350) throw new PosicaoInvalidaException("MOTO");
    	this.setY(this.getY() - 5);
    }

    public void moverBaixo() throws PosicaoInvalidaException {
    	if (this.getY() >= 850) throw new PosicaoInvalidaException("MOTO");
    	this.setY(this.getY() + 5);
    }
    public void moverDiagonalSuperiorDireito() throws PosicaoInvalidaException {
    	if (this.getX() >= 1900 || this.getY() <= 350) throw new PosicaoInvalidaException("MOTO");
    	this.setX(this.getX() + 5);
        this.setY(this.getY() - 5);
    }

    public void moverDiagonalInferiorDireito() throws PosicaoInvalidaException {
    	if (this.getX() >= 1900 || this.getY() >= 850) throw new PosicaoInvalidaException("MOTO");
    	this.setX(this.getX() + 5);
        this.setY(this.getY() + 5);
    }
}