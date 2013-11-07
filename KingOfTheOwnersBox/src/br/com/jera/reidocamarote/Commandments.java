package br.com.jera.reidocamarote;

public enum Commandments {
	DesignerClothing("Roupas de Grife","Burberry, Armani, Prada, Gucci"),
	Car("Carr�o","Tem que ser Ferrari"),
	OwnersBox("Camarote","Quem est� na pista � s� mais um"),
	ExclusiveService("Servi�o Exclusivo","Algu�m para servir seus convidados"),
	BodyGuard("Seguran�as","Cuidado com sua integridade f�sica"),
	Champagne("Champagne","Chama aten��o na balada, � mais status"),
	Celebrities("Famosos","Pessoas da m�dia, celebridades"),
	Women("Mulheres","Camarote sem mulher n�o faz sentido"),
	Music("M�sica","House, Hip Hop, Black, Funk"),
	Instagram("Instagram","Tem que divulgar suas fotos e v�deos");
	
	String name;
	String description;
	
	private Commandments(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	
}
