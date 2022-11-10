import java.awt.Color;//import para usar cor
import java.awt.Graphics;//import para usar graficos
import java.awt.Graphics2D;//import para usar graficos 2d
import java.awt.Image;//import para poder usar imagem
import java.awt.Rectangle;//import para poder usar retangulo (metado de colisão)
import java.awt.event.ActionEvent;//import para poder usar eventos
import java.awt.event.ActionListener;//import para poder usar eventos
import java.awt.event.KeyAdapter;//import para as teclas
import java.awt.event.KeyEvent;//import para as teclas
import java.util.ArrayList;//import para poder usar arraylist
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;//import para poder usar painel
import javax.swing.Timer;//import para poder usar timer

public class Fase extends JPanel implements ActionListener {//cria a classe fase que herda de JPanel e implementa ActionListener

	private Image fundo;//cria a variavel imagem para o fundo
	private Nave nave;//cria a variavel nave
	private Timer timer;//cria a variavel timer

	private boolean emJogo;//variavel pra saber se o jogo começou

	private List<Inimigo> inimigos;//cria a lista de inimigos

    //NUMERO TOTAL DE INIMIGOS DETERMINADO PELO VETOR DE POSIÇÕES X E Y DELES
	private int[][] coordenadas = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 },//cordernadas dos inimigos no eixo x e y
			{ 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
			{ 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
			{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
			{ 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
			{ 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
			{ 920, 300 }, { 856, 328 }, { 456, 320 } };

	public Fase() {//construtor da classe fase

		setFocusable(true);//faz com que o painel fique em foco
		setDoubleBuffered(true);//habilita o buffer duplo
		addKeyListener(new TecladoAdapter());//adiciona o teclado

		ImageIcon referencia = new ImageIcon("res\\fundo.gif");//carrega a imagem de fundo
		fundo = referencia.getImage();//pega a imagem
		nave = new Nave();//instancia a classe nave

		emJogo = true;//inicia o jogo

		inicializaInimigos();//inicializa os inimigos

		timer = new Timer(1, this);//tempo que cada frame leva pra att no painel pintado 
		timer.start();//inicia o timer

	}

	public void inicializaInimigos() {//metado para inicializar os inimigos

		inimigos = new ArrayList<Inimigo>();//uma lista de inimigos

		for (int i = 0; i < coordenadas.length; i++) {//percorre o array de coordenadas
			inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1]));//adiciona os inimigos na lista

		}

	}

	public void paint(Graphics g) {//metado para usar graficos e graficos2D na tela 
		Graphics2D graficos = (Graphics2D) g;//cria a variavel graficos2D
		graficos.drawImage(fundo, 0, 0, null);//desenha a imagem de fundo

		if (emJogo) {//se o jogo estiver em jogo

			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);//desenha a imagem da nave

			List<Missel> misseis = nave.getMisseis();//cria uma lista de misseis

			for (int i = 0; i < misseis.size(); i++) {//percorre a lista de misseis

				Missel m = (Missel) misseis.get(i);//cria uma variavel m do tipo missel
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);//desenha a imagem do missel

			}

			for (int i = 0; i < inimigos.size(); i++) {//percorre a lista de inimigos

				Inimigo in = inimigos.get(i);//cria uma variavel in do tipo inimigo
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);//desenha a imagem do inimigo

			}

			graficos.setColor(Color.WHITE);//seta a cor para branco do numeros de inimigos na tela
			graficos.drawString("INIMIGOS: " + inimigos.size(), 5, 15);//desenha o numero de inimigos na tela
			
		} else {
			
			ImageIcon fimJogo = new ImageIcon("res\\GAMEOVER.png");//carrega a imagem de fim de jogo
			
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);//desenha a imagem de fim de jogo
			
			
		}
		
		g.dispose();//libera a memoria

	}

	@Override//sobrescreve o metodo
	public void actionPerformed(ActionEvent arg0) {//metado para atualizar a tela

		if (inimigos.size() == 0) {//se a lista de inimigos estiver vazia
			emJogo = false;//o jogo acaba
		}

		List<Missel> misseis = nave.getMisseis();//cria uma lista de misseis

		for (int i = 0; i < misseis.size(); i++) {//percorre a lista de misseis

			Missel m = (Missel) misseis.get(i);//cria uma variavel m do tipo missel

			if (m.isVisivel()) {//se o missel estiver visivel
				m.mexer();//chama o metado mexer da classe missel
			} else {
				misseis.remove(i);//remove o missel da lista
			}

		}

		for (int i = 0; i < inimigos.size(); i++) {//percorre a lista de inimigos

			Inimigo in = inimigos.get(i);//cria uma variavel in do tipo inimigo

			if (in.isVisivel()) {//se o inimigo estiver visivel
				in.mexer();//chama o metado mexer da classe inimigo
			} else {
				inimigos.remove(i);//remove o inimigo da lista
			}

		}

		nave.mexer();//chama o metado mexer da classe nave
		checarColisoes();//chama o metado checarColisoes
		repaint();//atualiza a tela
	}

	public void checarColisoes() {//metado para checar as colisoes

		Rectangle formaNave = nave.getBounds();//cria uma variavel do tipo retangulo para a nave
		Rectangle formaInimigo;//cria uma variavel do tipo retangulo para o inimigo
		Rectangle formaMissel;//cria uma variavel do tipo retangulo para o missel

		for (int i = 0; i < inimigos.size(); i++) {//percorre a lista de inimigos

			Inimigo tempInimigo = inimigos.get(i);//cria uma variavel do tipo inimigo
			formaInimigo = tempInimigo.getBounds();//pega o retangulo do inimigo

			if (formaNave.intersects(formaInimigo)) {//se o retangulo da nave colidir com o retangulo do inimigo

				nave.setVisivel(false);//a nave some
				tempInimigo.setVisivel(false);//o inimigo some

				emJogo = false;//o jogo acaba

			}

		}

		List<Missel> misseis = nave.getMisseis();//cria uma lista de misseis

		for (int i = 0; i < misseis.size(); i++) {//percorre a lista de misseis

			Missel tempMissel = misseis.get(i);//cria uma variavel do tipo missel
			formaMissel = tempMissel.getBounds();//pega o retangulo do missel

			for (int j = 0; j < inimigos.size(); j++) {//percorre a lista de inimigos

				Inimigo tempInimigo = inimigos.get(j);//cria uma variavel do tipo inimigo
				formaInimigo = tempInimigo.getBounds();//pega o retangulo do inimigo

				if (formaMissel.intersects(formaInimigo)) {//se o retangulo do missel colidir com o retangulo do inimigo

					tempInimigo.setVisivel(false);//o inimigo some
					tempMissel.setVisivel(false);//o missel some

				}

			}

		}

	}

	private class TecladoAdapter extends KeyAdapter {//classe para pegar os eventos do teclado

		@Override
		public void keyPressed(KeyEvent e) {//metado para pegar o evento de tecla pressionada
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER){//se a tecla enter for pressionada
				emJogo = true;//o jogo começa
				nave = new Nave();//cria uma nova nave
				inicializaInimigos();//chama o metado inicializaInimigos
			}
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){//se a tecla esc for pressionada encerra jogo
                System.exit(0);
            }
			
			nave.keyPressed(e);//chama o metado keyPressed da classe nave
		}

		@Override
		public void keyReleased(KeyEvent e) {//metado para pegar o evento de tecla solta
			nave.keyReleased(e);//chama o metado keyReleased da classe nave
		}

	}

}
