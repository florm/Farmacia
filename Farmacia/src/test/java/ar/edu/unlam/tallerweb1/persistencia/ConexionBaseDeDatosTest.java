package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

// Clase que prueba la conexion a la base de datos. Hereda de SpringTest por lo que corre dentro del contexto
// de spring
public class ConexionBaseDeDatosTest extends SpringTest{

    @Test
    @Transactional @Rollback(true)
    public void pruebaConexion(){
        assertThat(getSession().isConnected()).isTrue();
    }
    
    @Test
    @Transactional @Rollback(false)
    @SuppressWarnings(value = { "unchecked" }) 
    public void testQueBuscaFarmaciasDeTurnoMartes() {
    	Farmacia farmacia1 = new Farmacia();
    	farmacia1.setDiaDeTurno("Martes");
    	farmacia1.setNombre("Farmacity");
    	getSession().save(farmacia1);
    	
    	Farmacia farmacia2 = new Farmacia();
    	farmacia2.setDiaDeTurno("Lunes");
    	farmacia2.setNombre("DrAhorro");
    	getSession().save(farmacia2);
    	
    	Farmacia farmacia3 = new Farmacia();
    	farmacia3.setDiaDeTurno("Jueves");
    	farmacia3.setNombre("Dofarma");
    	getSession().save(farmacia3);
    	
    	Farmacia farmacia4 = new Farmacia();
    	farmacia4.setDiaDeTurno("Martes");
    	farmacia4.setNombre("Central Oeste");
    	getSession().save(farmacia4);
    	
    	Session session1 = getSession();
    	List<Farmacia> listaMartes =
    			session1.createCriteria(Farmacia.class)
    			.add(Restrictions.eq("diaDeTurno", "Martes"))
    			.list();
    	
    	assertEquals(2, listaMartes.size());
    }
    
    @Test
    @Transactional @Rollback(false)
    @SuppressWarnings(value = { "unchecked" }) 
    public void testQueBuscaFarmaciasDeUnaCalle() {
    	
    	Direccion direccion1 = new Direccion();
    	direccion1.setCalle("Florencio Varela");
    	getSession().save(direccion1);
    	
    	Direccion direccion2 = new Direccion();
    	direccion2.setCalle("Corrientes");
    	getSession().save(direccion2);
    	
    	Farmacia farmacia1 = new Farmacia();
    	farmacia1.setDiaDeTurno("Martes");
    	farmacia1.setNombre("Farmacity");
    	farmacia1.setDireccion(direccion2);
    	getSession().save(farmacia1);
    	
    	Farmacia farmacia2 = new Farmacia();
    	farmacia2.setDiaDeTurno("Lunes");
    	farmacia2.setNombre("DrAhorro");
    	farmacia2.setDireccion(direccion1);
    	getSession().save(farmacia2);
    	
    	Farmacia farmacia3 = new Farmacia();
    	farmacia3.setDiaDeTurno("Jueves");
    	farmacia3.setNombre("Dofarma");
    	farmacia3.setDireccion(direccion1);
    	getSession().save(farmacia3);
    	
    	Farmacia farmacia4 = new Farmacia();
    	farmacia4.setDiaDeTurno("Martes");
    	farmacia4.setNombre("Central Oeste");
    	farmacia4.setDireccion(direccion1);
    	getSession().save(farmacia4);
    	
  	  	Session session1 = getSession();
    	List<Farmacia> listaCalle =
    			session1.createCriteria(Farmacia.class)
    			.add(Restrictions.eq("direccion", direccion1))
    			.list();
    	
    	assertEquals(3, listaCalle.size());
    }
    
}
