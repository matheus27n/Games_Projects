import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

public class ContainerDeJanelas extends JFrame{
	
	public ContainerDeJanelas(){
		
		JMenuBar barraMenu = new JMenuBar();//cria a barra de menu
		
		JMenu menu = new JMenu("Menu");//cria o menu

		JMenuItem sobre = new JMenuItem("Sobre");//cria o item sobre
		sobre.addActionListener(new ActionListener() {//adiciona um evento ao item sobre
			
			@Override
			public void actionPerformed(ActionEvent e) {//quando o item sobre for clicado

				JOptionPane.showMessageDialog(null, "Primeiro Game, Diveritam-se", "Informacoes", JOptionPane.INFORMATION_MESSAGE);//mostra uma mensagem
			}
		});
		
		JMenuItem sair = new JMenuItem("Sair");//cria o item sair
		sair.addActionListener(new ActionListener(){//adiciona um evento ao item sair
			
			public void actionPerformed(ActionEvent e){//quando o item sair for clicado
				System.exit(0);//encerra o jogo
			}
		});
		
		menu.add(sobre);//adiciona o item sobre ao menu
		menu.add(new JSeparator());//adiciona uma linha ao menu
		menu.add(sair);//adiciona o item sair ao menu
		
		barraMenu.add(menu);//adiciona o menu a barra de menu
		
		setJMenuBar(barraMenu);//adiciona a barra de menu a janela
		
		
		add(new Fase());
		setTitle("B-Space Invaders");//titulo da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//encerra o jogo quando a janela for fechada
		setSize(810, 510);//tamanho da janela
		setLocationRelativeTo(null);//centraliza a janela
		setResizable(false);//nao permite que a janela seja redimensionada
		setVisible(true);//deixa a janela visivel
		
	}
	
	public static void main(String[] args) {
		new ContainerDeJanelas();//cria uma nova janela
	}
}
