import java.awt.Image;//import Image class
import java.awt.Rectangle;//import Rectangle class
import java.awt.event.KeyEvent;//import KeyEvent class
import java.util.ArrayList;//import ArrayList class
import java.util.List;//import List class

import javax.swing.ImageIcon;//import ImageIcon class

public class Nave {

	private int x, y;//posição da nave
	private int dx, dy;//velocidade da nave
	private int altura, largura;//altura e largura da nave
	private boolean isVisivel;//verifica se a nave está visivel

	private Image imagem;//imagem da nave
	
	private List<Missel> misseis;//lista de misseis
	
	public Nave(){//construtor da classe Nave
		
		ImageIcon referencia = new ImageIcon("res\\nave.gif");//carrega a imagem da nave
		imagem = referencia.getImage();//pega a imagem da nave
		
		altura = imagem.getHeight(null);//pega a altura da imagem da nave
		largura = imagem.getWidth(null);//pega a largura da imagem da nave
		
		misseis = new ArrayList<Missel>();//instancia a lista de misseis
		
		this.x = 100;//posição inicial da nave
		this.y = 100;//posição inicial da nave
		
	}
	
	public void mexer(){//método para mover a nave

		x += dx; // 1 e 462 //ate onde ele pode se mover
		y += dy; // 1 e 340 //ate onde ele pode se mover

		if(this.x < 1){//se a posição da nave for menor que 1
			x = 1;//a posição da nave é 1
		}
		
		if(this.x > 760){//se a posição da nave for maior que 462 eixo x ONDE ELE VAI RETO
			x = 760;//a posição da nave é 462
		}
		
		if(this.y < 1){//se a posição da nave for menor que 1
			y = 1;//a posição da nave é 1
		}

		if(this.y > 420){//se a posição da nave for maior que 340
			y = 420;//a posição da nave é 340
		}
		
	}
	
	public List<Missel> getMisseis() {//retorna a lista de misseis
		return misseis;
	}

	public int getX() {//retorna a posição x da nave
		return x;
	}
	
	public int getY() {	//retorna a posição y da nave
		return y;
	}
	
	public Image getImagem() {//retorna a imagem da nave
		return imagem;
	}

	public boolean isVisivel() {//retorna se a nave está visivel
		return isVisivel;
	}
	
	public void setVisivel(boolean isVisivel) {//seta se a nave está visivel
		this.isVisivel = isVisivel;//seta se a nave está visivel
	}
	
	public void atira(){//método para atirar
		this.misseis.add(new Missel(x+largura, y + altura/2 ));//adiciona um novo missel na lista de misseis
	}
    
    //PARTE IMPORTANTE SOBRE AS COLIÇÕES DO JOGO

	public Rectangle getBounds(){//retorna o retangulo da nave
		return new Rectangle(x, y, largura, altura);//retorna o retangulo da nave
	}
	
	
	public void keyPressed(KeyEvent tecla){//método para verificar se uma tecla foi pressionada
		
		int codigo = tecla.getKeyCode();//pega o código da tecla pressionada
		
		if(codigo == KeyEvent.VK_SPACE){//se a tecla pressionada for o espaço ATIRA
			atira();
		}
        
		if(codigo == KeyEvent.VK_UP){
			dy = -3;
		}
		
		if(codigo == KeyEvent.VK_DOWN){
			dy = 3;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = -3;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 3;
		}
		
	}
	
	public void keyReleased(KeyEvent tecla){//método para verificar se uma tecla foi solta
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP){
			dy = 0;
		}
 
		if(codigo == KeyEvent.VK_DOWN){
			dy = 0;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = 0;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		
	}
	
	
}
