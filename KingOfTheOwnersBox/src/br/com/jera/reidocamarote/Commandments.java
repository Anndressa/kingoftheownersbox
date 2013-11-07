package br.com.jera.reidocamarote;

public enum Commandments {
	DesignerClothing("Roupas de Grife","Burberry, Armani, Prada, Gucci"),
	Car("Carrão","Tem que ser Ferrari"),
	OwnersBox("Camarote","Quem está na pista é só mais um"),
	ExclusiveService("Serviço Exclusivo","Alguém para servir seus convidados"),
	BodyGuard("Seguranças","Cuidado com sua integridade física"),
	Champagne("Champagne","Chama atenção na balada, é mais status"),
	Celebrities("Famosos","Pessoas da mídia, celebridades"),
	Women("Mulheres","Camarote sem mulher não faz sentido"),
	Music("Música","House, Hip Hop, Black, Funk"),
	Instagram("Instagram","Tem que divulgar suas fotos e vídeos");
	
	String name;
	String description;
	
	private Commandments(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	
}
