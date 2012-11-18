package br.ufrj.tp.chat.client;

public class GamePerguntas {
	public static String p1 = "Protocolo que transmite datagramas sem garantia de entrega dos pacotes?\n";
	public static String r1 = "udp";
	public static String p2 = "Categoria dos servidores DNS responsaveis por dominos como .com, .fr. .br...?\n";
	public static String r2 = "tld";
	public static String p3 = "Campo de verificacao de integridade de dados incluido no cabecalho de mensagens TCP?\n";
	public static String r3 = "checksum";
	public static String p4 = "Servidor que ocupa a porta 25 do TCP?\n";
	public static String r4 = "smtp";
	public static String p5 = "Sistema de transferencia de arquivos de cliente para cliente, geralmente usando protocolo propriet‡rio?\n";
	public static String r5 = "p2p";
	
	public static String perguntas(String mensagem){
		if (mensagem.startsWith("?1")) return p1;
		if (mensagem.startsWith("?2")) return p2;
		if (mensagem.startsWith("?3")) return p3;
		if (mensagem.startsWith("?4")) return p4;
		if (mensagem.startsWith("?5")) return p5;
		return "";
	}
	
	public static String respostas(String mensagem){
		if (mensagem.toLowerCase().contains(r1)) return "!1";
		if (mensagem.toLowerCase().contains(r2)) return "!2";
		if (mensagem.toLowerCase().contains(r3)) return "!3";
		if (mensagem.toLowerCase().contains(r4)) return "!4";
		if (mensagem.toLowerCase().contains(r5)) return "!5";
		
		return "0";
	}
	
}
