package teste.projeto_jsf;

import static org.junit.Assert.assertTrue;

import javax.persistence.Persistence;

import org.junit.Test;

public class AppTest {
	public static void main(String[] args) {
		
		Persistence.createEntityManagerFactory("projeto-jsf");
		
	}
}
