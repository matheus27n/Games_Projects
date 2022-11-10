import java.awt.Image;//import para poder usar imagem
import java.awt.Rectangle;//import para poder usar retangulo (metado de colisão)

import javax.swing.ImageIcon;//import para poder usar imagem

public class Missel{

	private Image imagem;//imagem do missel
	private int x, y;//posição do missel
	private int largura, altura;//largura e altura do missel
	private boolean isVisivel;//verifica se o missel está visivel

	private static final int LARGURA_TELA = 810;//largura da tela
	private static final int VELOCIDADE = 3;//velocidade do missel

	public Missel(int x, int y) {//construtor da classe missel

		this.x = x;//posição do missel eixo x
		this.y = y;//posição do missel eixo y

		ImageIcon referencia = new ImageIcon("res\\missel.png");//carrega a imagem do missel
		imagem = referencia.getImage();//pega a imagem do missel

		this.largura = imagem.getWidth(null);//pega a largura da imagem do missel
		this.altura = imagem.getHeight(null);//pega a altura da imagem do missel
		
		isVisivel = true;//missel visivel
	}

	public void mexer(){//método para mover o missel
		
		this.x += VELOCIDADE;//posição do missel eixo x
		if(this.x > LARGURA_TELA){//se a posição do missel for maior que a largura da tela
			isVisivel = false;//missel não visivel
		}
		
	}
	
	public boolean isVisivel() {//verifica se o missel está visivel
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {//seta se o missel está visivel
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {//retorna a imagem do missel
		return imagem;
	}

	public int getX() {//retorna a posição do missel eixo x
		return x;
	}

	public int getY() {//retorna a posição do missel eixo y
		return y;
	}
	
	public Rectangle getBounds(){//metado de colisão
		return new Rectangle(x, y, largura, altura);//retorna um retangulo
	}

	
}
