package suporte;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class Generator {
	//metodo publico e statico que retorna uma string 
	public static String  dataHoraParaArquivo() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		return new SimpleDateFormat("yyyyMMddhhmmss").format(ts); 
		
		
		
		
	}
	

}
