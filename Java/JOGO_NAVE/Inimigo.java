import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {

	private Image imagem;//imagem do inimigo
	private int x, y;//posição do inimigo
	private int largura, altura;//largura e altura do inimigo
	private boolean isVisivel;//verifica se o inimigo está visivel

	private static final int LARGURA_TELA = 810;//largura da tela
	private static final int VELOCIDADE = 2;//velocidade do inimigo

	private static int contador = 0;//contador para os inimigos
	
	public Inimigo(int x, int y) {//construtor da classe inimigo

		this.x = x;//posição do inimigo eixo x
		this.y = y;//posição do inimigo eixo y

		ImageIcon referencia;//carrega a imagem do inimigo

		if(contador++ % 2 == 0){//se o contador for divisivel por 3
			referencia = new ImageIcon("res\\inimigo_2.gif");//carrega a imagem do inimigo 2
		
		} else {
			
			referencia = new ImageIcon("res\\inimigo_1.gif");//carrega a imagem do inimigo 1
		}
		imagem = referencia.getImage();//pega a imagem do inimigo
		
		this.largura = imagem.getWidth(null);//pega a largura da imagem do inimigo
		this.altura = imagem.getHeight(null);//pega a altura da imagem do inimigo

		isVisivel = true;//inimigo visivel
	}

	public void mexer(){//método para mover o inimigo

		if(this.x < 0){
			this.x = LARGURA_TELA;//posição do inimigo eixo x
		} else {
			this.x -= VELOCIDADE;//posição do inimigo eixo x
		}
		
	}
	
	public boolean isVisivel() {//verifica se o inimigo está visivel
		return isVisivel;	
	}

	public void setVisivel(boolean isVisivel) {//seta se o inimigo está visivel
		this.isVisivel = isVisivel;//inimigo não visivel
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getBounds(){//metado de colisão
		return new Rectangle(x, y, largura, altura);//retorna a posição do inimigo
	}
	
}
